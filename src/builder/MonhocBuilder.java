package builder;

import DoAn.Model.MonHoc;

public class MonhocBuilder {
	private String id;
	private String ten;
	private String idgv;
	
	public MonhocBuilder setid(String id) {
		this.id = id;
		return this;
	}
	public MonhocBuilder setten(String ten) {
		this.ten = ten;
		return this;
	}
	public MonhocBuilder setidgv(String idgv) {
		this.idgv = idgv;
		return this;
	}
	public MonHoc build() {
		return new MonHoc(id,ten,idgv);
	}
}
