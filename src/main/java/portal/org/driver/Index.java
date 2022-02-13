package portal.org.driver;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.SelectorParam;
import org.zkoss.zhtml.Menu;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.LabelElement;
import org.zkoss.zul.Button;

import portal.org.session.WLSession;

public class Index extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Window winPadron;
	@Wire
	private Borderlayout  portal;
	@Wire
	private North norte;
	@Wire
	private Button btnSalir;
	private String name="";
	private String activo="";
	private String perfil="";
	
	
	
	private West menu;
	private South sur;
	@Wire
	private Tabbox tabs;	
	private Center leaf;	
	private Toolbarbutton tbUsuario;
	private WLSession sesion=null;
	private HttpSession s=null;
	private String link;
	private ArrayList<String>hojas=new ArrayList<String>();
	
	
	@AfterCompose 
  	public void init()throws UnsupportedEncodingException {		
 		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this);
		this.winPadron.setVisible(false);
		this.portal.setVisible(false);
		this.Setting();
			
	}
	private void Desviar() {
		Executions.createComponents("page/modal/NoActivo.zul",null,null);
		
	}

	
	private void Setting() {
		
		
		this.sesion=new WLSession();	
		this.s=sesion.getSession();
		 if(sesion.UsuarioEnSession()==false) {
	        	Executions.createComponents("page/modal/login.zul",null,null);
	      }
		 
	      else {
	    	  	name=sesion.getName();
	    	  	perfil=sesion.getPerfil();
      			activo=sesion.getAct();
      			System.out.println("activo: "+activo);
	        	
	        	if(activo.equals("0")) {
	        		Desviar();
	        	}
	        	else {	        		
	        		this.winPadron.setVisible(true);
	        		this.portal.setVisible(true);
	        		this.btnSalir.setLabel(name);       		
	        		
	        	}
	        	
	        	
	        	
	        }
	}
	@Listen("onClick=#btnSalir")
	public void aregistro_Click() {
		Salir();
	}
	
	@Listen("onClick=menuitem")
    public void menuAction(MouseEvent event){
		String codigo="";
		int code;
		
		codigo=((LabelElement)event.getTarget()).getId();
		System.out.println(codigo);
		code=Integer.parseInt(codigo);
		this.Dispacher(code);
		
	}
	
	private void Dispacher(int codigo) {
		String icono="faces/resources/pic/home.png";
		String page="";
		String alias="";
		
		switch(codigo){		
		case 1:{
			page="Electores";
			
			if(this.perfil.equals("super")||this.perfil.equals("admin")) {
				newpage(page,icono,page);
				break;
				
			}
			else {
				Messagebox.show("Usted no tiene los permisos para ejecutar este proceso","RM", Messagebox.OK, Messagebox.INFORMATION);
				break;
				
			}
			
			
		}
		case 2:{
			page="Buscar";
			
			newpage(page,icono,page);
			break;
		}
		case 3:{
			page="Usuarios";
			if(this.perfil.equals("admin")) {
				newpage(page,icono,page);
				break;
				
			}
			else {
				Messagebox.show("Usted no tiene los permisos para ejecutar este proceso","RM", Messagebox.OK, Messagebox.INFORMATION);
				break;
				
			}
		}
		case 4:{
			Messagebox.show("Propiedad de Eosxdx Technology\nTodos los derechos reservados","RM", Messagebox.OK, Messagebox.INFORMATION);
			break;
		}
		default:{
			Messagebox.show("Esta hoja web no existe","RM", Messagebox.OK, Messagebox.INFORMATION);
		}
			break;
		}
		
	}
	
	public void newpage(String arg1,String icono,String alias) {
		String hoja=arg1;
		String pic=icono;
		String nalias=alias;
		System.out.println("\nicono "+icono);

				this.hojas.add(hoja);
				this.add(hoja,pic,nalias);
			
	}
	
	public void add(String arg1,String arg2,String arg3) {
        link = null;
      
        try {
        	String dirhoja="page/"+arg1+".zul";
        	System.out.println("\ndirhoja: "+dirhoja+"\n");
        	Window elector=(Window)Executions.createComponents(dirhoja, null,null);
			elector.doModal();   
        }
        catch(Exception e) {
        	e.printStackTrace();
        }

    }
	
	public void refreshLink(String text) {
        link = text;
        BindUtils.postNotifyChange(null, null, this, "link");
    }
	
	private void Salir() {
		Execution exe=Executions.getCurrent();			
		HttpSession session = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
		exe.sendRedirect("/");
		sesion.CerrarSesion();
		
	}
	
	

}
