package portal.org.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.zkoss.zul.Messagebox;

import portal.org.dblink.MyBatisConnectionFactory;
import portal.org.pojo.PUsuarios;


public class UsuarioDAO {
	private SqlSessionFactory sqlSessionFactory=null;

	public UsuarioDAO() {
		this.sqlSessionFactory=MyBatisConnectionFactory.getSqlSessionFactory();
	}
	public int getIDperfil(String mapa) {
		
		int idperfil=-1;
		SqlSession session=sqlSessionFactory.openSession();
		try {			
			idperfil=session.selectOne("Combos.idperfil",mapa);				
		}
		catch(Exception e) {
		
			e.printStackTrace();
			Messagebox.show("Error.No fue posible obtener el id del perfil"+e,"RM", Messagebox.OK, Messagebox.INFORMATION);
			e.printStackTrace();
		}
		finally {
			session.close();
		}	
		
		
		return idperfil;
	}
	
	public boolean UpdateStatus(HashMap<Object,Object>mapa) {
		boolean flag=false;
		SqlSession session=sqlSessionFactory.openSession();
		
		try {			
			session.selectList("Combos.updateStatus",mapa);
			flag=true;
			
		}
		catch(Exception e) {
			flag=false;
			e.printStackTrace();
			Messagebox.show("Error.No fue posible actualizar el status del usuario:"+e,"RM", Messagebox.OK, Messagebox.INFORMATION);
			e.printStackTrace();
		}
		finally {
			session.close();
		}	
		System.out.println("Status de Usuario actualizado");
		
		return flag;
		
	}
	
	public boolean UpdatePerfil(HashMap<Object,Object>mapa) {
		boolean flag=false;
		SqlSession session=sqlSessionFactory.openSession();
		
		try {			
			session.selectList("Combos.updatePerfil",mapa);
			flag=true;
			
		}
		catch(Exception e) {
			flag=false;
			e.printStackTrace();
			Messagebox.show("Error.No fue posible actualizar el status del usuario:"+e,"RM", Messagebox.OK, Messagebox.INFORMATION);
			e.printStackTrace();
		}
		finally {
			session.close();
		}	
		System.out.println("Perfil de Usuario actualizado");
		
		return flag;
		
	}
	
	public List<PUsuarios>getListaUsuarios(){
		List<PUsuarios>lstUsuarios=null;
		SqlSession session=sqlSessionFactory.openSession();

		try {			
			lstUsuarios=session.selectList("Combos.usuarios");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			Messagebox.show("Error.No fue obtener la Lista de Usuarios :"+e,"RM", Messagebox.OK, Messagebox.INFORMATION);
			e.printStackTrace();
		}
		finally {
			session.close();
		}	
		System.out.println("Lista de Usuarios-->"+lstUsuarios+"\n");
		return lstUsuarios;
	}
	
	public boolean SaveUsuario(HashMap<Object,Object>mapa) {
		SqlSession session=sqlSessionFactory.openSession();
		System.out.println(""+sqlSessionFactory.openSession());
		boolean flag=false;
		
		System.out.println("----------Nuevo Usuario----\n\n");	
		System.out.print("call rm.sp_usuarios(");
		System.out.print("'"+mapa.get("op")+"',");
		System.out.print("'"+mapa.get("pidusr")+"',");
		System.out.print("'"+mapa.get("pname")+"',");
		System.out.print("'"+mapa.get("pemail")+"',");
		System.out.print("'"+mapa.get("ppws")+"',");
		System.out.print("'"+mapa.get("pact")+"',");
		System.out.println("'"+mapa.get("pprfnm")+"');\n\n");
		
		
		try {			
			session.selectList("Combos.crear",mapa);
			flag=true;
		}
		catch(Exception e) {
			e.printStackTrace();
			Messagebox.show("Error.No fue posible ejecutar crear el usuario\n* Escoja otro nombre de Usuario\n* Escoja otro correo\n"
					+ "Si el problema persiste contactese con programación urgente\n "+e,
					"RM", Messagebox.OK, Messagebox.INFORMATION);
			e.printStackTrace();
			flag=false;
		}
		finally {
			session.close();
		}			
		
		return flag;
		
	}

}
