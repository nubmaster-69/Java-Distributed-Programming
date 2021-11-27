package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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

import dao.EmployeeDAO;
import entity.NhanVien;
import facade.IEmployeeFacade;

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
			dpNgaySinh.setEnabled(false);
			txtMaNV.setEnabled(false);
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
				try {
					jButton1ActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(100, 100, 100).addComponent(lblTieuDe,
										GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(160, 160, 160).addComponent(jButton1,
										GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
						.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(txtMaNV, GroupLayout.PREFERRED_SIZE, 216,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTenNV, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addGroup(layout.createSequentialGroup()
														.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addGap(116, 116, 116))
												.addComponent(dpNgaySinh, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(txtDiaChi)
								.addGroup(GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(txtSoDT, GroupLayout.PREFERRED_SIZE, 216,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblSoDT, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblGioiTinh, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(cbxGioiTinh, GroupLayout.PREFERRED_SIZE, 216,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(
														LayoutStyle.ComponentPlacement.RELATED,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(
																lblMatKhau, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(layout
																.createParallelGroup(GroupLayout.Alignment.LEADING,
																		false)
																.addComponent(pfMatKhau, GroupLayout.Alignment.TRAILING)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(lblChucVu,
																				GroupLayout.PREFERRED_SIZE, 100,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(116, 116, 116))
																.addComponent(cbxChucVu, 0, GroupLayout.DEFAULT_SIZE,
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
				.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addGap(30, 30, 30)
				.addComponent(lblHoTen, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dpNgaySinh, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblTenNV, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtMaNV, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblSoDT, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtSoDT, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(lblMatKhau, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(pfMatKhau, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
				.addGap(20, 20, 20).addComponent(lblDiaChi).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20)
				.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
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
		pfMatKhau.setText(nhanVien.getMatKhau());
	}

	private void setTextFieldEditable(boolean state) {
		txtDiaChi.setEditable(state);
		txtHoTen.setEditable(state);
		txtMaNV.setEditable(state);
		txtSoDT.setEditable(state);
		pfMatKhau.setEditable(state);
		dpNgaySinh.setEnabled(state);
	}

	private void jButton1ActionPerformed(ActionEvent evt) throws Exception {
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

	private boolean updateEvent() throws Exception {
//		Viết code xử lí chức năng cập nhật ở đây nè
		if (regex()) {
			NhanVien nv = getNhanVien();
			IEmployeeFacade emlf = new EmployeeDAO();
			emlf.updateEmployeeInfo(nv);
			return true;
		}
		return false;
	}

	private NhanVien getNhanVien() throws ParseException {
		NhanVien nv = null;
		String maNV = txtMaNV.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String ngay = dpNgaySinh.getText();
		String sdt = txtSoDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String quyenDangNhap = (String) (cbxChucVu.getSelectedItem());
		String matKhau = pfMatKhau.getText().toString().trim();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = format.parse(ngay);
		java.sql.Date ngaySinh = new java.sql.Date(parsed.getTime());

		if (cbxGioiTinh.getSelectedIndex() == 1) {
			nv = new NhanVien(maNV, hoTen, "Nữ", ngaySinh.toLocalDate(), sdt, diaChi, matKhau, quyenDangNhap);
		} else {
			nv = new NhanVien(maNV, hoTen, "Nam", ngaySinh.toLocalDate(), sdt, diaChi, matKhau, quyenDangNhap);
		}
		return nv;
	}

	private boolean regex() {
		String maNV = txtMaNV.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSoDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String matKhau = pfMatKhau.getText().toString().trim();

		if (!(hoTen.length() > 0 && sdt.length() > 0 && diaChi.length() > 0 && matKhau.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Phải nhập đủ thông tin");
		}

		if (!(hoTen.length() > 0)) {
			txtHoTen.requestFocus();
			return false;
		}

		if (!(sdt.length() > 0)) {
			txtSoDT.requestFocus();
			return false;
		}

		if (!(diaChi.length() > 0)) {
			txtDiaChi.requestFocus();
			return false;
		}

		if (!(matKhau.length() > 0)) {
			pfMatKhau.requestFocus();
			return false;
		}

		if (!(hoTen.matches(
				"^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$"))) {
			JOptionPane.showMessageDialog(this, "Tên không hợp lệ");
			txtHoTen.requestFocus();
			txtHoTen.selectAll();
			return false;
		}

		String pattern = "^(032|033|034|035|036|037|038|039|086|096|097|098|" + "070|079|077|076|078|089|090|093|"
				+ "083|084|085|081|082|088|091|094|" + "056|058|092|" + "059|099)[0-9]{7}$";

		if (!(sdt.matches(pattern))) {
			JOptionPane.showMessageDialog(this, "SDT không hợp lệ");
			txtSoDT.requestFocus();
			txtSoDT.selectAll();
			return false;
		}

		return true;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}
}