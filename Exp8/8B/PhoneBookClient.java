 import java.net.*;
 import java.io.*;
 public class PhoneBookClient
 {
 private Socket sock = null;
 private PrintWriter out = null;
 private BufferedReader in = null;
 public PhoneBookClient(String host, int port)
 { try {
 sock = new Socket(host, port);
 out = new PrintWriter(sock.getOutputStream(), true);
 in = new BufferedReader(
 new
 InputStreamReader( sock.getInputStream()));
 System.out.println("This Program is done by SURESH S 221211101143"); makeRequest("get Kermit");
 makeRequest("get Piggy");
 makeRequest("add Gonzo 77777");
 makeRequest("add Gonzo");
 makeRequest("get Gonzo");
 makeRequest("add Gonzo333333");
makeRequest("replace Gonzo 333333");
 makeRequest("replace Piggy 202020");
 makeRequest("get Gonzo");
 makeRequest("add"); makeRequest("");
 makeRequest("bye");
 in.close();
 out.close();
 sock.close();
 } catch (UnknownHostException e)
 {
 System.err.println("Unknown host: "+host);
 System.exit(2);
 } catch (IOException e)
 { System.err.println("I/O error for");
 System.exit(3);
 }
 catch (Exception exc)
 { exc.printStackTrace();
 System.exit(4);
 }
 }
private boolean makeRequest(String req) throws IOException
 { System.out.println("Request: " + req);
 out.println(req);
 String resp = in.readLine();
 System.out.println(resp);
 boolean ok =resp.startsWith("0");
 if (req.startsWith("get") && ok)
 System.out.println(in.readLine());
 return ok;
 }
 public static void main(String[] args) {
 new PhoneBookClient(args[0], Integer.parseInt(args[1]));
 }
 }