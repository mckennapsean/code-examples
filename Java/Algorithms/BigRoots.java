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

// checks a big integer (P) for its square root, N
// this was problem #3 of the ACM 2009 Regional Competition

import java.math.BigInteger;

public class BigRoots{
  private static final BigInteger P = new BigInteger("4357186184021382204544");
  private static final int N = 7;
  
  public static void main(String[] args){
    BigInteger k = BigInteger.ZERO;
    BigInteger temp = BigInteger.ONE.pow(N);
    boolean onward = true;
    while(temp.pow(N).max(P).equals(P) && onward){
      if(temp.pow(N).equals(P)){
        onward = false;
        k = temp;
      }
      temp = temp.add(BigInteger.ONE);
    }
    
    if(onward)
      System.out.println("No Solution");
    else
      System.out.println(k);
  }
}
