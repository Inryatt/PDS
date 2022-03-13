package ex3;

public class VPN {
    private Script mediator;
    private String name;
    private boolean isOn;

    public VPN(String name,Script mediator) {
        this.name = name;
        this.isOn = false;
        this.mediator=mediator;
    }

    public void start() {
        this.isOn=true;
    }

    public void stop() {
        this.isOn=false;
    }

    public boolean isRunning() { return isOn; }

}
