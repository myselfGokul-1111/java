import java.util.ArrayList;
class Genericstack<T>
{

ArrayList<T> stack;
int top;

Genericstack()
{
stack=new ArrayList<>();
top=-1;
}
void push(T element)
{
    stack.add(element);
    top++;
}

boolean isempty()
{

if(top == -1)
{
System.out.println("The stack is empty");
return true;
}
else
return false;


}
T peek()
{
if(isempty())
{
    return null;
}
else{
    return(stack.get(stack.size()-1));
}

}
T pop()
{
if(peek() == null)
{
 
 return null;

}
else{
    T togive=peek();
    stack.remove(stack.size()-1);
    top--;
    return(togive);
    }
}
int size()
{
    return(stack.size());
}

}
class Student
{
 int studentid;
 double[] marks=new double[3];
 Student(int studentid,double[] marks)
 {
        this.studentid=studentid;
        this.marks=marks;

 }
 public String toString()
 {
    return ("Student id :"+studentid);
 }
 double[] getmarks()
 {
    return marks;
 }


}
class Main
{
    public static void main(String[] args)
    {
        double[] marks={99,98,100};
        Student a=new Student(1004,marks);
        Genericstack<Integer> intstack = new Genericstack<>();
        Genericstack<String> strstack =new Genericstack<>();
        Genericstack<Student> objstack=new Genericstack<>();

        intstack.push(5);
        Integer result=intstack.pop();
        System.out.println(result);
        result=intstack.pop();
        System.out.println(result);

        strstack.push("Gokul");
        String sresult=strstack.pop();
        System.out.println(sresult);
        sresult=strstack.pop();
        System.out.println(result);

        objstack.push(a);
        Student oresult=objstack.pop();
        System.out.println(oresult);
        oresult=objstack.pop();
        System.out.println(result);


        
        

    }


}
