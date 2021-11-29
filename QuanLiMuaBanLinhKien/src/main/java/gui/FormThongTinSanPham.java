package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import entity.LinhKien;
import facade.IComponentFacade;

public class FormThongTinSanPham extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int FORM_CAP_NHAT = 0;
	public static final int FORM_XEM_THONG_TIN = 1;

	private JLabel lblTieuDe;

	private JButton jButton1;
	private JButton btnUpdate;

	private JLabel lblBaoHanh;
	private JTextField txtBaoHanh;

	private JLabel lblDonGia;
	private JTextField txtDonGia;

	private JLabel lblLoaiLK;
	private JTextField txtLoaiLK;

	private JLabel lblNgayNhap;

	private JLabel lblSoLuongTon;
	private JTextField txtSoLuongTon;

	private JLabel lblTenLK;
	private JTextField txtTenLK;

	private JLabel lblThuongHieu;
	private JTextField txtThuongHieu;

	private JTextArea moTa;

	private JLabel lblMota;
	private JScrollPane jScrollPane1;

	private DatePicker dpNgayNhap;

	private LinhKien linhKien;
	private int cheDo = 0;

	private String btnExitColor = "#e74c3c";
	private String btnUpdateColor = "#3498db";

	public FormThongTinSanPham(int formType, LinhKien lk) {

		linhKien = lk;
		cheDo = formType;

		initComponents();
		if (formType == FORM_XEM_THONG_TIN) {
			jButton1.setText("Thoát");
			jButton1.setName("Thoat");
			jButton1.setBackground(Color.decode(btnExitColor));
		} else {
			jButton1.setText("Cập Nhật");
			jButton1.setName("CapNhat");
			jButton1.setBackground(Color.decode(btnUpdateColor));
		}
	}

	private void initComponents() {

		lblTieuDe = new JLabel();
		txtTenLK = new JTextField();
		lblTenLK = new JLabel();
		lblLoaiLK = new JLabel();
		txtLoaiLK = new JTextField();
		lblThuongHieu = new JLabel();
		txtThuongHieu = new JTextField();
		lblDonGia = new JLabel();
		txtDonGia = new JTextField();
		lblSoLuongTon = new JLabel();
		txtSoLuongTon = new JTextField();
		lblBaoHanh = new JLabel();
		txtBaoHanh = new JTextField();
		lblNgayNhap = new JLabel();

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFontValidDate(new Font("SansSerif", 0, 16));
		dateSettings.setFormatForDatesCommonEra("dd-MM-yyyy");
		dateSettings.setAllowKeyboardEditing(false);
		dpNgayNhap = new DatePicker(dateSettings);
		dpNgayNhap.setFocusable(false);
		dpNgayNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));

		jButton1 = new JButton();
		jButton1.setFocusable(false);
		jButton1.setBorderPainted(false);
		jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jButton1.setOpaque(true);
		jButton1.setForeground(Color.white);

		jScrollPane1 = new JScrollPane();
		jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		jScrollPane1.getHorizontalScrollBar().setPreferredSize(new Dimension(5, 5));

		lblMota = new JLabel();
		moTa = new JTextArea();

		lblMota.setText("Mô tả");
		lblMota.setFont(new Font("SansSerif", 0, 16));

		moTa.setFont(new Font("SansSerif", 0, 16));

		moTa.setColumns(20);
		moTa.setRows(5);
		jScrollPane1.setViewportView(moTa);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		lblTieuDe.setFont(new Font("SansSerif", 0, 24));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setText("Thông Tin Linh Kiện");

		txtTenLK.setFont(new Font("SansSerif", 0, 18));

		lblTenLK.setFont(new Font("SansSerif", 0, 16));
		lblTenLK.setText("Tên linh kiện");

		lblLoaiLK.setFont(new Font("SansSerif", 0, 16));
		lblLoaiLK.setText("Loại linh kiện");

		txtLoaiLK.setFont(new Font("SansSerif", 0, 18));

		lblThuongHieu.setFont(new Font("SansSerif", 0, 16));
		lblThuongHieu.setText("Thương hiệu");

		txtThuongHieu.setFont(new Font("SansSerif", 0, 18));

		lblDonGia.setFont(new Font("SansSerif", 0, 16));
		lblDonGia.setText("Đơn giá");

		txtDonGia.setFont(new Font("SansSerif", 0, 18));

		lblSoLuongTon.setFont(new Font("SansSerif", 0, 16));
		lblSoLuongTon.setText("Số lượng tồn");

		txtSoLuongTon.setFont(new Font("SansSerif", 0, 18));

		lblBaoHanh.setFont(new Font("SansSerif", 0, 16));
		lblBaoHanh.setText("Bảo hành");

		txtBaoHanh.setFont(new Font("SansSerif", 0, 18));

		lblNgayNhap.setFont(new Font("SansSerif", 0, 16));
		lblNgayNhap.setText("Ngày nhập");

		jButton1.setFont(new Font("SansSerif", 0, 16));
		jButton1.setText("Thoát");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(
												lblTenLK, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addGap(100, 100, 100).addComponent(
												lblTieuDe, GroupLayout.PREFERRED_SIZE, 283,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addGap(180, 180, 180).addComponent(
												jButton1, GroupLayout.PREFERRED_SIZE, 152,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(
												lblNgayNhap, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(0, 89, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(txtTenLK)
								.addComponent(jScrollPane1, GroupLayout.Alignment.LEADING)
								.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
										.addComponent(dpNgayNhap, GroupLayout.PREFERRED_SIZE, 216,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(txtBaoHanh, GroupLayout.PREFERRED_SIZE, 216,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblBaoHanh, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(lblDonGia, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup().addComponent(txtDonGia).addGap(30, 30,
												30)))
										.addGroup(layout
												.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addGroup(layout.createSequentialGroup()
														.addComponent(lblSoLuongTon,
																GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addGap(116, 116, 116))
												.addComponent(txtSoLuongTon)))
								.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
										.addComponent(lblNgayNhap, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(txtLoaiLK, GroupLayout.PREFERRED_SIZE, 216,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblLoaiLK, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lblThuongHieu, GroupLayout.PREFERRED_SIZE,
														100, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtThuongHieu, GroupLayout.PREFERRED_SIZE,
														216, GroupLayout.PREFERRED_SIZE))))))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 45,
						GroupLayout.PREFERRED_SIZE)
				.addGap(30, 30, 30)
				.addComponent(lblTenLK, GroupLayout.PREFERRED_SIZE, 20,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(
						txtTenLK, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblThuongHieu, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtThuongHieu, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblLoaiLK, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtLoaiLK, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblSoLuongTon, GroupLayout.PREFERRED_SIZE, 20,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDonGia, GroupLayout.PREFERRED_SIZE, 20,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(txtSoLuongTon, GroupLayout.PREFERRED_SIZE, 40,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDonGia, GroupLayout.PREFERRED_SIZE, 40,
								GroupLayout.PREFERRED_SIZE))
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblNgayNhap).addComponent(lblBaoHanh, GroupLayout.PREFERRED_SIZE, 20,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(dpNgayNhap, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
						.addComponent(txtBaoHanh, GroupLayout.PREFERRED_SIZE, 40,
								GroupLayout.PREFERRED_SIZE))
				.addGap(20, 20, 20).addComponent(lblNgayNhap)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 85,
						GroupLayout.PREFERRED_SIZE)
				.addGap(20, 20, 20).addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 34,
						GroupLayout.PREFERRED_SIZE)
				.addGap(20, 20, 20)));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);

		loadCompInfo();
	}

	private void loadCompInfo() {
		if (cheDo == FORM_XEM_THONG_TIN)
			setTextFieldEditable(false);
		else
			setTextFieldEditable(true);

		txtTenLK.setText(linhKien.getTenLinhKien());
		txtLoaiLK.setText(linhKien.getLoaiLinhKien());
		txtThuongHieu.setText(linhKien.getThuongHieu());
		txtSoLuongTon.setText(linhKien.getSoLuongTon() + "");
		dpNgayNhap.setDate(linhKien.getNgayNhap());
		txtBaoHanh.setText(linhKien.getBaoHanh() + "");
		txtDonGia.setText(new DecimalFormat("#,###").format(linhKien.getDonGia()));
		moTa.append(linhKien.getMoTa());

		txtTenLK.requestFocus();
		txtTenLK.setCaretPosition(0);
	}

	private void setTextFieldEditable(boolean state) {
		txtLoaiLK.setEditable(state);
		txtThuongHieu.setEditable(state);
		txtSoLuongTon.setEditable(state);
		dpNgayNhap.setEnabled(state);
		txtBaoHanh.setEditable(state);
		txtDonGia.setEditable(state);
		moTa.setEditable(state);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jButton1.getName().equals("Thoat"))
			dispose();
		else {
			boolean isUpdated = false;
			try {
				isUpdated = updateEvent();
			} catch (RemoteException | MalformedURLException | NotBoundException e) {
				e.printStackTrace();
			}
			if (isUpdated) {
				JOptionPane.showMessageDialog(null, "Cập nhật thông tin linh kiện thành công!");
				btnUpdate.doClick();
				dispose();
			}
		}
	}

	private boolean updateEvent() throws RemoteException, MalformedURLException, NotBoundException {
		linhKien.setSoLuongTon(linhKien.getSoLuongTon() - 1);
		
		IComponentFacade componentFacade = (IComponentFacade) Naming.lookup("rmi://localhost:1341/componentFacade");
		
		boolean res = componentFacade.updateComponentByID(linhKien); 
		
		return res; 
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}
}