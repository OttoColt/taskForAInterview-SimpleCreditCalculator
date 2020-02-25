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
    private PaymentSchedule paymentSchedule;

    private List<Payment> payments;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.,
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Payment> getPayments() {
        return payments;
    }

    @PostConstruct
    public void calculatePayments() {
        DataForSchedule dto = new DataForSchedule(100000,10,12,"ann");
        payments = paymentSchedule.getPayments(dto);
    }

}
