package ex3;

public class Main {

    public static void main(String[] args){
        CollectionHolder coll=new CollectionHolder();
        Control control=new Control();


        Command addCom = new CommandAdd(coll);
        Command delCom = new CommandRemove(coll);


        addCom.execute("Teste");

        System.out.println(coll.toString());

        addCom.execute("Palavra");
        System.out.println(coll.toString());

        addCom.undo();
        System.out.println(coll.toString());

        delCom.execute("Teste");
        System.out.println(coll.toString());

        delCom.undo();
        System.out.println(coll.toString());


    }
}
