package agr.object;

import agr.object.dao.NewDao;

public class New extends NewDao{
	private String seq = "news_id_seq";	
	
	public New(){}
	
	public String getUpdateQuery(){
		String query="";
		if(getId()!=null){
			query=" UPDATE news SET ";
			String r="";			
			if(isNotUpdate(getTitle())!=null){
				if(getTitle().equals("")){r=r+"title=null, ";
				}else{r=r+"title='"+isNotUpdate(getTitle())+"', ";}
			}
			if(isNotUpdate(getDescription())!=null){
				if(getDescription().equals("")){r=r+"description=null, ";
				}else{r=r+"description='"+isNotUpdate(getDescription())+"', ";}
			}
			if(isNotUpdate(getContent())!=null){
				if(getContent().equals("")){r=r+"content=null, ";
				}else{r=r+"content='"+isNotUpdate(getContent())+"', ";}
			}
			if(isNotUpdate(getCategory())!=null){
				if(getCategory().equals("")){r=r+"category=null, ";
				}else{r=r+"category='"+isNotUpdate(getCategory())+"', ";}
			}
			if(isNotUpdate(getLink())!=null){
				if(getLink().equals("")){r=r+"link=null, ";
				}else{r=r+"link='"+isNotUpdate(getLink())+"', ";}
			}
			if(isNotUpdate(getGuid())!=null){
				if(getGuid().equals("")){r=r+"guid=null, ";
				}else{r=r+"guid='"+isNotUpdate(getGuid())+"', ";}
			}
			if(isNotUpdate(getPubDate())!=null){
				if(getPubDate().equals("")){r=r+"pub_date=null, ";
				}else{r=r+"pub_date='"+isNotUpdate(getPubDate())+"'::timestamp without time zone, ";}
			}
			if(isNotUpdate(getSiteId())!=null){r=r+"site_id='"+isNotUpdate(getSiteId())+"', ";}		
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
			query="INSERT INTO news ";
			String r="";
			String p1="(";
			String p2="VALUES (";
			if(isNotUpdate(getId())!=null){p1=p1+"id, "; p2=p2+isNotUpdate(getId())+", ";}
			
			if(isNotUpdate(getTitle())!=null){
				p1=p1+"title, "; 			
				if(getTitle().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getTitle())+"', ";}			
			}	
			
			if(isNotUpdate(getDescription())!=null){
				p1=p1+"description, "; 			
				if(getDescription().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getDescription())+"', ";}			
			}
			
			if(isNotUpdate(getContent())!=null){
				p1=p1+"content, "; 			
				if(getContent().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getContent())+"', ";}			
			}
			
			if(isNotUpdate(getCategory())!=null){
				p1=p1+"category, "; 			
				if(getCategory().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getCategory())+"', ";}			
			}
		
			if(isNotUpdate(getLink())!=null){
				p1=p1+"link, "; 			
				if(getLink().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getLink())+"', ";}			
			}
			
			if(isNotUpdate(getGuid())!=null){
				p1=p1+"guid, "; 			
				if(getGuid().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getGuid())+"', ";}			
			}
			if(isNotUpdate(getPubDate())!=null){
				p1=p1+"pub_date, "; 			
				if(getPubDate().equals("")){p2=p2+"null, ";}else{p2=p2+"'"+isNotUpdate(getPubDate())+"'::timestamp without time zone, ";}			
			}
			if(isNotUpdate(getSiteId())!=null){p1=p1+"site_id, "; p2=p2+isNotUpdate(getSiteId())+", ";}			
			p1=p1+"@@&&"; p2=p2+"@@&&";
			p1=p1.replace(", @@&&", " ").replace("@@&&", " ")+")"; 	p2=p2.replace(", @@&&", " ").replace("@@&&", " ")+")";
			r=p1+p2;
			query=query+r;
			query=query+"; ";
		}
		return query;
	}
	
	public String getDeleteyQuery(String id){
		String query="";
		if(id!=null && !id.equals("")){
			query=" DELETE FROM news WHERE id="+id+"; ";		
		}
		return query;
	}
		
	public String isNotUpdate(String val){
		if(val==null) {return null;}
		else if(val.equals("not") || val.equals("null")){return null;}
		else if(val.equals("")) {return "null";}
		else {return val;}	
	}
	
	public String getSeq() {return seq;}
}
