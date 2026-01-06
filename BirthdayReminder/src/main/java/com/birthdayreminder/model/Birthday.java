package com.birthdayreminder.model;

import java.time.LocalDate;
import java.time.Period;

public class Birthday {
    private int id;
    private String name;
    private LocalDate birthDate;
    private String note;

    public Birthday() {}

    public Birthday(int id, String name, LocalDate birthDate, String note) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.note = note;
    }

    public Birthday(String name, LocalDate birthDate, String note) {
        this(0, name, birthDate, note);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAge() {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return name + " (" + birthDate + ")";
    }
}
