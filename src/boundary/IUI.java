package boundary;

public interface IUI {
	
	int visMenu(String tittel, String[] valgmulighedder);
	String input(String besked);
	String[] multiInput(String tittel, String[] navne);
	
	boolean bekræft(String besked);
	
	void besked(String besked);
	
}
