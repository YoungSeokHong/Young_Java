package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
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

public class BasketView extends JPanel {
	private static final long serialVersionUID = 1L;
	// sub-components
	public JButton btMoveToRight, btRemoveBasket;
	private JButton btMoveToLeft, btRemoveSincheong;
	BasketList basketList;
	BasketList sincheongList;
	public JButton btSave;
	
	Vector<Lecture> baskets;
	private Vector<Lecture> sincheongs;
	
	public BasketView(JPanel parentPanel) {
		// initiate Listener
		BasketHandler basketHandler = new BasketHandler();
		MouseHandler mouseHandler = new MouseHandler();
		
		// attributes
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalStrut(10));
		
		// left panel
		JPanel leftPanel = new JPanel();
			leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
				leftPanel.add(Box.createVerticalStrut(20));
				JLabel lbBasket = new JLabel("책가방 목록");
				lbBasket.setAlignmentX(Component.CENTER_ALIGNMENT);
				leftPanel.add(lbBasket);
				leftPanel.add(Box.createVerticalStrut(5));
				
				this.basketList = new BasketList(null);
				JScrollPane basketListScrollPane = new JScrollPane();
				basketListScrollPane.setViewportView(basketList);
				this.basketList.addMouseListener(mouseHandler);
				leftPanel.add(basketListScrollPane);
				leftPanel.add(Box.createVerticalStrut(10));
				this.btRemoveBasket = new JButton("삭제");
				this.btRemoveBasket.addActionListener(basketHandler);
				this.btRemoveBasket.setEnabled(false);
				this.btRemoveBasket.setAlignmentX(Component.CENTER_ALIGNMENT);
				leftPanel.add(btRemoveBasket);				
				leftPanel.add(Box.createVerticalStrut(10));
				this.add(leftPanel);
			
				this.add(Box.createHorizontalStrut(10));
				
		// center panel
		JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				this.btMoveToRight = new JButton(">>");
				this.btMoveToRight.addActionListener(basketHandler);
				this.btMoveToRight.setEnabled(false);
				centerPanel.add(btMoveToRight);
				this.btMoveToLeft = new JButton("<<");
				this.btMoveToLeft.addActionListener(basketHandler);
				this.btMoveToLeft.setEnabled(false);
				centerPanel.add(btMoveToLeft);
				
				this.add(centerPanel);
				this.add(Box.createHorizontalStrut(10));
	
		// right panel		
		JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
			
				JPanel upperRightPanel = new JPanel();
				upperRightPanel.setLayout(new BoxLayout(upperRightPanel, BoxLayout.Y_AXIS));
				JPanel lowerRightPanel = new JPanel();
				lowerRightPanel.setLayout(new BoxLayout(lowerRightPanel, BoxLayout.X_AXIS));
					upperRightPanel.add(Box.createVerticalStrut(20));
					JLabel lbSincheong = new JLabel("수강신청 목록");
					lbSincheong.setAlignmentX(Component.CENTER_ALIGNMENT);
					upperRightPanel.add(lbSincheong);
					upperRightPanel.add(Box.createVerticalStrut(5));
					this.sincheongList = new BasketList(null);
					JScrollPane sincheongListScrollPane = new JScrollPane();
					sincheongListScrollPane.setViewportView(sincheongList);
					this.sincheongList.addMouseListener(mouseHandler);
					upperRightPanel.add(sincheongListScrollPane);
					upperRightPanel.add(Box.createVerticalStrut(10));
					
					this.btRemoveSincheong = new JButton("삭제");
					this.btRemoveSincheong.addActionListener(basketHandler);
					this.btRemoveSincheong.setEnabled(false);
					
					lowerRightPanel.add(btRemoveSincheong);	
					lowerRightPanel.add(Box.createHorizontalStrut(50));
					this.btSave = new JButton("저장");
					this.btSave.addActionListener(basketHandler);
					this.btSave.setEnabled(false);
					
					lowerRightPanel.add(btSave);	
					rightPanel.add(upperRightPanel);
					rightPanel.add(lowerRightPanel);
					rightPanel.add(Box.createVerticalStrut(10));
					this.add(rightPanel);
					this.add(Box.createHorizontalStrut(10));
		// add sub-components
					
					this.baskets = new Vector<Lecture>();
					this.sincheongs = new Vector<Lecture>();
	}

	public void openBasket(Vector<Lecture> selectedLectures2) {
		this.basketList.openBasket(selectedLectures2);
		
		for(Lecture lecture: selectedLectures2){
			baskets.addElement(lecture);
		}
		
	}
	
	private void moveToRight() {
		for(int i = 0; i < basketList.getRowCount(); i++){
			if(basketList.isRowSelected(i)){
				sincheongs.addElement(baskets.get(i));
			}
		}
	}

	private void deleteBasket() {
		int[] indices = basketList.getSelectedRows();
		int index = 0;
		for (int i= indices.length-1; i>=0; i--) {
			index = indices[i];
			baskets.remove(index);
		}
		if(baskets.size() == 0){
			this.btRemoveBasket.setEnabled(false);
			this.btMoveToRight.setEnabled(false);
		}
	}
	
	private void moveToLeft() {
		for(int i = 0; i < sincheongList.getRowCount(); i++){
			if(sincheongList.isRowSelected(i)){
				baskets.addElement(sincheongs.get(i));
			}
		}
	}

	private void deleteSincheong() {
		int[] indices = sincheongList.getSelectedRows();
		int index = 0;
		for (int i= indices.length-1; i>=0; i--) {
			index = indices[i];
			sincheongs.remove(index);
		}
		if(sincheongs.size() == 0){
			this.btMoveToLeft.setEnabled(false);
			this.btRemoveSincheong.setEnabled(false);
			this.btSave.setEnabled(false);
		}
	}
	
	public void save(){
		try {
			File fileOutput = new File("data/output.txt");
			PrintStream printStream = new PrintStream(fileOutput);
			System.setOut(printStream);
			for(int i = 0; i < sincheongList.getRowCount(); i++){
					System.out.println(sincheongs.get(i).getId()+" "+sincheongs.get(i).getName()+" "+sincheongs.get(i).getProfessorName()+" "+sincheongs.get(i).getCredit()+" "+sincheongs.get(i).getTime());
			}
			printStream.close();
		} catch (FileNotFoundException e) {
		}
	}
	
	private void selectSincheongList() {
		this.basketList.clearSelection();
		this.btMoveToLeft.setEnabled(true);
		this.btRemoveSincheong.setEnabled(true);
		this.btSave.setEnabled(true);
	}
	private void selectBasketList() {
		this.sincheongList.clearSelection();
		this.btMoveToRight.setEnabled(true);
		this.btRemoveBasket.setEnabled(true);
	}
	
	public JButton getBtMoveToRight() { return btMoveToRight; }
	public void setBtMoveToRight(JButton btMoveToRight) { this.btMoveToRight = btMoveToRight; }
	public JButton getBtMoveToLeft() { return btMoveToLeft; }
	public JButton getBtRemoveBasket() { return btRemoveBasket; }
	public void setBtRemoveBasket(JButton btRemoveBasket) { this.btRemoveBasket = btRemoveBasket; }
	public JButton getBtRemoveSincheong() { return btRemoveSincheong; }
	public JButton getBtSave() { return btSave; }


	public class BasketHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource().equals(getBtMoveToRight())){
				moveToRight();
				deleteBasket();
				basketList.updateList(baskets);
				sincheongList.updateList(sincheongs);
				btMoveToLeft.setEnabled(true);
				btRemoveSincheong.setEnabled(true);
				btSave.setEnabled(true);
			} else if(event.getSource().equals(getBtMoveToLeft())){
				moveToLeft();
				deleteSincheong();
				basketList.updateList(baskets);
				sincheongList.updateList(sincheongs);
				btMoveToRight.setEnabled(true);
				btRemoveBasket.setEnabled(true);
			} else if(event.getSource().equals(getBtRemoveBasket())){
				deleteBasket();
				basketList.updateList(baskets);
			} else if(event.getSource().equals(getBtRemoveSincheong())){
				deleteSincheong();
				sincheongList.updateList(sincheongs);
			} else if(event.getSource().equals(getBtSave())){
				save();
			}
		}
	}
	
	private class MouseHandler implements MouseListener {
		public void mouseClicked(MouseEvent event) {
			if (event.getClickCount()==1) {
				if (event.getSource().equals(basketList)) {
					selectBasketList();
				} else if (event.getSource().equals(sincheongList)) {
					selectSincheongList();
				}
			} 
			else if (event.getClickCount()==2) {
				if (event.getSource().equals(basketList)) {
					moveToRight();
					deleteBasket();
					basketList.updateList(baskets);
					sincheongList.updateList(sincheongs);
					btMoveToLeft.setEnabled(true);
					btRemoveSincheong.setEnabled(true);
					btSave.setEnabled(true);
				} else if (event.getSource().equals(sincheongList)) {
					moveToLeft();
					deleteSincheong();
					basketList.updateList(baskets);
					sincheongList.updateList(sincheongs);
					btMoveToRight.setEnabled(true);
					btRemoveBasket.setEnabled(true);
				}					
			}		
		}
		
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
	}
}
