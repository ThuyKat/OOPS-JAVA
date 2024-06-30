package Interface;

public interface Animal2 {
	default void animalSound() {
		System.out.println("Animal2's sound");
	};
}
