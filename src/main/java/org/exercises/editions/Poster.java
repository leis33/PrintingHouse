package org.exercises.editions;

public class Poster extends Edition {
    public Poster(String title, int pages, Paper paper) {
        super(title, pages, paper);
    }

    @Override
    public double getPrice(double additionalCost) {
        return pages * paper.getPrice(additionalCost);
    }
}
