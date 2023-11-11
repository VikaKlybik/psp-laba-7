package com.bsuir.laba72.servlet;

import com.bsuir.laba72.entity.Parameter;
import com.bsuir.laba72.entity.ParameterProductGroup;
import com.bsuir.laba72.entity.ProductGroup;
import com.bsuir.laba72.service.ParameterService;
import com.bsuir.laba72.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "detailProductGroupServlet", value = "/product-group/*")
public class DetailProductGroupServlet extends HttpServlet {

    private ProductService productService;
    private ParameterService parameterService;

    public void init() {
        productService = new ProductService();
        parameterService = new ParameterService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productGroupId = Integer.parseInt(request.getPathInfo().substring(1));
        ProductGroup selectedProductGroup = productService.getProductGroupById(productGroupId);
        List<Parameter> parameters = parameterService.getAllParameters();
        List<Parameter> selectedParameters = parameterService.getParametersForProductGroup(productGroupId);

        request.setAttribute("selectedParameters", selectedParameters);
        request.setAttribute("selectedProductGroup", selectedProductGroup);

        request.setAttribute("parameters", parameters);
        request.getRequestDispatcher("/WEB-INF/pages/product-group-detail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productGroupId = Integer.parseInt(request.getPathInfo().substring(1));
        Integer parameterId = Integer.parseInt(request.getParameter("parameterId"));
        ParameterProductGroup parameter = new ParameterProductGroup(parameterId,productGroupId);
        parameterService.createParameterProductGroup(parameter);
        response.sendRedirect(request.getContextPath() + "/product-group/"+productGroupId);
    }
}