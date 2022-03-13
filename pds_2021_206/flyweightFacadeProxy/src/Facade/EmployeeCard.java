package Ex2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmployeeCard {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private String name;
    private int id;
    private static int curId = 10000;
    LocalDateTime firstDay;
    boolean parkingAccess;

    public EmployeeCard(String name, LocalDateTime firstDay) {
        this.name = name;
        this.id = curId; curId++;
        this.firstDay = firstDay;
    }

    public void setParkingAccess(boolean b) {
        this.parkingAccess = b;
    }

    @Override public String toString() {
        return  "=========================\n"+
                "Employee "+name+"\n"+
                "EmployeeID: "+id+"\n"+
                "With us since: "+dtf.format(firstDay)+"\n"+
                ((parkingAccess)?"Has parking access\n":"")+
                "=========================\n";

    }

}
