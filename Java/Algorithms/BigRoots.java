/** by Sean McKenna on February 10th, 2010
 *
 *  This program sees if a big integer (P) has a root of N, and if so it prints
 *  the given root, else prints no solution.
 *
 *  This program was Problem 3 of the ACM 2009 Regional Competition.
 */

import java.math.BigInteger;

public class BigRoots {
    private static final BigInteger P = new BigInteger("4357186184021382204544");
    private static final int N = 7;

    public static void main(String[] args) {
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
	if(onward){
	    System.out.println("No Solution");
	} else{
	    System.out.println(k);
	}
    }
}
