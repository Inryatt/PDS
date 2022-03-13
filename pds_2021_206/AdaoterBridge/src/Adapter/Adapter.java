package ex1;

class Adapter {
    private  static Database db=new Database();
    private static Registos rg=new Registos();
    public  static void newEmployee(String name, long emp_num, double salary){
        if( !db.addEmployee(new Employee(name, emp_num, salary)))
            System.err.println("Error creating the employee. Usage: Name, Employee nº, salary");
    }

    public static void removeEmployee(int code){
        try{
            db.deleteEmployee(code);
        }catch(NullPointerException ex){
            rg.remove(code);
        }
    }

    public static boolean doesThisEmployeeExist(int code){
        if(rg.isEmpregado(code)){
            return true;
        }
        else
            for(Employee emp : db.getAllEmployees()){
                if(code==emp.getEmpNum()){
                    return true;
                }
            }
        return false;
    }

    public static void printRegisters(){
        System.out.println("Employees:");
        for(Employee emp : db.getAllEmployees()){
            System.out.printf("Name: %-15s Employee Number: %5d  Salary: %10f \n",emp.getName(),emp.getEmpNum(),emp.getSalary());
        }
        for(Empregado emp : rg.listaDeEmpregados()){
            System.out.printf("Name: %-15s Employee Number: %5d  Salary: %10f \n",emp.nome(),emp.codigo(),emp.salario());
        }
    }
    public static void main(){ // To test this thing

    }
}

