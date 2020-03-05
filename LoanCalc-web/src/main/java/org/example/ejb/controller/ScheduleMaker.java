package org.example.ejb.controller;

import org.example.ejb.DAO.ProductDAO;
import org.example.ejb.payments.DataForSchedule;
import org.example.ejb.payments.Payment;
import org.example.ejb.payments.PaymentSchedule;
import org.example.jpa.products.Product;
import org.example.jpa.products.ProductConditions;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
@Stateless
public class ScheduleMaker {

    @Inject
    private Logger logger;

    @Inject
    private DataForSchedule dataForSchedule;

    @Inject
    private ProductsDropList productsDropList;

    @Inject
    private ProductDAO pd;

    @Inject
    private PaymentSchedule paymentSchedule;

    private List<Payment> payments;

    @Produces
    @Named
    public List<Payment> getPayments() {
        return payments;
    }

    public void makePaymentsSchedule() {

        Product product;

        try {
            product = pd.findById(Integer.parseInt(productsDropList.getProductId()));
        } catch (NumberFormatException e) {
            return;
        }

        int percent = 0;
        for (ProductConditions pc : product.getProductConditionsList()) {
            percent = Math.max(percent, pc.getPercent());
        }
        dataForSchedule.setTypeSchedule(product.getScheduleType().getType());
        dataForSchedule.setAmount(productsDropList.getSumOfCredit());
        dataForSchedule.setPeriod(productsDropList.getPeriod());
        dataForSchedule.setPercent(percent);

        String message = "makePaymentsSchedule dataForSchedule = "+dataForSchedule.toString();
        logger.info(message);
        if (dataForSchedule.getTypeSchedule() != null && dataForSchedule.getPercent() != 0 && dataForSchedule.getAmount() != 0 && dataForSchedule.getPeriod() != 0) {
            payments = paymentSchedule.getPayments(dataForSchedule);
        } else {
            payments = null;
        }

    }
}
