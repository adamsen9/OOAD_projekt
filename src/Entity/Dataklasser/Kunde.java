package Entity.Dataklasser;

public class Kunde {
	int id;
	String tlf;
	
	public Kunde() {
		//kald til databasen i forbindelse med oprettelse af objekt
		
		
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
}
