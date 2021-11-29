package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import dao.ComponentDAO;
import dao.CustomerDAO;
import dao.EmployeeDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.Utility;
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
			
			Naming.bind(String.format("rmi://localhost:%d/employeeFacade", PORT), employeeFacade);
			Naming.bind(String.format("rmi://localhost:%d/componentFacade", PORT), componentFacade);
			Naming.bind(String.format("rmi://localhost:%d/customerFacade", PORT), customerFacade);
			Naming.bind(String.format("rmi://localhost:%d/orderFacade", PORT), orderFacade);
			Naming.bind(String.format("rmi://localhost:%d/orderDetailFacade", PORT), orderDetailFacade);
			Naming.bind(String.format("rmi://localhost:%d/utilityFacade", PORT), utilityFacade);
			
			System.out.printf("Server bound and started at port %d!", PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}