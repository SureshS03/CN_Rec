 import java.rmi.*;
 public interface myinterface extends Remote
 {
 public String countInput(String input)throws RemoteException;
 }