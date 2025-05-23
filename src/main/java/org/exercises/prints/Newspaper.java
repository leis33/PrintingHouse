package org.exercises.prints;

public class Newspaper extends Print {
    public Newspaper(String title, int pages, PageSize size) {
        super(title, pages, size);
    }

    @Override
    public double getPrice() {
        return 1.0;
    }
}
