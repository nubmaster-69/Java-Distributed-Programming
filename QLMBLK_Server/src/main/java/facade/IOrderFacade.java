package facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.HoaDon;

public interface IOrderFacade extends Remote {
	List<HoaDon> getBills() throws RemoteException;

	String getLastBillID() throws RemoteException;

	boolean addNewBill(HoaDon hoaDon) throws RemoteException;
	
//	Double getTotalUnitPriceByDay(int day) throws RemoteException;

//	Double getTotalUnitPriceByYear(int year) throws RemoteException;
}