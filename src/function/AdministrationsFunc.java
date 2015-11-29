package function;


import java.util.ArrayList;

import entity.HyttePladsDAL;
import entity.dataklasser.Hytte;
import entity.dataklasser.HyttePlads;

public class AdministrationsFunc {

	public static void opretHyttePlads(String[] attributes){
		HyttePlads ny = hyttePladsFraArray(attributes);
		HyttePladsDAL.createHyttePlads(ny);
	}
	
	public static void retHyttePlads(int id, String[] attributes){
		HyttePlads ny = hyttePladsFraArray(attributes);
		ny.setId(id);
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

	public static ArrayList<Hytte> getAlleHytter() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ArrayList<HyttePlads> getHyttePladsAfType(int type){
		//TODO implement
		return null;
	}

	public static Hytte getHytte(int input) {
		// TODO Auto-generated method stub
		return null;
	}

	public static HyttePlads getHyttePlads(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<Hytte> getHytterAfType(String hytteType) {
		// TODO Auto-generated method stub
		return null;
	}

}
