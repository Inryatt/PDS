package ex1;

public class TeamMember  extends EmpDecorator{

    TeamMember(EmployeeInterface e){super(e);}
    @Override public void work(){
        e.work();
        System.out.print("on a team ");
    }
    public void collaborate(){
        System.out.println("-- Collaborates!");
    }



}
