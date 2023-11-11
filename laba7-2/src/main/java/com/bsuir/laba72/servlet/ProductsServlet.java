package com.bsuir.laba72.servlet;

import com.bsuir.laba72.entity.Product;
import com.bsuir.laba72.service.ProductService;
import com.bsuir.laba72.entity.ProductGroup;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "productsServlet", value = "/products")
public class ProductsServlet extends HttpServlet {
    private ProductService productService;

    public void init() {
       productService = new ProductService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // List products
            List<Product> products = productService.getAllProducts();
            List<ProductGroup> productGroups = productService.getAllProductGroups();
            request.setAttribute("products", products);
            request.setAttribute("productGroups", productGroups);
            request.getRequestDispatcher("/WEB-INF/pages/product-list.jsp").forward(request, response);
       }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            // Create a new product
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int productGroupId = Integer.parseInt(request.getParameter("productGroupId"));
            Product product = new Product(0,name, description, new Date(), productGroupId);
            productService.create(product);
            response.sendRedirect(request.getContextPath() + "/products");
    }
}