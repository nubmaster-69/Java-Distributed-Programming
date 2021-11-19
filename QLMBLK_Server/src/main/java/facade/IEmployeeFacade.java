package facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhanVien;

public interface IEmployeeFacade extends Remote{
	List<NhanVien> getEmployees() throws RemoteException;
	NhanVien getEmployeeByID(String maNhanVien) throws RemoteException;
	String getEmployeeRoleByID(String maNV) throws RemoteException;
	boolean addEmployee(NhanVien nv) throws RemoteException;
	boolean removeEmployeeByID(String maNhanVien) throws RemoteException;
	boolean updateEmployeeInfo(NhanVien nv) throws RemoteException;
}