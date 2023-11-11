package com.bsuir.laba72.servlet;

import com.bsuir.laba72.entity.Parameter;
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

@WebServlet(name = "parametersServlet", value = "/parameter")
public class ParametersServlet extends HttpServlet {
    private ParameterService parameterService;

    public void init() {
       parameterService = new ParameterService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Parameter> parameters = parameterService.getAllParameters();
            request.setAttribute("parameters", parameters);
            request.getRequestDispatcher("WEB-INF/pages/parameter-list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String measure = request.getParameter("measure");
        Parameter parameter = new Parameter(0,name, measure);
        parameterService.createParameter(parameter);
        response.sendRedirect(request.getContextPath() + "/parameter");
    }
}