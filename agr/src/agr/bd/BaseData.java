package agr.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BaseData {
	private String server = "jdbc:postgresql://localhost:5432/postgres";
	private String user = "postgres";
	private String pass = "147896325";
	private Connection connect = null;
	private Statement status = null;
	public ResultSet dataSQL = null;

	// КОНСТРУКТОР СОЗДАЕТ СОЕДИНЕНИЕ	
	public BaseData() {}
	
	public BaseData(String url, String user, String pass) {
		try {
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection(url, user, pass);
			//System.out.println("connection bd good");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("connection bd bad: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		};
	}
	
	public void connect() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");			
			connect = DriverManager.getConnection(this.server, this.user, this.pass);
			//System.out.println("connection bd good");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("connection bd bad: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		};
	}

	// ЗАКРЫТЬ СЕАНС
	public void close() throws SQLException {
		try {
            if (dataSQL != null) {dataSQL.close();}
            if (status != null) {status.close();}
            if (connect != null) {connect.close();}
        } catch (SQLException ex) {ex.printStackTrace();}
	}

	// ЗАПРОС SELECT
	public void select(String query) {
		try {
			status = null;
			dataSQL = null;
			status = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			dataSQL = status.executeQuery(query);
			//System.out.println("Select good");
		} catch (Exception e) {
			System.out.println("Select bad: " + e);
		}
	}

	// ЗАПРОСЫ НА UPDATE, DELETE, INSERT
	public Boolean update(String query) {
		Statement status = null;
		try {
			status = connect.createStatement();
			status.executeUpdate(query);
			status.close();
			return true;
		} catch (Exception e) {
			System.out.println("Select bad: " + query+":" +e);
			return false;
		}
	}

	// ЗАПРОС SELECT в JSONarray
	public JsonArray toJSON() throws Exception {
		JsonArray jsonArray = new JsonArray();
		while (dataSQL.next()) {
			String columnName = null;
			String columnValue = null;
			int total_rows = dataSQL.getMetaData().getColumnCount();
			JsonObject obj = new JsonObject();
			for (int i = 0; i < total_rows; i++) {				
				columnName = dataSQL.getMetaData().getColumnLabel(i + 1).toLowerCase(); // Получить название столбца				
				columnValue = dataSQL.getString(i + 1); // Получить текстовое представление значения строки				
				obj.addProperty(columnName, columnValue); // Сохранять все значения в общую строку JSON
			}
			jsonArray.add(obj); // Сохранять общую строку JSON в массив строк JSON			
		}
		return jsonArray;
	}
	
	
	public String[][] getValueStringArr() throws Exception {//Получить массив значений
		String[][] data = new String[getRowCount()][getColCount()];
		int j=0;
		while (dataSQL.next()) {
			String columnValue = null;
			int total_rows = dataSQL.getMetaData().getColumnCount();
			for (int i = 0; i < total_rows; i++) {							
				columnValue = dataSQL.getString(i + 1); // Получить текстовое представление значения строки				
				data[j][i] = columnValue;
			}		
			j++;				
		}
		this.dataSQL.beforeFirst();
		return data;
	}
	
	public String[] getNameStringArr() throws Exception {//Получить массив наименования столбцов
		String[] data = new String[getColCount()];		
		this.dataSQL.last();
	    String columnName = null;
		int total_rows = dataSQL.getMetaData().getColumnCount();
		for (int i = 0; i < total_rows; i++) {	
			columnName = dataSQL.getMetaData().getColumnLabel(i+1).toLowerCase(); // Получить название столбца			
			data[i] = columnName;
		}
		this.dataSQL.beforeFirst();
		return data;
	}
	

	public String getServer() {return server;}
	public void setServer(String server) {this.server = server;}

	public String getUser() {return user;}
	public void setUser(String user) {this.user = user;}

	public String getPass() {return pass;}
	public void setPass(String pass) {this.pass = pass;}
	
	public int getRowCount() {
		int size=0;
		try {
			this.dataSQL.last();
		    size = this.dataSQL.getRow();
		    this.dataSQL.beforeFirst();
		}
		catch(SQLException ex) {return 0;}		
		return size;
	}
	
	public int getColCount() {
		int size=0;
		try {
			this.dataSQL.last();
		    size = this.dataSQL.getMetaData().getColumnCount();
		    this.dataSQL.beforeFirst();
		}
		catch(SQLException ex) {return 0;}		
		return size;
	}
	
	
	
	public void doQuery(String query){	
		String error = "";
		try {connect();} catch (SQLException e) {error= error + " <b>Ошибка</b>, соединения с базой: " + e;}
		update(query);
		try{close();} catch(SQLException e){error = error + "<b>Ошибка</b>, закрытия соединения с базой:" + e;}
	}
	
	
	
	
	public String getNewId(String seqName){
		String id = "";
		if(seqName!=null && !seqName.equals("")){
			try {connect();} catch (SQLException ee) {System.out.println("Error class Tbd, method getNewId(). Сonnection BD:" + ee);}
			select("SELECT nextval('"+seqName+"') AS id");
			try {while (dataSQL.next()) {
				id=dataSQL.getString("id");
			}} 
			catch (Exception ee) {System.out.println("Error class Tbd, method getNewId(). Error read result BD: " + ee);};	
			try {close();} catch (SQLException ee){System.out.println("Error class Tbd, method getNewId(). Error closed BD: " + ee);};	
		}
		return id;
		
	}
}
