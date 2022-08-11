public class Account {

    int balance, accountID;
    String accountPassword;

    public Account(int balance, int accountID, String accountPassword){
        this.balance = balance;
        this.accountID = accountID;
        this.accountPassword = accountPassword;
    }

    public void viewBalance(){
        System.out.println("Your current balance is: " + this.balance);
    }
}
