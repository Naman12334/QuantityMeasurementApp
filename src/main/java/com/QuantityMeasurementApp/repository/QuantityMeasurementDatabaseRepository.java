package com.QuantityMeasurementApp.repository;

import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final String URL = "jdbc:h2:./quantitydb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // ✅ Constructor → auto create table
    public QuantityMeasurementDatabaseRepository() {
        createTableIfNotExists();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ✅ FIX: Create table automatically
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS quantity_measurement (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "operation VARCHAR(50), " +
                "result VARCHAR(255))";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            throw new DatabaseException("Error creating table", e);
        }
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = "INSERT INTO quantity_measurement (operation, result) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getOperation());
            stmt.setString(2, entity.getResult());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error saving data", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        String sql = "SELECT * FROM quantity_measurement";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new QuantityMeasurementEntity(
                        rs.getString("operation"),
                        rs.getString("result")
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching data", e);
        }

        return list;
    }
}