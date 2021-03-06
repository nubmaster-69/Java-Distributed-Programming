package facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import entity.LinhKien;

public interface IComponentFacade extends Remote {
	List<LinhKien> getComponents() throws RemoteException;

	List<LinhKien> getSlowOnSaleComponents() throws RemoteException;

	LinhKien getComponentByID(String maLK) throws RemoteException;

	String getComponentDescription(String maLK) throws RemoteException;

	boolean addComponent(LinhKien lk) throws RemoteException;

	boolean removeComponentByID(String maLK) throws RemoteException;

	boolean updateComponentByID(LinhKien lk) throws RemoteException;
	
	String getLastLK() throws RemoteException;
	
	Map<LinhKien,Integer> getComponentsBestSelling(LocalDate fromDate, LocalDate toDate) throws RemoteException;
	
	Map<LinhKien,Integer> getComponentsWorstSelling(LocalDate fromDate, LocalDate toDate) throws RemoteException;
	
}