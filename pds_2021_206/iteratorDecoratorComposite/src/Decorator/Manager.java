package ex1;

public class Manager  extends EmpDecorator{

    Manager(EmployeeInterface e){super(e);}
    @Override public void work(){
        e.work();
        System.out.print("as a manager ");

    }
    public void manage(){
        System.out.print("-- manages!");

    }



}
