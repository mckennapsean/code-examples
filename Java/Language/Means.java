/*  by Sean McKenna on February 1st, 2010
    This file was copied from a template.
    It takes the input of two numbers and computes the means.
    Means are computed arithemetically, geometrically, and harmonically.
*/

import java.util.Scanner;

public class Means {
    // arithemetic mean (2 doubles), sum divided by amount of numbers
    private double arithmeticMean(double a,double b){
        return (a+b)/2;
    }

    // geometric mean (2 doubles), square root of the product of the numbers
    private double geometricMean(double a,double b){
	return Math.sqrt(a*b);
    }

    //harmonic mean (2 doubles), total number divided by the sum of the inverses
    private double harmonicMean(double a, double b){
	return 2/((1/a)+(1/b));
    }

    public Means() {
        Scanner scanner = new Scanner( System.in ) ;
        System.out.println( "Enter two floating point numbers whose" );
        System.out.println( "means you would like to compute." ) ;
        double x = scanner.nextDouble() ;
        double y = scanner.nextDouble() ;

	//Output based on the input given (first two numbers input)
        System.out.println("For the given set ("+x+","+y+"),");
	System.out.println("Arithemetic Mean = " + arithmeticMean(x,y));
	System.out.println("Geometric Mean = " + geometricMean(x,y));
	System.out.println("Harmonic Mean = " + harmonicMean(x,y));
    }

    //main run of program
    public static void main(String[] args) {
        Means means = new Means() ;
    }
}
