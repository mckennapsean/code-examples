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

// takes basic input (integers, line-by-line) to output sums
// input file ends with a 0, the sum is produced for each integer
// that is, for a given integer N, compute the sum of integers between 1 and N, inclusive

// necessary to input and output a file
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Locale;
import java.io.IOException;

// run the program with I/O exceptions
public class inputOutput{
  public static void main(String [] args) throws IOException{
  	
  	// create reader & writer
    Scanner inputStream = null;
    PrintWriter outputStream = null;
    try{
      inputStream = new Scanner(new BufferedReader(new FileReader("inputOutput.in")));
      outputStream = new PrintWriter(new FileWriter("inputOutput.out"));
      
      // create integers and sum variables
      int I;
      int Sum = 0;
      
      // for each integer, compute the sum (until a zero is hit)
      while((I = inputStream.nextInt()) != 0){
        if(I > 0){
          for(int i = 1; i <= I; i++){
            Sum = Sum + i;
          }
        }else if (I < 0){
          for(int j = 1; j >= I; j--){
            Sum = Sum + j;
          }
        
        // if no integer is found
        }else{
          System.out.println("Error, integer not found.");
        }
        
        // otherwise, print out the sum for each integer
        outputStream.println("N = "+ I + "      Sum = " + Sum);
        
        // reset sum before getting a new integer
        Sum = 0;
      }
    
    // close all files
    }finally{
      if(inputStream != null)
        inputStream.close();
      if(outputStream != null)
        outputStream.close();
    }
  } 
}
