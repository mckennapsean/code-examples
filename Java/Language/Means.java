// Copyright 2013 Sean McKenna
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//

// takes two numbers & computes the means
// means are calculated arithmetically, geometrically, & harmonically

import java.util.Scanner;

public class Means{
  
  // arithemetic mean (2 doubles), sum divided by amount of numbers
  private double arithmeticMean(double a, double b){
    return (a + b) / 2;
  }
  
  // geometric mean (2 doubles), square root of the product of the numbers
  private double geometricMean(double a, double b){
    return Math.sqrt(a * b);
  }
  
  // harmonic mean (2 doubles), total number divided by the sum of the inverses
  private double harmonicMean(double a, double b){
    return 2 / ((1 / a) + (1 / b));
  }
  
  public Means(){
    Scanner scanner = new Scanner( System.in ) ;
    System.out.println("Enter two floating point numbers whose");
    System.out.println("means you would like to compute.");
    double x = scanner.nextDouble();
    double y = scanner.nextDouble();
    
    // output based on the input given (first two numbers input)
    System.out.println("For the given set ("+x+","+y+"),");
    System.out.println("Arithemetic Mean = " + arithmeticMean(x,y));
    System.out.println("Geometric Mean = " + geometricMean(x,y));
    System.out.println("Harmonic Mean = " + harmonicMean(x,y));
  }
  
  //main run of program
  public static void main(String[] args){
    Means means = new Means() ;
  }
}
