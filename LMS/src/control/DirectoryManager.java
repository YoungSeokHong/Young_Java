package control;

import java.io.FileNotFoundException;
import java.util.Vector;

import entity.Directory;
import entity.DirectoryEntity;

public class DirectoryManager {
	private DirectoryEntity directoryEntity;
	public DirectoryManager() {
		this.directoryEntity = new DirectoryEntity();
	}
	public Vector<Directory> getDirectorues(String selection) throws FileNotFoundException {
		return this.directoryEntity.readFromFile(selection);		
	}

}
