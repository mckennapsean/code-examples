/** by Sean McKenna
 *  takes input from user to add and average two heights
 *  Heights are given in feet and then in inches.  Output gives the average
 *  of the two heights and their sum.
 *
 *  First Created: February 1st, 2010.
 *  Last Modified: February 9th, 2010.
 * 
 *  @author Sean McKenna
 *  @version 1.01
 */

import java.util.Scanner;

public class Heights {
/**
 * Main constructor, asks user for input, does math on that input, and outputs the results.
 */
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

	System.out.println("Sum of heights = "+sumFt+"' "+sumIn+"\"");
	System.out.println("Average of heights = "+averageFt+"' "+averageIn+"\"");
    }

/**
 * Averages two values.
 *  @param a Height 1 - Total Inches
 *  @param b Height 2 - Total Inches
 *  @return (a + b) / 2
 */
    public double average(double a, double b){
	return (a+b)/2;
    }

/**
 * Sums two values.
 *  @param a Height 1 - Total Inches
 *  @param b Height 2 - Total Inches
 *  @return (a + b)
 */
    public double sum(double a, double b){
	return a+b;
    }

/**
 * Given a feet and b inches, the total amount of inches is given.
 *  @param a Feet
 *  @param b Inches
 *  @return (12 * a + b)
 */
    public double totalInches(double a,double b){
	return (12*a + b);
    }

/**
 * Finds the whole number of feet based on an amount of inches.
 *  @param a Total Inches
 *  @return Integer: (a / 12)
 */
    public int inchesToFeet(double a){
	return (int) a/12;
    }

/**
 * Finds the leftover inches after divided into feet.
 *  @param a Total Inches
 *  @return (a mod 12)
 */
    public double remainingInches(double a){
	return a%12;
    }

/**
 * Main method, creates a Heights object.
 */
    public static void main(String[] args) {
	Heights heights = new Heights();
    }
}
