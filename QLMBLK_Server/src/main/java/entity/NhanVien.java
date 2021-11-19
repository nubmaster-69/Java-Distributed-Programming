package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class NhanVien implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String maNhanVien;
	private String hoTenNV;
	private String gioiTinhNV;
	private LocalDate ngaySinhNV;
	private String soDienThoaiNV;
	private String diaChiNV;
	private String matKhau;
	private String quyenTruyCap;

	@OneToMany(mappedBy = "maNhanVien")
	private List<HoaDon> maHoaDon;
	
	public NhanVien() {
	}

	public NhanVien(String maNhanVien, String hoTenNV, String gioiTinhNV, LocalDate ngaySinhNV, String soDienThoaiNV,
			String diaChiNV, String matKhau, String quyenTruyCap) {
		this.maNhanVien = maNhanVien;
		this.hoTenNV = hoTenNV;
		this.gioiTinhNV = gioiTinhNV;
		this.ngaySinhNV = ngaySinhNV;
		this.soDienThoaiNV = soDienThoaiNV;
		this.diaChiNV = diaChiNV;
		this.matKhau = matKhau;
		this.quyenTruyCap = quyenTruyCap;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTenNV() {
		return hoTenNV;
	}

	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}

	public String getGioiTinhNV() {
		return gioiTinhNV;
	}

	public void setGioiTinhNV(String gioiTinhNV) {
		this.gioiTinhNV = gioiTinhNV;
	}

	public LocalDate getNgaySinhNV() {
		return ngaySinhNV;
	}

	public void setNgaySinhNV(LocalDate ngaySinhNV) {
		this.ngaySinhNV = ngaySinhNV;
	}

	public String getSoDienThoaiNV() {
		return soDienThoaiNV;
	}

	public void setSoDienThoaiNV(String soDienThoaiNV) {
		this.soDienThoaiNV = soDienThoaiNV;
	}

	public String getDiaChiNV() {
		return diaChiNV;
	}

	public void setDiaChiNV(String diaChiNV) {
		this.diaChiNV = diaChiNV;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getquyenTruyCap() {
		return quyenTruyCap;
	}

	public void setquyenTruyCap(String quyenTruyCap) {
		this.quyenTruyCap = quyenTruyCap;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTenNV=" + hoTenNV + ", gioiTinhNV=" + gioiTinhNV
				+ ", ngaySinhNV=" + ngaySinhNV + ", soDienThoaiNV=" + soDienThoaiNV + ", diaChiNV=" + diaChiNV
				+ ", matKhau=" + matKhau + ", quyenTruyCap=" + quyenTruyCap + "]";
	}

}