// by Sean McKenna on February 9th, 2010
// supports Date.java to check user input for date format exceptions

// prints exceptions out in the text field and in the terminal
public class DateFormatException extends IllegalArgumentException{
  public DateFormatException(String message){
    System.out.println(message);
    Date.output.setText(message);
  }
}
