package entity;

import java.util.Scanner;

public class Student {
	// attributes
	private int id;
	private String userName;
	private String password;
	private int departmentId;
	
	// constructors & destructor
	public Student(){
	}
	// read & write
	void readFromFile(Scanner scanner) {
		this.setId(scanner.nextInt());
		this.setUserName(scanner.next());
		this.setPassword(scanner.next());
		this.setDepartmentId(scanner.nextInt());
	}
	void writeToFile() {}
	
	// getters & setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
}//end Student