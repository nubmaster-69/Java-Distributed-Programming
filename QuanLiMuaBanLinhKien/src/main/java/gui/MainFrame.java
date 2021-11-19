package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import entity.NhanVien;

public abstract class MainFrame extends javax.swing.JFrame {
    
    private static final long serialVersionUID = 1L;

    protected JButton btnDonHang;
    protected JButton btnHoaDon;
    protected JButton btnKhachHang;
    protected JButton btnNhanVien;
    protected JButton btnSanPham;
    protected JButton btnThoat;
    protected JButton btnThongKe;
    protected JLabel jLabel1;
    protected JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JPanel mainPanel;
    protected JPanel menuPanel;
    private NhanVien nhanVienLogin;
    
    public MainFrame(NhanVien nv) {
    	nhanVienLogin = nv;
        initComponents();
        mainPanel.setLayout(new BorderLayout());
    }

    public MainFrame() {
		super();
	}
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        menuPanel = new JPanel();
        btnDonHang = new JButton();
        btnSanPham = new JButton();
        btnKhachHang = new JButton();
        btnHoaDon = new JButton();
        btnThongKe = new JButton();
        btnThoat = new JButton();
        btnNhanVien = new JButton();
        jLabel1 = new JLabel();
        mainPanel = new JPanel();
        jLabel2 = new JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ứng Dụng Quản Lý Mua Bán Linh Kiện");
        setResizable(false);

        menuPanel.setBackground(new java.awt.Color(102, 153, 255));

        btnDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/shopping-cart.png")));
        btnDonHang.setText("Đơn Hàng");
        btnDonHang.setFont(new Font("Tahoma", 0, 15));
        btnDonHang.setBorderPainted(false);
        btnDonHang.setContentAreaFilled(false);
        btnDonHang.setFocusPainted(false);
        btnDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonHangActionPerformed(evt);
            }
        });

        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/box.png")));
        btnSanPham.setText("Sản Phẩm");
        btnSanPham.setFont(new Font("Tahoma", 0, 15));
        btnSanPham.setBorderPainted(false);
        btnSanPham.setContentAreaFilled(false);
        btnSanPham.setFocusPainted(false);
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/multi-users.png")));
        btnKhachHang.setText("Khách Hàng");
        btnKhachHang.setFont(new Font("Tahoma", 0, 15));
        btnKhachHang.setBorderPainted(false);
        btnKhachHang.setContentAreaFilled(false);
        btnKhachHang.setFocusPainted(false);
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/invoice.png")));
        btnHoaDon.setText("Hóa Đơn");
        btnHoaDon.setFont(new Font("Tahoma", 0, 15));
        btnHoaDon.setBorderPainted(false);
        btnHoaDon.setContentAreaFilled(false);
        btnHoaDon.setFocusPainted(false);
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trend.png")));
        btnThongKe.setText("Thống Kê");
        btnThongKe.setFont(new Font("Tahoma", 0, 15));
        btnThongKe.setBorderPainted(false);
        btnThongKe.setContentAreaFilled(false);
        btnThongKe.setFocusPainted(false);
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png")));
        btnThoat.setText("Đăng Xuất");
        btnThoat.setFont(new Font("Tahoma", 0, 15));
        btnThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThoat.setBorderPainted(false);
        btnThoat.setContentAreaFilled(false);
        btnThoat.setFocusPainted(false);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png")));
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setFont(new Font("Tahoma", 0, 15));
        btnNhanVien.setBorderPainted(false);
        btnNhanVien.setContentAreaFilled(false);
        btnNhanVien.setFocusPainted(false);
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logo_IUH.png")));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThoat))
        );

        btnDonHang.getAccessibleContext().setAccessibleDescription("");

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/welcome.jpg")));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(293, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(230, 230, 230))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel2)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        pack();
        setLocationRelativeTo(null);
    }

    private void btnDonHangActionPerformed(java.awt.event.ActionEvent evt) {
        Object obj = evt.getSource();
        if (obj.equals(btnDonHang)) {
            btnDonHang.setContentAreaFilled(true);
            btnHoaDon.setContentAreaFilled(false);
            btnKhachHang.setContentAreaFilled(false);
            btnSanPham.setContentAreaFilled(false);
            btnThongKe.setContentAreaFilled(false);
            btnNhanVien.setContentAreaFilled(false);
                    
            mainPanel.removeAll();
            PanelHangCoSan panelDS = new PanelHangCoSan(nhanVienLogin);
            mainPanel.add(panelDS, BorderLayout.CENTER);
            mainPanel.repaint();
            mainPanel.revalidate();
        }
    }

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {
        btnDonHang.setContentAreaFilled(false);
        btnHoaDon.setContentAreaFilled(false);
        btnKhachHang.setContentAreaFilled(false);
        btnSanPham.setContentAreaFilled(true);
        btnThongKe.setContentAreaFilled(false);
        btnNhanVien.setContentAreaFilled(false);
        
        mainPanel.removeAll();
        PanelDanhSachSanPham panelDS = new PanelDanhSachSanPham();
        mainPanel.add(panelDS, BorderLayout.WEST);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {
        btnDonHang.setContentAreaFilled(false);
        btnHoaDon.setContentAreaFilled(false);
        btnKhachHang.setContentAreaFilled(true);
        btnSanPham.setContentAreaFilled(false);
        btnThongKe.setContentAreaFilled(false);
        btnNhanVien.setContentAreaFilled(false);
        
        mainPanel.removeAll();
        PanelDanhSachKhachHang panelDS = new PanelDanhSachKhachHang();
        mainPanel.add(panelDS, BorderLayout.CENTER);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {
        btnDonHang.setContentAreaFilled(false);
        btnHoaDon.setContentAreaFilled(true);
        btnKhachHang.setContentAreaFilled(false);
        btnSanPham.setContentAreaFilled(false);
        btnThongKe.setContentAreaFilled(false);
        btnNhanVien.setContentAreaFilled(false);
        
        mainPanel.removeAll();
        PanelDanhSachHoaDon panelDS = new PanelDanhSachHoaDon();
        mainPanel.add(panelDS, BorderLayout.CENTER);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát ?");
        if (confirm == 0) {
        	this.dispose();
        	new DangNhap().setVisible(true);;
        }
    }

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {
        btnDonHang.setContentAreaFilled(false);
        btnHoaDon.setContentAreaFilled(false);
        btnKhachHang.setContentAreaFilled(false);
        btnSanPham.setContentAreaFilled(false);
        btnThongKe.setContentAreaFilled(true);
        btnNhanVien.setContentAreaFilled(false);
        
        mainPanel.removeAll();
        PanelThongKe panelTK = new PanelThongKe();
        mainPanel.add(panelTK, BorderLayout.CENTER);
        mainPanel.repaint();
        mainPanel.revalidate();   
    }

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {
        btnDonHang.setContentAreaFilled(false);
        btnHoaDon.setContentAreaFilled(false);
        btnKhachHang.setContentAreaFilled(false);
        btnSanPham.setContentAreaFilled(false);
        btnThongKe.setContentAreaFilled(false);
        btnNhanVien.setContentAreaFilled(true);
        
        mainPanel.removeAll();
        PanelDanhSachNhanVien panelNV = new PanelDanhSachNhanVien();
        mainPanel.add(panelNV, BorderLayout.CENTER);
        mainPanel.repaint();
        mainPanel.revalidate();  
    }
}
