package agr.parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import agr.bd.BaseData;
import agr.object.New;
import agr.object.Site;

public class Aggregator {
	private Site site = null;
	private static BaseData bd = new BaseData();
	
	public Aggregator(Site site){
		if(site!=null){
			setSite(site);			
			load();
		}
	}

	public Site getSite() {return site;}
	public void setSite(Site site) {this.site = site;}	
	public void load(){
		Document doc;
		Elements blocks;			
		try { // определяем откуда будем скачивать данные        
            New addNew;           
            doc = Jsoup.connect(site.getName()).get(); // задаем с какого места парсить           
            blocks = doc.select(site.getNewBlock());           
            Document documentNew;
            for (Element block : blocks) {
            	addNew = new New();       
            	//Поиск данных на стартовой странице сайта
            	addNew.setTitle(getValue(block,site.getNewTitle(),false));
            	addNew.setCategory(getValue(block,site.getNewCategory(),false));            	
            	addNew.setDescription(getValue(block,site.getNewDescription(),false));
            	addNew.setLink(getValue(block,site.getNewLink(),false));
            	addNew.setGuid(getValue(block,site.getNewGuid(),false));
            	addNew.setPubDate(getValue(block,site.getNewPubDate(),false));             	
            	documentNew = getDocumentNew(addNew);
            	if(documentNew!=null){//Поиск данных внутри странице статьи
            		if(addNew.getTitle()==null || addNew.getTitle().equals("")){addNew.setTitle(getValue(documentNew,site.getNewTitle(),true));} 
            		if(addNew.getCategory()==null || addNew.getCategory().equals("")){addNew.setCategory(getValue(documentNew,site.getNewCategory(),true));} 
            		if(addNew.getDescription()==null || addNew.getDescription().equals("")){addNew.setDescription(getValue(documentNew,site.getNewDescription(),true));}
            		if(addNew.getGuid()==null || addNew.getGuid().equals("")){addNew.setGuid(getValue(documentNew,site.getNewGuid(),true));}
            		if(addNew.getPubDate()==null || addNew.getPubDate().equals("")){addNew.setPubDate(getValue(documentNew,site.getNewPubDate(),true));}            		
                	addNew.setContent(documentNew.select(site.getNewContent()).html());                
            	}
            	addNew.setSiteId(site.getId());
                saveNews(addNew);
               
            }            
        } catch (IOException e) {e.printStackTrace();}
	}
	public static Document getDocumentNew(New addNew){
		Document doc = null;		
		if(addNew!=null){
			if(addNew.getLink()!=null && !addNew.getLink().equals("")){
				try { // определяем откуда будем скачивать данные  
					doc = Jsoup.connect(addNew.getLink()).get(); // задаем с какого места парсить  
					System.out.println(addNew.getLink());
				} catch (IOException e) {e.printStackTrace();}
			}		
		}		
		return doc;
	}
	
	private void saveNews(New add){
		String query = "";
		Integer xExist = 0;		
		if (add.getGuid()!=null && add.getSiteId()!=null 
				&& !add.getGuid().equals("") &&  !add.getSiteId().equals("")){
			//Поиск дублей
			try {bd.connect();} catch (SQLException ee) {System.out.println("Сonnection BD:" + ee);}		
			bd.select("SELECT count(id) AS count FROM news WHERE guid='"+add.getGuid()+"' and site_id="+add.getSiteId()+";");				
			try {
				while (bd.dataSQL.next()) {					
					xExist=bd.dataSQL.getInt("count");
				}	
			} 
			catch (Exception ee) {System.out.println("Error read result BD: " + ee);};			
			try {bd.close();} catch (SQLException ee){System.out.println("Error closed BD: " + ee);};	
			
			if(xExist==0){
				add.setId(bd.getNewId(add.getSeq()));
				query=add.getInsertQuery();
				bd.doQuery(query);				
			}
		}
	}
	
	private static String getValue(Element e, String s, Boolean isG){
		String result = "";
		if(s!=null && !s.equals("") && isG!=null && e!=null){
			if(s.contains(",//g")){
				if(isG){
					s=s.replace(",//g","");
					result = getValue(e, s);
				}
			}else{
				result = getValue(e, s);	
			}
		}
		return result;	
	}
	private static String getValue(Element e, String s){
		String result = "";
		if(s!=null && !s.equals("") && e!=null){
			if(s.contains(",")){
				String tag ="";
				tag = s.split(",")[0];				
				s=s.split("attr=")[1];
				result = e.select(tag).attr(s);
			}else{
				result=e.select(s).text();		
			}			
		}
		return result;	
	}
	
	
	
}
