import java.util.Scanner;
class Subpangram{

    Scanner sc=new Scanner(System.in);
    String sentence;
    public void check()
    {

        System.out.println("Enter an sentence :");
        sentence=sc.nextLine().toLowerCase();
        int[] lettercount=new int[26];
        for(char i: sentence.toCharArray())
        {

            if(i >='a' && i<= 'z')
            {
                lettercount[i-'a']++;
            }
        }
        boolean status=true;
        for(int i=0;i<26;i++)
        {
            if(lettercount[i] == 0)
            {
                status=false;
                break;
            }
        }
        if(status)
        {
            System.out.println("The given string is a panagram");
            for(int i=0;i<26;i++)
            {
                    System.out.println((i+'a') + " : " +lettercount[i]);
            }
        }
        else{

            System.out.println("The given String is not a panagram");
        }



    }
}
class Pangram{

    public static void main(String[] args)
    {

        Subpangram s=new Subpangram();
        s.check();
    }
}

