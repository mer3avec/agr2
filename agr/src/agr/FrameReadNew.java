package agr;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import agr.bd.BaseData;

public class FrameReadNew extends JFrame{
	private static JPanel panelNews;
	private static JLabel labelNews;
	private static JScrollPane scrollPaneNews;
	private static BaseData bd = new BaseData();
	
	public FrameReadNew(){
		 setTitle("Новость");
		 setBounds(150, 150, 750, 660);
		 getContentPane().setLayout(null);
	     getContentPane().setBackground(new Color(183,229,211));
	     getContentPane().add(getScrollPaneNews());
	     setResizable(false);
	     setVisible(false);
	}
	
	public static JScrollPane getScrollPaneNews() {
		if (scrollPaneNews==null){
			scrollPaneNews = new JScrollPane(getLabelNews());		
			scrollPaneNews.setBackground(new Color(255,255,255));
			scrollPaneNews.getVerticalScrollBar().setUnitIncrement(18);
			scrollPaneNews.getHorizontalScrollBar().setVisible(false);
			scrollPaneNews.setBounds(2, 2, 740, 627); 
		}		
		return scrollPaneNews;
	}
	public static void setScrollPanelNews(JScrollPane scrollPaneNews) {
		FrameReadNew.scrollPaneNews = scrollPaneNews;
	}
	
	public static JLabel getLabelNews() {
		if(labelNews==null){
	        labelNews = new JLabel();
	        
	        labelNews.setOpaque(true);
	        labelNews.setBackground(new Color(255,255,255));  
	       
	        Font font = new Font(null, Font.PLAIN, 12);
	        labelNews.setFont(font);
	        
	        labelNews.setVerticalAlignment(JLabel.TOP);
		}
		return labelNews;
	}
	public static void setLabelNews(JLabel labelNews) {
		FrameReadNew.labelNews = labelNews;
	}
	public void updateNews(String newsId){//Обновление окна
		if(newsId!=null || !newsId.equals("")){	
			String query ="SELECT title, content FROM news WHERE id="+newsId+";";			
			try {bd.connect();} catch (SQLException ee) {System.out.println("Сonnection BD:" + ee);}		
			bd.select(query);				
			try {
				while (bd.dataSQL.next()) {
					scrollPaneNews.setBorder(BorderFactory.createTitledBorder(bd.dataSQL.getString("title")));
					labelNews.setText("<html>"+bd.dataSQL.getString("content")+"</html>");
				}				
			} 
			catch (Exception ee) {System.out.println("Error read result BD: " + ee);};			
			try {bd.close();} catch (SQLException ee){System.out.println("Error closed BD: " + ee);};	
		}
	}	
}
