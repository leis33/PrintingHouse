package org.exercises.prints;

import org.exercises.prints.enums.PageSize;

public class Poster extends PrintEdition {
    public Poster(String title, int pages, PageSize size) {
        super(title, pages, size);
    }

    @Override
    public double getPrice() {
        return 2.0;
    }
}
