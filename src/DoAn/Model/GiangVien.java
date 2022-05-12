package DoAn.Model;

public class GiangVien {
	private String id;
	private String ten;
	private String gioitinh;
	private String ngaysinh;
	private String mail;
	private String sdt;
	
	public GiangVien() {
	}
	public GiangVien(String id, String ten,String gioitinh, String ngaysinh, String mail, String sdt) {
		this.id=id;
		this.ten=ten;
		this.gioitinh=gioitinh;
		this.ngaysinh=ngaysinh;
		this.mail=mail;
		this.sdt=sdt;
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
		return mail;
	}
	public void setEmail(String mail) {
		this.mail=mail;
	}
	
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt=sdt;
	}
}
