package ex3;
import java.util.ArrayList;

public class Caixa extends Item {
    private ArrayList<Item> items = new ArrayList<Item>();
    private String name;
    private Integer weight;

    public void add(Item item){
        this.items.add(item);
    }

     public Caixa(String name, Integer weight ){
        this.name=name;
        this.weight=weight;
    }

    public void draw() {
        System.out.println(indent.toString()+"* Caixa '" + name + "' [ Weight : " + weight+".0 ; Total: "+this.getWeight()+".0]");
        indent.append("    ");

        for (Item item : items){
           item.draw();
        }
        indent.setLength(indent.length() - 4);

    }

    public Integer getWeight(){
        Integer weight_sum=0;
        for(Item item : items){
            weight_sum=weight_sum+item.getWeight();
        }
        weight_sum=weight_sum+this.weight;
        return weight_sum;
    }
}