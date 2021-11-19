package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import connectDatabase.ConnectDatabase;
import entity.LinhKien;
import facade.IComponentFacade;

public class ComponentDAO extends UnicastRemoteObject implements IComponentFacade {

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public ComponentDAO() throws RemoteException {
		this.sessionFactory = ConnectDatabase.getInstance().getSessionFactory();
	}

	@Override
	public List<LinhKien> getComponents() throws RemoteException {
		List<LinhKien> comps = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			comps = session.createNativeQuery("select * from LinhKien", LinhKien.class).getResultList();

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return comps;
	}

	@Override
	public List<LinhKien> getSlowOnSaleComponents() throws RemoteException {
		List<LinhKien> comps = new ArrayList<>();

		String query = "select * from LinhKien "
				+ "where maLinhKien not in (select maLinhKien from ChiTietHoaDon) order by ngayNhap";

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			comps = session.createNativeQuery(query, LinhKien.class).getResultList();

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return comps;
	}

	@Override
	public LinhKien getComponentByID(String maLK) throws RemoteException {
		LinhKien lk = null;

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			lk = session.find(LinhKien.class, maLK);

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return lk;
	}

	@Override
	public String getComponentDescription(String maLK) throws RemoteException {
		String moTa = "";

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			moTa = session.find(LinhKien.class, maLK).getMoTa();

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return moTa;
	}

	@Override
	public boolean addComponent(LinhKien lk) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.save(lk);

			trans.commit();

			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean removeComponentByID(String maLK) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.delete(session.find(LinhKien.class, maLK));

			trans.commit();

			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateComponentByID(LinhKien lk) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.update(lk);

			trans.commit();

			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return false;
	}
}