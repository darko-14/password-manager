import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static void changeMasterPassword() {
    }

    public static void logout() {
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
  
    public static void showPasswords(){}

}
