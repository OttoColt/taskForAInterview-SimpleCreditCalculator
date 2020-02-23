
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.LinkedHashMap;
import java.util.Map;

@Named
@SessionScoped
public class DropDownBox {

    public String favCoffee1;

    public String getFavCoffee1() {
        return favCoffee1;
    }

    public void setFavCoffee1(String favCoffee1) {
        this.favCoffee1 = favCoffee1;
    }

    private static Map<String,Object> coffee2Value;
    static{
        coffee2Value = new LinkedHashMap<String,Object>();
        coffee2Value.put("Потребительский кредит", "Ipoteka"); //label, value
        coffee2Value.put("Ипотека кредит на новостройку", "PotrebKredit");
    }

    public Map<String,Object> getFavCoffee2Value() {
        return coffee2Value;
    }
}