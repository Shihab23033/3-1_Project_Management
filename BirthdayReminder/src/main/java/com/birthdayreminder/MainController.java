package com.birthdayreminder;

import com.birthdayreminder.dao.BirthdayDAO;
import com.birthdayreminder.model.Birthday;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> monthBox;
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField noteField;
    @FXML
    private TableView<Birthday> tableView;
    @FXML
    private TableColumn<Birthday, String> colName;
    @FXML
    private TableColumn<Birthday, String> colBirthDate;
    @FXML
    private TableColumn<Birthday, String> colAge;
    @FXML
    private TableColumn<Birthday, String> colNote;
    @FXML
    private Label todayLabel;

    private final BirthdayDAO dao = new BirthdayDAO();
    private final ObservableList<Birthday> data = FXCollections.observableArrayList();
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    public void initialize() {
        // fill months (Bangla)
        String[] months = {"জানুয়ারি","ফেব্রুয়ারি","মার্চ","এপ্রিল","মে","জুন","জুলাই","আগস্ট","সেপ্টেম্বর","অক্টোবর","নভেম্বর","ডিসেম্বর"};
        monthBox.getItems().addAll(months);

        colName.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));
        colBirthDate.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getBirthDate().toString()));
        colAge.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(String.valueOf(c.getValue().getAge())));
        colNote.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNote() == null ? "" : c.getValue().getNote()));

        tableView.setItems(data);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                populateForm(newSel);
            }
        });

        // load data
        Platform.runLater(() -> {
            refreshList();
            showTodayNotifications();
        });
    }

    private void populateForm(Birthday b) {
        nameField.setText(b.getName());
        datePicker.setValue(b.getBirthDate());
        noteField.setText(b.getNote());
    }

    @FXML
    private void onAdd() {
        String name = nameField.getText().trim();
        LocalDate date = datePicker.getValue();
        String note = noteField.getText().trim();
        if (name.isEmpty() || date == null) {
            showAlert("ত্রুটি", "অনুগ্রহ করে নাম এবং জন্মতারিখ পূরণ করুন।", Alert.AlertType.ERROR);
            return;
        }
        try {
            Birthday b = new Birthday(name, date, note);
            dao.create(b);
            refreshList();
            clearForm();
            showAlert("সফল", "জন্মদিন যোগ করা হয়েছে।", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("ত্রুটি", "ডাটাবেজ ত্রুটি: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onUpdate() {
        Birthday sel = tableView.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert("তথ্য নেই", "আপডেট করার জন্য একটি আইটেম নির্বাচন করুন।", Alert.AlertType.WARNING);
            return;
        }
        String name = nameField.getText().trim();
        LocalDate date = datePicker.getValue();
        String note = noteField.getText().trim();
        if (name.isEmpty() || date == null) {
            showAlert("ত্রুটি", "অনুগ্রহ করে নাম এবং জন্মতারিখ পূরণ করুন।", Alert.AlertType.ERROR);
            return;
        }
        sel.setName(name);
        sel.setBirthDate(date);
        sel.setNote(note);
        try {
            dao.update(sel);
            refreshList();
            showAlert("সফল", "জন্মদিন আপডেট করা হয়েছে।", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("ত্রুটি", "ডাটাবেজ ত্রুটি: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onDelete() {
        Birthday sel = tableView.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert("তথ্য নেই", "মুছতে একটি আইটেম নির্বাচন করুন।", Alert.AlertType.WARNING);
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "নিশ্চিত যে আপনি মুছতে চান?", ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        confirm.showAndWait().ifPresent(b -> {
            if (b == ButtonType.YES) {
                try {
                    dao.delete(sel.getId());
                    refreshList();
                    clearForm();
                    showAlert("সফল", "আইটেমটি মুছে ফেলা হয়েছে।", Alert.AlertType.INFORMATION);
                } catch (SQLException e) {
                    showAlert("ত্রুটি", "ডাটাবেজ ত্রুটি: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });
    }

    @FXML
    private void onSearch() {
        String name = searchField.getText().trim();
        String monthName = monthBox.getSelectionModel().getSelectedItem();
        try {
            if (!name.isEmpty()) {
                List<Birthday> res = dao.searchByName(name);
                data.setAll(res);
            } else if (monthName != null) {
                int monthIndex = monthBox.getSelectionModel().getSelectedIndex() + 1;
                List<Birthday> res = dao.searchByMonth(monthIndex);
                data.setAll(res);
            } else {
                refreshList();
            }
        } catch (SQLException e) {
            showAlert("ত্রুটি", "ডাটাবেজ ত্রুটি: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClear() {
        clearForm();
        tableView.getSelectionModel().clearSelection();
    }

    private void clearForm() {
        nameField.clear();
        datePicker.setValue(null);
        noteField.clear();
    }

    private void refreshList() {
        try {
            List<Birthday> all = dao.getAll();
            data.setAll(all);
        } catch (SQLException e) {
            showAlert("ত্রুটি", "ডাটাবেজ ত্রুটি: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showTodayNotifications() {
        try {
            List<Birthday> today = dao.getBirthdaysToday();
            if (today.isEmpty()) {
                todayLabel.setText("আজ কেউ জন্মদিন নয়।");
            } else {
                String names = today.stream().map(Birthday::getName).collect(Collectors.joining(", "));
                todayLabel.setText(names);
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("আজকের জন্মদিন");
                info.setHeaderText(null);
                info.setContentText("আজকার জন্মদিন: " + names);
                info.show();
            }
        } catch (SQLException e) {
            todayLabel.setText("আজকের তথ্য পাওয়া যায়নি।");
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}
