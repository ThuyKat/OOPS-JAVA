package inheritance;

public class Client {
public static void main(String[] args) {
	Animal animal = new Cat();
	// running this will return 2 statements, first one is ' This is default constructor of Animal, second one is 'This is cat's default constructor'
	animal.makeNoise(); // this will not able to run because animal is Animal type, not cat Type. it only has methods of Animal
	System.out.println(animal.foodEaten); // print out the parent's foodEaten attribute
//	animal.pur();
	
	System.out.println();
	
	Animal animal1 = new Cat("abc");
	System.out.println(animal1.foodEaten); // even with parameterised constructor of child, it still print parent's foodEaten 
	System.out.println();
	
	System.out.println("Which method will be printed? Child or Parent's");
	animal1.makeNoise(); // with methods, it prints the CHILD's METHOD...
	
	//HOW TO MAKE IT PRINT THE VALUE OF CHILD CLASS' ATTRIBUTES ??
	// One way for it to declared child's attribute is using "super" keyword and redeclared in child's class, either in the child's constructor or outside
	// The other way is change animal1 back to Cat type:
	Cat cat1 = (Cat) animal1; // DOWNCASTING
	System.out.println("after downcasting");
	System.out.println(cat1.foodEaten); //after change animal1 back to Cat type via downcasting, it prints child's attribute value.
	System.out.println();
	
	animal1.isVegetarian = "true"; // unable to access because the attribute is only available within class Animal as its under private mode
	// when isVegetarian is changed to protected, it is accessible within same package, child class in other package
	// unable to access if isVeg in different package
	// protected: different package but accessible only for child class. Unable to use in Main. Can only use in child's methods.
	Cat cat = new Cat(); 
	// when child class constructor is evoked, parents class' constructor is automatically evoked
	cat.isVegetarian = "true";
}

}
