package agr;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import agr.bd.BaseData;
import agr.object.Site;
import agr.parser.Aggregator;

public class FrameLoadNews extends JFrame{	
	private static BaseData bd = new BaseData();
		
	private static JTextField fieldSiteName;
	
	private static JTextField fieldNewBlock;
	private static JTextField fieldNewTitle;
	private static JTextField fieldNewDescription;
	private static JTextField fieldNewContent;
	private static JTextField fieldNewCategory;
	private static JTextField fieldNewLink;
	private static JTextField fieldNewGuid;
	private static JTextField fieldPubDate;	
	//private static String siteId = null;	
	private static JComboBox comboBoxSite; 
	
	private static JButton buttonLoadNews; //Кнопка загрузки	
	public FrameLoadNews(){
		 setTitle("Загрузка статей");
		 setBounds(150, 150, 750, 310);
		 
		 getContentPane().setLayout(null);
	     getContentPane().setBackground(new Color(183,229,211));
	     setResizable(false);
	     
	     //Правила парсинга
	     JPanel panelSite = new JPanel();	     
	     panelSite.setLayout(null);	     
	     panelSite.setBounds(2, 23, 740, 232);
	     panelSite.setBorder(BorderFactory.createTitledBorder("Правила парсинга:"));  
	     
	     JLabel labelSiteName = new JLabel("Путь до сайта(RSS):");
	     labelSiteName.setBounds(8, 8, 120, 46);
	     labelSiteName.setHorizontalAlignment(JLabel.RIGHT);
	     panelSite.add(labelSiteName);
	     panelSite.add(getFieldSiteName());
	     
		     //Панель css запросов
		     JPanel panelTagNew = new JPanel();
		     panelTagNew.setLayout(null);	     
		     panelTagNew.setBounds(4, 43, 732, 186);
		     panelTagNew.setBorder(BorderFactory.createTitledBorder("CSS запросы:"));    
		    
		     JLabel labelNewsBlock = new JLabel("Блоки новостей:");
		     labelNewsBlock.setBounds(4, 8, 120, 46);
		     labelNewsBlock.setHorizontalAlignment(JLabel.RIGHT);
		     panelTagNew.add(labelNewsBlock);
		     panelTagNew.add(getFieldNewBlock());
		     
		     JLabel labelNewsTitle = new JLabel("Заголовок:");
		     labelNewsTitle.setBounds(8, 28, 120, 46);
		     labelNewsTitle.setHorizontalAlignment(JLabel.RIGHT);
		     panelTagNew.add(labelNewsTitle);
		     panelTagNew.add(getFieldNewTitle());
		     
		     JLabel labelNewsContent = new JLabel("Описание:");
		     labelNewsContent.setBounds(8, 48, 120, 46);
		     labelNewsContent.setHorizontalAlignment(JLabel.RIGHT);
		     panelTagNew.add(labelNewsContent);
		     panelTagNew.add(getFieldNewContent());	     
		     
		     JLabel labelNewDescription = new JLabel("Содержание:");
		     labelNewDescription.setBounds(8, 68, 120, 46);
		     labelNewDescription.setHorizontalAlignment(JLabel.RIGHT);
		     panelTagNew.add(labelNewDescription);
		     panelTagNew.add(getFieldNewDescription());
		     
		     JLabel labelNewsCategory = new JLabel("Категория:");
		     labelNewsCategory.setBounds(8, 88, 120, 46);
		     labelNewsCategory.setHorizontalAlignment(JLabel.RIGHT);
		     panelTagNew.add(labelNewsCategory);
		     panelTagNew.add(getFieldNewCategory());
		     
		     JLabel labelNewsLink = new JLabel("URL путь:");
		     labelNewsLink.setBounds(8, 108, 120, 46);
		     labelNewsLink.setHorizontalAlignment(JLabel.RIGHT);
		     panelTagNew.add(labelNewsLink);
		     panelTagNew.add(getFieldNewLink());
		     
		     JLabel labelNewsGuid = new JLabel("guid:");
		     labelNewsGuid.setBounds(8, 128, 120, 46);
		     labelNewsGuid.setHorizontalAlignment(JLabel.RIGHT);
		     panelTagNew.add(labelNewsGuid);
		     panelTagNew.add(getFieldNewGuid());
		     
		     JLabel labelNewsPubDate = new JLabel("Дата публикации:");
		     labelNewsPubDate.setBounds(8, 148, 120, 46);
		     labelNewsPubDate.setHorizontalAlignment(JLabel.RIGHT);
		     panelTagNew.add(labelNewsPubDate);
		     panelTagNew.add(getFieldPubDate());  
	     
		 panelSite.add(panelTagNew);
	     getContentPane().add(panelSite);
	     getContentPane().add(getButtonLoadNews());
	     getContentPane().add(getComboBoxSite());
	     setVisible(false);
	}
	
	public static JTextField getFieldNewBlock() {
		if(fieldNewBlock==null){			
			fieldNewBlock = new JTextField();
			fieldNewBlock.setBounds(135, 20, 592, 20);
		}		
		return fieldNewBlock;
	}
	public static void setFieldNewBlock(JTextField fieldNewBlock) {
		FrameLoadNews.fieldNewBlock = fieldNewBlock;
	}
		
	public static JTextField getFieldNewTitle() {
		if(fieldNewTitle==null){			
			fieldNewTitle = new JTextField();
			fieldNewTitle.setBounds(135, 40, 592, 20);
		}	
		return fieldNewTitle;
	}
	public static void setFieldNewTitle(JTextField fieldNewTitle) {
		FrameLoadNews.fieldNewTitle = fieldNewTitle;
	}
	
	public static JTextField getFieldNewDescription() {
		if(fieldNewDescription==null){			
			fieldNewDescription = new JTextField();
			fieldNewDescription.setBounds(135, 60, 592, 20);
		}	
		return fieldNewDescription;
	}
	public static void setFieldNewDescription(JTextField fieldNewDescription) {
		FrameLoadNews.fieldNewDescription = fieldNewDescription;
	}
		
	public static JTextField getFieldNewContent() {
		if(fieldNewContent==null){			
			fieldNewContent = new JTextField();
			fieldNewContent.setBounds(135, 80, 592, 20);
		}	
		return fieldNewContent;
	}	
	public static void setFieldNewContent(JTextField fieldNewContent) {		
		FrameLoadNews.fieldNewContent = fieldNewContent;
	}
		
	public static JTextField getFieldNewCategory() {
		if(fieldNewCategory==null){			
			fieldNewCategory = new JTextField();
			fieldNewCategory.setBounds(135, 100, 592, 20);
		}
		return fieldNewCategory;
	}	
	public static void setFieldNewCategory(JTextField fieldNewCategory) {
		FrameLoadNews.fieldNewCategory = fieldNewCategory;
	}
		
	public static JTextField getFieldNewLink() {
		if(fieldNewLink==null){			
			fieldNewLink = new JTextField();
			fieldNewLink.setBounds(135, 120, 592, 20);
		}		
		return fieldNewLink;
	}
	public static void setFieldNewLink(JTextField fieldNewLink) {
		FrameLoadNews.fieldNewLink = fieldNewLink;
	}
		
	public static JTextField getFieldNewGuid() {
		if(fieldNewGuid==null){			
			fieldNewGuid = new JTextField();
			fieldNewGuid.setBounds(135, 140, 592, 20);
		}
		return fieldNewGuid;
	}
	public static void setFieldNewGuid(JTextField fieldNewGuid) {
		FrameLoadNews.fieldNewGuid = fieldNewGuid;
	}
		
	public static JTextField getFieldPubDate() {
		if(fieldPubDate==null){			
			fieldPubDate = new JTextField();
			fieldPubDate.setBounds(135, 160, 592, 20);
		}
		return fieldPubDate;
	}
	public static void setFieldPubDate(JTextField fieldPubDate) {		
		FrameLoadNews.fieldPubDate = fieldPubDate;
	}
	
	public static JTextField getFieldSiteName() {
		if(fieldSiteName==null){			
			fieldSiteName = new JTextField();
			fieldSiteName.setBounds(135, 20, 592, 20);
		}
		return fieldSiteName;
	}
	public static void setFieldSiteName(JTextField fieldSiteName) {
		FrameLoadNews.fieldSiteName = fieldSiteName;
	}

	public static JComboBox getComboBoxSite() {
		if(comboBoxSite==null){
			comboBoxSite = new JComboBox();
			getBoxSiteData();
			//comboBoxSite.setFont(font);
			comboBoxSite.setAlignmentX(LEFT_ALIGNMENT);
			comboBoxSite.setBounds(2, 2, 220, 20);
			comboBoxSite.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String item = (String) comboBoxSite.getSelectedItem();
	                setSite(item);
	                //System.out.println(item);
	            }
	        });
		}
		return comboBoxSite;
	}

	public static void setComboBoxSite(JComboBox comboBoxSite) {
		FrameLoadNews.comboBoxSite = comboBoxSite;
	}

	public static JButton getButtonLoadNews() {
		if(buttonLoadNews==null){
			buttonLoadNews = new JButton("Загрузить");
			buttonLoadNews.setBounds(2,256,100,23);
			buttonLoadNews.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	      
	            	loadSite();
	            }
	        });       
		}
		
		return buttonLoadNews;
	}
	public static void setButtonLoadNews(JButton buttonLoadNews) {
		FrameLoadNews.buttonLoadNews = buttonLoadNews;
	}

	//Загрузка статей в базу
	private static void loadSite(){		
		Site site = getSite();		
		saveSite(site); //Сохранение правил парсинга или обновление	
		Aggregator agg = new Aggregator(site);	//Загрузка новостей в базу
	}
	//Запись правил в базу
	private static void saveSite(Site site){
		String query = "";
		if (site.getName()!=null && !site.getName().equals("")){
			try {bd.connect();} catch (SQLException ee) {System.out.println("Сonnection BD:" + ee);}		
			bd.select("SELECT id FROM sites WHERE name='"+site.getName()+"' ORDER BY name DESC LIMIT 1;");				
			try {
				while (bd.dataSQL.next()) {
					site.setId(bd.dataSQL.getString("id"));					
				}	
			} 
			catch (Exception ee) {System.out.println("Error read result BD: " + ee);};			
			try {bd.close();} catch (SQLException ee){System.out.println("Error closed BD: " + ee);};	
			if(site.getId()==null){
				site.setId(bd.getNewId(site.getSeq()));
				query=site.getInsertQuery();
			}else{
				query=site.getUpdateQuery();
			}
			bd.doQuery(query);
			
		}
	}
	//Формирует новый Site
	private static Site getSite(){
		Site site = new Site();
			//site.setId(siteId);	
			site.setName(fieldSiteName.getText());			
			site.setNewBlock(fieldNewBlock.getText());
			site.setNewTitle(fieldNewTitle.getText());
			site.setNewDescription(fieldNewDescription.getText());
			site.setNewContent(fieldNewContent.getText());			
			site.setNewCategory(fieldNewCategory.getText());
			site.setNewLink(fieldNewLink.getText());
			site.setNewGuid(fieldNewGuid.getText());
			site.setNewPubDate(fieldPubDate.getText());	
		return site;
	}
	//Получения списка Сайтов
	private static void getBoxSiteData(){
		try {bd.connect();} catch (SQLException ee) {System.out.println("Сonnection BD:" + ee);}		
		bd.select("SELECT name FROM sites ORDER BY name ASC");				
		try {
			String[] boxOption = new String[bd.getRowCount()];
			int i = 0;
			while (bd.dataSQL.next()) {
				boxOption[i]=bd.dataSQL.getString("name");
				i++;
			}
			comboBoxSite.removeAllItems();
			comboBoxSite.addItem("");
			for(String s: boxOption){
				comboBoxSite.addItem(s);
		    }
		} 
		catch (Exception ee) {System.out.println("Error read result BD: " + ee);};			
		try {bd.close();} catch (SQLException ee){System.out.println("Error closed BD: " + ee);};
	}
	//Заполнение
	private static void setSite(String siteName){
		if(siteName!=null && !siteName.equals("")){
			try {bd.connect();} catch (SQLException ee) {System.out.println("Сonnection BD:" + ee);}		
			bd.select("SELECT name, new_block, new_title, new_description, new_content, new_category, "
					+ "new_link, new_guid, new_pub_date FROM sites WHERE name='"+siteName+"' ORDER BY name DESC LIMIT 1");				
			try {
				while (bd.dataSQL.next()) {					
					fieldSiteName.setText(bd.dataSQL.getString("name"));			
					fieldNewBlock.setText(bd.dataSQL.getString("new_block"));
					fieldNewTitle.setText(bd.dataSQL.getString("new_title"));
					fieldNewDescription.setText(bd.dataSQL.getString("new_description"));
					fieldNewContent.setText(bd.dataSQL.getString("new_content"));
					fieldNewCategory.setText(bd.dataSQL.getString("new_category"));
					fieldNewLink.setText(bd.dataSQL.getString("new_link"));
					fieldNewGuid.setText(bd.dataSQL.getString("new_guid"));
					fieldPubDate.setText(bd.dataSQL.getString("new_pub_date"));					
				}
			}
			catch (Exception ee) {System.out.println("Error read result BD: " + ee);};			
			try {bd.close();} catch (SQLException ee){System.out.println("Error closed BD: " + ee);};
		}
	}
}
