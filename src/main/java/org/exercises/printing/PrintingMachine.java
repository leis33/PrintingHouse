package org.exercises.printing;

import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintException;
import org.exercises.prints.PrintEdition;

import java.util.*;

public class PrintingMachine {
    private final boolean hasColor;
    private final int maxSheets;
    private final int pagesPerMinute;
    private int loadedSheets;

    private Map<PrintEdition, Integer> printed;

    public PrintingMachine(boolean hasColor, int maxSheets, int pagesPerMinute) {
        this.hasColor = hasColor;
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

    public void print(PrintEdition printEdition, boolean colorPrint) throws PrintException {
        if (colorPrint && !hasColor) {
            throw new PrintException(ExceptionMessages.UNSUPPORTED_COLOR_PRINT);
        }

        int pages = printEdition.getPages();

        if (loadedSheets < pages) {
            throw new PrintException(ExceptionMessages.INSUFFICIENT_PAPER);
        }

        loadedSheets -= pages;
        printed.put(printEdition, printed.getOrDefault(printEdition, 0) + printEdition.getPages());
    }

    public int getTotalPrintedPages() {
        int sum = 0;
        for (Map.Entry<PrintEdition, Integer> entry : printed.entrySet()) {
            sum += entry.getKey().getPages() * entry.getValue();
        }

        return sum;
    }
}
