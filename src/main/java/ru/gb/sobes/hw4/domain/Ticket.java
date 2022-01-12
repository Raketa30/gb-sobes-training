package ru.gb.sobes.hw4.domain;

public class Ticket {
    private int seat;
    private Session session;

    public Ticket() {
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
