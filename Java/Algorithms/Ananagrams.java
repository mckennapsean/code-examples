// by Sean McKenna
// original solution by Adam Reed & Sean McKenna on February 18th, 2010
// addresses an ACM competition problem, called Ananagrams

// imports for text processing
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// main program
public class Ananagrams{
  
  // initial variables for processing ananagrams
  private static final String NUM_SYMBOL = "#";
  private static final String in = "Ananagrams.in";
  private static final String out = "Ananagrams.out";
  private int maxWordLength = 0;
  private ArrayList<String> dictionary = new ArrayList();
  private ArrayList<Integer> ananagrams = new ArrayList();
  private ArrayList<char[]> characters = new ArrayList();
  private Scanner scan;
  private PrintWriter print;
  
  // read in the input file to a dictionary
  // find maximum word length & create initial ananagram integer list
  public Ananagrams() throws FileNotFoundException, IOException{
    
    // input file, need to catch for errors!
    try{
      scan = new Scanner(new BufferedReader(new FileReader(in)));
      print = new PrintWriter(new FileWriter(out));
      String temp = "";
      while(!(temp = scan.next()).equals(NUM_SYMBOL)){
        if(temp.length()>maxWordLength)
          maxWordLength = temp.length();
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
        output[i] = dictionary.get(word);
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
      if(scan != null)
        scan.close();
      if(print != null)
        print.close();
    }
  }
  
  // main method, create and start Ananagrams test
  public static void main(String[] args) throws FileNotFoundException, IOException{
    Ananagrams ananagrams = new Ananagrams();
  }
  
  // how to compare ananagrams appropriately
  public void compare(){
    
    // go through the character array, based on the word length
    for(int i = 2; i <= maxWordLength; i++){
      //arrays for each word length, unsorted and their indices
      ArrayList<char[]> comparableWords = new ArrayList();
      ArrayList<char[]> sortedWords = new ArrayList();
      ArrayList<Integer> wordIndexes = new ArrayList();
      for(char[] characterArray : characters){
        if(characterArray.length == i){
          comparableWords.add(characterArray);
          wordIndexes.add(characters.indexOf(characterArray));
        }
      }
      
      // sort the unsorted array liste into a sorted one
      for(char[] element : comparableWords){
        Arrays.sort(element);
        sortedWords.add(element);
      }
      
      //step through each sorted character array, compare characters
      //ignore comparing same characters and remove indices of ananagrams
      //from the main array list (ananagrams), if they still exist
      for(int q = 0; q < sortedWords.size(); q++){
        char[] wordone = sortedWords.get(q);
        for(int r = 1; r < sortedWords.size(); r++){
          char[] wordtwo = sortedWords.get(r);
          if(q==r){
            // do nothing if the same
          }else if(compareArrays(wordone, wordtwo)){
            if(ananagrams.contains(wordIndexes.get(q)))
              ananagrams.remove(wordIndexes.get(q));
            if(ananagrams.contains(wordIndexes.get(r)))
              ananagrams.remove(wordIndexes.get(r));
          }
        }
      }
    }
  }
  
  // compare two arrays for the ananagram comparison
  public Boolean compareArrays(char[] one, char[] two){
    for(int i = 0; i < one.length; i++){
      char charOne = one[i];
      char charTwo = two[i];
      if(charOne!=charTwo)
        return false;
    }
    return true;
  }
}
