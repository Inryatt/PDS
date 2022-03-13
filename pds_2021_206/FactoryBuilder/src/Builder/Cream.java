package Ex2;

public enum Cream {
    WHIPPED("Whipped Cream"),
    RED_BERRIES("Red Berries Cream"),
    BLUE_BERRIES("Blue Berries Cream"),
    VANILLA("Vanilla Cream"),
    CHOCOLATE("Chocolate Cream");

    private String str;

    private Cream(String str) {
        this.str = str;
    }

    public String toString() {
        return str;
    }
}
