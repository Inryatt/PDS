package Ex2;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    List<Employee> allowedStaff = new ArrayList<>();

    public void allow(Employee e) {
        allowedStaff.add(e);
    }
}
