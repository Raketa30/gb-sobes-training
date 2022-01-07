package ru.gb.sobes.hw1.ex2.task;

public abstract class Car implements Movable {
    protected Engine engine;
    protected String color;
    protected String model;

    protected Car(Engine engine, String color, String model) {
        this.engine = engine;
        this.color = color;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getColor() {
        return color;
    }
}
