package gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import entity.NhanVien;
import facade.IEmployeeFacade;

public class DangNhap extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnDangNhap;
	private JButton jButton2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPasswordField txtMatKhau;
	private JTextField txtTenDangNhap;
	private IEmployeeFacade employeeFacade;

	public DangNhap() {
		try {
			employeeFacade = (IEmployeeFacade) Naming.lookup("rmi://localhost:1341/employeeFacade");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		initComponents();
	}

	private void initComponents() {

		jLabel1 = new JLabel();
		jLabel2 = new JLabel();

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setFont(new Font("SansSerif", Font.PLAIN, 16));

		jLabel3 = new JLabel();

		txtMatKhau = new JPasswordField();
		txtMatKhau.setEchoChar('•');
		txtMatKhau.setFont(new Font("SansSerif", Font.PLAIN, 16));

		btnDangNhap = new JButton();
		btnDangNhap.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnDangNhap.setFocusable(false);
		btnDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));

		jButton2 = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Ứng dụng mua bán linh kiện");
		setResizable(false);

		jLabel1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Chào Mừng Bạn Đã Quay Trở Lại");

		jLabel2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		jLabel2.setText("Tên đăng nhập: ");

		jLabel3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		jLabel3.setText("Mật khẩu: ");

		btnDangNhap.setText("Đăng nhập");

		btnDangNhap.addActionListener(this);
		jButton2.setIcon(new ImageIcon(getClass().getResource("/img/Logo_IUH.png")));
		jButton2.setBorderPainted(false);
		jButton2.setContentAreaFilled(false);
		jButton2.setFocusable(false);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(60, 60, 60)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
								.addComponent(jLabel3).addComponent(jLabel2).addComponent(txtMatKhau)
								.addComponent(txtTenDangNhap))
						.addContainerGap(60, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jButton2, GroupLayout.Alignment.TRAILING)
								.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addComponent(btnDangNhap).addGap(184, 184, 184)))));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(jButton2).addGap(74, 74, 74)
						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 39,
								GroupLayout.PREFERRED_SIZE)
						.addGap(30, 30, 30).addComponent(jLabel2)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtTenDangNhap, GroupLayout.PREFERRED_SIZE, 40,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
						.addComponent(jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtMatKhau, GroupLayout.PREFERRED_SIZE, 40,
								GroupLayout.PREFERRED_SIZE)
						.addGap(50, 50, 50).addComponent(btnDangNhap).addGap(44, 44, 44)));

		pack();

		txtTenDangNhap.setText("19521341");
		txtMatKhau.setText("admin");

		setLocationRelativeTo(null);
	}

	private void btnDangNhapActionPerformed() {
		if (validateAccount()) {
			String userRole;
			try {
				userRole = employeeFacade.getEmployeeRoleByID(txtTenDangNhap.getText().trim());
				NhanVien nv = employeeFacade.getEmployeeByID(txtTenDangNhap.getText().trim());

				if (userRole.equalsIgnoreCase("Quản Lý"))
					new AdminFrame(nv).setVisible(true);
				else
					new EmployeeFrame(nv).setVisible(true);

				this.dispose();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean validateAccount() {

		String userName = txtTenDangNhap.getText().trim();
		@SuppressWarnings("deprecation")
		String pass = txtMatKhau.getText().trim();

		NhanVien nv = null;
		try {
			nv = employeeFacade.getEmployeeByID(userName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (userName.isEmpty()) {
			showMsg("Tên đăng nhập không được bỏ trống!");
			return false;
		} else if (nv == null) {
			showMsg("Tên đăng nhập không tồn tại!");
			return false;
		}

		if (pass.isEmpty()) {
			showMsg("Mật khẩu không được bỏ trống!");
			return false;
		} else if (!pass.equals(nv.getMatKhau())) {
			showMsg("Mật khẩu không chính xác!");
			return false;
		}

		return true;
	}

	private void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnDangNhap))
			btnDangNhapActionPerformed();
	}
}