package org.exercises.prints;

public class Book extends Edition {
    public Book(String title, int pages, Paper paper) {
        super(title, pages, paper);
    }

    @Override
    public double getPrice(double additionalCost) {
        return pages * paper.getPrice(additionalCost);
    }
}
