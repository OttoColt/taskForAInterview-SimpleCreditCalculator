
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
@Stateless
public class RadioButton{

    private int period;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        System.out.println(period);
        this.period = period;
    }


}
