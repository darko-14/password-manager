import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Database{
    /* 
    1. Create a database manually in MySQL Workbench
    2. Create a table for the users 
    create table password_database.users (id int unsigned auto_increment not null,username varchar(50) not null unique,
    mPassword varchar(500) not null, createdAt timestamp default now(), primary key (id));
    3. Create a table for the saved passwords
    create table password_database.passwords (id int unsigned auto_increment not null,username varchar(50) not null unique,
    adress varchar(100) not null unique,password varchar(500) not null,createdAt timestamp default now(),primary key (id),
    foreign key (username) references users (username));
    */

    final String secretKey = "secretkey";

    AES aes = new AES();
    public void addNewPassword(String u, String a, String p) {

        String hpw = AES.encrypt(p, secretKey);
        
        String query = "INSERT INTO password_database.passwords (username, adress, password) VALUES ('" + u + "','"+ a + "','"+ hpw +"')";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");
            PreparedStatement pst = con.prepareStatement(query);
            pst.execute();

            System.out.println("Password added successfully!");
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String revealPassword(String username, String id){
        String decryptedPass = null; 
        String query = "SELECT * FROM password_database.passwords WHERE username = '"+username+"' AND id = " +id+";";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");
            Statement st = con.createStatement();  
            ResultSet rs = st.executeQuery(query); 
            while(rs.next()){
                String pasq = rs.getString(4);
                decryptedPass = AES.decrypt(pasq, secretKey);
              }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedPass;
    }

    public static void editPassword() {

    }
    public void showPasswords(String username){
        String query = "SELECT * FROM password_database.passwords WHERE username = '"+username+"';";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");
            Statement st = con.createStatement();  
            ResultSet rs = st.executeQuery(query);  
            System.out.println("-------------");
            System.out.println("Saved Passwords");
            while(rs.next()){
                
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
                
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePassword(String username, String id) {
        String query = "DELETE FROM password_database.passwords WHERE username = '"+username+"' AND id = " +id+";";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");
            PreparedStatement pst = con.prepareStatement(query);
            pst.execute();
            con.close();
            System.out.println("Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   
    public boolean login(String u, String pw) throws NoSuchAlgorithmException {

        String query = "SELECT username, mPassword FROM password_database.users";
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");
            Statement st = con.createStatement();  
            ResultSet rs = st.executeQuery(query);  

            while(rs.next()){
            
                if(u.equals(rs.getString(1))){  
                    String pass = pw;                 
                    String hpw = hashPassword(pass);
                    //System.out.println("hpw: "+hpw);
                    if(hpw.equals(rs.getString(2))){
                        System.out.println("logged in");
                        return true;
                    }else{
                        System.out.println("vo prv else");
                        break;
                    }
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void register(User u) throws NoSuchAlgorithmException{
        
        String hPass = hashPassword(u.getMPass());
        String query = "INSERT INTO password_database.users (username,mPassword) VALUES ('" + u.getUsername() + "','"+ hPass + "')";
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");
            PreparedStatement pst = con.prepareStatement(query);
            pst.execute();

            System.out.println("Register successfull!");
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Username already exists!");
        }
    }

    public void changeMasterPassword(String u, String oldPass, String newPass) {
        // check user, hash old pass -> check old pass
        // hash new pass -> change old with new pass
    }

    public String hashPassword(String pw) throws NoSuchAlgorithmException {
        //sha1
        String hashedPassword = null;
 
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            //md.update(salt);
            byte[] bytes = md.digest(pw.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i <bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
  
   

}
