package portal.org.dao;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.zkoss.zul.Messagebox;
import portal.org.dblink.MyBatisConnectionFactory;
import portal.org.pojo.Cias;



public class CiasDAO {
	
	private SqlSessionFactory sqlSessionFactory=null;

	public CiasDAO() {
		this.sqlSessionFactory=MyBatisConnectionFactory.getSqlSessionFactory();
		
	}
	

	public ArrayList<Cias>Nombre_Cias(){
		List<Cias>nombre_cias=null;
		SqlSession session=sqlSessionFactory.openSession();
		try {			
			//nombre_cias=(ArrayList<Cias>)session.selectList("Cias.v_cias");
			nombre_cias=session.selectList("Cias.v_cias");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			Messagebox.show("Error al obtener la lista de compañias :"+e,"WLS", Messagebox.OK, Messagebox.INFORMATION);
		}
		finally {
			session.close();
		}	
		System.out.println("Nombre_Cias()-->"+nombre_cias+"\n");
		return (ArrayList<Cias>) nombre_cias;
		
	}

}
