// by Sean McKenna on February 9th, 2010
// takes input form user to add & average two heights

import java.util.Scanner;

public class Heights{
  
  // main constructor, with user input & math on that input, outputting results
  public Heights(){
    System.out.println("Please type two heights using floating");
    System.out.println("point numbers, the first height in feet");
    System.out.println("and then in inches, then the last height.");
    Scanner sc = new Scanner(System.in);
    double ft1 = sc.nextDouble();
    double in1 = sc.nextDouble();
    double ft2 = sc.nextDouble();
    double in2 = sc.nextDouble();
    double totalIn1 = totalInches(ft1,in1);
    double totalIn2 = totalInches(ft2,in2);
    
    double average = average(totalIn1,totalIn2);
    int averageFt = inchesToFeet(average);
    double averageIn = remainingInches(average);
    
    double sum = sum(totalIn1,totalIn2);
    int sumFt = inchesToFeet(sum);
    double sumIn = remainingInches(sum);
    
    System.out.println("Sum of heights = " + sumFt + "' " + sumIn + "\"");
    System.out.println("Average of heights = " + averageFt + "' " + averageIn + "\"");
  }
  
  // averages two values
  public double average(double a, double b){
    return (a + b) / 2;
  }
  
  // sums two values
  public double sum(double a, double b){
    return a + b;
  }
  
  // given a feet & b inches, the total amount of inches is output
  public double totalInches(double a,double b){
    return (12 * a + b);
  }
  
  // given the total number of inches, the total feet is output
  public int inchesToFeet(double a){
    return (int) a / 12;
  }
  
  // find leftover inches after divided into feet
  public double remainingInches(double a){
    return a % 12;
  }
  
  // create a Height object
  public static void main(String[] args){
    Heights heights = new Heights();
  }
}
