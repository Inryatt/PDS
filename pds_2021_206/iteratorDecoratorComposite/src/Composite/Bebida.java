package ex3;

public class Bebida extends Item {

    private String name;
    private Integer weight;

    public Bebida(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }
    public Integer getWeight() {
        return this.weight;
    }

    public void draw() {
        System.out.println(indent.toString()+"Bebida '" + name + "' - Weight : " + weight+".0");
    }
}
