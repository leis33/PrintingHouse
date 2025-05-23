package org.exercises.prints;

class Book extends Print {
    public Book(String title, int pages, PageSize size) {
        super(title, pages, size);
    }

    @Override
    public double getPrice() {
        return 5.0;
    }
}
