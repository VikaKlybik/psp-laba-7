package com.bsuir.laba72.dao;

import com.bsuir.laba72.entity.Parameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParameterDAO {
    private Connection connection;

    public ParameterDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new parameter
    public void create(Parameter parameter) {
        String sql = "INSERT INTO parametr (name, measure) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, parameter.getName());
            preparedStatement.setString(2, parameter.getMeasure());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Read a parameter by ID
    public Parameter read(int parameterId) {
        String sql = "SELECT * FROM parametr WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, parameterId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapParameter(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return null;
    }

    // Update an existing parameter
    public void update(Parameter parameter) {
        String sql = "UPDATE parametr SET name = ?, measurementUnit = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, parameter.getName());
            preparedStatement.setString(2, parameter.getMeasure());
            preparedStatement.setInt(3, parameter.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    public List<Parameter> getParametersForProductGroup(int productGroupId) {
        List<Parameter> parameters = new ArrayList<>();
        String sql = "SELECT p.* FROM parametr p " +
                "INNER JOIN parametr_product_group pg ON p.id = pg.parametr_id " +
                "WHERE pg.product_group_id = ?";
        return getAllParameterBy(productGroupId, sql);
    }

    public Parameter getParameterById(int parameterId) {
        List<Parameter> parameters = new ArrayList<>();
        String sql = "SELECT * FROM parametr where id = ?";
        return getAllParameterBy(parameterId, sql).get(0);
    }

    // Delete a parameter by ID
    public void delete(int parameterId) {

        String sql = "DELETE FROM parametr WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, parameterId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Retrieve all parameters
    public List<Parameter> getAllParameters() {
        List<Parameter> parameters = new ArrayList<>();
        String sql = "SELECT * FROM parametr";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                parameters.add(mapParameter(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return parameters;
    }

    private Parameter mapParameter(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String measurementUnit = resultSet.getString("measure");
        return new Parameter(id, name, measurementUnit);
    }

    public List<Parameter> getAllNotUsedParameterForProduct(int productId) {
        List<Parameter> parameters = new ArrayList<>();
        String sql = "SELECT * FROM parametr p " +
                "WHERE NOT EXISTS (SELECT 1 FROM parameter_values pv " +
                "                  WHERE pv.parametr_id = p.id " +
                "                  AND pv.product_id = ?)";
        return getAllParameterBy(productId, sql);
    }

    private List<Parameter> getAllParameterBy(int id, String sql) {
        List<Parameter> parameters = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                parameters.add(mapParameter(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return parameters;
    }
}