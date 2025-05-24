package org.exercises.printing;

import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintException;
import org.exercises.prints.Edition;

import java.util.*;

public class PrintingMachine {
    private final boolean supportsColor;
    private final int maxSheets;
    private final int pagesPerMinute;
    private int loadedSheets;

    private Map<Edition, Integer> printed;

    public PrintingMachine(boolean supportsColor, int maxSheets, int pagesPerMinute) {
        this.supportsColor = supportsColor;
        this.maxSheets = maxSheets;
        this.pagesPerMinute = pagesPerMinute;
        this.printed = new HashMap<>();
    }

    public void loadPaper(int sheets) {
        if (sheets + loadedSheets > maxSheets) {
            loadedSheets = maxSheets;
        } else {
            loadedSheets += sheets;
        }
    }

    public void print(Edition edition, int copies, boolean isColored) throws PrintException {
        if (isColored && !supportsColor) {
            throw new PrintException(ExceptionMessages.UNSUPPORTED_COLOR_PRINT);
        }

        int pages = edition.getPages();
        int count = 0;

        while (count < copies) {
            if (loadedSheets < pages) {
                throw new PrintException(ExceptionMessages.INSUFFICIENT_PAPER);
            }

            loadedSheets -= pages;
            count++;
        }

        printed.put(edition, printed.getOrDefault(edition, 0) + count);
    }

    public int getTotalCopiesFromEdition(Edition edition) {
        return printed.getOrDefault(edition, 0);
    }

    /*public int getTotalPrintedPages() {
        int sum = 0;
        for (Map.Entry<Edition, Integer> entry : printed.entrySet()) {
            sum += entry.getKey().getPages() * entry.getValue();
        }

        return sum;
    }*/

    public double getTotalExpenses() {
        double sum = 0;
        for (Map.Entry<Edition, Integer> entry : printed.entrySet()) {
            Edition edition = entry.getKey();
            sum += edition.getPages() * entry.getValue() * edition.getTypeBasePrice();
        }

        return sum;
    }
}
