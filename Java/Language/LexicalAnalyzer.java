// by Sean McKenna
// lexical analyzer that breaks apart interesting characters in code

import java.util.*;
import java.util.regex.*;

public class LexicalAnalyzer{
  
  // enum for the tokenizers
  public enum Tokens{
    INTEGER_LITERAL,
    FLOATING_LITERAL,
    IDENTIFIER,
    ASSIGNMENT_OPERATOR,
    ADDITION_OPERATOR,
    SUBTRACTION_OPERATOR,
    MULTIPLICATION_OPERATOR,
    DIVISION_OPERATOR,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    COMMENT,
    UNRECOGNIZED
  };
  
  // main method - lexical analyzer
  public static void main(String[] args){
    
    // debug for tokenizers
    //for(Tokens t: Tokens.values())
    //  System.out.println(t);
    
    // set up regex for all tokens in language
    String commRegex = "[//][\\p{Print}&&[^\n]]*";
    String identRegex = "[a-zA-Z][a-zA-Z0-9]*";
    String intRegex = "[\\-\\+]?[0-9]+";
    String floRegex = "[0-9]*[.][0-9]+";
    String assignRegex = "=";
    String addRegex = "\\+";
    String subRegex = "\\-";
    String mulRegex = "\\*";
    String divRegex = "/";
    String lPaRegex = "\\(";
    String rPaRegex = "\\)";
    
    // regex for any token in the language
    String regexp = commRegex + "|" + identRegex + "|" + floRegex + "|" + intRegex + "|" + assignRegex + "|" + addRegex + "|" + subRegex + "|" + mulRegex + "|" + divRegex + "|" + lPaRegex + "|" + rPaRegex;
    
    // pattern matcher (or FSM) for each regular expression
    Pattern commP = Pattern.compile(commRegex);
    Pattern identP = Pattern.compile(identRegex);
    Pattern intP = Pattern.compile(intRegex);
    Pattern floP = Pattern.compile(floRegex);
    Pattern assignP = Pattern.compile(assignRegex);
    Pattern addP = Pattern.compile(addRegex);
    Pattern subP = Pattern.compile(subRegex);
    Pattern mulP = Pattern.compile(mulRegex);
    Pattern divP = Pattern.compile(divRegex);
    Pattern lPaP = Pattern.compile(lPaRegex);
    Pattern rPaP = Pattern.compile(rPaRegex);
    Pattern anyP = Pattern.compile(regexp);
    
    // get a line from the user to analyze
    List<String> lines = new LinkedList<String>();
    Scanner s = new Scanner(System.in);
    System.out.println("");
    System.out.print("> ");
    String in = s.nextLine();
    
    // create object to match patterns in input string
    Matcher commM = commP.matcher(in);
    Matcher identM = identP.matcher(in);
    Matcher intM = intP.matcher(in);
    Matcher floM = floP.matcher(in);
    Matcher assignM = assignP.matcher(in);
    Matcher addM = addP.matcher(in);
    Matcher subM = subP.matcher(in);
    Matcher mulM = mulP.matcher(in);
    Matcher divM = divP.matcher(in);
    Matcher lPaM = lPaP.matcher(in);
    Matcher rPaM = rPaP.matcher(in);
    Matcher anyM = anyP.matcher(in);
    
    // scan through string, categorizing the pieces,
    // and print out the lexical analysis
    boolean success = anyM.find();
    while(success){
      
      // get start and end indices of current token
      int start = anyM.start();
      int end = anyM.end();
      
      // grab the matching sub-string
      String lex = anyM.group();
      
      // matcher focus to the sub-string
      commM.region(start, end);
      identM.region(start, end);
      intM.region(start, end);
      floM.region(start, end);
      assignM.region(start, end);
      addM.region(start, end);
      subM.region(start, end);
      mulM.region(start, end);
      divM.region(start, end);
      lPaM.region(start, end);
      rPaM.region(start, end);
      
      // find matching pattern & assign type
      Tokens type = Tokens.UNRECOGNIZED;
      if(commM.matches()){
        type = Tokens.COMMENT;
      }else if(identM.matches()){
        type = Tokens.IDENTIFIER;
      }else if(intM.matches()){
        type = Tokens.INTEGER_LITERAL;
      }else if(floM.matches()){
        type = Tokens.FLOATING_LITERAL;
      }else if(assignM.matches()){
        type = Tokens.ASSIGNMENT_OPERATOR;
      }else if(addM.matches()){
        type = Tokens.ADDITION_OPERATOR;
      }else if(subM.matches()){
        type = Tokens.SUBTRACTION_OPERATOR;
      }else if(mulM.matches()){
        type = Tokens.MULTIPLICATION_OPERATOR;
      }else if(divM.matches()){
        type = Tokens.DIVISION_OPERATOR;
      }else if(lPaM.matches()){
        type = Tokens.LEFT_PARENTHESIS;
      }else if(rPaM.matches()){
        type = Tokens.RIGHT_PARENTHESIS;
      }
      
      // print out token
      System.out.println("index = " + start + " type =  " + type + " lexeme = \"" + lex + "\"");
      
      // are there any more tokens left?
      success = anyM.find();
    }
    
    // Close scanner
    s.close();
  }
}
