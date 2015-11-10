package Entity.Dataklasser;

public class HyttePlads implements IListEntity{
	int id, type, status, elmåler_id, måler_tilstand;
	String sStatus;
	
	
	public HyttePlads() {

		//kald til databasen i forbindelse med oprettelse af objekt
		
	}
	
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
	public int getElmåler_id() {
		return elmåler_id;
	}
	public void setElmåler_id(int elmåler_id) {
		this.elmåler_id = elmåler_id;
	}
	public int getMåler_tilstand() {
		return måler_tilstand;
	}
	public void setMåler_tilstand(int måler_tilstand) {
		this.måler_tilstand = måler_tilstand;
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
