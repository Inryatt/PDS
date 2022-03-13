package Ex2;

public enum Topping {
    FRUIT("Fruit"),
    CHOCOLATE("Chocolate"),
    CHERRIES("Cherries"),
    STRAWBERRIES("Strawberries");

    private String str;

    private Topping(String str) {
        this.str = str;
    }

    public String toString() {
        return str;
    }
}
