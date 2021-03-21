package menu;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import frame.GDrawingPanel;
import global.GConstants.EFileMenu;
import global.GConstants.EFont;
// open file name missing
// write file name duplication
// EXIT_ON_CLOSE
// Wrong File read
// update flag reset (isUpdate())
public class GFileMenu extends GMenu {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private File dir;
	private File file;

	public GFileMenu(String name) {
		super(name);

		for(EFileMenu eMenuItem: EFileMenu.values()) {

			eMenuItem.getMenuItem().setIcon(new ImageIcon(eMenuItem.getImageIcon()));
			eMenuItem.getMenuItem().addActionListener(actionHandler);
			eMenuItem.getMenuItem().setActionCommand(eMenuItem.getCommand());
			eMenuItem.getMenuItem().setToolTipText(eMenuItem.getToolTipText());
			eMenuItem.getMenuItem().setFont(new Font(EFont.eMenuItemFont.getFont(), Font.PLAIN, EFont.eMenuItemFont.getFontSize()));
			this.add(eMenuItem.getMenuItem());
			if(eMenuItem.getCommand().equals("open")||eMenuItem.getCommand().equals("saveAs")||eMenuItem.getCommand().equals("close")){
				this.addSeparator();
			}
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		super.initialize(drawingPanel);
		
		this.dir = null;
		this.file = null;
	}


	private void readObject() {
		try {
			ObjectInputStream in;
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.file)));
			Object shapeVector = in.readObject();
			this.getDrawingPanel().setShapeVector(shapeVector);
			in.close();
		} catch (IOException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	private void writeObject() {
		try {
			ObjectOutputStream out;
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			out.writeObject(this.getDrawingPanel().getShapeVector());
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkSave() {
		int reply = JOptionPane.NO_OPTION;
		boolean cancel = false;
		if (this.getDrawingPanel().isUpdated() == true) {
			reply = JOptionPane.showConfirmDialog(this.getDrawingPanel(), "변경내용을 저장하시겠습니까?");
			if (reply == JOptionPane.CANCEL_OPTION) {
				cancel = true;
			}
		}
		if (!cancel) {
			if (reply == JOptionPane.OK_OPTION) {
				this.save();
			}
		}
		return cancel;
	}

	public void open() {
		boolean cancel = this.checkSave();
		if(!cancel) {
			this.getDrawingPanel().setShapeVector(null);			
		}
		JFileChooser chooser = new JFileChooser(this.dir);
		chooser.setSelectedFile(file);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "gvs");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.getDrawingPanel());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.dir = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			this.readObject();
		}

	}

	public void nnew() {
		boolean cancel = this.checkSave();
		if(!cancel) {
			this.getDrawingPanel().setShapeVector(null);		
			this.getDrawingPanel().getSelectedVector().clear();
		}
	}

	public void save() {
		if(this.file == null) {
			this.saveAs();
		}
		this.writeObject();
	}
	
	public void saveAs() {
		JFileChooser chooser = new JFileChooser(this.dir);
		chooser.setSelectedFile(file);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "gvs");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(this.getDrawingPanel());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.dir = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			this.writeObject();
		}
	}
	public void print() {
		try {
			ObjectOutputStream out;
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/print.txt")));
			out.writeObject(this.getDrawingPanel().getShapeVector());
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		boolean cancel = this.checkSave();
		if(!cancel) {
			this.getDrawingPanel().setShapeVector(null);
			this.getDrawingPanel().getSelectedVector().clear();
		}
	}

	public void exit() {
		boolean cancel = this.checkSave();
		if(!cancel) {
			System.exit(0);	
		}
	}
}
