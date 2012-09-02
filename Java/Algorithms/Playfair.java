/** by Sean McKenna on February 2nd, 2010
 *  last modified on February 3rd, 2010
 *
 * This program encodes a given text input using the Playfair cipher,
 *  and results (encode and decode) are output along with the table.
 * This uses a keyword given by user input before the message.
 * The letter to use for insertion is the letter "X."  I replaces J.
 */

import java.awt.Point;
import java.util.Scanner;

public class Playfair {
    private int length = 0;	    //Length for digraph array, declared later
    private String [][] table;	    //table for Playfair cipher

    //Main method to call the playfair method
    public static void main(String[] args) {
	Playfair pf = new Playfair();
    }

    //This method controls the main run of the program
    private Playfair(){
	//Prompts user for the keyword to use for encoding & creates tables
	System.out.println("Please input the keyword for the Playfair cipher.");
	Scanner sc = new Scanner(System.in);
	String keyword = parseString(sc);
	while(keyword.equals("")){
	    keyword = parseString(sc);
	}
	System.out.println();
	table = this.cipherTable(keyword);

	//Prompts user for the message to be encoded
	System.out.println("Please input the message to be encoded");
	System.out.println("using the previously given keyword");
	String input = parseString(sc);
	while(input.equals("")){
	    input = parseString(sc);
	}
	System.out.println();

	//Encodes and then decodes the encoded message
	String output = cipher(input);
	String decodedOutput = decode(output);

	//Output the results to user
	this.printTable(table);
	this.printResults(output,decodedOutput);
    }

    //Parses any input string to remove numbers, punctuation,
    //  replaces any J's with I's, and makes string all caps
    private String parseString(Scanner s){
	String parse = s.nextLine();
	parse = parse.toUpperCase();
	parse = parse.replaceAll("[^A-Z]","");
	parse = parse.replace("J","I");
	return parse;
    }

    //This creates the cipher table based on some input string (already parsed)
    private String[][] cipherTable(String key){
	String[][] playfairTable = new String[5][5];
	String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	for(int i=0;i<5;i++){
	    for(int j=0;j<5;j++){
		playfairTable[i][j] = "";   //fill string array with empty string
	    }
	}
	for(int k=0;k<keyString.length();k++){
	    boolean repeat = false;
	    boolean used = false;
	    for(int i=0;i<5;i++){
		for(int j=0;j<5;j++){
		    if(playfairTable[i][j].equals(""+keyString.charAt(k))){
			repeat = true;
		    }
		    else if(playfairTable[i][j].equals("") && !repeat && !used){
			playfairTable[i][j] = "" + keyString.charAt(k);
			used = true;
		    }
		}
	    }
	}
	return playfairTable;
    }

    //Cipher: takes input (all upper-case), encodes it, and returns output
    private String cipher(String in){
	length = (int) in.length()/2 + in.length() % 2;
	
	//insert x between double-letter digraphs & redefines "length"
	for(int i=0;i<length-1;i++){
	    if(in.charAt(2*i)==in.charAt(2*i+1)){
		in = new StringBuffer(in).insert(2*i+1,'X').toString();
		length = (int) in.length()/2 + in.length() % 2;
	    }
	}

	//Adds an x to the last digraph if necessary
	String [] digraph = new String[length];
	for(int j=0;j<length;j++){
	    if(j==length-1 && in.length()/2==length-1){
		in = in + "X";
	    }
	    digraph[j] = in.charAt(2*j) +""+ in.charAt(2*j+1);
	}

	//Encodes the digraphs and returns the output back to the main program
	String out = "";
	String [] encDigraphs = new String [length];
	encDigraphs = encodeDigraph(digraph);
	for(int k=0;k<length;k++){
	    out = out + encDigraphs[k];
	}
	return out;
    }

    //Encodes the digraph input with the cipher's specifications
    private String [] encodeDigraph(String di []){
	String [] enc = new String [length];
	for(int i=0;i<length;i++){
	    char a = di[i].charAt(0);
	    char b = di[i].charAt(1);
	    int r1 = (int) getPoint(a).getX();
	    int r2 = (int) getPoint(b).getX();
	    int c1 = (int) getPoint(a).getY();
	    int c2 = (int) getPoint(b).getY();

	    //case 1: letters in digraph are of same row, shift columns to right
	    if(r1==r2){
		c1 = (c1 + 1) % 5;
		c2 = (c2 + 1) % 5;
	    }

	    //case 2: letters in digraph are of same column, shift rows down
	    else if(c1==c2){
		r1 = (r1 + 1) % 5;
		r2 = (r2 + 1) % 5;
	    }

	    //case 3: letters in digraph form rectangle, swap first column # with second column #
	    else{
		int temp = c1;
		c1 = c2;
		c2 = temp;
	    }

	    //performs the table look-up and puts those values into the encoded array
	    enc[i] = table[r1][c1] +""+ table[r2][c2];
	}
	return enc;
    }

    //This code decodes the output given from the cipher and decode methods (opp. of encoding process)
    private String decode(String out){
	String decoded = "";
	for(int i=0;i<out.length()/2;i++){
	    char a = out.charAt(2*i);
	    char b = out.charAt(2*i+1);
	    int r1 = (int) getPoint(a).getX();
	    int r2 = (int) getPoint(b).getX();
	    int c1 = (int) getPoint(a).getY();
	    int c2 = (int) getPoint(b).getY();
	    if(r1==r2){
		c1 = (c1 + 4) % 5;
		c2 = (c2 + 4) % 5;
	    }
	    else if(c1==c2){
		r1 = (r1 + 4) % 5;
		r2 = (r2 + 4) % 5;
	    }
	    else{
		int temp = c1;
		c1 = c2;
		c2 = temp;
	    }
	    decoded = decoded + table[r1][c1] + table[r2][c2];
	}
	return decoded;
    }

    //This returns a point containing the row and column of the letter
    private Point getPoint(char c){
	Point pt = new Point(0,0);
	for(int i=0;i<5;i++){
	    for(int j=0;j<5;j++){
		if(c==table[i][j].charAt(0)){
		    pt = new Point(i,j);
		}
	    }
	}
	return pt;
    }

    //Method prints the cipher table out for user
    private void printTable(String[][] printedTable){
	System.out.println("This is the cipher table from the given keyword.");
	System.out.println();
	for(int i=0;i<5;i++){
	    for(int j=0;j<5;j++){
		System.out.print(printedTable[i][j]+" ");
	    }
	    System.out.println();
	}
	System.out.println();
    }

    //Prints results (both encoded and decoded) to user
    private void printResults(String enc, String dec){
	System.out.println("This is the encoded message:");
	System.out.println(enc);
	System.out.println();
	System.out.println("This is the decoded message:");
	System.out.println(dec);
    }
}
