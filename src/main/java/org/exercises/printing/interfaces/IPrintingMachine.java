package org.exercises.printing.interfaces;

import org.exercises.exceptions.PrintException;
import org.exercises.prints.Edition;

public interface IPrintingMachine {
    public void loadPaper(int sheets);
    public void print(Edition edition, boolean colorPrint) throws PrintException;
    public int getTotalPrintedPages();
}
