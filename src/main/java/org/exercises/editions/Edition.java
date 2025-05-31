package org.exercises.editions;

import org.exercises.editions.interfaces.IEdition;
import org.exercises.exceptions.PrintingException;

public abstract class Edition implements IEdition {
    protected String title;
    protected int pages;
    protected Paper paper;

    protected double price;

    public Edition(String title, int pages, Paper paper) {
        this.title = title;
        this.pages = pages;
        this.paper = paper;
    }

    public int getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public double getBasePrice() throws PrintingException {
        return paper.getPrice(0);
    }

    public abstract double getPrice(double additionalCost) throws PrintingException;
}
