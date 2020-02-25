
import org.example.ejb.DAO.ProductDAO;
import org.example.ejb.payments.DataForSchedule;
import org.example.jpa.products.Product;
import org.example.jpa.products.ProductConditions;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
@Stateless
public class ProductsWebController {

    private int period;
    private int sumOfCredit;

    @Inject
    DataForSchedule dto;

    public Product product;


    @Inject
    public ProductDAO pd;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        System.out.println(product);
        this.product = product;
    }

    @GET
    public Map<String,Product> getRelevantProduct() {

        List<Product> pdL = pd.findAll();
        Map<String,Product>relevantProduct = new LinkedHashMap<String,Product>();
        for (Product p:pdL) {
            for (ProductConditions pc:p.getProductConditionsList()) {
                if(pc.getMinSum()<sumOfCredit && pc.getMaxSum()>sumOfCredit && pc.getPeriod().getInt()==period){
                    relevantProduct.put(p.getNameProd(),p);
                }
            }
        }
        return relevantProduct;
    }

    public void createDTO() {
        double percent;

//        System.out.println(ddb.getProduct().getScheduleType().getType());
//        dto.setTypeSchedule(ddb.getProduct().getScheduleType().getType());
        dto.setTypeSchedule("ann");
        dto.setAmount(sumOfCredit);
//        dto.setPercent(10);
        System.out.println(period);
        dto.setPeriod(period);

//        dto.setAmount(1000);
        dto.setPercent(10);
//        dto.setPeriod(12);

    }
}