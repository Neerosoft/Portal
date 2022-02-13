package portal.org.driver;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;

import portal.org.session.WLSession;

public class NoActivo extends SelectorComposer<Component> {
	
	@Wire
	private Button btnOk;
	private WLSession sesion=null;
	private HttpSession s=null;

	public NoActivo() {
		// TODO Auto-generated constructor stub
	}
	@AfterCompose 
  	public void init()throws UnsupportedEncodingException {		
 		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this);
		this.sesion=new WLSession();	
		this.s=sesion.getSession();
			
	}
	@Listen("onClick=#btnOk")
	public void aregistro_Click() {
		Execution exe=Executions.getCurrent();			
		HttpSession session = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
		exe.sendRedirect("/");
		sesion.CerrarSesion();
	}

}
