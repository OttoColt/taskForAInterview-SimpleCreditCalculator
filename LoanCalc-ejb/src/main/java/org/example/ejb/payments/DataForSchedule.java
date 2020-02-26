package org.example.ejb.payments;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.Objects;

@Stateless
public class DataForSchedule {
    private double amount;
    private int percent;
    private int period;
    private String typeSchedule;

    public DataForSchedule() {
    }

    public DataForSchedule(double amount, int percent, int period, String typeSchedule) {
        this.amount = amount;
        this.percent = percent;
        this.period = period;
        this.typeSchedule = typeSchedule;
    }

    public String getTypeSchedule() {
        return typeSchedule;
    }

    public void setTypeSchedule(String typeSchedule) {
        this.typeSchedule = typeSchedule;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getAmount() {
        return amount;
    }

    public int getPercent() {
        return percent;
    }

    public int getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "DataToSchedule{" +
                "amount=" + amount +
                ", percent=" + percent +
                ", period=" + period +
                ", typeSchedule='" + typeSchedule + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataForSchedule that = (DataForSchedule) o;
        return Double.compare(that.amount, amount) == 0 &&
                percent == that.percent &&
                period == that.period &&
                typeSchedule.equals(that.typeSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, percent, period, typeSchedule);
    }
}
