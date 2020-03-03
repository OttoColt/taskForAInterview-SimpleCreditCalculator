package org.example.ejb.controller;


import org.example.ejb.payments.DataForSchedule;
import org.example.ejb.payments.Payment;
import org.example.ejb.payments.PaymentSchedule;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class PaymentScheduleProducer implements Serializable {
    @Inject
    private DataForSchedule dto;

    @Inject
    private PaymentSchedule paymentSchedule;

//    private List<Payment> payments;
//
//    @Produces
//    @Named
//    public List<Payment> getPayments() {
//        return payments;
//    }

//    @PostConstruct
//    public void calculatePayments() {
//
//        if (dto.getTypeSchedule() != null && dto.getPercent() != 0 && dto.getAmount() != 0 && dto.getPeriod() != 0) {
//            payments = paymentSchedule.getPayments(dto);
//        }
//    }

}
