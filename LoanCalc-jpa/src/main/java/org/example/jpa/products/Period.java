package org.example.jpa.products;

public enum Period {
    ONE_YEAR(12),
    TWO_YEARS(24),
    TREE_YEARS(36),
    FORE_YEARS(48);

    private int period;

    Period(int period) {
        this.period = period;
    }

    public int getInt() {
        return period;
    }
}
