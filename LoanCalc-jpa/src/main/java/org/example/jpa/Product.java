package org.example.jpa;


import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Named
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 1, max = 45)
    @Column(nullable = false, unique = true, name = "product_name")
    private String nameProd;

    @Column(nullable = false, name = "schedule_type")
    private CreditScheduleType scheduleType;

    @OneToMany(mappedBy = "product")
    private List<ProductConditions> productConditionsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public CreditScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(CreditScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public List<ProductConditions> getProductConditionsList() {
        return productConditionsList;
    }

    public void setProductConditionsList(List<ProductConditions> productConditionsList) {
        this.productConditionsList = productConditionsList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProd='" + nameProd + '\'' +
                ", scheduleType=" + scheduleType +
                ", productConditionsList=" + productConditionsList +
                '}';
    }
}
