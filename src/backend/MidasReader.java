package backend;

import static backend.Constants.*;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import exceptions.MissingInformationException;
import exceptions.RedundantInformationException;

public class MidasReader {

	private int debug;
	
	public MidasReader(){
		debug = 0;
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
		
		boolean PIsuccess = parseData(data);
		//boolean RIsuccess = parseData(PARSE_RECIPES, data);
		//return PIsuccess && RIsuccess;
		return true;
	}
	
	private boolean parseData(File data){
		Scanner lineScanner;
		String blockBuffer = "";
		boolean hasDescription = false;
		
		try {
			lineScanner = new Scanner(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		while(lineScanner.hasNext()){				// go until end of file
			String currLine = lineScanner.nextLine();
			if(currLine.startsWith(PARSE_RECIPES)){
				breakBlock(blockBuffer);
				blockBuffer = "";

			}else{
				
			}
			blockBuffer = blockBuffer + currLine + "\n";
		}
		breakBlock(blockBuffer);
		return true;
	}
	
	
	private void breakBlock(String block){
		block = block.trim();
		if(block.isEmpty()){
			return;
		}
		
		Scanner blockScanner = new Scanner(block);
		String recipeLine = "";
		String rdescriptionLines = "";
		String ingredientLines = "";
		String currLine;
		while(blockScanner.hasNextLine()){
			currLine = blockScanner.nextLine();
			if(currLine.startsWith(PARSE_RECIPES)){
				recipeLine = currLine;
			}else if(currLine.startsWith(PARSE_INGREDIENTS) || currLine.startsWith(INGREDIENT_DESC)){
				ingredientLines = ingredientLines + currLine + "\n";
			}else if(currLine.startsWith(RECIPE_DESC)){
				rdescriptionLines = rdescriptionLines + currLine + "\n";
			}else{
				System.out.println("Line has no ID: " + currLine);
			}
		}

		try {
			Recipe r = parseRecipe(recipeLine);
			parseIngredientLines(ingredientLines, r);
			parseDescription(rdescriptionLines, r);
		} catch (MissingInformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RedundantInformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseIngredientLines(String iLines, Recipe r) throws MissingInformationException{
		iLines = iLines.trim();
		if(iLines.isEmpty()){
			throw new MissingInformationException("Attempted to create recipe " + 
					r.getName()+" without any ingredients");
		}
		
		Scanner iScanner = new Scanner(iLines);		// Scanner for the 1-* lines containing 
													// ingredients and their descriptions
		Ingredient currIngredient = null;			// The most recently parsed Ingredient
		
		while(iScanner.hasNextLine()){
			String curriLine = iScanner.nextLine();
			if(curriLine.startsWith(PARSE_INGREDIENTS)){
				
				curriLine = curriLine.replaceFirst("^i: ?", "");
				Point minMax = parseIngredientQuantity(curriLine);
				curriLine = curriLine.replaceFirst("^[0-9]+ ?-?[0-9]? +", "");
				int min = minMax.x;
				int max = minMax.y;
				
				String iName = curriLine.trim();
				if(Ingredient.allIngredients.containsKey(iName)){
					currIngredient = Ingredient.allIngredients.get(iName);
				}else{
					currIngredient = new Ingredient(iName);
				}
				
				r.addIngredient(new IngredientQuantity(currIngredient, min, max));
			}else if(curriLine.startsWith(INGREDIENT_DESC)){
				if(currIngredient == null){
					throw new MissingInformationException("Ingredient description without matching " +
							"ingredient: " + curriLine);
				}else{
					parseDescription(curriLine, currIngredient);
				}
				
			}else{
				throw new MissingInformationException("Ingredient description without matching ingredient: " +
						curriLine);
			}
		}
	}
	
	private void parseDescription(String dLines, Item newItem){
		dLines = dLines.trim();
		if(dLines.isEmpty()){
			return;
		}
		
		Scanner descScanner = new Scanner(dLines);
		
		while(descScanner.hasNextLine()){
			String currLine = descScanner.nextLine();
			if(currLine.startsWith(INGREDIENT_DESC)){
				dLines = dLines.replaceFirst(INGREDIENT_DESC, "");
				newItem.setInfo(dLines);
			}else if(currLine.startsWith(RECIPE_DESC)){
				dLines=dLines.replaceFirst(RECIPE_DESC, "");
				newItem.setInfo(dLines);
			}
		}
		
	}
	
	private Recipe parseRecipe(String rLine) throws MissingInformationException, RedundantInformationException{

		if(rLine.isEmpty()){
			throw new MissingInformationException("Attempting to parse recipe without name");
		}
		rLine = rLine.replaceFirst("^r: ?", "");
		String rName = rLine.replaceFirst("^Midas['’]? +", "");
		rName = rName.trim();
		if(Recipe.allRecipes.containsKey(rName)){
			throw new RedundantInformationException(Recipe.allRecipes.get(rName));
		}
		
		return new Recipe(rName);
	}
	
	private Point parseIngredientQuantity(String iLine){
		// "1 ..... OR ....."
		// "3-5 ......."
		Scanner numScan = new Scanner(iLine);
		int min;
		int max;
		
		String numData = numScan.next();
		if(numData.matches("^[0-9][0-9]?-[0-9][0-9]?")){
			String[] numArray = numData.split("-");

			min = Integer.parseInt(numArray[0]);
			max = Integer.parseInt(numArray[1]);
		}else if(numData.matches("^[0-9]$")){
			min = Integer.parseInt(numData);
			max = min;
		}else{
			min = -1;
			max = -1;
		}
		
		return new Point(min, max);
	}
}