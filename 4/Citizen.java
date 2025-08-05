import java.util.Scanner;
class MinorCitizenException extends Exception
{

int age;
MinorCitizenException(int age)
{
this.age=age;
}
public String toString()
{
    return "MinorCitizenException : "+ age +"is less than 18 so unable to vote";
}

}
class AgeFormatInvalidException extends Exception
{
    public String tostring()
    {
        return("Age cat be negative");
    }
}
class Citizen
{

    String name;
    int age;
    String aadharnumber;
    Scanner sc =new Scanner(System.in);
    int valid=0;
    int vote=1;
    String[] database={"A123456789123456","B123456789123456","C123456789123456","D123456789123456","E123456789123456"};
    public void getInput(){
                System.out.println("Enter Name          :");
                sc.nextLine();
        String input;
         try{

                System.out.println("Enter age           :");
                input=sc.nextLine();
                age=Integer.parseInt(input);
                if(age < 18)
                {
                    if(age < 0)
                    throw new AgeFormatInvalidException();
                    else
                    throw new MinorCitizenException(age);
                }
                
            }
        catch(NumberFormatException e)
        {
            System.out.println("Enter only integer :"+e);
            vote=0;
        }
        catch(AgeFormatInvalidException e)
        {
            System.out.println(age);
            vote=0;
        }
        catch(MinorCitizenException e)
        {
            System.out.println(e);
            vote=0;
        }
        try{

            System.out.println("Enter Aadhar Number :");
                aadharnumber=sc.nextLine();
                for(String i: database)
                {
                    if(i.equalsIgnoreCase(aadharnumber))
                    {
                        valid=1;
                    }
                }
                if(valid == 0)
                {
                    throw new NullPointerException(); 
                }
        }
        catch(NullPointerException e)
        {
            System.out.println("Aadhar Number is invalid");
        }

    }
    void display()
        {
            System.out.println("Name          :"+name);
            System.out.println("age           :"+age);
            System.out.println("Aadhar Number :"+aadharnumber);
        }
    boolean canVote()
    {
        
        if(vote == 1 && valid == 1)
        {
            System.out.println("Can vote");
            return(true);
        }
        else
        {
            System.out.println("Cannot vote");
            return(false);
        }
    }

    boolean hasAadhar()
    {
        if(valid == 1)
        {
            System.out.println("has Aadhar");
            return(true);
        }
        else
        {
            System.out.println("Dont have aadhar");
            return(false);
        }
    }
   public static void main(String[] args) {
       Citizen a=new Citizen();
       a.getInput();
       a.display();
       boolean r1=a.canVote();
       boolean r2=a.hasAadhar();
   }
}
