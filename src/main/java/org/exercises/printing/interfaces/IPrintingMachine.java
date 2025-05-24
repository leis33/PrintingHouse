package org.exercises.printing.interfaces;

import org.exercises.exceptions.PrintingException;
import org.exercises.prints.Edition;

public interface IPrintingMachine {
    public void loadPaper(int sheets);
    public void print(Edition edition, int copies, boolean colorPrint) throws PrintingException;
    public double getTotalExpenses();
}
