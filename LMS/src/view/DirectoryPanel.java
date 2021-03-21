package view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entity.Lecture;
import global.Constants;

public class DirectoryPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ListSelectionHandler listSelectionHandler;
	private ActionHandler actionHandler;
	
	private DirectoryList campusList;
	private DirectoryList collegeList;
	private DirectoryList departmentList;
	private LectureList lectureList;
	private BasketView basketView;
	private JButton btSincheong;

	private Vector<Lecture> selectedLectures;
	
	public LectureList getLectureList() {
		return this.lectureList;
	}

	public DirectoryPanel() {
		// listeners
		this.listSelectionHandler = new ListSelectionHandler();
		this.actionHandler = new ActionHandler();
		
		// layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// components
		JPanel upperTitlePanel1 = new JPanel();
		JPanel upperTitlePanel = new JPanel();
		JPanel upperPanel1 = new JPanel();
		JPanel upperPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		
		this.add(upperTitlePanel1);
		this.add(upperPanel1);
		this.add(Box.createVerticalStrut(10));
		this.add(centerPanel);
		this.add(Box.createVerticalStrut(10));
		this.add(lowerPanel);
		
		upperTitlePanel1.setLayout(new BoxLayout(upperTitlePanel1, BoxLayout.X_AXIS));
		upperTitlePanel.setLayout(new GridLayout(1,3));
		upperPanel1.setLayout(new BoxLayout(upperPanel1, BoxLayout.X_AXIS));
		upperPanel.setLayout(new GridLayout(1,3));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = null;

		// upper title panel
		upperTitlePanel1.add(Box.createHorizontalStrut(5));
			JLabel lbCampus = new JLabel("캠퍼스");
			upperTitlePanel.add(lbCampus);
			JLabel lbCollege = new JLabel("대학");
			upperTitlePanel.add(lbCollege);
			JLabel lbDepartment = new JLabel("학과");
			upperTitlePanel.add(lbDepartment);
		upperTitlePanel1.add(upperTitlePanel);
		
		// upper panel
		upperPanel1.add(Box.createHorizontalStrut(5));
			this.campusList = new DirectoryList(this.listSelectionHandler);
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(this.campusList);
			upperPanel.add(scrollPane);

			this.collegeList = new DirectoryList(this.listSelectionHandler);
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(this.collegeList);
			upperPanel.add(scrollPane);
		
			this.departmentList = new DirectoryList(this.listSelectionHandler);
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(this.departmentList);
			upperPanel.add(scrollPane);
		upperPanel1.add(upperPanel);
		upperPanel1.add(Box.createHorizontalStrut(5));
		
	
		// center panel
		centerPanel.add(Box.createHorizontalStrut(5));
		this.lectureList = new LectureList(this.listSelectionHandler);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.lectureList);
		centerPanel.add(scrollPane);
		centerPanel.add(Box.createHorizontalStrut(5));
		
		// lower panel
		this.btSincheong = new JButton("신청");
		this.btSincheong.addActionListener(actionHandler);

		lowerPanel.add(Box.createHorizontalStrut(500));
		lowerPanel.add(btSincheong);

		// basket view
		this.basketView = new BasketView(this);
		this.add(basketView);
		
		
	}
	public void initialize() {
		this.showDirectories(null);
	}
	private void showDirectories(Object source) {
		try {
			String fileName = null;
			if (source == null) {
				this.campusList.showDirectories(Constants.FILENAME_ROOT);
			} else if (source.equals(this.campusList)) {
				fileName = this.campusList.getSelectedFileName();
				this.collegeList.showDirectories(fileName);
				fileName = this.collegeList.getSelectedFileName();
				this.departmentList.showDirectories(fileName);
				fileName = this.departmentList.getSelectedFileName();
				this.lectureList.showLectures(fileName);
			} else if (source.equals(this.collegeList)) {
				fileName = this.collegeList.getSelectedFileName();
				this.departmentList.showDirectories(fileName);			
				fileName = this.departmentList.getSelectedFileName();
				this.lectureList.showLectures(fileName);
			} else if (source.equals(this.departmentList)) {
				fileName = this.departmentList.getSelectedFileName();				
				fileName = this.departmentList.getSelectedFileName();
				this.lectureList.showLectures(fileName);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}		
	
	public Object getBtSincheong() { return this.btSincheong; }
	public Object getBtSave() { return basketView.btSave; }
	
//	public void showRegistrations() {
//		Vector<Lecture> selectedLectures = this.lectureList.getSelectedLectures();
//		basketView.addLectures(selectedLectures);
//		basketView.setVisible(true);
//	}
	
	public void getSelectedLecture() {
		this.selectedLectures = new Vector<Lecture>();
		System.out.println(lectureList.getSelectedLectures().size());
		
		for(int i = 0;i < lectureList.getSelectedLectures().size();i++){
			this.selectedLectures.addElement(lectureList.getSelectedLectures().get(i));
			System.out.println("done");
		}

	}
	
	private void openBasket(Vector<Lecture> selectedLectures2) {
		this.basketView.openBasket(selectedLectures2);
		
	}
	
	public class ListSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			showDirectories(event.getSource());
		}
	}
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource().equals(getBtSincheong())) {
				getSelectedLecture();
				openBasket(selectedLectures);
				basketView.btMoveToRight.setEnabled(true);
				basketView.btRemoveBasket.setEnabled(true);
			}
		}

		
		
	}
//	public void saveRegistrations() {
//		try {
//			this.basketView.saveRegistrations("basket", "sincheong");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}
}
