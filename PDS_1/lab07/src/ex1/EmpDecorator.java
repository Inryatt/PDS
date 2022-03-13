package ex1;

public abstract class EmpDecorator implements EmployeeInterface {
    protected EmployeeInterface e;

    EmpDecorator(EmployeeInterface e){this.e=e;}


    public void work(){ e.work();   }
    public void start(String start){ e.start(start);   }
    public void terminate(){ e.terminate();   }


}
