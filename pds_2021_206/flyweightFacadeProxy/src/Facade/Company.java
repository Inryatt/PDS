package Ex2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company { // acts like a facade

    public static User user;
    private Insurance insurance;
    private Parking parking = new Parking();
    private SocialSecurity social;
    private List<Employee> emps = new ArrayList<>();

    public Company(Insurance insurance, SocialSecurity social) {
        this.insurance = insurance;
        this.social = social;
    }

    public EmployeeCard admitPerson(String name, double salary) {
        Employee e = new Employee(name, salary);
        emps.add(e);
        insurance.regist(e);
        social.regist(e);
        double totalSal = 0;
        for(Employee emp : this.emps) totalSal += emp.getSalary();
        double median = totalSal/this.emps.size();
        boolean canPark = false;
        if(e.getSalary()>median) canPark = true;
        if(canPark) parking.allow(e);
        LocalDateTime today = LocalDateTime.now();
        EmployeeCard card = new EmployeeCard(e.getName(),today);
        if(canPark) card.setParkingAccess(true);
        return card;
    }
    public void paySalaries(int month) {
        for (Employee e : emps) {
            BankAccount ba = e.getBankAccount();
            ba.deposit((e.getSalary()));
        }
    }
    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}
