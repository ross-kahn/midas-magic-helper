package backend;

public abstract class Item {

	private String info;
	private String name;
	
	public Item(String name){
		this.name = name;
		this.info = "No Information Available";
	}
	
	public void setInfo(String information){
		info = information;
	}
	
	public String getInfo(){
		return info;
	}
	
	public String getName(){
		return name;
	}
	
		
}
