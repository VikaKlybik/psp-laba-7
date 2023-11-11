package com.bsuir.laba72.servlet;

import com.bsuir.laba72.entity.Parameter;
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

@WebServlet(name = "detailProductsServlet", value = "/products/*")
public class DetailProductsServlet extends HttpServlet {
    private ProductService productService;
    private ParameterService parameterService;

    public void init() {
       productService = new ProductService();
       parameterService = new ParameterService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getPathInfo().substring(1));
        Product product = productService.getProductById(productId);
        List<ProductGroup> productGroups = productService.getAllProductGroups();
        List<ParameterValues> parameterValues = parameterService.getAllParameterValuesForProduct(productId);
        List<Parameter> parameters = parameterService.getAllNotUsedParameterForProduct(productId);
        request.setAttribute("product", product);
        request.setAttribute("productGroups", productGroups);
        request.setAttribute("parameterValues", parameterValues);
        request.setAttribute("notUsedParameters", parameters);
        request.getRequestDispatcher("/WEB-INF/pages/product-update.jsp").forward(request, response);
       }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getPathInfo().substring(1));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Product product = new Product(productId, name, description, new Date(),0);
        productService.updateProduct(product);
        response.sendRedirect(request.getContextPath() + "/products/"+productId);
    }
}