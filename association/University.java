package association;

public class University {
private String uniName;
private Department department;

//CONSTRUCTOR
public University(String universityName) {
	this.uniName = universityName;
}

public Department createDepartment(final String depName) {
	final Department dep = new Department(this,depName);
	department = dep;
	return dep;
}

// if University is destroyed, department is also destroyed
public void destroy() {
	department = null;
}

}
