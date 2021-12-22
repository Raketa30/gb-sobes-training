package ru.gb.sobes.hw1.ex2.task;

public class LightWeightCar extends Car{
    private Locker locker;

    public LightWeightCar(Engine engine, String color, String model, Locker locker) {
        super(engine, color, model);
        this.locker = locker;
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

    public void open() {
        locker.open();
    }

    public void close() {
        locker.close();
    }
}
