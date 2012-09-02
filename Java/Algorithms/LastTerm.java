/** by Sean McKenna on February 10th, 2010
 *
 *  This program gets the length of a DDF (decimal digit factor) sequence based
 *  on an input integer.  Once the sequence repeats, the length is determined.
 *
 *  This program was Problem 6 of the ACM 2009 Regional Competition.
 */

public class LastTerm {
    private static final int INPUT = 1448;
    private int COUNT = 0;

    public static void main(String[] args) {
	//Starts loop to create a recursive method to finding the ddf length
	int ddf = INPUT;
	int lastTerm = 0;
	boolean onward = true;
	while(onward){
	    int previousDDF =ddf;

	    //String array to store the factors (and initialized with 0's)
	    //Can handle up to twelve separate factors in a number
	    String[] factors = new String[13];
	    for(int i=0;i<13;i++){
		factors[i] = "0";
	    }

	    //Finds all factors and stores in the string array
	    int stringCount = 1;
	    for(int j=1;j<=ddf/2;j++){
		for(int k=1;k<=ddf;k++){
		    if((""+j).equals(factors[stringCount-1])){  //for repeated factors
			j=k=ddf+1;				//jump out of loop
		    } else if(j*k == ddf){
			if(j==k){
			    factors[stringCount] = ""+j;
			} else{
			    factors[stringCount] = ""+j;
			    stringCount++;
			    factors[stringCount] = ""+k;
			}
			stringCount++;
		    }
		}
	    }

	    //Sums the characters in the string array
	    int ddfSum = 0;
	    for(int l=0;l<13;l++){
		for(int m=0;m<factors[l].length();m++){
		    ddfSum = ddfSum + Character.getNumericValue(factors[l].charAt(m));
		}
	    }
	    ddf = ddfSum;
	    lastTerm++;
	    if(ddf == previousDDF){
		onward = false;
	    }
	}
	System.out.println("For N = "+INPUT+", DDF Sequence has "+lastTerm+" terms.");
    }
}
