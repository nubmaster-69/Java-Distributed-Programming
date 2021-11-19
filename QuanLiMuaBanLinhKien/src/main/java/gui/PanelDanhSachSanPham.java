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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.DecimalFormat;
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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.github.lgooddatepicker.components.DatePicker;

import entity.LinhKien;
import facade.IComponentFacade;

public class PanelDanhSachSanPham extends JPanel implements MouseListener, ActionListener, KeyListener {

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

		dpNgayNhap = new DatePicker();

		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Tahoma", 0, 14));

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", 0, 14));

		lblTenSP = new JLabel();

		txtLoaiLinhKien = new JTextField();
		txtLoaiLinhKien.setFont(new Font("Tahoma", 0, 14));

		lblDonGia = new JLabel();
		lblThuongHieu = new JLabel();
		lblSoLuongTon = new JLabel();
		lblBaoHanh = new JLabel();

		txtBaoHanh = new JTextField();
		txtBaoHanh.setFont(new Font("Tahoma", 0, 14));

		txtThuongHieu = new JTextField();
		txtThuongHieu.setFont(new Font("Tahoma", 0, 14));

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setFont(new Font("Tahoma", 0, 14));

		lblMoTa = new JLabel();
		lblTimKiem = new JLabel();
		jScrollPane2 = new JScrollPane();
		txtAreaMoTa = new JTextArea();
		jScrollPane1 = new JScrollPane();

		lblTimKiem.setFont(new Font("Tahoma", 0, 16));
		lblTimKiem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTimKiem.setText("Tìm Sảm Phẩm");

		model = new DefaultTableModel(new String[] { "Mã LK", "Tên LK", "Thương hiệu", "Đơn giá", "Số lượng tồn" }, 0);

		tableDanhSachSanPham = new JTable(model);
		tableDanhSachSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
			if (i == 1 || i == 2 || i == 3 || i == 4)
				tableDanhSachSanPham.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
			else if (i == 5 || i == 6 || i == 7)
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
		btnThem.setFont(new Font("Tahoma", 0, 16));
		btnThem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
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

		lblTieuDe.setFont(new Font("Tahoma", 1, 24));
		lblTieuDe.setText("Danh sách sản phẩm");

		btnXoa.setText("Xóa");
		btnXoa.setFocusable(false);
		btnXoa.setFont(new Font("Tahoma", 0, 16));
		btnXoa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaActionPerformed();
			}
		});

		lblThongTinSanPham.setFont(new Font("Tahoma", 1, 24));
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
		lblSoLuongTon.setText("Số lượng tồn");

		lblBaoHanh.setFont(new Font("SansSerif", 0, 14));
		lblBaoHanh.setText("Bảo hành");

		lblMoTa.setFont(new Font("SansSerif", 0, 14));
		lblMoTa.setText("Mô tả");

		txtAreaMoTa.setColumns(20);
		txtAreaMoTa.setFont(new Font("Tahoma", 0, 18));
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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
										layout.createSequentialGroup().addGap(187, 187, 187).addComponent(btnXoa)
												.addGap(261, 261, 261).addComponent(btnLamMoi))
								.addGroup(
										javax.swing.GroupLayout.Alignment.LEADING,
										layout.createSequentialGroup().addGap(165, 165, 165).addComponent(lblTieuDe)))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
								.createSequentialGroup()
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
														.addComponent(btnThem).addGap(180, 180, 180))
										.addGroup(layout.createSequentialGroup().addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblTenSP)
												.addGroup(layout.createSequentialGroup().addGap(6, 6, 6).addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(lblLoai).addComponent(
																lblDonGia)
														.addComponent(lblNgayNhap).addComponent(lblMoTa))))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(
														javax.swing.GroupLayout.Alignment.LEADING, false)
														.addGroup(layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false)
																.addComponent(txtLoaiLinhKien,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 165,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(dpNgayNhap,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 165,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		txtDonGia,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 165,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18, 18)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(lblSoLuongTon)
																				.addComponent(lblThuongHieu))
																		.addComponent(lblBaoHanh))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		false)
																		.addComponent(txtThuongHieu,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				165,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(txtSoLuongTon,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				165,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(txtBaoHanh,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				165,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(jScrollPane2).addComponent(txtTenSanPham))
												.addContainerGap(20, Short.MAX_VALUE))))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblThongTinSanPham).addGap(133, 133, 133))))
				.addGroup(layout.createSequentialGroup().addGap(21, 21, 21).addComponent(lblTimKiem)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 320,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(220, 220, 220).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 30,
												Short.MAX_VALUE)
										.addComponent(
												lblTimKiem, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(18, 18,
										18)
								.addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblTieuDe).addComponent(lblThongTinSanPham))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(lblThuongHieu).addComponent(txtThuongHieu,
																javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(18, 18, 18)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(lblSoLuongTon).addComponent(txtSoLuongTon,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(18, 18,
																18)
														.addComponent(txtBaoHanh,
																javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(lblTenSP).addComponent(txtTenSanPham,
																javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(18, 18, 18)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(lblDonGia).addComponent(txtDonGia,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(18, 18, 18)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(lblLoai).addComponent(txtLoaiLinhKien,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(18, 18, 18)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(lblNgayNhap)
																.addComponent(dpNgayNhap,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(lblBaoHanh))))
												.addGap(18, 18, 18)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jScrollPane2)
														.addGroup(layout.createSequentialGroup().addComponent(lblMoTa)
																.addGap(0, 0, Short.MAX_VALUE))))
										.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449,
												Short.MAX_VALUE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(btnThem).addComponent(btnLamMoi).addComponent(btnXoa))
								.addContainerGap()));

		loadProductsData();
	}

//	kiểm tra lại hàm này
	private void btnThemActionPerformed() {
		if (validateData("check")) {
			String ten = txtTenSanPham.getText().trim();
			String mota = txtAreaMoTa.getText().trim();
			double gia = Double.parseDouble(txtDonGia.getText().trim());
			String loai = txtLoaiLinhKien.getText();
			String thuonghieu = txtThuongHieu.getText().trim();
			int soluong = Integer.parseInt(txtSoLuongTon.getText().trim());
			Date ngaynhap = Date.valueOf(dpNgayNhap.getDate());
			int bh = Integer.parseInt(txtBaoHanh.getText());

			try {
				LinhKien lk = null;

				if (componentFacade.addComponent(lk)) {
					if (JOptionPane.showConfirmDialog(null, "Thêm sản phẩm thành công! Tiếp tục thêm?",
							"Thêm thông tin sản phẩm", JOptionPane.YES_NO_OPTION) == 1)
						clearTextFields();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		loadProductsData();
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

	private void btnXoaActionPerformed() {
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);

		if (confirm == 0) {

			while (tableDanhSachSanPham.getSelectedRowCount() != 0) {
				int selectedRow = tableDanhSachSanPham.getSelectedRow();
				String maLK = tableDanhSachSanPham.getValueAt(selectedRow, 0).toString();
				try {
					if (componentFacade.removeComponentByID(maLK))
						model.removeRow(selectedRow);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
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

//	Sua lai ham nay nhe
	private boolean validateData(String checkMa) {
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThem))
			btnThemActionPerformed();
		else if (o.equals(btnXoa))
			btnXoaActionPerformed();
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
			FormThongTinSanPham form = new FormThongTinSanPham(FormThongTinSanPham.FORM_CAP_NHAT,
					comps.get(tableDanhSachSanPham.getValueAt(row, 0).toString()));

			form.setBtnUpdate(btnLamMoi);
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

	private String currencyFormat(double donGia) {
		return new DecimalFormat("#,###").format(donGia);
	}
}