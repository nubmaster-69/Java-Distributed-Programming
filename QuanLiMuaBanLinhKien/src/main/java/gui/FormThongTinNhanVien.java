package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import entity.NhanVien;

public class FormThongTinNhanVien extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int FORM_CAP_NHAT = 0;
	public static final int FORM_XEM_THONG_TIN = 1;

	private JComboBox<String> cbxChucVu;
	private JComboBox<String> cbxGioiTinh;

	private DatePicker dpNgaySinh;

	private JButton jButton1;
	private JButton btnUpdate;

	private JLabel lblChucVu;
	private JLabel lblDiaChi;
	private JLabel lblGioiTinh;
	private JLabel lblHoTen;
	private JLabel lblNgaySinh;
	private JLabel lblSoDT;
	private JLabel lblTenNV;
	private JLabel lblTieuDe;
	private JLabel lblMatKhau;

	private JTextField txtDiaChi;
	private JTextField txtHoTen;
	private JTextField txtMaNV;
	private JTextField txtSoDT;

	private JPasswordField pfMatKhau;

	private NhanVien nhanVien;
	private int cheDo = 0;

	private String btnExitColor = "#e74c3c";
	private String btnUpdateColor = "#3498db";

	public FormThongTinNhanVien(int formType, NhanVien nv) {
		nhanVien = nv;
		cheDo = formType;

		initComponents();
		if (formType == FORM_XEM_THONG_TIN) {
			jButton1.setText("Thoát");
			jButton1.setName("Thoat");
			jButton1.setBackground(Color.decode(btnExitColor));
		} else {
			jButton1.setText("Cập Nhật");
			jButton1.setName("CapNhat");
			jButton1.setBackground(Color.decode(btnUpdateColor));
		}
	}

	private void initComponents() {

		lblTieuDe = new JLabel();
		txtMaNV = new JTextField();
		lblTenNV = new JLabel();
		lblHoTen = new JLabel();
		txtHoTen = new JTextField();
		lblGioiTinh = new JLabel();
		lblNgaySinh = new JLabel();
		lblSoDT = new JLabel();
		txtSoDT = new JTextField();
		lblChucVu = new JLabel();
		txtDiaChi = new JTextField();

		jButton1 = new JButton();
		jButton1.setFocusable(false);
		jButton1.setBorderPainted(false);
		jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jButton1.setOpaque(true);
		jButton1.setForeground(Color.white);

		lblDiaChi = new JLabel();
		cbxGioiTinh = new JComboBox<>();
		cbxChucVu = new JComboBox<>();

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFontValidDate(new Font("SansSerif", 0, 16));
		dateSettings.setFormatForDatesCommonEra("dd-MM-yyyy");
		dateSettings.setAllowKeyboardEditing(false);
		dpNgaySinh = new DatePicker(dateSettings);
		dpNgaySinh.setFocusable(false);
		dpNgaySinh.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lblMatKhau = new JLabel();
		pfMatKhau = new JPasswordField();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(new Color(102, 255, 255));

		lblTieuDe.setFont(new Font("SansSerif", 0, 24));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setText("Thông Tin Nhân Viên");

		txtMaNV.setFont(new Font("SansSerif", 0, 16));
		txtMaNV.setText("jTextField1");

		lblTenNV.setFont(new Font("SansSerif", 0, 16));
		lblTenNV.setText("Mã nhân viên");

		lblHoTen.setFont(new Font("SansSerif", 0, 16));
		lblHoTen.setText("Họ tên nhân viên");

		lblMatKhau.setFont(new Font("SansSerif", 0, 16));
		lblMatKhau.setText("Mật Khẩu");

		pfMatKhau.setFont(new Font("SansSerif", 0, 16));

		txtHoTen.setFont(new Font("SansSerif", 0, 16));
		txtHoTen.setText("jTextField1");

		lblGioiTinh.setFont(new Font("SansSerif", 0, 16));
		lblGioiTinh.setText("Giới tính");

		lblNgaySinh.setFont(new Font("SansSerif", 0, 16));
		lblNgaySinh.setText("Ngày sinh");

		lblSoDT.setFont(new Font("SansSerif", 0, 16));
		lblSoDT.setText("Số điện thoại");

		txtSoDT.setFont(new Font("SansSerif", 0, 16));
		txtSoDT.setText("jTextField1");

		lblChucVu.setFont(new Font("SansSerif", 0, 16));
		lblChucVu.setText("Chức vụ");

		txtDiaChi.setFont(new Font("SansSerif", 0, 16));
		txtDiaChi.setText("jTextField1");

		jButton1.setFont(new Font("SansSerif", 0, 16));
		jButton1.setText("Thoát");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		lblDiaChi.setFont(new Font("SansSerif", 0, 16));
		lblDiaChi.setText("Địa chỉ");

		cbxGioiTinh.setFont(new Font("SansSerif", 0, 16));
		cbxGioiTinh.setModel(new DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
		cbxGioiTinh.setFocusable(false);
		cbxGioiTinh.setCursor(new Cursor(Cursor.HAND_CURSOR));

		cbxChucVu.setFont(new Font("SansSerif", 0, 16));
		cbxChucVu.setModel(new DefaultComboBoxModel<>(new String[] { "Nhân Viên", "Quản Lý" }));
		cbxChucVu.setFocusable(false);
		cbxChucVu.setCursor(new Cursor(Cursor.HAND_CURSOR));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout
										.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(100, 100, 100)
												.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 283,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addGap(160, 160, 160).addComponent(
												jButton1, GroupLayout.PREFERRED_SIZE, 152,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(
												layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(txtMaNV, GroupLayout.PREFERRED_SIZE,
																216, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblTenNV, GroupLayout.PREFERRED_SIZE,
																100, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout
												.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addGroup(layout.createSequentialGroup()
														.addComponent(lblNgaySinh,
																GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addGap(116, 116, 116))
												.addComponent(dpNgaySinh, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(txtDiaChi)
								.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(txtSoDT, GroupLayout.PREFERRED_SIZE, 216,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSoDT, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblGioiTinh, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(cbxGioiTinh, GroupLayout.PREFERRED_SIZE, 216,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lblMatKhau, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(layout
														.createParallelGroup(GroupLayout.Alignment.LEADING,
																false)
														.addComponent(pfMatKhau,
																GroupLayout.Alignment.TRAILING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(lblChucVu,
																		GroupLayout.PREFERRED_SIZE, 100,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(116, 116, 116))
														.addComponent(cbxChucVu, 0,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 462,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHoTen, GroupLayout.PREFERRED_SIZE, 127,
														GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, Short.MAX_VALUE)))))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 45,
						GroupLayout.PREFERRED_SIZE)
				.addGap(30, 30, 30)
				.addComponent(lblHoTen, GroupLayout.PREFERRED_SIZE, 20,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(
						txtHoTen, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dpNgaySinh, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblTenNV, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtMaNV, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblSoDT, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtSoDT, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblChucVu, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGioiTinh, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(cbxChucVu, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(cbxGioiTinh, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE))
								.addGap(20, 20, 20)
								.addComponent(lblMatKhau, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(pfMatKhau, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(20, 20, 20).addComponent(lblDiaChi)
				.addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 40,
						GroupLayout.PREFERRED_SIZE)
				.addGap(20, 20, 20).addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 34,
						GroupLayout.PREFERRED_SIZE)
				.addGap(19, 19, 19)));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);

		loadEmpInfo();
	}

	private void loadEmpInfo() {
		if (cheDo == FORM_XEM_THONG_TIN)
			setTextFieldEditable(false);
		else
			setTextFieldEditable(true);

		txtMaNV.setText(nhanVien.getMaNhanVien());
		txtHoTen.setText(nhanVien.getHoTenNV());
		txtDiaChi.setText(nhanVien.getDiaChiNV());
		txtSoDT.setText(nhanVien.getSoDienThoaiNV());
		dpNgaySinh.setDate(nhanVien.getNgaySinhNV());
		cbxChucVu.setSelectedItem(nhanVien.getquyenTruyCap());
		cbxGioiTinh.setSelectedItem(nhanVien.getGioiTinhNV());
	}

	private void setTextFieldEditable(boolean state) {
		txtDiaChi.setEditable(state);
		txtHoTen.setEditable(state);
		txtMaNV.setEditable(state);
		txtSoDT.setEditable(state);
		pfMatKhau.setEditable(state);
		dpNgaySinh.setEnabled(state);
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		if (jButton1.getName().equals("Thoat"))
			dispose();
		else {
			boolean isUpdated = updateEvent();
			if (isUpdated) {
				JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công!");
				btnUpdate.doClick();
				dispose();
			}
		}
	}

	private boolean updateEvent() {
//		Viết code xử lí chức năng cập nhật ở đây nè
		return true;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}
}