package ru.gb.sobes.hw4.domain;

import java.util.Objects;

public class Film {
    private long id;
    private String title;
    private int length;

    public Film() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return id == film.id &&
                length == film.length &&
                title.equals(film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, length);
    }
}
