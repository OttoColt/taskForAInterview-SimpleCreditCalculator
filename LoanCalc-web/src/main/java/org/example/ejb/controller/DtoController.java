package org.example.ejb.controller;

import org.example.ejb.DAO.ProductDAO;
import org.example.ejb.payments.DataForSchedule;
import org.example.ejb.payments.PaymentScheduleProducer;
import org.example.jpa.products.Product;
import org.example.jpa.products.ProductConditions;

import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
        scheduleProducer.calculatePayments();

    }
}
