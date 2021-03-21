package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class LectureEntity {	
	private Vector<Lecture> lectures;	
	public LectureEntity() {
		this.lectures = new Vector<Lecture>();
	}
	
	public Vector<Lecture> readFromFile(String fileName) throws FileNotFoundException {
		File file = new File("data/"+fileName);
		Scanner scanner = new Scanner(file);
		this.lectures.clear();
		while (scanner.hasNext()) {
			Lecture lecture = new Lecture();
			lecture.readFromFile(scanner);
			lectures.add(lecture);
		}
		scanner.close();
		return lectures;		
	}

	public void writeToFile(String fileName, Vector<Lecture> lectures) throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter("data/"+fileName);
		for (Lecture lecture: lectures) {
			lecture.writeToFile(printWriter);
		}
		printWriter.close();
	}
}
