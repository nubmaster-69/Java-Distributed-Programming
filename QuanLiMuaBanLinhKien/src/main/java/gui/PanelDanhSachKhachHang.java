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
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entity.KhachHang;
import facade.ICustomerFacade;

public class PanelDanhSachKhachHang extends JPanel implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JButton btnChinhSua;
	private JButton btnXoa;

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel12;

	private JTextField txtDiaChi;
	private JTextField txtMaKhachHang;
	private JTextField txtSDT;
	private JTextField txtTenKhachHang;
	private JTextField txtTimKiem;

	private JTable tableDanhSachKhachHang;
	private JScrollPane jScrollPane1;
	private DefaultTableModel model;
	private TableRowSorter<DefaultTableModel> rowSorter;

	ICustomerFacade customerFacade;

	public PanelDanhSachKhachHang() {
		try {
			customerFacade = (ICustomerFacade) Naming.lookup("rmi://localhost:1341/customerFacade");
		} catch (Exception e) {
			e.printStackTrace();
		}

		initComponents();
	}

	private void initComponents() {
		jScrollPane1 = new JScrollPane();

		btnChinhSua = new JButton();
		jLabel1 = new JLabel();
		btnXoa = new JButton();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 16));

		jLabel4 = new JLabel();
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 16));

		jLabel5 = new JLabel();

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("SansSerif", Font.PLAIN, 16));

		jLabel6 = new JLabel();
		jLabel7 = new JLabel();

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 16));

		jLabel12 = new JLabel();
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 16));

		model = new DefaultTableModel(new String[] { "M?? Kh??ch H??ng", "T??n Kh??ch H??ng", "S??? ??T", "?????a Ch???" }, 0);
		tableDanhSachKhachHang = new JTable(model);
		rowSorter = new TableRowSorter<>(model);
		tableDanhSachKhachHang.setRowSorter(rowSorter);

		tableDanhSachKhachHang.addMouseListener(this);
		tableDanhSachKhachHang.setRowHeight(20);
		tableDanhSachKhachHang.setDefaultEditor(Object.class, null);
		tableDanhSachKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tableDanhSachKhachHang.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));

		tableDanhSachKhachHang.setGridColor(Color.WHITE);
		tableDanhSachKhachHang.setFocusable(false);

		tableDanhSachKhachHang.addMouseListener(this);

		datHanhDongChoTxtDiaChi();
		datHanhDongChoTxtHoTen();
		datHanhDongChoTxtSDT();

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableDanhSachKhachHang.getTableHeader()
				.getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

		for (int i = 0; i < tableDanhSachKhachHang.getColumnCount(); ++i) {
			if (i == 0 || i == 2)
				tableDanhSachKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			else
				tableDanhSachKhachHang.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
		}

		tableDanhSachKhachHang.setCursor(new Cursor(Cursor.HAND_CURSOR));

		jScrollPane1.setViewportView(tableDanhSachKhachHang);
		if (tableDanhSachKhachHang.getColumnModel().getColumnCount() > 0) {
			tableDanhSachKhachHang.getColumnModel().getColumn(0).setResizable(false);
			tableDanhSachKhachHang.getColumnModel().getColumn(1).setResizable(false);
			tableDanhSachKhachHang.getColumnModel().getColumn(2).setResizable(false);
			tableDanhSachKhachHang.getColumnModel().getColumn(3).setResizable(false);
		}

		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

		btnChinhSua.setText("Ch???nh s???a");
		btnChinhSua.setFocusable(false);
		btnChinhSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnChinhSua.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnChinhSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnChinhSuaActionPerformed();
			}
		});

		jLabel1.setFont(new Font("SansSerif", 1, 24));
		jLabel1.setText("Danh S??ch Kh??ch H??ng");

		btnXoa.setText("X??a");
		btnXoa.setFocusable(false);
		btnXoa.setForeground(new Color(240, 240, 240));
		btnXoa.setOpaque(false);
		btnXoa.setContentAreaFilled(false);
		btnXoa.setBorderPainted(false);
		btnXoa.setFocusable(false);
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 16));

		txtTimKiem.addKeyListener(this);

		jLabel2.setFont(new Font("SansSerif", 1, 24));
		jLabel2.setText("Th??ng Tin Kh??ch H??ng");

		jLabel3.setFont(new Font("SansSerif", 0, 24));
		jLabel3.setText("T??n kh??ch h??ng");

		jLabel4.setFont(new Font("SansSerif", 0, 24));
		jLabel4.setText("M?? kh??ch h??ng");

		txtMaKhachHang.setEditable(false);

		jLabel5.setFont(new Font("SansSerif", 0, 24));
		jLabel5.setText("S??? ??i???n tho???i");

		jLabel6.setFont(new Font("SansSerif", 0, 24));
		jLabel6.setText("?????a ch???");

		jLabel7.setFont(new Font("SansSerif", 0, 16));
		jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel7.setText("T??m Kh??ch H??ng");

		jLabel12.setFont(new Font("SansSerif", 0, 24));
		jLabel12.setText("Danh m???c");

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 799, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addGap(365, 365, 365).addComponent(btnXoa)))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
								.createSequentialGroup()
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
								.addComponent(btnChinhSua).addGap(107, 107, 107))
								.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(jLabel3).addComponent(jLabel4).addComponent(jLabel5)
												.addComponent(jLabel6).addComponent(txtSDT).addComponent(txtMaKhachHang)
												.addComponent(txtTenKhachHang, GroupLayout.Alignment.TRAILING)
												.addComponent(txtDiaChi, GroupLayout.Alignment.TRAILING))
										.addContainerGap())))
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addGap(62, 62, 62)
								.addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel1)
								.addGap(220, 220, 220)
								.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
								.addComponent(jLabel2))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout
												.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(btnChinhSua).addComponent(btnXoa)))
								.addGroup(layout.createSequentialGroup().addComponent(jLabel3)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txtTenKhachHang, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel4)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txtMaKhachHang, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(jLabel5)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(jLabel6)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));

		loadCustomersData();
	}

	private void btnXoaActionPerformed() {
		int confirm = JOptionPane.showConfirmDialog(null, "B???n c?? ch???c ch???n mu???n x??a?", "X??c nh???n",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (confirm == 0) {
			while (tableDanhSachKhachHang.getSelectedRowCount() != 0) {
				int selectedRow = tableDanhSachKhachHang.getSelectedRow();

				String maKH = tableDanhSachKhachHang.getValueAt(selectedRow, 0).toString();
				try {
					if (customerFacade.removeCustomerByID(maKH)) {
						selectedRow = tableDanhSachKhachHang.convertRowIndexToModel(selectedRow);
						model.removeRow(selectedRow);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}

		txtDiaChi.setText("");
		txtMaKhachHang.setText("");
		txtSDT.setText("");
		txtTenKhachHang.setText("");
	}

	private void btnChinhSuaActionPerformed() {
		sua();
	}

	private void loadCustomersData() {
		model.getDataVector().removeAllElements();
		List<KhachHang> customers = null;
		try {
			customers = customerFacade.getCustomers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		customers.forEach(cus -> {
			model.addRow(
					new Object[] { cus.getMaKhachHang(), cus.getHoTenKH(), cus.getSoDienThoaiKH(), cus.getDiaChiKH() });
		});
	}

	private boolean regex() {
		String tenKH = txtTenKhachHang.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();

		if (!(tenKH.length() > 0 && sdt.length() > 0 && diaChi.length() > 0 && sdt.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p ????? c??c tr?????ng th??ng tin!");
		}

		if (!(tenKH.length() > 0)) {
			txtTenKhachHang.requestFocus();
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

		if (!(tenKH.matches(
				"^[a-zA-Z_???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? ]+$"))) {
			JOptionPane.showMessageDialog(this, "T??n kh??ng h???p l???!");
			txtTenKhachHang.requestFocus();
			txtTenKhachHang.selectAll();
			return false;
		}

		String pattern = "^(032|033|034|035|036|037|038|039|086|096|097|098|" + "070|079|077|076|078|089|090|093|"
				+ "083|084|085|081|082|088|091|094|" + "056|058|092|" + "059|099)[0-9]{7}$";
		if (!(sdt.matches(pattern))) {
			JOptionPane.showInternalMessageDialog(this,
					"S??? ??i???n tho???i ph???i c?? 10 s??? v?? b???t ?????u b???ng c??c ?????u s??? c???a nh?? m???ng Vi???t Nam!\n V?? d???: 0366497865");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}

		return true;
	}

	private void sua() {
		int row = tableDanhSachKhachHang.getSelectedRow();

		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n kh??ch h??ng mu???n c???p nh???t th??ng tin!");
			return;
		} else {

			KhachHang kh = null;
			if (regex()) {
				kh = getKhachHang();
			}

			int t1 = JOptionPane.showConfirmDialog(null, "B???n c?? ch???c mu???n c???p nh???t l???i th??ng tin c???a kh??ch h??ng n??y?",
					"C???p nh???t th??ng tin", JOptionPane.YES_NO_OPTION);
			if (t1 == JOptionPane.YES_OPTION && kh != null) {
				boolean isUpdated = false;
				try {
					isUpdated = customerFacade.updateCustomerInfo(kh);
					if (isUpdated) {
						JOptionPane.showMessageDialog(this, "C???p nh???t th??ng tin Kh??ch h??ng th??nh c??ng!");
						loadCustomersData();
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}

		tableDanhSachKhachHang.clearSelection();
	}

	private KhachHang getKhachHang() {
		String tenKH = txtTenKhachHang.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String maKH = txtMaKhachHang.getText().trim();

		return new KhachHang(maKH, tenKH, sdt, diaChi);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tableDanhSachKhachHang)) {
			int idx = tableDanhSachKhachHang.getSelectedRow();
			txtTenKhachHang.setText(tableDanhSachKhachHang.getValueAt(idx, 1).toString());
			txtMaKhachHang.setText(tableDanhSachKhachHang.getValueAt(idx, 0).toString());
			txtSDT.setText(tableDanhSachKhachHang.getValueAt(idx, 2).toString());
			txtDiaChi.setText(tableDanhSachKhachHang.getValueAt(idx, 3).toString());
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
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoa))
			btnXoaActionPerformed();
		else if (o.equals(btnChinhSua))
			btnChinhSuaActionPerformed();
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

	private boolean ktraHoten() {
		String hoTen = txtTenKhachHang.getText().trim();
		if (!(hoTen.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Vui l??ng nh???p h??? t??n");
			txtTenKhachHang.requestFocus();
			return false;
		}
		if (!(hoTen.matches(
				"^[a-zA-Z_???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? ]+$"))) {
			JOptionPane.showMessageDialog(this, "T??n kh??ng h???p l???");
			txtTenKhachHang.requestFocus();
			txtTenKhachHang.selectAll();
			return false;
		}
		return true;
	}

	private void datHanhDongChoTxtHoTen() {
		txtTenKhachHang.addActionListener((e) -> {
			if (ktraHoten())
				txtSDT.requestFocus();

		});
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
			JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng h???p l???");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}

		List<KhachHang> list = customerFacade.getCustomers();
		for (KhachHang kh : list) {
			if (kh.getSoDienThoaiKH().equals(sdt) && !kh.getMaKhachHang().equals(txtMaKhachHang.getText().trim())) {
				JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i ???? thu???c v??? nh??n vi??n kh??c");
				txtSDT.selectAll();
				txtSDT.requestFocus();
				return false;
			}
		}

		return true;
	}

	private void datHanhDongChoTxtSDT() {
		txtSDT.addActionListener((e) -> {
			try {
				if (ktraSDT())
					txtDiaChi.requestFocus();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	private void datHanhDongChoTxtDiaChi() {
		txtDiaChi.addActionListener((e) -> {
			if (txtDiaChi.getText().trim().length() > 0)
				txtTenKhachHang.requestFocus();
			else {
				JOptionPane.showMessageDialog(this, "Vui l??ng nh???p ?????a ch???");
			}
		});
	}
}