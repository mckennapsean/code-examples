/** by Sean McKenna on February 10th, 2010
 *
 *  This program uses a palindrome of a given Roman Numeral string and
 *  calculates the "Roman Palindrome Number" off of that.
 *
 *  This program was Problem 8 of the ACM 2009 Regional Competition.
 */

public class RomanPalindromes {
    private static final String INPUT = "MCMLXXIV";
    private static int sum = 0;

    public static void main(String[] args) {
	//Array that holds values of Roman Numerals
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

	//Creates the roman numeral palindrome
	int count = 0;
	String palindrome = "";
	if(INPUT.length() % 2 == 1){	//if length is ODD
	    count++;
	}
	for(int i=1;i<=INPUT.length()-count;i++){
	    palindrome = palindrome + INPUT.charAt(INPUT.length()-i);
	}
	palindrome = palindrome + INPUT;

	//Calculates the roman numeral palindrome number
	for(int j=0;j<palindrome.length()-1;j++){
	    int pairSum = 0;
	    int first = 0;
	    int second = 0;
	    for(int k=0;k<7;k++){
		if(palindrome.substring(j,j+1).equals(array[k][0])){
		    first = Integer.parseInt(array[k][1]);
		}
		if(palindrome.substring(j+1,j+2).equals(array[k][0])){
		    second = Integer.parseInt(array[k][1]);
		}
	    }
	    if(first<second){
		pairSum = second - first;
	    }else{
		pairSum = second + first;
	    }
	    sum = sum + pairSum;
	}

	//Output of results
	System.out.println("Input: "+INPUT);
	System.out.println("Palindrome: "+palindrome);
	System.out.println("Palindrome Number: "+sum);
    }
}
