package org.example.ejb.controller;

import org.example.ejb.DAO.ProductDAO;
import org.example.jpa.products.Product;
import org.example.jpa.products.ProductConditions;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
@Stateless
public class ProductsDropList {

    private int period;
    private int sumOfCredit;
    private String productId;

    @Inject
    private ProductDAO pd;

    @PostConstruct
    public void init() {
        period = 12;
        sumOfCredit = 10000;
    }

    public int getSumOfCredit() {
        return sumOfCredit;
    }

    public void setSumOfCredit(int sumOfCredit) {
        this.sumOfCredit = sumOfCredit;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Map<String, Object> getRelevantProduct() {

        List<Product> pdL = pd.findAll();
        Map<String, Object> relevantProduct = new LinkedHashMap<String, Object>();
        for (Product p : pdL) {
            for (ProductConditions pc : p.getProductConditionsList()) {
                if (pc.getMinSum() < sumOfCredit && pc.getMaxSum() > sumOfCredit && pc.getPeriod().getInt() == period) {
                    relevantProduct.put(p.getNameProd(), p.getId());
                }
            }
        }
        return relevantProduct;
    }

}