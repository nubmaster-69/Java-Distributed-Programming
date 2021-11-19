package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import connectDatabase.ConnectDatabase;
import entity.HoaDon;
import facade.IOrderFacade;

public class OrderDAO extends UnicastRemoteObject implements IOrderFacade {

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public OrderDAO() throws RemoteException {
		this.sessionFactory = ConnectDatabase.getInstance().getSessionFactory();
	}

	@Override
	public List<HoaDon> getBills() throws RemoteException {
		List<HoaDon> bills = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();

		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			bills = session.createNativeQuery("select * from HoaDon", HoaDon.class).getResultList();

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return bills;
	}

	@Override
	public String getLastBillID() throws RemoteException {
		String billID = "";
		Session session = sessionFactory.getCurrentSession();

		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			billID = session.createNativeQuery("select top 1 * from HoaDon order by maHoaDon desc", HoaDon.class)
					.uniqueResult().getMaHoaDon();

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return billID;
	}

	@Override
	public boolean addNewBill(HoaDon hoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.save(hoaDon);

			trans.commit();
			
			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return false;
	}

//  2 hàm bên dưới chưa chuyển đổi sang RMI
//	public boolean addNewBill(HoaDon hoaDon) throws SQLException {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Transaction trans = session.getTransaction();
//		
//		try {
//			trans.begin();
//			
//			session.save(hoaDon);
//			
//			trans.commit();
//		} catch (Exception e) {
//			trans.rollback();
//			e.printStackTrace();
//		}
//		
//		return false;
//	}

//	public Double getTotalUnitPriceByDay(int day) throws RemoteException {
//
//		String query = "select h.maHoaDon, sum(ct.soLuong*lk.donGia) from HoaDon as h join ChiTietHoaDon as ct"
//				+ "	on h.maHoaDon = ct.maHoaDon join LinhKien as lk on lk.maLinhKien = ct.maLinhKien"
//				+ "	where day(h.ngayLapHoaDon) = ?" + "	group by h.maHoaDon";
//
//		double totalPrice = 0;
//
//		PreparedStatement pState = con.prepareStatement(query);
//
//		pState.setInt(1, day);
//
//		ResultSet res = pState.executeQuery();
//
//		while (res.next())
//			totalPrice += res.getDouble(2);
//
//		return totalPrice;
//	}

//	public Double getTotalUnitPriceByYear(int year) throws RemoteException {
//
//		String query = "select h.maHoaDon, sum(ct.soLuong*lk.donGia) from HoaDon as h join ChiTietHoaDon as ct"
//				+ "	on h.maHoaDon = ct.maHoaDon join LinhKien as lk on lk.maLinhKien = ct.maLinhKien"
//				+ "	where year(h.ngayLapHoaDon) = ?" + "	group by h.maHoaDon";
//
//		double totalPrice = 0;
//
//		PreparedStatement pState = con.prepareStatement(query);
//
//		pState.setInt(1, year);
//
//		ResultSet res = pState.executeQuery();
//
//		while (res.next())
//			totalPrice += res.getDouble(2);
//
//		return totalPrice;
//	}
}