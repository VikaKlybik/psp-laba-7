package com.bsuir.laba72.dao;

import com.bsuir.laba72.entity.ParameterProductGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParameterProductGroupDAO {
    private Connection connection;

    public ParameterProductGroupDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new parameter-product group relationship
    public void create(ParameterProductGroup parameterProductGroup) {
        String sql = "INSERT INTO parametr_product_group (parametr_id, product_group_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, parameterProductGroup.getParameterId());
            preparedStatement.setInt(2, parameterProductGroup.getProductGroupId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Read a parameter-product group relationship by parameter and group IDs
    public ParameterProductGroup read(int parameterId, int productGroupId) {
        String sql = "SELECT * FROM parametr_product_group WHERE parametr_id = ? AND product_group_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, parameterId);
            preparedStatement.setInt(2, productGroupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapParameterProductGroup(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return null;
    }

    // Delete a parameter-product group relationship by parameter and group IDs
    public void delete(int parameterId, int productGroupId) {
        String sql = "DELETE FROM parametr_product_group WHERE parametr_id = ? AND product_group_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, parameterId);
            preparedStatement.setInt(2, productGroupId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Retrieve all parameter-product group relationships
    public List<ParameterProductGroup> getAllParameterProductGroups() {
        List<ParameterProductGroup> parameterProductGroups = new ArrayList<>();
        String sql = "SELECT * FROM parametr_product_group";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                parameterProductGroups.add(mapParameterProductGroup(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return parameterProductGroups;
    }

    private ParameterProductGroup mapParameterProductGroup(ResultSet resultSet) throws SQLException {
        int parameterId = resultSet.getInt("parametr_id");
        int productGroupId = resultSet.getInt("product_group_id");
        return new ParameterProductGroup(parameterId, productGroupId);
    }
}