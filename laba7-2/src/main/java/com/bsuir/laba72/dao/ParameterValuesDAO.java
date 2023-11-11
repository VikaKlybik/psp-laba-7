package com.bsuir.laba72.dao;

import com.bsuir.laba72.entity.ParameterValues;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParameterValuesDAO {
    private Connection connection;

    public ParameterValuesDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new parameter value
    public void create(ParameterValues parameterValue) {
        String sql = "INSERT INTO parameter_values (product_id, parametr_id, value, name, measure) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, parameterValue.getProductId());
            preparedStatement.setInt(2, parameterValue.getParameterId());
            preparedStatement.setString(3, parameterValue.getValue());
            preparedStatement.setString(4, parameterValue.getName());
            preparedStatement.setString(5, parameterValue.getMeasure());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Read a parameter value by ID
    public List<ParameterValues> getByProductId(int productId) {
        List<ParameterValues> parameterValues = new ArrayList<>();
        String sql = "SELECT * FROM parameter_values WHERE product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                parameterValues.add(mapParameterValue(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return parameterValues;
    }

    // Update an existing parameter value
    public void update(ParameterValues parameterValue) {
        String sql = "UPDATE parameter_values SET product_id = ?, parameter_id = ?, value = ?, name = ?, measure = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, parameterValue.getProductId());
            preparedStatement.setInt(2, parameterValue.getParameterId());
            preparedStatement.setString(3, parameterValue.getValue());
            preparedStatement.setString(4, parameterValue.getName());
            preparedStatement.setString(5, parameterValue.getMeasure());
            preparedStatement.setInt(6, parameterValue.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Delete a parameter value by ID
    public void delete(int valueId) {
        String sql = "DELETE FROM parameter_values WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, valueId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Retrieve all parameter values
    public List<ParameterValues> getAllParameterValues() {
        List<ParameterValues> parameterValues = new ArrayList<>();
        String sql = "SELECT * FROM parameter_values";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                parameterValues.add(mapParameterValue(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return parameterValues;
    }

    private ParameterValues mapParameterValue(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int productId = resultSet.getInt("product_id");
        int parameterId = resultSet.getInt("parametr_id");
        String value = resultSet.getString("value");
        String name = resultSet.getString("name");
        String measure = resultSet.getString("measure");
        return new ParameterValues(id, productId, parameterId, name, value, measure);
    }
}