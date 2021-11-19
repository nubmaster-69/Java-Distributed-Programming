package gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entity.NhanVien;
import facade.IEmployeeFacade;

public class DangNhap extends javax.swing.JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnDangNhap;
	private JButton jButton2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPasswordField txtMatKhau;
	private JTextField txtTenDangNhap;
	IEmployeeFacade employeeFacade;

	public DangNhap() {
		initComponents();

		try {
			employeeFacade = (IEmployeeFacade) Naming.lookup("rmi://localhost:1341/employeeFacade");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		jLabel1 = new JLabel();
		jLabel2 = new JLabel();

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));

		jLabel3 = new JLabel();

		txtMatKhau = new JPasswordField();
		txtMatKhau.setEchoChar('•');
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnDangNhap = new JButton();
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDangNhap.setFocusable(false);
		btnDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));

		jButton2 = new JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Ứng dụng mua bán linh kiện");
		setResizable(false);

		jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Đăng Nhập");

		jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLabel2.setText("Tên đăng nhập: ");

		jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLabel3.setText("Mật khẩu: ");

		btnDangNhap.setText("Đăng nhập");

		btnDangNhap.addActionListener(this);
		jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logo_IUH.png"))); // NOI18N
		jButton2.setBorderPainted(false);
		jButton2.setContentAreaFilled(false);
		jButton2.setFocusable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(38, 38, 38)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jButton2))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(txtTenDangNhap, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
										.addComponent(txtMatKhau, javax.swing.GroupLayout.Alignment.LEADING))
								.addGap(0, 38, Short.MAX_VALUE))))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDangNhap).addGap(145, 145, 145)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(jButton2).addGap(25, 25, 25)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20).addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(jLabel3)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(32, 32, 32).addComponent(btnDangNhap).addContainerGap(33, Short.MAX_VALUE)));

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