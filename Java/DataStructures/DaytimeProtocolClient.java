// connects to a server (via a socket) to get date-time information in a GUI
// uses port 13, the Internet Daytime Protocol
// solution to exercise 19.8.3 in "Java: An Eventful Approach"

// necessary imports for GUI elements & server-client connections
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// main class
public class DaytimeProtocolClient extends JFrame implements ActionListener{
  
  // variables for program setup
  private static final int WINDOW_WIDTH = 550;
  private static final int WINDOW_HEIGHT = 125;
  private static final int COLUMN_WIDTH = WINDOW_WIDTH / 20;
  private static final String TITLE = "Daytime Protocol Client";
  private static final String START_SERVER = "time.nist.gov";
  private JTextField server;
  private JTextArea output;
  public Socket time;
  
  // main method, create a new client
  public static void main(String[] args){
    DaytimeProtocolClient dpc = new DaytimeProtocolClient();
  }
  
  // new client, creates the window and responds to user input on event call
  public DaytimeProtocolClient(){
    
    // set up window attributes
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setTitle(TITLE);
    this.setVisible(true);
    Container frame = this.getContentPane();
    
    // set up window objects
    output = new JTextArea();
    frame.add(output,BorderLayout.CENTER);
    
    // set up container to store GUI elements
    JPanel lower = new JPanel();
    server = new JTextField(START_SERVER);
    server.setColumns(COLUMN_WIDTH);
    JButton refresh = new JButton("Get Date");
    refresh.addActionListener((ActionListener) this);
    refresh.setActionCommand("refresh");
    lower.add(server);
    lower.add(refresh);
    frame.add(lower,BorderLayout.SOUTH);
    this.validate();
  }
  
  // when clicking the button, refresh the date-time information
  public void actionPerformed(ActionEvent e){
    String inputServer = server.getText();
    try{
      time = new Socket(inputServer, 13);
      sendRequest();
      displayResponse();
      time.close();
    }catch(UnknownHostException ex){
      output.setText("Error: Unknown Host");
    }catch(IOException ex){
      output.setText("I/O Error");
    }
  }
  
  // how to send request to the server
  private void sendRequest() throws IOException{
    Writer toServer = new OutputStreamWriter(time.getOutputStream());
    toServer.write("GET /"+"\nr");
    toServer.flush();
  }
  
  // how to display the response from the server
  private void displayResponse() throws IOException{
    BufferedReader response = new BufferedReader(new InputStreamReader(time.getInputStream()));
    String temp = response.readLine();
    while(temp != null){
      output.setText(temp);
      temp = response.readLine();
    }
  }
}
