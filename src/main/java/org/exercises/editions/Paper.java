package org.exercises.editions;

import org.exercises.editions.enums.PageSize;
import org.exercises.editions.enums.PaperType;
import org.exercises.editions.interfaces.IPaper;
import org.exercises.exceptions.ExceptionMessages;
import org.exercises.exceptions.PrintingException;

public class Paper implements IPaper {
    public static final double BASE_NEWSPAPER = 0.03;
    public static final double BASE_REGULAR = 0.05;
    public static final double BASE_GLOSSY = 0.10;

    private final PaperType type;
    private final PageSize size;

    public Paper(PaperType type, PageSize size) {
        this.type = type;
        this.size = size;
    }

    public double getPrice(double additionalCost) throws PrintingException {
        if (additionalCost < 0) {
            throw new PrintingException(ExceptionMessages.INCORRECT_ADDITIONAL_COST_VALUE);
        }

        double base = switch (type) {
            case REGULAR -> Paper.BASE_REGULAR;
            case GLOSSY -> Paper.BASE_GLOSSY;
            case NEWSPAPER -> Paper.BASE_NEWSPAPER;
        };

        base += additionalCost;
        return base * (size.ordinal() + 1);
    }
}
