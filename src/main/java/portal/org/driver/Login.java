package portal.org.driver;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import portal.org.pojo.V_Login;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import portal.org.dao.CiasDAO;
import portal.org.dao.Access;
import portal.org.pojo.Cias;
import portal.org.session.WLSession;

public class Login extends SelectorComposer<Component>  {

	private static final long serialVersionUID = 1L;
	@Wire
	private Window winlogin;
	@Wire
	private Combobox cbbcia;
	@Wire
	private Button btnok;
	@Wire
	private Textbox txtUsuario,txtPws;
	
	private CiasDAO dao;
	private Access ac;
	private WLSession sesion=null;
	
	public Login() {
		this.dao=new CiasDAO();
		this.ac=new Access();
		this.sesion=new WLSession();
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this); 	
		this.LoadCias();
			
	}
	
	@AfterCompose 
  	public void init()throws UnsupportedEncodingException {
	}
	
//	@Listen("onCreate=#winlogin")
	private void LoadCias() {
		
		ArrayList<Cias>cias=null;		
		try {
			cias=dao.getAllCias();
			this.cbbcia.getItems().clear();
			for(Cias c:cias) {
				this.cbbcia.appendItem(c.getNamecia());
				}
			this.cbbcia.setValue(cias.get(0).getNamecia());	
			}
		
		catch(Exception e) {
			System.out.println("Error al cargar la lista de compañias "+e);
			e.printStackTrace();
			
		}
	
	}
	
	@Listen("onClick=#btnok")
	public void btnok_Click() {
		if(this.txtUsuario.getValue().equals("")||this.txtPws.getValue().equals("")||this.cbbcia.getValue().equals("")) {
			Messagebox.show("Disculpe!. Ingrese sus datos de usuario WLS","WLS", Messagebox.OK, Messagebox.INFORMATION);	
		}
		else {
			getUsuario();
			
		}
		
	}
	private void getUsuario() {
		ArrayList<V_Login>usuario=null;
		boolean estatus=false;
		
		System.out.println(txtUsuario.getValue()+"   "+txtPws.getValue()+"   "+cbbcia.getText());
		usuario=this.ac.doLogin(this.txtUsuario.getValue(),this.txtPws.getValue(), this.cbbcia.getText());
		if(usuario.size()==0) {
			Messagebox.show("Disculpe!. El usuario indicado no existe.\nVerifique sus datos y vuelva a intentar","WLS", Messagebox.OK, Messagebox.ERROR);	
			
		}
		else {
			estatus=sesion.SalvarUsuarioEnSession(usuario);
			if(estatus==true) {
				Messagebox.show("Bienvenido "+usuario.get(0).getNombre()+"  "+usuario.get(0).getApellido(),"WLS", Messagebox.OK, Messagebox.INFORMATION);
				this.winlogin.detach();
				
				Execution exe=Executions.getCurrent();			
	    		HttpSession session = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
	    		exe.sendRedirect("/");
			}
			else {
				Messagebox.show("Disculpe!. Error al momento de obtner la session\nRemita a Sistemas","WLS", Messagebox.OK, Messagebox.ERROR);
			}
			
		}
		
		
		
		
	}

}
