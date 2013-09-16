package backend;

import java.io.BufferedWriter;
import java.io.File;

/**
 * @author Ross Kahn
 *
 */
public class DataWriter {

	File data;
	BufferedWriter writer;
	
	public DataWriter(){
		/*data = new File(datafile);
		if(!data.exists()){
			try {
				data.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writer = new BufferedWriter(new FileWriter(data, true));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public DataWriter(File data){
		this.data = data;
	}
	
	public void write(Recipe recipe){
		/*try {
			
			// ### Write recipe ID and recipe name
			writer.write(PARSE_RECIPES);
			writer.write(recipe.getName() + ",");
			
			// ## Write recipe ingredients
			ArrayList<IngredientQuantity> writeList = recipe.getWritableIngredients();
			
			for(int i=0; i<writeList.size(); i++){
			
				IngredientQuantity iq = writeList.get(i);
				String endComma = ", ";
				if(i == writeList.size()-1){
					endComma = "";
				}
				writer.write(iq.getIngredient().getName() + ING_QUANT_SPLITTER +
						iq.getMin() + "-" + iq.getMax() + endComma);
			}
			
			if(!writeInfo(recipe)){
				writer.newLine();
			}
			
			writer.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	private boolean writeInfo(Item i){
		return true;
		/*// ## Write recipe information, if there is any
		if(!i.getInfo().isEmpty()){
			try {
				writer.write(DESC_MARK);
				
				writer.newLine();
				
				Scanner infoScanner = new Scanner(i.getInfo());
				while(infoScanner.hasNextLine()){
					writer.write(DESC_LINE + " ");
					String line = infoScanner.nextLine();
					writer.write(line);
					writer.newLine();
				}
				writer.write(DESC_MARK);
				writer.newLine();
				
				writer.flush();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}*/
	}
	
	public void write(Ingredient ingredient){
		/*try {
			writer.write(PARSE_INGREDIENTS);
			writer.write(ingredient.getName());
			
			if(!writeInfo(ingredient)){
				writer.newLine();
			}
			
			writer.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
