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

// how many N-bits (1) exist in two's complement representation of numbers
// solution to Problem #1 of the ACM 2009 Regional Competition

public class Nbits{
  private static final int A = 5;
  private static final int B = 14;
  private static final int N = 3;
  
  public static void main(String[] args){
    int count = 0;
    
    for(int i = A; i <= B; i++){
      int bits = Integer.bitCount(i);
      if(bits == N)
        count++;
    }
    System.out.println(count);
  }
}
