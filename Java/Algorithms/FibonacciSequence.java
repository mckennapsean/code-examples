// by Sean McKenna on February 17th, 2010
// outputs the Fibonacci values at the input's index values (index 0 = index 1 = 1)

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciSequence {
    public BigInteger[] sequence;
    public int stoppingPoint = 0;
    public ArrayList<Integer> input;
    public static final String inputFile = "fib.in";

    public static void main(String[] args) throws IOException{
	FibonacciSequence fs = new FibonacciSequence(inputFile);
    }

    public FibonacciSequence(String fileInput) throws IOException{
	Scanner inputStream = null;
	PrintWriter outputStream = null;

	try{
	    inputStream = new Scanner(new BufferedReader(new FileReader(fileInput)));
	    outputStream = new PrintWriter(new FileWriter("fib.out"));
	    input = new ArrayList();
	    while(inputStream.hasNextInt()){
		input.add(inputStream.nextInt());
	    }

	    findIndexMaximum();
	    createSequence();

	    for(int i:input){
		outputStream.println(sequence[i]);
	    }
	}finally{
	    if(inputStream != null){
		inputStream.close();
	    }
	    if(outputStream != null){
		outputStream.close();
	    }
	}
    }

    public void findIndexMaximum(){
	for(int i=0;i<input.size();i++){
	    if(stoppingPoint < input.get(i)){
		stoppingPoint = input.get(i);
	    }
	}
    }

    public void createSequence(){
	sequence = new BigInteger[stoppingPoint+1];
	sequence[0] = BigInteger.ONE;
	sequence[1] = BigInteger.ONE;
	for(int i=2;i<=stoppingPoint;i++){
	    sequence[i] = sequence[i-1].add(sequence[i-2]);
	}
    }
}
