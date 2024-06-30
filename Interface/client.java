package Interface;

public class client {
public static void main(String[] args) {
	Pig pig = new Pig();
	Animal animal = new Pig();
	
	pig.animalSound(); // method of Pig

	animal.animalSound(); // method of Animal
	
	pig.eatFood();
	Pig.eatFood();
	
	Animal.eatFood();
//	animal.eatFood();
}
}
