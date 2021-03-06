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
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;
import facade.IComponentFacade;
import facade.ICustomerFacade;
import facade.IEmployeeFacade;
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
	private KhachHang khachHang = null;

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
		tableDonHang = new JTable();
		btnTaoDon = new JButton();
		btnLamMoi = new JButton();
		jLabel2 = new JLabel();

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", 0, 16));

		jLabel4 = new JLabel();

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", 0, 16));

		jScrollPane2 = new JScrollPane();
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
		txtTienKhachDua = new JFormattedTextField();
		txtTienThoi = new JTextField();

		jLabel10.setFont(new Font("Tahoma", 0, 16));
		jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel10.setText("T??m S???n Ph???m");

		jLabel1.setFont(new Font("Tahoma", 1, 24));
		jLabel1.setText("Danh s??ch s???n ph???m t???i c???a h??ng");

		model = new DefaultTableModel(
				new String[] { "M?? Linh Ki???n", "T??n Linh Ki???n", "Th????ng Hi???u", "Gi??", "S??? L?????ng" }, 0);

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
			if (i == 1)
				tableHangCoSan.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
			else if (i == 3 || i == 4)
				tableHangCoSan.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
			else
				tableHangCoSan.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		jScrollPane1.setViewportView(tableHangCoSan);
		if (tableHangCoSan.getColumnModel().getColumnCount() > 0) {
			tableHangCoSan.getColumnModel().getColumn(0).setPreferredWidth(50);
			tableHangCoSan.getColumnModel().getColumn(1).setPreferredWidth(200);
			tableHangCoSan.getColumnModel().getColumn(2).setPreferredWidth(80);
			tableHangCoSan.getColumnModel().getColumn(3).setPreferredWidth(40);
			tableHangCoSan.getColumnModel().getColumn(4).setPreferredWidth(40);
		}

		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

		txtTimKiem.setFont(new Font("Tahoma", 0, 16));
		txtTimKiem.addKeyListener(this);

		btnTaoDon.setText("T???o ????n h??ng");
		btnTaoDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTaoDon.setFocusable(false);
		btnTaoDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnTaoDon.setText("T???o ????n h??ng");
		btnTaoDon.addActionListener(this);
		btnLamMoi.setText("L??m m???i");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnLamMoiActionPerformed();
			}
		});

		jLabel2.setText("T??n Kh??ch H??ng");
		jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 15));

		jLabel4.setText("?????a Ch???");
		jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 15));

		jLabel7.setText("S??? ??i???n tho???i");
		jLabel7.setFont(new Font("Tahoma", Font.PLAIN, 15));

		modelTaoDonHang = new DefaultTableModel(
				new String[] { "M?? LK", "T??n Linh Ki???n", "Th????ng Hi???u", "S??? L?????ng", "T???ng Ti???n" }, 0);

		tableDonHang = new JTable(modelTaoDonHang) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex != 3)
					return false;
				return true;
			}
		};

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
		jLabel5.setText("T???o ????n H??ng");

		jLabel3.setFont(new Font("Tahoma", 0, 24));
		jLabel3.setText("Danh m???c");

		jLabel6.setText("T???ng th??nh ti???n");
		jLabel6.setFont(new Font("Tahoma", 0, 16));

		jLabel8.setText("Ti???n kh??ch ????a");
		jLabel8.setFont(new Font("Tahoma", 0, 16));

		jLabel9.setText("Ti???n th???i l???i");
		jLabel9.setFont(new Font("Tahoma", 0, 16));

		txtTongTien.setEditable(false);
		txtTongTien.setFont(new Font("Tahoma", 0, 16));
		txtTongTien.setForeground(Color.decode("#d35400"));

		txtTienKhachDua.setFont(new Font("Tahoma", 0, 16));

		txtTienThoi.setEditable(false);
		txtTienThoi.setFont(new Font("Tahoma", 0, 16));
		txtTienThoi.setForeground(Color.decode("#2ecc71"));
		addAction();
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
								.addGroup(layout.createSequentialGroup().addGap(13, 13, 13).addComponent(jScrollPane1,
										GroupLayout.PREFERRED_SIZE, 656, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addContainerGap()
										.addComponent(jLabel10, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
												.addComponent(txtTimKiem).addComponent(jLabel1,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addGap(125, 125, 125)))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
										.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
												GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addGroup(
														layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
																.addComponent(txtTongTien,
																		GroupLayout.Alignment.TRAILING)
																.addComponent(txtTienKhachDua,
																		GroupLayout.Alignment.TRAILING,
																		GroupLayout.PREFERRED_SIZE, 280,
																		GroupLayout.PREFERRED_SIZE)))
												.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 0,
														Short.MAX_VALUE)
												.addGroup(GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(jLabel2)
																		.addGap(0, 0, Short.MAX_VALUE))
																.addComponent(txtHoTen)).addGap(18, 18, 18)
																.addGroup(layout
																		.createParallelGroup(
																				GroupLayout.Alignment.LEADING, false)
																		.addComponent(jLabel7).addComponent(txtSDT,
																				GroupLayout.PREFERRED_SIZE, 209,
																				GroupLayout.PREFERRED_SIZE)))
												.addComponent(txtDiaChi)
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(jLabel4)
																.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 131,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 131,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 131,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(0, 0, Short.MAX_VALUE))
												.addGroup(GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup().addGap(135, 135, 135)
																.addComponent(txtTienThoi, GroupLayout.PREFERRED_SIZE,
																		280, GroupLayout.PREFERRED_SIZE))))
										.addGroup(GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup()
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jLabel5).addGap(146, 146, 146))))
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(292, 292, 292).addComponent(btnLamMoi)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnTaoDon).addGap(135, 135, 135)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
								.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2).addComponent(jLabel7))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel4)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 216,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6).addComponent(txtTongTien,
														GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel8).addComponent(txtTienKhachDua,
														GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel9).addComponent(txtTienThoi,
														GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
						.addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(btnTaoDon).addComponent(btnLamMoi))
						.addContainerGap()));
		loadProductsData();
	}

	private void addAction() {
		txtSDT.addKeyListener(this);
		modelTaoDonHang.addTableModelListener(this);
		txtTienKhachDua.addActionListener(this);
	}

	private void btnTaoDonActionPerformed() {
		if (!validateCusData())
			return;

		if (khachHang == null) {

			try {
				if (!ktraSDT())
					return;
				long maKhachHang = 0;
				maKhachHang = Long.parseLong(customerFacade.getLastCustomerID());
				maKhachHang++;
				khachHang = new KhachHang(String.valueOf(maKhachHang), txtHoTen.getText().trim(),
						txtSDT.getText().trim(), txtDiaChi.getText().trim());
				customerFacade.addCustomer(khachHang);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String newBillID;
		HoaDon hoadon = null;

		if (tableDonHang.getRowCount() < 1) {
			showMsg("Vui l??ng ch???n s???n ph???m c???n mua!");
			return;
		}

		if (!tinhTienThoi()) {
			return;
		}

		try {
			newBillID = Integer.parseInt(orderFacade.getLastBillID()) + 1 + "";
			LocalDate ngayLapHoaDon = LocalDate.now();
			hoadon = new HoaDon(newBillID, nhanVienLogin, khachHang, ngayLapHoaDon);
			orderFacade.addNewBill(hoadon);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < tableDonHang.getRowCount(); i++) {
			LinhKien lk = null;
			try {
				lk = componentFacade.getComponentByID((String) tableDonHang.getValueAt(i, 0));
				int soluong = Integer.parseInt(tableDonHang.getValueAt(i, 3).toString());
				ChiTietHoaDon cthd = new ChiTietHoaDon(hoadon, lk, soluong);
				orderDetailFacade.addNewBillDetail(cthd);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng ????n h??ng");

		clear();
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

	private boolean ktraSDT() throws Exception {
		String sdt = txtSDT.getText().trim();
		String pattern = "^(032|033|034|035|036|037|038|039|086|096|097|098|" + "070|079|077|076|078|089|090|093|"
				+ "083|084|085|081|082|088|091|094|" + "056|058|092|" + "059|099)[0-9]{7}$";

		if (!(sdt.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p s??? ??i???n tho???i");
			txtSDT.requestFocus();
			return false;
		}

		if (!(sdt.matches(pattern))) {
			JOptionPane.showMessageDialog(this, "SDT kh??ng h???p l???");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}

		List<KhachHang> list = customerFacade.getCustomers();
		for (KhachHang kh : list) {
			if (kh.getSoDienThoaiKH().equals(sdt)) {
				JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i ???? thu???c v??? 1 kh??ch h??ng kh??c");
				txtSDT.selectAll();
				txtSDT.requestFocus();
				return false;
			}
		}
		return true;
	}

	private boolean validateCusData() {
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();

		if (hoTen.trim().equals("")) {
			showMsg("H??? t??n kh??ch h??ng tr???ng !");
			txtHoTen.selectAll();
			txtHoTen.requestFocus();
			return false;
		} else {
			if ((hoTen.matches(
					"^[a-zA-Z ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????]"))) {
				showMsg("t??n kh??ch h??ng kh??ng h???p l???");
				txtHoTen.requestFocus();
				txtHoTen.selectAll();
				return false;
			}
		}

		if (sdt.trim().equals("")) {
			showMsg("S??? ??i???n tho???i kh??ch h??ng tr???ng !");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		} else {
			if (!(sdt.matches("^(032|033|034|035|036|037|038|039|086|096|097|098|" + "070|079|077|076|078|089|090|093|"
					+ "083|084|085|081|082|088|091|094|" + "056|058|092|" + "059|099)[0-9]{7}$"))) {

				showMsg("S??? ??i???n tho???i kh??ch h??ng kh??ng h???p l???\nS??? ??i???n tho???i c?? 10 s???, b???t ?????u b???ng c??c ?????u s??? c???a nh?? m???ng VN");
				txtSDT.requestFocus();
				txtSDT.selectAll();
				return false;
			}
		}

		if (diaChi.trim().equals("")) {
			showMsg("?????a ch??? kh??ng ???????c tr???ng !");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		} else {
			if (diaChi.matches(
					"^[a-z0-9A-Z ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????]")) {
				showMsg("?????a ch??? kh??ng h???p l??");
				txtDiaChi.requestFocus();
				txtDiaChi.selectAll();
				return false;
			}
		}

		return true;
	}

	private void btnLamMoiActionPerformed() {
		if (JOptionPane.showConfirmDialog(null, "B???n c?? ch???c mu???n l??m m???i ????n h??ng?", "X??c nh???n",
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
			if (tinhTienThoi()) {
				String tienKT = txtTienKhachDua.getText().trim().replaceAll("[^0-9]", "");

				if (tienKT.isEmpty()) {
					showMsg("Ti???n kh??ch ????a kh??ng h???p l???! Vui l??ng ki???m tra l???i!");
					txtTienKhachDua.requestFocus();
					return;
				}

				txtTienKhachDua.setText(
						df.format(Double.parseDouble(txtTienKhachDua.getText().trim().replaceAll("[^0-9]", ""))));
			}
		}
	}

	public double tongTien() {
		double tongTien = 0;
		int soLuongMH = modelTaoDonHang.getRowCount();

		for (int i = 0; i < soLuongMH; i++) {
			tongTien += Double.parseDouble(modelTaoDonHang.getValueAt(i, 4).toString().replaceAll("[^0-9]", ""));
		}

		return tongTien;
	}

	public boolean tinhTienThoi() {
		if (!txtTongTien.getText().equals("")) {
			String tienKT = txtTienKhachDua.getText().trim();

			if (tienKT.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Ti???n kh??ch tr??? kh??ng h???p l???!");
				txtTienKhachDua.selectAll();
				txtTienKhachDua.requestFocus();
				return false;
			}

			tienKT = tienKT.replaceAll("[^0-9]", "");

			if (tienKT.isEmpty())
				tienKT = "-1";

			double tienKhachTra = 0;
			double tongTien = tongTien();
			try {
				tienKhachTra = Double.parseDouble(tienKT);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Ti???n kh??ch tr??? kh??ng h???p l???!");
				txtTienKhachDua.selectAll();
				txtTienKhachDua.requestFocus();
				e.printStackTrace();
				return false;
			}

			if (tienKT.matches("^\\d+$")) {
				if (tienKhachTra >= tongTien) {
					txtTienThoi.setText(df.format(tienKhachTra - tongTien));
				} else {
					txtTienKhachDua.setText(
							df.format(Double.parseDouble(txtTienKhachDua.getText().trim().replaceAll("[^0-9]", ""))));
					JOptionPane.showMessageDialog(this, "S??? ti???n kh??ch tr??? Kh??ng ?????! Vui l??ng ki???m tra l???i!");
					txtTienKhachDua.selectAll();
					txtTienKhachDua.requestFocus();
					return false;
				}
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
		Object obj = e.getSource();
		if (obj.equals(txtSDT)) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					khachHang = customerFacade.getCustomerByPhoneNumer(txtSDT.getText().trim());
					if (khachHang != null) {
						txtDiaChi.setText(khachHang.getDiaChiKH());
						txtHoTen.setText(khachHang.getHoTenKH());
					} else {
						JOptionPane.showMessageDialog(this,
								"S??? ??i???n tho???i kh??ng t???n t???i!\nVui l??ng th??m kh??ch h??ng m???i!");
						txtDiaChi.setText("");
						txtHoTen.setText("");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			String keyword = txtTimKiem.getText().trim();
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
		}
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

				String orderQuantity = JOptionPane.showInputDialog(null, "Nh???p s??? l?????ng kh??ch mu???n mua", "1");

				if (orderQuantity == null || orderQuantity.isEmpty())
					return;
				else {

					if (!orderQuantity.matches("\\d+"))
						orderQuantity = "1";

					int quantity = Integer.parseInt(orderQuantity);

					if (quantity > currentQuantity) {
						showMsg(String.format("S??? l?????ng c???a m???t h??ng n??y ch??? c??n %d!", currentQuantity));
						return;
					}

					orderItems.put(compID,
							orderItems.containsKey(compID) ? orderItems.get(compID) + quantity : quantity);
					double tongTienSP = Double
							.parseDouble(tableHangCoSan.getValueAt(row, 3).toString().replaceAll("[^0-9]", ""));
					Thread addItemThread = new Thread(() -> {
						modelTaoDonHang.addRow(new Object[] { tableHangCoSan.getValueAt(row, 0),
								tableHangCoSan.getValueAt(row, 1), tableHangCoSan.getValueAt(row, 2),
								orderItems.get(compID) + "", df.format(quantity * tongTienSP) });

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
					showMsg("???? ?????t gi???i h???n s??? l?????ng s???n ph???m c?? th??? ?????t ?????i v???i m???t h??ng n??y!");
					return;
				}
				orderItems.put(compID, orderItems.get(compID) + 1);
				tableDonHang.setValueAt(orderItems.get(compID) + "", modifyRow, 3);
			}

			updateTableDonHang();
		}
	}

	private void removeOrderItem(int mouseClick, int clickCount) {
		int row = tableDonHang.getSelectedRow();

		if (row == -1)
			return;

		if (mouseClick == 3 && clickCount == 1) {
			int confirm = JOptionPane.showConfirmDialog(null, "B???n c?? ch???c mu???n h???y ?????t s???n ph???m n??y?", "X??c nh???n",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				orderItems.remove(tableDonHang.getValueAt(row, 0).toString());
				modelTaoDonHang.removeRow(row);
			}

			updateTableDonHang();
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
		for (int i = 0; i < len; i++)
			if (tableHangCoSan.getValueAt(i, 0).toString().equalsIgnoreCase(value))
				return i;
		return -1;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		SwingUtilities.invokeLater(() -> {
			if (tableDonHang.getSelectedRow() != -1) {
				int i = tableDonHang.getSelectedRow();
				String maLK = tableDonHang.getValueAt(i, 0).toString();

				if (!isValidOrderQuantity(tableDonHang.getValueAt(i, 3).toString())) {
					showMsg(String.format("S??? l?????ng ?????t ph???i l?? s???! Vui l??ng ki???m tra l???i!"));
					tableDonHang.setValueAt("1", i, 3);
					orderItems.put(maLK, 1);
					return;
				}

				int soLuongDat = Integer.valueOf(tableDonHang.getValueAt(i, 3).toString());

				if (soLuongDat == 0) {
					orderItems.remove(tableDonHang.getValueAt(i, 0).toString());
					modelTaoDonHang.removeRow(i);
					updateTableDonHang();
					return;
				}

				int soLuongHienTai = Integer.valueOf(tableHangCoSan.getValueAt(findRowByValue(maLK), 4).toString());
				double tongTienSP = Double
						.parseDouble(tableHangCoSan.getValueAt(findRowByValue(maLK), 3).toString().replaceAll("[^0-9]", ""));
				if (soLuongDat > soLuongHienTai) {
					showMsg(String.format("S???n ph???m n??y ch??? c?? %s m???t h??ng, kh??ng th??? ?????t qu?? s??? l?????ng tr??n!",
							soLuongHienTai));
					orderItems.put(maLK, 1);
					tableDonHang.setValueAt(1 + "", i, 3);
					return;
				}

				tableDonHang.setValueAt(df.format(tongTienSP * soLuongDat), i, 4);
				orderItems.put(maLK, soLuongDat);

				txtTongTien.setText(String.valueOf(df.format(tongTien())));
				tableDonHang.clearSelection();
			}
		});
	}

	private boolean isValidOrderQuantity(String quantity) {
		return quantity.matches("\\d+");
	}

	private void updateTableDonHang() {
		int len = modelTaoDonHang.getRowCount();

		for (int i = 0; i < len; i++) {
			String maLK = tableDonHang.getValueAt(i, 0).toString();
			int soLuongDat = Integer.valueOf(tableDonHang.getValueAt(i, 3).toString());
			double tongTienSP = Double
					.parseDouble(tableHangCoSan.getValueAt(findRowByValue(maLK), 3).toString().replaceAll("[^0-9]", ""));
			tableDonHang.setValueAt(df.format(tongTienSP * soLuongDat), i, 4);
		}

		txtTongTien.setText(String.valueOf(df.format(tongTien())));
	}
}