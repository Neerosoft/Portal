package system.x.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import system.x.connections.HSQLDBConnections;
import system.x.pojo.V_Login;


public class Access {
	
	private HSQLDBConnections hsql;

	public Access() {
		this.hsql=new HSQLDBConnections();
	
	}
	public ArrayList<V_Login>doLogin(String usr,String pws){
		ArrayList<V_Login>usuario=new ArrayList<V_Login>();
		usuario.clear();
		String sqlCommander="select * from v_login where usuario=? and pws=? and activo=true";
		Connection con=null;	
		PreparedStatement ps=null;
		ResultSet salida=null;
		try {
			con=hsql.getConnection();
			ps=con.prepareStatement(sqlCommander);
			ps.setString(1,usr);
			ps.setString(2,pws);
			
			
			salida=ps.executeQuery();
			
			while(salida.next()) {
					V_Login data=new V_Login();
					data.setIdusr(salida.getInt("IDUSR"));				
					data.setUsuario(salida.getString("USUARIO"));
					data.setPws(salida.getString("PWS"));
					data.setNombre(salida.getString("NOMBRE"));
					data.setApellido(salida.getString("APELLIDO"));
					data.setActivo(salida.getString("ACTIVO"));
					
					usuario.add(data);
			}
			salida.close();
			ps.close();
			hsql.close(con);
			
		} 
		catch (Exception e) {
			usuario=null;
			System.out.println("Error en doLogin  "+e);
			e.printStackTrace();

		}
		
		return usuario;
		
	}
	
	
	

}
