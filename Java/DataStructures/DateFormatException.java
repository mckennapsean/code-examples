//This class handles the exceptions based on the input of the user on the date.

/** This class handles exceptions that occur in Date.java and prints out their
 *  results both in the terminal and in the output text field.
 *
 * @author Sean McKenna
 * @version Last Modified: February 9th, 2010
 */
public class DateFormatException extends IllegalArgumentException{
    /**	Main exception, handles all exceptions with the date input in a MyDate object.
     * 
     * @param message String that will be output in the terminal and on the screen.
     */
    public DateFormatException(String message) {
	System.out.println(message);
	Date.output.setText(message);
    }
}
