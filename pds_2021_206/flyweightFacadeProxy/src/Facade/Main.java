package Ex2;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SocialSecurity socialSecurity = new SocialSecurity();
        Insurance insurance = new Insurance("sharkCare");
        Company shark = new Company(insurance, socialSecurity);
        Company.user = User.COMPANY;
        System.out.print(shark.admitPerson("Maria Silva", 1000));
        System.out.print(shark.admitPerson("Manuel Pereira", 900));
        System.out.print(shark.admitPerson("Aurora Machado", 1200));
        System.out.print(shark.admitPerson("Augusto Lima", 1100));
    }
}
