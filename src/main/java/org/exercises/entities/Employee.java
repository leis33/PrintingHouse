package org.exercises.entities;

import org.exercises.entities.interfaces.IEmployee;

import java.io.*;

public abstract class Employee implements Serializable, IEmployee {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public abstract double getSalary(double revenue);
}
