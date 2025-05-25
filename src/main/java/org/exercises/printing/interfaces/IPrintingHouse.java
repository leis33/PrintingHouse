package org.exercises.printing.interfaces;

import org.exercises.entities.Employee;
import org.exercises.exceptions.PrintingException;
import org.exercises.printing.PrintingMachine;
import org.exercises.prints.Edition;

import java.io.IOException;

public interface IPrintingHouse {
    public void hire(Employee employee);
    public void addMachine(PrintingMachine machine);
    public void printEdition(Edition edition, int copies, PrintingMachine machine, boolean isColored) throws PrintingException;
    public void saveRecordsToFile(String filePath) throws IOException;
}
