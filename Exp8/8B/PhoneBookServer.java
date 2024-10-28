import java.net.*;
import java.io.*;
import java.util.regex.*;

public class PhoneBookServer {
  private PhoneDirectory pd = null; // the map associating names and numbers
  private ServerSocket ss = null;
  private BufferedReader in = null; // socket streams
  private PrintWriter out = null; // for communicating with the client

  public PhoneBookServer(PhoneDirectory pd, ServerSocket ss) {
    this.pd = pd;
    this.ss = ss;
    System.out.println("This program is done by SURESH S 221211101143");
    System.out.println("Server started");
    System.out.println("on port: " + ss.getLocalPort());
    System.out.println("bind address: " + ss.getInetAddress());
    serviceConnections(); // listen to connections
  }

  // The method listens for connections made by clients.
  // After accepting a connection it creates a new Socket
  // and calls the method serviceRequest to handle the request
  private void serviceConnections() {
    boolean serverRunning = true; // the server is running continuously
    while (serverRunning) {
      try {
        Socket conn = ss.accept(); // listens and accepts connections
        System.out.println("Connection established");
        serviceRequests(conn);
      } catch (Exception exc) {
        exc.printStackTrace();
      }
    }
    // close the socket
    try {
      ss.close();
    } catch (Exception exc) {
      exc.printStackTrace();
    }
  }

  // the pattern for parsing requests
  private static Pattern reqPatt = Pattern.compile(" +", 3);
  
  // Server messages.
  // The corresponding codes are indices of the array.
  private static String msg[] = {
    "Ok", "Invalid request", "Not found",
    "Couldn't add - entry already exists",
    "Couldn't replace non-existing entry",
  };

  // Handling client's request
  private void serviceRequests(Socket connection) throws IOException {
    try {
      // create the streams
      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      out = new PrintWriter(connection.getOutputStream(), true);

      // Parse the request
      for (String line; (line = in.readLine()) != null;) {
        String resp;
        // answer
        String[] req = reqPatt.split(line, 3);
        String cmd = req[0];
        if (cmd.equals("bye")) {
          writeResp(0, null);
          break;
        }
        // command
        // end of message
        else if (cmd.equals("get")) { // get the number
          if (req.length != 2) writeResp(1, null);
          else {
            String phNum = (String) pd.getPhoneNumber(req[1]);
            if (phNum == null) writeResp(2, null);
            else writeResp(0, phNum);
          }
        } else if (cmd.equals("add")) { // add a number
          if (req.length != 3) writeResp(1, null);
          else {
            boolean added = pd.addPhoneNumber(req[1], req[2]);
            if (added) writeResp(0, null);
            else writeResp(3, null);
          }
        } else if (cmd.equals("replace")) { // change the number
          if (req.length != 3) writeResp(1, null);
          else {
            boolean replaced = pd.replacePhoneNumber(req[1], req[2]);
            if (replaced) writeResp(0, null);
            else writeResp(4, null);
          }
        } else writeResp(1, null); // invalid request
      }
    } catch (Exception exc) {
      exc.printStackTrace();
    } finally {
      // close the streams and the socket
      try {
        in.close();
        out.close();
        connection.close();
      } catch (Exception exc) {
        exc.printStackTrace();
      }
    }
  }

  // Pass the response to the client
  private void writeResp(int rc, String addMsg) throws IOException {
    out.println(rc + " " + msg[rc]);
    if (addMsg != null) out.println(addMsg);
  }

  public static void main(String[] args) {
    PhoneDirectory pd = null;
    ServerSocket ss = null;
    try {
      String phdFileName = args[0];
      String host = args[1];
      int port = Integer.parseInt(args[2]);
      pd = new PhoneDirectory(phdFileName); // create the map (read data from the file)
      InetSocketAddress isa = new InetSocketAddress(host, port);
      ss = new ServerSocket();
      ss.bind(isa);
    } catch (Exception exc) {
      exc.printStackTrace();
      System.exit(1);
    }
    new PhoneBookServer(pd, ss);
  }
}
