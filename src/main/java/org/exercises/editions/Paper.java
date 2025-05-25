package org.exercises.editions;

import org.exercises.editions.enums.PageSize;
import org.exercises.editions.enums.PaperType;
import org.exercises.editions.interfaces.IPaper;

public class Paper implements IPaper {
    private static final double BASE_NEWSPAPER = 0.03;
    private static final double BASE_REGULAR = 0.05;
    private static final double BASE_GLOSSY = 0.10;

    private final PaperType type;
    private final PageSize size;

    public Paper(PaperType type, PageSize size) {
        this.type = type;
        this.size = size;
    }

    public double getTypeBasePrice() {
        return switch (type) {
            case REGULAR -> Paper.BASE_REGULAR;
            case GLOSSY -> Paper.BASE_GLOSSY;
            case NEWSPAPER -> Paper.BASE_NEWSPAPER;
        };
    }

    public double getPrice(double additionalCost) {
        double base = switch (type) {
            case REGULAR -> Paper.BASE_REGULAR;
            case GLOSSY -> Paper.BASE_GLOSSY;
            case NEWSPAPER -> Paper.BASE_NEWSPAPER;
        };

        base += additionalCost;
        return base * (size.ordinal() + 1);
    }
}
