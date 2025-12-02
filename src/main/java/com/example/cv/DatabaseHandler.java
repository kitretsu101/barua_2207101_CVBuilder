package com.example.cv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:cv_database.db";

    public static void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS cv_table (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT," +
                "phone TEXT," +
                "address TEXT," +
                "education TEXT," +
                "skills TEXT," +
                "work TEXT," +
                "projects TEXT" +
                ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCV(CV cv) {
        String sql = "INSERT INTO cv_table(name, email, phone, address, education, skills, work, projects) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cv.getName());
            pstmt.setString(2, cv.getEmail());
            pstmt.setString(3, cv.getPhone());
            pstmt.setString(4, cv.getAddress());
            pstmt.setString(5, cv.getEducation());
            pstmt.setString(6, cv.getSkills());
            pstmt.setString(7, cv.getWork());
            pstmt.setString(8, cv.getProjects());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<CV> getAllCVs() {
        List<CV> cvList = new ArrayList<>();
        String sql = "SELECT * FROM cv_table";

        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CV cv = new CV();
                cv.setId(rs.getInt("id"));
                cv.name = rs.getString("name");
                cv.email = rs.getString("email");
                cv.phone = rs.getString("phone");
                cv.address = rs.getString("address");
                cv.education = rs.getString("education");
                cv.skills = rs.getString("skills");
                cv.work = rs.getString("work");
                cv.projects = rs.getString("projects");
                cvList.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cvList;
    }

    public static void updateCV(CV cv) {
        String sql = "UPDATE cv_table SET name=?, email=?, phone=?, address=?, education=?, skills=?, work=?, projects=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cv.getName());
            pstmt.setString(2, cv.getEmail());
            pstmt.setString(3, cv.getPhone());
            pstmt.setString(4, cv.getAddress());
            pstmt.setString(5, cv.getEducation());
            pstmt.setString(6, cv.getSkills());
            pstmt.setString(7, cv.getWork());
            pstmt.setString(8, cv.getProjects());
            pstmt.setInt(9, cv.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCV(int id) {
        String sql = "DELETE FROM cv_table WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
