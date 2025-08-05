import java.util.Arrays;
import java.util.Scanner;

class Matrixcreate
{
int rows;
int cols;
Scanner sc = new Scanner(System.in);
int[][] mat;
public void create()
{

System.out.println("Enter number of rows :");
rows=sc.nextInt();
System.out.println("Enter number of columns :");
cols=sc.nextInt();
mat=new int[rows][cols];
for(int i=0;i<rows;i++)
{
for(int j=0;j<cols;j++)
{

    System.out.println("Enter Element row[" + i + "] column[" + j + "] :");
    mat[i][j]=sc.nextInt();
}


}

System.out.println(Arrays.deepToString(mat));

}
}
class Matrixoperation
{
public int[][] Addition(int[][] a,int[][] b)
{
int row1len =a.length;
int column1len=a[0].length;

int row2len=b.length;
int column2len=b[0].length;

if(row1len != row2len || column1len != column2len)
{

System.out.println("The order of the matrix is different");
return(null);

}
int[][] c =new int[row1len][column1len];
for(int i=0;i<row1len;i++)
{

    for(int j=0;j<column1len;j++)
    {

        c[i][j] = a[i][j] + b[i][j];
    }
}
return(c);


}

public int[][] subtraction(int[][] a,int[][] b)
{
int row1len =a.length;
int column1len=a[0].length;

int row2len=b.length;
int column2len=b[0].length;

if(row1len != row2len || column1len != column2len)
{

System.out.println("The order of the matrix is different");
return(null);

}
int[][] c =new int[row1len][column1len];
for(int i=0;i<row1len;i++)
{

    for(int j=0;j<column1len;j++)
    {

        c[i][j] = a[i][j] - b[i][j];
    }
}
return(c);


}

public int[][] multiplication(int[][] a,int[][] b)
{
int row1len =a.length;
int column1len=a[0].length;

int row2len=b.length;
int column2len=b[0].length;

if(column1len != row2len )
{

System.out.println("The order of the matrix is different");
return(null);

}
int[][] c =new int[row1len][column1len];
for(int i=0;i<row1len;i++)
{

    for(int j=0;j<column2len;j++)
    {

        for(int k=0;k<column1len;k++)
        {
        c[i][j] += a[i][k]  * b[k][j];
        }
    }
}
return(c);


}



}


