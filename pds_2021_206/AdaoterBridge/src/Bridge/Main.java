package Bridge;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Our interface, we should be able to do everything with just this object, only varying the storage passed
        ContactsInterface myInterface = new Interface();

        // Binary storage
        Contact paula = new Contact("Paula Couves",910023001,"couves@hotmail.com");
        Contact batista = new Contact("Batista",910000000,"mariobatista@hotmail.com");
        Contact rui = new Contact("Rui",920011223,"ruizvaldo@gmail.com");
        Contact sara = new Contact("Sara Bastos",929292929,"sarita@outlook.com");
        Contact guilherme = new Contact("Gui Guimarães",914206996,"guiiguii@outlook.com");
        Contact amora = new Contact("Amari",917766889,"amorita@icloud.com");
        List<Contact> lista = new ArrayList<>();
        lista.add(paula); lista.add(batista); lista.add(rui); lista.add(sara); lista.add(guilherme); lista.add(amora);
        ContactsStorageBinary bin = new ContactsStorageBinary();
        bin.saveContacts(lista); // initializes our storage with the list defined above

        // Play with the binary storage
        // batista -> pre-existing contact on the list
        Contact gina = new Contact("Gina",445567123,""); // non-existent contact
        Contact agnes = new Contact("Agnes Crumplebottom", 917709091,"theagnescrump@gmail.com"); // non-existent contact, we wish to add it
        //---------------------------
        myInterface.openAndLoad(bin); // load contacts from txt onto the interface
        //System.out.println(myInterface); // check if contacts were successfully loaded
        myInterface.saveAndClose(); // save contacts currently in the interface into a binary file in Ex2Output
        System.out.print("Should be true: ");
        System.out.println(myInterface.exist(batista)); // true
        System.out.print("Should be false: ");
        System.out.println(myInterface.exist(gina)); // false
        System.out.print("Should be 'Gui Guimarães;914206996;guiiguii@outlook.com': ");
        System.out.println(myInterface.getByName("Gui Guimarães")); // should print this person, as they exist on the current list
        System.out.print("Should be null: ");
        System.out.println(myInterface.getByName("Gina")); // should print null, Gina is not a contact from the list
        System.out.print("Should be true: ");
        System.out.println(myInterface.add(agnes)); // true, brand new contact to add to the list!
        System.out.print("Should be false: ");
        System.out.println(myInterface.add(agnes)); // false, this person is already in the list, won't add again
        System.out.print("Should be true: ");
        System.out.println(myInterface.remove(batista)); // true, should be able to remove
        System.out.print("Should be false: ");
        System.out.println(myInterface.remove(gina)); // false, this contact does not exist, so it shouldn't alter the list
        //System.out.println(myInterface); // check if contacts were successfully added and removed
        myInterface.saveAndClose(bin); // update the storage with the alterations currently on the interface, also saves contacts to file
        //System.out.println(txt); // check if contacts were successfully stored onto txt

        // Text storage
        ContactsStorageTxt txt = new ContactsStorageTxt(); // our txt storage (only works with .txt files)
        File contacts1 = new File("lab06\\Ex2Input\\contacts1.txt");
        txt.readContactsTxt(contacts1); // load contacts from text file, txt holds contacts of file contacts1.txt

        // Play with the text storage
        // batista -> pre-existing contact on the list
        Contact gargula = new Contact("Gargula",333,"eheheh"); // non-existent contact
        Contact palmira = new Contact("Palmira Esteves", 9177,"palmirita@gmail.com"); // non-existent contact, we wish to add it
        //---------------------------
        myInterface.openAndLoad(txt); // load contacts from txt onto the interface
        //System.out.println(myInterface); // check if contacts were successfully loaded
        myInterface.saveAndClose(); // save contacts currently in the interface into a text file in Ex2Output TODO: different file format depending on latest interface opened?
        System.out.print("Should be true: ");
        System.out.println(myInterface.exist(batista)); // true
        System.out.print("Should be false: ");
        System.out.println(myInterface.exist(gargula)); // false
        System.out.print("Should be 'Fátima Teresa;934137854;teresinha@icloud.com': ");
        System.out.println(myInterface.getByName("Fátima Teresa")); // should print this person, as they exist on the current list
        System.out.print("Should be null: ");
        System.out.println(myInterface.getByName("Batata")); // should print null, Batata is not a contact from the list
        System.out.print("Should be true: ");
        System.out.println(myInterface.add(palmira)); // true, brand new contact to add to the list!
        System.out.print("Should be false: ");
        System.out.println(myInterface.add(palmira)); // false, this person is already in the list, won't add again
        System.out.print("Should be true: ");
        System.out.println(myInterface.remove(batista)); // true, should be able to remove
        System.out.print("Should be false: ");
        System.out.println(myInterface.remove(gargula)); // false, this contact does not exist, so it shouldn't alter the list
        //System.out.println(myInterface); // check if contacts were successfully added and removed
        myInterface.saveAndClose(txt); // update the storage with the alterations currently on the interface, also saves contacts to file
        //System.out.println(txt); // check if contacts were successfully stored onto txt
    }
}
