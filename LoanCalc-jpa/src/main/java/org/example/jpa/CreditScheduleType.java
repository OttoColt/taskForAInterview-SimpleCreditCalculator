package org.example.jpa;

public enum CreditScheduleType {
    ANN("ann"),
    DIFF("diff");
    private String type;

    CreditScheduleType(String s){
        this.type = s;
    }

    public String getType() {
        return type;
    }
}
