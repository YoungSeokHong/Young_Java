package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.MainView.LogoutHandler;

public class RegistrationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ControlPanel controlPanel;
	private DirectoryPanel directoryPanel;
	
	public RegistrationPanel(LogoutHandler logoutHandler, String userName, int userId, int departmentId) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		ActionHandler actionHandler = new ActionHandler();
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
			JLabel collegeLogo = new JLabel();
			collegeLogo.setIcon(new ImageIcon("img/collegeLogo.png"));
			titlePanel.add(collegeLogo);
			titlePanel.add(Box.createHorizontalStrut(150));
			JLabel collegeLogo1 = new JLabel();
			collegeLogo1.setIcon(new ImageIcon("img/collegeLogo1.png"));
			titlePanel.add(collegeLogo1);
		this.add(titlePanel);
		
		
		this.add(Box.createVerticalStrut(10));
		this.controlPanel = new ControlPanel(actionHandler, logoutHandler, userName, userId, departmentId);
		this.add(this.controlPanel);
		this.add(Box.createVerticalStrut(10));

		this.directoryPanel = new DirectoryPanel();		
		this.add(this.directoryPanel);
	}

	public void initialize() {
		this.controlPanel.initialize();
		this.directoryPanel.initialize();
	}
	
	
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource().equals(controlPanel.getBtLogout())) {
				
			}
		}
	}
}
