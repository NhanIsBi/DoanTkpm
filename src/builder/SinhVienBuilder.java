package builder;

import DoAn.Model.SinhVien;

public class SinhVienBuilder {
	private String id;
	private String ten;
	private String gioitinh;
	private String ngaysinh;
	private String lop;
	private String email;
	private String sdt;

	public SinhVienBuilder setId(String id) {
		this.id = id;
		return this;
	}
	public SinhVienBuilder setTen(String ten) {
		this.ten = ten;
		return this;
	}
	public SinhVienBuilder setGioiTinh(String gioitinh) {
		this.gioitinh = gioitinh;
		return this;
	}
	public SinhVienBuilder setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
		return this;
	}
	public SinhVienBuilder setLop(String lop) {
		this.lop = lop;
		return this;
	}
	public SinhVienBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	public SinhVienBuilder setSDT(String sdt) {
		this.sdt = sdt;
		return this;
	}
	public SinhVien build() {
		return new SinhVien(id,ten,gioitinh,ngaysinh,lop,email,sdt);
	}
}
