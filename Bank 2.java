import java.util.Scanner;

class Customer{
    public String accountno;    //Use of AccessSpecifiers and Data members Implementation//String
    private StringBuffer name;        //StringBuffer
    private long balance;
    static long min_balance;  //static variable

    Scanner scanner = new Scanner(System.in);
    //Constructor overloading
    Customer(){
        this.accountno = "";            //this keyword
        this.name = new StringBuffer("New Customer");
        this.balance = 0 ;
    }
    Customer(String accno,String cname,long bal){
        accountno = accno;
        name = new StringBuffer(cname);
        balance = bal;
    }
    
    //static block
    static{
        min_balance = 500;
    }

    //To open an account      //Member Functions Implementation
    void openAccount() {
        System.out.print("Enter Account No: ");
        accountno = scanner.nextLine();
        if(!accountno.chars().allMatch(Character::isDigit))
        {
           System.out.print("AccountNo Invalid");
           System.exit(1);
        }
        System.out.print("Enter Name: ");
        name = new StringBuffer(scanner.nextLine());
        System.out.print("Enter Balance: ");
        balance = scanner.nextLong();
    }

    //To display account details
     void showAccount() {
        System.out.println("Account No.:"+accountno + "\nName:" + name + "\nBalance:" + balance+"\nMinimum Balance:"+ min_balance+"\n" );
    }
    //function overloading
    void showAccount(String accno) {
        System.out.println("Account No.:"+accountno + "\nName:" + name + "\nBalance:" + balance+"\n");
    }
    

    //method to deposit money
    void deposit() {
        long amount;
        System.out.println("Enter the Amount to deposit : ");
        amount = scanner.nextLong();
        balance = balance + amount;
    }

    //To withdraw money
    void withdrawal() {
        long amount;
        System.out.println("Enter Amount to withdraw : ");
        amount = scanner.nextLong();
        //Control Statements
        if (balance >= amount) {                    
            balance = balance - amount;
        }
        else {
            System.out.println("Insufficient Balance");
        }
    }
    
    boolean find(String accno)
    {
        if(accountno.equals(accno))
        {
            return (true);
        }
        return (false);
    }
    
    //static function
    static void welcome()
    {
        System.out.println("Welcome to XYZ Bank");
    }
    //static class
    public static class Goodday{
        public void message()
        {
            System.out.println("Have a Good Day");
        }
        
    }
}
public class Bank
{
    public static void main(String arg[]) {
        Scanner scanner = new Scanner(System.in);

        //create initial accounts
        System.out.println("No.of customers: ");
        int n = scanner.nextInt();
        Customer B[] = new Customer[n];  //Object Array
        Customer.Goodday bye =new Customer.Goodday();
        B[0] = new Customer("1000","Default", 10000);
        for (int i = 1; i < B.length; i++) {
            B[i] = new Customer();
            B[i].openAccount();
        }
        
        

        //Control Statements 
        int op;
        do {
            Customer.welcome();
            System.out.println("Menu\n1. Account Details\n2. Deposit\n3. Withdrawal\n4. Exit ");
                op = scanner.nextInt();
                String accno;
                int flag=0;
                int i;
                switch (op) {
                    case 1:
                        System.out.println("Enter Account No.: ");
                        accno = scanner.next();
                        for (i = 0; i < B.length; i++) {
                           if(B[i].find(accno))
                           {
                               B[i].showAccount();
                               flag=1;
                           }
                        }
                        if(flag==0)
                           { System.out.println("Account Does not exist");}
                        break;

                    case 2:
                         System.out.println("Enter Account No.: ");
                        accno = scanner.next();
                        for (i = 0; i < B.length; i++) {
                            
                           if( B[i].find(accno))
                           {
                               B[i].deposit();
                               flag=1;
                               break;
                           }
                        }
                        if(flag==1)
                            B[i].showAccount(accno);
                            
                        if(flag==0)
                           { System.out.println("Account Does not exist");}
                        break;

                    case 3:
                         System.out.println("Enter Account No.: ");
                        accno = scanner.next();
                        for (i = 0; i < B.length; i++) {
                           if(B[i].find(accno))
                           {
                               B[i].withdrawal();
                               flag=1;
                               break;
                           }
                        }
                        if(flag==1)
                            B[i].showAccount(accno);
                        if(flag==0)
                           { System.out.println("Account Does not exist");}
                        break;

                    case 4:
                        System.out.println("Thankyou");
                        bye.message();
                        break;
                }
            }
            while (op != 4);
        }
}

