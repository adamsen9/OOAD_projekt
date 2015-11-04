package Entity.Dataklasser;

public class Reservation implements Dataclass {
	int res_id, status, start_dato, start_el, slut_dato, slut_el, plads_id, kunde_id;
	
	public Reservation() {
		//kald til databasen i forbindelse med oprettelse af objekt
	}

	public int getRes_id() {
		return res_id;
	}

	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStart_dato() {
		return start_dato;
	}

	public void setStart_dato(int start_dato) {
		this.start_dato = start_dato;
	}

	public int getStart_el() {
		return start_el;
	}

	public void setStart_el(int start_el) {
		this.start_el = start_el;
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
