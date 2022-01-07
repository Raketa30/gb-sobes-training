package ru.gb.sobes.hw1.ex3;

public class Square extends Shape {
    private final double side;

    public Square(double side) {
        super("square");
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }
}
