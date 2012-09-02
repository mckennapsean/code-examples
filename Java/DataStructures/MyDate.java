//This class allows for a creation of a new date and methods to access that date.

/** MyDate.java is an object representating a single date as a string.  It takes
 *  the input of a string and reformats that string into the date format
 *  MM/DD/YYYY based on said string.  A DateFormatException is thrown for errors
 *  based on the user's input.
 *
 * @author Sean McKenna
 * @version Last Modified: February 9th, 2010
 */
public class MyDate {
    /**	Date (string array) holds the three pieces (month, day, year) of the
     *	date in an array which is then checked for a DateFormatException.
     */
    public String[] date = new String[3];
    
    /**	dateString conbines the data from the Date array to create a properly
     *	formatted output string which is returned by using the toString() method
     *	of any MyDate object.
     */
    private String dateString;

    /**	Any MyDate object parses the input string into the date array and checks
     *	for the date format exception.
     *
     * @param s Input string that is parsed.
     */
    public MyDate(String s){
	s = s.replace(" ","");
        date = s.split("/");
	try{
	    String test = date[0]+"/"+date[1]+"/"+date[2];
	} catch(ArrayIndexOutOfBoundsException a){
	    throw new DateFormatException("Slash Error: must have exactly two.");
	}
    }

    /**	getMonth removes the month number from the date array and checks for a
     *	date format exception.
     *
     * @return Yields an integer with the month number
     */
    public int getMonth(){
	int month = 0;
	try{
	    month = Integer.parseInt(date[0]);
	} catch(NumberFormatException nfe){
	    throw new DateFormatException("Month Not A Number: "+date[0]);
	}
	if(month > 12 || month < 1){
	    throw new DateFormatException("Month Input Error: "+date[0]);
	}
	return month;
    }

    /**	getDay removes the day number from the date array and checks for a date
     *	format exception.
     *
     * @return Yields an integer with the day number
     */
    public int getDay(){
	int day = 0;
	try{
	    day = Integer.parseInt(date[1]);
	} catch(NumberFormatException nfe){
	    throw new DateFormatException("Day Not A Number: "+date[1]);
	}
	if(day > 31 || day < 1){
	    throw new DateFormatException("Day Input Error: "+date[1]);
	}
	return day;
    }

    /**	getYear removes the year number from the date array and checks for a
     *	date format exception.
     *
     * @return Yields an integer with the year number.
     */
    public int getYear(){
    	int year = 0;
	try{
	    year = Integer.parseInt(date[2]);
	} catch(NumberFormatException nfe){
	    throw new DateFormatException("Year Not A Number: "+date[2]);
	}
	return year;
    }

    /**	toString will return a string in a proper format using the get methods
     *	and adds zeros to months and days that are less than ten.
     *
     * @return Produces a string to represent the date in the form MM/DD/YYYY
     */
    @Override
    public String toString(){
	int month = this.getMonth();
	int day = this.getDay();
	int year = this.getYear();
	String m = "";
	String d = "";
	if(month<10 && month>0){
	    m = "0";
	}
	if(day<10 && day>0){
	    d = "0";
	}
	dateString = m+month+"/"+d+day+"/"+year;
	return dateString;
    }
}
