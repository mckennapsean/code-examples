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

// uses a palindrome of some input roman numeral to get the number
// problem #8 of the ACM 2009 Regional Competition

public class RomanPalindromes{
  
  // variables to test string
  private static final String INPUT = "MCMLXXIV";
  private static int sum = 0;
  
  // main test of the input string
  public static void main(String[] args){
    
    // array that holds values of Roman Numerals
    String[][] array = new String[7][2];
    array[0][0] = "I";
    array[0][1] = "1";
    array[1][0] = "V";
    array[1][1] = "5";
    array[2][0] = "X";
    array[2][1] = "10";
    array[3][0] = "L";
    array[3][1] = "50";
    array[4][0] = "C";
    array[4][1] = "100";
    array[5][0] = "D";
    array[5][1] = "500";
    array[6][0] = "M";
    array[6][1] = "1000";
    
    // creates the roman numeral palindrome
    int count = 0;
    String palindrome = "";
    
    // if length is odd
    if(INPUT.length() % 2 == 1)
      count++;
    
    for(int i = 1; i <= (INPUT.length() -count); i++)
      palindrome = palindrome + INPUT.charAt(INPUT.length() - i);
    palindrome = palindrome + INPUT;
    
    // calculates the roman numeral palindrome number
    for(int j = 0; j < (palindrome.length() - 1); j++){
      int pairSum = 0;
      int first = 0;
      int second = 0;
      for(int k = 0; k < 7; k++){
        if(palindrome.substring(j, j + 1).equals(array[k][0]))
          first = Integer.parseInt(array[k][1]);
        if(palindrome.substring(j + 1, j + 2).equals(array[k][0]))
          second = Integer.parseInt(array[k][1]);
      }
      if(first < second)
        pairSum = second - first;
      else
        pairSum = second + first;
      sum = sum + pairSum;
    }
    
    // output of results
    System.out.println("Input: " + INPUT);
    System.out.println("Palindrome: " + palindrome);
    System.out.println("Palindrome Number: " + sum);
  }
}
