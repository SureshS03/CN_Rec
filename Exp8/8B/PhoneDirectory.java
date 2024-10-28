import java.util.*; 
import java.io.*; 

public class PhoneDirectory { 
  private Map<String, String> pbMap = new HashMap<>(); 

  public PhoneDirectory(String fileName) { 
    // Initial content of the phone book is read from the file  
    // storing lines of the form: name phone_number 
    try { 
      BufferedReader br = new BufferedReader(new FileReader(fileName)); 
      String line; 
      while ((line = br.readLine()) != null) { 
        String[] info = line.split(" +", 2); 
        pbMap.put(info[0], info[1]); 
      } 
    } catch (Exception exc) { 
      exc.printStackTrace(); 
      System.exit(1); 
    } 
  } 

  // Returns the phone number of the given person 
  public String getPhoneNumber(String name) { 
    return pbMap.get(name); 
  } 

  // Adds a new entry to the book 
  // The result: 
  // - true - the entry was added 
  // - false - the entry was not added  
  //           (the book associates another number with the given person) 
  public boolean addPhoneNumber(String name, String num) { 
    if (pbMap.containsKey(name)) return false; 
    pbMap.put(name, num); 
    return true; 
  } // <-- Add this missing closing brace to close addPhoneNumber method

  // Replaces the phone number of the given person 
  // The result: 
  // - true (success) 
  // - false (failure - the given person does not exist) 
  public boolean replacePhoneNumber(String name, String num) { 
    if (!pbMap.containsKey(name)) return false; 
    pbMap.put(name, num); 
    return true; 
  } 
} 
