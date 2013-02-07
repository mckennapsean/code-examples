// by Sean McKenna on May 4th, 2010
// multi-threaded, parallel-processing web server

import java.io.*;
import java.net.*;
import java.util.*;

public final class WebServer{
  
  // main thread, start the server
  public static void main(String[] args)throws Exception{
    
    // set the port number to operate on
    int port = 3389;
    
    // establish the listen socket
    ServerSocket socket = new ServerSocket(port);
    
    // process HTTP requests (loop)
    while(true){
      
      // listen for TCP connection request
      Socket connection = socket.accept();
      
      // construct an object to process the HTTP request message
      HttpRequest request = new HttpRequest(connection);
      
      // create new thread to process the request
      Thread thread = new Thread(request);
      
      // start the thread created above
      thread.start();
    }
  }
}

// HTTP requests (how to process)
final class HttpRequest implements Runnable{
  final static String CRLF = "\r\n";
  Socket socket;
  
  // constructor
  public HttpRequest(Socket socket)throws Exception{
    this.socket = socket;
  }
  
  // implementation of run method for runnable interface
  public void run(){
    try{
      processRequest();
    }catch(Exception e){
      System.out.println(e);
    }
  }
  
  // how to process HTTP requests
  private void processRequest()throws Exception{
    
    // get reference to socket input and output streams
    InputStream is = socket.getInputStream();
    DataOutputStream os = new DataOutputStream(socket.getOutputStream());
    
    // set-up input stream filters
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    
    // get the request line of the HTTP request message
    String requestLine = br.readLine();
    
    // extract filename from the request line
    StringTokenizer tokens = new StringTokenizer(requestLine);
    tokens.nextToken();
    String filename = tokens.nextToken();
    
    // prepend '.' to get request in proper directory
    filename = '.' + filename;
    
    // open file
    FileInputStream fis = null;
    boolean fileExists = true;
    try{
      fis = new FileInputStream(filename);
    }catch(FileNotFoundException e){
      fileExists = false;
    }
    
    // display the request line
    System.out.println();
    System.out.println(requestLine);
    
    // get and display header lines
    String headerLine = null;
    while((headerLine = br.readLine()).length() != 0)
      System.out.println(headerLine);
    
    // construct response message
    String statusLine = null;
    String contentTypeLine = null;
    String entityBody = null;
    if(fileExists){
      statusLine = "HTTP/1.0 200 OK"+CRLF;
      contentTypeLine = "Content-type: "+contentType(filename) + CRLF;
    }else{
      statusLine = "HTTP/1.0 404 Not Found"+CRLF;
      contentTypeLine = "Content-type: text/html"+CRLF;
      entityBody = "<HTML>" + "<HEAD><TITLE>Not Found</TITLE></HEAD>" + "<BODY>Not Found</BODY></HTML>";
    }
    
    // send the status line
    os.writeBytes(statusLine);
    
    // send the content type line
    os.writeBytes(contentTypeLine);
    
    // send a blank line to indicate the end of a header line
    os.writeBytes(CRLF);
    
    // send entity body
    if(fileExists){
      sendBytes(fis,os);
      fis.close();
    }else{
      os.writeBytes(entityBody);
    }
    
    // close streams and socket
    os.close();
    br.close();
    socket.close();
  }
  
  // send HTTP request info back
  private static void sendBytes(FileInputStream fis,OutputStream os)throws Exception{
    
    // construct a 1K buffer to hold bytes on their way to socket
    byte[] buffer = new byte[1024];
    int bytes = 0;
    
    // copy requested file into the socket's output stream
    while((bytes = fis.read(buffer)) != -1)
      os.write(buffer,0,bytes);
  }
  
  // determine content type
  private static String contentType(String filename){
    if(filename.endsWith(".htm") || filename.endsWith(".html"))
      return "text/html";
    if(filename.endsWith(".ram") || filename.endsWith(".ra"))
      return "audio/x-pn-realaudio";
    if(filename.endsWith(".jpg"))
      return "image/jpg";
    return "application/octect-stream";
  }
}
