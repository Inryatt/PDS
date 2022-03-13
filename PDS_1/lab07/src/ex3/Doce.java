package ex3;

public class Doce extends Item {
    private String name;
    private Integer  weight;

    public Doce(String name, Integer weight){
        this.name=name;
        this.weight=weight;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void draw(){

        System.out.println(indent.toString()+"Doce \'" + name + "\' - Weight : " + weight+".0");
    }
}
