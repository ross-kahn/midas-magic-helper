package backend;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 */

/**
 * @author Ross Kahn
 *
 */
public class Ingredient extends Item{
	
	public static HashMap<String, Ingredient> allIngredients = new HashMap<String, Ingredient>();
	public static HashMap<String, IngredientQuantity> allIngredientQuantities = new HashMap<String, IngredientQuantity>();
	// should maintain a reference to all recipes it is a part of
	
	private HashSet<Recipe> parentRecipes;
	
	public Ingredient(String name){
		super(name);
		parentRecipes = new HashSet<Recipe>();
		allIngredients.put(name, this);
		allIngredientQuantities.put(name, new IngredientQuantity(this));
	}
	
	public void addParentRecipe(Recipe recipe){
		parentRecipes.add(recipe);
	}
	
	public HashSet<String> getPrintableParentRecipes(){
		HashSet<String> printable = new HashSet<String>();
		for (Recipe r: parentRecipes){
			printable.add(r.getName());
		}
		return printable;
	}
	
	public int parentRecipeSize(){
		return parentRecipes.size();
	}

}
