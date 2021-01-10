import java.util.Scanner;

public class Manager {
    
    static String username;
    static String masterPassword;

    static final Database db = new Database();
    static final User u = new User(username, masterPassword);
    
    
    public static void main(String[] args) {
        showFistMenu();
        //Database.connection();

    }

    public static void showFistMenu(){
        
        Scanner sc = new Scanner(System.in);
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
                    System.out.println("Login menu:");
                    System.out.println("----------------");
                    System.out.println("Enter your username");
                    String username = sc.next();
                    System.out.println("Enter your master password");
                    String masterPassword = sc.next();
                    System.out.println(username +" "+ masterPassword);
                    showMainMenu();
                
                    // input > 0
                    // Login
                    // showMainMenu()
                } catch (Exception e) {
                    // Login failed. Try again
                }
                
                break;
            case 2:
                try {
                    System.out.println("Register menu:");
                    System.out.println("----------------");
                    System.out.println("Enter your username");
                    String newUsername = sc.next();
                    System.out.println("Enter your master password");
                    String newMasterPassword = sc.next();   
                    User u = new User (newUsername, newMasterPassword);

                    if(newUsername.length() > 2 && newMasterPassword.length() > 3){
                        db.register(u);
                        //showFistMenu();
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
        //System.out.println("Saved passwords for - "+ u.getUsername() + " - ");
        // showPasswords()
        System.out.println("-------------");
        System.out.println("- menu -");
        System.out.println("1. Add new password");
        System.out.println("2. Edit password (id)");
        System.out.println("3. Delete password (id)");
        System.out.println("4. Change your Master Password");
        System.out.println("5. Logout");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                try {
                    System.out.println("Add new password:");
                    System.out.println("----------------");
                    System.out.println("Enter adress");
                    String adress = sc.next();
                    System.out.println("Enter password");
                    String password = sc.next();   
                    System.out.println("Added successfull!");
                    System.out.println(adress +" "+ password);
                    //addNewPassword()
                    //showMainMenu()
                } catch (Exception e) {
                    //TODO: handle exception
                }
                break;
            case 2:
                try {
                    System.out.println("Edit password:");
                    System.out.println("----------------");
                    System.out.println("Enter new adress or hit Enter to continue");
                    String newAdress = sc.next();
                    System.out.println("Enter new password or hit Enter to continue");
                    String newPassword = sc.next(); 
                    //editPassword()
                    //showMainMenu()
                } catch (Exception e) {
                    //TODO: handle exception
                }
                break;
            case 3:
                try {
                    System.out.println("Delete password:");
                    System.out.println("----------------");
                    System.out.println("Enter id");
                    String id = sc.next();
                    //deletePassword(id)
                    //showMainMenu()
                } catch (Exception e) {
                    //TODO: handle exception
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
                    System.out.println("Enter again your new master password");
                    String newmp2 = sc.next();
                    //changeMasterPassword()
                    //showMainMenu()
                } catch (Exception e) {
                    //TODO: handle exception
                }
                break;
            case 5:
                try {
                    //logout()
                    //showMainMenu()
                } catch (Exception e) {
                    //TODO: handle exception
                }
                break;
        
            default:
                System.out.println("Enter a valid option");
                break;
        }
    }

}

    
