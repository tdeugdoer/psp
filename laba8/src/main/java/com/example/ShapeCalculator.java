package com.example;

public class ShapeCalculator {
    public static double calculateParallelepipedVolume(double length, double width, double height) {
        return length * width * height;
    }

    public static double calculateCubeVolume(double side) {
        return Math.pow(side, 3);
    }

    public static double calculateSphereVolume(double radius) {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    public static double calculateTetrahedronVolume(double side1) {
        return (Math.sqrt(2) / 12.0) * Math.pow(side1, 3);
    }

    public static double calculateTorusVolume(double majorRadius, double minorRadius) {
        return (Math.PI * Math.pow(minorRadius, 2)) * (2 * Math.PI * majorRadius);
    }
}