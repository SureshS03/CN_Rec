 import java.io.*;
 import java.net.URL;
 import java.net.MalformedURLException;
 public class Server {
 public static void DownloadWebPage(String webpage)
 {
 try {
 URL url = new URL(webpage);
 BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream()));
 BufferedWriter writer = new BufferedWriter(new FileWriter("Data.html"));
 String line;
 while ((line = readr.readLine()) != null)
 {
 writer.write(line);
 }
 readr.close();
 writer.close();
 System.out.println("THIS PROGRAM IS EXECUTED BY SURESH S 221211101143");
 System.out.println("Successfully Downloaded.");
}
 catch (MalformedURLException mue)
 {
 System.out.println("Malformed URL Exception raised");
 }
 catch (IOException ie)
 {
 System.out.println("IOException raised");
 }
 }
 public static void main(String args[])
 throws IOException
 {
 String url ="https://en.wikipedia.org/wiki/Russian_invasion_of_Ukraine";
 DownloadWebPage(url);
 }
 }