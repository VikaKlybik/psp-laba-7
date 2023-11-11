package com.bsuir.laba72.dao;

import com.bsuir.laba72.entity.Parameter;
import com.bsuir.laba72.entity.ProductGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductGroupDAO {
    private Connection connection;

    public ProductGroupDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new product group
    public void create(ProductGroup productGroup) {
        String sql = "INSERT INTO product_group (name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, productGroup.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Read a product group by ID
    public ProductGroup read(int productGroupId) {
        String sql = "SELECT * FROM product_group WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productGroupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapProductGroup(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return null;
    }

    // Update an existing product group
    public void update(ProductGroup productGroup) {
        String sql = "UPDATE product_group SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, productGroup.getName());
            preparedStatement.setInt(2, productGroup.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Delete a product group by ID
    public void delete(int productGroupId) {
        String sql = "DELETE FROM product_group WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productGroupId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Retrieve all product groups
    public List<ProductGroup> getAllProductGroups() {
        List<ProductGroup> productGroups = new ArrayList<>();
        String sql = "SELECT * FROM product_group";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productGroups.add(mapProductGroup(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return productGroups;
    }

    public ProductGroup getProductGroupById(int productGroupId) {
        String sql = "SELECT * FROM product_group WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productGroupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapProductGroup(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
        return null;
    }


    private ProductGroup mapProductGroup(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new ProductGroup(id, name);
    }
}