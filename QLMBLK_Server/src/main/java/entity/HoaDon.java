package entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class HoaDon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String maHoaDon;

	@ManyToOne
	@JoinColumn(name = "maNhanVien")
	private NhanVien maNhanVien;

	@ManyToOne
	@JoinColumn(name = "maKhachHang")
	private KhachHang maKhachHang;
	private LocalDate ngayLapHoaDon;
	@OneToMany(mappedBy = "maHoaDon",fetch = FetchType.EAGER)
	private List<ChiTietHoaDon> chiTietHoaDon;

	public HoaDon() {
		super();
	}

	public HoaDon(String maHoaDon, NhanVien maNhanVien, KhachHang maKhachHang, LocalDate ngayLapHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public KhachHang getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(KhachHang maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public LocalDate getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}
	
	
	public List<ChiTietHoaDon> getChiTietHoaDon() {
		return chiTietHoaDon;
	}

	public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
		this.chiTietHoaDon = chiTietHoaDon;
	}

	public double tongTienHoaDon() {
		double tongTien = 0;
		
		for (int i = 0; i< chiTietHoaDon.size(); i++) {
			tongTien += chiTietHoaDon.get(i).getSoLuong() * chiTietHoaDon.get(i).getMaLinhKien().getDonGia();
		}
		
		return tongTien;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", maKhachHang=" + maKhachHang
				+ ", ngayLapHoaDon=" + ngayLapHoaDon +"chi tiet = "+ "]";
	}
	
	public Object[] convertToRowTableInGDThongKe() {
		DecimalFormat df = new DecimalFormat("#,###");
		return new Object[]{maKhachHang.getHoTenKH(),maNhanVien.getHoTenNV(),ngayLapHoaDon,df.format(tongTienHoaDon()) + " VNĐ"
		};
	}

}