package view;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import entity.Lecture;

public class BasketList extends JTable{
	private static final long serialVersionUID = 1L;
	
	private Vector<String> userColumn = new Vector<String>();
	public DefaultTableModel model;
	public Vector<String> userRow;
	private Vector<String> selectedLecture;
	
	
	public BasketList(Vector<Lecture> lectures){
		userColumn.addElement("강좌ID");
		userColumn.addElement("강좌명");
		userColumn.addElement("교수");
		userColumn.addElement("학점");
		userColumn.addElement("시간");
	    
		model = new DefaultTableModel(userColumn,0){ public boolean isCellEditable(int i, int c){ return false; } };

		
		this.setModel(model);
		
		for(int i = 0; i < model.getColumnCount(); i++){
			TableColumn column = this.getColumnModel().getColumn(i);
			if(i == 0){
				column.setPreferredWidth(60);
			} else if(i == 1){
				column.setPreferredWidth(130);
			} else if(i == 2){
				column.setPreferredWidth(70);
			} else if(i == 3){
				column.setPreferredWidth(40);
			} else if(i == 4){
				column.setPreferredWidth(230);
			}
		}
	}
	
	public void openBasket(Vector<Lecture> lectures) {
		
		for(Lecture lecture: lectures){
			selectedLecture = new Vector<String>();
			selectedLecture.addElement(Integer.toString(lecture.getId()));
			selectedLecture.addElement(lecture.getName());
			selectedLecture.addElement(lecture.getProfessorName());
			selectedLecture.addElement(Integer.toString(lecture.getCredit()));
			selectedLecture.addElement(lecture.getTime());
			this.model.addRow(selectedLecture);
		}
		this.updateUI();
		
//		this.baskets = new Vector<Lecture>();
//		for(Lecture lecture: lectures){
//			baskets.addElement(lecture);
//		}
//		sincheongs = new Vector<Lecture>();
	}

	public void saveLectures(String basketFileName, String sincheongFileName) {
		
		// TODO Auto-generated method stub
		
	}

	public void updateList(Vector<Lecture> updateLectures) {
		this.model.setRowCount(0);
		
		for(Lecture lecture: updateLectures){
			selectedLecture = new Vector<String>();
			this.selectedLecture.addElement(Integer.toString(lecture.getId()));
			this.selectedLecture.addElement(lecture.getName());
			this.selectedLecture.addElement(lecture.getProfessorName());
			this.selectedLecture.addElement(Integer.toString(lecture.getCredit()));
			this.selectedLecture.addElement(lecture.getTime());
			this.model.addRow(selectedLecture);
		}
		this.updateUI();
		
		
	}
}

