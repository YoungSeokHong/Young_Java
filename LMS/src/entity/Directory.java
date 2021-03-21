package entity;

import java.util.Scanner;

public class Directory {
	private int id;
	private String name;
	private String fileName;
	
	public int getId() { return id;	}
	public void setId(int id) {	this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }

	public void readFromFile(Scanner scanner) {
		this.id = scanner.nextInt();
		this.name = scanner.next();
		this.fileName = scanner.next();
	}	
}
