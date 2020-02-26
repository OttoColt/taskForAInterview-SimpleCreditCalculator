
import org.example.ejb.DAO.ProductDAO;
import org.example.jpa.products.Product;
import org.example.jpa.products.ProductConditions;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
@Stateless
public class ProductsWebController implements Serializable {

    private int period;
    private int sumOfCredit;
    public String productName;

    @Inject
    public ProductDAO pd;

    @PostConstruct
    public void init() {
        period = 12;
        sumOfCredit = 10000;
        productName = "Some product";
    }

    public int getSumOfCredit() {
        System.out.println("getSumOfCredit ="+sumOfCredit);
        return sumOfCredit;
    }

    public void setSumOfCredit(int sumOfCredit) {
        System.out.println("setSumOfCredit ="+sumOfCredit);
        this.sumOfCredit = sumOfCredit;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

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

}