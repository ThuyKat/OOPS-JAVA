package inheritance;

public class Animal {
	protected String foodEaten = "Parent's foodEaten";
	int noOfLegs;
	protected String isVegetarian;
	
	protected Animal(){
		System.out.println("This is default constructor of Animal");
	}
	
	void makeNoise() {
		System.out.println(" animal's method");
	}

}
