import java.sql.Date;
import java.text.SimpleDateFormat;

public class User {

    private String username;
    private String mPass;
    private String createdAt;

    public User(String username, String mPass){
        this.username = username;
        this.mPass = mPass;
        
    }

    
    public String getUsername(){ return username;}
    public String getMPass(){ return mPass; }
    public String getCreatedAt() { return createdAt; }

    public void setUsername(String username){
        this.username = username;
    }
    public void setMPass(String mPass){
        this.mPass = mPass;
    }
    public void setCreatedAt(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        createdAt = formatter.format(date);        
    }
    
}