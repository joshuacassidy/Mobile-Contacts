import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Phone myphone = new Phone("0039 330 4404");

    public static void main(String[] args){
        boolean quit = false;
        startPhone();
        printActions();
        while (!quit){
            System.out.println("Enter action: (6 to show actions)");
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action){
                case 0:
                    System.out.println("\nShutting down...");
                    quit = true;
                    break;
                case 1:
                    myphone.printContacts();
                    break;
                case 2: addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;
            }
        }
    }
    private static void addNewContact(){
        System.out.println("Enter new contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name,phone);
        if(myphone.addNewContact(newContact)){
            System.out.println("New contact was added to the phone: \nname:" + name + " \nphone: " + phone);
        }
        else{
            System.out.println(name + " cannot be added to the phone, as " + name + " already exists.");
        }
    }
    public static void updateContact(){
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = myphone.queryContact(name);
        if(existingContactRecord == null){
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Enter new contact name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String newPhone = scanner.nextLine();
        Contact newContact = Contact.createContact(newName,newPhone);
        if(myphone.update(existingContactRecord,newContact)){
            System.out.println("Update was successful.");
        }
        else{
            System.out.println("Error updating contact.");
        }
    }
    public static void removeContact(){
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = myphone.queryContact(name);
        if(existingContactRecord == null){
            System.out.println("Contact not found.");
            return;
        }
        if(myphone.removeContact(existingContactRecord)){
            System.out.println("Successfully deleted.");
        }
        else{
            System.out.println("Error deleting contact.");
        }
    }

    public static void queryContact(){
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = myphone.queryContact(name);
        if(existingContactRecord == null){
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Name: " + existingContactRecord.getName() + " phone number is " + existingContactRecord.getPhoneNumber());
    }

    private static void startPhone(){
        System.out.println("Phone started.");
    }
    private static void printActions(){
        System.out.println("\nAvailable actions: \n press");
        System.out.println("0 - to shutdown \n 1 - to print contacts\n 2 - t- add a new contact\n 3 - to update an existing contact\n 4 - to remove an existing contact\n 5 - query if an existing contact exists\n 6 - to print a list of available actions. \n Choose your action: \n");

    }
}
