package portal.org.driver;

import java.io.UnsupportedEncodingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;

import org.zkoss.zul.Window;;

public class Index extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Window winStart;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this); 	
		
			
	}
	
	@AfterCompose 
  	public void init()throws UnsupportedEncodingException {
	}
	
	
	@Listen("onCreate=#winStart")
	public void Login() {		  
	        Executions.createComponents("page/modal/login.zul",null,null);
		
	}
}
