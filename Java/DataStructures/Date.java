/** by Sean McKenna on February 8th, 2010
 *  Last modified on February 9th, 2010
 *
 *  This program creates an interface to allow a date to be input.
 *  The output (based on user input) is in the form of "MM/DD/YYYY"
 *  This is a solution to exercise 18.10.2 in Java: An Eventful Approach.
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Date.java builds an interface for the user to input a date of varying
 *  formats and reformats that input using the MyDate class.  The result is then
 *  output to the output text field.
 *
 * @author Sean McKenna
 * @version Last Modified: February 9th, 2010
 */
public class Date extends JFrame implements ActionListener{
    /**	Default height for the window (in pixels).
     */
    private static final int WINDOW_HEIGHT = 100;

    /**	Default width for the window (in pixels).
     */
    private static final int WINDOW_WIDTH = 220;

    /**	Window title.
     */
    private static final String TITLE = "Date";

    /**	Text field for input (at top).
     */
    private JTextField input;

    /**	Text field for the output (bottom).
     */
    public static JTextField output;

    /**	The main method that runs and creates a new Date/interface.
     */
    public static void main(String[] args) {
	Date date = new Date();
    }

    /**	The main object that creates a user interface with two text fields and
     *	one button.
     */
    private Date(){
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
	this.setTitle(TITLE);
	this.setVisible(true);
	Container frame = this.getContentPane();

	input = new JTextField();
	output = new JTextField();
	JPanel button = new JPanel();
	JButton getDate = new JButton("Get the Date");
	button.add(getDate);
	getDate.addActionListener((ActionListener) this);
	getDate.setActionCommand("getDate");

	frame.add(input,BorderLayout.NORTH);
	frame.add(button,BorderLayout.CENTER);
	frame.add(output,BorderLayout.SOUTH);
	this.validate();
    }

    /**`This method takes the input and creates a MyDate object to output the
     *	result in the output text field.  Occurs whenever the button is pressed.
     *
     * @param e Java action event, when the button is pressed.
     */
    public void actionPerformed(ActionEvent e) {
	String inputString = input.getText();
	MyDate md = new MyDate(inputString);
	String outputString = md.toString();
	output.setText(outputString);
    }
}
