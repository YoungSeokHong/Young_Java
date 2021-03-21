package menu;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

import frame.GDrawingPanel;
import global.GConstants.EColorMenu;
import global.GConstants.EFont;

public class GColorMenu extends GMenu {
	private static final long serialVersionUID = 1L;

	public GColorMenu(String name) {
		super(name);
		
		for(EColorMenu eMenuItem: EColorMenu.values()) {
			eMenuItem.getMenuItem().setIcon(new ImageIcon(eMenuItem.getImageIcon()));
			eMenuItem.getMenuItem().addActionListener(actionHandler);
			eMenuItem.getMenuItem().setActionCommand(eMenuItem.getCommand());
			eMenuItem.getMenuItem().setToolTipText(eMenuItem.getToolTipText());
			eMenuItem.getMenuItem().setFont(new Font(EFont.eMenuItemFont.getFont(), Font.PLAIN, EFont.eMenuItemFont.getFontSize()));
			this.add(eMenuItem.getMenuItem());
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		super.initialize(drawingPanel);
		
	}
	
	public void lineColor() {
		Color color = JColorChooser.showDialog(this.getDrawingPanel(), "Line Color", this.getForeground());
		this.getDrawingPanel().setLineColor(color);
	}
	
	public void fillColor() {
		Color color = JColorChooser.showDialog(this.getDrawingPanel(), "Fill Color", this.getForeground());
		this.getDrawingPanel().setFillColor(color);
	}
}
