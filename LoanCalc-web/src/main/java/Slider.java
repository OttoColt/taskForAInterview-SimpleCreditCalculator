
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
@Stateless
public class Slider {

    private int sumOfCredit;

    public int getSumOfCredit() {
        return sumOfCredit;
    }

    public void setSumOfCredit(int sumOfCredit) {
        System.out.println(sumOfCredit);
        this.sumOfCredit = sumOfCredit;
    }

}
