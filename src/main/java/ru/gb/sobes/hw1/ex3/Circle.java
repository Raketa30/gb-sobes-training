package ru.gb.sobes.hw1.ex3;

public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        super("radius");
        this.radius = radius;
    }

    public double getArea() {
        return 3.14 * radius * radius;
    }
}
