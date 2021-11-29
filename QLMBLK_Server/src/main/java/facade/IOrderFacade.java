package facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import entity.HoaDon;

public interface IOrderFacade extends Remote {
	List<HoaDon> getBills() throws RemoteException;

	String getLastBillID() throws RemoteException;

	boolean addNewBill(HoaDon hoaDon) throws RemoteException;

	HoaDon getBillsByID(String id) throws RemoteException;
	
	List<HoaDon> getBillsByMaKH(String id,LocalDate fromDate, LocalDate toDate) throws RemoteException;
	List<HoaDon> getBillsByDate(LocalDate fromDate, LocalDate toDate) throws RemoteException;
	
}