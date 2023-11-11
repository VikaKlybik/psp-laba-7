package com.bsuir.laba72.service;

import com.bsuir.laba72.connection.DatabaseConnection;
import com.bsuir.laba72.dao.ParameterDAO;
import com.bsuir.laba72.dao.ParameterProductGroupDAO;
import com.bsuir.laba72.dao.ParameterValuesDAO;
import com.bsuir.laba72.entity.Parameter;
import com.bsuir.laba72.entity.ParameterProductGroup;
import com.bsuir.laba72.entity.ParameterValues;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ParameterService {
    private ParameterDAO parameterDAO;
    private ParameterProductGroupDAO parameterProductGroupDAO;
    private ParameterValuesDAO parameterValuesDAO;

    public ParameterService() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            this.parameterDAO = new ParameterDAO(connection);
            this.parameterProductGroupDAO = new ParameterProductGroupDAO(connection);
            this.parameterValuesDAO = new ParameterValuesDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Parameter> getAllParameters() {
        return parameterDAO.getAllParameters();
    }

    public void createParameter(Parameter parameter) {
        parameterDAO.create(parameter);
    }

    public List<Parameter> getParametersForProductGroup(int productGroupId) {
        return parameterDAO.getParametersForProductGroup(productGroupId);
    }

    public void createParameterProductGroup(ParameterProductGroup parameter) {
        parameterProductGroupDAO.create(parameter);
    }


    public List<ParameterValues> getAllParameterValuesForProduct(int productId) {
        return parameterValuesDAO.getByProductId(productId);
    }

    public void createParameterValue(int productId, int parameterId, String value) {
        Parameter parameter = parameterDAO.getParameterById(parameterId);
        ParameterValues parameterValues = new ParameterValues(0, productId, parameterId, parameter.getName(), value,parameter.getMeasure());
        parameterValuesDAO.create(parameterValues);
    }

    public List<Parameter> getAllNotUsedParameterForProduct(int productId) {
        return parameterDAO.getAllNotUsedParameterForProduct(productId);
    }
}