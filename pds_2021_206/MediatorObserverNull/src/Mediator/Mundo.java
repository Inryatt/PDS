package ex3;

public class Mundo {
    private Script mediator;
    private String name;
    private boolean isOn;
    private boolean LANisOn;

    public Mundo(String name, Script script) {
        this.name = name;
        this.LANisOn = false;
        this.mediator = script;
    }

    public void loadWorld() {
        this.isOn = true;
    }

    public void LANon() {
        this.LANisOn = true;
    }

    public void closeWorld() {
        this.isOn = false;
    }

    public void LANoff() {
        this.LANisOn = false;
    }

    public boolean isRunning(){
        return isOn;
    }

}
