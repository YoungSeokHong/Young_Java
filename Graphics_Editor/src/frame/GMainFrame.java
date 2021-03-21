package frame;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import global.GConstants;
import global.GConstants.EMenu;
import menu.GColorMenu;
import menu.GEditMenu;
import menu.GFileMenu;

public class GMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	private KeyboardHandler keyboardHandler;
	private WindowHandler windowHandler;

	
	public GMainFrame() {
		keyboardHandler = new KeyboardHandler();
		windowHandler = new WindowHandler();
		// attributes
		this.setTitle("Graphics Editor");
		this.setLocation(GConstants.MAINFRAME_X, GConstants.MAINFRAME_Y);
		this.setSize(GConstants.MAINFRAME_W, GConstants.MAINFRAME_H);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(keyboardHandler);
		this.addWindowListener(windowHandler);

		this.setLayout(new BorderLayout());
		// components		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);

		this.toolBar = new GToolBar(keyboardHandler);
		this.add(this.toolBar, BorderLayout.NORTH);

		this.drawingPanel = new GDrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		// initialize
		this.drawingPanel.initialize();
		this.menuBar.initialize(this.drawingPanel);
		this.toolBar.initialize(this.drawingPanel);
	}
	
	

	public class KeyboardHandler implements KeyListener {
		int firstClicked;

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_CONTROL){
				firstClicked = KeyEvent.VK_CONTROL;
			} else if(e.getKeyCode() == KeyEvent.VK_ALT){
				firstClicked = KeyEvent.VK_ALT;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(firstClicked == KeyEvent.VK_CONTROL){
				if(e.getKeyCode() == KeyEvent.VK_G){
					System.out.println("group");
					((GEditMenu)EMenu.eEditMenu.getMenu()).group();
				}else if(e.getKeyCode() == KeyEvent.VK_U){
					System.out.println("ungroup");
					((GEditMenu)EMenu.eEditMenu.getMenu()).ungroup();
				}else if(e.getKeyCode() == KeyEvent.VK_X){
					System.out.println("cut");
					((GEditMenu)EMenu.eEditMenu.getMenu()).cut();
				}else if(e.getKeyCode() == KeyEvent.VK_C){
					System.out.println("copy");
					((GEditMenu)EMenu.eEditMenu.getMenu()).copy();
				}else if(e.getKeyCode() == KeyEvent.VK_V){
					System.out.println("paste");
					((GEditMenu)EMenu.eEditMenu.getMenu()).paste();
				}else if(e.getKeyCode() == KeyEvent.VK_D){
					System.out.println("delete");
					((GEditMenu)EMenu.eEditMenu.getMenu()).delete();
				}else if(e.getKeyCode() == KeyEvent.VK_Y){
					System.out.println("do");
					((GEditMenu)EMenu.eEditMenu.getMenu()).ddo();
				}else if(e.getKeyCode() == KeyEvent.VK_Z){
					System.out.println("undo");
					((GEditMenu)EMenu.eEditMenu.getMenu()).undo();
				}else if(e.getKeyCode() == KeyEvent.VK_F){
					System.out.println("setFillColor");
					((GColorMenu)EMenu.eColorMenu.getMenu()).fillColor();
				}else if(e.getKeyCode() == KeyEvent.VK_L){
					System.out.println("setLineColor");
					((GColorMenu)EMenu.eColorMenu.getMenu()).lineColor();
				}
			} else if(firstClicked == KeyEvent.VK_ALT){
				if(e.getKeyCode() == KeyEvent.VK_N){
					System.out.println("new");
					((GFileMenu)EMenu.eFileMenu.getMenu()).nnew();
				}else if(e.getKeyCode() == KeyEvent.VK_O){
					System.out.println("open");
					((GFileMenu)EMenu.eFileMenu.getMenu()).open();
				}else if(e.getKeyCode() == KeyEvent.VK_C){
					System.out.println("close");
					((GFileMenu)EMenu.eFileMenu.getMenu()).close();
				}else if(e.getKeyCode() == KeyEvent.VK_S){
					System.out.println("save");
					((GFileMenu)EMenu.eFileMenu.getMenu()).save();
				}else if(e.getKeyCode() == KeyEvent.VK_A){
					System.out.println("saveAs");
					((GFileMenu)EMenu.eFileMenu.getMenu()).saveAs();
				}else if(e.getKeyCode() == KeyEvent.VK_P){
					System.out.println("print");
					((GFileMenu)EMenu.eFileMenu.getMenu()).print();
				}else if(e.getKeyCode() == KeyEvent.VK_X){
					System.out.println("exit");
					((GFileMenu)EMenu.eFileMenu.getMenu()).exit();
				}
			}

		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

	}
	
	private class WindowHandler implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			((GFileMenu)EMenu.eFileMenu.getMenu()).exit();
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
