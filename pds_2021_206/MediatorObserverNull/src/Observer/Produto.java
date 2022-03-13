package ex1;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    private static int productCount = 0;
    private int code;
    private String description;
    private Double base_price;
    private List<Observer> observers = new ArrayList<>();
    private Cliente latestBid = null;
    private int state; // 0-Stock ; 1-Leilão ; 2-Vendido
    
    public Produto(String description, Double base_price) {
        this.code = productCount++;
        this.base_price = base_price;
        this.description = description;
        this.state = 0;
    }

    public void attach(Observer o) {
        observers.add(o); 
    }

    public int getState() { return state; }

    public void updateState(){
        switch(this.state){
            case(0):{
                System.out.println("PRODUCT#" + code + ": is now up for auction!");
                this.state++;
                break;
            }
            case(1):{
                if(latestBid==null){
                    System.out.println("PRODUCT#" + code + ": due to low interest, the product is no longer in auction!");
                    this.state--;
                    break;
                }
                else{
                    System.out.println("PRODUCT#" + code + ": got sold to client " + latestBid.getName() + "!");
                    this.state++;
                    break;
                }
            }
            default: return;
        }
        notifyObservers();
    }

    public int getCode(){ return this.code;   }

    private void notifyObservers() {
        for(Observer obs: observers){
            obs.update(this);
        }
    }   

    public void bid(Cliente cli) {
        this.latestBid = cli;
        //notifyObservers();
    }


}
