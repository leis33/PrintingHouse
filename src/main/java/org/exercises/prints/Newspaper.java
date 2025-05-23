package org.exercises.prints;

import org.exercises.prints.enums.PageSize;

public class Newspaper extends Print {
    public Newspaper(String title, int pages, PageSize size) {
        super(title, pages, size);
    }

    @Override
    public double getPrice() {
        return 1.0;
    }
}
