package association;

public class Client {
public static void main(String[] args) {
	University university = new University("HVTC");
	Department department = university.createDepartment("Accounting");
	
	Professor professor = new Professor("Micheal Thomas");
	department.assign(professor);
}
}
