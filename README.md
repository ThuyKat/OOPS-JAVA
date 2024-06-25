# OOPS-JAVA
# INHERITANCE, RUNTIME POLYMORPHISM, ASSOCIATION
1. When child class constructor is evoked, parents class constructor is automatically evoked

For example, we have parent class named "Animal" : 
```java
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
```
And a child class named " Cat":

```java
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
```
In main(), if we initiate: 
```java
Animal animal = new Cat();
```
This will return 2 statements, first one is ' This is default constructor of Animal, second one is 'This is cat's default constructor'

2. animal object will not be able to evoke methods that are exist in Cat but not in Animal. In other words, it can only call methods that Cat inherits from Animal. For example: animal.pur() will throw an error

3. animal object will call overriden inherited methods of Cat, not Animal. For example, both Animal and Cat has makeNoise() method, but animal.makeNoise will return "Meow's method". The method to be executed is determined at runtime based on the actual object's class. Object is a reference of type Animal, but it actually referes to an object of type Cat. The decidsion of which method to call is made during runtime, hence the term runtime polymorphism

4. animal object can access attributes declared in Animal,but not Cat. For example, animal.foodEaten will return "Parent's foodEaten" if foodEaten are there in both Cat and Animal. It wont be able to return value of attributes declared in Cat. 

5. animal object is an example of runtime polymophism. Runtime polymorphism happens when we create an instance of Child class and store it in parent reference type. 

6. In order to make animal object return values of Cat's attributes instead of Animal, we have two options:
* Use "super" keyword and re-declare the attributes of parents' class in child class (either inside or outside constructor of child class)
```java
Cat(String foodEaten){
	//super()
	this.foodEaten = foodEaten;
	super.foodEaten = "super attb declared inside child's constructor";
}
```
```java
Animal animal1 = new Cat("abc");
	System.out.println(animal1.foodEaten);
```
Now the return at main() will be "super attb declared inside child's constructor"

* Downcasting animal to Cat type :
```java
Cat cat = (Cat) animal1;
```
Now animal1.foodEaten will print out "abc". 

7. Parent-child relationship : is-a relationship, which means if parents got destroyed, child will be destroyed ( not the other way around) 

8. Association: has-a relationship. For example, Class Car has an instance of class Wheel:

```java
Class Wheel{
    int abc;
    public isAlignedCorrectly();
}
Class Car{
    Wheel wheel;
}
```
--> To access methods of of wheel from instance car of Car : 
```java
car.wheel.isAlignedCorrectly();
 ```
The above relationship is a strong relationship since Wheel will not exist without Car. Strong relationship is called a **composition**, which in contrast to a weak relationship - an **aggregation**

**Example of Association: composition and aggregation**

Composition strong relationship: University and Department
Aggregation weak relationship: Department and Professor

- University must be created first before department can be created. Department can only be instantiated by using an instance of University. 
```java
University university = new University("HVTC");
Department department = university.createDepartment("Accounting");
```
In other to establish this composition relationship, we define constructor of Department in Department class as below: 
```java
public Department(University university, String name) {
	this.name = name;
}
```
In University class, we have createDepartment method: 

```java
public Department createDepartment(final String depName) {
	final Department dep = new Department(this,depName);
	department = dep;
	return dep;
}
```
In contrast, since Professor only has aggregation relationship with Department, we can instantiate a professor without going through department instance: 
```java
Professor professor = new Professor("Micheal Thomas");
	department.assign(professor);
```
# NESTING CLASSES
# INTERFACE

1. All methods in interface are public and abstract (without the need of declaring so)
2. We wont be able to create an instance from interface, but we can use interface as a type reference, which allows runtime polymorphism. When you declare a variable with an interface type, you are saying that the variable can refer to any object that implements that interface. The actual object assigned to the variable will be an instance of a class that implements the interface
For example, we have Animal interface and Pig class provides implementation for method makeSound() of Animal. In main : 
```java
public class client {
public static void main(String[] args) {
	Pig pig = new Pig();
	Animal animal = new Pig();
	
	pig.animalSound(); // method of Pig

	animal.animalSound(); // method of Animal
}
}
```
pig.animalSound() and animal.animalSound() return the same result, eventhough pig.animalSound() is method defined in Pig class and animal.animalSound() is method defiend in Animal class. Evenif we change makeSound() in Animal to default method and provides default implementation, the result is unchanged. 

```java
public interface Animal {
	default void animalSound() {
		System.out.println("Animal's sound");
	};
}
```
We only get the return of default method if we choose not to override the method in Pig class. 
3. An interface can only extends another interface, not implements. The purpose of the extension is to create a new interface that inherits the methods and constants from one or more existing interface. 
* The child interface do not provide implementations for inherited abstract methods. These methods will stay abstract and be implemented when another interface/class implements this child interface
* In case that we want to implement the abstract method in child interface, we must write the implemented method as a default method in the child interface. For example: 
```java
interface ParentInterface{
    void abstractMethod();
}
interface ChildInterface extends ParentInterface{
    default void abstractMethod(){
        System.out.println("default implementation of abstract method in child interface");
    }
} 
```
Later if there is class/interface that implements this Child interface, it does not need to provide implementation for this abstractMethod() any more unless it chooses to override the default implementation
4. all instance variable are public static final in interface. 
5. If one class implements 2 interfaces which have the same default methods. For example, apart from Animal, Pig also implements Animal2 which also has makeSound() method. Before Java 8, there would be no issues since Java 8 does not have default methods in Interface and only abstract methods without any implementation. But later when default method is introduced, it causes this issue. There are two options: 

* Option 1:  Pig must provide its own implementation of the method. In other words, Pig must override makeSound(). 
* Option 2: If we want to use any version of the method in one of its interface/super class, we use keyword " super" : 
```java
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

}
```
3. Using parent type reference, we cannot evoke a static method using the instance because static methods are resolved at compile time based on the reference type. For example animal.staticMethod() cannot be evoked, we have to use the class name of parent: Animal.staticMethod(). This is different to Pig pig = new Pig(), if Pig class has static method, we can use either pig or Pig to evoke that method. 

4.  Interface VS abstract class vs concrete class
* Interface: can only extend interface(s). Able to provide (with default keyword) or not provide implementations for inherited abstract methods [ALLOW PARTIAL IMPLEMENTATION]
* concrete class: can extend only one another class ( either abstract or concrete) and can implement interface(s). Concrete class have to provide implementation for all inherited abstract methods either from the parents class or interface(s)
* abstract class: can only extend another abstract class, can implement interface(s). Different to concrete class, abstract class does not have to provide implementations for all the interface's methods or parent abstract class' methods.It can leave implementation of some or all of these methods to its subclasses [ALLOW PARTIAL IMPLEMENTATION]

