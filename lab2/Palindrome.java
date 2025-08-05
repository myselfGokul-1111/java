import java.util.Scanner;
class Palindromecheck
{
    String s;
    String reversed;
    public void getinput()
    {
    System.out.println("Enter a string:");
    Scanner sc = new Scanner(System.in);
    s=sc.nextLine();
    reversed=new StringBuilder(s).reverse().toString();
    }
    public boolean check()
    {

    return(reversed.equalsIgnoreCase(s));
    }


}
class Palindrome{
    public static void main(String[] args){

        Palindromecheck a=new Palindromecheck();
        a.getinput();
        boolean result=a.check();
        if(result)
        {
            System.out.println("It is a palindrome");
        }
        else
        {

            System.out.println("It is not a palindrome");
        }
    }
}

