package ru.gb.sobes.hw1.ex2.task;

public class CentralLocker implements Locker {
    @Override
    public void open() {
        System.out.println("Open all doors");
    }

    @Override
    public void close() {
        System.out.println("Close all doors");
    }
}
