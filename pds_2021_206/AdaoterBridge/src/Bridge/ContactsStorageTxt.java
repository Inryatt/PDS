package Bridge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsStorageTxt implements ContactsStorageInterface {
    private List<Contact> contacts = new ArrayList<>();

    // Reads a text file with one contact per line in the format name;phone number;email
    // Erases any previous stored contacts!
    public boolean readContactsTxt(File file) {
        try {
            this.contacts.clear();
            Scanner contactList = new Scanner(file);
            while (contactList.hasNextLine()) {
                String contactText = contactList.nextLine();
                String[] contactParameters = contactText.split(";");
                if (contactParameters.length != 3) {
                    this.contacts.clear();
                    System.err.println("Bad file format. (Wrong number of contact arguments).");
                    return false;
                }
                if (!contactParameters[1].matches("[0-9]+")) {
                    this.contacts.clear();
                    System.err.println("Bad file format. (phone number is not numeric).");
                    return false;
                }
                contacts.add(new Contact(contactParameters[0], Integer.parseInt(contactParameters[1]), contactParameters[2]));
            }
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Could not read the contact list.");
            return false;
        }
    }

    public List<Contact> loadContacts() {
        return this.contacts;
    }

    public boolean saveContacts(List<Contact> list) {
        // rewrites storage
        this.contacts.clear();
        this.contacts.addAll(list);
        return true;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder("Storage:\n");
        for(Contact contact : contacts) ret.append(contact).append("\n");
        return ret.toString();
    }
}
