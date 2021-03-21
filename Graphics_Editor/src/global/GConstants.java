package global;

import java.awt.Cursor;

import menu.GColorMenu;
import menu.GEditMenu;
import menu.GFileMenu;
import menu.GMenu;
import menu.GMenuItem;
import shape.GRectangle;
import shape.GRoundRectangle;
import shape.GSelect;
import shape.GShape;
import shape.GEllipse;
import shape.GLine;
import shape.GPolygon;

public class GConstants {
	// GMainFrame
	public final static int MAINFRAME_X = 200;
	public final static int MAINFRAME_Y = 100;
	public final static int MAINFRAME_W = 900;
	public final static int MAINFRAME_H = 800;
	// GMenuBar
	
	public enum EMenu {
		eFileMenu(new GFileMenu("File") ),
		eEditMenu(new GEditMenu("Edit")),
		eColorMenu(new GColorMenu("Color"));
		
		private GMenu menu;
		
		
		EMenu(GMenu menu){
			this.menu = menu;
		}
		public GMenu getMenu() {
			return this.menu;
		}
	}
	public enum EFont {
		eMenuFont(17, "Arial Nova"),
		eMenuItemFont(15, "Arial Nova");
		
		private int fontSize;
		private String font;
		
		EFont(int fontSize, String font){
			this.fontSize = fontSize;
			this.font = font;
		}
		
		public int getFontSize() {
			return this.fontSize;
		}
		public String getFont() {
			return this.font;
		}
	}
	
	public enum EFileMenu {
		
		eNewItem(new GMenuItem("new"), "nnew", "alt + n", "resource/new.png"),
		eOpenItem(new GMenuItem("open"), "open", "alt + o", "resource/open.png"),
		eCloseItem(new GMenuItem("close"), "close", "alt + c", "resource/close.png"),
		eSaveItem(new GMenuItem("save"), "save", "alt + s", "resource/save.png"),
		eSaveAsItem(new GMenuItem("saveAs"), "saveAs", "alt + a", "resource/saveAs.png"),
		ePrintItem(new GMenuItem("print"), "print", "alt + p", "resource/print.png"),
		eExitItem(new GMenuItem("exit"), "exit", "alt + x", "resource/exit.png");

		private GMenuItem menuItem;
		private String command;
		private String toolTipText;
		private String imageIcon;
		
		EFileMenu(GMenuItem menuItem, String command, String toolTipText, String imageIcon) {
			this.menuItem = menuItem;
			this.command = command;
			this.toolTipText = toolTipText;
			this.imageIcon = imageIcon;
		}
		
		public GMenuItem getMenuItem() {
			return this.menuItem;
		}
		public String getCommand() {
			return this.command;
		}
		
		public String getToolTipText(){
			return this.toolTipText;
		}
		public String getImageIcon(){
			return this.imageIcon;
		}
	}
	public enum EEditMenu {
		eGroupItem(new GMenuItem("group"), "group", "ctrl + g", "resource/group.png"),
		eUnGrouptItem(new GMenuItem("ungroup"), "ungroup", "ctrl + u", "resource/ungroup.png"),
		eCutItem(new GMenuItem("cut"), "cut", "ctrl + t", "resource/cut.png"),
		eCopyItem(new GMenuItem("copy"), "copy", "ctrl + c", "resource/copy.png"),
		ePasteItem(new GMenuItem("paste"), "paste", "ctrl + v", "resource/paste.png"),
		eDeleteItem(new GMenuItem("delete"), "delete", "ctrl + d", "resource/delete.png"),
		eDoItem(new GMenuItem("do"), "ddo", "ctrl + y", "resource/do.png"),
		eUndoItem(new GMenuItem("undo"), "undo", "ctrl + z", "resource/undo.png");
		
		private GMenuItem menuItem;
		private String command;
		private String toolTipText;
		private String imageIcon;
		
		EEditMenu(GMenuItem menuItem, String command, String toolTipText, String imageIcon) {
			this.menuItem = menuItem;
			this.command = command;
			this.toolTipText = toolTipText;
			this.imageIcon = imageIcon;
		}
		
		public GMenuItem getMenuItem() {
			return this.menuItem;
		}
		public String getCommand() {
			return this.command;
		}
		
		public String getToolTipText(){
			return this.toolTipText;
		}
		public String getImageIcon(){
			return this.imageIcon;
		}
	}
	public enum EColorMenu {
		eLineColorItem(new GMenuItem("line color"), "lineColor", "ctrl + l", "resource/lineColor.png"),
		eFillColorItem(new GMenuItem("fill color"), "fillColor", "ctrl + f", "resource/fillColor.png");
		
		private GMenuItem menuItem;
		private String command;
		private String toolTipText;
		private String imageIcon;
		
		EColorMenu(GMenuItem menuItem, String command, String toolTipText, String imageIcon) {
			this.menuItem = menuItem;
			this.command = command;
			this.toolTipText = toolTipText;
			this.imageIcon = imageIcon;
		}
		
		public GMenuItem getMenuItem() {
			return this.menuItem;
		}
		public String getCommand() {
			return this.command;
		}
		
		public String getToolTipText(){
			return this.toolTipText;
		}
		public String getImageIcon(){
			return this.imageIcon;
		}
	}
	
	public enum EToolBar {
		eSelectButton("resource/select.gif", "resource/selectSLT.gif", new GSelect() , "select"),
		eRectangleButton("resource/rectangle.gif", "resource/rectangleSLT.gif", new GRectangle(), "rectangle"),
		eEllipseButton("resource/ellipse.gif", "resource/ellipseSLT.gif", new GEllipse(), "ellipse"),
		eLineButton("resource/line.gif", "resource/lineSLT.gif", new GLine(), "line"),
		ePolygonButton("resource/polygon.gif", "resource/polygonSLT.gif", new GPolygon(), "polygon"),
		eRoundRectangleButton("resource/roundRectangle.gif", "resource/roundRectangleSLT.gif", new GRoundRectangle(), "round rectangle");
		
		private String iconFileName;
		private String selectedIconFileName;
		private GShape selectedTool;
		private String toolTipText;
		
		EToolBar(String icon, String selectedIcon, GShape selectedTool, String toolTipText){
			this.iconFileName = icon;
			this.selectedIconFileName = selectedIcon;
			this.selectedTool = selectedTool;
			this.toolTipText = toolTipText;
		}
		public String getIcon() {
			return iconFileName;
		}
		public String getSelectedIcon() {
			return selectedIconFileName;
		}
		public GShape getSelectedTool() {
			return this.selectedTool;
		}
		public String getToolTipText() {
			return this.toolTipText;
		}
	}
	public enum EAnchors { 
		N(Cursor.N_RESIZE_CURSOR),
		S(Cursor.S_RESIZE_CURSOR), 
		E(Cursor.E_RESIZE_CURSOR), 
		W(Cursor.W_RESIZE_CURSOR), 
		NE(Cursor.NE_RESIZE_CURSOR), 
		NW(Cursor.NW_RESIZE_CURSOR), 
		SE(Cursor.SE_RESIZE_CURSOR), 
		SW(Cursor.SW_RESIZE_CURSOR), 
		R(Cursor.HAND_CURSOR);
		
		private int cursorType;
		
		EAnchors(int cursorType){
			this.cursorType = cursorType;
		}
		
		public int getCursorType() {
			return this.cursorType;
		}
	}
}
