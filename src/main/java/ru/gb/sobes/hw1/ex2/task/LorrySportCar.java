package ru.gb.sobes.hw1.ex2.task;

public class LorrySportCar extends Car implements Upgradeable{

    public LorrySportCar(Engine engine, String color) {
        super(engine, color, "Lorry");
    }

    @Override
    public void move() {
        engine.start();
        System.out.println("start moving");
    }

    @Override
    public void stop() {
        System.out.println("stop moving");
        engine.shutdown();
    }

    @Override
    public void setEngine(Engine engine) {
        super.engine = engine;
    }

    @Override
    public void setColor(String color) {
        super.color = color;
    }
}
