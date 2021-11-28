package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import entity.NhanVien;
import facade.IEmployeeFacade;

public class PanelDanhSachNhanVien extends JPanel implements MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JButton btnChinhSua;
	private JButton btnThem;
	private JButton btnXoa;

	private JCheckBox chkNu;

	private JComboBox<String> cmbBoxQuyenDangNhap;

	private JLabel jLabel1;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel lblTimKiem;

	private JTextField txtDiaChi;
	private JTextField txtHoTen;
	private JTextField txtMaNhanVien;
	private JPasswordField txtMatKhau;
	private JTextField txtNgaySinh;
	private JTextField txtSDT;
	private JTextField txtTimKiem;

	private JScrollPane jScrollPane1;

	private JTable tableDanhSachNhanVien;
	private DefaultTableModel model;
	private TableRowSorter<DefaultTableModel> rowSorter;

	private DatePicker dpNgaySinh;

	private Map<String, NhanVien> emps = new HashMap<>();

	private IEmployeeFacade employeeFacade = null;

	public PanelDanhSachNhanVien() {
		try {
			employeeFacade = (IEmployeeFacade) Naming.lookup("rmi://localhost:1341/employeeFacade");
		} catch (Exception e) {
			e.printStackTrace();
		}
		initComponents();
	}

	private void initComponents() {

		jLabel1 = new JLabel();
		jScrollPane1 = new JScrollPane();
		btnThem = new JButton();
		btnChinhSua = new JButton();
		btnXoa = new JButton();
		jLabel8 = new JLabel();
		jLabel3 = new JLabel();
		txtDiaChi = new JTextField();
		jLabel4 = new JLabel();
		txtMaNhanVien = new JTextField();
		txtHoTen = new JTextField();
		jLabel5 = new JLabel();
		jLabel11 = new JLabel();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		txtMatKhau = new JPasswordField();
		txtNgaySinh = new JTextField();
		jLabel2 = new JLabel();
		txtSDT = new JTextField();
		chkNu = new JCheckBox();
		jLabel12 = new JLabel();
		cmbBoxQuyenDangNhap = new JComboBox<>();
		txtTimKiem = new JTextField();
		lblTimKiem = new JLabel();

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFontValidDate(new Font("SansSerif", 0, 16));
		dateSettings.setFormatForDatesCommonEra("dd-MM-yyyy");
		dateSettings.setAllowKeyboardEditing(false);
		dpNgaySinh = new DatePicker(dateSettings);
		dpNgaySinh.setFocusable(false);
		dpNgaySinh.setCursor(new Cursor(Cursor.HAND_CURSOR));

		cmbBoxQuyenDangNhap.setFont(new Font("SansSerif", 0, 14));
		cmbBoxQuyenDangNhap.setFocusable(false);

		txtSDT.setFont(new Font("SansSerif", Font.PLAIN, 19));
		txtDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 19));
		txtHoTen.setFont(new Font("SansSerif", Font.PLAIN, 19));
		txtMaNhanVien.setFont(new Font("SansSerif", Font.PLAIN, 19));
		txtMatKhau.setFont(new Font("SansSerif", Font.PLAIN, 19));
		txtNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 19));
		txtTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 18));
		txtTimKiem.addKeyListener(this);

		jLabel1.setFont(new Font("SansSerif", 1, 24));
		jLabel1.setText("Danh sách nhân viên");

		model = new DefaultTableModel(new String[] { "Mã NV", "Họ & Tên", "Số ĐT", "Vai Trò" }, 0);

		tableDanhSachNhanVien = new JTable(model);
		rowSorter = new TableRowSorter<>(model);
		tableDanhSachNhanVien.setRowSorter(rowSorter);

		tableDanhSachNhanVien.addMouseListener(this);
		tableDanhSachNhanVien.setRowHeight(20);
		tableDanhSachNhanVien.setDefaultEditor(Object.class, null);
		tableDanhSachNhanVien.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tableDanhSachNhanVien.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));

		tableDanhSachNhanVien.setGridColor(Color.WHITE);
		tableDanhSachNhanVien.setFocusable(false);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableDanhSachNhanVien.getTableHeader()
				.getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tableDanhSachNhanVien.getColumnCount(); ++i) {
			tableDanhSachNhanVien.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		tableDanhSachNhanVien.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tableDanhSachNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jScrollPane1.setViewportView(tableDanhSachNhanVien);
		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

		btnThem.setText("Thêm");
		btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnThem.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnThemActionPerformed(evt);
			}
		});

		btnChinhSua.setText("Chỉnh sửa");
		btnChinhSua.setOpaque(true);
		btnChinhSua.setBorderPainted(false);
		btnChinhSua.setBackground(new Color(240, 240, 240));
		btnChinhSua.setForeground(new Color(240, 240, 240));
		btnChinhSua.setFocusable(false);
		btnChinhSua.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnChinhSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnChinhSuaActionPerformed(evt);
			}
		});

		btnXoa.setText("Xóa");
		btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnXoaActionPerformed(evt);
			}
		});

		jLabel8.setFont(new Font("SansSerif", 0, 18));
		jLabel8.setText("Địa chỉ");

		jLabel3.setFont(new Font("SansSerif", 0, 18));
		jLabel3.setText("Mã nhân viên");

		jLabel4.setFont(new Font("SansSerif", 0, 18));
		jLabel4.setText("Họ và tên");

		jLabel5.setFont(new Font("SansSerif", 0, 18));
		jLabel5.setText("Nữ");

		jLabel11.setFont(new Font("SansSerif", 0, 18));
		jLabel11.setText("Mật khẩu");

		jLabel6.setFont(new Font("SansSerif", 0, 18));
		jLabel6.setText("Ngày sinh");

		jLabel7.setFont(new Font("SansSerif", 0, 18));
		jLabel7.setText("Số điện thoại");

		jLabel2.setFont(new Font("SansSerif", 1, 24));
		jLabel2.setText("Thông tin nhân viên");

		jLabel12.setFont(new Font("SansSerif", 0, 18));
		jLabel12.setText("Quyền đăng nhập");

		cmbBoxQuyenDangNhap.setModel(new DefaultComboBoxModel<>(new String[] { "Nhân Viên", "Quản Lý" }));
		cmbBoxQuyenDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lblTimKiem.setFont(new Font("SansSerif", 0, 14));
		lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem.setText("Tìm Kiếm Nhân Viên");
		lblTimKiem.setHorizontalTextPosition(SwingConstants.CENTER);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(177, 177, 177).addComponent(btnXoa)
						.addGap(318, 318, 318).addComponent(btnChinhSua)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnThem).addGap(185, 185, 185))
				.addGroup(layout.createSequentialGroup().addGap(114, 114, 114).addComponent(jLabel1)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabel2).addGap(123, 123, 123))
				.addGroup(GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(jScrollPane1)
										.addGroup(layout.createSequentialGroup().addGap(6, 6, 6)
												.addComponent(lblTimKiem, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(
														txtTimKiem, GroupLayout.PREFERRED_SIZE, 454,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(GroupLayout.Alignment.TRAILING, layout
												.createSequentialGroup().addGap(18, 18, Short.MAX_VALUE)
												.addGroup(layout
														.createParallelGroup(GroupLayout.Alignment.LEADING,
																false)
														.addGroup(layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(
																		GroupLayout.Alignment.LEADING,
																		false)
																.addComponent(jLabel3)
																.addGroup(layout.createSequentialGroup().addGroup(layout
																		.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel5).addComponent(chkNu,
																				GroupLayout.Alignment.TRAILING,
																				GroupLayout.PREFERRED_SIZE,
																				21,
																				GroupLayout.PREFERRED_SIZE))
																		.addGroup(layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addGroup(layout.createSequentialGroup()
																						.addGap(15, 15, 15)
																						.addComponent(dpNgaySinh,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																				.addGroup(layout.createSequentialGroup()
																						.addGap(18, 18, 18)
																						.addComponent(jLabel6))))
																.addComponent(txtMaNhanVien,
																		GroupLayout.PREFERRED_SIZE, 203,
																		GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18, 18)
																.addGroup(layout.createParallelGroup(
																		GroupLayout.Alignment.LEADING,
																		false).addComponent(jLabel4)
																		.addComponent(jLabel7)
																		.addComponent(txtHoTen,
																				GroupLayout.DEFAULT_SIZE,
																				200, Short.MAX_VALUE)
																		.addComponent(txtSDT)))
														.addComponent(jLabel8)
														.addGroup(layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(
																		GroupLayout.Alignment.LEADING)
																.addGroup(layout.createSequentialGroup().addGap(2, 2, 2)
																		.addComponent(cmbBoxQuyenDangNhap,
																				GroupLayout.PREFERRED_SIZE,
																				200,
																				GroupLayout.PREFERRED_SIZE))
																.addComponent(jLabel12)).addGap(18, 18, 18)
																.addGroup(layout.createParallelGroup(
																		GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel11)
																		.addComponent(txtMatKhau)))
														.addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE,
																421, GroupLayout.PREFERRED_SIZE))
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addContainerGap(
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(
								GroupLayout.Alignment.LEADING,
								layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTimKiem, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1).addComponent(jLabel2))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(jLabel4).addComponent(jLabel3))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout
												.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(txtHoTen, GroupLayout.DEFAULT_SIZE, 30,
														Short.MAX_VALUE)
												.addComponent(txtMaNhanVien))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6).addComponent(jLabel7).addComponent(
														jLabel5, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout
												.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(dpNgaySinh, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(chkNu, GroupLayout.PREFERRED_SIZE, 29,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtSDT, GroupLayout.DEFAULT_SIZE, 30,
														Short.MAX_VALUE))
										.addGap(21, 21, 21).addComponent(jLabel8)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												txtDiaChi, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
												.addGroup(layout.createSequentialGroup().addComponent(jLabel12)
														.addPreferredGap(
																LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(cmbBoxQuyenDangNhap,
																GroupLayout.PREFERRED_SIZE, 30,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup().addComponent(jLabel11)
														.addPreferredGap(
																LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(txtMatKhau,
																GroupLayout.PREFERRED_SIZE, 30,
																GroupLayout.PREFERRED_SIZE)))
										.addGap(0, 40, Short.MAX_VALUE)))
						.addPreferredGap(
								LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(btnChinhSua).addComponent(btnThem).addComponent(btnXoa))
						.addGap(11, 11, 11)));

		loadEmployeesData();
	}

	private void btnXoaActionPerformed(ActionEvent evt) {
		xoa();
		tableDanhSachNhanVien.clearSelection();
	}

	private void btnThemActionPerformed(ActionEvent evt) {
		if (regex()) {
			try {
				NhanVien nv = getNhanVien();
				boolean isInserted = employeeFacade.addEmployee(nv);

				if (isInserted) {
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					removeForm();
					loadEmployeesData();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void btnChinhSuaActionPerformed(ActionEvent evt) {
		loadEmployeesData();
	}

	private void removeForm() {
		txtMaNhanVien.setText("");
		txtHoTen.setText("");
		txtNgaySinh.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtMatKhau.setText("");
	}

	private boolean regex() {
		String maNV = txtMaNhanVien.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String ngay = txtNgaySinh.getText();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String matKhau = txtMatKhau.getPassword().toString().trim();

		if (!(maNV.length() > 0 && hoTen.length() > 0 && ngay.length() > 0 && sdt.length() > 0 && diaChi.length() > 0
				&& matKhau.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Phải nhập đủ thông tin");
		}

		if (!(maNV.length() > 0)) {
			txtMaNhanVien.requestFocus();
			return false;
		}

		if (!(hoTen.length() > 0)) {
			txtHoTen.requestFocus();
			return false;
		}

		if (!(ngay.length() > 0)) {
			txtNgaySinh.requestFocus();
			return false;
		}

		if (!(sdt.length() > 0)) {
			txtSDT.requestFocus();
			return false;
		}

		if (!(diaChi.length() > 0)) {
			txtDiaChi.requestFocus();
			return false;
		}

		if (!(matKhau.length() > 0)) {
			txtMatKhau.requestFocus();
			return false;
		}

		if (!(hoTen.matches(
				"^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$"))) {
			JOptionPane.showMessageDialog(this, "Tên không hợp lệ");
			txtHoTen.requestFocus();
			txtHoTen.selectAll();
			return false;
		}

		if (!(ngay.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$"))) {
			JOptionPane.showMessageDialog(this, "Ngày sinh phải theo dạng: YYYY-MM-DD");
			txtNgaySinh.selectAll();
			txtNgaySinh.requestFocus();
			return false;
		}

		if (!(sdt.matches("^0[0-9]{9}$"))) {
			JOptionPane.showInternalMessageDialog(this, "Số điện thoại k hợp lệ");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}

		String[] ngayFormat = ngay.split("-");
		LocalDate ngaysinh = null;
		LocalDate ngayHienTai = null;
		try {
			ngaysinh = LocalDate.of(Integer.parseInt(ngayFormat[0]), Integer.parseInt(ngayFormat[1]),
					Integer.parseInt(ngayFormat[2]));
			ngayHienTai = LocalDate.now();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày sinh Không hợp lệ");
			txtNgaySinh.selectAll();
			txtNgaySinh.requestFocus();
			return false;
		}

		if (!(ngaysinh.isBefore(ngayHienTai))) {
			JOptionPane.showMessageDialog(this, "Ngày sinh phải bé hơn ngày hiện tại");
			txtNgaySinh.selectAll();
			txtNgaySinh.requestFocus();
			return false;
		}

		return true;
	}

	private void loadEmployeesData() {
		List<NhanVien> empList = null;
		try {
			empList = employeeFacade.getEmployees();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		model.getDataVector().removeAllElements();
		empList.forEach(emp -> {
			model.addRow(new Object[] { emp.getMaNhanVien(), emp.getHoTenNV(), emp.getSoDienThoaiNV(),
					emp.getquyenTruyCap() });

			emps.put(emp.getMaNhanVien(), emp);
		});
	}

	private void xoa() {
		int row = tableDanhSachNhanVien.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Chọn dòng cần xóa");
			return;
		} else {
			int t = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
			if (t == JOptionPane.YES_OPTION) {
				String maNV = tableDanhSachNhanVien.getValueAt(row, 0).toString();

				boolean isRemoved = false;

				try {
					isRemoved = employeeFacade.removeEmployeeByID(maNV);
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				if (isRemoved)
					loadEmployeesData();
			}
		}
	}

	@SuppressWarnings("deprecation")
	private NhanVien getNhanVien() throws ParseException {
		NhanVien nv = null;
		String maNV = txtMaNhanVien.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String ngay = txtNgaySinh.getText();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String quyenDangNhap = (String) (cmbBoxQuyenDangNhap.getSelectedItem());
		String matKhau = "";

		if (!txtMatKhau.getText().trim().isEmpty())
			matKhau = txtMatKhau.getText().trim();
		else
			try {
				matKhau = employeeFacade.getEmployeeByID(maNV).getMatKhau();
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = format.parse(ngay);
		java.sql.Date ngaySinh = new java.sql.Date(parsed.getTime());

		if (chkNu.isSelected()) {
			nv = new NhanVien(maNV, hoTen, "Nữ", ngaySinh.toLocalDate(), sdt, diaChi, matKhau, quyenDangNhap);
		} else {
			nv = new NhanVien(maNV, hoTen, "Nam", ngaySinh.toLocalDate(), sdt, diaChi, matKhau, quyenDangNhap);
		}
		return nv;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseClick = e.getButton();
		int mouseCount = e.getClickCount();

		int row = tableDanhSachNhanVien.getSelectedRow();

		if (mouseClick == 1 && mouseCount == 2)
			new FormThongTinNhanVien(FormThongTinNhanVien.FORM_XEM_THONG_TIN,
					emps.get(tableDanhSachNhanVien.getValueAt(row, 0).toString())).setVisible(true);
		else if (mouseClick == 3 && mouseCount == 2) {
			FormThongTinNhanVien form = new FormThongTinNhanVien(FormThongTinNhanVien.FORM_CAP_NHAT,
					emps.get(tableDanhSachNhanVien.getValueAt(row, 0).toString()));

			form.setBtnUpdate(btnChinhSua);
			form.setVisible(true);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource().equals(txtTimKiem)) {
			String keyword = txtTimKiem.getText().trim();
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
		}
	}
}