package Entity.Dataklasser;

public class Reservation implements Dataclass {
	int res_id, state, start_dato, slut_dato, slut_el, plads_id, kunde_id, antal_voksne, antal_børn;
	
	public Reservation() {
		//kald til databasen i forbindelse med oprettelse af objekt
	}
	
	
	public Reservation(int res_id, int state, int start_dato, int slut_dato, int slut_el, int plads_id, int kunde_id, int antal_voksne, int antal_børn){
		this.res_id = res_id;
		this.state = state;
		this.start_dato = start_dato;
		this.slut_dato = slut_dato;
		this.slut_el = slut_el;
		this.plads_id = plads_id;
		this.kunde_id = kunde_id;
		this.antal_voksne = antal_voksne;
		this.antal_børn = antal_børn;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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


	public int getRes_id() {
		return res_id;
	}

	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}

	public int getStatus() {
		return state;
	}

	public void setStatus(int status) {
		this.state = state;
	}

	public int getStart_dato() {
		return start_dato;
	}

	public void setStart_dato(int start_dato) {
		this.start_dato = start_dato;
	}

	public int getSlut_dato() {
		return slut_dato;
	}

	public void setSlut_dato(int slut_dato) {
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
}
