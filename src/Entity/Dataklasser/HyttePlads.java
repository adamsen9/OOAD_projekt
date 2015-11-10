package Entity.Dataklasser;

public class HyttePlads implements Dataclass,IListEntity {

	public HyttePlads(int plads_id, int type, int lavseason, int hoejseason, int iStatus, int måler_Status,
			String sStatus) {
		super();
		this.plads_id = plads_id;
		this.type = type;
		this.lavseason = lavseason;
		this.hoejseason = hoejseason;
		this.iStatus = iStatus;
		this.måler_Status = måler_Status;
		this.sStatus = sStatus;
	}

	int plads_id, type, lavseason, hoejseason, iStatus, måler_Status;
	String sStatus;

	public HyttePlads() {

		// kald til databasen i forbindelse med oprettelse af objekt

	}

	public int getPlads_id() {
		return plads_id;
	}

	public void setPlads_id(int plads_id) {
		this.plads_id = plads_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLavseason() {
		return lavseason;
	}

	public void setLavseason(int lavseason) {
		this.lavseason = lavseason;
	}

	public int getHoejseason() {
		return hoejseason;
	}

	public void setHoejseason(int hoejseason) {
		this.hoejseason = hoejseason;
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
		//TODO fix
		return toString();
	}
}
