package Ex1;

import java.util.ArrayList;

public class PanicSort implements  Algorithm
{
    public ArrayList<String> sort(ArrayList<String> unsortedList){
        ArrayList<String> sortedList= new ArrayList<>(unsortedList);
        sortedList.add("List sorted via PanicSort.");

        return sortedList;
    }
}
