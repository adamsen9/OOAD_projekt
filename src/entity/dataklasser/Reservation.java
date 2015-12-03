package entity.dataklasser;

import java.time.LocalDate;

public class Reservation {
	
	public static final int STANDARD_VOKSNE = 1;
	public static final int STANDARD_BØRN = 0;
	public static final int STANDARD_HUND = 0;
	
	public static final int STATUS_RESERVERET = 0;
	public static final int STATUS_I_BRUG = 1;
	public static final int STATUS_AFREGNET = 2;
	
	public static final String[] STATUSER = {"Reserveret", "I brug","afregnet"};
	
	int res_id, 
		status, 
		start_el, 
		slut_el,
		plads_type,
		plads_id, 
		kunde_id, 
		antal_voksne, 
		antal_børn, 
		antal_hunde;
	
	LocalDate start_dato, slut_dato;
	
	public Reservation() {
	}
	
	public Reservation(int res_id, int status, int start_el, int slut_el, int plads_type, int plads_id, int kunde_id,
			int antal_voksne, int antal_børn, int antal_hunde, LocalDate start_dato, LocalDate slut_dato) {
		super();
		this.res_id = res_id;
		this.status = status;
		this.start_el = start_el;
		this.slut_el = slut_el;
		this.plads_type = plads_type;
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

	public LocalDate getStart_dato() {
		return start_dato;
	}

	public void setStart_dato(LocalDate start_dato) {
		this.start_dato = start_dato;
	}

	public LocalDate getSlut_dato() {
		return slut_dato;
	}

	public void setSlut_dato(LocalDate slut_dato) {
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


	public int getPlads_type() {
		return plads_type;
	}


	public void setPlads_type(int plads_type) {
		this.plads_type = plads_type;
	}


	public int getAntal_hunde() {
		return antal_hunde;
	}

	public void setAntal_hunde(int antal_hunde) {
		this.antal_hunde = antal_hunde;
	}
	
	public String prettyPrint(){
		return "ID:" + getId() + " Start:" + getStart_dato() + " Slut:" + getSlut_dato() + " Type:" 
				+ HyttePlads.TYPER[getPlads_type()] + " Status:" + STATUSER[getStatus()];
	}
}
