package ex3;

import java.util.Collection;

public class CommandAdd implements Command{

    CommandAdd(CollectionHolder c){
        this.collection=c;
    }

    CollectionHolder collection;

    public void execute(String string){
        collection.addColl(string);
        collection.setLastAdded(string);
    }

    public void undo(){
        if (collection.getLastAdded() != null) {
            collection.remColl(collection.getLastAdded());
            collection.popLastAdded();
       }
        else
            System.out.println("There is no more undo to be done.");
    }
}


