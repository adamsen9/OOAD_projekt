package Entity.Dataklasser;

public class Kunde implements IListEntity {
	int kunde_id;
	String tlf;
	String kunde_navn;
	
	public Kunde() {
		
	}
	
	public Kunde(String kunde_navn, int kunde_id, String tlf) {
		this.kunde_id = kunde_id;
		this.tlf = tlf;
		this.kunde_navn = kunde_navn;
	}
	
	public Kunde(String kunde_navn, String tlf) {
		this.kunde_id = 0;
		this.tlf = tlf;
		this.kunde_navn = kunde_navn;
	}
	
	public int getId() {
		return kunde_id;
	}
	public void setId(int id) {
		this.kunde_id = id;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public String getNavn() {
		return kunde_navn;
	}
	public void setNavn(String navn) {
		this.kunde_navn = navn;
	}

	@Override
	public String prettyPrint() {
		return "ID: " + getId() + " Navn: " + getNavn() + " Tlf: " + getTlf();
	}
	
}
