package portal.org.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

public class Filtro implements Filter{


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req=null;
		HttpServletResponse res=null;
		//HttpSession sesion;
	
		try {
		
		
		req=(HttpServletRequest)request;
		res=(HttpServletResponse)response;
		//sesion=req.getSession();
	
			
			System.out.println("la url    "+req.getRequestURL());
			res.sendRedirect("/Portal");
			
			
		
	
		chain.doFilter(request, response);
		}
		catch(Exception e) {
		//	e.printStackTrace();
			System.out.println("**Error a causa de que no hay nadie en session***");
			System.out.println("la url    "+req.getRequestURL()+"\n"+req.getContextPath());
			res.sendRedirect(req.getContextPath());
		
		}
		
		
		
		
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}



}
