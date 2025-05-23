package org.exercises.entities;

public class Operator extends Employee {
    public Operator(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double getSalary(double revenue) {
        return baseSalary;
    }
}
