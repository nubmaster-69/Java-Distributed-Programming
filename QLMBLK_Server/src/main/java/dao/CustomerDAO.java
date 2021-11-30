package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.apache.commons.collections4.map.HashedMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import connectDatabase.ConnectDatabase;
import entity.HoaDon;
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

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			kh = session.find(KhachHang.class, maKH);

			trans.commit();
			session.clear();
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
			try {
				kh = session.createQuery("from KhachHang where soDienThoaiKH = :phoneNumber", KhachHang.class)
						.setParameter("phoneNumber", soDT).getSingleResult();
			} catch (NoResultException  e) {
				trans.rollback();
				return null;
			}
			trans.commit();
		} catch (Exception e) {
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

	@Override
	public Map<KhachHang,List<HoaDon>> getCustomersPotential(LocalDate fromDate, LocalDate toDate) throws RemoteException{
		Map<KhachHang,List<HoaDon>> ListKhachHangTiemNang = new HashedMap<KhachHang,List<HoaDon>>();
		
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		int year = toDate.getYear() - fromDate.getYear();
		int sl = 2;
		if(year > 1) {
			sl+= year+1;
		}
		String sFromDate = fromDate.format(formatters);
		String stoDate = toDate.format(formatters);
		
		String query = "select kh.maKhachHang from KhachHang as kh \r\n"
				+ "join HoaDon as hd on kh.maKhachHang = hd.maKhachHang \r\n"
				+ "where hd.ngayLapHoaDon between '"+sFromDate+"' and '"+stoDate+"'\r\n"
				+ "group by kh.maKhachHang\r\n"
				+ "having COUNT( kh.maKhachHang ) >="+sl+"\r\n"
				+ "order by COUNT( kh.maKhachHang ) desc";	

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();
		try {
			trans.begin();

			List<String> listMa = (List<String>)session.createNativeQuery(query).getResultList();
			for (int i = 0 ; i< listMa.size(); i++) {
				String maKH = listMa.get(i);
				KhachHang kh = getCustomerByID(maKH);
				List<HoaDon> listHD = (new OrderDAO()).getBillsByMaKH(maKH, fromDate, toDate);
				ListKhachHangTiemNang.put(kh,listHD);
			}
			trans.commit();
			return ListKhachHangTiemNang;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	
		return null;
	}

}