package org.exercises.entities;

public class Manager extends Employee {
    private final double bonusPercent;
    private final double threshold;

    public Manager(String name, double baseSalary, double bonusSalaryPercent, double threshold) {
        super(name, baseSalary);
        this.bonusPercent = bonusSalaryPercent;
        this.threshold = threshold;
    }

    @Override
    public double getSalary(double revenue) {
        if (revenue > threshold) {
            return baseSalary + baseSalary * bonusPercent;
        }
        return baseSalary;
    }
}
