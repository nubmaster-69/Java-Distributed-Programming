package facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import entity.HoaDon;
import entity.KhachHang;

public interface ICustomerFacade extends Remote {
	List<KhachHang> getCustomers() throws RemoteException;

	KhachHang getCustomerByID(String maKH) throws RemoteException;

	String getLastCustomerID() throws RemoteException;

	KhachHang getCustomerByPhoneNumer(String soDT) throws RemoteException;

	int customerQuantity() throws RemoteException;

	boolean addCustomer(KhachHang kh) throws RemoteException;

	boolean removeCustomerByID(String maKH) throws RemoteException;

	boolean updateCustomerInfo(KhachHang kh) throws RemoteException;
	
	Map<KhachHang,List<HoaDon>> getCustomersPotential(LocalDate fromDate, LocalDate toDate) throws RemoteException;
}