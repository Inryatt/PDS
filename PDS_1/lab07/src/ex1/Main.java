package ex1;

public class Main {
    public static void main(String[] args){
        EmployeeInterface emp1= new Employee("Dwight");

        TeamLeader tl1=new TeamLeader(new Employee("Joana"));
        Manager man1= new Manager(new Employee("Karen"));
        TeamMember tm1 = new TeamMember(new Employee("Sulu"));


        emp1.start("Today");
        tl1.start("Yesterday");
        man1.start("Ten years ago");
        tm1.start("April 12th");
        emp1.work();
        tl1.work();
        tl1.plan();
        man1.work();
        tm1.work();

        emp1.terminate();

        man1.manage();
        tl1.plan();
        tm1.collaborate();

        Manager man2=new Manager(emp1);
        tl1.terminate();
        TeamLeader tl2=new TeamLeader( new TeamMember( new Employee("Gadget") ) );

        tl2.work();
        man2.work();


    }
}
