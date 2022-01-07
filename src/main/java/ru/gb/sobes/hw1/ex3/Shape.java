package ru.gb.sobes.hw1.ex3;

public abstract class Shape {
    private final String shapeName;

    protected Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    public abstract double getArea();

    public String getShapeName() {
        return shapeName;
    }
}
