package connectDatabase;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonPK;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

public class ConnectDatabase {
	private static ConnectDatabase instance = null;
	private SessionFactory sessionFactory;
	
	private ConnectDatabase() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		
		Metadata metadata = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(NhanVien.class)
				.addAnnotatedClass(KhachHang.class)
				.addAnnotatedClass(LinhKien.class)
				.addAnnotatedClass(HoaDon.class)
				.addAnnotatedClass(ChiTietHoaDon.class)
				.addAnnotatedClass(ChiTietHoaDonPK.class)
				.getMetadataBuilder().build();
		
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}
	
	public static ConnectDatabase getInstance() {
		if (instance == null) {
			instance = new ConnectDatabase();
		}
		return instance;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void closeSessionFactory() {
		sessionFactory.close();
	}
}