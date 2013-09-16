package exceptions;

import backend.Item;


public class RedundantInformationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public RedundantInformationException(Item i){
		super("Attempted to create item " + i.getName() + ", however " + 
				i.getName() + "already exists in the system.");
	}
}
