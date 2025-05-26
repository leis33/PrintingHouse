package org.exercises.entities.interfaces;

import org.exercises.exceptions.PrintingException;

public interface IEmployee {
    public double getSalary(double revenue) throws PrintingException;
}
