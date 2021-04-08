package portal.org.session;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;

import portal.org.pojo.V_Login;



public class WLSession {
	
	private HttpSession objsesion=null;

	public WLSession() {

	}
	public HttpSession getSession() {
		try {
		this.objsesion = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
		}
		catch(Exception e) {
			System.out.println("Error en portal.org.session.getSession()");
			e.printStackTrace();			
		}
		return objsesion;
		
	}
	
	    
	public boolean SalvarUsuarioEnSession(ArrayList<V_Login>d) {
		boolean flag=false;
		try {
		
		HttpSession session=this.getSession();
		
		session.setAttribute("idusr",d.get(0).getIdusr());
		session.setAttribute("namecia",d.get(0).getNamecia());
		session.setAttribute("usuario",d.get(0).getUsuario());
		session.setAttribute("pws",d.get(0).getPws());
		session.setAttribute("nombre",d.get(0).getNombre());
		session.setAttribute("apellido",d.get(0).getApellido());
		session.setAttribute("activo",d.get(0).getActivo());
	
		
		flag=true;
		System.out.println("\n-->Datos Salvados en session\n");
		}
		catch(Exception e) {
			System.out.println("Error en Salvar los datos de Usuarios en Session ");
			flag=false;
			
		}
		return flag;
		
	}
	
	public boolean CerrarSesion() {
		boolean flag=false;
		try {
		HttpSession session=this.getSession();
		session.invalidate();
		System.out.println("\n-->Sesion Cerrada con exito\n");
		
		
		
		
		}
		catch(Exception e) {
			System.out.println("Error al invalidar la session ");
			e.printStackTrace();
		}
		return flag;
		
	}
	public boolean UsuarioEnSession() {
		boolean bandera=false;
		HttpSession session;
		String usuario="";
		try {
			session=this.getSession();
			usuario=session.getAttribute("usuario").toString();
			System.out.println("usuario e sesion  "+usuario);
			if(usuario.isEmpty()||usuario.equals("")||usuario.equals(null)) {
				bandera=false;
			}
			else {
				bandera=true;
			}
			
		}
		catch(Exception e) {
			System.out.println("No hay usuarios en session");
			bandera=false;
		}
		return bandera;
		
	}
		

}
