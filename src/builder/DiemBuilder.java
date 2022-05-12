package builder;

import DoAn.Model.Diem;

public class DiemBuilder {
	private String idsv;
	private String idgv;
	private String idmh;
	private String diem;
	public DiemBuilder setidsv(String idsv) {
		this.idsv = idsv;
		return this;
	}
	public DiemBuilder setidgv(String idgv) {
		this.idgv = idgv;
		return this;
	}
	public DiemBuilder setidmh(String idmh) {
		this.idmh = idmh;
		return this;
	}
	public DiemBuilder setdiem(String diem) {
		this.diem = diem;
		return this;
	}
	public Diem build() {
		return new Diem(idsv,idgv,idmh,diem);
	}
}
