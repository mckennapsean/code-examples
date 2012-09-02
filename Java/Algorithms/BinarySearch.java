// by Sean McKenna on February 16th, 2010

/** Binary Search class performs a binary search on a data set.
 *
 *  @author Sean McKenna
 *  @version February 16th, 2010
 */
public class BinarySearch{

    /**	Main method creates an array and starts the search for each element in the array
     *	and then checks for an element outside of the array
     * 
     *	@param args necessary string array to run the Java program
     */
    public static void main(String[] args){
	int term = 0;
	int[] array = {1,1,2,3,5,8,13,21,34,55,89};
//	int[] array = {1,1,2,3,5,8,13,21,34,55};
	for(int i=0;i<array.length;i++){
	    term = array[array.length-i-1];
	    BinarySearch bs = new BinarySearch(array,term);
	}
	term = -1;
	BinarySearch bs = new BinarySearch(array,term);
    }

    /**	BinarySearch is an object created to search an array and prints out results
     *
     *	<p>
     *	PRE-CONDITION: Requires a sorted array to be input
     *	</p>
     *
     *	@param array integer array to be searched through
     *	@param term search term to find in the array
     */
    public BinarySearch(int[] array,int term){
	int index = searcher(array,term,0,array.length-1);
	System.out.println("For term = "+term+", index = "+index);
    }

    /**	Searcher is the recursive, logarithmic method called to search the array
     *	for a position that contains the value of the term
     *
     * <p>
     *	PRE-CONDITION: Must originally be called with the startIndex=0 and
     *	endIndex=array.length-1.  The array must be sorted to work.
     *	</p><p>
     *	POST-CONDITION: Returns the index of the first encountered position of
     *	the search term in the array, so repeated terms will be ignored, and if
     *	the term is not in the array index=-1 is the result.
     *	</p>
     *
     *	@param array integer array to be searched through recursively
     *	@param term search term to find in the array
     *	@param startIndex index to start the search from
     *	@param endIndex index to finish searching from
     */
    public int searcher(int[] array,int term,int startIndex,int endIndex){
	if(endIndex<startIndex){
	    return -1;
	}else{
	    int mid = (startIndex+endIndex)/2;
	    if(array[mid]==term){
		return mid;
	    }else if(array[mid]>term){
		return searcher(array,term,startIndex,mid-1);
	    }else{
		return  searcher(array,term,mid+1,endIndex);
	    }
	}
    }
}
