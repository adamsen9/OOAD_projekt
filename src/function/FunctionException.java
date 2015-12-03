package function;

import java.util.ArrayList;

public class FunctionException extends Exception {
	
	private static final long serialVersionUID = 7281759899057507972L;

	private ArrayList<String> fejlbeskedder;
	
	public FunctionException(ArrayList<String> fejlbeskedder){
		this.fejlbeskedder = fejlbeskedder;
	}
	
	public ArrayList<String> getFejlbeskedder(){
		return fejlbeskedder;
	}

}
