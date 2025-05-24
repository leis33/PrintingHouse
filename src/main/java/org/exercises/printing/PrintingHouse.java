package org.exercises.printing;

import org.exercises.entities.Employee;
import org.exercises.exceptions.PrintException;
import org.exercises.prints.Edition;

import java.util.*;
import java.io.*;

public class PrintingHouse {
    private final double discountThreshold;
    private final double discountPercent;
    private final double costPercentPerPageSize;
    private final double additionalCostPerPage;

    private List<Employee> employees = new ArrayList<>();
    private List<PrintingMachine> machines = new ArrayList<>();
    private double totalRevenue = 0;

    public PrintingHouse(double discountThreshold, double discountPercent, double additionalCostPerPage, double costPercentPerPageSize) {
        this.discountThreshold = discountThreshold;
        this.discountPercent = discountPercent;
        this.additionalCostPerPage = additionalCostPerPage;
        this.costPercentPerPageSize = costPercentPerPageSize;
    }

    public double getRevenue() {
        return totalRevenue;
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void addMachine(PrintingMachine machine) {
        machines.add(machine);
    }

    public void printEdition(Edition edition, int copies, PrintingMachine machine, boolean isColored) throws PrintException {
        PrintingMachine machineCopy = machines.get(machines.indexOf(machine));
        machineCopy.print(edition, copies, isColored);

        double price = edition.getPrice(additionalCostPerPage) * (1 + costPercentPerPageSize / 100.0);

        if (machineCopy.getTotalCopiesFromEdition(edition) > discountThreshold) {
            price *= (1 - discountPercent / 100.0);
        }
        totalRevenue += copies * price;
    }

    public void saveToFile(String filePath) throws IOException {
        try (PrintWriter out = new PrintWriter(filePath)) {
            out.println("Total revenue: " + totalRevenue);
            out.println("Total salaries: " + getTotalSalaries());
            out.println("Total page expenses: " + getTotalPageExpenses());
        }
    }

    public void serializeEmployees(String filePath) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(employees);
        }
    }

    public void deserializeEmployees(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            employees = (List<Employee>)in.readObject();
        }
    }

    private double getTotalSalaries() {
        double sum = 0;
        for (Employee employee : employees) {
            sum += employee.getSalary(totalRevenue);
        }
        return sum;
    }

    private double getTotalPageExpenses() {
        double sum = 0;
        for (PrintingMachine machine : machines) {
            sum += machine.getTotalExpenses();
        }
        return sum;
    }
}
