package org.exercises.printing;

import org.exercises.editions.*;
import org.exercises.editions.enums.PageSize;
import org.exercises.editions.enums.PaperType;
import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintingException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrintingMachineTests {
    private PrintingMachine machine;
    private Edition edition;

    @BeforeEach
    void setup() {
        int MAX_SHEETS = 40000;
        boolean supportsColor = false;
        machine = new PrintingMachine(supportsColor, MAX_SHEETS, 12);

        edition = new Book("Test", 400, new Paper(PaperType.REGULAR, PageSize.A5));
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

    @Test
    void whenPrintingZeroOrNegativeCopies_thenThrowException() throws PrintingException {
        // when
        machine.loadPaper(30000);
        Exception ex = assertThrows(PrintingException.class, () -> machine.print(edition, 0, false));

        // then
        assertEquals(ExceptionMessages.INCORRECT_COPIES_VALUE, ex.getMessage());
    }

    @Test
    void whenPrintingCorrectly_thenPaperIsReduced() throws PrintingException {
        // when
        int copies = 12;
        machine.loadPaper(10000);
        machine.print(edition, copies, false);

        // then
        assertEquals(5200, machine.getLoadedSheets());
    }

    @Test
    void whenPrintingColorWithoutColorSupport_thenThrowException() throws PrintingException {
        // when
        machine.loadPaper(30000);
        Exception ex = assertThrows(PrintingException.class, () -> machine.print(edition, 2, true));

        // then
        assertEquals(ExceptionMessages.UNSUPPORTED_COLOR_PRINT, ex.getMessage());
    }

    @Test
    void whenPaperIsInsufficientWhilePrinting_thenThrowException() throws PrintingException {
        // when
        machine.loadPaper(3000);
        Exception ex = assertThrows(PrintingException.class, () -> machine.print(edition, 10, false));

        // then
        assertEquals(ExceptionMessages.INSUFFICIENT_PAPER, ex.getMessage());
    }

    @Test
    void whenPrintingCorrectly_thenReturnCopiesOfEdition() throws PrintingException {
        // when
        int copies = 12;
        machine.loadPaper(30000);
        machine.print(edition, copies, false);

        // then
        assertEquals(copies, machine.getTotalCopiesFromEdition(edition));
    }

    @Test
    void whenPrintingCorrectly_thenReturnMachineTotalExpenses() throws PrintingException {
        // when
        machine.loadPaper(30000);

        Book book1 = new Book("Book", 150, new Paper(PaperType.REGULAR, PageSize.A5));
        Poster poster1 = new Poster("Poster", 2, new Paper(PaperType.GLOSSY, PageSize.A3));
        Newspaper newspaper1 = new Newspaper("Newspaper", 32, new Paper(PaperType.NEWSPAPER, PageSize.A4));

        machine.print(book1, 10, false);
        machine.print(poster1, 50, false);
        machine.print(newspaper1, 100, false);

        double expected = 10 * 150 * Paper.BASE_REGULAR * (PageSize.A5.ordinal() + 1) +
                50 * 2 * Paper.BASE_GLOSSY * (PageSize.A3.ordinal() + 1) +
                100 * 32 * Paper.BASE_NEWSPAPER * (PageSize.A4.ordinal() + 1);

        // then
        assertEquals(expected, machine.getTotalExpenses());
    }
}
