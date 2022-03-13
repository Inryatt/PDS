package ex3;



public abstract class Item {
    protected static StringBuffer indent = new StringBuffer();
    public String name;
    public Integer weight;
    public abstract Integer getWeight();
    public abstract void draw();

}
