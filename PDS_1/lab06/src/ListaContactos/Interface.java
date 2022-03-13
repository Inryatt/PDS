package ListaContactos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Interface implements ContactsInterface {
    private List<Contact> contacts = new ArrayList<>();
    private ContactsStorageInterface latestFormat = null;

    @Override
    public void openAndLoad(ContactsStorageInterface store) {
        // Take contacts from storage, resets the contact list if anything was in it before
        this.contacts.clear();
        this.contacts.addAll(store.loadContacts());
        this.latestFormat = store;
    }

    @Override
    public void saveAndClose() {
        // Uhhh, I guess it saves contact list to file? Wipes the current list and storage.
        fileWriter();
        this.latestFormat = null;
    }

    @Override
    public void saveAndClose(ContactsStorageInterface store) {
        // Saves contact list to file and specified storage (rewrites any contacts in storage!).
        store.saveContacts(this.contacts);
        this.latestFormat = store;
        fileWriter();
        this.latestFormat = null;
    }

    private void fileWriter() {
        // Writes to a file depending on latest storage passed!
        if(this.latestFormat instanceof ContactsStorageTxt) {
            try {
                FileWriter writer = new FileWriter("lab06\\Ex2Output\\contacts1.txt");
                for(Contact contact : contacts) {
                    writer.write(contact + "\n");
                }
                writer.close();
            } catch (IOException e) {
                System.err.println("Could not write to text file.");
            }
        }
        else if(this.latestFormat instanceof ContactsStorageBinary) {
            FileOutputStream fos;
            ObjectOutputStream oos;
            try {
                fos = new FileOutputStream(new File("lab06\\Ex2Output\\contacts1"));
                oos = new ObjectOutputStream(fos);
                oos.writeObject(this.contacts);
            } catch (IOException e) {
                System.err.println("Could not write to binary file.");
            }
        }
        else {
            System.err.println("Storage format not yet implemented.");
        }
    }

    @Override
    public boolean exist(Contact contact) {
        // checks if a contact exists in contact list currently being handled
        return this.contacts.contains(contact);
    }

    @Override
    public Contact getByName(String name) {
        for(Contact contact : this.contacts) {
            if(contact.getName().equals(name)) return contact;
        }
        return null;
    }

    @Override
    public boolean add(Contact contact) {
        // adds someone to the current, doesn't add if contact already exists in list
        if(!this.contacts.contains(contact)) {
            this.contacts.add(contact);
            return true;
        }
        else return false;
    }

    @Override
    public boolean remove(Contact contact) {
        // returns false if there is no such contact to delete
        return this.contacts.remove(contact);
    }

    public String toString() {
        StringBuilder ret = new StringBuilder("Contacts currently being handled:\n");
        for(Contact contact : contacts) ret.append(contact).append("\n");
        return ret.toString();
    }
}
