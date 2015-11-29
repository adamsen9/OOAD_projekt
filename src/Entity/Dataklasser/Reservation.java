package Entity.Dataklasser;

public class Reservation {

	int res_id, 
		status, 
		start_el, 
		slut_el,
		plads_typpe,
		plads_id, 
		kunde_id, 
		antal_voksne, 
		antal_børn, 
		antal_hunde;
	
	String start_dato, slut_dato;
	
	public Reservation() {
	}
	
	public Reservation(int res_id, int status, int start_el, int slut_el, int plads_typpe, int plads_id, int kunde_id,
			int antal_voksne, int antal_børn, int antal_hunde, String start_dato, String slut_dato) {
		super();
		this.res_id = res_id;
		this.status = status;
		this.start_el = start_el;
		this.slut_el = slut_el;
		this.plads_typpe = plads_typpe;
		this.plads_id = plads_id;
		this.kunde_id = kunde_id;
		this.antal_voksne = antal_voksne;
		this.antal_børn = antal_børn;
		this.antal_hunde = antal_hunde;
		this.start_dato = start_dato;
		this.slut_dato = slut_dato;
	}
	
	public int getState() {
		return status;
	}

	public void setState(int state) {
		this.status = state;
	}

	public int getAntal_voksne() {
		return antal_voksne;
	}

	public void setAntal_voksne(int antal_voksne) {
		this.antal_voksne = antal_voksne;
	}

	public int getAntal_børn() {
		return antal_børn;
	}

	public void setAntal_børn(int antal_børn) {
		this.antal_børn = antal_børn;
	}


	public int getId() {
		return res_id;
	}

	public void setId(int res_id) {
		this.res_id = res_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStart_dato() {
		return start_dato;
	}

	public void setStart_dato(String start_dato) {
		this.start_dato = start_dato;
	}

	public String getSlut_dato() {
		return slut_dato;
	}

	public void setSlut_dato(String slut_dato) {
		this.slut_dato = slut_dato;
	}

	public int getSlut_el() {
		return slut_el;
	}

	public void setSlut_el(int slut_el) {
		this.slut_el = slut_el;
	}

	public int getPlads_id() {
		return plads_id;
	}

	public void setPlads_id(int plads_id) {
		this.plads_id = plads_id;
	}

	public int getKunde_id() {
		return kunde_id;
	}

	public void setKunde_id(int kunde_id) {
		this.kunde_id = kunde_id;
	}
	
	public int getStart_el() {
		return start_el;
	}


	public void setStart_el(int start_el) {
		this.start_el = start_el;
	}


	public int getPlads_typpe() {
		return plads_typpe;
	}


	public void setPlads_typpe(int plads_typpe) {
		this.plads_typpe = plads_typpe;
	}


	public int getAntal_hunde() {
		return antal_hunde;
	}

	public void setAntal_hunde(int antal_hunde) {
		this.antal_hunde = antal_hunde;
	}
}
