package Interface;

public interface Animal {
	default void animalSound() {
		System.out.println("Animal's sound");
	};
	
	static void eatFood() {
		System.out.println("Animal is eating");
	}
}
