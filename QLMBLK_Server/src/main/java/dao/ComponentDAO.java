package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
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

		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();

		try {
			trans.begin();

			lk = session.find(LinhKien.class, maLK);

			trans.commit();
			session.clear();
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

	@Override
	public String getLastLK() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maLinhKien from LinhKien order by maLinhKien desc";
        try {
            tr.begin();
            String maKhachCuoi="";
            String maCuoiCung = "LK";
            try {
                maKhachCuoi = (String)session.createNativeQuery(sql).uniqueResult();
                int so = Integer.parseInt(maKhachCuoi.split("LK")[1]) + 1;
                int soChuSo = String.valueOf(so).length();
                
                for (int i = 0; i< 6 - soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
            } catch (Exception e) {
                maCuoiCung = "LK000001";
            } 
            tr.commit();
            return maCuoiCung;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
	}

	@Override
	public Map<LinhKien,Integer> getComponentsBestSelling(LocalDate fromDate, LocalDate toDate) throws RemoteException {
		Map<LinhKien,Integer> ListLinhKienDaBan = new HashedMap<LinhKien,Integer>();
		
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM-dd-yyyy");

		String sFromDate = fromDate.format(formatters);
		String stoDate = toDate.format(formatters);
		
		System.out.println(sFromDate + "    " + stoDate);
		
		String query = "select lk.maLinhKien,SUM(cthd.soLuong) from ChiTietHoaDon as cthd \r\n"
				+ "join HoaDon as hd on cthd.maHoaDon = hd.maHoaDon \r\n"
				+ "join LinhKien as lk on cthd.maLinhKien = lk.maLinhKien \r\n"
				+ "where hd.ngayLapHoaDon between '"+sFromDate+"' and '"+stoDate+"'\r\n"
				+ "group by lk.maLinhKien\r\n"
				+ "having SUM(cthd.soLuong) >= 4";

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();
		try {
			trans.begin();

			List<?> list = session.createNativeQuery(query).getResultList();
			Iterator<?> i = list.iterator();
			while (i.hasNext()) {
				Object[] row = (Object[]) i.next();
				LinhKien lk = getComponentByID(row[0].toString());
				ListLinhKienDaBan.put(lk, (int)row[1]);
			}
			trans.commit();
			return ListLinhKienDaBan;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	
		return null;
	}
	
	@Override
	public Map<LinhKien,Integer> getComponentsWorstSelling(LocalDate fromDate, LocalDate toDate) throws RemoteException {
		Map<LinhKien,Integer> ListLinhKienDaBan = new HashedMap<LinhKien,Integer>();
		
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM-dd-yyyy");

		String sFromDate = fromDate.format(formatters);
		String stoDate = toDate.format(formatters);
		
		String query = "select lk.maLinhKien,sum(ct.soLuong) from LinhKien as lk\r\n"
				+ "left join ChiTietHoaDon as ct on lk.maLinhKien = ct.maLinhKien \r\n"
				+ "left join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\r\n"
				+ "where ngayLapHoaDon between '"+sFromDate+"' and '"+stoDate+"' or ngayLapHoaDon is null\r\n"
				+ "group by lk.maLinhKien\r\n"
				+ "having sum(ct.soLuong) <= 2 or sum(ct.soLuong) is null";

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.getTransaction();
		try {
			trans.begin();

			List<?> list = session.createNativeQuery(query).getResultList();
			Iterator<?> i = list.iterator();
			while (i.hasNext()) {
				Object[] row = (Object[]) i.next();
				LinhKien lk = getComponentByID(row[0].toString());
				ListLinhKienDaBan.put(lk, row[1] == null ? 0 : (int)row[1]);
			}
			trans.commit();
			return ListLinhKienDaBan;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	
		return null;
	}
//	
}