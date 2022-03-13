package ex1;

import java.util.ArrayList;
import java.util.List;

class Registos {
    // Data elements
    private ArrayList<Empregado> empregados;    // Stores the employees

    public Registos() {
        empregados = new ArrayList<>();
    }


    public void insere(Empregado emp) {
        empregados.add(emp);
        // Code to insert employee
    }

    public void remove(int codigo) {
        // Code to remove employee
        for(Empregado emp : empregados){
            if (emp.codigo() == codigo){
                empregados.remove(emp);
                break;
            }
        }
    }

    public boolean isEmpregado(int codigo) {
        // Code to find employee
        for(Empregado emp : empregados){
            if (emp.codigo() == codigo){
                return true;
            }
        }
        return false;
    }

    public List<Empregado> listaDeEmpregados() {
        // Code to retrieve collection
        return empregados;
    }
}

