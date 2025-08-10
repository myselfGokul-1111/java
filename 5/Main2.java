import java.util.ArrayList;
import java.util.Scanner;
abstract class Shape
{
    public abstract double getArea();
}
class Circle extends Shape
{

double radius;
double area;

Circle(double radius)
{
this.radius=radius;
}
public double getArea()
{
area=3.14*radius*radius;
return(area);
}


}
class Rectangle extends Shape
{
    double length;
    double width;
    double area;
    Rectangle(double length,double width)
    {
        this.length = length;
        this.width  = width;
    }
    public double getArea()
    {
        area=length*width;
        return(area);
    }
}
class ShapeBox<T extends Shape>{

    double totarea=0;
    ArrayList<T> shapes = new ArrayList<>();
    void addShape(T obj)
    {
        shapes.add(obj);
    }
    double CalculateTotalArea()
    {
        for(T i:shapes)
        {
            totarea+=i.getArea();
        }
        return(totarea);

    }

}
class Main2
{
    public static void main(String[] arg)
    {
        Scanner sc = new Scanner(System.in);
        ShapeBox<Shape> shapeBox =new ShapeBox<>();

        System.out.println("Enter Radius      :");
        double radius =sc.nextDouble();
         

        shapeBox.addShape(new Circle(radius));

        System.out.println("Enter Length      :");
        double length =sc.nextDouble();
        System.out.println("Enter Width       :");
        double width  =sc.nextDouble();

        shapeBox.addShape(new Rectangle(length,width));

        double total=shapeBox.CalculateTotalArea();
        System.out.println(total);
        




    }


}
