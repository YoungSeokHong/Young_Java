package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import frame.GMainFrame.KeyboardHandler;
import global.GConstants.EToolBar;

public class GToolBar extends JToolBar {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	// associations
	private GDrawingPanel drawingPanel;
	
	public GToolBar(KeyboardHandler keyboardHandler) {

		// Group the radio buttons.
		ActionHandler actionHandler = new ActionHandler();
		ButtonGroup group = new ButtonGroup();
		for (EToolBar button : EToolBar.values()) {
			System.out.println(button);
			JRadioButton newButton = new JRadioButton();
			newButton.setToolTipText(button.getToolTipText());
			newButton.setIcon(new ImageIcon(button.getIcon()));
			newButton.setSelectedIcon(new ImageIcon(button.getSelectedIcon()));
			newButton.addKeyListener(keyboardHandler);
			group.add(newButton);
			this.add(newButton);
			newButton.addActionListener(actionHandler);
			newButton.setActionCommand(button.name());
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		((JRadioButton)(this.getComponent(EToolBar.eRectangleButton.ordinal()))).doClick();
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawingPanel.setActionCommand(EToolBar.valueOf(e.getActionCommand()).getSelectedTool());
		}

	}
}
