package ex1;

public class Main {
    public static void main(String[] args){
        Database diceBag=new Database();
        Employee thor = new Employee("Thor",1,890);
        System.out.println(thor.getName());
        System.out.println(thor.getEmpNum());
        System.out.println(thor.getSalary());

        diceBag.addEmployee(thor);
        Employee loki = new Employee("Loki",2,99999);
        diceBag.addEmployee(loki);
        diceBag.getAllEmployees();
        diceBag.deleteEmployee(1);
        diceBag.getAllEmployees();

        System.out.println("Teste dos Empregados:");
        Empregado manel = new Empregado("manel"," das couves",234234,342);
        Empregado gato = new Empregado("miau","tareco",2342345,235);
        System.out.println(gato.nome());
        System.out.println(gato.codigo());
        System.out.println(gato.salario());

        Registos reg=new Registos();
        reg.insere(gato);
        for(Empregado emp : reg.listaDeEmpregados()){
            System.out.printf("aName: %15s Employee Number: %5d  Salary: %10f \n",emp.nome(),emp.codigo(),emp.salario());
        }
        reg.insere(manel);
        for(Empregado emp : reg.listaDeEmpregados()){
            System.out.printf("bName: %15s Employee Number: %5d  Salary: %10f \n",emp.nome(),emp.codigo(),emp.salario());
        }

        reg.remove(manel.codigo());
        for(Empregado emp : reg.listaDeEmpregados()){
            System.out.printf("cName: %15s Employee Number: %5d  Salary: %10f \n",emp.nome(),emp.codigo(),emp.salario());
        }

        Adapter.newEmployee("joao",1999,8233);
        Adapter.newEmployee("max",2345,8364);

        boolean result=Adapter.doesThisEmployeeExist(2345);
        System.out.println(result);
        result=Adapter.doesThisEmployeeExist(23); //nope
        System.out.println(result);


        Adapter.printRegisters();
    }
}
