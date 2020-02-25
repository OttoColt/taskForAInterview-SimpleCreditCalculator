package org.example.ejb.payments;

import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Objects;

@Stateless
public class Payment {

    private double paymentInMonth;
    private double percentPart;
    private double currentBalance;

    public Payment(double paymentInMonth, double percentPart, double currentBalance) {
        this.paymentInMonth = paymentInMonth;
        this.percentPart = percentPart;
        this.currentBalance = currentBalance;
    }

    public Payment() {
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getPaymentInMonth() {
        return paymentInMonth;
    }

    public void setPaymentInMonth(double paymentInMonth) {
        this.paymentInMonth = paymentInMonth;
    }

    public double getPercentPart() {
        return percentPart;
    }

    public void setPercentPart(double percentPart) {
        this.percentPart = percentPart;
    }

    public double getDebtPart() {
        return (BigDecimal.valueOf(paymentInMonth).subtract(BigDecimal.valueOf(percentPart))).doubleValue();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment=" + paymentInMonth +
                ", percentPart=" + percentPart +
                ", currentBalance=" + currentBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment1 = (Payment) o;
        return Double.compare(payment1.paymentInMonth, paymentInMonth) == 0 &&
                Double.compare(payment1.percentPart, percentPart) == 0 &&
                Double.compare(payment1.currentBalance, currentBalance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentInMonth, percentPart, currentBalance);
    }
}
