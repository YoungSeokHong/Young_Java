package menu;

import java.awt.Font;

import javax.swing.ImageIcon;

import frame.GDrawingPanel;
import global.GConstants.EEditMenu;
import global.GConstants.EFont;

public class GEditMenu extends GMenu {
	private static final long serialVersionUID = 1L;

	public GEditMenu(String name) {
		super(name);

		for(EEditMenu eMenuItem: EEditMenu.values()) {
			eMenuItem.getMenuItem().setIcon(new ImageIcon(eMenuItem.getImageIcon()));
			eMenuItem.getMenuItem().addActionListener(actionHandler);
			eMenuItem.getMenuItem().setActionCommand(eMenuItem.getCommand());
			eMenuItem.getMenuItem().setToolTipText(eMenuItem.getToolTipText());
			eMenuItem.getMenuItem().setFont(new Font(EFont.eMenuItemFont.getFont(), Font.PLAIN, EFont.eMenuItemFont.getFontSize()));
			this.add(eMenuItem.getMenuItem());
			if(eMenuItem.getCommand().equals("ungroup")||eMenuItem.getCommand().equals("delete")){
				this.addSeparator();
			}
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		super.initialize(drawingPanel);

	}
	
	public void group() {
//		this.getDrawingPanel().group(new GGroup());
		
	}
	public void ungroup() {
//		this.getDrawingPanel().unGroup();
	}
	public void cut() {
		this.getDrawingPanel().cut();
	}
	public void copy() {
		this.getDrawingPanel().copy();
	}
	public void paste() {
		this.getDrawingPanel().paste();
	}
	public void delete() {
		this.getDrawingPanel().delete();
	}
	public void ddo() {
		this.getDrawingPanel().ddo();
	}
	public void undo() {
		this.getDrawingPanel().undo();
	}
}
