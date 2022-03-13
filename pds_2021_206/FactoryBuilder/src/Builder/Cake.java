package Ex2;

public class Cake {
    private Shape shape = Shape.CIRCLE; //default: round, não imprime se não especificado
    private String cakeLayer; // obrigatório qnd layers = 1
    private int numCakeLayers = 0; // def: 0, obrigatório
    private Cream midLayerCream; //
    private Cream topLayerCream; // tem sempre
    private Topping topping; // tem sempre
    private String message;  // opcional

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setCakeLayer(String cakeLayer) {
        this.cakeLayer = cakeLayer;
    }

    public void setNumCakeLayers(int numCakeLayers) {
        this.numCakeLayers = numCakeLayers;
    }

    public void setMidLayerCream(Cream midLayerCream) {
        this.midLayerCream = midLayerCream;
    }

    public void setTopLayerCream(Cream topLayerCream) {
        this.topLayerCream = topLayerCream;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCakeLayers() {
        return this.numCakeLayers;
    }



    public String toString() {
        boolean shape, midLayerC, topLayerC, topping, message;
        shape = this.shape != Shape.CIRCLE;
        midLayerC = this.midLayerCream != null;
        topLayerC = this.topLayerCream != null;
        topping = this.topping != null;
        message = this.message != null;
        String firstSection = ((shape) ? (this.shape.toString() + " ") : ("")) + cakeLayer + " cake with " + numCakeLayers + " layers" + ((midLayerC) ? (" and " + midLayerCream.toString()) : ""); // shape, layerType, numLayers, midCream
        String toppingSection = (topping || topLayerC) ? (", topped with:\n" + ((topping) ? ("\t" + this.topping.toString() + ";\n") : "") + ((topLayerC) ? ("\t" + this.topLayerCream.toString() + ";\n") : "")) : ("");
        String messageSection = (message) ? ("Message says: \"" + this.message + "\"") : ("");
        return firstSection + toppingSection + messageSection + ".";
    }
}