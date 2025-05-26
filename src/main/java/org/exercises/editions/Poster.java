package org.exercises.editions;

import org.exercises.exceptions.PrintingException;

public class Poster extends Edition {
    public Poster(String title, int pages, Paper paper) {
        super(title, pages, paper);
    }

    @Override
    public double getPrice(double additionalCost) throws PrintingException {
        return pages * paper.getPrice(additionalCost);
    }
}
