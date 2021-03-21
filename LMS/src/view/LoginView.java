package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.StudentControl;
import entity.Student;
import global.Constants;

public class LoginView extends JDialog{

	private static final long serialVersionUID = 1L;

	//controller
	private StudentControl studentControl;
	
	//sub-components
	private JLabel idUserName , idPassword;
	private JTextField tfUserName;
	private JPasswordField tfPassword;
	private JButton btOk,btCancel;
	
	//
	private String userName;
	private String password;
	private Student student;
	private boolean login;

	public LoginView(JFrame frame) throws FileNotFoundException{
		super(frame,Constants.LOGINVIEW_TITLE, true);
		//attributes
		this.studentControl= new StudentControl();
		this.setSize(200, 150);
		this.setLocation(400, 400);
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		
		//add sub-components
		this.idUserName = new JLabel("아이디");
		this.add(this.idUserName);
		
		this.tfUserName = new JTextField(10);
		this.add(this.tfUserName);
		
		this.idPassword = new JLabel("비밀번호");
		this.add(this.idPassword);
		
		this.tfPassword = new JPasswordField(10);
		this.add(this.tfPassword);
		
		ActionHandler actionHandler = new ActionHandler();
		this.btOk= new JButton("확인");
		this.add(btOk);
		this.btOk.setActionCommand("ok");
		this.btOk.addActionListener(actionHandler);
		
		this.btCancel= new JButton("취소");
		this.btCancel.addActionListener(actionHandler);
		this.add(btCancel);
		this.btCancel.setActionCommand("cancel");
		
	}

	
	
	// 로그인 기능의 핵심
	public void login(){
		// 텍스트 박스 읽어오기
		this.userName = this.tfUserName.getText();
		this.password = new String(this.tfPassword.getPassword());
		// 로그인 기능
		this.student =this.studentControl.login(userName, password);
		
		if(student ==null) {
			JOptionPane.showMessageDialog(this, Constants.LOGINVIEW_ERRORMESSAGE, Constants.LOGINVIEW_ERRORTITLE,JOptionPane.ERROR_MESSAGE);
		}else {
			this.setLogin(true);
			this.dispose();	
		}
	}
	
	

	
	private void cancel(){
		this.dispose();
	}
	
	


	class ActionHandler implements ActionListener{

		public void actionPerformed(ActionEvent actionEvent) {
			if(actionEvent.getActionCommand().equals("ok")){
				login();
			}else if(actionEvent.getActionCommand().equals("cancel")){
				cancel();
			}
			
		}		
	}

	public boolean isLogin() {return login;}
	public void setLogin(boolean login) {this.login = login;}
	
	public Student getStudent() {return student;}
	public void setStudent(Student student) {this.student = student;}




	public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;}


}