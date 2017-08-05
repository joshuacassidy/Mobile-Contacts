import java.util.ArrayList;

public class Phone {
    private String nyNumber;
    private ArrayList<Contact> myContacts;

    public Phone(String nyNumber) {
        this.nyNumber = nyNumber;
        this.myContacts = new ArrayList<Contact>();
    }
    public boolean addNewContact(Contact contact) {
        if(findContact(contact.getName()) >=0) {
            System.out.println("Contact is already on file");
            return false;
        }

        myContacts.add(contact);
        return true;

    }

    public boolean update(Contact oldContact, Contact newContact){
        int foundPosition = findContact(oldContact);
        if(foundPosition < 0){
            System.out.println(oldContact.getName() + " was not found.");
            return false;
        }
        this.myContacts.set(foundPosition,newContact);
        System.out.println(oldContact.getName() + " was replaced with " + newContact.getName());
        return true;
    }

    public boolean removeContact(Contact contact){
        int foundPositon = findContact(contact);
        if(foundPositon < 0){
            System.out.println(contact.getName() + " was not found.");
            return false;
        }
        this.myContacts.remove(foundPositon);
        System.out.println(contact.getName() + "was deleted");
        return true;
    }

    private int findContact(Contact contact){
        return this.myContacts.indexOf(contact);
    }

    private int findContact(String contactName){
        for(int i = 0; i<this.myContacts.size(); i++){
            Contact contact = this.myContacts.get(i);
            if(contact.getName().equals(contactName)){
                return i;
            }
        }
        return -1;
    }


    public String queryContact(Contact contact){
        if (findContact(contact) >= 0){
            return contact.getName();
        }
        return null;
    }

    public Contact queryContact(String name){
        int postion = findContact(name);
        if(postion >= 0){
            return this.myContacts.get(postion);
        }
        return null;
    }
    public void printContacts(){
        System.out.println("Contact List:");
        for(int i = 0; i < this.myContacts.size(); i++){
            System.out.println((i+1) + ". " + this.myContacts.get(i).getName() + " -> " + this.myContacts.get(i).getPhoneNumber());
        }
    }

}
