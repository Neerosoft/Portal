package portal.org.driver;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.SelectorParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;
import portal.org.session.WLSession;

public class Index extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Window winStart;
	private Borderlayout  portal;
	private North norte;
	private West menu;
	private South sur;
	private Tabbox tabs,tabsmenu;	
	private Center leaf;
	private Toolbar tb;
	private Toolbarbutton tbUsuario;
	private WLSession sesion=null;
	private HttpSession s=null;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this); 	
		
			
	}
	private void Setting() {
		this.sesion=new WLSession();	
		this.s=sesion.getSession();
		 if(sesion.UsuarioEnSession()==false) {
	        	Executions.createComponents("page/modal/login.zul",null,null);
	        }
	        else {
	        	this.norte.setVisible(true);
	        	tbUsuario.setTooltiptext("Cerrar sesion de "+s.getAttribute("nombre").toString()+" "+s.getAttribute("apellido").toString());
	        	
	        	
	        }
	}
	
	@AfterCompose 
  	public void init( @SelectorParam("#tabs") Tabbox tabs,@SelectorParam("#tabsmenu") Tabbox tabsmenu,
	    	@SelectorParam("#menu") West menu,
	    	@SelectorParam("#portal") Borderlayout portal,
	    	@SelectorParam("#tb")Toolbar tb,
	    	@SelectorParam("#tbUsuario")Toolbarbutton tbUsuario,
	    	@SelectorParam("#norte")North norte,
	    	@SelectorParam("#sur")South sur)throws UnsupportedEncodingException {	
		
		this.tabs = tabs;
        this.tabsmenu=tabsmenu;
        this.menu=menu;
        this.portal=portal;	
        this.tb=tb;
        this.tbUsuario=tbUsuario;
        this.norte=norte;
        this.sur=sur;
        
        this.Setting();
        
        tbUsuario.addEventListener("onClick", new EventListener<Event>() {
            @Override
            public void onEvent(Event t) throws Exception {
                Salir();
            }
        });
       
		
	}
	private void Salir() {
		Execution exe=Executions.getCurrent();			
		HttpSession session = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
		exe.sendRedirect("/");
		sesion.CerrarSesion();
		
	}
	
	
	/*@Listen("onCreate=#winStart")
	public void Login() {		  
	        Executions.createComponents("page/modal/login.zul",null,null);
		
	}*/
}
