package org.exercises.prints;

import org.exercises.prints.interfaces.IPrint;

enum PageSize {
    A5,
    A4,
    A3,
    A2,
    A1
}

abstract class Print implements IPrint {
    protected String title;
    protected int pages;
    protected PageSize size;
    protected double price;

    public Print(String title, int pages, PageSize size) {
        this.title = title;
        this.pages = pages;
        this.size = size;
    }

    public int getTotalPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public abstract double getPrice();
}
