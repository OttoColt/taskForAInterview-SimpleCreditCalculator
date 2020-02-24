
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

    public String product;

    @Inject
    public RadioButton radioButton;
    @Inject
    public Slider slider;

    @Inject
    public ProductDAO pd;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @GET
    public Map<String,Object> getRelevantProduct() {
        int sum = slider.getSumOfCredit();
        int period = radioButton.getPeriod();

        List<Product> pdL = pd.findAll();
        Map<String,Object>relevantProduct = new LinkedHashMap<String,Object>();
        for (Product p:pdL) {
            for (ProductConditions pc:p.getProductConditionsList()) {
                if(pc.getMinSum()<sum && pc.getMaxSum()>sum && pc.getPeriod().getInt()==period){
                    relevantProduct.put(p.getNameProd(),p.getId());
                }
            }
        }
        return relevantProduct;
    }
}