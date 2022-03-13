package ex1;

import java.util.Iterator;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args){
        VectorGeneric<Integer> vetor = new VectorGeneric<>() ;
        vetor.addElem(2);
        vetor.addElem(3);
        vetor.addElem(5);
        vetor.addElem(7);
        vetor.addElem(9);
        vetor.addElem(11);
        vetor.addElem(13);
        vetor.addElem(17);
        vetor.addElem(-30);

        Iterator<Integer> itr = vetor.Iterator();
        ListIterator<Integer> itr2 = vetor.listIterator();

        while(itr.hasNext()) {
        itr.next();
            if(itr2.hasNext()){
                System.out.println("itr2 "+itr2.next());
              //  System.out.println("itr2 "+itr2.previousIndex());

            }
        }
        while(itr2.hasPrevious()){
            System.out.println("itr2 "+itr2.previous());
        }


    }
}
