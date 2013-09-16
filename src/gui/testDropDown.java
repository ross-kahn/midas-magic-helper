/**
 * 
 */
package gui;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 * @author Ross Kahn
 *
 */
public class testDropDown {

	public testDropDown(){
		JFrame window = new JFrame("Test");
		
		JTable table = new JTable();
		
		
		window.add(table);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(825, 250);
		window.setVisible(true);
	}
}
