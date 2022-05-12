package DoAn.Model;

public class MonHoc {
	private String id;
	private String ten;
	private String idgv;
	
	public MonHoc() {
	}
	public MonHoc(String id, String ten,String idgv) {
		this.id=id;
		this.ten=ten;
		this.idgv=idgv;
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
	
	public String getIdgv() {
		return idgv;
	}
	public void setIdgv(String idgv) {
		this.idgv=idgv;
	}
}
