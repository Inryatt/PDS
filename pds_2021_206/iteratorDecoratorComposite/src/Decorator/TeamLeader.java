package ex1;

public class TeamLeader  extends EmpDecorator{

    TeamLeader(EmployeeInterface e){super(e);}
    @Override public void work(){
        e.work();
        System.out.print("leading a team ");

    }
    public void plan(){
        System.out.println("-- plans!");

    }

}
