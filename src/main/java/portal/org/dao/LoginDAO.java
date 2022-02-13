package portal.org.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.zkoss.zul.Messagebox;
import portal.org.dblink.MyBatisConnectionFactory;
import portal.org.pojo.PLogin;



public class LoginDAO {
	private SqlSessionFactory sqlSessionFactory=null;

	public LoginDAO() {
		this.sqlSessionFactory=MyBatisConnectionFactory.getSqlSessionFactory();
	}
	public List<PLogin>doLogin(HashMap<Object,Object>mapa){
		List<PLogin>usr=null;
		SqlSession session=sqlSessionFactory.openSession();
	
		try {			
			usr=session.selectList("Combos.login",mapa);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			Messagebox.show("Error.No fue posible ejecutar la operación :"+e,"RM", Messagebox.OK, Messagebox.INFORMATION);
			e.printStackTrace();
		}
		finally {
			session.close();
		}	
		System.out.println("usuario-->"+usr+"\n");
		
		return usr;
		
	}

}
