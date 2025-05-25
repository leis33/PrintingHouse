package org.exercises.printing;

import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintingException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mockito;

public class PrintingMachineTests {
    PrintingMachine machine;

    @BeforeEach
    void setup() {
        machine = new PrintingMachine(true, 40000, 12);

    }

    @Test
    void whenLoadPaperHasMoreThanMaxSheets_thenPaperShouldBeMaxLoaded() throws PrintingException {
        // when
        machine.loadPaper(60000);

        // then
        assertEquals(40000, machine.getLoadedSheets());
    }

    @Test
    void whenLoadPaperIsLessThanMaxSheets_thenPaperShouldBeAsLoaded() throws PrintingException {
        // when
        machine.loadPaper(30000);

        // then
        assertEquals(30000, machine.getLoadedSheets());
    }

    @Test
    void whenLoadPaperValueIsNegative_thenThrowException() throws PrintingException {
        // when
        Exception ex = assertThrows(PrintingException.class, () -> machine.loadPaper(-100));

        // then
        assertEquals(ExceptionMessages.INCORRECT_LOAD_PAPER_VALUE, ex.getMessage());
    }
}
