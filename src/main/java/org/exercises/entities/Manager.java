package org.exercises.entities;

import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintingException;

public class Manager extends Employee {
    public Manager(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double getSalary(double bonusPercent) throws PrintingException {
        if (bonusPercent < 0) {
            throw new PrintingException(ExceptionMessages.INCORRECT_BONUS_VALUE);
        }

        return baseSalary + baseSalary * bonusPercent / 100;
    }
}
