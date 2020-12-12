import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class BejarMidterms {
    private static Scanner scanner = new Scanner(System.in);
    private static Contacts contactsList = new Contacts();
    static List<Integer> l = new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        instructions();
        menu();
    }

    public static void menu() {
        boolean quit = false;
        int choice = 0;
        while (!quit) {
            System.out.println("Enter your choice from menu:");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    instructions();
                case 1:
                    addContact();
                    break;
                case 2:
                    removeContact();
                    break;
                case 3:
                    contactsList.printContacts();
                    break;
                case 4:
                    updateContact();
                    break;
                case 5:
                    quit = true;
                    break;

                default:
                    break;
            }
        }

    }

    public static void addContact() {
        //adding name
        System.out.println("Enter the name of the new contact");
        String newContact = scanner.nextLine();
        contactsList.addNewContact(newContact);

        //adding age
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Age(No.)");
        int num = in.nextInt();
        if (num == 0)
            System.out.println("0 is not alowed");
        else
            l.add(num);
        System.out.println("After adding Name and Age, enter choice number 3 (View All) as reference when updating or removing contacts & age");
    }

    public static void updateContact() {
        System.out.println("Enter the name of the contact that you want to replace");
        String replacedName = scanner.nextLine();
        System.out.println("Enter the updated name");
        String newName = scanner.nextLine();
        contactsList.updateContact(replacedName, newName);
    }

    public static void removeContact() {
        System.out.println("Which contact do you wish to remove?");
        String removedContact = scanner.nextLine();
        contactsList.removeContact(removedContact);
    }


    public static void instructions() {
        System.out.println("1)Add new Contact & Age\n2)Delete Contact & Age\n3)View All (Contacts & Age)\n4)Update contact & age\n5)Exit");
    }


    public static class Contacts {
        private ArrayList<String> contactsList = new ArrayList<>();

        public void printContacts() {
            //printing the Contact List
            System.out.println("You have " + contactsList.size() + " contacts. (Contact List)");
            for (int i = 0; i < contactsList.size(); i++) {
                System.out.println((i + 1) + ". " + contactsList.get(i));

                //printing the Age list
                if (l.isEmpty())
                    System.out.println("list is empty ");
                else
                    System.out.println("Age list in relation to Contact List(0 = 1, 1 = 2, etc.)");
                for (int z = 0; z < l.size(); z++)
                    System.out.println("Age of Number " + z + " : " + l.get(z) + " ");
            }

        }

        public void addNewContact(String contact) {
            contactsList.add(contact);
        }

        public void removeContact(String contact) {
            int index = findContact(contact);
            if (index >= 0) {
                deleteContact(index);
            } else {
                System.out.println("No such contact found");
            }

            Scanner in=new Scanner(System.in);
            System.out.println("Enter position you want to delete/ in relation to age list (0 = 1, 1 = 2, etc.)");
            int num=in.nextInt();
            l.remove(num);
        }

        public void updateContact(String replacedContact, String newContact) {
            Scanner in=new Scanner(System.in);
            int index = findContact(replacedContact);
            if (index >= 0) {
                contactsList.set(findContact(replacedContact), newContact);
            } else {
                System.out.println("No such contact found to update");
            }

            // updating age
            int num1,num2;
            System.out.println("Enter new age");
            num2=in.nextInt();
            if(num2==0)
                System.out.println("0 is not alowed");
            else{
                try{
                    System.out.println("To update Age, Enter position you want to edit from the Age List from (0, 1, 2 etc.)");
                    num1=in.nextInt();
                    l.set(num1, num2);
                }catch(IndexOutOfBoundsException e) {
                    System.err.println("caught IndexOutOfBoundsException: specified position is empty " + e.getMessage());
                }
            }
        }

        public int findContact(String contact) {
            return contactsList.indexOf(contact);
        }

        private void deleteContact(int index) {
            contactsList.remove(index);
        }

        public ArrayList<String> getContactsList() {
            return contactsList;
        }
    }
}