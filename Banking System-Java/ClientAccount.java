public class ClientAccount {

    int ID;
    String userName;

    // Constructor initialization
    public ClientAccount(int ID, String userName){
        this.ID = ID;
        this.userName = userName;
    }

    public void getInfo(){
        System.out.println("ID: " + this.ID + " NAME: " + this.userName);
    }
}
