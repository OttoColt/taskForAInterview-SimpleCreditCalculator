package org.example.ejb.payments;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
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

    public void onPaymentsChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Payment payment) {
        calculatePayments();
    }

    @PostConstruct
    public void calculatePayments() {
        if (dto.getTypeSchedule() != null) {
            payments = paymentSchedule.getPayments(dto);
        }
    }

}
