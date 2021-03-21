package view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Constants;
import view.MainView.LogoutHandler;
import view.RegistrationPanel.ActionHandler;

public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton btLogout;
	private JLabel lbHello, lbCredit;
	public ControlPanel(ActionHandler actionHandler, LogoutHandler logoutHandler, String userName, int userId, int departmentId) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalStrut(50));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		this.lbHello = new JLabel(Constants.CONTROLPANEL_LB_HELLO1+userName+Constants.CONTROLPANEL_LB_HELLO2);
		this.lbCredit = new JLabel(Constants.CONTROLPANEL_LB_CREDIT1+userId+Constants.CONTROLPANEL_LB_CREDIT2+departmentId);
		this.lbHello.setFont(new Font(Constants.ALL_FONT, Font.BOLD, Constants.CONTROLPANEL_LB_HELLO_TEXTSIZE));
		this.lbCredit.setFont(new Font(Constants.ALL_FONT, Font.PLAIN, Constants.CONTROLPANEL_LB_CREDIT_TEXTSIZE));
		leftPanel.add(this.lbHello);
		leftPanel.add(Box.createVerticalStrut(5));
		leftPanel.add(this.lbCredit);
		this.add(leftPanel);
	
		
		this.btLogout = new JButton(Constants.BTITLE_LOGOUT);
//		this.btLogout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.btLogout.addActionListener(actionHandler);
		this.btLogout.addActionListener(logoutHandler);
		this.add(this.btLogout);
		this.add(Box.createHorizontalStrut(50));
	}

	public void initialize() {
	}
	
	public Object getBtLogout() { return this.btLogout; }
}
