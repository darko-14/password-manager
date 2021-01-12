import java.util.Scanner;

public class Manager {
    
    static String user;
    static String masterPassword;

    static final Database db = new Database();
    
    
    public static void main(String[] args) {
        showFirstMenu();

    }

    public static void showFirstMenu(){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("Password Manager");
        System.out.println("----------------");
        System.out.println("Choose an option");
        System.out.println("1. Login");
        System.out.println("2. Register");
        int choice = sc.nextInt();
        System.out.println("\n");
        System.out.println(choice);

        switch (choice) {
            case 1:
               try {
                    System.out.println("----------------");
                    System.out.println("Login:");
                    System.out.println("----------------");
                    System.out.println("Enter your username");
                    String username = sc.next();
                    System.out.println("Enter your master password");
                    String masterPassword = sc.next();

                    if (db.login(username, masterPassword)) {
                        System.out.println("\n");
                        System.out.println("Welcome - "+username+" -");         
                        user = username;               
                        showMainMenu();
                    }else{
                        System.out.println("Wrong username or password. Try again.");
                    }
                
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                break;
            case 2:
                try {
                    System.out.println("----------------");
                    System.out.println("Register:");
                    System.out.println("----------------");
                    System.out.println("Enter your username");
                    String newUsername = sc.next();
                    System.out.println("Enter your master password");
                    String newMasterPassword = sc.next();   
                    User u = new User (newUsername, newMasterPassword);

                    if(newUsername.length() > 2 && newMasterPassword.length() > 3){
                        db.register(u);
                    }else{
                        System.out.println("Choose longer username & password");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Enter a valid option");
                break;
        }
        sc.close();
    }

    public static void showMainMenu(){
        Scanner sc = new Scanner(System.in);
        db.showPasswords(user);
        System.out.println("-------------");
        System.out.println("** Menu **");
        System.out.println("1. Add new password");
        System.out.println("2. Reveal password (id)");
        System.out.println("3. Delete password (id)");
        System.out.println("4. Change your Master Password");
        System.out.println("5. Logout");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                try {
                    System.out.println("Add new password:");
                    System.out.println("----------------");
                    System.out.println("Enter address");
                    String address = sc.next();
                    System.out.println("Enter password");
                    String password = sc.next();   
                    System.out.println("Added successfull!");
                    System.out.println(address +" "+ password);
                    db.addNewPassword(user, address, password);
                    showMainMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    System.out.println("Reveal password: ");
                    System.out.println("----------------");
                    System.out.println("Enter id");
                    String id = sc.next();
                    System.out.println("Password: "+db.revealPassword(user, id));
                    showMainMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    System.out.println("Delete password:");
                    System.out.println("----------------");
                    System.out.println("Enter id");
                    String id = sc.next();
                    db.deletePassword(user, id);
                    showMainMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    System.out.println("Change your master password:");
                    System.out.println("----------------");
                    System.out.println("Enter your old master password");
                    String oldmp = sc.next();
                    System.out.println("Enter your new master password");
                    String newmp = sc.next();
                    if (db.changeMasterPassword(user, oldmp, newmp)) {
                        System.out.println("Password Changed");
                        showMainMenu();
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                try {
                    user = null;
                    showFirstMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        
            default:
                System.out.println("Enter a valid option");
                break;
        }
        sc.close();
    }

}

    
