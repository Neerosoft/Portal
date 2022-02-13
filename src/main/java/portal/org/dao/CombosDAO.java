package portal.org.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.zkoss.zul.Messagebox;
import portal.org.dblink.MyBatisConnectionFactory;
import portal.org.pojo.PCrr;
import portal.org.pojo.PDistrito;
import portal.org.pojo.PProvincia;

public class CombosDAO {
	private SqlSessionFactory sqlSessionFactory=null;

	public CombosDAO() {
		this.sqlSessionFactory=MyBatisConnectionFactory.getSqlSessionFactory();
		
	}
	
	public List<PProvincia> getProvincia(){		
		List<PProvincia>prov=null;
		
		SqlSession session=sqlSessionFactory.openSession();
		System.out.println(""+sqlSessionFactory.openSession());
		try {			
			prov=session.selectList("Combos.prov");
		}
		catch(Exception e) {
			e.printStackTrace();
			Messagebox.show("Error al obtener la lista de Provincias:"+e,"RM", Messagebox.OK, Messagebox.INFORMATION);
		}
		finally {
			session.close();
		}	
		System.out.println("getListaProvincias()-->"+prov+"\n");
		
		return prov;
		
	}
	
	public List<PDistrito> getDistrito(){	
		List<PDistrito>dst=null;
		
		SqlSession session=sqlSessionFactory.openSession();
		System.out.println(""+sqlSessionFactory.openSession());
		try {			
			dst=session.selectList("Combos.dst");
		}
		catch(Exception e) {
			e.printStackTrace();
			Messagebox.show("Error al obtener la lista de Distritos:"+e,"RM", Messagebox.OK, Messagebox.INFORMATION);
		}
		finally {
			session.close();
		}	
		System.out.println("getListaDistritos()-->"+dst+"\n");
		
		return dst;
		
	}
	
	public List<PCrr> getCrr(){	
		
		List<PCrr>crr=null;
		
		SqlSession session=sqlSessionFactory.openSession();
		System.out.println(""+sqlSessionFactory.openSession());
		
		try {			
			crr=session.selectList("Combos.crr");
		}
		catch(Exception e) {
			e.printStackTrace();
			Messagebox.show("Error al obtener la lista de Corregimientos:"+e,"RM", Messagebox.OK, Messagebox.INFORMATION);
		}
		finally {
			session.close();
		}	
		System.out.println("getListaCorregimientos()-->"+crr+"\n");
		
		return crr;
		
	}

}
