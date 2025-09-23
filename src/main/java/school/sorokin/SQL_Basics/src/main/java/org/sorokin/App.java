package org.sorokin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres1";
        String user = "postgres";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Подключение к базе данных успешно!");

            String sql = "SELECT s.name AS student_name, t.name AS tutor_name " +
                    "FROM students s " +
                    "JOIN tutors t ON s.tutor_id = t.tutor_id;";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                System.out.println("--- Список студентов и их репетиторов ---");
                while (resultSet.next()) {
                    String studentName = resultSet.getString("student_name");
                    String tutorName = resultSet.getString("tutor_name");
                    System.out.println("Студент: " + studentName + ", Репетитор: " + tutorName);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
    }
}