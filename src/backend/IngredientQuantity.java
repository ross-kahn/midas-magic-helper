package backend;
/**
 * 
 */

/**
 * @author Ross Kahn
 *
 */
public class IngredientQuantity implements Comparable<IngredientQuantity>{

	private Ingredient ingredient;
	private int min;
	private int max;
	
	public IngredientQuantity(Ingredient ingredient, int min, int max){
		this.ingredient = ingredient;
		this.min = min;
		this.max = max;
	}
	
	public IngredientQuantity(Ingredient ingredient){
		this(ingredient, 1, 1);
	}
	
	public Ingredient getIngredient(){
		return this.ingredient;
	}
	
	public int getMin(){
		return this.min;
	}
	
	public int getMax(){
		return this.max;
	}
	
	public void setMin(int newMin){
		min = newMin;
	}
	
	public void setMax(int newMax){
		max = newMax;
	}

	@Override
	public int compareTo(IngredientQuantity arg0) {
		// TODO Auto-generated method stub
		IngredientQuantity toCompare = (IngredientQuantity) arg0;
		return this.ingredient.getName().compareTo(toCompare.getIngredient().getName());
	}
	
	public String toString(){
		if(min == max){
			return min + " " + ingredient.getName();
		}else{
			return min + "-" + max + " " + ingredient.getName();
		}
	}
}
