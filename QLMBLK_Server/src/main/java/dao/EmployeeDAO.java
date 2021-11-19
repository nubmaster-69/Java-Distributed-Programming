package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import connectDatabase.ConnectDatabase;
import entity.NhanVien;
import facade.IEmployeeFacade;

public class EmployeeDAO extends UnicastRemoteObject implements IEmployeeFacade {

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public EmployeeDAO() throws RemoteException {
		this.sessionFactory = ConnectDatabase.getInstance().getSessionFactory();
	}

	@Override
	public List<NhanVien> getEmployees() throws RemoteException {
		List<NhanVien> emps = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			emps = session.createNativeQuery("select * from NhanVien", NhanVien.class).getResultList();

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return emps;
	}

	@Override
	public NhanVien getEmployeeByID(String maNhanVien) throws RemoteException {
		NhanVien nv = null;

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			nv = session.find(NhanVien.class, maNhanVien);

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return nv;
	}

	@Override
	public String getEmployeeRoleByID(String maNV) throws RemoteException {
		String role = "";

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			role = session.find(NhanVien.class, maNV).getquyenTruyCap();

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return role;
	}

	@Override
	public boolean addEmployee(NhanVien nv) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.save(nv);

			trans.commit();

			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean removeEmployeeByID(String maNhanVien) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.delete(session.find(NhanVien.class, maNhanVien));

			trans.commit();

			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateEmployeeInfo(NhanVien nv) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.update(nv);

			trans.commit();

			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return false;
	}
}