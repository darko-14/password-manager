public class User {

    private String username;
    private String mPass;

    public User(String username, String mPass){
        this.username = username;
        this.mPass = mPass;
        
    }

    public String getUsername(){ return username;}
    public String getMPass(){ return mPass; }

    public void setUsername(String username){
        this.username = username;
    }
    public void setMPass(String mPass){
        this.mPass = mPass;
    }

    
}