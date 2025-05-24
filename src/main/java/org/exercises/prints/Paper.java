package org.exercises.prints;

import org.exercises.prints.enums.PageSize;
import org.exercises.prints.enums.PaperType;
import org.exercises.prints.interfaces.IPaper;

public class Paper implements IPaper {
    private static final double BASE_NEWSPAPER = 0.03;
    private static final double BASE_REGULAR = 0.05;
    private static final double BASE_GLOSSY = 0.10;

    private final PaperType type;
    private final PageSize size;
    private final double pricePercentagePerSize;

    public Paper(PaperType type, PageSize size, double pricePercentagePerSize) {
        this.type = type;
        this.size = size;
        this.pricePercentagePerSize = pricePercentagePerSize;
    }

    public double getPrice() {
        double base = switch (type) {
            case REGULAR -> Paper.BASE_REGULAR;
            case GLOSSY -> Paper.BASE_GLOSSY;
            case NEWSPAPER -> Paper.BASE_NEWSPAPER;
        };

        return base * (size.ordinal() + 1) * (1 + pricePercentagePerSize / 100);
    }
}
