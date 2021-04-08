package portal.org.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import portal.org.connections.HSQLDBConnections;
import portal.org.pojo.Cias;

public class CiasDAO {
	
	private HSQLDBConnections hsqldb=null;

	public CiasDAO() {
		this.hsqldb=new HSQLDBConnections();		
	}
	public ArrayList<Cias>getAllCias(){
		
		ArrayList<Cias>cias=new ArrayList<Cias>();
		String sqlCommander="select * from v_cia order by namecia";
		Connection con=null;
		Statement orden=null;
		ResultSet salida=null;
		
		try {
			con=hsqldb.getConnection();			
			orden=con.createStatement();
			salida=orden.executeQuery(sqlCommander);
			while(salida.next()) {
				Cias c=new Cias();
				c.setIdcia(salida.getString("IDCIA"));
				c.setNamecia(salida.getString("NAMECIA"));
				c.setActivo(salida.getBoolean("ACTIVO"));
				c.setDir1(salida.getString("DIR1"));
				c.setTel(salida.getString("TEL"));
				
				cias.add(c);
			}
			
			salida.close();		
			hsqldb.close(con);
			
			
		}
		catch(Exception e) {
			System.out.println("Error al consultar la tabla cia");
			e.printStackTrace();
		}
		return cias;
		
	}
	

}
