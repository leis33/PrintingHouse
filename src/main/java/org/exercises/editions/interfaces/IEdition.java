package org.exercises.editions.interfaces;

import org.exercises.exceptions.PrintingException;

public interface IEdition {
    public double getPrice(double additionalCost) throws PrintingException;
}
