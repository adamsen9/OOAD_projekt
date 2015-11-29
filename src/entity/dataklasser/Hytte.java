package entity.dataklasser;

public class Hytte extends HyttePlads {
	
	int antal_personer;
	int størrelse;
	int hytte_nummer;
	String beskrivelse;
	
	public Hytte() {
	}

	public Hytte(HyttePlads hp, int antal_personer, int størrelse, int hytte_nummer, String beskrivelse) {
		super(hp.getPlads_id(), hp.getType(), hp.getiStatus(), hp.getMåler_id(), hp.getMåler_Status(), hp.getsStatus());
		this.antal_personer = antal_personer;
		this.størrelse = størrelse;
		this.hytte_nummer = hytte_nummer;
		this.beskrivelse = beskrivelse;
	}


	public Hytte(int plads_id, int type, int iStatus, int måler_id, int måler_Status, String sStatus,
			int antal_personer, int størrelse, int hytte_nummer, String beskrivelse) {
		super(plads_id, type, iStatus, måler_id, måler_Status, sStatus);
		this.antal_personer = antal_personer;
		this.størrelse = størrelse;
		this.hytte_nummer = hytte_nummer;
		this.beskrivelse = beskrivelse;
	}

	public int getAntal_personer() {
		return antal_personer;
	}

	public void setAntal_personer(int antal_personer) {
		this.antal_personer = antal_personer;
	}

	public int getStørrelse() {
		return størrelse;
	}

	public void setStørrelse(int størrelse) {
		this.størrelse = størrelse;
	}

	public int getHytte_nummer() {
		return hytte_nummer;
	}

	public void setHytte_nummer(int hytte_nummer) {
		this.hytte_nummer = hytte_nummer;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	
}
