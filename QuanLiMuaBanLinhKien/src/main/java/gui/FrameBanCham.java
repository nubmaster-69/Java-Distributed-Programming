package gui;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableSanPhamBanCham = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Danh sách sản phẩm bán chậm");
		setResizable(false);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24));
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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1).addContainerGap())
				.addGroup(layout.createSequentialGroup().addGap(169, 169, 169).addComponent(jLabel1)
						.addContainerGap(169, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(53, Short.MAX_VALUE)));
		loadSlowOnSaleComponents();
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(FrameBanCham.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FrameBanCham.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FrameBanCham.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrameBanCham.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrameBanCham().setVisible(true);
			}
		});
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