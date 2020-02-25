import org.example.ejb.DataForSchedule;


import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Produces;

@Named
@SessionScoped
@Stateless
public class CalculatedTab {
    @Inject
    DataForSchedule dto;

    @Inject
    DropDownBox ddb;
    @Inject
    RadioButton rb;
    @Inject
    Slider sv;

    public void createDTO() {
        double percent;

//        System.out.println(ddb.getProduct().getScheduleType().getType());
//        dto.setTypeSchedule(ddb.getProduct().getScheduleType().getType());
        dto.setTypeSchedule("ann");
        dto.setAmount(sv.getSumOfCredit());
//        dto.setPercent(10);
        System.out.println(rb.getPeriod());
        dto.setPeriod(rb.getPeriod());

//        dto.setAmount(1000);
        dto.setPercent(10);
//        dto.setPeriod(12);

    }
}
