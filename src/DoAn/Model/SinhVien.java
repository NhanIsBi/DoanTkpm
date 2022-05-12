package DoAn.Model;

public class SinhVien {
	private String id;
	private String ten;
	private String gioitinh;
	private String ngaysinh;
	private String lop;
	private String email;
	private String sdt;
	
	public SinhVien() {
	}
	public SinhVien(String id, String ten, String lop, String sdt, String gioitinh, String ngaysinh, String email) {
		this.id=id;
		this.ten=ten;
		this.lop=lop;
		this.sdt=sdt;
		this.gioitinh=gioitinh;
		this.ngaysinh=ngaysinh;
		this.email=email;
	}
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id=id;
	}
	
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten=ten;
	}
	
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop=lop;
	}
	
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt=sdt;
	}
	public String getGioitih() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh=gioitinh;
	}
	
	public String getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh=ngaysinh;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
}
