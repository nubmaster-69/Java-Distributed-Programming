package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class LinhKien implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String maLinhKien;
	private String tenLinhKien;
	private String loaiLinhKien;
	private String moTa;
	private double donGia;
	private String thuongHieu;
	private int soLuongTon;
	private LocalDate ngayNhap;
	private int baoHanh;

	@OneToMany(mappedBy = "maLinhKien")
	List<ChiTietHoaDon> chiTietHoaDon;
	
	public LinhKien() {
	}

	public LinhKien(String maLinhKien, String tenLinhKien, String loaiLinhKien, String moTa, double donGia,
			String thuongHieu, int soLuongTon, LocalDate ngayNhap, int baoHanh) {
		this.maLinhKien = maLinhKien;
		this.tenLinhKien = tenLinhKien;
		this.loaiLinhKien = loaiLinhKien;
		this.moTa = moTa;
		this.donGia = donGia;
		this.thuongHieu = thuongHieu;
		this.soLuongTon = soLuongTon;
		this.ngayNhap = ngayNhap;
		this.baoHanh = baoHanh;
	}

	public String getMaLinhKien() {
		return maLinhKien;
	}

	public void setMaLinhKien(String maLinhKien) {
		this.maLinhKien = maLinhKien;
	}

	public String getTenLinhKien() {
		return tenLinhKien;
	}

	public void setTenLinhKien(String tenLinhKien) {
		this.tenLinhKien = tenLinhKien;
	}

	public String getLoaiLinhKien() {
		return loaiLinhKien;
	}

	public void setLoaiLinhKien(String loaiLinhKien) {
		this.loaiLinhKien = loaiLinhKien;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public String getThuongHieu() {
		return thuongHieu;
	}

	public void setThuongHieu(String thuongHieu) {
		this.thuongHieu = thuongHieu;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public LocalDate getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(LocalDate ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public int getBaoHanh() {
		return baoHanh;
	}

	public void setBaoHanh(int baoHanh) {
		this.baoHanh = baoHanh;
	}

	@Override
	public String toString() {
		return "LinhKien [maLinhKien=" + maLinhKien + ", tenLinhKien=" + tenLinhKien + ", loaiLinhKien=" + loaiLinhKien
				+ ", moTa=" + moTa + ", donGia=" + donGia + ", thuongHieu=" + thuongHieu + ", soLuongTon=" + soLuongTon
				+ ", ngayNhap=" + ngayNhap + ", baoHanh=" + baoHanh + "]";
	}

}