import java.util.Scanner;
class data{
    int value;
    int index;
    data(int value,int index)
    {
            this.index=index;
            this.value=value;

    }
}
class Search1
{
public int[] elements;
int n;
Scanner sc = new Scanner(System.in);
public void getelements()
{

System.out.println("Enter number of elements : ");

n=sc.nextInt();
elements = new int[n];
for(int i=0;i<n;i++)
{
System.out.println("Enter element :" + (i+1));
elements[i]=sc.nextInt();

}


}
public int linear()
{

int s;
boolean status =false;
System.out.println(" Enter an element to search ");
s=sc.nextInt();
for(int i=0;i<n;i++)
{
if(elements[i]==s)
{
System.out.println("The element found in the index :"+ i);
status = true;
return(i);
}

}
if(!status)
System.out.println("No such element found");
return(-1);


}
public data[] mergesort(data[] array)
{
if(array.length <= 1)
{
    return(array);
}
int n=array.length;
int mid=n/2;

data[] L=new data[mid];
data[] R=new data[n-mid];

for(int i=0;i<mid;i++)
{

    L[i]=array[i];
}
for(int j=0;j<(n-mid);j++)
{

    R[j]=array[mid+j];

}

data[] left=mergesort(L);
data[] right=mergesort(R);

return(merge(left,right));


}

public data[] merge(data[] L,data[] R)
{

int i=0;
int j=0;
int k=0;
int n=L.length+R.length;
data[] result=new data[n];

while(i<L.length && j<R.length)
{

if(L[i].value < R[j].value)
{


  result[k++]=L[i++];
}
else
{

  result[k++]=R[j++];

}

}

while(i<L.length)
{
    result[k++]=L[i++];

}

while(j<R.length)
{
    result[k++]=R[j++];
}
return(result);
}
int count=0;
data[] arr;
data[] sortedelements;
public int binarysearch()
{



if(count++ == 0)
{
arr=new data[n];
sortedelements=new data[n];
for(int i=0;i<n;i++)
{
arr[i]=new data(elements[i],i);

}
sortedelements=mergesort(arr);
}
int s;
boolean status=false;
System.out.println("Enter an element to search");
s=sc.nextInt();
int low =0;
int high =elements.length-1;
int mid;
while(low <= high)
{
mid = low + (high - low) / 2;
if(sortedelements[mid].value == s)
{
System.out.println("The element found in the index:" + sortedelements[mid].index);
return(mid);
}
else if(s < sortedelements[mid].value)
{
high=mid-1;
}
else if(s > sortedelements[mid].value)
{
low = mid +1;
}

}
System.out.println("No such element found");
return(-1);
}
}
class Search
{
public static void main(String[] args)
{
int index;
Scanner sc = new Scanner(System.in);
Search1 s=new Search1();
s.getelements();
while(true)
{
System.out.println("------------------- MENU ------------------");
System.out.println("1. PRESS 1 : LINEAR SEARCH");
System.out.println("2. PRESS 2 : BINARY SEARCH");
System.out.println("3. PRESS 3 : EXIT         ");
System.out.println("-------------------------------------------");
int res =sc.nextInt();
if(res == 1)
index=s.linear();
else if(res == 2)
index=s.binarysearch();

else if(res == 3)
break;
else
System.out.println("INVALID INPUT");



}


}






}

