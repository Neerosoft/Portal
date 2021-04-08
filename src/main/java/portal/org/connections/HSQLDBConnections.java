package portal.org.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class HSQLDBConnections {
	
	  private Connection con = null;
	  private String driver = "org.hsqldb.jdbcDriver";
	  private String puente = "jdbc:hsqldb:hsql://";
	  private String hostname = "64.91.249.118";
	  private String port="9001";
	  private String db = "raptor";
	  private String u = "SA";
	  private String p = "RAPTOR";	  
	  
	  
	  

	public HSQLDBConnections() {
		
	}
	
	public Connection getConnection(){
	    try
	    {
	      Class.forName(this.driver).newInstance();
	      this.puente = (this.puente+ this.hostname +":"+this.port+"/"+this.db);
	      
	      System.out.println(this.puente);
	      
	      this.con = DriverManager.getConnection(this.puente, this.u, this.p);
	      
	      System.out.println("__conexión establecida..:\n\n");
	      return this.con;
	    }
	    catch (Exception e)
	    {
	      System.out.print("problema " + e + "\n\n" + this.puente);
	      e.printStackTrace();
	    }
	    return this.con;
	  }
	
	public  void close(Connection con) {
		  try {
				con.close();
	                        System.out.println("__conexión finalizada..:\n\n");
			} 
			catch (Exception ex) {
				System.out.println("__conexión finalizada..:\n\n");
			}
		}

	public Connection getCon() {
		return con;
	}


	public void setCon(Connection con) {
		this.con = con;
	}


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public String getPuente() {
		return puente;
	}


	public void setPuente(String puente) {
		this.puente = puente;
	}


	public String getU() {
		return u;
	}


	public void setU(String u) {
		this.u = u;
	}


	public String getP() {
		return p;
	}


	public void setP(String p) {
		this.p = p;
	}


	public String getHostname() {
		return hostname;
	}


	public void setHostname(String hostname) {
		this.hostname = hostname;
	}


	public String getDb() {
		return db;
	}


	public void setDb(String db) {
		this.db = db;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}
	

}
