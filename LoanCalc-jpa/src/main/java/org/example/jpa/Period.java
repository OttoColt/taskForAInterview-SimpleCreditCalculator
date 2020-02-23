package org.example.jpa;

public enum Period {
    ONE_YEAR("12"),
    TWO_YEARS("24"),
    TREE_YEARS("24"),
    FORE_YEARS("24");

    private String period;

    Period(String period){
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }
}
