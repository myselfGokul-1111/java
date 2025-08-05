import java.util.Scanner;
class Largestword
{

String sentence;
String[] words;
public void getinput()
{
    
System.out.println(" Enter an Sentence : ");
Scanner sc=new Scanner(System.in);
sentence=sc.nextLine();
words=sentence.split(" ");
}
public void find()
{
int max=words[0].length();
int index=0;
int lenplus=0;
String large=words[0];
for(String i:words)
{
    System.out.println(i);
    if(i.length() > max)
    {

        max=i.length();
        large=i;
        index=lenplus;
    }
    lenplus+=i.length()+1;

}
System.out.println("The largest word is :" + large + "," + index + " length :" +max);
}


}
class Large{

    public static void main(String[] args){
            Largestword a=new Largestword();
            a.getinput();
            a.find();


}
}