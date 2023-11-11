package com.bsuir.laba72.servlet;

import com.bsuir.laba72.entity.ParameterValues;
import com.bsuir.laba72.entity.Product;
import com.bsuir.laba72.entity.ProductGroup;
import com.bsuir.laba72.service.ParameterService;
import com.bsuir.laba72.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "updateParameterValuesServlet", value = "/create-value")
public class UpdateParameterValuesServlet extends HttpServlet {
    private ParameterService parameterService;

    public void init() {
       parameterService = new ParameterService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String value = request.getParameter("value");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int parameterId = Integer.parseInt(request.getParameter("parameterId"));
        parameterService.createParameterValue(productId, parameterId, value);
        response.sendRedirect(request.getContextPath() + "/products/"+productId);
    }
}