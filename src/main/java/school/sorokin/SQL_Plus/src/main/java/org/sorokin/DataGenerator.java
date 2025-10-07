package org.sorokin;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Random;

public class DataGenerator {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "admin";

    private static final int TOTAL_ORDERS = 40_000_000;
    private static final int BATCH_SIZE = 10_000;
    private static final int CUSTOMER_COUNT = 1000;
    private static final int PRODUCT_COUNT = 100;
    private static final int DAYS_RANGE = 425;

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        System.out.println("Начало генерации и вставки " + TOTAL_ORDERS + " заказов...");
        long startTime = System.currentTimeMillis();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            conn.setAutoCommit(false);
            String insertSQL = "INSERT INTO orders " +
                    "(customer_id, product_id, created_at, quantity, amount, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                for (int i = 1; i <= TOTAL_ORDERS; i++) {
                    int customerId = RANDOM.nextInt(CUSTOMER_COUNT) + 1;
                    int productId = RANDOM.nextInt(PRODUCT_COUNT) + 1;

                    long randomDays = RANDOM.nextInt(DAYS_RANGE);
                    LocalDateTime createdDate = LocalDateTime.now().minusDays(randomDays);
                    Timestamp createdTimestamp = Timestamp.valueOf(createdDate);

                    int quantity = RANDOM.nextInt(10) + 1;
                    double amount = Math.round((RANDOM.nextDouble() * 5000 + 10) * 100.0) / 100.0;
                    String status = RANDOM.nextDouble() < 0.9 ? "COMPLETED" : "NEW";
                    pstmt.setInt(1, customerId);
                    pstmt.setInt(2, productId);
                    pstmt.setTimestamp(3, createdTimestamp);
                    pstmt.setInt(4, quantity);
                    pstmt.setDouble(5, amount);
                    pstmt.setString(6, status);
                    pstmt.addBatch();
                    if (i % BATCH_SIZE == 0) {
                        pstmt.executeBatch();
                        conn.commit();
                        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
                        System.out.printf("Прогресс: %,d строк вставлено. Время: %d сек.\n", i, elapsed);
                    }
                }
                pstmt.executeBatch();
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
        System.out.printf("\nГенерация завершена. Всего вставлено %,d строк за %d секунд.\n",
                TOTAL_ORDERS, totalTime);
    }
}
