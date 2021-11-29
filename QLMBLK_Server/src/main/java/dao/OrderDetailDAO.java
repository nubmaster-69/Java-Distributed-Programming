package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import connectDatabase.ConnectDatabase;
import entity.ChiTietHoaDon;
import facade.IOrderDetailFacade;

public class OrderDetailDAO extends UnicastRemoteObject implements IOrderDetailFacade {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public OrderDetailDAO() throws RemoteException {
		this.sessionFactory = ConnectDatabase.getInstance().getSessionFactory();
	}

	@Override
	public List<String> getBillDetailByBillID(String maHD) throws RemoteException {
		List<String> cthds = new ArrayList<>();
		String query = "select kh.hoTenKH, kh.maKhachHang, kh.diaChiKH, kh.soDienThoaiKH, lk.maLinhKien, lk.tenLinhKien,"
				+ "lk.loaiLinhKien, lk.thuongHieu, cthd.soLuong, lk.donGia from ChiTietHoaDon cthd "
				+ "join HoaDon hd on hd.maHoaDon = cthd.maHoaDon "
				+ "join LinhKien lk on lk.maLinhKien = cthd.maLinhKien "
				+ "join KhachHang kh on hd.maKhachHang = kh.maKhachHang " + "where hd.maHoaDon = :maHoaDon";

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			List<?> list = session.createQuery(query).setParameter("maHoaDon", maHD).list();

			Iterator<?> i = list.iterator();

			while (i.hasNext()) {
				Object[] row = (Object[]) i.next();
				cthds.add(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", row[0], row[1], row[2], row[3], row[4], row[5],
						row[6], row[7], row[8], row[9]));
			}

			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return cthds;
	}

	@Override
	public boolean addNewBillDetail(ChiTietHoaDon cthd) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			session.save(cthd);

			trans.commit();
			
			return true;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}

		return false;
	}
	
}