package com.birthdayreminder.dao;

import com.birthdayreminder.db.DBUtil;
import com.birthdayreminder.model.Birthday;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BirthdayDAO {

    public List<Birthday> getAll() throws SQLException {
        String sql = "SELECT id, name, birth_date, note FROM birthdays ORDER BY MONTH(birth_date), DAY(birth_date)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            List<Birthday> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        }
    }

    public Birthday create(Birthday b) throws SQLException {
        String sql = "INSERT INTO birthdays (name, birth_date, note) VALUES (?, ?, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, b.getName());
            ps.setDate(2, Date.valueOf(b.getBirthDate()));
            ps.setString(3, b.getNote());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    b.setId(keys.getInt(1));
                }
            }
            return b;
        }
    }

    public boolean update(Birthday b) throws SQLException {
        String sql = "UPDATE birthdays SET name = ?, birth_date = ?, note = ? WHERE id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getName());
            ps.setDate(2, Date.valueOf(b.getBirthDate()));
            ps.setString(3, b.getNote());
            ps.setInt(4, b.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM birthdays WHERE id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Birthday> searchByName(String name) throws SQLException {
        String sql = "SELECT id, name, birth_date, note FROM birthdays WHERE name LIKE ? ORDER BY MONTH(birth_date), DAY(birth_date)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                List<Birthday> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
                return list;
            }
        }
    }

    public List<Birthday> searchByMonth(int month) throws SQLException {
        String sql = "SELECT id, name, birth_date, note FROM birthdays WHERE MONTH(birth_date) = ? ORDER BY DAY(birth_date)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, month);
            try (ResultSet rs = ps.executeQuery()) {
                List<Birthday> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
                return list;
            }
        }
    }

    public List<Birthday> getBirthdaysToday() throws SQLException {
        String sql = "SELECT id, name, birth_date, note FROM birthdays WHERE MONTH(birth_date) = MONTH(CURDATE()) AND DAY(birth_date) = DAY(CURDATE())";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            List<Birthday> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        }
    }

    private Birthday mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        LocalDate d = rs.getDate("birth_date").toLocalDate();
        String note = rs.getString("note");
        return new Birthday(id, name, d, note);
    }
}
