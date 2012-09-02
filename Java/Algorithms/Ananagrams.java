// by Adam Reed and Sean McKenna on February 18th, 2010
// Last Modified: February 21st, 2010 by Sean: cleanup of code and comments

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ananagrams{

    private static final String NUM_SYMBOL = "#";
    private static final String in = "ananagramsIn.txt";
    private static final String out = "ananagramsOut.txt";
    private int maxWordLength = 0;
    private ArrayList<String> dictionary = new ArrayList();
    private ArrayList<Integer> ananagrams = new ArrayList();
    private ArrayList<char[]> characters = new ArrayList();
    private Scanner scan;
    private PrintWriter print;

    public Ananagrams() throws FileNotFoundException,IOException{
	//read in file to the dictionary array list, finds maximum word
	//length, and creates the initial state of the ananagram integer list
	try{
	    scan = new Scanner(new BufferedReader(new FileReader(in)));
            print = new PrintWriter(new FileWriter(out));
            String temp = "";
            while(!(temp = scan.next()).equals(NUM_SYMBOL)){
                if(temp.length()>maxWordLength){
                    maxWordLength = temp.length();
                }
                dictionary.add(temp);
                ananagrams.add(dictionary.indexOf(temp));
            }

	    //creates array list of char arrays and the compare method is called
            for(String addWord : dictionary){
                char[] character = addWord.toLowerCase().toCharArray();
	        characters.add(character);
	    }
            compare();

	    //uses a string array to order the output and prints it to a file
	    String[] output = new String[ananagrams.size()];
	    int i = 0;
            for(int word : ananagrams){
		output[i]=dictionary.get(word);
		i++;
	    }
	    Arrays.sort(output);
	    for(String s : output){
		print.println(s);
	    }

	//catch for no file found and then closes streams to reserve resources
        }catch(FileNotFoundException f){
            System.out.println("File not found.");
        }finally{
            if(scan != null){
                scan.close();
            }
            if(print != null){
                print.close();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException,IOException{
        Ananagrams ananagrams = new Ananagrams();
    }

    public void compare(){
        //Going through character array based on word length
	for(int i = 2; i<=maxWordLength; i++){
        
	    //arrays for each word length, unsorted and their indices
	    ArrayList<char[]> comparableWords = new ArrayList();
            ArrayList<char[]> sortedWords = new ArrayList();
            ArrayList<Integer> wordIndexes = new ArrayList();
            for(char[] characterArray : characters){
                if (characterArray.length == i){
                    comparableWords.add(characterArray);
                    wordIndexes.add(characters.indexOf(characterArray));
                }
            }

	    //sorting of the unsorted array list into a sorted array list
            for(char[] element : comparableWords){
		Arrays.sort(element);
		sortedWords.add(element);
            }

	    //step through each sorted character array, compare characters,
	    //ignore comparing same characters and remove indexes of ananagrams
	    //from the main array list (ananagrams) if they still exist
            for(int q = 0; q<sortedWords.size(); q++){
                char[] wordone = sortedWords.get(q);
                for(int r = 1; r<sortedWords.size(); r++){
                    char[] wordtwo = sortedWords.get(r);
                    if(q==r){
                    }else if(compareArrays(wordone, wordtwo)){
                        if(ananagrams.contains(wordIndexes.get(q))){
                            ananagrams.remove(wordIndexes.get(q));
                        }if(ananagrams.contains(wordIndexes.get(r))){
                            ananagrams.remove(wordIndexes.get(r));
                        }
                    }
                }
            }
        }
    }

    public Boolean compareArrays(char[] one, char[] two){
        for (int i = 0; i<one.length; i++){
            char charOne = one[i];
            char charTwo = two[i];
            if(charOne!=charTwo){
                return false;
            }
        }
        return true;
    }
}
