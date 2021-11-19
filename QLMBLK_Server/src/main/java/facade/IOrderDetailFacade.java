package facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.ChiTietHoaDon;

public interface IOrderDetailFacade extends Remote {
	List<String> getBillDetailByBillID(String maHD) throws RemoteException;

	boolean addNewBillDetail(ChiTietHoaDon cthd) throws RemoteException;
}