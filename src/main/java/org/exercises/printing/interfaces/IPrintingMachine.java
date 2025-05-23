package org.exercises.printing.interfaces;

import org.exercises.exceptions.PrintException;
import org.exercises.prints.PrintEdition;

public interface IPrintingMachine {
    public void loadPaper(int sheets);
    public void print(PrintEdition printEdition, boolean colorPrint) throws PrintException;
    public int getTotalPrintedPages();
}
