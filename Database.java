import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;

public class Database{
    private byte[] salt = {};
    public static void addNewUser() {
    }

    public static void addNewPassword() {
    }

    public static void editPassword() {
    }

    public static void deletePassword() {
    }
    public String validate(String pw, byte[] salt) throws NoSuchAlgorithmException {
        //sha1 + salt
        String hashedPassword = null;
                
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
    public boolean login(String u, String pw) throws NoSuchAlgorithmException {

        String query = "SELECT username, mPassword, salt FROM password_database.users";
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_database?serverTimezone=EST", "root", "darko");
            Statement st = con.createStatement();  
            ResultSet rs = st.executeQuery(query);  
            while(rs.next()){
                System.out.println("rs: "+rs.getString(1));
                System.out.println("u: "+u);
                
                if(rs.getString(1) == u){  // ne vleva vo ovoj  if
                    System.out.println("Vo prv if");
                    String pass = pw;
                    byte[] salt = rs.getBytes(3);
                    String hpw = validate(pass, salt);
                    if(hpw == rs.getString(2)){
                        System.out.println("logged in");
                        break;
                    }else{
                        System.out.println("vo prv else");
                    }
                }else{
                    System.out.println("vo else");
                    break;
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("slabo login");
        }



        return false;
    }

    public void register(User u) throws NoSuchAlgorithmException{
        
        String hPass = hashPassword(u.getMPass());
        String query = "INSERT INTO password_database.users (username,mPassword,salt) VALUES ('" + u.getUsername() + "','"+ hPass +"','"+ salt + "')";
        
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



    public String hashPassword(String pw) throws NoSuchAlgorithmException {
        //sha1 + salt
        String hashedPassword = null;
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        salt = new byte[16];
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
