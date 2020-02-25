
import org.example.ejb.ProductDAO;
import org.example.jpa.Product;
import org.example.jpa.ProductConditions;

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
public class DropDownBox {
    @Inject
    public Product product;

    @Inject
    public RadioButton radioButton;
    @Inject
    public Slider slider;

    @Inject
    public ProductDAO pd;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @GET
    public Map<String,Product> getRelevantProduct() {
        int sum = slider.getSumOfCredit();
        int period = radioButton.getPeriod();

        List<Product> pdL = pd.findAll();
        Map<String,Product>relevantProduct = new LinkedHashMap<String,Product>();
        for (Product p:pdL) {
            for (ProductConditions pc:p.getProductConditionsList()) {
                if(pc.getMinSum()<sum && pc.getMaxSum()>sum && pc.getPeriod().getInt()==period){
                    relevantProduct.put(p.getNameProd(),p);
                }
            }
        }
        return relevantProduct;
    }
}