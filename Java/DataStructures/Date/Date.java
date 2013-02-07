// by Sean McKenna on February 9th, 2010
// queries the user for a date string, checking it for exceptions
// broken down into a separate MyDate object class & format exception class
// solution to exercise 18.10.2 in "Java: An Eventful Approach"

// necessary imports for Java GUI
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// interface to check format of user input
public class Date extends JFrame implements ActionListener{
  
  // height & width of window
  private static final int WINDOW_HEIGHT = 100;
  private static final int WINDOW_WIDTH = 220;

  // window title
  private static final String TITLE = "Date";
  
  // text fields for input & output
  private JTextField input;
  public static JTextField output;
  
  // main method to create the Date interface
  public static void main(String[] args){
    Date date = new Date();
  }
  
  // Date object that creates the GUI
  private Date(){
    
    // set up window attributes
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setTitle(TITLE);
    this.setVisible(true);
    Container frame = this.getContentPane();
    
    // set up window objects, text fields, input, etc.
    input = new JTextField();
    output = new JTextField();
    JPanel button = new JPanel();
    JButton getDate = new JButton("Get the Date");
    button.add(getDate);
    getDate.addActionListener((ActionListener) this);
    getDate.setActionCommand("getDate");
    
    // set up container to automatically lay out the GUI elements
    frame.add(input,BorderLayout.NORTH);
    frame.add(button,BorderLayout.CENTER);
    frame.add(output,BorderLayout.SOUTH);
    this.validate();
  }
  
  // creates a MyDate object from input and push output to the text field
  // happens whenever the button is pressed
  public void actionPerformed(ActionEvent e){
    String inputString = input.getText();
    MyDate md = new MyDate(inputString);
    String outputString = md.toString();
    output.setText(outputString);
  }
}
