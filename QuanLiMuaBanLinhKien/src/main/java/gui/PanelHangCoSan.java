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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;
import facade.IComponentFacade;
import facade.ICustomerFacade;
import facade.IOrderDetailFacade;
import facade.IOrderFacade;

public class PanelHangCoSan extends JPanel implements MouseListener, ActionListener, KeyListener, TableModelListener {

	private static final long serialVersionUID = 1L;

	private JButton btnLamMoi;
	private JButton btnTaoDon;

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JLabel jLabel10;

	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;

	private JTextField txtTongTien;
	private JTextField txtTienKhachDua;
	private JTextField txtTienThoi;

	private JTable tableDonHang;
	private JTable tableHangCoSan;

	private JTextField txtDiaChi;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JTextField txtTimKiem;

	private DefaultTableModel model;
	private DefaultTableModel modelTaoDonHang;

	private IComponentFacade componentFacade = null;
	private ICustomerFacade customerFacade = null;
	private IOrderFacade orderFacade = null;
	private IOrderDetailFacade orderDetailFacade = null;

	private DecimalFormat df;
	private NhanVien nhanVienLogin;

	private TableRowSorter<DefaultTableModel> rowSorter;

	private Map<String, LinhKien> items = new HashMap<>();
	private Map<String, Integer> orderItems = new HashMap<>();

	public PanelHangCoSan(NhanVien nv) {

		try {
			componentFacade = (IComponentFacade) Naming.lookup("rmi://localhost:1341/componentFacade");
			customerFacade = (ICustomerFacade) Naming.lookup("rmi://localhost:1341/customerFacade");
			orderFacade = (IOrderFacade) Naming.lookup("rmi://localhost:1341/orderFacade");
			orderDetailFacade = (IOrderDetailFacade) Naming.lookup("rmi://localhost:1341/orderDetailFacade");
		} catch (Exception e) {
			e.printStackTrace();
		}

		initComponents();
		nhanVienLogin = nv;
	}

	private void initComponents() {
		df = new DecimalFormat("#,###");
		jLabel1 = new JLabel();
		jScrollPane1 = new JScrollPane();
		tableHangCoSan = new JTable();
		btnTaoDon = new JButton();
		btnLamMoi = new JButton();
		jLabel2 = new JLabel();

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", 0, 16));

		jLabel4 = new JLabel();

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", 0, 16));

		jScrollPane2 = new JScrollPane();
		tableDonHang = new JTable();
		jLabel5 = new JLabel();
		jLabel7 = new JLabel();

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", 0, 16));
		txtSDT.requestFocus();

		jLabel3 = new JLabel();
		txtTimKiem = new JTextField();
		jLabel6 = new JLabel();
		jLabel8 = new JLabel();
		jLabel9 = new JLabel();
		jLabel10 = new JLabel();
		txtTongTien = new JTextField();
		txtTienKhachDua = new JTextField();
		txtTienThoi = new JTextField();

		jLabel10.setFont(new Font("Tahoma", 0, 16));
		jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel10.setText("Tìm Sản Phẩm");

		jLabel1.setFont(new Font("Tahoma", 1, 24));
		jLabel1.setText("Danh sách sản phẩm tại cửa hàng");

		model = new DefaultTableModel(
				new String[] { "Mã linh kiện", "Tên linh kiện", "Thương hiệu", "Giá", "Số lượng" }, 0);

		tableHangCoSan = new JTable(model);

		rowSorter = new TableRowSorter<DefaultTableModel>(model);

		tableHangCoSan.setRowSorter(rowSorter);
		tableHangCoSan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHangCoSan.setDefaultEditor(Object.class, null);
		tableHangCoSan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableHangCoSan.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableHangCoSan.setGridColor(Color.WHITE);
		tableHangCoSan.setFocusable(false);
		tableHangCoSan.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tableHangCoSan.addMouseListener(this);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableHangCoSan.getTableHeader()
				.getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

		tableHangCoSan.setRowHeight(20);
		tableDonHang.setRowHeight(20);
		tableHangCoSan.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableHangCoSan.getColumnModel().getColumn(3).setPreferredWidth(15);
		tableHangCoSan.getColumnModel().getColumn(4).setPreferredWidth(10);

		for (int i = 0; i < tableHangCoSan.getColumnCount(); ++i) {
			if (i == 1 || i == 2)
				tableHangCoSan.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
			else if (i == 3 || i == 4)
				tableHangCoSan.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
			else
				tableHangCoSan.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		jScrollPane1.setViewportView(tableHangCoSan);
		if (tableHangCoSan.getColumnModel().getColumnCount() > 0) {
			tableHangCoSan.getColumnModel().getColumn(0).setResizable(false);
			tableHangCoSan.getColumnModel().getColumn(1).setResizable(false);
			tableHangCoSan.getColumnModel().getColumn(2).setResizable(false);
			tableHangCoSan.getColumnModel().getColumn(3).setResizable(false);
			tableHangCoSan.getColumnModel().getColumn(4).setResizable(false);
		}

		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

		txtTimKiem.setFont(new Font("Tahoma", 0, 16));
		txtTimKiem.addKeyListener(this);

		btnTaoDon.setText("Tạo đơn hàng");
		btnTaoDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTaoDon.setFocusable(false);
		btnTaoDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnTaoDon.setText("Tạo đơn hàng");
		btnTaoDon.addActionListener(this);
		btnLamMoi.setText("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLamMoiActionPerformed();
			}
		});

		jLabel2.setText("Tên Khách Hàng");
		jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 15));

		jLabel4.setText("Địa Chỉ");
		jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 15));

		jLabel7.setText("Số điện thoại");
		jLabel7.setFont(new Font("Tahoma", Font.PLAIN, 15));

		tableDonHang = new JTable(modelTaoDonHang = new DefaultTableModel(
				new String[] { "Mã LK", "Tên Linh Kiện", "Thương Hiệu", "Số Lượng", "Giá" }, 0));

		tableDonHang.setFocusable(false);
		tableDonHang.setGridColor(Color.WHITE);
		tableDonHang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableDonHang.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableDonHang.addMouseListener(this);
		tableDonHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTableCellRenderer rendererDonHang = (DefaultTableCellRenderer) tableDonHang.getTableHeader()
				.getDefaultRenderer();
		rendererDonHang.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRendererDonHang = new DefaultTableCellRenderer();
		centerRendererDonHang.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tableDonHang.getColumnCount(); ++i) {
			tableDonHang.getColumnModel().getColumn(i).setCellRenderer(centerRendererDonHang);
		}

		jScrollPane2.setViewportView(tableDonHang);

		jLabel5.setFont(new Font("Tahoma", 1, 24));
		jLabel5.setText("Tạo Đơn Hàng");

		jLabel3.setFont(new Font("Tahoma", 0, 24));
		jLabel3.setText("Danh mục");

		jLabel6.setText("Tổng thành tiền");
		jLabel6.setFont(new Font("Tahoma", 0, 16));

		jLabel8.setText("Tiền khách đưa");
		jLabel8.setFont(new Font("Tahoma", 0, 16));

		jLabel9.setText("Tiền thối lại");
		jLabel9.setFont(new Font("Tahoma", 0, 16));

		txtTongTien.setEditable(false);
		txtTongTien.setFont(new Font("Tahoma", 0, 16));
		txtTongTien.setForeground(Color.decode("#d35400"));

		txtTienKhachDua.setFont(new Font("Tahoma", 0, 16));

		txtTienThoi.setEditable(false);
		txtTienThoi.setFont(new Font("Tahoma", 0, 16));
		txtTienThoi.setForeground(Color.decode("#2ecc71"));

		modelTaoDonHang.addTableModelListener(this);

		txtTienKhachDua.addActionListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addGroup(layout.createSequentialGroup().addGap(13, 13, 13).addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE, 656,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addContainerGap()
										.addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 124,
												Short.MAX_VALUE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(txtTimKiem).addComponent(jLabel1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(125, 125, 125)))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
																.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false)
																		.addComponent(txtTongTien,
																				javax.swing.GroupLayout.Alignment.TRAILING)
																		.addComponent(txtTienKhachDua,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				280,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(jScrollPane2,
																javax.swing.GroupLayout.PREFERRED_SIZE, 0,
																Short.MAX_VALUE)
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
																.createSequentialGroup()
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(jLabel2)
																				.addGap(0, 0, Short.MAX_VALUE))
																		.addComponent(txtHoTen))
																.addGap(18, 18, 18)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false).addComponent(jLabel7)
																		.addComponent(txtSDT,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				209,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(txtDiaChi)
														.addGroup(layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jLabel4)
																.addComponent(jLabel6,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 131,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jLabel8,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 131,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jLabel9,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 131,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(0, 0, Short.MAX_VALUE))
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
																.createSequentialGroup().addGap(135, 135, 135)
																.addComponent(txtTienThoi,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 280,
																		javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
												.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel5).addGap(146, 146, 146))))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(292, 292, 292).addComponent(btnLamMoi)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnTaoDon).addGap(135, 135, 135)))
				.addContainerGap()));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE, 448,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2).addComponent(jLabel7))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(txtHoTen,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(txtSDT,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jLabel4)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
																30, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(jScrollPane2,
																javax.swing.GroupLayout.PREFERRED_SIZE, 216,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jLabel6).addComponent(txtTongTien,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jLabel8).addComponent(txtTienKhachDua,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jLabel9).addComponent(txtTienThoi,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 30,
																		javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGap(20, 20, 20)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnTaoDon).addComponent(btnLamMoi))
										.addContainerGap()));

		loadProductsData();
	}

// Chinh lai cai nay nha
	private void btnTaoDonActionPerformed() {
		KhachHang kh = null;
		try {
			kh = customerFacade.getCustomerByPhoneNumer(txtSDT.getText());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (kh != null) {
			txtHoTen.setText(kh.getHoTenKH());
			txtDiaChi.setText(kh.getDiaChiKH());
		} else {
			int confirm = JOptionPane.showConfirmDialog(null,
					"Đây là khách hàng mới, bạn có muốn thêm khách hàng mới không?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				// Kiểm tra dữ liệu và thêm khách hàng mới
			}

		}

//		KhachHang checkSdt = null;
//		KhachHang cus = null;
//		String newBillID = Integer.parseInt(hoaDonDAO.getLastBillID()) + 1 + "";
//		if (checkSL() && validateCusData()) {
//			long millis = System.currentTimeMillis();
//			java.sql.Date ngayLapHoaDon = new java.sql.Date(millis);
//			try {
//				checkSdt = khachHangDAO.getOneCusBySdt(txtSDT.getText().trim());
//			} catch (Exception e) {
//				checkSdt = null;
//			}
//			if (checkSdt != null) {
//				cus = checkSdt;
//				if (JOptionPane.showConfirmDialog(null,
//						"Số điện thoại đã có trong hệ thống có muốn thay đổi thông tin ??", "",
//						JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
//					HoaDon hoadon = new HoaDon(newBillID, nhanVienLogin, cus, ngayLapHoaDon);
//					try {
//						hoaDonDAO.addNewBill(hoadon);
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//					for (int i = 0; i < tableDonHang.getRowCount(); i++) {
//						LinhKien lk = null;
//						try {
//							lk = linhKienDao.getComponentByID((String) tableDonHang.getValueAt(i, 0));
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						int soluong = Integer.parseInt((String) tableDonHang.getValueAt(i, 4));
//						ChiTietHoaDon cthd = new ChiTietHoaDon(hoadon, lk, soluong);
//						try {
//							chiTietHoaDonDAO.addNewBillDetail(cthd);
//							lk.setSoLuongTon(lk.getSoLuongTon() - soluong);
//							linhKienDao.updateComponentByID((String) tableDonHang.getValueAt(i, 0), lk);
//						} catch (SQLException e) {
//							e.printStackTrace();
//
//						}
//					}
//				} else {
//					cus = new KhachHang(cus.getMaKH(), txtHoTen.getText().trim(), txtSDT.getText().trim(),
//							txtDiaChi.getText().trim());
//					try {
//						khachHangDAO.updateCustomerInfoByID(cus.getMaKH(), cus);
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//					HoaDon hoadon = new HoaDon(newBillID, nhanVienLogin, cus, ngayLapHoaDon);
//					try {
//						hoaDonDAO.addNewBill(hoadon);
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//					for (int i = 0; i < tableDonHang.getRowCount(); i++) {
//						LinhKien lk = null;
//						try {
//							lk = linhKienDao.getComponentByID((String) tableDonHang.getValueAt(i, 0));
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						int soluong = Integer.parseInt((String) tableDonHang.getValueAt(i, 4));
//						ChiTietHoaDon cthd = new ChiTietHoaDon(hoadon, lk, soluong);
//						try {
//							chiTietHoaDonDAO.addNewBillDetail(cthd);
//							lk.setSoLuongTon(lk.getSoLuongTon() - soluong);
//							linhKienDao.updateComponentByID((String) tableDonHang.getValueAt(i, 0), lk);
//						} catch (SQLException e) {
//							e.printStackTrace();
//
//						}
//					}
//				}
//			} else {
//				String cusID = Integer.parseInt(khachHangDAO.getLastCustomerID()) + 1 + "";
//				cus = new KhachHang(cusID, txtHoTen.getText().trim(), txtSDT.getText().trim(),
//						txtDiaChi.getText().trim());
//				HoaDon hoadon = new HoaDon(newBillID, nhanVienLogin, cus, ngayLapHoaDon);
//				try {
//					khachHangDAO.addCustomer(cus);
//					hoaDonDAO.addNewBill(hoadon);
//				} catch (SQLException e) {
//					e.printStackTrace();
//
//				}
//				for (int i = 0; i < tableDonHang.getRowCount(); i++) {
//					LinhKien lk = null;
//					try {
//						lk = linhKienDao.getComponentByID((String) tableDonHang.getValueAt(i, 0));
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//					int soluong = Integer.parseInt((String) tableDonHang.getValueAt(i, 4));
//					ChiTietHoaDon cthd = new ChiTietHoaDon(hoadon, lk, soluong);
//					try {
//						chiTietHoaDonDAO.addNewBillDetail(cthd);
//						lk.setSoLuongTon(lk.getSoLuongTon() - soluong);
//						linhKienDao.updateComponentByID((String) tableDonHang.getValueAt(i, 0), lk);
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//
//			}
//			JOptionPane.showMessageDialog(this, "Thêm thành công đơn hàng");
//			clear();
//		}
	}

	private void clear() {
		txtHoTen.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtTienKhachDua.setText("");
		txtTongTien.setText("");
		txtTienThoi.setText("");

		modelTaoDonHang.setRowCount(0);

		loadProductsData();
	}

	private boolean validateCusData() {
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String tienTra = txtTienKhachDua.getText().trim();
		String tienThoi = txtTienThoi.getText().trim();

		if (hoTen.trim().equals("")) {
			showMsg("Họ tên khách hàng trống !");
			txtHoTen.selectAll();
			txtHoTen.requestFocus();
			return false;
		} else {

			if ((hoTen.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				showMsg("tên khách hàng không hợp lệ");
				txtHoTen.requestFocus();
				txtHoTen.selectAll();
				return false;
			}
		}

		if (sdt.trim().equals("")) {
			showMsg("Số điện thoại khách hàng trống !");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		} else {

			if (!(sdt.matches("^[0-9]{10}$"))) {
				showMsg("Số điện thoại khách hàng không hợp lệ");
				txtSDT.requestFocus();
				txtSDT.selectAll();
				return false;
			}
		}

		if (diaChi.trim().equals("")) {
			showMsg("Địa chỉ không được trống !");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		} else {

			if (diaChi.matches(
					"^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]")) {
				showMsg("Địa chỉ không hợp lê");
				txtDiaChi.requestFocus();
				txtDiaChi.selectAll();
				return false;

			}
		}

		if (!(tienTra.length() > 0)) {
			showMsg("Chưa nhập tiền khách trả");
			txtTienKhachDua.selectAll();
			txtTienKhachDua.requestFocus();
			return false;
		}

		if (!(tienThoi.length() > 0)) {
			showMsg("Nhập lại tiền khách trả");
			txtTienKhachDua.selectAll();
			txtTienKhachDua.requestFocus();
			return false;
		}

		return true;
	}

	private void btnLamMoiActionPerformed() {
		if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn làm mới đơn hàng?", "Xác nhận",
				JOptionPane.YES_NO_OPTION) == 0) {
			clearModel();
			tableHangCoSan.clearSelection();
		}
	}

	private void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	private void clearModel() {
		modelTaoDonHang.setRowCount(0);

		txtHoTen.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");

		txtTienKhachDua.setText("");
		txtTongTien.setText("");
		txtTienThoi.setText("");
	}

	private boolean checkSL() {
		int rowCount = modelTaoDonHang.getRowCount();
		if (rowCount > 0) {
			for (int i = 0; i < rowCount; i++) {
				if (((String) modelTaoDonHang.getValueAt(i, 4)).equals("")) {
					JOptionPane.showMessageDialog(this,
							"Sản phẩm " + (String) modelTaoDonHang.getValueAt(i, 0) + " chưa confirm số lượng");
					return false;
				}
			}
		}
		return true;
	}

	private void loadProductsData() {
		model.getDataVector().removeAllElements();
		List<LinhKien> comps = null;
		try {
			comps = componentFacade.getComponents();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (LinhKien comp : comps) {
			items.put(comp.getMaLinhKien(), comp);
			model.addRow(new Object[] { comp.getMaLinhKien(), comp.getTenLinhKien(), comp.getThuongHieu(),
					currencyFormat(comp.getDonGia()), comp.getSoLuongTon() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTaoDon))
			btnTaoDonActionPerformed();
		else if (o.equals(btnLamMoi))
			btnLamMoiActionPerformed();
		else if (o.equals(txtTienKhachDua)) {
			if (tinhTienThoi())
				txtTienKhachDua.setText(df.format(Double.parseDouble(txtTienKhachDua.getText().trim())));
		}
	}

	public double tongTien() {
		double tongTien = 0;
		int soLuongMH = modelTaoDonHang.getRowCount();
		
		for (int i = 0; i < soLuongMH; i++) {
			tongTien += Integer.parseInt((String) modelTaoDonHang.getValueAt(i, 3))
					* Double.parseDouble(modelTaoDonHang.getValueAt(i, 4).toString().replace(",", ""));
		}

		return tongTien;
	}

	public boolean tinhTienThoi() {
		if (!txtTongTien.getText().equals("")) {
			String tienKT = txtTienKhachDua.getText().trim();
			double tienKhachTra = 0;
			double tongTien = tongTien();
			try {
				tienKhachTra = Double.parseDouble(tienKT);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Tiền khách trả không hợp lệ");
				return false;
			}

			if (tienKT.matches("^\\d+$") && tienKhachTra >= tongTien) {
				txtTienThoi.setText(df.format(tienKhachTra - tongTien));
			} else {
				JOptionPane.showMessageDialog(this, "Số tiền trả Không đủ");
				txtTienKhachDua.selectAll();
				txtTienKhachDua.requestFocus();
				return false;
			}
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tableHangCoSan))
			addOrderItem(e.getButton(), e.getClickCount());
		else if (e.getSource().equals(tableDonHang))
			removeOrderItem(e.getButton(), e.getClickCount());
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
		String keyword = txtTimKiem.getText().trim();
		rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
	}

	private String currencyFormat(double donGia) {
		return new DecimalFormat("#,###").format(donGia);
	}

	private void addOrderItem(int mouseClick, int clickCount) {

		CountDownLatch latch = new CountDownLatch(1);

		int row = tableHangCoSan.getSelectedRow();

		if (mouseClick == 1 && clickCount == 2) {

			String compID = tableHangCoSan.getValueAt(row, 0).toString().trim();
			int modifyRow = getRowIndexByCompID(compID);
			int currentQuantity = Integer.parseInt(model.getValueAt(row, 4).toString());

			if (modifyRow == -1) {

				String orderQuantity = JOptionPane.showInputDialog(null, "Nhập số lượng khách muốn mua", "1");

				if (orderQuantity == null || orderQuantity.isEmpty())
					return;
				else {

					if (!orderQuantity.matches("\\d+"))
						orderQuantity = "1";

					int quantity = Integer.parseInt(orderQuantity);

					if (quantity > currentQuantity) {
						showMsg(String.format("Số lượng sản phẩm chỉ còn %d", currentQuantity));
						return;
					}

					orderItems.put(compID,
							orderItems.containsKey(compID) ? orderItems.get(compID) + quantity : quantity);

					Thread addItemThread = new Thread(() -> {
						modelTaoDonHang.addRow(new Object[] { tableHangCoSan.getValueAt(row, 0),
								tableHangCoSan.getValueAt(row, 1), tableHangCoSan.getValueAt(row, 2),
								orderItems.get(compID) + "", tableHangCoSan.getValueAt(row, 3) });
						latch.countDown();
					});

					addItemThread.start();
					try {
						latch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				if (orderItems.get(compID) >= currentQuantity) {
					showMsg("Đã đạt giới hạn số lượng sản phẩm có thể đặt đối với mặt hàng này!");
					return;
				}
				orderItems.put(compID, orderItems.get(compID) + 1);
				tableDonHang.setValueAt(orderItems.get(compID) + "", modifyRow, 3);
			}
		}
	}

	private void removeOrderItem(int mouseClick, int clickCount) {
		int row = tableDonHang.getSelectedRow();

		if (row == -1)
			return;

		if (mouseClick == 3 && clickCount == 1) {
			int confirm = JOptionPane.showConfirmDialog(null, "Hủy đặt sản phẩm này?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				orderItems.remove(tableDonHang.getValueAt(row, 0).toString());
				modelTaoDonHang.removeRow(row);
			}
		}
	}

	private int getRowIndexByCompID(String maLK) {
		int len = tableDonHang.getRowCount();
		for (int i = 0; i < len; i++)
			if (tableDonHang.getValueAt(i, 0).toString().equals(maLK))
				return i;
		return -1;
	}
	
	private int findRowByValue(String value) {
		int len = tableHangCoSan.getRowCount();
		for(int i = 0; i < len; i++)
			if(tableHangCoSan.getValueAt(i, 0).toString().equalsIgnoreCase(value))
				return i;
		return -1;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		SwingUtilities.invokeLater(() -> {
			if (tableDonHang.getSelectedRow() != -1) {
				int i = tableDonHang.getSelectedRow();
				String maLK = tableDonHang.getValueAt(i, 0).toString();
				
				if(!isValidOrderQuantity(tableDonHang.getValueAt(i, 3).toString())) {
					showMsg(String.format("Số lượng đặt phải là số! Vui lòng kiểm tra lại!"));
					return;
				}
				
				int soLuongDat = Integer.valueOf(tableDonHang.getValueAt(i, 3).toString());
				int soLuongHienTai = Integer.valueOf(tableHangCoSan.getValueAt(findRowByValue(maLK), 4).toString());
				
				if(soLuongDat > soLuongHienTai) {
					showMsg(String.format("Sản phẩm này chỉ có %s mặt hàng, không thể đặt quá số lượng trên!", soLuongHienTai));
					orderItems.put(maLK, 1);
					tableDonHang.setValueAt(1+"", i, 3);
					return;
				}
				
				orderItems.put(maLK, soLuongDat);
			}
			
			txtTongTien.setText(String.valueOf(df.format(tongTien())));
		});
	}
	
	private boolean isValidOrderQuantity(String quantity) {
		return quantity.matches("\\d+");
	}
}