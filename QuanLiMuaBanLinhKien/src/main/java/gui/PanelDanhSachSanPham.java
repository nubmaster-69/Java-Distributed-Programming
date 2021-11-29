package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import entity.LinhKien;
import facade.IComponentFacade;

public class PanelDanhSachSanPham extends JPanel implements MouseListener, ActionListener, KeyListener, DateChangeListener {

	private static final long serialVersionUID = 1L;

	private JButton btnLamMoi;
	private JButton btnThem;
	private JButton btnXoa;
	
	private DatePicker dpNgayNhap;
	
	private JPanel jPanel1;

	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;

	private JLabel lblBaoHanh;
	private JLabel lblDonGia;
	private JLabel lblLoai;
	private JLabel lblMoTa;
	private JLabel lblNgayNhap;
	private JLabel lblSoLuongTon;
	private JLabel lblTenSP;
	private JLabel lblThongTinSanPham;
	private JLabel lblThuongHieu;
	private JLabel lblTieuDe;
	private JLabel lblTimKiem;

	private JTextArea txtAreaMoTa;

	private JTextField txtBaoHanh;
	private JTextField txtDonGia;
	private JTextField txtLoaiLinhKien;
	private JTextField txtSoLuongTon;
	private JTextField txtTenSanPham;
	private JTextField txtThuongHieu;
	private JTextField txtTimKiem;

	private JTable tableDanhSachSanPham;
	private DefaultTableModel model;
	private TableRowSorter<DefaultTableModel> rowSorter;

	private IComponentFacade componentFacade = null;

	private Map<String, LinhKien> comps = new HashMap<>();

	public PanelDanhSachSanPham() {
		try {
			componentFacade = (IComponentFacade) Naming.lookup("rmi://localhost:1341/componentFacade");
		} catch (Exception e) {
			e.printStackTrace();
		}

		initComponents();
	}

	private void initComponents() {

		jPanel1 = new JPanel();
		btnThem = new JButton();
		btnLamMoi = new JButton();
		lblTieuDe = new JLabel();
		btnXoa = new JButton();
		lblThongTinSanPham = new JLabel();

		lblLoai = new JLabel();
		lblNgayNhap = new JLabel();

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFormatForDatesCommonEra("dd-MM-yyyy");
		dateSettings.setFormatForDatesBeforeCommonEra("dd-MM-yyyy");
		dateSettings.setFontValidDate(new Font("SansSerif", 0, 16));
		dateSettings.setAllowKeyboardEditing(false);
		dpNgayNhap = new DatePicker(dateSettings);
		dpNgayNhap.setFocusable(false);
		dpNgayNhap.setDateToToday();
		
		dpNgayNhap.addDateChangeListener(this);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("SansSerif", 0, 14));

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("SansSerif", 0, 14));

		lblTenSP = new JLabel();

		txtLoaiLinhKien = new JTextField();
		txtLoaiLinhKien.setFont(new Font("SansSerif", 0, 14));

		lblDonGia = new JLabel();
		lblThuongHieu = new JLabel();
		lblSoLuongTon = new JLabel();
		lblBaoHanh = new JLabel();

		txtBaoHanh = new JTextField();
		txtBaoHanh.setFont(new Font("SansSerif", 0, 14));

		txtThuongHieu = new JTextField();
		txtThuongHieu.setFont(new Font("SansSerif", 0, 14));

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setFont(new Font("SansSerif", 0, 14));

		lblMoTa = new JLabel();
		lblTimKiem = new JLabel();
		jScrollPane2 = new JScrollPane();
		txtAreaMoTa = new JTextArea();
		jScrollPane1 = new JScrollPane();

		lblTimKiem.setFont(new Font("SansSerif", 0, 16));
		lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem.setText("Tìm Sảm Phẩm");

		model = new DefaultTableModel(new String[] { "Mã LK", "Tên LK", "Thương Hiệu", "Đơn Giá", "Số Lượng" }, 0);

		tableDanhSachSanPham = new JTable(model);
		rowSorter = new TableRowSorter<DefaultTableModel>(model);
		tableDanhSachSanPham.setRowSorter(rowSorter);
		tableDanhSachSanPham.setDefaultEditor(Object.class, null);

		tableDanhSachSanPham.setGridColor(Color.WHITE);
		tableDanhSachSanPham.setRowHeight(20);
		tableDanhSachSanPham.setFocusable(false);
		tableDanhSachSanPham.setFont(new Font("SansSerif", 0, 14));
		tableDanhSachSanPham.getTableHeader().setFont(new Font("SansSerif", 0, 14));

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableDanhSachSanPham.getTableHeader()
				.getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

		for (int i = 0; i < tableDanhSachSanPham.getColumnCount(); ++i) {
			if (i == 1)
				tableDanhSachSanPham.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
			else if (i == 3 || i == 4)
				tableDanhSachSanPham.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
			else
				tableDanhSachSanPham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		tableDanhSachSanPham.setCursor(new Cursor(Cursor.HAND_CURSOR));

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("SansSerif", 0, 16));
		txtTimKiem.setMargin(new Insets(2, 5, 2, 5));

		tableDanhSachSanPham.addMouseListener(this);

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));

		txtTimKiem.addKeyListener(this);

		btnThem.setText("Thêm");
		btnThem.setFocusable(false);
		btnThem.setFont(new Font("SansSerif", 0, 16));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnThemActionPerformed();
			}
		});

		btnLamMoi.setText("Làm mới");
		btnLamMoi.setOpaque(true);
		btnLamMoi.setBorderPainted(false);
		btnLamMoi.setBackground(new Color(240, 240, 240));
		btnLamMoi.setForeground(new Color(240, 240, 240));
		btnLamMoi.setFocusable(false);
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnLamMoiActionPerformed();
			}
		});

		lblTieuDe.setFont(new Font("SansSerif", 1, 24));
		lblTieuDe.setText("Danh sách sản phẩm");

		btnXoa.setText("Xóa");
		
		btnXoa.setForeground(new Color(240,240,240));
		btnXoa.setOpaque(false);
		btnXoa.setContentAreaFilled(false);
		btnXoa.setBorderPainted(false);
		btnXoa.setFocusable(false);
		btnXoa.setFont(new Font("SansSerif", 0, 16));

		lblThongTinSanPham.setFont(new Font("SansSerif", 1, 24));
		lblThongTinSanPham.setText("Thông tin sản phẩm");

		lblLoai.setFont(new Font("SansSerif", 0, 14));
		lblLoai.setText("Loại linh kiện");

		lblNgayNhap.setFont(new Font("SansSerif", 0, 14));
		lblNgayNhap.setText("Ngày nhập");

		lblTenSP.setFont(new Font("SansSerif", 0, 14));
		lblTenSP.setText("Tên sản phẩm");

		lblDonGia.setFont(new Font("SansSerif", 0, 14));
		lblDonGia.setText("Đơn giá");

		lblThuongHieu.setFont(new Font("SansSerif", 0, 14));
		lblThuongHieu.setText("Thương hiệu");

		lblSoLuongTon.setFont(new Font("SansSerif", 0, 14));
		lblSoLuongTon.setText("Số lượng      ");

		lblBaoHanh.setFont(new Font("SansSerif", 0, 14));
		lblBaoHanh.setText("Bảo hành");

		lblMoTa.setFont(new Font("SansSerif", 0, 14));
		lblMoTa.setText("Mô tả");

		txtAreaMoTa.setColumns(20);
		txtAreaMoTa.setFont(new Font("SansSerif", 0, 18));
		txtAreaMoTa.setRows(5);
		jScrollPane2.setViewportView(txtAreaMoTa);

		jScrollPane1.setViewportView(tableDanhSachSanPham);
		if (tableDanhSachSanPham.getColumnModel().getColumnCount() > 0) {
			tableDanhSachSanPham.getColumnModel().getColumn(0).setResizable(false);
			tableDanhSachSanPham.getColumnModel().getColumn(1).setResizable(false);
			tableDanhSachSanPham.getColumnModel().getColumn(2).setResizable(false);
			tableDanhSachSanPham.getColumnModel().getColumn(3).setResizable(false);
			tableDanhSachSanPham.getColumnModel().getColumn(4).setResizable(false);
		}

		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
								.addComponent(jScrollPane1, GroupLayout.Alignment.LEADING)
								.addGroup(GroupLayout.Alignment.LEADING,
										layout.createSequentialGroup().addGap(187, 187, 187).addComponent(btnXoa)
												.addGap(261, 261, 261).addComponent(btnLamMoi))
								.addGroup(
										GroupLayout.Alignment.LEADING,
										layout.createSequentialGroup().addGap(165, 165, 165).addComponent(lblTieuDe)))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
								.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
										GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
												.addComponent(btnThem).addGap(180, 180, 180))
										.addGroup(layout.createSequentialGroup().addGroup(layout
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lblTenSP)
												.addGroup(layout.createSequentialGroup().addGap(6, 6, 6)
														.addGroup(layout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(lblLoai).addComponent(lblDonGia)
																.addComponent(lblNgayNhap).addComponent(lblMoTa))))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout
														.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addGroup(layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(GroupLayout.Alignment.LEADING,
																		false)
																.addComponent(txtLoaiLinhKien,
																		GroupLayout.PREFERRED_SIZE, 165,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(dpNgayNhap, GroupLayout.PREFERRED_SIZE,
																		165, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtDonGia, GroupLayout.PREFERRED_SIZE,
																		165, GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18, 18)
																.addGroup(layout
																		.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																		.addGroup(layout
																				.createParallelGroup(
																						GroupLayout.Alignment.TRAILING)
																				.addComponent(lblSoLuongTon)
																				.addComponent(lblThuongHieu))
																		.addComponent(lblBaoHanh))
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(layout
																		.createParallelGroup(
																				GroupLayout.Alignment.TRAILING, false)
																		.addComponent(txtThuongHieu,
																				GroupLayout.Alignment.LEADING,
																				GroupLayout.PREFERRED_SIZE, 165,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(txtSoLuongTon,
																				GroupLayout.Alignment.LEADING,
																				GroupLayout.PREFERRED_SIZE, 165,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(txtBaoHanh,
																				GroupLayout.PREFERRED_SIZE, 165,
																				GroupLayout.PREFERRED_SIZE)))
														.addComponent(jScrollPane2).addComponent(txtTenSanPham))
												.addContainerGap(20, Short.MAX_VALUE))))
								.addGroup(GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblThongTinSanPham).addGap(133, 133, 133))))
				.addGroup(layout.createSequentialGroup().addGap(21, 21, 21).addComponent(lblTimKiem)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
						.addGap(220, 220, 220).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
								.addComponent(txtTimKiem, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addComponent(lblTimKiem, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(lblTieuDe)
								.addComponent(lblThongTinSanPham))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
								.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout
										.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lblThuongHieu).addComponent(txtThuongHieu,
														GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lblSoLuongTon).addComponent(txtSoLuongTon,
														GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18).addComponent(txtBaoHanh, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(lblTenSP).addComponent(
																txtTenSanPham, GroupLayout.PREFERRED_SIZE, 30,
																GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(lblDonGia)
														.addComponent(txtDonGia, GroupLayout.PREFERRED_SIZE, 30,
																GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(lblLoai).addComponent(txtLoaiLinhKien,
																GroupLayout.PREFERRED_SIZE, 30,
																GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(lblNgayNhap)
														.addComponent(dpNgayNhap, GroupLayout.PREFERRED_SIZE, 30,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblBaoHanh))))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(jScrollPane2)
										.addGroup(layout.createSequentialGroup().addComponent(lblMoTa).addGap(0, 0,
												Short.MAX_VALUE))))
								.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
						.addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(btnThem).addComponent(btnLamMoi).addComponent(btnXoa))
						.addContainerGap()));

		loadProductsData();
	}

	private void btnThemActionPerformed() {
		if (validateData("check")) {
			String ten = txtTenSanPham.getText().trim();
			String mota = txtAreaMoTa.getText().trim();
			double gia = Double.parseDouble(txtDonGia.getText().trim());
			String loai = txtLoaiLinhKien.getText();
			String thuonghieu = txtThuongHieu.getText().trim();
			int soluong = Integer.parseInt(txtSoLuongTon.getText().trim());
			LocalDate ngaynhap = dpNgayNhap.getDate();
			int bh = Integer.parseInt(txtBaoHanh.getText());

			try {
				String maLinhKien = componentFacade.getLastLK();
				LinhKien lk = new LinhKien(maLinhKien, ten, loai, mota, gia, thuonghieu, soluong, ngaynhap, bh);
				if (componentFacade.addComponent(lk)) {
					if (JOptionPane.showConfirmDialog(null, "Thêm sản phẩm thành công! Bạn có muốn tiếp tục thêm?",
							"Thêm thông tin sản phẩm", JOptionPane.YES_NO_OPTION) == 1)
						clearTextFields();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			loadProductsData();
		}
	}

	private void clearTextFields() {
		txtTenSanPham.setText("");
		txtDonGia.setText("");
		txtThuongHieu.setText("");
		txtLoaiLinhKien.setText("");
		txtSoLuongTon.setText("");
		dpNgayNhap.setText("");
		txtBaoHanh.setText("");
		txtAreaMoTa.setText("");
	}

	private void btnLamMoiActionPerformed() {
		loadProductsData();
	}

	private void loadProductsData() {
		model.getDataVector().removeAllElements();
		List<LinhKien> compList = null;
		try {
			compList = componentFacade.getComponents();
			for (LinhKien comp : compList) {
				model.addRow(new Object[] { comp.getMaLinhKien(), comp.getTenLinhKien(), comp.getThuongHieu(),
						currencyFormat(comp.getDonGia()), comp.getSoLuongTon() });
				comps.put(comp.getMaLinhKien(), comp);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	private boolean validateData(String checkMa) {
		if (checkMa.equals("check")) {

			String ten = txtTenSanPham.getText().trim();
			String loai = txtLoaiLinhKien.getText();
			String thuonghieu = txtThuongHieu.getText().trim();

			if (ten.equals("")) {
				showMsg("Tên sản phẩm không được trống!");
				txtTenSanPham.selectAll();
				txtTenSanPham.requestFocus();
				return false;
			}

			try {
				double gia = Double.parseDouble(txtDonGia.getText().trim().replace(".", ""));
				if (gia < 0) {
					showMsg("Giá không được bé hơn 0!");
					txtDonGia.selectAll();
					txtDonGia.requestFocus();
					return false;
				}
			} catch (Exception e) {
				showMsg("Giá phải là số!");
				txtDonGia.selectAll();
				txtDonGia.requestFocus();
				return false;
			}

			if (thuonghieu.equals("")) {
				showMsg("Thương hiệu không được trống!");
				txtThuongHieu.selectAll();
				txtThuongHieu.requestFocus();
				return false;
			}

			if (loai.equals("")) {
				showMsg("Loại linh kiện không được trống!");
				txtLoaiLinhKien.selectAll();
				txtLoaiLinhKien.requestFocus();
				return false;
			}

			try {
				int soluong = Integer.parseInt(txtSoLuongTon.getText().trim());
				if (soluong < 0) {
					showMsg("Số lượng không được bé hơn 0!");
					txtSoLuongTon.selectAll();
					txtSoLuongTon.requestFocus();
					return false;
				}
			} catch (Exception e) {
				showMsg("Số lượng phải là số!");
				txtSoLuongTon.selectAll();
				txtSoLuongTon.requestFocus();
				return false;
			}

			LocalDate ngayNhap = dpNgayNhap.getDate();

			if (ngayNhap == null || dpNgayNhap == null) {
				showMsg("Vui lòng chọn ngày nhập!");
				dpNgayNhap.requestFocus();
				return false;
			}
			
			if(ngayNhap.isAfter(LocalDate.now())) {
				showMsg("Ngày nhập hàng phải trước hoặc là ngày hôm nay!");
				dpNgayNhap.requestFocus();
				return false;
			}

			try {
				int bh = Integer.parseInt(txtBaoHanh.getText());
				if (bh < 0) {
					showMsg("Thời gian bảo hành không được bé hơn 0!");
					txtBaoHanh.selectAll();
					txtBaoHanh.requestFocus();
					return false;
				}
			} catch (Exception e) {
				showMsg("Thời gian bảo hành phải là số!");
				txtBaoHanh.selectAll();
				txtBaoHanh.requestFocus();
				return false;
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThem))
			btnThemActionPerformed();
		else if (o.equals(btnLamMoi))
			btnLamMoiActionPerformed();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseClick = e.getButton();
		int mouseCount = e.getClickCount();

		int row = tableDanhSachSanPham.getSelectedRow();

		if (mouseClick == 1 && mouseCount == 2)
			new FormThongTinSanPham(FormThongTinSanPham.FORM_XEM_THONG_TIN,
					comps.get(tableDanhSachSanPham.getValueAt(row, 0).toString())).setVisible(true);
		else if (mouseClick == 3 && mouseCount == 2) {
			if (tableDanhSachSanPham.getSelectedRow() != -1) {
				FormThongTinSanPham form = new FormThongTinSanPham(FormThongTinSanPham.FORM_CAP_NHAT,
						comps.get(tableDanhSachSanPham.getValueAt(row, 0).toString()));

				form.setBtnUpdate(btnLamMoi);
				form.setVisible(true);
			}
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

	private String currencyFormat(double donGia) {
		return new DecimalFormat("#,###").format(donGia);
	}

	@Override
	public void dateChanged(DateChangeEvent event) {
		if(dpNgayNhap.getDate().isAfter(LocalDate.now())) {
			dpNgayNhap.closePopup();
			showMsg("Ngày nhập hàng phải trước hoặc là ngày hôm nay!");
		}
	}
}