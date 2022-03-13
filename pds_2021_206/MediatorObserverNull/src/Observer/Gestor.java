package ex1;

import java.util.ArrayList;

public class Gestor implements Observer {
    String name;
    ArrayList<Produto> produtos;
    //ArrayList<Cliente> clientes = new ArrayList<>();

    public Gestor(ArrayList<Produto> lista, String name){
        this.name = name;
        this.produtos = lista;
        for(Produto prod : this.produtos) {
            prod.attach(this);
        }
    }

    public void update(Produto p){
        switch(p.getState()){
            case(1):
            {
                System.out.println("Gestor " + name + " saw PRODUCT#" + p.getCode() + " get put up for auction!");
                break;
            }
            case(2):{
                System.out.println("Gestor " + name + " saw PRODUCT#" + p.getCode() + " get sold!");
                break;
            }
        }   
    }

    //public void attach(Cliente c){
    //    this.clientes.add(c);
    //}

    public ArrayList<Produto> getInStock(){
        ArrayList<Produto> toReturn=new ArrayList<>();
        for(Produto p : this.produtos){
            if(p.getState()==0){
                toReturn.add(p);
            }
        }
        return toReturn;
    }

    public ArrayList<Produto> getInAuction(){
        ArrayList<Produto> toReturn=new ArrayList<>();
        for(Produto p : this.produtos){
            if(p.getState()==1){
                toReturn.add(p);
            }
        }
        return toReturn;
    }

    public ArrayList<Produto> getSold(){
        ArrayList<Produto> toReturn=new ArrayList<>();
        for(Produto p : this.produtos){
            if(p.getState()==2){
                toReturn.add(p);
            }
        }
        return toReturn;
    }
}
