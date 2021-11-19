package facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface IUtilityFacade extends Remote {
	void exportFileToExcel(ResultSet res, String type, String filePath) throws RemoteException;
	String formatFileName(String type) throws RemoteException;
}