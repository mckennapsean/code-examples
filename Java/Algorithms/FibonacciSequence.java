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

// outputs the Fibonacci values at the input's index values (index 0 = index 1 = 1)

// imports for the Fibonacci sequence
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciSequence{
  
  // variables for outputting the Fibonacci sequence
  public BigInteger[] sequence;
  public int stoppingPoint = 0;
  public ArrayList<Integer> input;
  public static final String inputFile = "FibonacciSequence.in";
  
  // main method to run test sequence
  public static void main(String[] args) throws IOException{
    FibonacciSequence fs = new FibonacciSequence(inputFile);
  }
  
  // object that outputs a Fibonacci sequence
  public FibonacciSequence(String fileInput) throws IOException{
    
    // need to input a file to generate the sequence values at certain indices
    Scanner inputStream = null;
    PrintWriter outputStream = null;
    try{
      
      // input the file to know where to get values in the sequence
      inputStream = new Scanner(new BufferedReader(new FileReader(fileInput)));
      outputStream = new PrintWriter(new FileWriter("fib.out"));
      input = new ArrayList();
      while(inputStream.hasNextInt()){
        input.add(inputStream.nextInt());
      }
      
      // create the sequence only as far as needed
      findIndexMaximum();
      createSequence();
      
      // output the values of the Fibonacci sequence
      for(int i : input){
        outputStream.println(sequence[i]);
      }
    
    // after processing the sequence values and outputting, close streams
    }finally{
      if(inputStream != null)
        inputStream.close();
      if(outputStream != null)
        outputStream.close();
    }
  }
  
  // find the maximum value for the Fibonacci sequence
  public void findIndexMaximum(){
    for(int i = 0; i < input.size(); i++){
      if(stoppingPoint < input.get(i))
        stoppingPoint = input.get(i);
    }
  }
  
  // generate the Fibonacci sequence up to some value
  public void createSequence(){
    sequence = new BigInteger[stoppingPoint + 1];
    sequence[0] = BigInteger.ONE;
    sequence[1] = BigInteger.ONE;
    for(int i = 2; i <= stoppingPoint; i++){
      sequence[i] = sequence[i-1].add(sequence[i-2]);
    }
  }
}
