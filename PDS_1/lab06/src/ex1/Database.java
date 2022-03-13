package ex1;

import java.util.Vector;

class Database {// Data elements
    private Vector<Employee> employees; // Stores the employees

    public Database() {
        employees = new Vector<>();
    }

    public boolean addEmployee(Employee employee) {
        // Code to add employee
        try{
            employees.addElement(employee);
            return true;
        }catch(Exception TODO){
            return false;
        }
    }

    public void deleteEmployee(long emp_num) {
        // Code to delete employee
        employees.removeIf(emp -> emp.getEmpNum() == emp_num); //sugestão dada pelo intellij, v nice
    }

    public Employee[] getAllEmployees() {// Code to retrieve collection}}
        for(Employee emp:employees){
            System.out.println(emp.getName());
            System.out.println(emp.getSalary());
            System.out.println(emp.getEmpNum());
        }
        return employees.toArray(new Employee[employees.size()]);
    }
}