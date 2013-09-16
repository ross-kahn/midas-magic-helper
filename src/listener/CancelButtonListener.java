package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CancelButtonListener implements ActionListener{
	
	JDialog window;
	
	public CancelButtonListener(JDialog menu){
		window = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		window.setVisible(false);
	}
	
}
