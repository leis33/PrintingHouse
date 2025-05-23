package org.exercises.prints;

import org.exercises.prints.enums.PageSize;
import org.exercises.prints.interfaces.IPrintEdition;

public abstract class PrintEdition implements IPrintEdition {
    protected String title;
    protected int pages;
    protected PageSize size;
    protected double price;

    public PrintEdition(String title, int pages, PageSize size) {
        this.title = title;
        this.pages = pages;
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public abstract double getPrice();
}
