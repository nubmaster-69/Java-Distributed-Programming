package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.rmi.Naming;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import facade.IOrderDetailFacade;

public class FrameChiTietHoaDon extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel4;
	private JLabel jLabel6;
	private JLabel jLabel7;

	private JScrollPane jScrollPane2;
	private JTable tableDonHang;
	private DefaultTableModel model;

	private JTextField txtDiaChi;
	private JTextField txtHoTen;
	private JTextField txtMaKhachHang;
	private JTextField txtSDT;

	public FrameChiTietHoaDon(String maHD) {
		initComponents(maHD);
	}

	private void initComponents(String maHD) {

		jLabel1 = new JLabel();
		txtHoTen = new JTextField();
		jLabel4 = new JLabel();
		txtDiaChi = new JTextField();
		jScrollPane2 = new JScrollPane();
		jLabel6 = new JLabel();
		txtMaKhachHang = new JTextField();
		jLabel7 = new JLabel();
		txtSDT = new JTextField();
		jLabel2 = new JLabel();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setFont(new Font("SansSerif", 1, 24));
		jLabel1.setText("Chi Tiết Hóa Đơn");

		txtHoTen.setEditable(false);
		txtHoTen.setFont(new Font("SansSerif", 0, 16));

		jLabel4.setText("Địa chỉ");
		jLabel4.setFont(new Font("SansSerif", 0, 16));

		txtDiaChi.setEditable(false);
		txtDiaChi.setFont(new Font("SansSerif", 0, 16));

		tableDonHang = new JTable(model = new DefaultTableModel(
				new String[] { "Mã linh kiện", "Tên linh kiện", "Loại linh kiện", "Thương hiệu", "Số lượng", "Giá" },
				0));

		tableDonHang.setDefaultEditor(Object.class, null);
		tableDonHang.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tableDonHang.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));

		tableDonHang.setGridColor(Color.WHITE);
		tableDonHang.setFocusable(false);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableDonHang.getTableHeader()
				.getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tableDonHang.getColumnCount(); ++i) {
			tableDonHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		tableDonHang.setCursor(new Cursor(Cursor.HAND_CURSOR));

		jScrollPane2.setViewportView(tableDonHang);
		jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

		jLabel6.setText("Mã khách hàng");
		jLabel6.setFont(new Font("SansSerif", 0, 16));

		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setFont(new Font("SansSerif", 0, 16));

		jLabel7.setText("Số điện thoại");
		jLabel7.setFont(new Font("SansSerif", 0, 16));

		txtSDT.setEditable(false);
		txtSDT.setFont(new Font("SansSerif", 0, 16));

		jLabel2.setText("Họ và tên khách hàng");
		jLabel2.setFont(new Font("SansSerif", 0, 16));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane2, GroupLayout.Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addComponent(txtDiaChi, GroupLayout.DEFAULT_SIZE, 370,
												Short.MAX_VALUE)
										.addComponent(txtMaKhachHang))
								.addComponent(jLabel4).addComponent(jLabel6))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(txtHoTen, GroupLayout.Alignment.TRAILING,
												GroupLayout.PREFERRED_SIZE, 370,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 370,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(jLabel2).addComponent(jLabel7))
						.addContainerGap())
				.addGroup(layout.createSequentialGroup().addGap(354, 354, 354).addComponent(jLabel1)
						.addContainerGap(355, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(29, 29, 29)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(jLabel6))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(txtMaKhachHang, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE))
						.addGap(20, 20, 20)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel7).addComponent(jLabel4))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
						.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 308,
								GroupLayout.PREFERRED_SIZE)));

		setResizable(false);

		loadData(maHD);
		pack();
		setLocationRelativeTo(null);
	}

	private void loadData(String maHD) {

		IOrderDetailFacade orderDetailFacade;
		try {
			orderDetailFacade = (IOrderDetailFacade) Naming.lookup("rmi://localhost:1341/orderDetailFacade");

			List<String> cthds = orderDetailFacade.getBillDetailByBillID(maHD);

			for (String cthd : cthds) {
				String[] str = cthd.split(";");

				txtHoTen.setText(str[0]);
				txtMaKhachHang.setText(str[1]);
				txtDiaChi.setText(str[2]);
				txtSDT.setText(str[3]);

				model.addRow(new Object[] { str[4], str[5], str[6], str[7], str[8], str[9] });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}