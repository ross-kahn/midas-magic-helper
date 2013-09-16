package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class MVController {

	private ArrayList<String> recipeData;
	private ArrayList<String> ingredientData;
	private ArrayList<String> filteredData;
	private int currDataset;
	private String searchFilter;
	private DataWriter writer;
	//private ArrayList<String> activeData;
	public static final int RECIPE = 0;
	public static final int INGREDIENT = 1;
	
	public MVController(DataWriter writer){
		recipeData = new ArrayList<String>();
		ingredientData = new ArrayList<String>();
		resetData();
		this.writer = writer;
		currDataset = INGREDIENT;
		filteredData = ingredientData;
		searchFilter = "";
	}
	
	public ArrayList<String> getCurrentFilteredData(){
		Collections.sort(filteredData);
		return filteredData;
	}
	
	public void writeData(Ingredient i){
		if(!Main.reading){
			writer.write(i);
		}
	}
	
	public void writeData(Recipe r){
		if(!Main.reading){
			writer.write(r);
		}
	}
	
	public ArrayList<String> getAllSortedIngredientData(){
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.addAll(Ingredient.allIngredients.keySet());
		sortData(ingredients);
		return ingredients;
	}
	
	public ArrayList<String> getAllSortedRecipeData(){
		ArrayList<String> recipes = new ArrayList<String>();
		recipes.addAll(Recipe.allRecipes.keySet());
		sortData(recipes);
		return recipes;
	}
	
	public ArrayList<IngredientQuantity> getSortedIngredientQuantities(){
		ArrayList<IngredientQuantity> iq = new ArrayList<IngredientQuantity>();
		iq.addAll(Ingredient.allIngredientQuantities.values());
		Collections.sort(iq);
		return iq;
	}
	
	public void setActiveData(int dataSet){
		resetFilter();
		currDataset = dataSet;
	}
	
	public void resetData(){
		recipeData.clear();
		ingredientData.clear();
		
		Set<String> rawRecipe = Recipe.allRecipes.keySet();
		Set<String> rawIngredient = Ingredient.allIngredients.keySet();
		
		recipeData.addAll(rawRecipe);
		ingredientData.addAll(rawIngredient);
		
		if(currDataset == INGREDIENT){
			filteredData = ingredientData;
		}else{
			filteredData = recipeData;
		}
		
		sortData();
	}
	
	public Ingredient getSelectedIngredient(int index){
		return Ingredient.allIngredients.get(filteredData.get(index));
	}
	
	public Recipe getSelectedRecipe(int index){
		return Recipe.allRecipes.get(filteredData.get(index));
	}
	
	public void sortData(ArrayList<String> recipes, ArrayList<String> ingredients){
		Collections.sort(recipes);
		Collections.sort(ingredients);
	}
	
	public void sortData(ArrayList<String> collection){
		Collections.sort(collection);
	}
	
	public void sortData(){
		sortData(recipeData, ingredientData);
	}

	
	private void filterData(String filter){
		filteredData = new ArrayList<String>();

		if(currDataset == RECIPE){
			filterData(recipeData, filter);
		}else{
			filterData(ingredientData, filter);
		}
		sortData();
	}
	
	public void filterData(ArrayList<String> oldData, String filter){
		
		for(String s: oldData){
			if(s.toLowerCase().contains(filter.toLowerCase())){
				filteredData.add(s);
			}
		}
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
	
	public void logFilter(char toAdd){
		addChar(toAdd);
		filterData(searchFilter);
	}

	public String getFilter(){
		return searchFilter;
	}
	
	public void resetFilter(){
		searchFilter = "";
	}
}
