package backend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 */

/**
 * @author Ross Kahn
 *
 */
public class Recipe extends Item{
	
	public static HashMap<String, Recipe> allRecipes = new HashMap<String, Recipe>();
	
	// only needs an array of ingredients that it needs
	
	private HashSet<IngredientQuantity> allIngredients;

	public Recipe(String name){
		super(name);
		allIngredients = new HashSet<IngredientQuantity>();
		allRecipes.put(name, this);
	}
	
	public void addIngredient(IngredientQuantity iq){
		Ingredient toAdd = iq.getIngredient();
		allIngredients.add(iq);
		toAdd.addParentRecipe(this);
	}
	
	public void addIngredients(ArrayList<IngredientQuantity> iqList){
		for(IngredientQuantity iq: iqList){
			addIngredient(iq);
		}
	}
	
	public ArrayList<IngredientQuantity> getWritableIngredients(){
		ArrayList<IngredientQuantity> retList = new ArrayList<IngredientQuantity>();
		retList.addAll(allIngredients);
		return retList;
	}

	public HashSet<String> getPrintableIngredients(){
		HashSet<String> retSet = new HashSet<String>();
		for(IngredientQuantity iq : allIngredients){
			if(iq.getMax() == iq.getMin()){
				retSet.add(iq.getMin() + " " + iq.getIngredient().getName());
			}else{
				retSet.add(iq.getMin() + "-" + iq.getMax() + " " + iq.getIngredient().getName());
			}
		}
		return retSet;
	}
	
}
