package Interface;

public class Pig implements Animal, Animal2 {

	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		Animal.super.animalSound();
	}

//	@Override
//	public void animalSound() {
//		System.out.println("Here is the Pig's sound");
//		
//	}
	
	public static void eatFood() {
		System.out.println("Pig is eating");
	}

}
