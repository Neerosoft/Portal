package portal.org.driver;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import portal.org.dao.CiasDAO;
import portal.org.pojo.Cias;


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
	
	private CiasDAO dao=new CiasDAO();
	
	public Login() {
		
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
			cias=dao.Nombre_Cias();
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
			Messagebox.show("webapp esta en proceso de construccion","WLS", Messagebox.OK, Messagebox.INFORMATION);
			
		}
		
	}

}
