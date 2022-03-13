package ex3;


import java.util.ArrayList;
import java.util.Collection;

public class CollectionHolder {

    public CollectionHolder(){
        collection = new ArrayList<>();
        stack = new ArrayList<>();
        removestack= new ArrayList<>();
    }

    private  Collection<String> collection;

    private  ArrayList<String> stack;
    private  ArrayList<String> removestack;

    public Collection<String> getColl()
    {
        return this.collection;
    }

    public void addColl(String string){
        this.collection.add(string);
    }

    public void remColl(String string){
        this.collection.remove(string);
    }


    public String getLastAdded() {
        return this.stack.get(this.stack.size()-1);
    }

    public String toString(){
        String togo="[ ";
        for (String el : collection){
            togo=togo+" "+el+" ";
        }
        togo=togo+" ]";
        return togo;
    }

    public  void setLastAdded(String other){
        stack.add(other);
        while(stack.size()>5){
            stack.remove(0);
        }
    }
    public  void popLastAdded(){
        stack.remove(stack.size()-1);
    }

    public  String getLastRemoved(){
        return removestack.get(removestack.size()-1);
    }

    public  void popLastRemoved(){
        removestack.remove(removestack.size()-1);
    }

    public  void setLastRemoved(String string){
        removestack.add(string);
        while(removestack.size()>5){
            removestack.remove(0);
        }
    }

}
