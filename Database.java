import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;

public class Database{

    public static void addNewUser() {
    }

    public static void addNewPassword() {
    }

    public static void editPassword() {
    }

    public static void deletePassword() {
    }

    public static void login() {
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
            e.printStackTrace();
            System.out.println("Username already exists!");
        }
    }

    public static void changeMasterPassword() {
    }

    public static void logout() {
    }

    public boolean checkAvailability(String username) {
        String query = "SELECT username FROM password_database.users";
        boolean available = true;
        try {
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int counter = 1;

            while (rs.next()) {
                if (rs.getString(counter) == username) {
                    available = false;
                }
                counter++;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Greska vo checkAvailability()");
        }
        return available;

    }



    public static String hashPassword(String pw) throws NoSuchAlgorithmException {
        //sha1 + salt
        String hashedPassword = null;
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
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
  
    public static void showPasswords(){}

}
