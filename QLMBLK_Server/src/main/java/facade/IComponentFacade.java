package facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.LinhKien;

public interface IComponentFacade extends Remote {
	List<LinhKien> getComponents() throws RemoteException;

	List<LinhKien> getSlowOnSaleComponents() throws RemoteException;

	LinhKien getComponentByID(String maLK) throws RemoteException;

	String getComponentDescription(String maLK) throws RemoteException;

	boolean addComponent(LinhKien lk) throws RemoteException;

	boolean removeComponentByID(String maLK) throws RemoteException;

	boolean updateComponentByID(LinhKien lk) throws RemoteException;
}