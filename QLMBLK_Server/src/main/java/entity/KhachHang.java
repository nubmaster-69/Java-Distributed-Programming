package entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class KhachHang implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String maKhachHang;
	private String hoTenKH;
	private String soDienThoaiKH;
	private String diaChiKH;
	
	@OneToMany(mappedBy = "maKhachHang")
	private List<HoaDon> maHoaDon;
	
	public KhachHang() {
	}

	public KhachHang(String maKhachHang, String hoTenKH, String soDienThoaiKH, String diaChiKH) {
		this.maKhachHang = maKhachHang;
		this.hoTenKH = hoTenKH;
		this.soDienThoaiKH = soDienThoaiKH;
		this.diaChiKH = diaChiKH;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getHoTenKH() {
		return hoTenKH;
	}

	public void setHoTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}

	public String getSoDienThoaiKH() {
		return soDienThoaiKH;
	}

	public void setSoDienThoaiKH(String soDienThoaiKH) {
		this.soDienThoaiKH = soDienThoaiKH;
	}

	public String getDiaChiKH() {
		return diaChiKH;
	}

	public void setDiaChiKH(String diaChiKH) {
		this.diaChiKH = diaChiKH;
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTenKH=" + hoTenKH + ", soDienThoaiKH=" + soDienThoaiKH
				+ ", diaChiKH=" + diaChiKH + "]";
	}
	
	public Object[] convertToRowTableInGDThongKe(List<HoaDon> list) {
		DecimalFormat df = new DecimalFormat("#,###");
		double tien = 0;
		for(HoaDon hd : list) {
			tien += hd.tongTienHoaDon();
		}
		return new Object[]{hoTenKH,diaChiKH,list.size()
				,df.format(tien) + " VNĐ"
		};
	}

}