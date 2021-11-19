package gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class PanelThongKe extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnDSKHMuaNdpNgayKTTKDon;
	private JButton btnDSKhachHangMoi;
	private JButton btnSanPhamBanCham;
	private JButton btnSanPhamBanChay;
	private JButton btnThongKe;
	private JButton btnXuatExcel;

	private DatePicker dpNgayKTTK;
	private DatePicker dpNgayBDTK;

	private JLabel lblKHMoi;
	private JLabel lblKHMuaNdpNgayKTTKHD;
	private JLabel lblNgayBDTK;
	private JLabel lblNgayBKTTK;
	private JLabel lblSPBanChay;
	private JLabel lblSanPhamBanCham;
	private JLabel lblTieuDe;
	private JLabel lblTongDoanhThu;

	private JTextField txtTongDoanhThu;

	public PanelThongKe() {
		initComponents();
	}

	private void initComponents() {

		lblTieuDe = new JLabel();
		lblSPBanChay = new JLabel();
		lblSanPhamBanCham = new JLabel();
		lblTongDoanhThu = new JLabel();
		lblKHMuaNdpNgayKTTKHD = new JLabel();
		lblKHMoi = new JLabel();
		lblNgayBDTK = new JLabel();
		lblNgayBKTTK = new JLabel();
		

		DatePickerSettings dateSettingsForBegin = new DatePickerSettings();
		dateSettingsForBegin.setFontValidDate(new Font("SansSerif", 0, 18));
		dateSettingsForBegin.setFormatForDatesCommonEra("dd-MM-yyyy");
		dateSettingsForBegin.setAllowKeyboardEditing(false);
		
		dpNgayBDTK = new DatePicker(dateSettingsForBegin);
		dpNgayBDTK.setDate(LocalDate.now());
		dpNgayBDTK.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dpNgayBDTK.setFocusable(false);
		
		DatePickerSettings dateSettingsForEnd = new DatePickerSettings();
		dateSettingsForEnd.setFontValidDate(new Font("SansSerif", 0, 18));
		dateSettingsForEnd.setFormatForDatesCommonEra("dd-MM-yyyy");
		dateSettingsForEnd.setAllowKeyboardEditing(false);
		
		dpNgayKTTK = new DatePicker(dateSettingsForEnd);
		dpNgayKTTK.setDate(LocalDate.now());
		dpNgayKTTK.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dpNgayKTTK.setFocusable(false);
		
		btnThongKe = new JButton();
		btnThongKe.setFocusable(false);
		btnThongKe.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnXuatExcel = new JButton();
		btnXuatExcel.setFocusable(false);
		btnXuatExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnSanPhamBanChay = new JButton();
		btnSanPhamBanChay.setFocusable(false);
		btnSanPhamBanChay.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnDSKhachHangMoi = new JButton();
		btnDSKhachHangMoi.setFocusable(false);
		btnDSKhachHangMoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnDSKHMuaNdpNgayKTTKDon = new JButton();
		btnDSKHMuaNdpNgayKTTKDon.setFocusable(false);
		btnDSKHMuaNdpNgayKTTKDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnSanPhamBanCham = new JButton();
		btnSanPhamBanCham.setFocusable(false);
		btnSanPhamBanCham.setCursor(new Cursor(Cursor.HAND_CURSOR));

		txtTongDoanhThu = new JTextField();

		lblTieuDe.setFont(new Font("SansSerif", 1, 36));
		lblTieuDe.setText("Thống Kê Bán Hàng");

		lblSPBanChay.setFont(new Font("SansSerif", 0, 24));
		lblSPBanChay.setText("Sản phẩm bán chạy nhất");

		lblSanPhamBanCham.setFont(new Font("SansSerif", 0, 24));
		lblSanPhamBanCham.setText("Sản phẩm bán chậm nhất");

		lblTongDoanhThu.setFont(new Font("SansSerif", 0, 24));
		lblTongDoanhThu.setText("Tổng doanh thu cửa hàng");

		lblKHMuaNdpNgayKTTKHD.setFont(new Font("SansSerif", 0, 24));
		lblKHMuaNdpNgayKTTKHD.setText("Khách hàng mua nhiều hóa đơn");

		lblKHMoi.setFont(new Font("SansSerif", 0, 24));
		lblKHMoi.setText("Danh sách khách hàng mới");

		lblNgayBDTK.setFont(new Font("SansSerif", 0, 24));
		lblNgayBDTK.setText("Ngày bắt đầu");

		btnThongKe.setFont(new Font("SansSerif", 0, 16));
		btnThongKe.setText("Thống Kê");
		
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnThongKeActionPerformed(evt);
			}
		});

		btnXuatExcel.setFont(new Font("SansSerif", 0, 16));
		btnXuatExcel.setText("Xuất Excel");

		txtTongDoanhThu.setEditable(false);
		txtTongDoanhThu.setFont(new Font("SansSerif", 0, 18));
		txtTongDoanhThu.setText("1,923.543.212 VNĐ");
		txtTongDoanhThu.setMargin(new java.awt.Insets(2, 10, 2, 2));

		btnSanPhamBanChay.setFont(new Font("SansSerif", 0, 18));
		btnSanPhamBanChay.setText("Xem Danh Sách");

		btnDSKhachHangMoi.setFont(new Font("SansSerif", 0, 18));
		btnDSKhachHangMoi.setText("Xem Danh Sách");

		btnDSKHMuaNdpNgayKTTKDon.setFont(new Font("SansSerif", 0, 18));
		btnDSKHMuaNdpNgayKTTKDon.setText("Xem Danh Sách");

		btnSanPhamBanCham.setFont(new Font("SansSerif", 0, 18));
		btnSanPhamBanCham.setText("Xem Danh Sách");
		btnSanPhamBanCham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSanPhamBanChamActionPerformed(evt);
			}
		});

		lblNgayBKTTK.setFont(new Font("SansSerif", 0, 24));
		lblNgayBKTTK.setText("Ngày kết thúc");

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addComponent(lblSPBanChay,
										GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(38, 38, 38).addComponent(
										lblSanPhamBanCham, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addComponent(lblKHMoi,
										GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout
										.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(
												GroupLayout.Alignment.LEADING,
												layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addComponent(lblKHMuaNdpNgayKTTKHD, GroupLayout.DEFAULT_SIZE, 360,
																Short.MAX_VALUE)
														.addComponent(
																lblTongDoanhThu, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addGap(41, 41, 41)
														.addGroup(layout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(btnDSKHMuaNdpNgayKTTKDon,
																		GroupLayout.PREFERRED_SIZE, 200,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(txtTongDoanhThu,
																		GroupLayout.PREFERRED_SIZE, 569,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(btnSanPhamBanChay,
																		GroupLayout.PREFERRED_SIZE, 200,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(btnDSKhachHangMoi,
																		GroupLayout.PREFERRED_SIZE, 200,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(btnSanPhamBanCham,
																		GroupLayout.PREFERRED_SIZE, 200,
																		GroupLayout.PREFERRED_SIZE)))
										.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addComponent(btnThongKe, GroupLayout.PREFERRED_SIZE, 120,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(layout.createSequentialGroup()
																.addComponent(lblNgayBDTK, GroupLayout.PREFERRED_SIZE,
																		160, GroupLayout.PREFERRED_SIZE)
																.addGap(20, 20, 20).addComponent(dpNgayBDTK,
																		GroupLayout.PREFERRED_SIZE, 217,
																		GroupLayout.PREFERRED_SIZE)))
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup().addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(lblNgayBKTTK, GroupLayout.PREFERRED_SIZE,
																		160, GroupLayout.PREFERRED_SIZE)
																.addGap(20, 20, 20).addComponent(dpNgayKTTK,
																		GroupLayout.PREFERRED_SIZE, 217,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(layout.createSequentialGroup().addGap(206, 206, 206)
																.addComponent(btnXuatExcel, GroupLayout.PREFERRED_SIZE,
																		120, GroupLayout.PREFERRED_SIZE)
																.addGap(0, 0, Short.MAX_VALUE))))))
								.addGroup(layout.createSequentialGroup().addGap(360, 360, 360).addComponent(lblTieuDe)))
						.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap().addComponent(lblTieuDe).addGap(49, 49, 49)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(dpNgayKTTK, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgayBKTTK, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNgayBDTK, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(dpNgayBDTK, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
				.addGap(40, 40, 40)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblSPBanChay)
						.addComponent(btnSanPhamBanChay, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblSanPhamBanCham)
						.addComponent(btnSanPhamBanCham, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblTongDoanhThu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTongDoanhThu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblKHMuaNdpNgayKTTKHD, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDSKHMuaNdpNgayKTTKDon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblKHMoi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDSKhachHangMoi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(btnThongKe, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXuatExcel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(20, 20, 20)));
	}

	private void btnThongKeActionPerformed(ActionEvent evt) {
	}

	private void btnSanPhamBanChamActionPerformed(ActionEvent evt) {
	}
}