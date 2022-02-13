package portal.org.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.zkoss.web.servlet.dsp.action.Out;
import org.zkoss.zul.Messagebox;
import portal.org.dblink.MyBatisConnectionFactory;
import portal.org.pojo.PData;

public class DataDAO {
	private SqlSessionFactory sqlSessionFactory=null;

	public DataDAO() {
		this.sqlSessionFactory=MyBatisConnectionFactory.getSqlSessionFactory();
		
	}
	public List<PData> getData(String campo,HashMap<Object,Object>mapa){	
		List<PData>data=null;
		System.out.println(campo);
		SqlSession session=sqlSessionFactory.openSession();
		System.out.println(""+sqlSessionFactory.openSession());
		
		try {		
			if(campo.equals("*")){
				data=session.selectList("Combos.data",mapa);
				
			}
			if(campo.equals("Nombre"))  {
				System.out.println(mapa.get("param"));
				data=session.selectList("Combos.byNombre",mapa);
				
			}
			if(campo.equals("Cedula")) {
				data=session.selectList("Combos.byCedula",mapa);
				
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			Messagebox.show("Error al leer los datos"+e,"RM", Messagebox.OK, Messagebox.INFORMATION);
		}
		finally {
			session.close();
		}	
		System.out.println("getListaDatos()-->"+data+"\n");
		
		return data;
		
	}

}
