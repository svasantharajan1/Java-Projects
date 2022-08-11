import java.util.*;

public class Main {
    // global variables
    public static boolean running = true;
    public static HashMap<String, Integer> registeredUsers = new HashMap<>(); // Contains all users
    public static ClientAccount[] clientAccounts = new ClientAccount[6];

    public static void main(String[] args) {
        System.out.println("Welcome to Bank of Siam");
        Account mainAccount = new Account(0, 1234, "password1");

        buildHashMap(registeredUsers);
        initializeClientAccounts();
        driverCode(mainAccount);
    }

    public static void buildHashMap(HashMap<String, Integer> registeredUsers){
        // Build the hashmap of registered users with a username and a corresponding ID
        registeredUsers.put("Account1", 9843);
        registeredUsers.put("Account2", 6345);
        registeredUsers.put("Account3", 1235);
        registeredUsers.put("Account4", 7345);
        registeredUsers.put("Account5", 5233);
    }

    public static void initializeClientAccounts(){
        // Iterate through the hashmap of accounts and create the client objects
        int i = 0;

        for(Map.Entry<String, Integer> entry : registeredUsers.entrySet()){
            String name = entry.getKey(); // get the account name
            int id = entry.getValue(); // get the account ID

            clientAccounts[i++] = new ClientAccount(id, name);
        }
    }

    // main driver code
    public static void driverCode(Account mainAccount) {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("-- MAIN MENU --");
            System.out.println("OPT 1 - WITHDRAW MONEY");
            System.out.println("OPT 2 - DEPOSIT MONEY");
            System.out.println("OPT 3 - TRANSFER MONEY");
            System.out.println("OPT 4 - CHECK ACCOUNT");
            System.out.print(">> ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    withdrawMoney(mainAccount);
                    break;
                case "2":
                    depositMoney(mainAccount);
                    break;
                case "3":
                    transferMoney(mainAccount);
                    break;
                case "4":
                    checkAccount(mainAccount);
                    break;
                default:
                    System.out.println("ERROR: INVALID INPUT!");
                    break;
            }
        }
    }

    public static void withdrawMoney(Account mainAccount){
        Scanner scanner = new Scanner(System.in);
        while(running){

            System.out.println("How much money would you like to withdraw? Type 'Q' to go back.");
            System.out.print(">> ");
            int userInput = scanner.nextInt();

            if(mainAccount.balance < userInput){
               System.out.println("ERROR: INSUFFICIENT FUNDS");
            }else {
                mainAccount.balance -= userInput;
                System.out.println("$" + userInput + " was withdrawn from the account.");
                driverCode(mainAccount);
            }
        }
    }

    public static void depositMoney(Account mainAccount){
        Scanner scanner = new Scanner(System.in);
        while(running){
            System.out.println("How much money would you like to deposit?");
            System.out.print(">> ");
            int userInput = scanner.nextInt();

            if(userInput > 0){
                mainAccount.balance += userInput;
                System.out.println("$" + userInput + " has been added to the account.");
                driverCode(mainAccount);
            }else{
                System.out.println("ERROR: NUMERICAL VALUES ONLY");
            }
        }
    }

    public static void transferMoney(Account mainAccount){
        Scanner scanner = new Scanner(System.in);
        boolean found = false;

        while(running){
            System.out.println("Please type the ID of the account you would like to send funds to: ");
            System.out.print(">> ");
            int userInput = scanner.nextInt();

            for(Map.Entry<String, Integer> entry : registeredUsers.entrySet()){
                int id = entry.getValue(); // check all IDs registered

                if(userInput == id){
                    found = true;
                    System.out.println("How much money would you like to transfer?");
                    System.out.print(">> ");

                    int amountToTransfer = scanner.nextInt();
                    if(mainAccount.balance < amountToTransfer){
                        System.out.println("ERROR: INSUFFICIENT FUNDS");
                    }else{
                        System.out.println("$" + amountToTransfer + " has been successfully added to the account.");
                        mainAccount.balance -= amountToTransfer;
                        driverCode(mainAccount);
                    }
                }
            }
            if(!found){
                System.out.println("ERROR: INVALID USER ID");
            }
        }
    }

    public static void checkAccount(Account mainAccount){
        Scanner scanner = new Scanner(System.in);
        while(running) {
            System.out.println("-- ACCOUNT INFORMATION --");
            System.out.println("ACCOUNT ID: " + mainAccount.accountID);
            System.out.println("ACCOUNT PASS: " + mainAccount.accountPassword);
            System.out.println("ACCOUNT BALANCE: $" + mainAccount.balance);
            System.out.println("TYPE Q TO GO BACK");
            System.out.print(">> ");
            String userInput = scanner.nextLine();

            if(userInput.equals("Q") || userInput.equals("q")){
                driverCode(mainAccount);
            }else System.out.println("Please enter a valid input!");
        }
    }
}