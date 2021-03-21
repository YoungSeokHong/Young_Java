package control;

import java.io.FileNotFoundException;
import java.util.Vector;

import entity.Lecture;
import entity.LectureEntity;

public class LectureManager {
	private LectureEntity lectureEntity;
	public LectureManager() {
		this.lectureEntity = new LectureEntity();
	}
	public Vector<Lecture> getLectures(String fileName) throws FileNotFoundException {
		return this.lectureEntity.readFromFile(fileName);		
	}
	public void saveLectures(String fileName, Vector<Lecture> lectures) throws FileNotFoundException {
		this.lectureEntity.writeToFile(fileName, lectures);
	}

}
