package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import entity.NhanVien;

public abstract class MainFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	protected JButton btnDonHang;
	protected JButton btnHoaDon;
	protected JButton btnKhachHang;
	protected JButton btnNhanVien;
	protected JButton btnSanPham;
	protected JButton btnThoat;
	protected JButton btnThongKe;
	
	protected JLabel jLabel1;
	protected JLabel jLabel2;
	
	private JScrollPane jScrollPane1;
	
	private JTextArea jTextArea1;
	
	private JPanel mainPanel;
	protected JPanel menuPanel;
	
	private NhanVien nhanVienLogin;

	public MainFrame(NhanVien nv) {
		nhanVienLogin = nv;
		initComponents();
		mainPanel.setLayout(new BorderLayout());
	}

	public MainFrame() {
		super();
	}

	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		jTextArea1 = new JTextArea();
		menuPanel = new JPanel();
		btnDonHang = new JButton();
		btnSanPham = new JButton();
		btnKhachHang = new JButton();
		btnHoaDon = new JButton();
		btnThongKe = new JButton();
		btnThoat = new JButton();
		btnNhanVien = new JButton();
		jLabel1 = new JLabel();
		mainPanel = new JPanel();
		jLabel2 = new JLabel();

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Ứng Dụng Quản Lý Mua Bán Linh Kiện");
		setResizable(false);

		menuPanel.setBackground(new Color(102, 153, 255));

		btnDonHang.setIcon(new ImageIcon(getClass().getResource("/img/shopping-cart.png")));
		btnDonHang.setText("Đơn Hàng");
		btnDonHang.setFont(new Font("Tahoma", 0, 15));
		btnDonHang.setBorderPainted(false);
		btnDonHang.setContentAreaFilled(false);
		btnDonHang.setFocusPainted(false);
		
		btnDonHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnDonHangActionPerformed(evt);
			}
		});

		btnSanPham.setIcon(new ImageIcon(getClass().getResource("/img/box.png")));
		btnSanPham.setText("Sản Phẩm");
		btnSanPham.setFont(new Font("Tahoma", 0, 15));
		btnSanPham.setBorderPainted(false);
		btnSanPham.setContentAreaFilled(false);
		btnSanPham.setFocusPainted(false);
		btnSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSanPhamActionPerformed(evt);
			}
		});

		btnKhachHang.setIcon(new ImageIcon(getClass().getResource("/img/multi-users.png")));
		btnKhachHang.setText("Khách Hàng");
		btnKhachHang.setFont(new Font("Tahoma", 0, 15));
		btnKhachHang.setBorderPainted(false);
		btnKhachHang.setContentAreaFilled(false);
		btnKhachHang.setFocusPainted(false);
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnKhachHangActionPerformed(evt);
			}
		});

		btnHoaDon.setIcon(new ImageIcon(getClass().getResource("/img/invoice.png")));
		btnHoaDon.setText("Hóa Đơn");
		btnHoaDon.setFont(new Font("Tahoma", 0, 15));
		btnHoaDon.setBorderPainted(false);
		btnHoaDon.setContentAreaFilled(false);
		btnHoaDon.setFocusPainted(false);
		btnHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnHoaDonActionPerformed(evt);
			}
		});

		btnThongKe.setIcon(new ImageIcon(getClass().getResource("/img/trend.png")));
		btnThongKe.setText("Thống Kê");
		btnThongKe.setFont(new Font("Tahoma", 0, 15));
		btnThongKe.setBorderPainted(false);
		btnThongKe.setContentAreaFilled(false);
		btnThongKe.setFocusPainted(false);
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnThongKeActionPerformed(evt);
			}
		});

		btnThoat.setIcon(new ImageIcon(getClass().getResource("/img/logout.png")));
		btnThoat.setText("Đăng Xuất");
		btnThoat.setFont(new Font("Tahoma", 0, 15));
		btnThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnThoat.setBorderPainted(false);
		btnThoat.setContentAreaFilled(false);
		btnThoat.setFocusPainted(false);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnThoatActionPerformed(evt);
			}
		});

		btnNhanVien.setIcon(new ImageIcon(getClass().getResource("/img/user.png")));
		btnNhanVien.setText("Nhân viên");
		btnNhanVien.setFont(new Font("Tahoma", 0, 15));
		btnNhanVien.setBorderPainted(false);
		btnNhanVien.setContentAreaFilled(false);
		btnNhanVien.setFocusPainted(false);
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnNhanVienActionPerformed(evt);
			}
		});

		jLabel1.setIcon(new ImageIcon(getClass().getResource("/img/Logo_IUH.png")));

		GroupLayout menuPanelLayout = new GroupLayout(menuPanel);
		menuPanel.setLayout(menuPanelLayout);
		menuPanelLayout.setHorizontalGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(btnDonHang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnSanPham, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnKhachHang, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
				.addComponent(btnHoaDon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnThongKe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnThoat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnNhanVien, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(GroupLayout.Alignment.TRAILING,
						menuPanelLayout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		menuPanelLayout.setVerticalGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(menuPanelLayout.createSequentialGroup()
						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addGap(1, 1, 1)
						.addComponent(btnDonHang, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnSanPham, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnKhachHang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnHoaDon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnNhanVien)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnThongKe, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnThoat)));

		btnDonHang.getAccessibleContext().setAccessibleDescription("");

		mainPanel.setBackground(new Color(255, 255, 255));

		jLabel2.setIcon(new ImageIcon(getClass().getResource("/img/welcome.jpg")));

		GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
						.addContainerGap(293, Short.MAX_VALUE).addComponent(jLabel2).addGap(230, 230, 230)));
		mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup().addGap(172, 172, 172).addComponent(jLabel2)
						.addContainerGap(187, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(menuPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(menuPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(mainPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
		setLocationRelativeTo(null);
	}

	private void btnDonHangActionPerformed(ActionEvent evt) {
		Object obj = evt.getSource();
		if (obj.equals(btnDonHang)) {
			btnDonHang.setContentAreaFilled(true);
			btnHoaDon.setContentAreaFilled(false);
			btnKhachHang.setContentAreaFilled(false);
			btnSanPham.setContentAreaFilled(false);
			btnThongKe.setContentAreaFilled(false);
			btnNhanVien.setContentAreaFilled(false);

			mainPanel.removeAll();
			PanelHangCoSan panelDS = new PanelHangCoSan(nhanVienLogin);
			mainPanel.add(panelDS, BorderLayout.CENTER);
			mainPanel.repaint();
			mainPanel.revalidate();
		}
	}

	private void btnSanPhamActionPerformed(ActionEvent evt) {
		btnDonHang.setContentAreaFilled(false);
		btnHoaDon.setContentAreaFilled(false);
		btnKhachHang.setContentAreaFilled(false);
		btnSanPham.setContentAreaFilled(true);
		btnThongKe.setContentAreaFilled(false);
		btnNhanVien.setContentAreaFilled(false);

		mainPanel.removeAll();
		PanelDanhSachSanPham panelDS = new PanelDanhSachSanPham();
		mainPanel.add(panelDS, BorderLayout.WEST);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	private void btnKhachHangActionPerformed(ActionEvent evt) {
		btnDonHang.setContentAreaFilled(false);
		btnHoaDon.setContentAreaFilled(false);
		btnKhachHang.setContentAreaFilled(true);
		btnSanPham.setContentAreaFilled(false);
		btnThongKe.setContentAreaFilled(false);
		btnNhanVien.setContentAreaFilled(false);

		mainPanel.removeAll();
		PanelDanhSachKhachHang panelDS = new PanelDanhSachKhachHang();
		mainPanel.add(panelDS, BorderLayout.CENTER);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	private void btnHoaDonActionPerformed(ActionEvent evt) {
		btnDonHang.setContentAreaFilled(false);
		btnHoaDon.setContentAreaFilled(true);
		btnKhachHang.setContentAreaFilled(false);
		btnSanPham.setContentAreaFilled(false);
		btnThongKe.setContentAreaFilled(false);
		btnNhanVien.setContentAreaFilled(false);

		mainPanel.removeAll();
		PanelDanhSachHoaDon panelDS = new PanelDanhSachHoaDon();
		mainPanel.add(panelDS, BorderLayout.CENTER);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	private void btnThoatActionPerformed(ActionEvent evt) {
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát?", "Đăng Xuất",
				JOptionPane.YES_NO_OPTION);
		if (confirm == 0) {
			this.dispose();
			new DangNhap().setVisible(true);
		}
	}

	private void btnThongKeActionPerformed(ActionEvent evt) {
		btnDonHang.setContentAreaFilled(false);
		btnHoaDon.setContentAreaFilled(false);
		btnKhachHang.setContentAreaFilled(false);
		btnSanPham.setContentAreaFilled(false);
		btnThongKe.setContentAreaFilled(true);
		btnNhanVien.setContentAreaFilled(false);

		mainPanel.removeAll();
		PanelThongKe panelTK = new PanelThongKe();
		mainPanel.add(panelTK, BorderLayout.CENTER);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	private void btnNhanVienActionPerformed(ActionEvent evt) {
		btnDonHang.setContentAreaFilled(false);
		btnHoaDon.setContentAreaFilled(false);
		btnKhachHang.setContentAreaFilled(false);
		btnSanPham.setContentAreaFilled(false);
		btnThongKe.setContentAreaFilled(false);
		btnNhanVien.setContentAreaFilled(true);

		mainPanel.removeAll();
		PanelDanhSachNhanVien panelNV = new PanelDanhSachNhanVien();
		mainPanel.add(panelNV, BorderLayout.CENTER);
		mainPanel.repaint();
		mainPanel.revalidate();
	}
}