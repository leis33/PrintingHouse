package org.exercises.entities;

public class Manager extends Employee {
    public Manager(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double getSalary(double bonusPercent) {
        return baseSalary + baseSalary * bonusPercent / 100;
    }
}
