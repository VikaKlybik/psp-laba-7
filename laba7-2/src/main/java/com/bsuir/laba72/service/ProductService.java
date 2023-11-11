package com.bsuir.laba72.service;

import com.bsuir.laba72.connection.DatabaseConnection;
import com.bsuir.laba72.dao.ParameterDAO;
import com.bsuir.laba72.dao.ProductDAO;
import com.bsuir.laba72.dao.ProductGroupDAO;
import com.bsuir.laba72.entity.Parameter;
import com.bsuir.laba72.entity.Product;
import com.bsuir.laba72.entity.ProductGroup;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;
    private ProductGroupDAO productGroupDAO;
    private ParameterDAO parameterDAO;


    public ProductService() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            this.productDAO = new ProductDAO(connection);
            this.productGroupDAO = new ProductGroupDAO(connection);
            this.parameterDAO = new ParameterDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void create(Product product) {
        productDAO.create(product);
    }

    public List<ProductGroup> getAllProductGroups() {
        return productGroupDAO.getAllProductGroups();
    }

    public Product getProductById(int productId) {
        return productDAO.getProductById(productId);
    }

    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    public void createProductGroup(ProductGroup productGroup) {
        productGroupDAO.create(productGroup);
    }

    public ProductGroup getProductGroupById(int productGroupId) {
        return productGroupDAO.getProductGroupById(productGroupId);
    }
}