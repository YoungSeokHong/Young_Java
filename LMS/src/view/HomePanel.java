package view;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Constants;
import view.MainView.LoginHandler;

public class HomePanel extends JPanel {
	public JButton btLogin;
	
	public HomePanel(LoginHandler loginHandler) {
		// set attributes
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// title Panel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
			JLabel collegeLogo = new JLabel();
			collegeLogo.setIcon(new ImageIcon("img/collegeLogo.png"));
			titlePanel.add(collegeLogo);
			titlePanel.add(Box.createHorizontalStrut(500));
		this.add(titlePanel);
		
		// upper Panel
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
			JPanel homeLabel = new JPanel();
			homeLabel.setLayout(new BoxLayout(homeLabel, BoxLayout.Y_AXIS));
				JLabel lbHome01 = new JLabel("안녕하세요! 명지대학교 수강신청");
				JLabel lbHome02 = new JLabel("프로그램입니다.");
				lbHome01.setFont(new Font("굴림", Font.BOLD, Constants.CONTROLPANEL_LB_HELLO_TEXTSIZE));
				lbHome02.setFont(new Font("굴림", Font.BOLD, Constants.CONTROLPANEL_LB_HELLO_TEXTSIZE));
				homeLabel.add(lbHome01);
				homeLabel.add(lbHome02);
			upperPanel.add(homeLabel);
			upperPanel.add(Box.createHorizontalStrut(400));
			this.btLogin = new JButton("로그인");
			this.btLogin.addActionListener(loginHandler);
			upperPanel.add(btLogin);
		this.add(upperPanel);
		
		// center panel
		JPanel centerPanel = new JPanel();
			JLabel homeImg = new JLabel();
			homeImg.setIcon(new ImageIcon("img/homeImage.png"));
			centerPanel.add(homeImg);
		this.add(centerPanel);
		
		// lower panel
		JPanel lowerPanel = new JPanel();
		this.add(lowerPanel);
	}

	public Object getBtLogout() { return btLogin; }
}
