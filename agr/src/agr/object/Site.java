package agr.object;

import java.math.BigDecimal;
import java.math.RoundingMode;

import agr.object.dao.SiteDao;

public class Site extends SiteDao{
	private String seq = "sites_id_seq";	
	
	public Site(){}
	
	public String getUpdateQuery(){
		String query="";
		if(getId()!=null){//Если есть тип работы и проект
			query=" UPDATE sites SET ";
			String r="";
			if(isNotUpdate(getName())!=null){
				if(getName().equals("")){r=r+"name=null, ";
				}else{r=r+"name='"+isNotUpdate(getName())+"', ";}
			}
			if(isNotUpdate(getNewBlock())!=null){
				if(getNewBlock().equals("")){r=r+"new_block=null, ";
				}else{r=r+"new_block='"+isNotUpdate(getNewBlock())+"', ";}
			}
			if(isNotUpdate(getNewTitle())!=null){
				if(getNewTitle().equals("")){r=r+"new_title=null, ";
				}else{r=r+"new_title='"+isNotUpdate(getNewTitle())+"', ";}
			}
			if(isNotUpdate(getNewDescription())!=null){
				if(getNewDescription().equals("")){r=r+"new_description=null, ";
				}else{r=r+"new_description='"+isNotUpdate(getNewDescription())+"', ";}
			}
			if(isNotUpdate(getNewContent())!=null){
				if(getNewContent().equals("")){r=r+"new_content=null, ";
				}else{r=r+"new_content='"+isNotUpdate(getNewContent())+"', ";}
			}
			if(isNotUpdate(getNewCategory())!=null){
				if(getNewCategory().equals("")){r=r+"new_category=null, ";
				}else{r=r+"new_category='"+isNotUpdate(getNewCategory())+"', ";}
			}
			if(isNotUpdate(getNewLink())!=null){
				if(getNewLink().equals("")){r=r+"new_link=null, ";
				}else{r=r+"new_link='"+isNotUpdate(getNewLink())+"', ";}
			}
			if(isNotUpdate(getNewGuid())!=null){
				if(getNewGuid().equals("")){r=r+"new_guid=null, ";
				}else{r=r+"new_guid='"+isNotUpdate(getNewGuid())+"', ";}
			}
			if(isNotUpdate(getNewPubDate())!=null){
				if(getNewPubDate().equals("")){r=r+"new_pub_date=null, ";
				}else{r=r+"new_pub_date='"+isNotUpdate(getNewPubDate())+"', ";}
			}					
			r=r+"@@&&";
			r=r.replace(", @@&&", " ").replace("@@&&", " ");		
			if(r.equals(" ")){query="";
			}else{
				query=query+r;
				query=query+"WHERE id="+getId()+";";
			}			
		}
		return query;
	}
		
	public String getInsertQuery(){
		String query="";
		if(getId()!=null){
			query="INSERT INTO sites ";
			String r="";
			String p1="(";
			String p2="VALUES (";
			if(isNotUpdate(getId())!=null){p1=p1+"id, "; p2=p2+isNotUpdate(getId())+", ";}
			
			if(isNotUpdate(getName())!=null){
				p1=p1+"name, "; 			
				if(getName().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getName())+"', ";}			
			}
			
			if(isNotUpdate(getNewBlock())!=null){
				p1=p1+"new_block, "; 			
				if(getNewBlock().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getNewBlock())+"', ";}			
			}	
			
			if(isNotUpdate(getNewTitle())!=null){
				p1=p1+"new_title, "; 			
				if(getNewTitle().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getNewTitle())+"', ";}			
			}			
			
			if(isNotUpdate(getNewDescription())!=null){
				p1=p1+"new_description, "; 			
				if(getNewDescription().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getNewDescription())+"', ";}			
			}
			
			if(isNotUpdate(getNewContent())!=null){
				p1=p1+"new_content, "; 			
				if(getNewContent().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getNewContent())+"', ";}			
			}
			if(isNotUpdate(getNewCategory())!=null){
				p1=p1+"new_category, "; 			
				if(getNewCategory().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getNewCategory())+"', ";}			
			}
			if(isNotUpdate(getNewLink())!=null){
				p1=p1+"new_link, "; 			
				if(getNewLink().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getNewLink())+"', ";}			
			}
			if(isNotUpdate(getNewGuid())!=null){
				p1=p1+"new_guid, "; 			
				if(getNewGuid().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getNewGuid())+"', ";}			
			}
			if(isNotUpdate(getNewPubDate())!=null){
				p1=p1+"new_pub_date, "; 			
				if(getNewPubDate().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getNewPubDate())+"', ";}			
			}
			p1=p1+"@@&&"; p2=p2+"@@&&";
			p1=p1.replace(", @@&&", " ").replace("@@&&", " ")+")"; 	p2=p2.replace(", @@&&", " ").replace("@@&&", " ")+")";
			r=p1+p2;
			query=query+r;
			query=query+"; ";
		}
		return query;
	}
	
	public String getDeleteyQuery(String id){// Генерирует строку запроса на удаление в базе
		String query="";
		if(id!=null && !id.equals("")){//Если есть тип работы и проект
			query=" DELETE FROM sites WHERE id="+id+"; ";		
		}
		return query;
	}
		
	public String isNotUpdate(String val){
		if(val==null) {return null;}
		else if(val.equals("not") || val.equals("null")){return null;}
		else if(val.equals("")) {return "null";}
		else {return val;}	
	}
	
	public String round(String a){return round(a, 2);}
	public String round(String a, Integer size){
		String r = "";
		Double n = null;
		if (a!=null && size!=null && !a.equals("")){
			n=new BigDecimal(new Double(a)).setScale(size, RoundingMode.HALF_UP).doubleValue();
			r=n.toString();
		}		
		return r;
	}

	public String getSeq() {return seq;}
	
	
	
}
