package gui;

import entity.NhanVien;

public class AdminFrame extends MainFrame{
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private NhanVien nhanVienLogin;

	public AdminFrame(NhanVien nv) {
		super(nv);
		nhanVienLogin = nv;
	}
}