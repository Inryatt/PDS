package ex3;

import java.util.Collection;

public class CommandRemove   implements Command{
    CollectionHolder collection;


    CommandRemove(CollectionHolder c){
        this.collection=c;
    }

    public void undo() {
        if (collection.getLastAdded() != null) {
            collection.addColl(collection.getLastRemoved());
            collection.popLastRemoved();
    }
        else
            System.out.println("There is no more undo to be done.");
    }

    public void execute( String string){
        collection.remColl(string);
        collection.setLastRemoved(string);
    }

}
