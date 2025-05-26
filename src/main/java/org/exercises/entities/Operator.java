package org.exercises.entities;

import org.exercises.exceptions.PrintingException;

public class Operator extends Employee {
    public Operator(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double getSalary(double bonusPercent) throws PrintingException {
        return baseSalary;
    }
}
