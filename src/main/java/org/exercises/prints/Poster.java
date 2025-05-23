package org.exercises.prints;

public class Poster extends Print {
    public Poster(String title, int pages, PageSize size) {
        super(title, pages, size);
    }

    @Override
    public double getPrice() {
        return 2.0;
    }
}
