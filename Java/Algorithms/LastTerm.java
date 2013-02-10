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

// gets the length of a DDF (decimal digit factor) sequence
// generated from an input integer, finds the repetition, and prints length
// problem #6 of the ACM 2009 Regional Competition

public class LastTerm{
  
  // input variables for the sequence
  private static final int INPUT = 1448;
  private int COUNT = 0;
  
  // main method for calculating the length
  public static void main(String[] args){
    
    // more variables for the ddf sequence
    int ddf = INPUT;
    int lastTerm = 0;
    boolean onward = true;
    
    // start recursive loop to find the ddf length
    while(onward){
      int previousDDF =ddf;
      
      // store the factors (initially zero)
      // can store up to twelve separate factors in a number
      String[] factors = new String[13];
      for(int i = 0; i < 13; i++){
        factors[i] = "0";
      }
      
      // find all factors and store in a string array
      int stringCount = 1;
      for(int j = 1; j <= ddf / 2; j++){
        for(int k = 1; k <= ddf; k++){
          
          // for repeated factors
          if(("" + j).equals(factors[stringCount-1])){
            j = k = ddf + 1;
          }else if(j * k == ddf){
            if(j == k){
              factors[stringCount] = ""+j;
            }else{
              factors[stringCount] = ""+j;
              stringCount++;
              factors[stringCount] = ""+k;
            }
            stringCount++;
          }
        }
      }
      
      // sum the characters in the string array
      int ddfSum = 0;
      for(int l = 0; l < 13; l++)
        for(int m = 0; m < factors[l].length(); m++)
          ddfSum = ddfSum + Character.getNumericValue(factors[l].charAt(m));
      ddf = ddfSum;
      lastTerm++;
      if(ddf == previousDDF)
        onward = false;
    }
    System.out.println("For N = " + INPUT + ", DDF Sequence has " + lastTerm + " terms.");
  }
}
