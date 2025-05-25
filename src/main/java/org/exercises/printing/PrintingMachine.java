package org.exercises.printing;

import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintingException;
import org.exercises.printing.interfaces.IPrintingMachine;
import org.exercises.editions.Edition;

import java.util.*;

public class PrintingMachine implements IPrintingMachine {
    private final boolean supportsColor;
    private final int maxSheets;
    private final int pagesPerMinute;
    private int loadedSheets = 0;


    private Map<Edition, Integer> printed;

    public PrintingMachine(boolean supportsColor, int maxSheets, int pagesPerMinute) {
        this.supportsColor = supportsColor;
        this.maxSheets = maxSheets;
        this.pagesPerMinute = pagesPerMinute;
        this.printed = new HashMap<>();
    }

    public boolean isSupportsColor() {
        return supportsColor;
    }

    public int getMaxSheets() {
        return maxSheets;
    }

    public int getPagesPerMinute() {
        return pagesPerMinute;
    }

    public int getLoadedSheets() {
        return loadedSheets;
    }

    public void loadPaper(int sheets) throws PrintingException {
        if (sheets <= 0) {
            throw new PrintingException(ExceptionMessages.INCORRECT_LOAD_PAPER_VALUE);
        }

        if (sheets + loadedSheets > maxSheets) {
            loadedSheets = maxSheets;
        } else {
            loadedSheets += sheets;
        }
    }

    public void print(Edition edition, int copies, boolean isColored) throws PrintingException {
        if (isColored && !supportsColor) {
            throw new PrintingException(ExceptionMessages.UNSUPPORTED_COLOR_PRINT);
        }

        int pages = edition.getPages();
        int count = 0;

        while (count < copies) {
            if (loadedSheets < pages) {
                throw new PrintingException(ExceptionMessages.INSUFFICIENT_PAPER);
            }

            loadedSheets -= pages;
            count++;
        }

        printed.put(edition, printed.getOrDefault(edition, 0) + count);
    }

    public int getTotalCopiesFromEdition(Edition edition) {
        return printed.getOrDefault(edition, 0);
    }

    public double getTotalExpenses() {
        double sum = 0;
        for (Map.Entry<Edition, Integer> entry : printed.entrySet()) {
            Edition edition = entry.getKey();
            sum += edition.getPages() * entry.getValue() * edition.getTypeBasePrice();
        }

        return sum;
    }
}
