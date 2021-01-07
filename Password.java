import java.sql.Date;
import java.text.SimpleDateFormat;

public class Password {

   
    private String adress;
    private String password;
    private String createdAt;
    
    public String getPassword(){ return password; }
    public String getAdress(){ return adress; }
    public String getCreatedAt() { return createdAt; }

    public void setAdress(String adr){
        adress = adr;
    }

    public void setPassword(String pass){
        adress = pass;
    }
    public void setCreatedAt(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        createdAt = formatter.format(date);

        
    }
    
    



}
