package Ex2;

//abstract builder
interface CakeBuilder {
    public void setCakeShape(Shape shape);
    public void addCakeLayer();
    public void addCreamLayer();
    public void addMidLayer();
    public void addTopping();
    public void addTopLayer();
    public void addMessage(String m);
    public void createCake();
    public Cake getCake();
}

//concrete builders
class ChocolateCakeBuilder implements CakeBuilder {
    Cake cake;

    @Override
    public void setCakeShape(Shape shape) { // Sets cake shape, should only be called once (create cake)
        cake.setShape(shape);
    }

    @Override
    public void addCakeLayer() { // Increments cake layer count, should be called whenever we add a layer
        cake.setNumCakeLayers(cake.getCakeLayers()+1);
    }

    @Override
    public void addCreamLayer() { // Sets cream type in middle layers, should only be if there are middle layers
        cake.setMidLayerCream(Cream.RED_BERRIES);
    }

    @Override
    public void addMidLayer() { // Adds a middle layer
        addCakeLayer();
    }

    @Override
    public void addTopping() { // Adds the topping
        cake.setTopping(Topping.FRUIT);
    }

    @Override
    public void addTopLayer() { // Adds a top cake layer and its cream
        addCakeLayer();
        cake.setTopLayerCream(Cream.WHIPPED);
    }

    @Override
    public void addMessage(String m) { // Adds a message to the cake
        cake.setMessage(m);
    }

    @Override
    public void createCake() { // Create. That. Cake!!!
         cake = new Cake();
         cake.setCakeLayer("Chocolate"); // sets type of cake layers
    }

    @Override
    public Cake getCake() {
        return cake;
    }
}

class SpongeCakeBuilder implements CakeBuilder {
    Cake cake;

    @Override
    public void setCakeShape(Shape shape) { // Sets cake shape, should only be called once (create cake)
        cake.setShape(shape);
    }

    @Override
    public void addCakeLayer() { // Increments cake layer count, should be called whenever we add a layer
        cake.setNumCakeLayers(cake.getCakeLayers()+1);
    }

    @Override
    public void addCreamLayer() { // Sets cream type in middle layers, should only be if there are middle layers
        cake.setMidLayerCream(Cream.RED_BERRIES);
    }

    @Override
    public void addMidLayer() { // Adds a middle layer
        addCakeLayer();
    }

    @Override
    public void addTopping() { // Adds the topping
        cake.setTopping(Topping.FRUIT);
    }

    @Override
    public void addTopLayer() { // Adds a top cake layer and its cream
        addCakeLayer();
        cake.setTopLayerCream(Cream.WHIPPED);
    }

    @Override
    public void addMessage(String m) { // Adds a message to the cake
        cake.setMessage(m);
    }

    @Override
    public void createCake() { // Create. That. Cake!!!
        cake = new Cake();
        cake.setCakeLayer("Sponge"); // sets type of cake layers
    }

    @Override
    public Cake getCake() {
        return cake;
    }
}


class YogurtCakeBuilder implements CakeBuilder {
    Cake cake;

    @Override
    public void setCakeShape(Shape shape) { // Sets cake shape, should only be called once (create cake)
        cake.setShape(shape);
    }

    @Override
    public void addCakeLayer() { // Increments cake layer count, should be called whenever we add a layer
        cake.setNumCakeLayers(cake.getCakeLayers()+1);
    }

    @Override
    public void addCreamLayer() { // Sets cream type in middle layers, should only be if there are middle layers
        cake.setMidLayerCream(Cream.VANILLA);
    }

    @Override
    public void addMidLayer() { // Adds a middle layer
        addCakeLayer();
    }

    @Override
    public void addTopping() { // Adds the topping
        cake.setTopping(Topping.CHOCOLATE);
    }

    @Override
    public void addTopLayer() { // Adds a top cake layer and its cream
        addCakeLayer();
        cake.setTopLayerCream(Cream.RED_BERRIES);
    }

    @Override
    public void addMessage(String m) { // Adds a message to the cake
        cake.setMessage(m);
    }

    @Override
    public void createCake() { // Create. That. Cake!!!
        cake = new Cake();
        cake.setCakeLayer("Yogurt"); // sets type of cake layers
    }

    @Override
    public Cake getCake() {
        return cake;
    }
}

class IconicPortalCakeBuilder implements CakeBuilder {
    Cake cake;

    @Override
    public void setCakeShape(Shape shape) { // Sets cake shape, should only be called once (create cake)
        cake.setShape(shape);
    }

    @Override
    public void addCakeLayer() { // Increments cake layer count, should be called whenever we add a layer
        cake.setNumCakeLayers(cake.getCakeLayers()+1);
    }

    @Override
    public void addCreamLayer() { // Sets cream type in middle layers, should only be if there are middle layers
        cake.setMidLayerCream(Cream.CHOCOLATE);
    }

    @Override
    public void addMidLayer() { // Adds a middle layer
        addCakeLayer();
    }

    @Override
    public void addTopping() { // Adds the topping
        cake.setTopping(Topping.CHERRIES);
    }

    @Override
    public void addTopLayer() { // Adds a top cake layer and its cream
        addCakeLayer();
        cake.setTopLayerCream(Cream.CHOCOLATE);
    }

    @Override
    public void addMessage(String m) { // Adds a message to the cake
        cake.setMessage(m);
    }

    @Override
    public void createCake() { // Create. That. Cake!!!
        cake = new Cake();
        cake.setCakeLayer("Iconic Portal"); // sets type of cake layers
    }

    @Override
    public Cake getCake() {
        return cake;
    }
}

class LoveCakeBuilder implements CakeBuilder {
    Cake cake;

    @Override
    public void setCakeShape(Shape shape) { // Sets cake shape, should only be called once (create cake)
        cake.setShape(shape);
    }

    @Override
    public void addCakeLayer() { // Increments cake layer count, should be called whenever we add a layer
        cake.setNumCakeLayers(cake.getCakeLayers()+1);
    }

    @Override
    public void addCreamLayer() { // Sets cream type in middle layers, should only be if there are middle layers
        cake.setMidLayerCream(Cream.RED_BERRIES);
    }

    @Override
    public void addMidLayer() { // Adds a middle layer
        addCakeLayer();
    }

    @Override
    public void addTopping() { // Adds the topping
        cake.setTopping(Topping.STRAWBERRIES);
    }

    @Override
    public void addTopLayer() { // Adds a top cake layer and its cream
        addCakeLayer();
        cake.setTopLayerCream(Cream.RED_BERRIES);
    }

    @Override
    public void addMessage(String m) { // Adds a message to the cake
        cake.setMessage(m);
    }

    @Override
    public void createCake() { // Create. That. Cake!!!
        cake = new Cake();
        cake.setCakeLayer("Love"); // sets type of cake layers
    }

    @Override
    public Cake getCake() {
        return cake;
    }
}



