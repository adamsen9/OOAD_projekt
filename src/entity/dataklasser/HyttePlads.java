package entity.dataklasser;

public class HyttePlads implements IListEntity {

	public static final int LILLE_PLADS = 1;
	public static final int STOR_PLADS = 2;
	public static final int HYTTE = 3;
	
	public static final int SATUS_KLAR = 0;
	
	public static final String[] TYPER = {"Lille plads", "Stor plads", "Hytte"};
	
	private int id, type, iStatus, måler_id, måler_Status;
	private String sStatus;

	public HyttePlads() {
		this.id = 0;
		this.type = 0;
		this.iStatus = 0;
		this.måler_id = 0;
		this.måler_Status = 0;
		this.sStatus = "";
	}

	public HyttePlads(int plads_id, int type, int iStatus, int måler_id, int måler_Status, String sStatus) {
		this.id = plads_id;
		this.type = type;
		this.iStatus = iStatus;
		this.måler_id = måler_id;
		this.måler_Status = måler_Status;
		this.sStatus = sStatus;
	}

	public int getMåler_id() {
		return måler_id;
	}

	public void setMåler_id(int måler_id) {
		this.måler_id = måler_id;
	}
	
	@Override
	public int getId() {
		return id;
	}

	public void setId(int plads_id) {
		this.id = plads_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getiStatus() {
		return iStatus;
	}

	public void setiStatus(int iStatus) {
		this.iStatus = iStatus;
	}

	public int getMåler_Status() {
		return måler_Status;
	}

	public void setMåler_Status(int måler_Status) {
		this.måler_Status = måler_Status;
	}

	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
	
	public String prettyPrint(){
		return "ID: " + getId() + " Type: " + getType() + " Status: " + getsStatus();
	}

}
