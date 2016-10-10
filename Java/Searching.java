// employs several basic sorting algorithms on random data
// uses select, insertion, & merge sorts

// imports for random generation & formatting
import java.text.NumberFormat;
import java.util.Random;

// class for searching & sorting arrays
public class Searching{
  
  // variables for sorting
  private static final double EPSILON = 1E-8;
  private NumberFormat formatter;
  private Random rng;
  
  // returns true when the value is close enough to zero
  public boolean approxZero(double num){
    return Math.abs(num) < EPSILON;
  }
  
  // returns true when two values are close enough
  public boolean approxEqual(double num1, double num2){
    return this.approxZero(num1 - num2);
  }
  
  // create a random array of doubles
  public double[] create(int size){
    double[] data = new double[size];
    for(int i = 0; i < data.length; i++)
      data[i] = this.rng.nextDouble();
    return data;
  }
  
  // format output to print string above each data list
  public void print(double[] data, String str){
    
    // formatting
    System.out.println();
    System.out.println(str);
    
    // print out the data for each line
    if(data.length <= 24){
      for(double x : data)
        System.out.print(this.formatter.format(x) + " ");
      System.out.println();
    }else{
      for(double x : data)
        System.out.println(this.formatter.format(x));
    }
  }
  
  // see if the array contains a value
  public boolean contains(double [] data, double keyVal){
    for(int i = 0; i < data.length; i++){
      if(this.approxEqual(data[i], keyVal))
        return true;
    }
    return false;
  }
  
  // check if the current count is greater than the key value
  public int countGreaterThanKeyValue(double [] data, double keyVal){
    int count = 0;
    for(double x : data){
      if(x > keyVal)
        count++;
    }
    return count;
  }
  
  // check if the current count is less than the key value
  public int countLessThanKeyValue(double [] data, double keyVal){
    int count = 0;
    for(double x : data){
      if(x < keyVal)
        count++;
    }
    return count;
  }
  
  // get the array minimum value
  public double minimum(double[] data){
    double bestGuess = data[0];
    for(int i = 1; i < data.length; i++){
      if(data[i] < bestGuess)
        bestGuess = data[i];
    }
    return bestGuess;
  }
  
  // get the array maximum value
  public double maximum(double[] data){
    double bestGuess = data[0];
    for(int i = 1; i < data.length; i++){
      if(data[i] > bestGuess)
        bestGuess = data[i];
    }
    return bestGuess;
  }
  
  // find the position of the minimum value
  public int positionOfMinimum(double[] data){
    int bestGuess = 0;
    for(int i = 1; i < data.length; i++){
      if(data[i] < data[bestGuess])
        bestGuess = i;
    }
    return bestGuess;
  }
  
  // find the position of the minimum value based on a starting index
  public int positionOfMinimum(double[] array, int startIndex){
    int guess = startIndex;
    for(int i = startIndex + 1; i < array.length; i++){
      if(array[i] < array[guess])
        guess = i;
    }
    return guess;
  }
  
  // find the position of the maximum value
  public int positionOfMaximum(double[] data){
    int bestGuess = 0;
    for(int i = 1; i < data.length; i++){
      if(data[i] > data[bestGuess])
        bestGuess = i;
    }
    return bestGuess;
  }
  
  // find the position of the first value greater than some value
  public int positionOfFirstGreaterThan(double[] data, double keyVal){
    int result = -1;
    for(int i = 0; i < data.length; i++){
      if(data[i] > keyVal)
        return i;
    }
    return result;
  }
  
  // find the position of the last value greater than some value
  public int positionOfLastGreaterThan(double[] data, double keyVal){
    int result = -1;
    for(int i = data.length - 1; i >= 0; i--){
      if(data[i] > keyVal)
        return i;
    }
    return result;
  }
  
  // find the position of the first value less than some value
  public int positionOfFirstLessThan(double[] data, double keyVal){
    int result = -1;
    for(int i = 0; i < data.length; i++){
      if(data[i] < keyVal)
        return i;
    }
    return result;
  }
  
  // find the position of the last value less than some value
  public int positionOfLastLessThan(double[] data, double keyVal){
    int result = -1;
    for(int i = data.length - 1; i >= 0; i--){
      if(data[i] < keyVal)
        return i;
    }
    return result;
  }
  
  // find the position of the first value greater than some value
  public int positionOfFirstGreaterThan(double[] data, int index, double keyVal){
    int result = -1;
    for(int i = index; i < data.length; i++){
      if(data[i] > keyVal)
        return i;
    }
    return result;
  }
  
  // find the position of the last value greater than some value
  public int positionOfLastGreaterThan(double[] data, int index, double keyVal){
    int result = -1;
    for(int i = index; i >= 0; i--){
      if(data[i] > keyVal)
        return i;
    }
    return result;
  }
  
  // find the position of the first value less than some value
  public int positionOfFirstLessThan(double[] data, int index, double keyVal){
    int result = -1;
    for(int i = index; i < data.length; i++){
      if(data[i] < keyVal)
        return i;
    }
    return result;
  }
  
  //find the position of the last value less than some value
  public int positionOfLastLessThan(double[] data, int index, double keyVal){
    int result = -1;
    for(int i = index; i >= 0; i--){
      if(data[i] < keyVal)
        return i;
    }
    return result;
  }
  
  // exchanges two elements inside arrays
  public void swap(double[] data, int indexA, int indexB){
    double temp = data[indexA];
    data[indexA] = data[indexB];
    data[indexB] = temp;
  }
  
  // takes an array and index value to sort it, one piece at a time
  public void insertNext(int index, double[] array){
    
    // determines where the element should go in the array
    int pos = index - 1;
    
    // find the proper location of the element in the array (sorting)
    while(pos >= 0 && array[index] < array[pos])
      pos--;
    pos++;
    
    // prepare to re-align the array to allow for the insertion
    double temp = array[index];
    for(int move = (index - 1); move >= pos; move--)
      array[move + 1] = array[move];
    
    // insertion of element into the proper position
    array[pos] = temp;
  }
  
  // merge method: uses temp array to copy and sort (while merging) a portion of an array
  public void merge(double[] array, int l, int m, int r, double[] temp){
    
    // track the elements, split into the halves, and where to add the new element
    int indexL = l;
    int indexR = m + 1;
    int target = l;
    
    // copy the needed pieces of the array into a temporary array
    for(int i = l; i <= r; i++)
      temp[i] = array[i];
      
    // merges together any values if there are still elements in each half
    while(indexL <= m && indexR <= r){
      if(temp[indexL] < temp[indexR]){
        array[target] = temp[indexL];
        indexL++;
      }else{
        array[target] = temp[indexR];
        indexR++;
      }
      target++;
    }
    
    // copy any leftover elements from the left half of the temp array
    while(indexL <= m){
      array[target] = temp[indexL];
      indexL++;
      target++;
    }
    
    // finish copying any leftover elements from the right half
    while(indexR <= r){
      array[target] = temp[indexR];
      indexR++;
      target++;
    }
  }
  
  // test of various searching & sorting methods
  public Searching(){
    
    // variables for number formatting & random number generation
    this.formatter = NumberFormat.getInstance();
    this.formatter.setMinimumIntegerDigits(1);
    this.formatter.setMaximumFractionDigits(2);
    this.formatter.setMinimumFractionDigits(2);
    this.rng = new Random();
    
    // create data lists (and ones for sorting) and outputs to user
    double[] data = this.create(12);
    int i = 0;
    double[] sort0 = new double[data.length];
    double[] sort1 = new double[data.length];
    double[] sort2 = new double[data.length];
    for(double x : data){
      sort0[i] = x;
      sort1[i] = x;
      sort2[i] = x;
      i++;
    }
    this.print(data,"Random Data Set");
    
    // Selection Sort
    // recursive & iterative options, respectively
    //sort0 = this.selectionSort(sort0, 0);
    sort0 = this.selectionSort(sort0);
    this.print(sort0, "Iterative Selection Sort");
    
    // Insertion Sort
    // recursive & iterative options, respectively
    //sort1 = this.insertionSort(sort1,sort1.length-1);
    sort1 = this.insertionSort(sort1);
    this.print(sort1, "Iterative Insertion Sort");
    
    // Merge sort
    // always recursive
    double[] temp = new double[sort2.length];
    sort2 = this.mergeSort(sort2, 0, sort2.length - 1, temp);
    this.print(sort2, "Recursive Merge Sort");
  }
  
  // main method to create the searching & sorting procedure
  public static void main(String[] args){
    Searching searching = new Searching();
  }
  
  // Selection Sort (recursive)
  // find minimum, move to front, and continue
  public double[] selectionSort(double[] array, int startIndex){
    if(startIndex < (array.length - 1)){
      int minIndex = this.positionOfMinimum(array, startIndex);
      this.swap(array, minIndex, startIndex);
      selectionSort(array,startIndex + 1);
    }
    return array;
  }
  
  // Selection Sort (iterative)
  // find minimum, move to front, and continue
  public double[] selectionSort(double[] array){
    for(int i = 0; i < (array.length - 1); i++){
      int minIndex = positionOfMinimum(array, i);
      swap(array, minIndex, i);
    }
    return array;
  }
  
  // Insertion Sort (recursive)
  // add one unit at a time, sorts as you go
  public double[] insertionSort(double[] array, int lastIndex){
    if(lastIndex >= 1){
      insertionSort(array, lastIndex - 1);
      insertNext(lastIndex, array);
    }
    return array;
  }
  
  // Insertion Sort (iterative)
  // add one unit at a time, sorts as you go
  public double[] insertionSort(double[] array){
    for(int i = 1; i < array.length; i++)
      insertNext(i, array);
    return array;
  }
  
  // Merge Sort
  // divide & conquer, split array up and sort it as two halves, merging all halves
  public double[] mergeSort(double[] array, int l, int r, double[] temp){
    if(l < r){
      int m = (r + l) / 2;
      mergeSort(array, l, m, temp);
      mergeSort(array, m + 1, r, temp);
      merge(array, l, m, r, temp);
    }
    return array;
  }
}
