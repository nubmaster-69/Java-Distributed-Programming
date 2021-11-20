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

		model = new DefaultTableModel(new String[] { "Mã khách hàng", "Tên khách hàng", "SDT", "Địa chỉ" }, 0);
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

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableDanhSachKhachHang.getTableHeader()
				.getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

		for (int i = 0; i < tableDanhSachKhachHang.getColumnCount(); ++i) {
			if (i != 0)
				tableDanhSachKhachHang.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
			else
				tableDanhSachKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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

		btnChinhSua.setText("Chỉnh sửa");
		btnChinhSua.setFocusable(false);
		btnChinhSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnChinhSua.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnChinhSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnChinhSuaActionPerformed();
			}
		});

		jLabel1.setFont(new Font("SansSerif", 1, 24));
		jLabel1.setText("Danh Sách Khách Hàng");

		btnXoa.setText("Xóa");
		btnXoa.setFocusable(false);
		btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnXoaActionPerformed();
			}
		});

		txtTimKiem.addKeyListener(this);

		jLabel2.setFont(new Font("SansSerif", 1, 24));
		jLabel2.setText("Thông Tin Khách Hàng");

		jLabel3.setFont(new Font("SansSerif", 0, 24));
		jLabel3.setText("Tên khách hàng");

		jLabel4.setFont(new Font("SansSerif", 0, 24));
		jLabel4.setText("Mã khách hàng");

		txtMaKhachHang.setEditable(false);

		jLabel5.setFont(new Font("SansSerif", 0, 24));
		jLabel5.setText("Số điện thoại");

		jLabel6.setFont(new Font("SansSerif", 0, 24));
		jLabel6.setText("Địa chỉ");

		jLabel7.setFont(new Font("SansSerif", 0, 16));
		jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel7.setText("Tìm Khách Hàng");

		jLabel12.setFont(new Font("SansSerif", 0, 24));
		jLabel12.setText("Danh mục");

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
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa ?", "Xác nhận",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (confirm == 0) {
			while (tableDanhSachKhachHang.getSelectedRowCount() != 0) {
				int selectedRow = tableDanhSachKhachHang.getSelectedRow();
				String maKH = tableDanhSachKhachHang.getValueAt(selectedRow, 0).toString();
				try {
					if (customerFacade.removeCustomerByID(maKH))
						model.removeRow(selectedRow);
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
			// TODO Auto-generated catch block
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
			JOptionPane.showMessageDialog(this, "Phải nhập đủ thông tin");
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
				"^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$"))) {
			JOptionPane.showMessageDialog(this, "Tên không hợp lệ");
			txtTenKhachHang.requestFocus();
			txtTenKhachHang.selectAll();
			return false;
		}

		if (!(sdt.matches("^0[0-9]{9}$"))) {
			JOptionPane.showInternalMessageDialog(this, "Số điện thoại k hợp lệ");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}

		return true;
	}

	private void sua() {

		int row = tableDanhSachKhachHang.getSelectedRow();

		if (row == -1) {
			JOptionPane.showMessageDialog(null, "chọn dòng muốn sửa");
			return;
		} else {
			KhachHang kh = null;
			if (regex()) {
				kh = getKhachHang();
			}
			int t1 = JOptionPane.showConfirmDialog(null, "bạn có muốn sửa ?", "Sửa", JOptionPane.YES_NO_OPTION);
			if (t1 == JOptionPane.YES_OPTION && kh != null) {
				boolean isUpdated = false;
				try {
					isUpdated = customerFacade.updateCustomerInfo(kh);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (isUpdated) {
					JOptionPane.showMessageDialog(this, "Sửa thông tin Khách hàng thành công");
					loadCustomersData();
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
}