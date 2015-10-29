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
		int d;
		for(String t: s) {
			System.out.println(i + ". " + t);
			i++;
		}
		System.out.println("Indtast valg");
		while(true) {
			try {
				d = Integer.parseInt(br.readLine());
				if(d < 0 || d >= i) {
					System.out.println("Indtast tal mellem 0 og " + (i-1));
				} else {
					return d;
				}
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

	@Override
	public boolean confirm() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
