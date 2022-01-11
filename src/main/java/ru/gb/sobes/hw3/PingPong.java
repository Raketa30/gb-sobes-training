package ru.gb.sobes.hw3;

/*
    1. Реализовать программу, в которой два потока поочередно пишут ping и pong.
 */
public class PingPong {
        private final Object lock = new Object();
        private volatile String pp = "Ping";

    public static void main(String[] args) {
        doPingPong(new PingPong());
    }

    private static void doPingPong(PingPong pingPong) {
        new Thread(pingPong::printPing).start();
        new Thread(pingPong::printPong).start();
    }

    private void printPing() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 20; i++) {
                    while (!pp.equals("Ping")) {
                        lock.wait();
                    }
                    System.out.println("Ping");
                    pp = "Pong";
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printPong() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 20; i++) {
                    while (!pp.equals("Pong")) {
                        lock.wait();
                    }
                    System.out.println("Pong");
                    pp = "Ping";
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
