package org.example.jpa;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class ProductConditions {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, name = "min_sum")
    private int minSum;

    @Column(nullable = false, name = "max_sum")
    private int maxSum;

    private int percent;

    private Period period;

    @ManyToOne
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinSum() {
        return minSum;
    }

    public void setMinSum(int minSum) {
        this.minSum = minSum;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "ProductConditions{" +
                "id=" + id +
                ", minSum=" + minSum +
                ", maxSum=" + maxSum +
                ", percent=" + percent +
                ", period=" + period +
                '}';
    }
}
