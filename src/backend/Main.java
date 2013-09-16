package backend;

import gui.GUI;


/**
 * @author Ross Kahn
 *
 */
public class Main {

	
	public static boolean reading;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		reading = true;
		MidasReader reader = new MidasReader();
		reading = false;

		DataWriter writer = new DataWriter();
		MVController mvc = new MVController(writer);
		GUI gui = new GUI(mvc);
		gui.setVisible(true);
		
	}


}
