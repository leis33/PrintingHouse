package org.exercises.prints;

public class Newspaper extends Edition {
    public Newspaper(String title, int pages, Paper paper) {
        super(title, pages, paper);
    }

    @Override
    public double getPrice(double additionalCost) {
        return pages * paper.getPrice(additionalCost);
    }
}
