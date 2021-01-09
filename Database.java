import java.sql.*;

public class Database {
    
    


    public static void connection(String query){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");

            Statement statement = connection.createStatement();

            String query1 = "SELECT * FROM password_database.Users";
        
            ResultSet rset = statement.executeQuery(query);

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

            String hPass = hashPassword(u.getMPass());
            String q = "INSERT INTO password_database.Users (id,username,mPassword,CreatedAt) VALUES (" + 5 + u.getUsername()+ ","+hPass+","+u.getCreatedAt()+")" ;
            connection(q);    

        }else{
            System.out.println("Username already exists!");
        };
    }
    
    public static void changeMasterPassword(){}
    
    public static void logout(){}
    
    public boolean checkAvailability(String username){
        String query = "SELECT * FROM password_database.Users WHERE 'username' = "+username;
        connection(query);
        System.out.println(query);

        System.out.println(query);
        // if username in db return false
        return true; 
    }

    public static String hashPassword(String pw){
        return pw;
    }
    
    public static void showPasswords(){}

}
