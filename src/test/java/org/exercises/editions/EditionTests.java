package org.exercises.editions;

import org.exercises.editions.enums.PageSize;
import org.exercises.editions.enums.PaperType;
import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintingException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EditionTests {
    private Edition edition;

    @BeforeEach
    void setup() {
        edition = new Book("Test", 400, new Paper(PaperType.REGULAR, PageSize.A5));
    }

    @Test
    void whenAdditionalCostIsNegative_thenThrowException() throws PrintingException {
        // when
        Exception ex = assertThrows(PrintingException.class, () -> edition.getPrice(-10));

        // then
        assertEquals(ExceptionMessages.INCORRECT_ADDITIONAL_COST_VALUE, ex.getMessage());
    }

    @Test
    void whenAdditionalCostIsCorrect_thenGetPrice() throws PrintingException {
        double additionalCost = 0.05;
        double expected = (Paper.BASE_REGULAR + additionalCost) * (PageSize.A5.ordinal() + 1) * 400;

        // then
        assertEquals(expected, edition.getPrice(additionalCost));
    }
}


