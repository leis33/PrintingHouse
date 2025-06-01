package org.exercises.entities;

import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeTests {
    private Manager manager;
    private Operator operator;

    @BeforeEach
    public void setup() {
        manager = new Manager("Manager", 1000);
        operator = new Operator("Operator", 1000);
    }

    @Test
    public void whenBonusIsNegative_thenThrowExceptionForManager() throws PrintingException {
        //when
        Exception ex = assertThrows(PrintingException.class, () -> manager.getSalary(-10));

        // then
        assertEquals(ExceptionMessages.INCORRECT_BONUS_VALUE, ex.getMessage());
    }

    @Test
    public void whenBonusIsCorrect_thenReturnSalaryForManager() throws PrintingException {
        // then
        assertEquals(1100, manager.getSalary(10));
    }

    @Test
    public void whenBonusIsNegative_thenReturnSalaryForOperator() throws PrintingException {
        assertEquals(1000, operator.getSalary(-10));
    }

    @Test
    public void whenBonusIsCorrect_thenReturnSalaryForOperator() throws PrintingException {
        assertEquals(1000, operator.getSalary(10));
    }
}
