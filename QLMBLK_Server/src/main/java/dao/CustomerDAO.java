package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import connectDatabase.ConnectDatabase;
import entity.KhachHang;
import facade.ICustomerFacade;

public class CustomerDAO extends UnicastRemoteObject implements ICustomerFacade {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public CustomerDAO() throws RemoteException {
		this.sessionFactory = ConnectDatabase.getInstance().getSessionFactory();
	}

	@Override
	public List<KhachHang> getCustomers() throws RemoteException {
		List<KhachHang> customers = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			customers = session.createNativeQuery("select * from KhachHang", KhachHang.class).getResultList();

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return customers;
	}

	@Override
	public KhachHang getCustomerByID(String maKH) throws RemoteException {
		KhachHang kh = null;

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			kh = session.find(KhachHang.class, maKH);

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return kh;
	}

	@Override
	public String getLastCustomerID() throws RemoteException {
		String cusID = "";

		Session session = sessionFactory.getCurrentSession();

		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			cusID = session
					.createNativeQuery("select top 1 * from KhachHang order by maKhachHang desc", KhachHang.class)
					.uniqueResult().getMaKhachHang();

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return cusID;
	}

	@Override
	public KhachHang getCustomerByPhoneNumer(String soDT) throws RemoteException {
		KhachHang kh = null;

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			kh = session.createQuery("from KhachHang where soDienThoaiKH = :phoneNumber", KhachHang.class)
					.setParameter("phoneNumber", soDT).getSingleResult();

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}

		return kh;
	}

	@Override
	public int customerQuantity() throws RemoteException {
		return getCustomers().size();
	}

	@Override
	public boolean addCustomer(KhachHang kh) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.save(kh);

			trans.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}

		return false;
	}

	@Override
	public boolean removeCustomerByID(String maKH) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.delete(session.find(KhachHang.class, maKH));

			trans.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}

		return false;
	}

	@Override
	public boolean updateCustomerInfo(KhachHang kh) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.update(kh);

			trans.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}

		return false;
	}
}