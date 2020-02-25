package org.example.ejb;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class PaymentScheduleProducer {
    @Inject
    private DataForSchedule dto;

    @Inject
    private PaymentSchedule paymentSchedule;

    private List<Payment> payments;

    @Produces
    @Named
    public List<Payment> getPayments() {
        return payments;
    }

    @PostConstruct
    public void calculatePayments() {
        if (dto.getTypeSchedule() != null) {
            payments = paymentSchedule.getPayments(dto);
        }
    }

}
