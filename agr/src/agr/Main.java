package agr;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import agr.bd.BaseData;

public class Main extends JFrame{	
	private static BaseData bd = new BaseData();		
	private static JButton buttonOpenLoadNews; //Открытие окна загрузки новостей	
	private static JTextField fieldSearchNews; //Поле поиска
	private static JButton buttonSearchNews; //Кнопка поиска
	
	private static FrameLoadNews frameLoadNews = new FrameLoadNews(); //Загрузка статей
	private static FrameReadNew frameReadNew = new FrameReadNew(); //Чтение статьи
	
	private static JTable tableNews; //Таблица
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
	
	public Main() {initialize();}
	
	private void initialize() {	        
		 setBounds(100, 100, 700, 610);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setTitle("Агрегатор новостей");
		 setResizable(false);
		 getContentPane().setLayout(null);
	     getContentPane().setBackground(new Color(183,229,211));
	     
	     getContentPane().add(getButtonOpenLoadNews());
	     getContentPane().add(getButtonSearchNews());	   
	     getContentPane().add(getFieldSearchNews());	     
	     
	     JScrollPane scrollPaneNews = new JScrollPane(getTableNews());
	     scrollPaneNews.getVerticalScrollBar().setUnitIncrement(18);
		 scrollPaneNews.setBounds(2, 26, 691, 555); 
	     getContentPane().add(scrollPaneNews);	     
	}
	 
	public static JButton getButtonOpenLoadNews(){
	  if(buttonOpenLoadNews==null){
		  buttonOpenLoadNews = new JButton("Загрузка");
		  buttonOpenLoadNews.setBounds(2,2,100,23);
		  buttonOpenLoadNews.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent arg0) {
		    	   //Действие кнопки
		    	   getFrameLoadNews().setVisible(true);
		       }
		  });
	  }
	  return buttonOpenLoadNews;
	}
	public static void setButtonOpenLoadNews(JButton buttonOpenLoadNews) {
		Main.buttonOpenLoadNews = buttonOpenLoadNews;
	}
	
	public static JButton getButtonSearchNews(){
		if(buttonSearchNews==null){
			buttonSearchNews = new JButton("Поиск");
			buttonSearchNews.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	            	
	            	updateTabelNew(getFieldSearchNews().getText());
	            }
	        });       
		}
		buttonSearchNews.setBounds(104,2,100,23);
		return buttonSearchNews;
	}
	public static void setButtonSearchNews(JButton buttonSearchNews) {
		Main.buttonSearchNews = buttonSearchNews;
	}
	
	public static JTextField getFieldSearchNews() {
		if(fieldSearchNews==null){
			fieldSearchNews = new JTextField();
			fieldSearchNews.setBounds(208, 2, 485, 23);        
	        fieldSearchNews.setColumns(10);				
		}		
		return fieldSearchNews;
	}
	public static void setFieldSearchNews(JTextField fieldSearchNews) {
		Main.fieldSearchNews = fieldSearchNews;
	}

	public static JTable getTableNews() {
		if(tableNews==null){			
			tableNews = new JTable();			
			updateTabelNew(getFieldSearchNews().getText());		   
		    tableNews.addMouseListener(new MouseListener(){
		    	 public void mouseClicked(MouseEvent event) {}
		    	 public void mouseEntered(MouseEvent event) {}
		    	 public void mouseExited(MouseEvent event) {}
		    	 public void mousePressed(MouseEvent event) {
	                    if (event.getButton() == MouseEvent.BUTTON1) {
	                       String selectedData = null;   		         
	     		           selectedData = (String) tableNews.getValueAt(tableNews.getSelectedRows()[0], 0);
	       			      	//System.out.println("Selected: " + selectedData);
	       			       getFrameReadNew().updateNews(selectedData);;
	       			       getFrameReadNew().setVisible(true);
	                    }
	               }

	             public void mouseReleased(MouseEvent event) {}
		    });	    
		}
		return tableNews;
	}	
	public static void setTableNews(JTable tableNews) {
		Main.tableNews = tableNews;
	}	
	public static void updateTabelNew(String s){//Обновление таблицы новостей
		String[] columnNames = {"ИД","Заголовок","Описание","Категория"}; 
		String query;
		if(s.equals("") || s==null){
			query ="SELECT id, title, description, category FROM news ORDER BY id DESC;";
		}else{
			query ="SELECT id, title, description, category FROM news WHERE "
					+ "title like '%"+s+"%' or "
					+ "description like '%"+s+"%' or "
					+ "content like '%"+s+"%' ORDER BY id DESC";
		}
		try {bd.connect();} catch (SQLException ee) {System.out.println("Сonnection BD:" + ee);}		
		bd.select(query);				
		try {
			DefaultTableModel model = new DefaultTableModel(bd.getValueStringArr(), columnNames);			
			tableNews.setModel(model);	
			model.fireTableDataChanged();
			} 
		catch (Exception ee) {System.out.println("Error read result BD: " + ee);};			
		try {bd.close();} catch (SQLException ee){System.out.println("Error closed BD: " + ee);};		
	}
		
	public static FrameLoadNews getFrameLoadNews() {
		if(frameLoadNews==null){
			frameLoadNews = new FrameLoadNews();
		}
		return frameLoadNews;
	}
	public static void setFrameLoadNews(FrameLoadNews frameLoadNews) {
		Main.frameLoadNews = frameLoadNews;
	}
	
	public static FrameReadNew getFrameReadNew() {
		if(frameReadNew==null){
			frameReadNew = new FrameReadNew();
		}
		return frameReadNew;
	}	
	public static void setFrameReadNew(FrameReadNew frameReadNew) {	
		Main.frameReadNew = frameReadNew;
	}
}



