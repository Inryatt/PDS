package Ex2;

public enum Shape {
    CIRCLE("Circle shaped"),
    Square("Square shaped"),
    TRIANGLE("Triangle shaped"),
    STAR("Star shaped"),
    HEART("Heart shaped"),
    RECTANGLE("Rectangle shaped");

    private String str;

    private Shape(String str) {
        this.str = str;
    }

    public String toString() {
        return str;
    }
}
