import java.util.Scanner;

class Customer{
    public String accountno;    //Use of AccessSpecifiers and Data members Implementation
    private String name;
    private long balance;

    Scanner scanner = new Scanner(System.in);

    //To open an account      //Member Functions Implementation
    void openAccount() {
        System.out.print("Enter Account No: ");
        accountno = scanner.next();
        System.out.print("Enter Name: ");
        name = scanner.next();
        System.out.print("Enter Balance: ");
        balance = scanner.nextLong();
    }

    //To display account details
    void showAccount() {
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
}
public class Bank
{
    public static void main(String arg[]) {
        Scanner scanner = new Scanner(System.in);

        //To create account
        System.out.println("No.of customers: ");
        int n = scanner.nextInt();
        Customer B[] = new Customer[n];  //Object Array
    
        for (int i = 0; i < B.length; i++) {
            B[i] = new Customer();
            B[i].openAccount();
        }

        //Control Statements 
        int op;
        do {
            System.out.println("Menu\n1. Account Details\n2. Deposit\n3.Withdrawal\n4.Exit ");
                op = scanner.nextInt();
                String accno;
                int flag=0;
                switch (op) {
                    case 1:
                        System.out.println("Enter Account No.: ");
                        accno = scanner.next();
                        for (int i = 0; i < B.length; i++) {
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
                        for (int i = 0; i < B.length; i++) {
                            
                           if( B[i].find(accno))
                           {
                               B[i].deposit();
                               flag=1;
                           }
                        }
                        if(flag==0)
                           { System.out.println("Account Does not exist");}
                        break;

                    case 3:
                         System.out.println("Enter Account No.: ");
                        accno = scanner.next();
                        for (int i = 0; i < B.length; i++) {
                           if(B[i].find(accno))
                           {
                               B[i].withdrawal();
                               flag=1;
                           }
                        }
                        if(flag==0)
                           { System.out.println("Account Does not exist");}
                        break;

            

                    case 4:
                        System.out.println("Thankyou");
                        break;
                }
            }
            while (op != 4);
        }
}
