import java.util.ArrayList;
import java.util.Scanner;           //Built-in Package
import java.io.*;

interface display
{                  //interface Implementation
    public void showAccount();
}
abstract class Account
{
    public String accountno;    //Use of AccessSpecifiers and Data members Implementation//String
    public StringBuffer name;        //StringBuffer
    public long balance;
    static long min_balance;  //static variable
    public final long max_withdrawal=5000;      //final variable

    Scanner scanner = new Scanner(System.in);
    
    Account()
    {
        this.accountno = "";            //this keyword
        this.name = new StringBuffer("New Customer");
        this.balance = 0 ;
    }
    abstract void openAccount();            //abstract keyword
    
    
}
//Inheritance and Implementation of Interface
class Customer extends Account implements display
{
    //Constructor overloading
    Customer()
    {
        super();                    //super
    }
    Customer(String accno,String cname,long bal)
    {
        accountno = accno;
        name = new StringBuffer(cname);
        balance = bal;
    }
    
    //static block
    static
    {
        min_balance = 500;
    }
    

    //To open an account      //Member Functions Implementation
    void openAccount() 
    {
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
    public  void showAccount() 
    {
        System.out.println("Account No.:"+accountno + "\nName:" + name + "\nBalance:" + balance+"\nMinimum Balance:"+ min_balance+"\n" );
    }
    //function overloading
    void showAccount(String accno) 
    {
        System.out.println("Account No.:"+accountno + "\nName:" + name + "\nBalance:" + balance+"\n");
    }
    

    //method to deposit money
    void deposit() 
    {
        long amount;
        System.out.println("Enter the Amount to deposit : ");
        amount = scanner.nextLong();
        balance = balance + amount;
    }

    //To withdraw money
    void withdrawal() 
    {
        long amount;
        System.out.println("Maximum Withdrawal Amount :"+max_withdrawal+"\nEnter Amount to withdraw : ");
        amount = scanner.nextLong();
        //Control Statements
        if (balance >= amount) 
        {                    
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
    public static class Goodday
    {
        public void message()
        {
            System.out.println("Have a Good Day");
        }
        
    }
}
class ThreadAccount 
{
	private long balance = 300;
    public long getBalance() 
    {
	    return balance;
	}
    public void withdraw(long amount) 
    {
	    balance = balance - amount;
	}
}
//package synchronization;
//Thread class and Runnable interface
public class Bank implements Runnable
{
    private ThreadAccount tacc = new ThreadAccount();
    //IOException Handling
    public static void main(String args[]) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to "+args[0]+"\n"); //Command Line Arguments
        Bank r = new Bank();
        //create initial accounts
        System.out.println("No.of customers: ");
        int n = scanner.nextInt();
        ArrayList<Customer>B=new ArrayList<Customer>();//generic
       
        Customer.Goodday bye =new Customer.Goodday();
        Customer C = new Customer();
        for (int i = 1; i < n; i++) {
            B.add(C);
            C.openAccount();
        }
        
        

        //Control Statements 
        int op;
        do 
        {
            Customer.welcome();
            System.out.println("Menu\n1. Account Details\n2. Deposit\n3.Withdrawal\n4.Joint Account\n5.Exit ");
                op = scanner.nextInt();
                String accno;
                int flag=0;
                int i;
                switch (op) 
                {
                    case 1:
                        System.out.println("Enter Account No.: ");
                        accno = scanner.next();
                        for (i = 0; i < n; i++) 
                        {
                           if(B.get(i).find(accno))
                           {
                               B.get(i).showAccount();
                               flag=1;
                           }
                        }
                        if(flag==0)
                           { 
                               System.out.println("Account Does not exist");
                            }
                        break;

                    case 2:
                         System.out.println("Enter Account No.: ");
                         accno = scanner.next();
                         for (i = 0; i < n; i++) {
                            
                           if( B.get(i).find(accno))
                           {
                               B.get(i).deposit();
                               flag=1;
                               break;
                           }
                        }
                        if(flag==1)
                            B.get(i).showAccount(accno);
                            
                        if(flag==0)
                           { System.out.println("Account Does not exist");}
                        break;

                    case 3:
                         System.out.println("Enter Account No.: ");
                        accno = scanner.next();
                        for (i = 0; i < n; i++) {
                           if(B.get(i).find(accno))
                           {
                               B.get(i).withdrawal();
                               flag=1;
                               break;
                           }
                        }
                        if(flag==1)
                            B.get(i).showAccount(accno);
                        if(flag==0)
                           { System.out.println("Account Does not exist");}
                        break;
                    case 4:
                       // Main r = new Main();
                       //Multithreading
	                	Thread one = new Thread(r);
	                   	Thread two = new Thread(r);
		                one.setName("Ranjeet");
		                two.setName("Reema");
		                one.start();
		                two.start();
		                break;

                    case 5:
                        System.out.println("Thankyou");
                        bye.message();
                        break;
                }
            }
            while (op != 5);
            
        }
        
    @Override
	public void run() {
		    
		    Scanner scanner = new Scanner(System.in);
		    //System.out.println("\nAmount to withdraw:");
		    long withdrawamt=100;
		    for(int x = 0;x<4;x++)
		    {
		    threadWithdrawal(withdrawamt);
		    
			if (tacc.getBalance() < 0) {
				System.out.println("Balance Less than Zero");
			}
			}
		
	}
	//thread synchronization
	private synchronized void threadWithdrawal(long amt) {
		if (tacc.getBalance() >= amt) {
			System.out.println("\n" + Thread.currentThread().getName() + " is  withdrawing");
			try {
				wait();                 //Thread Communication
			}
			catch (InterruptedException ex) {
			}
			tacc.withdraw(amt);
			System.out.println("\n" + Thread.currentThread().getName() + " completed withdrawal");
			notify();
		}
		else {
			System.out.println("\nInsufficient Balance for " + Thread.currentThread().getName() + " to withdraw ");
		}
	}
}