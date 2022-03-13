package ex2;

public interface Handler {
    public void handle(String str);
    public void setNext(Handler h);
}
