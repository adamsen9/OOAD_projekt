package Boundary;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ConsoleUI implements IUI{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public ConsoleUI(){
		
	}

	@Override
	public int visMenu(String title, String[] s) {
		System.out.println(title);
		
		int i = 0;
		for(String t: s) {
			System.out.println(i + ". " + t);
			i++;
		}
		System.out.println("Indtast valg");
		while(true) {
			try {
				return Integer.parseInt(br.readLine());
			} catch(Exception e) {
				System.out.println("Indtast et tal input");
			}
		}
	}

	@Override
	public String input(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
