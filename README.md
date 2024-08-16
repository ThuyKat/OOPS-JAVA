# OOPS-JAVA
## INHERITANCE, RUNTIME POLYMORPHISM, ASSOCIATION
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

3. animal object will call overriden inherited methods of Cat, not Animal. For example, both Animal and Cat has makeNoise() method, but animal.makeNoise will return "Meow's method". The method to be executed is determined at runtime based on the actual object's class. Object is a reference of type Animal, but it actually referes to an object of type Cat. The decision of which method to call is made during **runtime**, hence the term **runtime polymorphism**

4. animal object can access attributes declared in Animal,but not Cat. For example, animal.foodEaten will return "Parent's foodEaten" if foodEaten are there in both Cat and Animal. It wont be able to return value of attributes declared in Cat. This is **Data Hiding** feature- internal presentation of an object is hidden -  when a subclass defines a variable with the same name as one in its superclass, the subclass' data contained in the variable is hidden. This happens because variable hiding does not support runtime polymorphism. It is a good practice to avoid variable hiding because it can lead to confusion and code hard to maintain. Using different names for variable in the subclass and superclass is recommended. 

***Data Hiding achieved by using access modifiers and abstractions are recommended***

This is because: 
* Encapsulation: access modifiers keep the state of the internal object safe from unintended harmful interference
* Abstraction: expose only necs info and hide implement details. 

5. animal object is an example of runtime polymophism. Runtime polymorphism happens when we create an instance of Child class and store it in parent reference type. In other words, runtime polymorphism  happens in Upcasting -when an instance of Child class and store it in parent reference type . Upcasting is a technique to achieve functional overriding - to run only the function which is overriden in child class. To run new function created in the child class, we can Downcasting the upcasted object

***With upcasting, at runtime we can decide which behaviours to use***

Example: 
```java
void abc ( Vehicle veh){
	veh.drive();
}

// if we want the behaviour defined in parents class
abc(new Vehicle());

// if we want the behaviour defined in child class
abc(new Car());
```

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
## NESTING CLASSES
- Association is differnt to Nesting classes. Eventhough department can only be instantiated once university is instantiated via .createDepartment() method, Department entity lies outside of University entity. In contrast, the nesting classes have one class lies totally inside the other:
```java
public class NestingDemo {
	static String nameOfNestingDemo;
	String idOfNestingDemo;
 public void print() {
	 System.out.println("NestingDemo");
 }
 
 class InnerDemo{
	 static String nameOfInnerDemo;
	 String idOfInnerDemo;
	 public void print() {
		 System.out.println("InnerDemo");
	 }
	 public String accessOuterField() {
		 return idOfNestingDemo;
	 }
	 public String accessOuterStaticField() {
		 return nameOfNestingDemo;
	 }
 }
 ```
 - To create instance of InnerDemo:

 STEP 1: create instance of NestingDemo : NestingDemo nestingDemo = new NestingDemo();

 STEP 2: use the nestingDemo object to create instance of InnerDemo: NestingDemo.InnerDemo innerDemo = nestingDemo.new InnerDemo();

 ```java
 NestingDemo nestingDemo = new NestingDemo();
	nestingDemo.print();
	
	/*create an instance of nested class named InnerDemo: using instance of NestingDemo
	 * It requires an instance of the inner class to access its methods and attributes
	 */
	NestingDemo.InnerDemo innerDemo = nestingDemo.new InnerDemo();
	
	innerDemo.print();
```
- In Java, it does not allow an entire class to be declared as static.  However, you can declare static nested classes.

```java
public class NestingDemo {
	static String nameOfNestingDemo;
	String idOfNestingDemo;
 public void print() {
	 System.out.println("NestingDemo");
 }
 static class StaticInnerDemo{
	 static String nameOfStaticInnerDemo;
	 String idOfStaticInnerDemo;
	 public void print() {
		 System.out.println("StaticInnerDemo");
	 }
	 
	 public static void staticPrint() {
		 System.out.println("StaticInnerDemo.staticPrint()");
	 }
	 
	 public String accessOuterField() {
		 return nameOfNestingDemo;
	 }
 }
}
```
- Difference between StaticInnerDemo and InnerDemo is that we dont need an instance of NestingDemo to call instance of StaticInnerDemo:
```java
/*In Java, it does not allow an entire class to be declared as static. 
	 * However, you can declare static nested classes. 
	 * It can access static members of the outer class 
	 * Outer class can access static class' attributes and methods via the StaticInnerClass name
	 */
	//create an instance of nested static class: using class name of NestingDemo
	NestingDemo.StaticInnerDemo staticInnerDemo = new NestingDemo.StaticInnerDemo();
	
	staticInnerDemo.print();
```
### When to use normal inner class and when tho use static inner class??
- Static class cannot access the outer class' instance variables if they are not static variables ->  hence, we use normal inner class if we want to access non-static attributes of outer class
- 

## INTERFACE

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
3. Using parent type reference, we cannot evoke a static method using the instance because static methods are resolved at compile time based on the reference type, not at runtime based on the object type. For example animal.staticMethod() cannot be evoked, we have to use the class name of parent: Animal.staticMethod(). This is different to Pig pig = new Pig(), if Pig class has static method, we can use either pig or Pig to evoke that method. 

##  Interface VS abstract class vs concrete class
* Interface: can only extend interface(s). Able to provide (with default keyword) or not provide implementations for inherited abstract methods [ALLOW PARTIAL IMPLEMENTATION]
* concrete class: can extend only one another class ( either abstract or concrete) and can implement interface(s). Concrete class have to provide implementation for all inherited abstract methods either from the parents class or interface(s)
* abstract class: can only extend another abstract class, can implement interface(s). Different to concrete class, abstract class does not have to provide implementations for all the interface's methods or parent abstract class' methods.It can leave implementation of some or all of these methods to its subclasses [ALLOW PARTIAL IMPLEMENTATION]

## EXCEPTION HANDLING
- Exception and Error are extension of Throwable class to handle to unexpected scenarios
- Exception include checked exception ( forced to handle) and unchecked exception ( not forced to handle)
- Checked Exception: are checked at compile time. Methods that possibly throw checked exceptions must declare them using the "throws" keyword in the method signature
- Unchecked Exception[ Runtime exception ]: are not checked at compile time. Hence compiler does not require methods to declare them in the method signature. These exceptions are handled by try-catch blocks and throw in method body. Throw keyword is used to transfer control to the nearest enclosing catch block that can handle the specified exception

## EQUAL AND HASHMAP

- In Object class, == is used to compare addresses of 2 object in equal method

--> it is used to compare primitives
- In String class, Equal method is overriden so that 2 String objects will be compared by their values not addresses
--> it is used to compare objects
```java
String a = new String("thuy");
		String b = new String("thuy");
		System.out.println(a==b); //false
		System.out.println(a.equals(b)); //true
```
- Replicate the way Equal method overriding in String class, we can also override Equal method in other class. For example, we have a student class
```java
public class Student {

	String name;

	Integer rollNo;

	public Student(String name, Integer rollNo) {
		this.name = name;
		this.rollNo = rollNo;
	}
}
```
And we want student to be compared by name and rollNo instead of memory addresses. Here we override the default equal method:
```java
@Override
	public boolean equals(Object s) {
		if (this == s) { // comparing with the same object
			// == is defined by the Java compiler and the java runtime environment
			return true;
		}
		if (!(s instanceof Student)) {
			return false;
		}

		Student student = (Student) s;
		if (this.name.equals((student.name)) && (this.rollNo == student.rollNo)) {
			return true;
		}
		return false;
	}
```
--> Note that:

1. Object must be the same type to be compared
2. Comparing same object will always return true 

- When overriding equal(), we just change the way objects are compared in our application. However, in Java operation, for example, to decide the object's location in a HashMap or a HashSet, it will still use the objects' addresses by default in its calculation. 

- Hence to make sure that objects are compared on a consistent basis across our application, we can implement custom hashcode() method

- This means we compute the hashcode based on the values of the object's fields.This is to ensure that two objects with the same data ( equal accordig to equals()) have the same hashcode

```java
@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.name.hashCode() + this.rollNo.hashCode();
	}
```

- Because HashCode is calculated, 2 unequal objects can still have the same hashcode. This is called **Collisions**

--> RULE:
1. When equals() return true, hashcode will always return true
2. When hashcode() return true, equals() can be true or false due to collisions

--> It is recommended that when equals() is overriden, hashcode also need to be overriden. Any change in equals() needs equivalent change in hashcode().