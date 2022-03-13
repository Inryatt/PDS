package ListaContactos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsStorageBinary implements ContactsStorageInterface {
    private List<Contact> contacts = new ArrayList<>();

    // Reads a binary file that stores an ArrayList object with all the contacts
    // Erases any previous stored contacts!
    public boolean readContactsBinary(File file) {
        this.contacts.clear();
        FileInputStream fis;
        ObjectInputStream ois;
        List<Contact> fileContacts;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            fileContacts = (List<Contact>) ois.readObject();
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
        this.contacts.addAll(fileContacts);
        return true;
    }

    public List<Contact> loadContacts() {
        return contacts;
    }

    public boolean saveContacts(List<Contact> list) {
        // rewrites storage
        this.contacts.clear();
        this.contacts.addAll(list);
        return true;
    }
}
