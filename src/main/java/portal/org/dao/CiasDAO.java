package portal.org.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.zkoss.zul.Messagebox;
import portal.org.dblink.MyBatisConnectionFactory;
import portal.org.pojo.Cias;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;



public class CiasDAO {
	private final static Logger log = Logger.getLogger(CiasDAO.class);
	private SqlSessionFactory sqlSessionFactory=null;

	public CiasDAO() {
		Properties props = new Properties();
		try {		
			props.load(getClass().getResourceAsStream("/portal/org/info/log4j.properties"));
		}
		catch(Exception e) {
			System.out.println("Error al configurar el log");
		}
		
		PropertyConfigurator.configure(props);
		log.warn("un warning");
		log.error("un error");
		this.sqlSessionFactory=MyBatisConnectionFactory.getSqlSessionFactory();
		
	}
	

	public ArrayList<Cias>Nombre_Cias(){
		List<Cias>nombre_cias=new ArrayList<Cias>() ;
		nombre_cias.clear();
		SqlSession session=sqlSessionFactory.openSession();
		log.warn("Se obtiene la session");
		try {			
			//nombre_cias=(ArrayList<Cias>)session.selectList("Cias.v_cias");
			nombre_cias=session.selectList("Cias.v_cias");
			log.warn("se ejecuta la consulta");
		}
		catch(Exception e) {
			e.printStackTrace();
			Messagebox.show("Error al obtener la lista de compañias :"+e,"WLS", Messagebox.OK, Messagebox.INFORMATION);
		}
		finally {
			session.close();
		}	
		System.out.println("Nombre_Cias()-->"+nombre_cias+"\n");
		log.warn("Nombre_Cias()-->"+nombre_cias+"\n");
		return (ArrayList<Cias>) nombre_cias;
		
	}

}
