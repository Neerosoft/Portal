package portal.org.session;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import org.zkoss.zk.ui.Executions;

import portal.org.pojo.PLogin;




public class WLSession {
	
	private HttpSession objsesion=null;
	private HttpSession session;
	private String idusr;
	private String name;
	private String email;
	private String prf;
	private String act;
	private String perfil;
	
	
	
	public WLSession() {
		
	}

	public String getIdusr() {
		HttpSession session;
		session=this.getSession();
		return session.getAttribute("idusr").toString();
	}
	public String getName() {
		HttpSession session;
		session=this.getSession();
		return session.getAttribute("name").toString();
	}
	public String getEmail() {
		HttpSession session;
		session=this.getSession();
		return session.getAttribute("email").toString();
	}
	public String getPrf() {
		HttpSession session;
		session=this.getSession();
		return session.getAttribute("prf").toString();
	}
	public String getAct() {
		HttpSession session;
		session=this.getSession();
		return session.getAttribute("act").toString();
	}
	public HttpSession getObjsesion() {
		return objsesion;
	}
	public void setObjsesion(HttpSession objsesion) {
		this.objsesion = objsesion;
	}
	
	
	
	public String getPerfil() {
		HttpSession session;
		session=this.getSession();
		return session.getAttribute("perfil").toString();
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
	
	    
	public boolean SalvarUsuarioEnSession(ArrayList<PLogin>mapa) {
		boolean flag=false;
		try {	
		session=this.getSession();
		session.setAttribute("idusr",mapa.get(0).getIdprf());
		session.setAttribute("name",mapa.get(0).getName());
		session.setAttribute("email",mapa.get(0).getEmail());
		session.setAttribute("prf",mapa.get(0).getIdprf());
		session.setAttribute("act",mapa.get(0).isAct());
		session.setAttribute("perfil",mapa.get(0).getPrfnm());
		
			
		flag=true;
		System.out.println("\n-->Datos Salvados en session\n");
		}
		catch(Exception e) {
			System.out.println("Error en Salvar los datos de Usuarios en Session "+e);
			e.printStackTrace();
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
			usuario=session.getAttribute("name").toString();
		    System.out.println("usuario en sesion  "+usuario);
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
