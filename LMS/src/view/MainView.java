package view;

import java.awt.Color;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import global.Constants;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;

	private RegistrationPanel registrationPanel;
	private HomePanel homePanel;
	private String userName;
	
	LoginView loginView;
	private boolean login;
	private Student loginStudent;
	
	public MainView() {
		// set attributes
		this.setLocation(Constants.MAINVIEW_X, Constants.MAINVIEW_Y);
		this.setSize(Constants.MAINVIEW_W, Constants.MAINVIEW_H);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// initiate Listener
		LoginHandler loginHandler = new LoginHandler();
		
		
		// home panel
		this.homePanel = new HomePanel(loginHandler);
		this.add(this.homePanel);
		
		// registration panel
	
		
//		this.registrationPanel.setVisible(false);
		
		
		
		
		
	}
	
	public void setLoginStudent(Student loginStudent) {this.loginStudent = loginStudent;}
	public void setLogin(boolean login) {this.login = login;}
	
	private void login() {
		LogoutHandler logoutHandler = new LogoutHandler();
		
		try {			
			loginView= new LoginView(this);			
			loginView.setVisible(true);
			this.setLogin(this.loginView.isLogin());
			System.out.println("lk");
			
			if (this.login){
				userName = loginView.getUserName();
				this.setLoginStudent(this.loginView.getStudent());
				
				this.homePanel.setVisible(false);
				
				this.registrationPanel = new RegistrationPanel(logoutHandler, userName, loginStudent.getId(), loginStudent.getDepartmentId());
				this.add(this.registrationPanel);
				this.initialize();
				
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void logout() {
		int choice = JOptionPane.showConfirmDialog(this, "정말 로그아웃 하시겠습니까?", "logout", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if(choice == 0){
			this.registrationPanel.setVisible(false);
			this.homePanel.setVisible(true);
		}
	}	
	
	public void initialize() {
		this.registrationPanel.initialize();
	}
	
	public class LoginHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource().equals(homePanel.getBtLogout())){
				login();
			}
		}
		
	}
	
	public class LogoutHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource().equals(registrationPanel.controlPanel.getBtLogout())){
				logout();
			} 
			
		}
		
	}
}
