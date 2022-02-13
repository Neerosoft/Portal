package portal.org.driver;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zhtml.A;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import portal.org.dao.LoginDAO;
import portal.org.dao.UsuarioDAO;
import portal.org.pojo.PLogin;
import portal.org.session.WLSession;
import portal.org.util.MD5;


//import portal.org.session.WLSession;

public class Login extends SelectorComposer<Component>  {

	private static final long serialVersionUID = 1L;
	@Wire
	private Window winlogin;
	
	@Wire
	private Button btnok;
	@Wire
	private Textbox txtUsuario,txtPws,txtEmail;
	@Wire
	private Hlayout hmail;
	
	private LoginDAO dao;
	private UsuarioDAO udao;
	public HashMap<Object, Object>param;
	public HashMap<Object, Object>sp;
	private MD5 md5;
	private int s;
	

	private WLSession sesion=null;
	
	public Login() {
		this.dao=new LoginDAO();
		this.udao=new UsuarioDAO();
		this.md5=new MD5();
		this.sesion=new WLSession();
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this); 	
		
			
	}
	@Listen("onClick=#aregistro")
	public void aregistro_Click() {
		this.winlogin.detach();
		Executions.createComponents("page/modal/Account.zul",null,null);
	}
	
	@AfterCompose 
  	public void init()throws UnsupportedEncodingException {
	}

	@Listen("onClick=#btnok")
	public void btnok_Click() {
		
		if(this.btnok.getLabel().equals("Enviar")) {
			
			if(this.txtUsuario.getValue().equals("")||this.txtPws.getValue().equals("")) {
				Messagebox.show("Disculpe!. Ingrese sus datos de usuario","RM", Messagebox.OK, Messagebox.INFORMATION);	
			}
			else {
				getUsuario();
			
			}
			
		}
		
		if(this.btnok.getLabel().equals("Crear")) {
			if(this.txtUsuario.getValue().equals("")||this.txtPws.getValue().equals("")||this.txtEmail.getValue().equals("")) {
				Messagebox.show("Disculpe!. Complete los datos de usuario","RM", Messagebox.OK, Messagebox.INFORMATION);	
				
			}
			else {
				SalvarUsuario();				
				
			}
			
			
			
		}
		
		
	
	}
	
	private void getUsuario() {
		ArrayList<PLogin>usuario=null;
		this.param=new HashMap<Object, Object>();
		
		boolean estatus=false;
		
		System.out.println(txtUsuario.getValue()+"   "+txtPws.getValue());
		param.put("usuario",this.txtUsuario.getValue().toUpperCase());
		param.put("pws",md5.EncriptarMD5(this.txtPws.getValue().toUpperCase()));
		
		usuario=(ArrayList<PLogin>) this.dao.doLogin(param);
		if(usuario.size()==0) {			
			this.s=Messagebox.show("El usuario especificado no existe\nDesea Crearlo?","RM",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION);
			CrearUsuario(s);
			
			
			
		}
		else {
			Execution exe=Executions.getCurrent();
			this.sesion.SalvarUsuarioEnSession(usuario);
			HttpSession session = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
    		exe.sendRedirect("/");
			
			
		}
	}
	
	private void SalvarUsuario() {
		boolean flag=false;
		this.sp=new HashMap<Object, Object>();
		this.sp.put("op","I");
		this.sp.put("pidusr","0");
		this.sp.put("pname",this.txtUsuario.getValue().toUpperCase());
		this.sp.put("pemail",this.txtEmail.getValue());
		this.sp.put("ppws",this.txtPws.getValue().toUpperCase());
		this.sp.put("pact","0");
		this.sp.put("pprfnm","query");
		
		flag=udao.SaveUsuario(sp);
		if(flag==true) {
			Messagebox.show("Felicidades!. Ha sido registrado con exitos\nEl equipo RMWS debe activar su cuenta para poder usar esta aplicación web",
					"RM", Messagebox.OK, Messagebox.INFORMATION);
			Execution exe=Executions.getCurrent();			
    		HttpSession session = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
    		exe.sendRedirect("/");
			
		}
		
	}
	
	private void CrearUsuario(int select) {		
		System.out.println("select: "+select);
		
		if(select==32) {
			Execution exe=Executions.getCurrent();			
    		HttpSession session = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
    		exe.sendRedirect("/");
			
		}
		if(select==16) {
			this.hmail.setVisible(true);
			this.btnok.setLabel("Crear");
			this.winlogin.setTitle("Crear Usuario");
			//Messagebox.show("Salvedad!. Favor ingrese su email","RM", Messagebox.OK, Messagebox.ERROR);
			
		

			
		}
		
		
	}
	


}
