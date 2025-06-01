package org.exercises.entities;

import org.exercises.entities.interfaces.IEmployee;
import org.exercises.exceptions.PrintingException;

import java.io.*;

public abstract class Employee implements Serializable, IEmployee {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    };

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double getSalary(double bonusPercent) throws PrintingException;
}
