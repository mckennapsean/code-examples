// inspired by Tony deLaubenfels
// serves to start a ping-able server over UDP

import java.io.*;
import java.net.*;
import java.util.*;

// server to process ping requests over UDP
public class UDPPingServer{
  
  // server settings
  private static final double LOSS_RATE = 0.3;
  private static final int AVERAGE_DELAY = 100;
  private static final int PORT = 1234;
  
  // main method to start up the server
  public static void main(String[] args) throws Exception{
    
    // create random number generatorm, simulating packet loss
    Random random = new Random();
    
    // datagram socket to send & receive UDP packets
    DatagramSocket socket = new DatagramSocket(PORT);
    
    // server loop
    while(true){
      
      // datagram packet to hold incoming data
      DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
      
      // block until the host receives a UDP packet.
      socket.receive(request);
      
      // print the recieved data.
      printData(request);
      
      // reply or simulate loss
      if(random.nextDouble() < LOSS_RATE){
        System.out.println("  Reply not sent.");
        continue;
      }
      
      // simulate network delay.
      Thread.sleep((int) (random.nextDouble() * 2 * AVERAGE_DELAY));
      
      // Send reply.
      InetAddress clientHost = request.getAddress();
      int clientPort = request.getPort();
      byte[] buf = request.getData();
      DatagramPacket reply = new DatagramPacket(buf, buf.length, clientHost, clientPort);
      socket.send(reply);
      
      System.out.println("  Reply sent.");
    }
  }
  
  // print PING data to output
  private static void printData(DatagramPacket request) throws Exception{
    
    // get size of request
    byte[] buf = request.getData();
    
    // wrap request into an input stream
    ByteArrayInputStream bais = new ByteArrayInputStream(buf);
    InputStreamReader isr = new InputStreamReader(bais);
    
    // turn the input stream into a buffered reader
    BufferedReader br = new BufferedReader(isr);
    
    // read the only line that contains the message
    String line = br.readLine();
    
    // print host address & associated data
    System.out.println("Received from " + request.getAddress().getHostAddress() + ": " + new String(line));
  }
}
