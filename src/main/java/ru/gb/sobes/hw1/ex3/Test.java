package ru.gb.sobes.hw1.ex3;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(4.2));
        shapes.add(new Square(5.0));
        shapes.add(new Triangle(4.2, 2, 3.2));
        shapes.add(new Circle(3.2));

        shapes.forEach(x -> System.out.println(x.getShapeName() + " area: " + x.getArea()));
    }
}
