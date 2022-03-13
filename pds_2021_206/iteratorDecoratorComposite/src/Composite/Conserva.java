package ex3;

public class Conserva extends Item{
    private String name;
    private Integer weight;

    public Conserva(String name, Integer weight){
        this.name=name;
        this.weight=weight;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void draw(){

        System.out.println(indent.toString()+"Conserva \'" + name + "\' - Weight : " + weight+".0");
    }

}
