package ex1;

import java.util.Random;
import java.util.ArrayList;

public class Cliente implements Observer {
    Random r = new Random();
    private final String name;
    private final ArrayList<Produto> observing = new ArrayList<>();
    private final Gestor gestor;

    public Cliente(Gestor gestor, String name) {
        this.gestor = gestor;
        this.name = name;
        //this.gestor.attach(this);
    }

    public String getName() { return this.name; }

    public void update(Produto p){
        switch(p.getState()){
            case(1):
                System.out.println("Client "+ name +" saw PRODUCT#"+ p.getCode()+" get bid on!");
                break;
            case(2):
                System.out.println("Client "+name +" saw PRODUCT#"+ p.getCode()+" get sold!");
                this.observing.remove(p);
                break;
        }
    }

    public void bid() {
       ArrayList<Produto> potentialProds = gestor.getInAuction();
       potentialProds.addAll(this.observing);
       if(potentialProds.size()<=0) return; // can't bid on anything!
       Produto productToBid = potentialProds.get(r.nextInt(potentialProds.size()));
       productToBid.bid(this);
       System.out.println("Client " + name + " bid higher on PRODUCT#" +productToBid.getCode() + "!");
       if(!observing.contains(productToBid)) {
           this.observing.add(productToBid);
           productToBid.attach(this);
       }
    }
}
