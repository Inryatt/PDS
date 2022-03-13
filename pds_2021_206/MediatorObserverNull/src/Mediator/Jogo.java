package ex3;

public class Jogo {
    private Script mediator;
    private String name;
    private boolean isOn;

    public Jogo(String name,Script mediator) {
        this.name = name;
        this.isOn = false;
        this.mediator=mediator;
    }

    public void run() {
        this.isOn=true;
    }

    public void noRun() {
        this.isOn=false;
    }

    public boolean isRunning(){
        return isOn;
    }

}
