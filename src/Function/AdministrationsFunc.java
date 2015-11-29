package Function;


import Entity.HyttePladsDAL;
import Entity.Dataklasser.HyttePlads;

public class AdministrationsFunc {

	public static void opretHyttePlads(String[] attributes){
		HyttePlads ny = hyttePladsFraArray(attributes);
		HyttePladsDAL.createHyttePlads(ny);
	}
	
	public static void retHyttePlads(int id, String[] attributes){
		HyttePlads ny = hyttePladsFraArray(attributes);
		ny.setPlads_id(id);
		HyttePladsDAL.updateHyttePlads(ny);
	}
	
	private static HyttePlads hyttePladsFraArray(String[] attributes){
		//TODO implement
		return null;
	}

	public static void opretPris(String[] svar) {
		// TODO Auto-generated method stub
		
	}

	public static void opretSæson(String[] svar) {
		// TODO Auto-generated method stub
		
	}

	public static void retPris(int id, String[] svar) {
		// TODO Auto-generated method stub
		
	}

	public static void retSæson(int id, String[] svar) {
		// TODO Auto-generated method stub
		
	}

}
