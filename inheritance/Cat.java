package inheritance;

public class Cat extends Animal {
String family;
String foodEaten ="Child's foodEaten";
Cat(){
	//super() - this always got evoked whether we declared it or not, either this or parameterized parent's constructor
	System.out.println("This is cat's default constructor");
}

Cat(String foodEaten){
	//super()
	this.foodEaten = foodEaten;
	super.foodEaten = "super attb declared inside child's constructor";
}

void makeNoise() {
	System.out.println("Mieow's method");
//	Cat cat = new Cat();
//	cat.isVegetarian = "true";
}

void purring() {
	System.out.println("pur");
}

}
