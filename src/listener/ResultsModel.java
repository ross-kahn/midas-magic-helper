package listener;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class ResultsModel extends AbstractListModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<String> data;
	private boolean ingredientsView;
	
	public ResultsModel(ArrayList<String> data, boolean viewType){
		this.data = data;
		ingredientsView = viewType;
	}
	
	@Override
	public Object getElementAt(int arg0) {
		return data.get(arg0);
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return data.size();
	}
}
