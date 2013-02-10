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

// performs a binary search on the dataset

public class BinarySearch{
  
  // create an array & search for each element in that array
  // then check for that element outside of the array
  public static void main(String[] args){
	  int term = 0;
  	int[] array = {1,1,2,3,5,8,13,21,34,55,89};
    //int[] array = {1,1,2,3,5,8,13,21,34,55};
    for(int i=0; i<array.length; i++){
      term = array[array.length - i - 1];
      BinarySearch bs = new BinarySearch(array, term);
    }
    term = -1;
    BinarySearch bs = new BinarySearch(array, term);
  }
  
  // object created to search an array & print out results
  // requires a sorted array and a search term
  public BinarySearch(int[] array, int term){
    int index = searcher(array, term, 0, array.length - 1);
    System.out.println("For term = " + term + ", index = " + index);
  }
  
  // recursive, logarithmic method to search a sorted array for a term
  // requires a sorted array and start index 0 and endIndex of one less than the array length
  // returns the index of the first encountered index of the search term
  // returns -1 if the search term is not found
  public int searcher(int[] array, int term, int startIndex, int endIndex){
    if(endIndex < startIndex){
      return -1;
    }else{
      int mid = (startIndex + endIndex) / 2;
      if(array[mid] == term)
        return mid;
      else if(array[mid] > term)
        return searcher(array, term, startIndex, mid - 1);
      else
        return searcher(array, term, mid + 1, endIndex);
    }
  }
}
