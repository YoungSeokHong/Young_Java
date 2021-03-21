package view;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import control.LectureManager;
import entity.Lecture;
import view.DirectoryPanel.ListSelectionHandler;

public class LectureList extends JTable {
	private static final long serialVersionUID = 1L;
	// controller
	private LectureManager lectureManager;
	// data model
	private DefaultTableModel tableModel;
	// entity data
	private Vector<Lecture> lectures;
	// selected lectures
	private Vector<Lecture> selectedLectures;
	public Vector<Lecture> getSelectedLectures() {
		this.selectedLectures = new Vector<Lecture>();
		for (int i=0; i<this.getRowCount(); i++) {
			if (this.isRowSelected(i)) {
				this.selectedLectures.addElement(lectures.get(i));
			}
		}
		return this.selectedLectures; 
	}
	
	public LectureList(ListSelectionHandler listSelectionHandler) {
		// controller
		this.lectureManager = new LectureManager();
		// data model
		Vector<String> header = new Vector<String>();
		header.addElement("과목ID");
		header.addElement("강좌명");
		header.addElement("담당교수");
		header.addElement("학점");
		header.addElement("시간");
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);
		
		for(int i = 0; i < tableModel.getColumnCount(); i++){
			TableColumn column = this.getColumnModel().getColumn(i);
			if(i == 0){
				column.setPreferredWidth(60);
			} else if(i == 1){
				column.setPreferredWidth(160);
			} else if(i == 2){
				column.setPreferredWidth(70);
			} else if(i == 3){
				column.setPreferredWidth(40);
			} else if(i == 4){
				column.setPreferredWidth(200);
			}
		}
		// add listener
	}
	
	public void initialize(){
	}
	
	public String getSelectedFileName() {
		return null;
	}
	public void getLectures(Vector<Lecture> lectures) {
		Vector<String> rowData = null;
		for (Lecture lecture: lectures) {
			rowData = new Vector<String>();
			rowData.addElement(Integer.toString(lecture.getId()));
			rowData.addElement(lecture.getName());
			rowData.addElement(lecture.getProfessorName());
			rowData.addElement(Integer.toString(lecture.getCredit()));
			rowData.addElement(lecture.getTime());
			this.tableModel.addRow(rowData);
		}
		this.updateUI();	
	}

	public void showLectures(String fileName) throws FileNotFoundException {
		this.tableModel.setRowCount(0);
		if (fileName == null) {
			return;
		}
		this.lectures = this.lectureManager.getLectures(fileName);
		Vector<String> rowData = null;
		for (Lecture lecture: lectures) {
			rowData = new Vector<String>();
			rowData.addElement(Integer.toString(lecture.getId()));
			rowData.addElement(lecture.getName());
			rowData.addElement(lecture.getProfessorName());
			rowData.addElement(Integer.toString(lecture.getCredit()));
			rowData.addElement(lecture.getTime());
			this.tableModel.addRow(rowData);
		}
		this.updateUI();	
	}

	public void saveLectures(String fileName) throws FileNotFoundException {
		this.lectureManager.saveLectures(fileName, this.lectures);
	}
}
