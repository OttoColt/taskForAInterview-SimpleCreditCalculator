import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CalculatedTab {
    @Inject
    DropDownBox ddb;
    @Inject
    RadioButton rb;
    @Inject
    SliderView sv;

    public String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getDdb() {
        return ddb.favCoffee1;
    }

    public void setDdb(DropDownBox ddb) {
        this.ddb = ddb;
    }
}
