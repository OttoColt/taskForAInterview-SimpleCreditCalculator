
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class RadioButton{

    public String favColor1;

    public String getFavColor1() {
        return favColor1;
    }

    public void setFavColor1(String favColor1) {
        this.favColor1 = favColor1;
        System.out.println(favColor1);
    }


}
