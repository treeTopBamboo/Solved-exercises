package rei11;

import java.io.Serializable;

public class OrderBean implements Serializable{
	private int ninzu;
	private String seminar;

	public int getNinzu() {
		return ninzu;
	}
	public void setNinzu(int ninzu) {
		this.ninzu = ninzu;
	}

	public String getSeminar() {
		return seminar;
	}
	public void setSeminar(String seminar) {
		this.seminar = seminar;
	}

}