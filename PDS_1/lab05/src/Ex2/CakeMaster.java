package Ex2;

class CakeMaster {
    protected CakeBuilder builder;

    public void setCakeBuilder(CakeBuilder cb ){ // Ex2.Cake Type is set here
        builder = cb;
    }

    public Cake getCake() {
        return builder.getCake();
    }

    public void createCake(int layerNum, String message) {
        builder.createCake();
        builder.setCakeShape(Shape.CIRCLE);
        if(layerNum > 1) {
            builder.addCreamLayer();
            for (int i = 0; i < layerNum-1; i++) {
                builder.addMidLayer();
            }
            builder.addTopLayer();
        }
        else if(layerNum == 1) {
            builder.addTopLayer();
        }
        builder.addTopping();
        builder.addMessage(message);
    }

    public void createCake(String message) {
        builder.createCake();
        builder.addTopLayer();
        builder.addTopping();
        builder.addMessage(message);
    }

    public void createCake(Shape shape, int layerNum, String message) {
        builder.createCake();
        builder.setCakeShape(shape);
        if(layerNum>1) {
            builder.addCreamLayer();
            for (int i = 0; i < layerNum-1; i++) {
                builder.addMidLayer();
            }
            builder.addTopLayer();
        }
        else if(layerNum == 1) {
            builder.addTopLayer();
        }
        builder.addTopping();
        builder.addMessage(message);
    }
}
