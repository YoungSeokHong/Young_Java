package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class StudentEntity {
	
	private Vector<Student> studentList;
	
	public StudentEntity() throws FileNotFoundException {
		this.studentList = new Vector<Student>();
		this.readFromFile();
	}
	private void readFromFile() throws FileNotFoundException {
		File file = new File("data/student.txt");
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			Student student = new Student();
			student.readFromFile(scanner);
			this.studentList.addElement(student);
		}	
		
	}
	
	// 로그인 기능
	public Student login(String userName, String password) {
		for(Student student: this.studentList) {
			if (student.getUserName().equals(userName) &&
					student.getPassword().equals(password)) {
				return student;				
			}
		}
		return null;
	}
}
