package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.rmi.Naming;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import entity.LinhKien;
import facade.IComponentFacade;

public class FrameBanCham extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JTable tableSanPhamBanCham;
	private DefaultTableModel model;

	public FrameBanCham() {
		initComponents();
	}

	private void initComponents() {

		jLabel1 = new JLabel();
		jScrollPane1 = new JScrollPane();
		tableSanPhamBanCham = new JTable();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Danh sách sản phẩm bán chậm");
		setResizable(false);

		jLabel1.setFont(new Font("SansSerif", 0, 24));
		jLabel1.setText("Thông tin sản phẩm bán chậm");

		tableSanPhamBanCham.setModel(model = new DefaultTableModel(
				new String[] { "Mã linh kiện", "Tên linh kiện", "Loại linh kiện", "Thương hiệu", "Số lượng", "Giá" },
				0));

		jScrollPane1.setViewportView(tableSanPhamBanCham);
		if (tableSanPhamBanCham.getColumnModel().getColumnCount() > 0) {
			tableSanPhamBanCham.getColumnModel().getColumn(0).setResizable(false);
			tableSanPhamBanCham.getColumnModel().getColumn(1).setResizable(false);
			tableSanPhamBanCham.getColumnModel().getColumn(2).setResizable(false);
			tableSanPhamBanCham.getColumnModel().getColumn(3).setResizable(false);
			tableSanPhamBanCham.getColumnModel().getColumn(4).setResizable(false);
			tableSanPhamBanCham.getColumnModel().getColumn(5).setResizable(false);
		}

		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1).addContainerGap())
				.addGroup(layout.createSequentialGroup().addGap(169, 169, 169).addComponent(jLabel1)
						.addContainerGap(169, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1,
								GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(53, Short.MAX_VALUE)));
		loadSlowOnSaleComponents();
		pack();
		setLocationRelativeTo(null);
	}

	private void loadSlowOnSaleComponents() {
		try {
			IComponentFacade componentFacade = (IComponentFacade) Naming.lookup("rmi://localhost:1341/componentFacade");
			List<LinhKien> lks = componentFacade.getSlowOnSaleComponents();
			for (LinhKien lk : lks)
				model.addRow(new Object[] { lk.getMaLinhKien(), lk.getTenLinhKien(), lk.getLoaiLinhKien(),
						lk.getThuongHieu(), lk.getSoLuongTon(), new DecimalFormat("#,###").format(lk.getDonGia()) });

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}