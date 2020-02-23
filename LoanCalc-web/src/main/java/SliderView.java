
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SliderView {

    private int number7;

    public int getNumber7() {
        return number7;
    }

    public void setNumber7(int number7) {
        this.number7 = number7;
    }

}
