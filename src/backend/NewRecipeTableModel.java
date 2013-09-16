/**
 * 
 */
package backend;

import gui.GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


/**
 * @author Ross Kahn
 *
 */
public class NewRecipeTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	public static final int CHECK_BOX_COL = 0;
	public static final int NAME_COL = 1;
	public static final int MIN_NUM_COL = 2;
	public static final int MAX_NUM_COL = 3;
	
	private ArrayList<IngredientQuantity> data;
	private ArrayList<IngredientQuantity> allData;
	private String[] columnNames = {"", "Ingredient Name", "Minimum Amount", "Maximum Amount"};
	private ArrayList<IngredientQuantity> selectedIngredients;
	private GUI gui;
	private MVController mvc;
	private String searchFilter;
	
	public NewRecipeTableModel(MVController mvc, JTable parentTable, GUI gui){
		allData = mvc.getSortedIngredientQuantities();
		data = allData;
		resetIQValues();
		selectedIngredients = new ArrayList<IngredientQuantity>();
		this.gui = gui;
		searchFilter = "";
		this.mvc = mvc;
	}
	
	private void resetIQValues(){
		for(IngredientQuantity iq : data){
			iq.setMax(1);
			iq.setMin(1);
		}
	}
	
	public void logFilter(char toAdd){

		addChar(toAdd);
		
		if(searchFilter.isEmpty()){
			resetData();
		}else{
			filterData(allData, searchFilter);
		}
		this.fireTableDataChanged();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(col == NAME_COL){
			return data.get(row).getIngredient().getName();
		}else if(col == CHECK_BOX_COL){
			return selectedIngredients.contains(data.get(row));
		}else if (col == MIN_NUM_COL){
			return data.get(row).getMin();
		}else if (col == MAX_NUM_COL){
			return data.get(row).getMax();
		}else{
			return null;
		}
	}
	
	public void toggleAddIngredient(int row){

		if((Boolean) getValueAt(row, CHECK_BOX_COL)){
			selectedIngredients.remove(data.get(row));
		}else{
			selectedIngredients.add(data.get(row));
		}
		this.fireTableDataChanged();
	}
	
	public ArrayList<IngredientQuantity> getSelectedIngredients(){
		return selectedIngredients;
	}
	
	public Class<?> getColumnClass(int c) {
		if(c == CHECK_BOX_COL){
			return Boolean.class;
		}else if(c == MAX_NUM_COL || c == MIN_NUM_COL){
			return Integer.class;
		}else{
			return String.class;
		}
    }
	
	@Override
	public String getColumnName(int columnIndex){
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex){
		if(columnIndex == NAME_COL){
			return false;
		}else{
			return true;
		}
	}
	
	public void setValueAt(Object value, int row, int column){
		
		if(column == MIN_NUM_COL){
			int val = Math.abs((Integer) value);
			data.get(row).setMin(val);
			if(val > data.get(row).getMax()){
				data.get(row).setMax(val);
			}
			
		}else if(column == MAX_NUM_COL){
			int val = Math.abs((Integer) value);
			data.get(row).setMax(val);
			if(val < data.get(row).getMin()){
				data.get(row).setMin(val);
			}
		}
		toggleAddIngredient(row);
		this.fireTableRowsUpdated(row, row);
		gui.populateAddedIngredientsList();
	}
	
	private void filterData(ArrayList<IngredientQuantity> oldData, String filter){
		data = new ArrayList<IngredientQuantity>();
		for(IngredientQuantity iq: oldData){
			if(iq.getIngredient().getName().toLowerCase().contains(filter.toLowerCase())){
				data.add(iq);
			}
		}
	}
	
	private void resetData(){
		allData = mvc.getSortedIngredientQuantities();
		data = allData;
	}
	
	/**
	 * adds a user-input character into the filter text. If the character
	 * is a backspace, this method also serves to delete the last element
	 * in the filter text
	 * @param c		character to be added (or backspace)
	 */
	public void addChar(char c){
		//System.out.println(Character.getType(c));
		
		if (Character.getType(c) == 15){ // 15 is the 'type' for backspace
			if (!searchFilter.isEmpty()){
				char[] oldText = searchFilter.toCharArray();
				String newText = "";
				for(int i=0; i<oldText.length - 1; i++){
					newText = newText + (Character) oldText[i];
				}
				searchFilter = newText;
			}
		}else{
			searchFilter = searchFilter + c;
		}
	}
	
}
