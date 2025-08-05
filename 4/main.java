import java.util.Scanner;
class PANRequiredException extends Exception
{

            public String tostring()
            {
                    return("Deposite Exceeds Rs.25000\n");
            }


}
class InsufficientBalanceException extends Exception{
    public String tostring()
    {
        return("Insufficient Balance");
    }
}
class MinBalRequiredException extends Exception{
    public String tostring()
    {
        return("Minimum Balance required");
    }
}
class BranchNotFoundException extends Exception
{
    public String toString()
    {
        return("You cant create account in the branch that you specified ");
    }
}
class AccountNotFoundException  extends Exception
{


    public String tostring()
    {
        return("No such account Found");
    }


}
class PANFormatMismatchException extends Exception
{
    public String toString()
    {
        return("Invalid PAN Number");
    }
}
class BankAccount
{
    String Name;
    String acct_num;
    String branch;
    double balance;
    String PAN_num;

    void deposite(double amount)
    {
        balance+=amount;
        System.out.println(amount + "added ,Current balance "+balance);
    }
    void withdraw(double amount)
    {
        balance-=amount;
        System.out.println(amount + "Withdrawed ,current balance"+balance);
    }
}
class main
{
        static String[] branch={"Chennai","Delhi","Bangalore","Mumbai","Hydrabad"};
        static double  MIN_BALANCE=1000;
        static Scanner sc=new Scanner(System.in);
        static double amt=0;
        static BankAccount[] Account=new BankAccount[100];
        static int top=0;
        static int input=0;

        public static  int check()
        {
            int index=-1;
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter Account number :");
            String check=sc.nextLine();
            for(int i=0;i<top;i++)
            {
                if(Account[i].acct_num.equalsIgnoreCase(check))
                {
                    index=i;
                }
            }
            try{
                if(index == -1)
                {
                    throw new AccountNotFoundException();
                }
            }
            catch(AccountNotFoundException e)
            {
                System.out.println(e);
            }
            finally{
                return(index);
            }
            

        }
    
        
        public static void main(String[] args) {
        
        while (true){
            
    
            System.out.println("========================= MENU ===================");
            System.out.println("1. Create Account   ");
            System.out.println("2. deposite    ");
            System.out.println("3. withdraw    ");
            System.out.println("4. Exit");
            System.out.println("====================================================");
            input=sc.nextInt();
            sc.nextLine();
            if(input == 1)
            {
                int i=top;
                Account[top++]=new BankAccount();
                System.out.println("Enter Account number :");
                Account[i].acct_num=sc.nextLine();
                System.out.println("Enter name           :");
                Account[i].Name=sc.nextLine();
                System.out.println("Enter Branch         :");
                Account[i].branch=sc.nextLine();
                int present=0;
                for(String a : branch)
                {
                    if(a.equalsIgnoreCase(Account[i].branch))
                    {
                        present = 1;
                        break;
                    }

                }
                try
                {
                if(present == 0)
                {
                    throw new BranchNotFoundException();
                }
                }
                catch(BranchNotFoundException e)
                {
                    System.out.println(e);
                }


            }
            else if(input == 2)
            {
                int index=check();
                
                        if(index >= 0)
                        {

                            System.out.println("===========================================");
                            System.out.println("Account NUMBER : "+Account[index].acct_num);
                            System.out.println("NAME           : "+Account[index].Name);
                            System.out.println("===========================================");
                            
                           

                                    
                                    System.out.println("Enter Amount to deposite :");
                                    int status=1;
                                    try
                                    {
                                        amt=sc.nextDouble();
                                        sc.nextLine();
                                        if(amt > 25000)
                                        {
                                            throw new PANRequiredException();
                                        }
                                    }
                                    catch(PANRequiredException e)
                                    {
                                        System.out.println(e);
                        
                                        System.out.println("Enter PAN Number :");
                                        try{
                                               
                                                String temp=sc.nextLine();
                                                if(temp.length() != 10)
                                                {
                                                    status=0;
                                                }
                                                else{
                                                for(int i=0;i<5;i++)
                                                {
                                                        if(!Character.isLetter(temp.charAt(i)))
                                                        {
                                                                status=0;
                                                                break;
                                                        }
                                                }
                                                for(int i=5;i<9;i++)
                                                {
                                                        if(!Character.isDigit(temp.charAt(i)))
                                                        {
                                                                status=0;
                                                                break;
                                                        }
                                                }
                                                if(!Character.isLetter(temp.charAt(9)))
                                                {
                                                    status=0;

                                                }
                                                }

                                                if(status == 0)
                                                {
                                                    throw new PANFormatMismatchException();
                                                }



                                        }
                                        catch(PANFormatMismatchException d)
                                        {
                                            System.out.println(d);
                                        }
                                       
                                    
                                    }
                                    finally
                                    {
                                        if(status == 1)
                                        Account[index].deposite(amt);
                                         Account[index].PAN_num=sc.nextLine();
                                    }
                        }
            }
            else if(input == 3)
            {
                    int index = check();
                    System.out.println(index);
                    if(index >= 0)
                    {
                            System.out.println("===========================================");
                            System.out.println("Account NUMBER : "+Account[index].acct_num);
                            System.out.println("NAME           : "+Account[index].Name);
                            System.out.println("===========================================");
                            System.out.println("Enter Amount to withdraw :");

                            try
                            {

                            double withdrawamt=sc.nextDouble();
                            if(withdrawamt > Account[index].balance)
                            {
                                throw new InsufficientBalanceException();

                            }
                            Account[index].withdraw(withdrawamt);
                            if(Account[index].balance < MIN_BALANCE)
                            {
                                throw new MinBalRequiredException();
                            }
                            }
                            catch(InsufficientBalanceException e)
                            {
                                System.out.println(e);
                            }
                            catch(MinBalRequiredException e)
                            {
                                System.out.println(e);
                            }
                    }
            }
            else if(input == 4)
                 break;


                            
                        
                        
        

        

    }
}
        }
