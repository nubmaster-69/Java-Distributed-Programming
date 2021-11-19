package main;

import java.awt.EventQueue;
import javax.swing.UIManager;

import gui.DangNhap;

public class MainProgram {
	public static void main(String[] args) {

		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		EventQueue.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			new DangNhap().setVisible(true);
		});
	}
}