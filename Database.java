import java.sql.*;

public class Database {
    
    public static void connection(){
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");

            Statement statement = connection.createStatement();

            ResultSet rset = statement.executeQuery("SELECT * FROM password_database.Users");

            while(rset.next()){
                System.out.println(rset.getString("id") + rset.getString("username") + rset.getString("mPassword") + rset.getString("createdAt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNewUser(){}
    
    public static void addNewPassword(){}
    
    public static void editPassword(){}
    
    public static void deletePassword(){}
    
    public static void login(){}
    
    public void register(User u){
        
        if(checkAvailability(u.getUsername())){
            
        }else{
            System.out.println("Username already exists!");
        };
    }
    
    public static void changeMasterPassword(){}
    
    public static void logout(){}
    
    public boolean checkAvailability(String username){
        // if username in db return false
        return true; 
    }

    public static void hashPassword(){}
    
    public static void showPasswords(){}

}
