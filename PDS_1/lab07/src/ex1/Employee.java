package ex1;

public class Employee implements  EmployeeInterface{
    private String Name;
    Employee(String name){this.Name=name;}
    @Override public void work(){
        System.out.print("\n"+this.Name +" works ");
    }

    @Override public void terminate(){
        System.out.println(this.Name +" has been terminated.");
    }

    @Override public void start(String date){
        System.out.println(this.Name +" starts on "+date+" .");
    }

}
