/** by Sean McKenna on February 8th, 2010
 *  Last Modified: February 11th, 2010
 *
 *  Searching.java employs basic sorting algorithms on a random set of data.
 *  The main sorting algorithms used are the select, insertion, and merge sorts.
 *  Results of the sort are output and labeled where appropriate.
 *
 *  Any code/methods I have majorly modified are commented above the method.
 *  The main void method, Searching, does most of the calling to other methods.
 *
 *  Currently, the algorithms used are the same as those in the book.
 */

import java.text.NumberFormat;
import java.util.Random;

public class Searching {

    private static final double EPSILON = 1E-8;
    private NumberFormat formatter;
    private Random rng;

    public boolean approximatelyZero( double x ) {
        return Math.abs( x ) < EPSILON ;
    } // approximatelyZero( double )

    public boolean approximatelyEqual( double x, double y ) {
        return this.approximatelyZero( x  - y ) ;
    } // approximatelyEqual( double, double )

    public double[] create(int size) {
        double[] data = new double[size];
        for (int i = 0; i < data.length; i++) {
            data[i] = this.rng.nextDouble();
        } // for
        return data;
    } // create( int )

    //Formatted to allow a string to be printed above each data list
    public void print(double[] data,String s) {
        //Formatting
	System.out.println();
	System.out.println(s);

	if (data.length <= 24) {
            for (double x : data) {
                System.out.print(this.formatter.format(x) + " ");
            } // for
            System.out.println();
        } // if
        else {
            for (double x : data) {
                System.out.println(this.formatter.format(x));
            } // for
        } // else
    } // print( double [] )

    public boolean contains( double [] data, double keyValue ) {
        for( int i = 0; i < data.length; i++ ) {
            if( this.approximatelyEqual( data[i], keyValue)) {
                return true ;
            } // if
        } // for
        return false ;
    } // contains( double [], double )

    public int countGreaterThanKeyValue( double [] data, double keyValue ) {
        int count = 0;
        for( double x : data ) {
            if( x > keyValue ) {
                count++ ;
            } // if
        } // for
        return count ;
    } // countGreaterThanKeyValue( double [], double )

    public int countLessThanKeyValue( double [] data, double keyValue ) {
        int count = 0;
        for( double x : data ) {
            if( x < keyValue ) {
                count++ ;
            } // if
        } // for
        return count ;
    } // countLessThanKeyValue( double [], double )

    public double minimum(double[] data) {
        double bestGuessSoFar = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] < bestGuessSoFar) {
                bestGuessSoFar = data[i];
            } // if
        } //  for
        return bestGuessSoFar;
    } // minimum( double [] )

    public double maximum(double[] data) {
        double bestGuessSoFar = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > bestGuessSoFar) {
                bestGuessSoFar = data[i];
            } // if
        } //  for
        return bestGuessSoFar;
    } // maximum( double [] )

    public int positionOfMinimum(double[] data) {
        int bestGuessSoFar = 0;
        for (int i = 1; i < data.length; i++) {
            if (data[i] < data[bestGuessSoFar]) {
                bestGuessSoFar = i;
            } // if
        } //  for
        return bestGuessSoFar;
    } // positionOfMinimum( double [] )

    //For finding a minimum value based on a starting index
    public int positionOfMinimum(double[] array,int startIndex){
	int guess = startIndex;
	for(int i=startIndex+1;i<array.length;i++){
	    if(array[i] < array[guess]){
		guess = i;
	    }
	}
	return guess;
    }

    public int positionOfMaximum(double[] data) {
        int bestGuessSoFar = 0;
        for (int i = 1; i < data.length; i++) {
            if (data[i] > data[bestGuessSoFar]) {
                bestGuessSoFar = i;
            } // if
        } //  for
        return bestGuessSoFar;
    } // positionOfMaximum( double [] )

    public int positionOfFirstGreaterThan(double[] data, double keyValue) {
        int result = -1;

        for (int i = 0; i < data.length; i++) {
            if (data[i] > keyValue) {
                return i;
            } // if
        } // for

        return result;
    } // positionOfFirstGreaterThan( double [], double  )

    public int positionOfLastGreaterThan(double[] data, double keyValue) {
        int result = -1;

        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] > keyValue) {
                return i;
            } // if
        } // for

        return result;
    } // positionOfLastGreaterThan( double [], double  )

    public int positionOfFirstLessThan(double[] data, double keyValue) {
        int result = -1;

        for (int i = 0; i < data.length; i++) {
            if (data[i] < keyValue) {
                return i;
            } // if
        } // for

        return result;
    } // positionOfFirstLessThan( double [], double  )

    public int positionOfLastLessThan(double[] data, double keyValue) {
        int result = -1;

        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] < keyValue) {
                return i;
            } // if
        } // for

        return result;
    } // positionOfLastLessThan( double [], double  )

    public int positionOfFirstGreaterThan(double[] data, int index,
            double keyValue) {
        int result = -1;

        for (int i = index; i < data.length; i++) {
            if (data[i] > keyValue) {
                return i;
            } // if
        } // for

        return result;
    } // positionOfFirstGreaterThan( double [], int, double  )

    public int positionOfLastGreaterThan(double[] data, int index,
            double keyValue ) {
        int result = -1;

        for (int i = index; i >= 0; i--) {
            if (data[i] > keyValue) {
                return i;
            } // if
        } // for

        return result;
    } // positionOfLastGreaterThan( double [], int, double  )

    public int positionOfFirstLessThan(double[] data, int index,
            double keyValue ) {
        int result = -1;

        for (int i = index; i < data.length; i++) {
            if (data[i] < keyValue) {
                return i;
            } // if
        } // for

        return result;
    } // positionOfFirstLessThan( double [], int, double  )

    public int positionOfLastLessThan(double[] data, int index,
            double keyValue ) {
        int result = -1;

        for (int i = index; i >= 0; i--) {
            if (data[i] < keyValue) {
                return i;
            } // if
        } // for

        return result;
    } // positionOfLastLessThan( double [], int, double  )

    //Swap exchanges two elements in an array of doubles
    public void swap(double[] data,int indexA,int indexB){
	double temp = data[indexA];
	data[indexA] = data[indexB];
	data[indexB] = temp;
    }

    //Insert Next will take an array and index value to sort it piece at a time
    public void insertNext(int index,double[] array){
	//Pos variable determines where the element should go in the array
	int pos = index - 1;
	while(pos >= 0 && array[index] < array[pos]){
	    pos--;
	}
	pos++;

	//prepartion of re-aligning the array to allow for the insertion
	double temp = array[index];
	for(int move=index-1;move >= pos;move--){
	    array[move+1] = array[move];
	}
	
	//Insertion of element into the proper position
	array[pos] = temp;
    }

    //Merge method: uses temp array to copy and sort (while merging) a portion of an array
    public void merge(double[] array,int l,int m,int r,double[] temp){
	//variables: tracks the elements split into the halves and where to add the new element
	int indexL = l;
	int indexR = m + 1;
	int target = l;

	//Copies the needed pieces of the array into a temporary array
	for(int i=l;i<=r;i++){
	    temp[i] = array[i];
	}

	//merges together any values if there are still elements in each half
	while(indexL<=m && indexR<=r){
	    if(temp[indexL]<temp[indexR]){
		array[target] = temp[indexL];
		indexL++;
	    } else{
		array[target] = temp[indexR];
		indexR++;
	    }
	    target++;
	}

	//copies any leftover elements from the left half of the temp array
	while(indexL<=m){
	    array[target] = temp[indexL];
	    indexL++;
	    target++;
	}

	//finishes copying any leftover elements from the right half
	while(indexR<=r){
	    array[target] = temp[indexR];
	    indexR++;
	    target++;
	}
    }

    public Searching() {
        this.formatter = NumberFormat.getInstance();
        this.formatter.setMinimumIntegerDigits(1);
        this.formatter.setMaximumFractionDigits(2);
        this.formatter.setMinimumFractionDigits(2);

        this.rng = new Random();

	//Creates data lists (and ones for sorting) and outputs to user
        double[] data = this.create(12);
	int i = 0;
	double[] sort0 = new double[data.length];
	double[] sort1 = new double[data.length];
	double[] sort2 = new double[data.length];
	for(double x:data){
	    sort0[i] = x;
	    sort1[i] = x;
	    sort2[i] = x;
	    i++;
	}
	this.print(data,"Random Data Set");

	//Selection Sort on the original data set
//	sort0 = this.selectionSort(sort0,0);	    //recursive
	sort0 = this.selectionSort(sort0);	    //iterative
	this.print(sort0,"Iterative Selection Sort");

	//Insertion sort on the original data set
//	sort1 = this.insertionSort(sort1,sort1.length-1);	//recursive
	sort1 = this.insertionSort(sort1);			//iterative
	this.print(sort1,"Iterative Insertion Sort");

	//Merge sort on the original data set
	double[] temp = new double[sort2.length];
	sort2 = this.mergeSort(sort2,0,sort2.length-1,temp);
	this.print(sort2,"Recursive Merge Sort");
    } // Searching()

    public static void main(String[] args) {
        Searching searching = new Searching();
    } // main( String [] )

    //Selection Sort: Find minimum, move to front, and continue (recursive)
    public double[] selectionSort(double[] array,int startIndex){
	if(startIndex < array.length - 1){
	    int minIndex = this.positionOfMinimum(array,startIndex);
	    this.swap(array,minIndex,startIndex);
	    selectionSort(array,startIndex+1);
	}
	return array;
    }

    //Selection Sort: Find minimum, move to front, and continue (iterative)
    public double[] selectionSort(double[] array){
	for(int i=0;i<array.length-1;i++){
	    int minIndex = positionOfMinimum(array,i);
	    swap(array,minIndex,i);
	}
	return array;
    }

    //Insertion Sort: Add one unit at a time, sorts as you go (recursive)
    public double[] insertionSort(double[] array,int lastIndex){
	if(lastIndex >= 1){
	    insertionSort(array,lastIndex-1);
	    insertNext(lastIndex,array);
	}
	return array;
    }

    //Insertion Sort: Add one unit at a time, sorts as you go (iterative)
    public double[] insertionSort(double[] array){
	for(int i=1;i<array.length;i++){
	    insertNext(i,array);
	}
	return array;
    }

    //Merge Sort: divide & conquer, split array up and sort it as two halves
    //splits up the array into little pieces and merges those pieces together
    public double[] mergeSort(double[] array,int l,int r,double[] temp){
	if(l<r){
	    int m = (r+l)/2;
	    mergeSort(array,l,m,temp);
	    mergeSort(array,m+1,r,temp);
	    merge(array,l,m,r,temp);
	}
	return array;
    }
} // Searching
