package agr.object.dao;

public class NewDao {
	private String id = null;
	private String title = null;
	private String description = null;
	private String content = null;
	private String category = null;
	private String link = null;
	private String guid = null;
	private String pub_date = null;
	private String site_id = null;
	
	public NewDao(){}

	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
	
	public String getLink() {return link;}
	public void setLink(String link) {this.link = link;}
	
	public String getGuid() {return guid;}
	public void setGuid(String guid) {this.guid = guid;}
	
	public String getPubDate() {return pub_date;}
	public void setPubDate(String pub_date) {this.pub_date = pub_date;}
	
	public String getSiteId() {return site_id;}
	public void setSiteId(String site_id) {this.site_id = site_id;}
}
