import org.example.ejb.payments.DataForSchedule;
import org.example.ejb.payments.PaymentScheduleProducer;
import org.example.jpa.products.Product;

import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@Stateless
public class DtoProducer {
    @Inject
    PaymentScheduleProducer scheduleProducer;

    @Inject
    DataForSchedule dto;

    @Inject
    ProductsWebController productsWebController;

    @Inject
    public Product product;

    public void createDTO() {
        System.out.println("create dto!!!!");
        System.out.println(product.getNameProd());
        //TODO figure out why the bean "Product" is not injected
        //this is a dummy code
        dto.setTypeSchedule("ann");
        dto.setAmount(productsWebController.getSumOfCredit());
        System.out.println(productsWebController.getPeriod());
        dto.setPeriod(productsWebController.getPeriod());
        dto.setPercent(10);
        scheduleProducer.calculatePayments();

    }
}
