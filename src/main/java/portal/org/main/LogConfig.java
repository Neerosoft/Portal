package portal.org.main;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

public class LogConfig {

	
	public  LogConfig() {		
			@SuppressWarnings("unused")
			Properties  props=new Properties();
			try {			
				PropertyConfigurator.configure(this.getClass().getResource("/portal/org/info/log4j.properties"));
			}
			catch(Exception e) {
				System.out.println("Error al configurar el log");
			}		
			
	

	}
	
	
	
	

}


