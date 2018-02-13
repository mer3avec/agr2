package agr.object.dao;

public class SiteDao {
	private String id = null;
	private String name = null;
	private String new_block = null;
	private String new_title = null;
	private String new_description = null;
	private String new_content = null;
	private String new_category = null;
	private String new_link = null;
	private String new_guid = null;
	private String new_pub_date = null;
	
	public SiteDao(){}

	public String getId() {return id;}
	public void setId(String id) {this.id = id;}	
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getNewBlock() {return new_block;}
	public void setNewBlock(String new_block) {this.new_block = new_block;}

	public String getNewTitle() {return new_title;}
	public void setNewTitle(String new_title) {this.new_title = new_title;}

	public String getNewDescription() {return new_description;}
	public void setNewDescription(String new_description) {this.new_description = new_description;}

	public String getNewContent() {return new_content;}
	public void setNewContent(String new_content) {this.new_content = new_content;}

	public String getNewCategory() {return new_category;}
	public void setNewCategory(String new_category) {this.new_category = new_category;}

	public String getNewLink() {return new_link;}
	public void setNewLink(String new_link) {this.new_link = new_link;}

	public String getNewGuid() {return new_guid;}
	public void setNewGuid(String new_guid) {this.new_guid = new_guid;}

	public String getNewPubDate() {return new_pub_date;}
	public void setNewPubDate(String new_pub_date) {this.new_pub_date = new_pub_date;}
}	
