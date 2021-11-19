package gui;

import entity.NhanVien;

public class EmployeeFrame extends MainFrame{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private NhanVien nhanVienLogin;
	public EmployeeFrame(NhanVien nv) {
		super(nv);
		nhanVienLogin = nv;
        btnNhanVien.setVisible(false);
    }
}