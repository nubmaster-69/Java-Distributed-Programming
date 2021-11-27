package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class PanelThongKe extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTabbedPane TPThongKe;
	
	private JButton btnXuatFileDoanhThu;
	private JButton btnXuatFileSP;
	private JButton btnXuatFileSPBanCham;
	
	private DatePicker dpNgayBatDau;
	private DatePicker dpNgayKetThuc;
	
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane6;
	
	private JLabel lblNgayBDTK;
	private JLabel lblNgayBKTTK;
	private JLabel lblTieuDe;
	private JLabel lblTongDoanhThu;
	private JLabel lblTongTienSanPham;
	
	private JPanel pnlDoanhThu;
	private JPanel pnlKhachhangTiemNang;
	private JPanel pnlSPBanCham;
	private JPanel pnlSPBanChay;
	
	private JTable tblDoanhThu;
	private DefaultTableModel modelDoanhThu;
	
	private JTable tblKhachHangTiemNang;
	private DefaultTableModel modelKHTN;
	
	private JTable tblSPBanCham;
	private DefaultTableModel modelSPBanCham;
	
	private JTable tblSPBanChay;
	private DefaultTableModel modelSPBanChay;
	
	private JTextField txtDoanhThu;
	private JTextField txtTongTienBanChay;

	public PanelThongKe() {
		initComponents();
	}

	private void initComponents() {

		lblTieuDe = new JLabel();
		lblNgayBDTK = new JLabel();
		lblNgayBKTTK = new JLabel();
		TPThongKe = new JTabbedPane();
		pnlSPBanChay = new JPanel();
		jScrollPane2 = new JScrollPane();
		tblSPBanChay = new JTable();
		btnXuatFileSP = new JButton();
		txtTongTienBanChay = new JTextField();
		lblTongTienSanPham = new JLabel();
		pnlSPBanCham = new JPanel();
		jScrollPane6 = new JScrollPane();
		tblSPBanCham = new JTable();
		btnXuatFileSPBanCham = new JButton();
		pnlKhachhangTiemNang = new JPanel();
		jScrollPane4 = new JScrollPane();
		tblKhachHangTiemNang = new JTable();
		pnlDoanhThu = new JPanel();
		jScrollPane1 = new JScrollPane();
		tblDoanhThu = new JTable();
		lblTongDoanhThu = new JLabel();
		txtDoanhThu = new JTextField();
		btnXuatFileDoanhThu = new JButton();
		
		DatePickerSettings dateSettingsStart = new DatePickerSettings();
		DatePickerSettings dateSettingsEnd = new DatePickerSettings();
		
		dateSettingsStart.setFontValidDate(new Font("SansSerif", 0, 16));
		dateSettingsStart.setFormatForDatesCommonEra("dd-MM-yyyy");
		
		dateSettingsEnd.setFontValidDate(new Font("SansSerif", 0, 16));
		dateSettingsEnd.setFormatForDatesCommonEra("dd-MM-yyyy");
		
		dpNgayKetThuc = new DatePicker(dateSettingsStart);
		dpNgayBatDau = new DatePicker(dateSettingsEnd);

		lblTieuDe.setFont(new Font("SansSerif", 1, 30));
		lblTieuDe.setText("Thống Kê Bán Hàng");

		lblNgayBDTK.setFont(new Font("SansSerif", 0, 20));
		lblNgayBDTK.setText("Ngày bắt đầu:");

		lblNgayBKTTK.setFont(new Font("SansSerif", 0, 20));
		lblNgayBKTTK.setText("Ngày kết thúc:");

		TPThongKe.setFont(new Font("SansSerif", 0, 14));
		
		modelSPBanChay = new DefaultTableModel(new String[] { "Tên sản phẩm", "Loại Linh Kiện", "Thương hiệu", "Số lượng đã bán", "Tổng tiền"}, 0);
		tblSPBanChay.setModel(modelSPBanChay);
		tblSPBanChay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSPBanChay.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSPBanChay.setRowHeight(20);
		
		DefaultTableCellRenderer renderer4 = (DefaultTableCellRenderer) tblSPBanChay.getTableHeader()
				.getDefaultRenderer();
		renderer4.setHorizontalAlignment(SwingConstants.CENTER);
		
		tblSPBanChay.setPreferredSize(new Dimension(380, 0));
		jScrollPane2.setViewportView(tblSPBanChay);
		
		btnXuatFileSP.setFocusable(false);
		btnXuatFileSP.setFont(new Font("SansSerif", 0, 22));
		btnXuatFileSP.setText("Xuất file");
		btnXuatFileSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnXuatFileSPActionPerformed(evt);
			}
		});

		txtTongTienBanChay.setEditable(false);
		txtTongTienBanChay.setFont(new Font("SansSerif", 0, 20));
		txtTongTienBanChay.setText("VND");
		txtTongTienBanChay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtTongTienBanChayActionPerformed(evt);
			}
		});

		lblTongTienSanPham.setFont(new Font("SansSerif", 0, 22));
		lblTongTienSanPham.setText("Tổng tiền sản phẩm đã bán:");

		GroupLayout pnlSPBanChayLayout = new GroupLayout(pnlSPBanChay);
		pnlSPBanChay.setLayout(pnlSPBanChayLayout);
		pnlSPBanChayLayout.setHorizontalGroup(pnlSPBanChayLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane2)
				.addGroup(pnlSPBanChayLayout.createSequentialGroup()
						.addComponent(lblTongTienSanPham, GroupLayout.PREFERRED_SIZE, 308,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtTongTienBanChay, GroupLayout.PREFERRED_SIZE, 223,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 408, Short.MAX_VALUE)
						.addComponent(btnXuatFileSP)));
		pnlSPBanChayLayout.setVerticalGroup(pnlSPBanChayLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(pnlSPBanChayLayout.createSequentialGroup()
						.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 379,
								GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)
						.addGroup(pnlSPBanChayLayout
								.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(pnlSPBanChayLayout
										.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblTongTienSanPham, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtTongTienBanChay, GroupLayout.PREFERRED_SIZE, 0,
												Short.MAX_VALUE))
								.addComponent(btnXuatFileSP))
						.addGap(23, 23, 23)));

		pnlSPBanChay.setFocusable(false);
		pnlSPBanChay.setCursor(new Cursor(Cursor.HAND_CURSOR));
		TPThongKe.addTab("Các sản phẩm bán chạy nhất", pnlSPBanChay);

		modelSPBanCham = new DefaultTableModel( new String[] { "Tên sản phẩm", "Loại Linh Kiện", "Thương hiệu", "Đơn giá ", "Số lượng đã bán" }, 0);
		tblSPBanCham.setModel(modelSPBanCham);
		tblSPBanCham.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSPBanCham.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSPBanCham.setRowHeight(20);
		
		DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer) tblSPBanCham.getTableHeader()
				.getDefaultRenderer();
		renderer1.setHorizontalAlignment(SwingConstants.CENTER);
		
		tblSPBanCham.setPreferredSize(new Dimension(380, 0));
		jScrollPane6.setViewportView(tblSPBanCham);

		btnXuatFileSPBanCham.setFocusable(false);
		btnXuatFileSPBanCham.setFont(new Font("SansSerif", 0, 22));
		btnXuatFileSPBanCham.setText("Xuất file");
		btnXuatFileSPBanCham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnXuatFileSPBanChamActionPerformed(evt);
			}
		});

		GroupLayout pnlSPBanChamLayout = new GroupLayout(pnlSPBanCham);
		pnlSPBanCham.setLayout(pnlSPBanChamLayout);
		pnlSPBanChamLayout.setHorizontalGroup(pnlSPBanChamLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(pnlSPBanChamLayout.createSequentialGroup().addContainerGap(949, Short.MAX_VALUE)
						.addComponent(btnXuatFileSPBanCham))
				.addGroup(pnlSPBanChamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane6, GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)));
		pnlSPBanChamLayout
				.setVerticalGroup(pnlSPBanChamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								pnlSPBanChamLayout.createSequentialGroup().addContainerGap(396, Short.MAX_VALUE)
										.addComponent(btnXuatFileSPBanCham).addGap(28, 28, 28))
						.addGroup(pnlSPBanChamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(pnlSPBanChamLayout.createSequentialGroup()
										.addComponent(jScrollPane6, GroupLayout.PREFERRED_SIZE, 381,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0, 80, Short.MAX_VALUE))));

		pnlSPBanCham.setFocusable(false);
		pnlSPBanCham.setCursor(new Cursor(Cursor.HAND_CURSOR));
		TPThongKe.addTab("Các sản phẩm bán chậm", pnlSPBanCham);
		
		modelKHTN = new DefaultTableModel(new String[] { "Họ tên khách hàng", "Địa chỉ", "Số lượng đơn hàng", "Tổng tiền" }, 0);
		tblKhachHangTiemNang.setModel(modelKHTN);
		tblKhachHangTiemNang.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblKhachHangTiemNang.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblKhachHangTiemNang.setRowHeight(20);
		
		DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer) tblKhachHangTiemNang.getTableHeader()
				.getDefaultRenderer();
		renderer2.setHorizontalAlignment(SwingConstants.CENTER);
		
		tblKhachHangTiemNang.setPreferredSize(new Dimension(380, 0));
		jScrollPane4.setViewportView(tblKhachHangTiemNang);

		GroupLayout pnlKhachhangTiemNangLayout = new GroupLayout(pnlKhachhangTiemNang);
		pnlKhachhangTiemNang.setLayout(pnlKhachhangTiemNangLayout);
		pnlKhachhangTiemNangLayout.setHorizontalGroup(
				pnlKhachhangTiemNangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE));
		pnlKhachhangTiemNangLayout.setVerticalGroup(
				pnlKhachhangTiemNangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE));

		pnlKhachhangTiemNang.setFocusable(false);
		pnlKhachhangTiemNang.setCursor(new Cursor(Cursor.HAND_CURSOR));
		TPThongKe.addTab("Khách hàng tiềm năng", pnlKhachhangTiemNang);

		modelDoanhThu = new DefaultTableModel(new String[] { "Khách hàng", "Nhân viên", "Ngày lập hóa đơn", "Tổng tiền" }, 0);
		tblDoanhThu.setModel(modelDoanhThu);
		tblDoanhThu.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblDoanhThu.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblDoanhThu.setRowHeight(20);
		
		DefaultTableCellRenderer renderer3 = (DefaultTableCellRenderer) tblDoanhThu.getTableHeader()
				.getDefaultRenderer();
		renderer3.setHorizontalAlignment(SwingConstants.CENTER);
		
		tblDoanhThu.setPreferredSize(new Dimension(380, 0));
		jScrollPane1.setViewportView(tblDoanhThu);

		lblTongDoanhThu.setFont(new Font("SansSerif", 0, 22));
		lblTongDoanhThu.setText("Tổng tiền:");

		txtDoanhThu.setEditable(false);
		txtDoanhThu.setFont(new Font("SansSerif", 0, 22));
		txtDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtDoanhThuActionPerformed(evt);
			}
		});

		btnXuatFileDoanhThu.setFocusable(false);
		btnXuatFileDoanhThu.setFont(new Font("SansSerif", 0, 22));
		btnXuatFileDoanhThu.setText("Xuất file");
		btnXuatFileDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnXuatFileDoanhThuActionPerformed(evt);
			}
		});

		GroupLayout pnlDoanhThuLayout = new GroupLayout(pnlDoanhThu);
		pnlDoanhThu.setLayout(pnlDoanhThuLayout);
		pnlDoanhThuLayout.setHorizontalGroup(pnlDoanhThuLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane1)
				.addGroup(pnlDoanhThuLayout.createSequentialGroup()
						.addComponent(lblTongDoanhThu, GroupLayout.PREFERRED_SIZE, 158,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtDoanhThu, GroupLayout.PREFERRED_SIZE, 223,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 564, Short.MAX_VALUE)
						.addComponent(btnXuatFileDoanhThu)));
		pnlDoanhThuLayout.setVerticalGroup(pnlDoanhThuLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(pnlDoanhThuLayout.createSequentialGroup()
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 380,
								GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)
						.addGroup(
								pnlDoanhThuLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(txtDoanhThu)
										.addComponent(lblTongDoanhThu, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnXuatFileDoanhThu))
						.addContainerGap(24, Short.MAX_VALUE)));

		pnlDoanhThu.setFocusable(false);
		pnlDoanhThu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		TPThongKe.addTab("Doanh thu cửa hàng", pnlDoanhThu);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(TPThongKe)
						.addGroup(layout.createSequentialGroup().addGap(8, 8, 8)
								.addComponent(lblNgayBDTK, GroupLayout.PREFERRED_SIZE, 160,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dpNgayBatDau, GroupLayout.PREFERRED_SIZE, 230,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNgayBKTTK, GroupLayout.PREFERRED_SIZE, 160,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dpNgayKetThuc, GroupLayout.PREFERRED_SIZE, 230,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap())
				.addGroup(GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTieuDe).addGap(357, 357, 357)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblTieuDe)
										.addGap(20, 20, 20)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(dpNgayKetThuc, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNgayBKTTK, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18))
								.addGroup(layout.createSequentialGroup().addContainerGap(76, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(dpNgayBatDau, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNgayBDTK, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)))
						.addComponent(TPThongKe, GroupLayout.PREFERRED_SIZE, 494,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
	}

	private void btnXuatFileDoanhThuActionPerformed(ActionEvent evt) {
	}

	private void txtDoanhThuActionPerformed(ActionEvent evt) {
	}

	private void btnXuatFileSPBanChamActionPerformed(ActionEvent evt) {
	}

	private void txtTongTienBanChayActionPerformed(ActionEvent evt) {
	}

	private void btnXuatFileSPActionPerformed(ActionEvent evt) {
	}
}