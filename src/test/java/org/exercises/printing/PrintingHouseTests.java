package org.exercises.printing;

import org.exercises.editions.*;
import org.exercises.editions.enums.PageSize;
import org.exercises.editions.enums.PaperType;
import org.exercises.entities.Employee;
import org.exercises.entities.Manager;
import org.exercises.entities.Operator;
import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintingException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrintingHouseTests {
    private PrintingHouse printingHouse;
    private PrintingMachine machine;
    private Edition edition;

    private Operator operator;
    private Manager manager;

    private final double DISCOUNT_THRESHOLD = 50;
    private final double DISCOUNT_PERCENTAGE = 10;
    private final double ADDITIONAL_COST_PER_PAGE = 0.05;
    private final double COST_PERCENT_PER_PAGE_SIZE = 5;
    private final double REVENUE_BONUS_THRESHOLD = 900;
    private final double BONUS_SALARY_PERCENT = 25;


    @BeforeEach
    void setup() {
        machine = new PrintingMachine(false, 40000, 12);
        edition = new Book("Test", 400, new Paper(PaperType.REGULAR, PageSize.A5));

        operator = new Operator("Operator", 1000);
        manager = new Manager("Manager", 1200);

        printingHouse = new PrintingHouse(
                DISCOUNT_THRESHOLD,
                DISCOUNT_PERCENTAGE,
                ADDITIONAL_COST_PER_PAGE,
                COST_PERCENT_PER_PAGE_SIZE,
                REVENUE_BONUS_THRESHOLD,
                BONUS_SALARY_PERCENT);

        printingHouse.hire(operator);
        printingHouse.hire(manager);
        printingHouse.addMachine(machine);
    }

    @Test
    void whenHireEmployee_thenSaveToArrayList() {
        // when
        Employee employee1 = new Operator("test123", 1200);
        printingHouse.hire(employee1);

        // then
        assertTrue(printingHouse.hasEmployee(employee1));
    }

    @Test
    void whenAddMachine_thenSaveToArrayList() {
        // when
        PrintingMachine machine1 = new PrintingMachine(true, 10000, 12);
        printingHouse.addMachine(machine1);

        // then
        assertTrue(printingHouse.hasMachine(machine1));
    }

    @Test
    void whenPrintingZeroOrNegativeCopies_thenThrowException() throws PrintingException {
        // when
        machine.loadPaper(30000);
        Exception ex = assertThrows(PrintingException.class, () -> printingHouse.printEdition(edition, 0, machine, false));

        // then
        assertEquals(ExceptionMessages.INCORRECT_COPIES_VALUE, ex.getMessage());
    }

    @Test
    void whenPrintingFromWrongMachine_thenThrowException() throws PrintingException {
        // when
        PrintingMachine machine1 = new PrintingMachine(true, 10000, 12);
        machine1.loadPaper(30000);
        Exception ex = assertThrows(PrintingException.class, () -> printingHouse.printEdition(edition, 2, machine1, false));

        // then
        assertEquals(ExceptionMessages.MISSING_MACHINE, ex.getMessage());
    }

    @Test
    void whenPrintingCorrectly_thenAddToRevenue() throws PrintingException {
        // when
        machine.loadPaper(30000);
        printingHouse.printEdition(edition, 5, machine, false);

        double expected = edition.getPrice(ADDITIONAL_COST_PER_PAGE) * (1 + COST_PERCENT_PER_PAGE_SIZE / 100.0) * 5;

        // then
        assertEquals(Math.floor(expected), Math.floor(printingHouse.getRevenue()));
    }

    @Test
    void whenPrintingPastThreshold_thenReducePriceAndAddToRevenueWith() throws PrintingException {
        // when
        machine.loadPaper(30000);
        printingHouse.printEdition(edition, 51, machine, false);

        double expected = edition.getPrice(ADDITIONAL_COST_PER_PAGE) * (1 + COST_PERCENT_PER_PAGE_SIZE / 100.0);
        if (machine.getTotalCopiesFromEdition(edition) > DISCOUNT_THRESHOLD) {
            expected *= (1 - DISCOUNT_PERCENTAGE / 100.0);
        }
        expected *= 51;

        // then
        assertEquals(Math.floor(expected), Math.floor(printingHouse.getRevenue()));
    }

    @Test
    void whenRevenueNotPastThreshold_thenReturnNormalSalaries() throws PrintingException {
        // when
        machine.loadPaper(30000);
        printingHouse.printEdition(edition, 10, machine, false);

        // then
        assertEquals(2200, printingHouse.getTotalSalaries());
    }

    @Test
    void whenRevenueIsPastThreshold_thenReturnBonusSalaries() throws PrintingException {
        // when
        machine.loadPaper(30000);
        printingHouse.printEdition(edition, 40, machine, false);

        double expected = 1000 + 1200 * (1 + BONUS_SALARY_PERCENT / 100.0);

        // then
        assertEquals(Math.floor(expected), printingHouse.getTotalSalaries());
    }
}
