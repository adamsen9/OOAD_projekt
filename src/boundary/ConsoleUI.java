package boundary;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ConsoleUI implements IUI{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public ConsoleUI(){
		
	}

	@Override
	public int visMenu(String tittel, String[] s) {
		System.out.println(tittel);
		
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
	public String input(String besked) {
		System.out.println(besked);
			try {
				return br.readLine();
			} catch(Exception e) {
				// TODO mere beskrivende fejlmeddelelse
				System.out.println("der skete en fejl");
				return null;
			}
	}

	@Override
	public String[] multiInput(String tittel, String[] navne) {
		System.out.println(tittel);

		String[] svar = new String[navne.length];
		for(int i = 0; i < navne.length; i++){
			svar[i] = input(navne[i]);
		}
		return svar;
	}

	@Override
	public boolean bekræft(String besked) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void besked(String besked) {
		System.out.println(besked);
	}
	
	
	
}
