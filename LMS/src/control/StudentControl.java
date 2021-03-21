package control;

import java.io.FileNotFoundException;

import entity.Student;
import entity.StudentEntity;

public class StudentControl {

	private StudentEntity studentEntity;
	
	public StudentControl() throws FileNotFoundException {
		this.studentEntity = new StudentEntity();
	}
	public Student login(String userName, String password) {
		Student student = 
				this.studentEntity.login(userName, password);
		return student;
	}

}
