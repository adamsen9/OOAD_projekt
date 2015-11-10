package Function;

import Entity.AdministrationsDAL;
import Entity.Dataklasser.HyttePlads;

public class AdministrationsFunc {

	public static void opretHyttePlads(String[] attributes){
		HyttePlads ny = hyttePladsFraArray(attributes);
		AdministrationsDAL.createHyttePlads(ny);
	}
	
	public static void retHyttePlads(int id, String[] attributes){
		HyttePlads ny = hyttePladsFraArray(attributes);
		ny.setId(id);
		AdministrationsDAL.updateHyttePlads(ny);
	}
	
	private static HyttePlads hyttePladsFraArray(String[] attributes){
		//TODO
		return null;
	}

	public static void opretPris(String[] svar) {
		// TODO Auto-generated method stub
		
	}

	public static void opretSæson(String[] svar) {
		// TODO Auto-generated method stub
		
	}

}
