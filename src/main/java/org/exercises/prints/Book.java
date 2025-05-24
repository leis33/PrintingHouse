package org.exercises.prints;

import org.exercises.prints.enums.PageSize;

public class Book extends Edition {
    public Book(String title, int pages, PageSize size) {
        super(title, pages, size);
    }

    @Override
    public double getPrice() {
        return 5.0;
    }
}
