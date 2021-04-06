/**
 * 
 */
package portal.org.dblink;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import portal.org.dao.CiasDAO;
import portal.org.main.LogConfig;

/**
 * @author eosxdx
 *
 */
public class MyBatisConnectionFactory {
private final static Logger log = Logger.getLogger(CiasDAO.class);
	
private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {			
			new LogConfig();
			String resource="portal/org/dblink/config.xml";
			log.warn(resource);
			Reader reader=Resources.getResourceAsReader(resource);
			log.warn("reader esta "+reader.ready());
			
			if(sqlSessionFactory==null) {
				sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
			}
			
	}
		catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
		
	}

}
