package backend;
import static backend.Constants.DATA_INDEX;
import static backend.Constants.DESC_LINE;
import static backend.Constants.DESC_MARK;
import static backend.Constants.ING_QUANT_SPLITTER;
import static backend.Constants.MAX_INDEX;
import static backend.Constants.MIN_INDEX;
import static backend.Constants.NAME_INDEX;
import static backend.Constants.PARSE_INGREDIENTS;
import static backend.Constants.PARSE_RECIPES;
import static backend.Constants.datafile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Ross Kahn
 *
 */
public class DataReader {
	
	public DataReader(){
		File data = new File(datafile);
		if(data.exists()){
			System.out.println("File exists.. DataReader.java constructor");
			if (!readData(data)){
				System.err.println("File reading unsuccessful. Program may be unstable.");
			}
		}else{
			System.out.println("No file exists, creating new one... DataReader.java constructor");
		}
	}
	
	private boolean readData(File data){
		
		boolean PIsuccess = parseData(PARSE_INGREDIENTS, data);
		boolean RIsuccess = parseData(PARSE_RECIPES, data);
		return PIsuccess && RIsuccess;
	}
	
	private boolean parseData(String toParse, File data){
		
		Scanner lineScanner;
		boolean hasDescription = false;
		
		try {
			lineScanner = new Scanner(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		while(lineScanner.hasNext()){				// go until end of file
			String line = lineScanner.nextLine();	// next line
			if(line.toLowerCase().startsWith(toParse)){
				if(toParse.equals(PARSE_INGREDIENTS)){	// currently parsing Ingredients
					hasDescription = line.contains(DESC_MARK);
					Ingredient i = parseIngredient(line);
					if(hasDescription) {parseDescription(lineScanner, i);};
				}else{							// currently parsing Recipes
					hasDescription = line.contains(DESC_MARK);
					Recipe r = parseRecipe(line);
					if(hasDescription) {parseDescription(lineScanner, r);};
				}
			}else{
				if (!line.toLowerCase().startsWith(PARSE_INGREDIENTS) && 
						!line.toLowerCase().startsWith(PARSE_RECIPES) &&
								!line.startsWith(DESC_LINE) &&
								!line.isEmpty()){
					System.err.println("midasData.txt Corrupt, Cannot Proceed." +
							"\n  Persistor.java, parseData()");
					return false;
				}
			}
		}
		return true;
	}
	
	private Ingredient parseIngredient(String line){
		line = line.replaceFirst("^i: *", ""); // remove ID and leading spaces
		line = line.trim();	   // remove trailing spaces
		line = line.replaceFirst(DESC_MARK, "");
		return new Ingredient(line);
	}
	
	private void parseDescription(Scanner lineReader, Item newItem){
		String info = "";
		while(lineReader.hasNext()){
			String line = lineReader.nextLine();
			if(line.contains(DESC_MARK)){
				line = line.replaceFirst(DESC_MARK, "");
				line = line.replaceFirst("^ *"+DESC_LINE+" ?", "");
				line = line.trim();
				info = info + line + "\n";
				break;
			}
			line = line.replaceFirst("^ *"+DESC_LINE+" ?", "");
			info = info + line + "\n";
		}
		
		if(!"".equals(info)){
			newItem.setInfo(info);
		}
	}
	
	private Recipe parseRecipe(String line){
		
		line = line.replaceFirst(" *"+DESC_MARK, "");
		String ingredientRegex = ".*"+ING_QUANT_SPLITTER+"[0-9]+-[0-9]+$";
		String recipeName = "";
		
		line = line.replaceFirst("^r: *", ""); // remove ID and leading spaces
		String[] components = line.split(",");	// array of recipe name and ingredients
		
		if (components[NAME_INDEX].matches(ingredientRegex)){
			// ERROR, MISSING INGREDIENT NAME
		}else{
			recipeName = components[NAME_INDEX].trim();
		}
		
		Recipe recipe = new Recipe(recipeName);
		
		for(String ingredient: components){
			ingredient = ingredient.trim();
			
			// makes sure ingredient is in correct format, and skips recipe name
			if(ingredient.matches(".*"+ING_QUANT_SPLITTER+"[0-9]+-[0-9]+$")){
				recipeIngredientParser(recipe, ingredient);
			}
		}
		return recipe;
	}
	
	private void recipeIngredientParser(Recipe recipe, String rawIngredient){
		String[] ingredientComponents = rawIngredient.split(ING_QUANT_SPLITTER);
		String ingredientName = ingredientComponents[NAME_INDEX];
		String rawQuantities = ingredientComponents[DATA_INDEX];
		
		String[] quantities = rawQuantities.split("-");
		int min = Integer.parseInt(quantities[MIN_INDEX]);
		int max = Integer.parseInt(quantities[MAX_INDEX]);
		
		if (Ingredient.allIngredients.get(ingredientName) == null){
			System.err.println("ERROR: Ingredient '" + ingredientName + "' not defined." +
					"\nPersistor.java, recipeIngredientParser()");
		}
		
		IngredientQuantity iq = new IngredientQuantity(Ingredient.allIngredients.get(ingredientName),
				min, max);
		
		recipe.addIngredient(iq);
	}
}
