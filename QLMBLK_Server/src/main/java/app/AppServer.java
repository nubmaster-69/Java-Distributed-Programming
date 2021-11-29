package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import dao.ComponentDAO;
import dao.CustomerDAO;
import dao.EmployeeDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.Utility;
import entity.HoaDon;
import entity.KhachHang;
import facade.IComponentFacade;
import facade.ICustomerFacade;
import facade.IEmployeeFacade;
import facade.IOrderDetailFacade;
import facade.IOrderFacade;
import facade.IUtilityFacade;

public class AppServer {
	
	private static final int PORT = 1341;
	
	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			LocateRegistry.createRegistry(PORT);
			
			IEmployeeFacade employeeFacade = new EmployeeDAO();
			IComponentFacade componentFacade = new ComponentDAO();
			ICustomerFacade customerFacade = new CustomerDAO();
			IOrderFacade orderFacade = new OrderDAO();
			IOrderDetailFacade orderDetailFacade = new OrderDetailDAO();
			IUtilityFacade utilityFacade = new Utility();
			
//			Map<KhachHang,List<HoaDon>> map = customerFacade.getCustomersPotential(LocalDate.of(2012, 1, 1), LocalDate.now());
//			
//			for (Map.Entry<KhachHang,List<HoaDon>> entry : map.entrySet()) {
//				KhachHang kh = entry.getKey();
//				List<HoaDon> listHD= entry.getValue();
//				listHD.forEach(doc -> {System.out.println(doc.getChiTietHoaDon());
//					System.out.println(doc.getMaHoaDon());
//				});
//				System.out.println("---");
//			}
			
			Naming.bind("rmi://localhost:1341/employeeFacade", employeeFacade);
			Naming.bind("rmi://localhost:1341/componentFacade", componentFacade);
			Naming.bind("rmi://localhost:1341/customerFacade", customerFacade);
			Naming.bind("rmi://localhost:1341/orderFacade", orderFacade);
			Naming.bind("rmi://localhost:1341/orderDetailFacade", orderDetailFacade);
			Naming.bind("rmi://localhost:1341/utilityFacade", utilityFacade);
			
			System.out.printf("Server bound and started at port %d! Ready to response!", PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}