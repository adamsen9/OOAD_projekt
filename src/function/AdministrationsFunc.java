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
		// TODO
		return null;
	}

	public static void opretPris(String[] svar) {
		// TODO
		
	}

	public static void opretSæson(String[] svar) {
		// TODO
		
	}

	public static void retPris(int id, String[] svar) {
		// TODO
		
	}

	public static void retSæson(int id, String[] svar) {
		// TODO
		
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Hytte> getAlleHytter() {
		return (ArrayList<Hytte>)HyttePladsDAL.getHyttePladserByType(HyttePlads.HYTTE);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<HyttePlads> getHyttePladsAfType(int type){
		return (ArrayList<HyttePlads>) HyttePladsDAL.getHyttePladserByType(type);
	}

	public static Hytte getHytte(int id) {
		return HyttePladsDAL.getHytte(id);
	}

	public static HyttePlads getHyttePlads(int id) {
		return HyttePladsDAL.getHyttePlads(id);
	}

	public static ArrayList<Hytte> getHytterAfType(String hytteType) {
		return HyttePladsDAL.getHytterAfType(hytteType);
	}

}
