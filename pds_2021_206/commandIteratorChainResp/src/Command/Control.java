package ex3;

public class Control {

    private Command command;
    public void setCommand(Command command){
        this.command=command;
    }

    public void doCommand(String content){
        command.execute(content);
    }


}
