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

@Named
@RequestScoped
@Stateless
public class DtoController {
    @Inject
    PaymentScheduleProducer scheduleProducer;

    @Inject
    DataForSchedule dto;

    @Inject
    ProductsWebController productsWebController;

    @Inject
    public ProductDAO pd;

    @Inject
    private PaymentSchedule paymentSchedule;

    private List<Payment> payments;

    @Produces
    @Named
    public List<Payment> getPayments() {
        return payments;
    }

    public void createDTO() {
        Product product = pd.findById(Integer.parseInt(productsWebController.getProductId()));

        int percent = 0;
        for (ProductConditions pc : product.getProductConditionsList()) {
            percent = Math.max(percent, pc.getPercent());
        }
        dto.setTypeSchedule(product.getScheduleType().getType());
        dto.setAmount(productsWebController.getSumOfCredit());
        dto.setPeriod(productsWebController.getPeriod());
        dto.setPercent(percent);

        if (dto.getTypeSchedule() != null && dto.getPercent() != 0 && dto.getAmount() != 0 && dto.getPeriod() != 0) {
            payments = paymentSchedule.getPayments(dto);
        }

//        scheduleProducer.calculatePayments();

    }
}
