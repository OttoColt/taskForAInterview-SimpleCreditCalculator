package org.example.ejb.payments;


import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

//TODO export payment classes to jar
@SessionScoped
public class PaymentScheduleProducer implements Serializable {
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

    public void onPaymentsChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Payment payment) {
        calculatePayments();
    }

    @PostConstruct
    public void calculatePayments() {

        if (dto.getTypeSchedule() != null && dto.getPercent() != 0 && dto.getAmount() != 0 && dto.getPeriod() != 0) {
            payments = paymentSchedule.getPayments(dto);
        }
    }

}
