package Entity;

public class HytterPladser {
	int id, type, status, elm�ler_id, m�ler_tilstand;
	String sStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getElm�ler_id() {
		return elm�ler_id;
	}
	public void setElm�ler_id(int elm�ler_id) {
		this.elm�ler_id = elm�ler_id;
	}
	public int getM�ler_tilstand() {
		return m�ler_tilstand;
	}
	public void setM�ler_tilstand(int m�ler_tilstand) {
		this.m�ler_tilstand = m�ler_tilstand;
	}
	public String getsStatus() {
		return sStatus;
	}
	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
}
