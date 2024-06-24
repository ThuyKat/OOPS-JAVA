# OOPS-JAVA
# INHERITANCE
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

3. animal object will call overriden inherited methods of Cat, not Animal. For example, both Animal and Cat has makeNoise() method, but animal.makeNoise will return "Meow's method"

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


 
