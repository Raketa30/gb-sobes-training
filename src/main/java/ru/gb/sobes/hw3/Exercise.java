package ru.gb.sobes.hw3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    2. Реализовать потокобезопасный счетчик с помощью интерфейса Lock.
 */

public class Exercise {
    public static void main(String[] args) {
        LockCounter cnt = new LockCounter(new ReentrantLock());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 25000000; i++) {
                cnt.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 25000000; i++) {
                cnt.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cnt.getCounter());
    }
}


class LockCounter {
    private int counter = 0;
    private final Lock lock;

    public LockCounter(Lock lock) {
        this.lock = lock;
    }

    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }

}
