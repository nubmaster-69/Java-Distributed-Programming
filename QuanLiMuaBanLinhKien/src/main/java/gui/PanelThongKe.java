package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import facade.IComponentFacade;
import facade.ICustomerFacade;
import facade.IOrderFacade;
import facade.IUtilityFacade;

public class PanelThongKe extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTabbedPane TPThongKe;

	private JButton btnXuatFileDoanhThu;
	private JButton btnXuatFileSP;
	private JButton btnXuatFileSPBanCham;

	private DatePicker dpNgayBatDau;
	private DatePicker dpNgayKetThuc;

	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPane4;

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

	private JTable tblKHTiemNang;
	private DefaultTableModel modelKHTiemNang;

	private JTable tblSPBanCham;
	private DefaultTableModel modelSPBanCham;

	private JTable tblSPBanChay;
	private DefaultTableModel modelSPBanChay;

	private JTextField txtDoanhThu;
	private JTextField txtTongTienBanChay;

	private DecimalFormat df;

	private IComponentFacade componentFacade;
	private ICustomerFacade customerFacade;
	private IOrderFacade orderFacade;
	private IUtilityFacade utilityFacade = null;
	private JFileChooser fileChooser = null;

	public PanelThongKe() {
		try {
			utilityFacade = (IUtilityFacade) Naming.lookup("rmi://localhost:1341/utilityFacade");
		} catch (Exception e) {
			e.printStackTrace();
		}

		initComponents();
		initDaoAndData();
		addAction();
	}

	private void initComponents() {

		lblTieuDe = new JLabel();
		lblNgayBDTK = new JLabel();
		lblNgayBKTTK = new JLabel();
		TPThongKe = new JTabbedPane();
		pnlSPBanChay = new JPanel();
		btnXuatFileSP = new JButton();
		
		txtTongTienBanChay = new JTextField();
		txtTongTienBanChay.setEditable(false);
		
		lblTongTienSanPham = new JLabel();
		jScrollPane1 = new JScrollPane();
		tblSPBanChay = new JTable();
		pnlSPBanCham = new JPanel();
		btnXuatFileSPBanCham = new JButton();
		jScrollPane2 = new JScrollPane();
		tblSPBanCham = new JTable();
		pnlKhachhangTiemNang = new JPanel();
		jScrollPane4 = new JScrollPane();
		tblKHTiemNang = new JTable();
		pnlDoanhThu = new JPanel();
		lblTongDoanhThu = new JLabel();
		
		txtDoanhThu = new JTextField();
		txtDoanhThu.setEditable(false);
		
		btnXuatFileDoanhThu = new JButton();
		jScrollPane3 = new JScrollPane();
		tblDoanhThu = new JTable();

		DatePickerSettings dateSettingsStart = new DatePickerSettings();
		DatePickerSettings dateSettingsEnd = new DatePickerSettings();

		dateSettingsStart.setFontValidDate(new Font("SansSerif", 0, 16));
		dateSettingsStart.setFormatForDatesCommonEra("dd-MM-yyyy");
		dateSettingsStart.setAllowKeyboardEditing(false);

		dateSettingsEnd.setFontValidDate(new Font("SansSerif", 0, 16));
		dateSettingsEnd.setFormatForDatesCommonEra("dd-MM-yyyy");
		dateSettingsEnd.setAllowKeyboardEditing(false);

		dpNgayKetThuc = new DatePicker(dateSettingsStart);
		dpNgayKetThuc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dpNgayKetThuc.setDateToToday();
		dpNgayKetThuc.setFocusable(false);

		dpNgayBatDau = new DatePicker(dateSettingsEnd);
		dpNgayBatDau.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dpNgayBatDau.setDateToToday();
		dpNgayBatDau.setFocusable(false);

		lblTieuDe.setText("Th???ng K?? B??n H??ng");
		lblTieuDe.setFont(new Font("SansSerif", 1, 30));

		lblNgayBDTK.setText("Ng??y b???t ?????u:");
		lblNgayBDTK.setFont(new Font("SansSerif", 0, 20));

		lblNgayBKTTK.setText("Ng??y k???t th??c:");
		lblNgayBKTTK.setFont(new Font("SansSerif", 0, 20));

		TPThongKe.setFont(new Font("SansSerif", 0, 14));

		btnXuatFileSP.setText("Xu???t file");
		btnXuatFileSP.setFocusable(false);

		btnXuatFileSP.setFont(new Font("SansSerif", 0, 22));
		btnXuatFileSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnXuatFileSPActionPerformed(evt);
			}
		});

		txtTongTienBanChay.setFont(new Font("SansSerif", 0, 20));

		lblTongTienSanPham.setText("T???ng ti???n s???n ph???m ???? b??n:");
		lblTongTienSanPham.setFont(new Font("SansSerif", 0, 22));

		modelSPBanChay = new DefaultTableModel(
				new String[] { "T??n Linh Ki???n", "Lo???i Linh Ki???n", "Th????ng Hi???u", "S??? L?????ng ???? B??n", "T???ng Ti???n" }, 0);

		tblSPBanChay.setModel(modelSPBanChay);
		tblSPBanChay.setDefaultEditor(Object.class, null);

		tblSPBanChay.setGridColor(Color.WHITE);
		tblSPBanChay.setFocusable(false);
		tblSPBanChay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSPBanChay.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSPBanChay.setRowHeight(20);

		DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer) tblSPBanChay.getTableHeader()
				.getDefaultRenderer();
		renderer1.setHorizontalAlignment(SwingConstants.CENTER);

		jScrollPane1.setViewportView(tblSPBanChay);

		GroupLayout pnlSPBanChayLayout = new GroupLayout(pnlSPBanChay);
		pnlSPBanChay.setLayout(pnlSPBanChayLayout);
		pnlSPBanChayLayout.setHorizontalGroup(pnlSPBanChayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(pnlSPBanChayLayout.createSequentialGroup()
						.addComponent(lblTongTienSanPham, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtTongTienBanChay, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 408, Short.MAX_VALUE)
						.addComponent(btnXuatFileSP))
				.addComponent(jScrollPane1));
		pnlSPBanChayLayout.setVerticalGroup(pnlSPBanChayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(pnlSPBanChayLayout.createSequentialGroup()
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(pnlSPBanChayLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(pnlSPBanChayLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblTongTienSanPham, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtTongTienBanChay, GroupLayout.PREFERRED_SIZE, 0,
												Short.MAX_VALUE))
								.addComponent(btnXuatFileSP))
						.addGap(23, 23, 23)));

		TPThongKe.setFocusable(false);

		pnlSPBanChay.setFocusable(false);
		pnlSPBanChay.setCursor(new Cursor(Cursor.HAND_CURSOR));
		TPThongKe.addTab("C??c s???n ph???m b??n ch???y nh???t", pnlSPBanChay);

		btnXuatFileSPBanCham.setText("Xu???t file");
		btnXuatFileSPBanCham.setFocusable(false);

		btnXuatFileSPBanCham.setFont(new Font("SansSerif", 0, 22));
		btnXuatFileSPBanCham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnXuatFileSPBanChamActionPerformed(evt);
			}
		});

		modelSPBanCham = new DefaultTableModel(
				new String[] { "T??n Linh Ki???n", "Lo???i Linh Ki???n", "Th????ng Hi???u", "S??? l?????ng ???? b??n", "????n gi??" }, 0);

		tblSPBanCham.setModel(modelSPBanCham);
		tblSPBanCham.setDefaultEditor(Object.class, null);

		tblSPBanCham.setGridColor(Color.WHITE);
		tblSPBanCham.setFocusable(false);
		tblSPBanCham.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSPBanCham.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSPBanCham.setRowHeight(20);

		DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer) tblSPBanCham.getTableHeader()
				.getDefaultRenderer();
		renderer2.setHorizontalAlignment(SwingConstants.CENTER);

		jScrollPane2.setViewportView(tblSPBanCham);

		GroupLayout pnlSPBanChamLayout = new GroupLayout(pnlSPBanCham);
		pnlSPBanCham.setLayout(pnlSPBanChamLayout);
		pnlSPBanChamLayout.setHorizontalGroup(pnlSPBanChamLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pnlSPBanChamLayout.createSequentialGroup()
						.addContainerGap(949, Short.MAX_VALUE).addComponent(btnXuatFileSPBanCham))
				.addComponent(jScrollPane2));
		pnlSPBanChamLayout.setVerticalGroup(pnlSPBanChamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, pnlSPBanChamLayout.createSequentialGroup()
						.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(btnXuatFileSPBanCham).addContainerGap(44, Short.MAX_VALUE)));

		pnlSPBanCham.setFocusable(false);
		pnlSPBanCham.setCursor(new Cursor(Cursor.HAND_CURSOR));
		TPThongKe.addTab("C??c s???n ph???m b??n ch???m", pnlSPBanCham);

		modelKHTiemNang = new DefaultTableModel(
				new String[] { "H??? T??n KH", "?????a Ch???", "S??? L?????ng ????n H??ng", "T???ng Ti???n" }, 0);

		tblKHTiemNang.setModel(modelKHTiemNang);
		tblKHTiemNang.setDefaultEditor(Object.class, null);

		tblKHTiemNang.setGridColor(Color.WHITE);
		tblKHTiemNang.setFocusable(false);
		tblKHTiemNang.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblKHTiemNang.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblKHTiemNang.setRowHeight(20);

		DefaultTableCellRenderer renderer3 = (DefaultTableCellRenderer) tblKHTiemNang.getTableHeader()
				.getDefaultRenderer();
		renderer3.setHorizontalAlignment(SwingConstants.CENTER);

		jScrollPane4.setViewportView(tblKHTiemNang);

		GroupLayout pnlKhachhangTiemNangLayout = new GroupLayout(pnlKhachhangTiemNang);
		pnlKhachhangTiemNang.setLayout(pnlKhachhangTiemNangLayout);
		pnlKhachhangTiemNangLayout
				.setHorizontalGroup(pnlKhachhangTiemNangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE));
		pnlKhachhangTiemNangLayout
				.setVerticalGroup(pnlKhachhangTiemNangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE));

		pnlKhachhangTiemNang.setFocusable(false);
		pnlKhachhangTiemNang.setCursor(new Cursor(Cursor.HAND_CURSOR));
		TPThongKe.addTab("Kh??ch h??ng ti???m n??ng", pnlKhachhangTiemNang);

		lblTongDoanhThu.setText("T???ng ti???n :");
		lblTongDoanhThu.setFont(new Font("SansSerif", 0, 22));

		txtDoanhThu.setFont(new Font("SansSerif", 0, 22));

		btnXuatFileDoanhThu.setText("Xu???t file");
		btnXuatFileDoanhThu.setFocusable(false);

		btnXuatFileDoanhThu.setFont(new Font("SansSerif", 0, 22));
		btnXuatFileDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnXuatFileDoanhThuActionPerformed(evt);
			}
		});

		modelDoanhThu = new DefaultTableModel(new String[] { "Kh??ch H??ng", "Nh??n Vi??n", "Ng??y L???p", "T???ng Ti???n" }, 0);

		tblDoanhThu.setModel(modelDoanhThu);
		tblDoanhThu.setDefaultEditor(Object.class, null);

		tblDoanhThu.setGridColor(Color.WHITE);
		tblDoanhThu.setFocusable(false);
		tblDoanhThu.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblDoanhThu.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblDoanhThu.setRowHeight(20);

		DefaultTableCellRenderer renderer4 = (DefaultTableCellRenderer) tblDoanhThu.getTableHeader()
				.getDefaultRenderer();
		renderer4.setHorizontalAlignment(SwingConstants.CENTER);

		jScrollPane3.setViewportView(tblDoanhThu);

		GroupLayout pnlDoanhThuLayout = new GroupLayout(pnlDoanhThu);
		pnlDoanhThu.setLayout(pnlDoanhThuLayout);
		pnlDoanhThuLayout.setHorizontalGroup(pnlDoanhThuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(pnlDoanhThuLayout.createSequentialGroup()
						.addComponent(lblTongDoanhThu, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtDoanhThu, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 564, Short.MAX_VALUE)
						.addComponent(btnXuatFileDoanhThu))
				.addComponent(jScrollPane3));
		pnlDoanhThuLayout.setVerticalGroup(pnlDoanhThuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(pnlDoanhThuLayout.createSequentialGroup()
						.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(pnlDoanhThuLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(txtDoanhThu)
								.addComponent(lblTongDoanhThu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnXuatFileDoanhThu))
						.addContainerGap(44, Short.MAX_VALUE)));

		pnlDoanhThu.setFocusable(false);
		pnlDoanhThu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		TPThongKe.addTab("Doanh thu c???a h??ng", pnlDoanhThu);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(TPThongKe)
						.addGroup(layout.createSequentialGroup().addGap(8, 8, 8)
								.addComponent(lblNgayBDTK, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dpNgayBatDau, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblNgayBKTTK, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(dpNgayKetThuc,
										GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap())
				.addGroup(layout.createSequentialGroup().addGap(394, 394, 394).addComponent(lblTieuDe)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addGap(68, 68, 68)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(dpNgayKetThuc, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgayBKTTK, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18))
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblTieuDe)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(dpNgayBatDau, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNgayBDTK, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)))
				.addComponent(TPThongKe, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
	}

	private void btnXuatFileDoanhThuActionPerformed(ActionEvent evt) {
		if(modelDoanhThu.getRowCount() > 0)
			capNhatTongTienThongKe(modelDoanhThu, -1, txtDoanhThu.getText().replace("VN??", ""));
		export(tblDoanhThu, "ThongKeDoanhThu", "Th???ng k?? doanh thu");
	}

	private void btnXuatFileSPBanChamActionPerformed(ActionEvent evt) {
		export(tblSPBanCham, "ThongKeSanPhamBanCham", "Th???ng k?? s???n ph???m b??n ch???m");
	}

	private void btnXuatFileSPActionPerformed(ActionEvent evt) {
		if(modelSPBanChay.getRowCount() > 0)
			capNhatTongTienThongKe(modelSPBanChay, count(modelSPBanChay), txtTongTienBanChay.getText().replace("VN??", ""));
		export(tblSPBanChay, "ThongKeSanPhamBanChay", "Th???ng k?? s???n ph???m b??n ch???y");
	}

	/**
	 * @author Hi???u
	 *         <p>
	 *         H??m c?? ch???c n??ng t??? ?????ng c???p nh???t s??? l?????ng v?? t???ng ti???n cho table
	 *         </p>
	 * @param model:    model c???n th???c hi???n c???p nh???t
	 * @param soLuong:  n???u s??? l?????ng == -1 s??? kh??ng g??n tr?????ng n??y
	 * @param tongTien: t???ng ti???n
	 */
	private void capNhatTongTienThongKe(DefaultTableModel model, int soLuong, String tongTien) {
		int len = model.getColumnCount();

		Object[] o = " ".repeat(len).split("");

		if (soLuong != -1)
			o[len - 2] = soLuong + "";

		o[len - 1] = tongTien + "";

		model.insertRow(0, o);
	}

	private void export(JTable table, String fileName, String title) {
		fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory().toPath().toString());

		fileChooser.setDialogTitle(title);

		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);

		if (table.getRowCount() < 1) {
			showMsg("Kh??ng c?? d??? li???u ????? xu???t file th???ng k??!");
			return;
		}

		int approve = fileChooser.showSaveDialog(fileChooser);
		if (approve == JFileChooser.APPROVE_OPTION) {
			String duongDan = fileChooser.getSelectedFile().toString().replace("\\", "/");

			try {
				utilityFacade.exportFileToExcel(table, fileName, duongDan);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			showMsg("Xu???t file th???ng k?? th??nh c??ng!");
		}
	}

	private void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	private int count(DefaultTableModel model) {
		int tong = 0;
		int col = 0;
		if (model.equals(modelSPBanChay)) {
			col = 3;
		} else if (model.equals(modelSPBanCham)) {
			col = 3;
		}
		for (int i = 0; i < model.getRowCount(); i++) {
			tong += (int) model.getValueAt(i, col);
		}
		return tong;
	}

	private void initDaoAndData() {
		df = new DecimalFormat("#,###");
		try {
			componentFacade = (IComponentFacade) Naming.lookup("rmi://localhost:1341/componentFacade");
			customerFacade = (ICustomerFacade) Naming.lookup("rmi://localhost:1341/customerFacade");
			orderFacade = (IOrderFacade) Naming.lookup("rmi://localhost:1341/orderFacade");
			addRowIntab0();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public void removeData(JTable table) {
		((DefaultTableModel) table.getModel()).setRowCount(0);
	}

	private void addRowIntab0() throws RemoteException {
		if (!checkDate()) {
			return;
		}

		Map<LinhKien, Integer> map = componentFacade.getComponentsBestSelling(dpNgayBatDau.getDate(),
				dpNgayKetThuc.getDate());

		removeData(tblSPBanChay);

		double tong = 0;
		for (Map.Entry<LinhKien, Integer> entry : map.entrySet()) {
			LinhKien lk = entry.getKey();
			int sl = entry.getValue();
			modelSPBanChay.addRow(lk.convertToRowTableInGDThongKe(sl));
			tong += lk.getDonGia() * sl;
		}

		txtTongTienBanChay.setText(df.format(tong) + " VN??");
	}

	private void addRowIntab1() throws RemoteException {

		if (!checkDate()) {
			return;
		}

		Map<LinhKien, Integer> map = componentFacade.getComponentsWorstSelling(dpNgayBatDau.getDate(),
				dpNgayKetThuc.getDate());
		removeData(tblSPBanCham);
		for (Map.Entry<LinhKien, Integer> entry : map.entrySet()) {
			LinhKien lk = entry.getKey();
			int sl = entry.getValue();
			modelSPBanCham.addRow(lk.convertToRowTableInGDThongKeWorst(sl));
		}

	}

	private void addRowIntab3() throws RemoteException {
		if (!checkDate()) {
			return;
		}

		List<HoaDon> list = orderFacade.getBillsByDate(dpNgayBatDau.getDate(), dpNgayKetThuc.getDate());
		removeData(tblDoanhThu);
		double tong = 0;
		for (HoaDon hoaDon : list) {
			modelDoanhThu.addRow(hoaDon.convertToRowTableInGDThongKe());
			tong += hoaDon.tongTienHoaDon();
		}

		txtDoanhThu.setText(df.format(tong) + " VN??");
	}

	private void addRowIntab2() throws RemoteException {
		if (!checkDate()) {
			return;
		}
		removeData(tblKHTiemNang);
		Map<KhachHang, List<HoaDon>> map = customerFacade.getCustomersPotential(dpNgayBatDau.getDate(),
				dpNgayKetThuc.getDate());

		for (Map.Entry<KhachHang, List<HoaDon>> entry : map.entrySet()) {
			KhachHang kh = entry.getKey();
			List<HoaDon> listHD = entry.getValue();
			modelKHTiemNang.addRow(kh.convertToRowTableInGDThongKe(listHD));
		}
	}

	private Boolean checkDate() {
		if (dpNgayBatDau.getDate() == null || dpNgayKetThuc.getDate() == null) {
			return false;
		}
		return true;
	}

	private void switchAndChangeEvent() {
		switch (TPThongKe.getSelectedIndex()) {
		case 0:
			try {
				addRowIntab0();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		case 2:
			try {
				addRowIntab2();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		case 3:
			try {
				addRowIntab3();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		case 1:
			try {
				addRowIntab1();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void addAction() {
		TPThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				switchAndChangeEvent();
			}
		});

		dpNgayBatDau.addDateChangeListener(new createDateChange());
		dpNgayKetThuc.addDateChangeListener(new createDateChange());
	}

	private class createDateChange implements DateChangeListener {
		@Override
		public void dateChanged(DateChangeEvent event) {
			switchAndChangeEvent();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(dpNgayBatDau) || obj.equals(dpNgayKetThuc) && TPThongKe.getSelectedIndex() == 0) {
			try {
				addRowIntab0();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
}