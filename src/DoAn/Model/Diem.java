package DoAn.Model;

public class Diem {
	private String idsv;
	private String idgv;
	private String idmh;
	private String diem;
	
	public Diem() {
	}
	public Diem(String idsv,String idgv,String idmh, String diem) {
		this.idsv=idsv;
		this.idgv=idgv;
		this.idmh=idmh;
		this.diem=diem;
	}
	public String getIDSV() {
		return idsv;
	}
	public void setIDSV(String idsv) {
		this.idsv=idsv;
	}
	public String getIDMH() {
		return idmh;
	}
	public void setIDMH(String idmh) {
		this.idmh=idmh;
	}
	
	public String getDiem() {
		return diem;
	}
	public void setDiem(String diem) {
		this.diem=diem;
	}
	
	public String getIdgv() {
		return idgv;
	}
	public void setIdgv(String idgv) {
		this.idgv=idgv;
	}
}
