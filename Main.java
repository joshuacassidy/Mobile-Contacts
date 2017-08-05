import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Phone myphone = new Phone("0039 330 4404");

    public static void main(String[] args){
        boolean quit = false;
        Actions[] listOfActions = new Actions[] {new printActions(), new  printContacts(), new addNewContact(), new updateContact(), new removeContact(),new queryContact()};
        System.out.flush();
        System.out.print("\033[H\033[2J");
        System.out.println("Phone started.");
        Actions printActions = selectAction(listOfActions,0);
        printActions.Action(scanner,myphone);
        int choice = 0;
        while (!quit){
            System.out.println("\nPress 0 to get the list of options.\nSelect an option: ");
            try{
                choice = scanner.nextInt();
                System.out.flush();
                System.out.print("\033[H\033[2J");
                Actions doAction = selectAction(listOfActions,choice);
                doAction.Action(scanner, myphone);
            }
                catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                    System.out.println(choice != 6 ? "Error invalid input shutting down phone." : "Shutting down phone");
                    quit = true;
                }
            }
        }
        public static Actions selectAction(Actions[] action,int selectedAction){
            return action[selectedAction];
        }
    }

interface Actions {
    void Action(Scanner scanner,Phone myphone);
}

class printContacts implements Actions {

    public void Action(Scanner scanner, Phone myphone) {
        myphone.printContacts();
    }
}

class addNewContact implements Actions{

    public void Action(Scanner scanner,Phone myphone){
        System.out.println("Enter new contact name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name,phone);
        System.out.printf(myphone.addNewContact(newContact) ? "New contact was added to the phone: \nname: %s \nphone: %s\n": "%s cannot be added to the phone, as %s already exists.\n",name,name);
        }
    }


class updateContact implements Actions{

    public void Action(Scanner scanner,Phone myphone){
        System.out.println("Enter existing contact name: ");
        scanner.nextLine();
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
        System.out.println(myphone.update(existingContactRecord,newContact) ? "Update was successful." : "Error updating contact.");
    }
}



class removeContact implements Actions{

    public void Action(Scanner scanner,Phone myphone) {
        System.out.println("Enter existing contact name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Contact existingContactRecord = myphone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.println(myphone.removeContact(existingContactRecord) ? "Successfully deleted." : "Error deleting contact.");
        }
    }

class queryContact implements Actions {
    public void Action(Scanner scanner,Phone myphone) {

        System.out.println("Enter existing contact name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Contact existingContactRecord = myphone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.printf("Name: %s phone number is %s\n", existingContactRecord.getName(), existingContactRecord.getPhoneNumber());
    }
}

class printActions implements Actions{

    public void Action(Scanner scanner,Phone myphone) {
        System.out.println("\nAvailable actions: \n press");
        System.out.println("Choose your action: \n 0 - to print available actions \n 1 - to print contacts \n 2 - to add a new contact\n 3 - to update an existing contact\n 4 - to remove an existing contact\n 5 - query if an existing contact exists\n 6 - to shutdown \n ");
    }
}

