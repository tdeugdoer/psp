package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShapeCalculatorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String shapeType = request.getParameter("shapeType");
        double volume = 0.0;

        switch (shapeType) {
            case "parallelepiped":
                double length = Double.parseDouble(request.getParameter("length"));
                double width = Double.parseDouble(request.getParameter("width"));
                double height = Double.parseDouble(request.getParameter("height"));
                volume = ShapeCalculator.calculateParallelepipedVolume(length, width, height);
                break;
            case "cube":
                double side = Double.parseDouble(request.getParameter("side"));
                volume = ShapeCalculator.calculateCubeVolume(side);
                break;
            case "sphere":
                double radius = Double.parseDouble(request.getParameter("radius"));
                volume = ShapeCalculator.calculateSphereVolume(radius);
                break;
            case "tetrahedron":
                side = Double.parseDouble(request.getParameter("side1"));
                volume = ShapeCalculator.calculateTetrahedronVolume(side);
                break;
            case "torus":
                double majorRadius = Double.parseDouble(request.getParameter("majorRadius"));
                double minorRadius = Double.parseDouble(request.getParameter("minorRadius"));
                volume = ShapeCalculator.calculateTorusVolume(majorRadius, minorRadius);
                break;
        }

        request.setAttribute("volume", volume);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}