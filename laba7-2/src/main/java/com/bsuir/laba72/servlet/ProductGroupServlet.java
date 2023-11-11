package com.bsuir.laba72.servlet;

import com.bsuir.laba72.entity.Parameter;
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

@WebServlet(name = "productGroupServlet", value = "/product-group")
public class ProductGroupServlet extends HttpServlet {

    private ProductService productService;
    private ParameterService parameterService;

    public void init() {
        productService = new ProductService();
        parameterService = new ParameterService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductGroup> productGroups = productService.getAllProductGroups();
        request.setAttribute("productGroups", productGroups);
        request.getRequestDispatcher("WEB-INF/pages/product-group-list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        ProductGroup productGroup = new ProductGroup(0,name);
        productService.createProductGroup(productGroup);
        response.sendRedirect(request.getContextPath() + "/product-group");
    }
}