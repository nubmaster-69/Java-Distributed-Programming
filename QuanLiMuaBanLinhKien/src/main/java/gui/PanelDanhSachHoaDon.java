package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entity.HoaDon;
import facade.IOrderFacade;

public class PanelDanhSachHoaDon extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblTieuDe;
	private JLabel lblTimKiem;

	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private DefaultTableModel model;
	private TableRowSorter<DefaultTableModel> rowSorter;
	
	private JTextField txtTimKiem;

	public PanelDanhSachHoaDon() {
		initComponents();
	}

	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		lblTieuDe = new JLabel();
		txtTimKiem = new JTextField();
		lblTimKiem = new JLabel();

		model = new DefaultTableModel(new String[] { "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày lập" }, 0);
		jTable1 = new JTable(model);
		rowSorter = new TableRowSorter<DefaultTableModel>(model);
		jTable1.setRowSorter(rowSorter);
		jTable1.setFont(new Font("Tahoma", 0, 14));
		jTable1.getTableHeader().setFont(new Font("Tahoma", 0, 14));
		jTable1.setGridColor(Color.WHITE);

		jTable1.setDefaultEditor(Object.class, null);
		jTable1.setRowHeight(20);
		

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < jTable1.getColumnCount(); ++i) {
			jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		jScrollPane1.setViewportView(jTable1);
		if (jTable1.getColumnModel().getColumnCount() > 0) {
			jTable1.getColumnModel().getColumn(0).setResizable(false);
			jTable1.getColumnModel().getColumn(1).setResizable(false);
			jTable1.getColumnModel().getColumn(2).setResizable(false);
			jTable1.getColumnModel().getColumn(3).setResizable(false);
		}
		
		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

		jTable1.addMouseListener(this);

		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTieuDe.setText("Danh Sách Hóa Đơn");

		txtTimKiem.setFont(new Font("Tahoma", 0, 16));

		lblTimKiem.setFont(new Font("Tahoma", 0, 18));
		lblTimKiem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTimKiem.setText("Tìm Kiếm Hóa đơn");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080,
												Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
												.addGap(499, 499, 499))))
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 210,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 601,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(172, 172, 172))
								.addGroup(layout.createSequentialGroup().addGap(425, 425, 425).addComponent(lblTieuDe)))
						.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(31, 31, 31).addComponent(lblTieuDe).addGap(18, 18, 18)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addContainerGap()));

		loadBillsData();
	}

	private void loadBillsData() {
		try {
			IOrderFacade orderFacade = (IOrderFacade) Naming.lookup("rmi://localhost:1341/orderFacade");
			
			List<HoaDon> bills = orderFacade.getBills();

			bills.forEach(bill -> {
				model.addRow(new Object[] { bill.getMaHoaDon(), bill.getMaNhanVien().getMaNhanVien(),
						bill.getMaKhachHang().getMaKhachHang(), bill.getNgayLapHoaDon() });
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseClicked = e.getButton();
		int mouseClickedCount = e.getClickCount();

		if (mouseClicked == 1 && mouseClickedCount == 2) {
			int idx = jTable1.getSelectedRow();

			if (idx != -1) {
				String maHD = jTable1.getValueAt(idx, 0).toString();
				if (!maHD.isEmpty())
					new FrameChiTietHoaDon(maHD).setVisible(true);
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
}
