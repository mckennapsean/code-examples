/** by Sean McKenna on February 8th, 2010
 *  Last modified on February 9th, 2010
 *
 *  This program connects to a server via a Socket to get current information
 *  regarding the Internet Daytime Protocol (using port 13).  A GUI is provided
 *  for typing in a custom URL and to refresh with a button.  Sometimes it takes
 *  a little while to connect or you have to wait before refreshing.
 *
 *  This program is a solution to exercise 19.8.3 in Java: An Eventful Approach.
 */

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

public class DaytimeProtocolClient extends JFrame implements ActionListener{
    private static final int WINDOW_WIDTH = 550;
    private static final int WINDOW_HEIGHT = 125;
    private static final int COLUMN_WIDTH = WINDOW_WIDTH / 20;
    private static final String TITLE = "Daytime Protocol Client";
    private static final String START_SERVER = "time.nist.gov";
    private JTextField server;
    private JTextArea output;
    public Socket time;

    public static void main(String[] args) {
	DaytimeProtocolClient dpc = new DaytimeProtocolClient();
    }

    public DaytimeProtocolClient(){
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
	this.setTitle(TITLE);
	this.setVisible(true);
	Container frame = this.getContentPane();

	output = new JTextArea();
	frame.add(output,BorderLayout.CENTER);

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

    public void actionPerformed(ActionEvent e){
	String inputServer = server.getText();
	try {
	    time = new Socket(inputServer, 13);
	    sendRequest();
	    displayResponse();
	    time.close();
	} catch (UnknownHostException ex) {
	    output.setText("Error: Unknown Host");
	} catch (IOException ex) {
	    output.setText("I/O Error");
	}
    }

    private void sendRequest() throws IOException{
	Writer toServer = new OutputStreamWriter(time.getOutputStream());
	toServer.write("GET /"+"\nr");
	toServer.flush();
    }

    private void displayResponse() throws IOException{
	BufferedReader response = new BufferedReader(new InputStreamReader(
		time.getInputStream()));
	String temp = response.readLine();
	while(temp != null){
	    output.setText(temp);
	    temp = response.readLine();
	}
	
    }
}
