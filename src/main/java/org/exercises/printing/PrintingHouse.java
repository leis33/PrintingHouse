package org.exercises.printing;

import org.exercises.entities.Employee;
import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintingException;
import org.exercises.editions.Edition;

import java.util.*;
import java.io.*;

public class PrintingHouse {
    private final double discountThreshold;
    private final double discountPercent;
    private final double costPercentPerPageSize;
    private final double additionalCostPerPage;
    private final double revenueBonusThreshold;
    private final double bonusSalaryPercent;

    private List<Employee> employees = new ArrayList<>();
    private List<PrintingMachine> machines = new ArrayList<>();
    private double totalRevenue = 0;

    public PrintingHouse(double discountThreshold,
                         double discountPercent,
                         double additionalCostPerPage,
                         double costPercentPerPageSize,
                         double revenueBonusThreshold,
                         double bonusSalaryPercent) {
        this.discountThreshold = discountThreshold;
        this.discountPercent = discountPercent;
        this.additionalCostPerPage = additionalCostPerPage;
        this.costPercentPerPageSize = costPercentPerPageSize;
        this.revenueBonusThreshold = revenueBonusThreshold;
        this.bonusSalaryPercent = bonusSalaryPercent;
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

    public void printEdition(Edition edition, int copies, PrintingMachine machine, boolean isColored) throws PrintingException {
        if (copies <= 0) {
            throw new PrintingException(ExceptionMessages.INCORRECT_COPIES_VALUE);
        }

        int idx = machines.indexOf(machine);
        if (idx == -1) {
            throw new PrintingException(ExceptionMessages.MISSING_MACHINE);
        }

        PrintingMachine machineCopy = machines.get(idx);
        machineCopy.print(edition, copies, isColored);

        double price = edition.getPrice(additionalCostPerPage) * (1 + costPercentPerPageSize / 100.0);
        if (machineCopy.getTotalCopiesFromEdition(edition) > discountThreshold) {
            price *= (1 - discountPercent / 100.0);
        }
        totalRevenue += copies * price;
    }

    public void saveRecordsToFile(String filePath) throws IOException, PrintingException {
        try (PrintWriter out = new PrintWriter(filePath)) {
            out.println("Total revenue: " + totalRevenue);
            out.println("Total salaries: " + getTotalSalaries());
            out.println("Total page expenses: " + getTotalPageExpenses());
        }
    }

    public void readRecordsFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }


    public void serializeEmployees(String filePath) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(employees);
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> deserializeEmployeesAndGetNames(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Employee> employeesCopy = (List<Employee>)in.readObject();

            List<String> names = new ArrayList<>();
            for (Employee employee : employeesCopy) {
                names.add(employee.getName());
            }

            return names;
        }
    }

    private double getTotalSalaries() throws PrintingException {
        double bonus = totalRevenue > revenueBonusThreshold ? bonusSalaryPercent : 0;

        double sum = 0;
        for (Employee employee : employees) {
            sum += employee.getSalary(bonus);
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
