package portal.org.driver;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Window;
import portal.org.dao.UsuarioDAO;
import portal.org.pojo.PData;
import portal.org.pojo.PUsuarios;


public class Usuarios extends SelectorComposer<Component>  {
	
	@Wire
	private Window winUsrs;
	@Wire
	private Grid griddata;
	@Wire
	private Button btnLoad;
	private UsuarioDAO dao;
	private ArrayList<PUsuarios>data=null;
	public HashMap<Object, Object>param;
	public HashMap<Object, Object>param2;
	private int indice_registro=-1;
	private int s;


	private static final long serialVersionUID = 1L;

	public Usuarios() {	
		
	}
	
	@AfterCompose 
  	public void init()throws UnsupportedEncodingException {
	
	}
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this); 
		this.dao=new UsuarioDAO();
		for (Component row : griddata.getRows().getChildren()) {
            row.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
 
                @Override
                public void onEvent(Event arg0) throws Exception {
                    Row row = (Row) arg0.getTarget();
                  //  Boolean rowSelected = (Boolean) row.getAttribute("Selected");             
                      //  row.setAttribute("Selected", false);                     
                        indice_registro=row.getIndex();
                        
                        
                
                }
            });
        }
		
			
	}
	@Listen("onClick=#btnLoad")
	public void aregistro_Click() {
		LoadUsuarios();
	}
	
	
	public void LoadUsuarios() {
		this.data=(ArrayList<PUsuarios>) dao.getListaUsuarios();
		ListModelList<PUsuarios> registros=new ListModelList<PUsuarios>(data);
		griddata.setVisible(true);
		griddata.setRenderdefer(5);
		griddata.setModel(registros);
		griddata.setModel(registros);
		griddata.renderAll();
		
		
	}
	
	
	@Listen("onUpdate=#winUsrs")
	public void onClickUpdate(ForwardEvent event) {
		this.param2=new HashMap<Object, Object>();
		boolean flag=false;
		String prfnm;
		String idusr="";
		int idprf=-1;
		
		if(indice_registro==-1) {
			Messagebox.show("Cambie el perfil de al menos un usuario","RM",Messagebox.OK, Messagebox.INFORMATION);
		}
		else {
			this.s=Messagebox.show("Desea actualizar el usuario?","RM",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION);
			if(s==16) {
				
			
			
			List components = griddata.getRows().getChildren();		
			Object obj=components.get(indice_registro);		
					 Row comp = (Row) obj;
					 Label label1=(Label)comp.getChildren().get(0);
					 idusr=label1.getValue();
					 Combobox cbbprf=(Combobox)comp.getChildren().get(4);					 
					 prfnm=cbbprf.getValue().toString();				 
					 idprf=dao.getIDperfil(prfnm);		
					 
					 param2.put("idusr", idusr);
					 param2.put("idprf", idprf);
					 
					 flag=dao.UpdatePerfil(param2);
					 if(flag==true) {
						 Messagebox.show("Perfil de Usuario Actualizado","RM",Messagebox.OK, Messagebox.INFORMATION);
						 LoadUsuarios();
					 }
					 else {
						 Messagebox.show("No se pudo  Actualizado el Perfil del Usuario","RM",Messagebox.OK, Messagebox.INFORMATION);
					 }		
				}
			else {;}

			
		}
		
		
		indice_registro=-1;
	}
	
	@Listen("onComprar=#winUsrs")
	public void onClick(ForwardEvent event) {
		this.param=new HashMap<Object, Object>();
		boolean flag=false;
		String idusr="";
		String act="";
		this.s=Messagebox.show("Desea actualizar el usuario?","RM",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION);
		if(s==16) {		
		String id = ((ForwardEvent) event).getOrigin().getTarget().getId();		
			if(id.contains("@")) {
				idusr=id.replace("@","");			
				param.put("act","0");
				param.put("idusr", idusr);
			
				}
			else {
				idusr=id;
				param.put("act","1");
				param.put("idusr", idusr);
		
			}
		flag=dao.UpdateStatus(param);
		
				if(flag==true) {
						Messagebox.show("El usuario ha sido actualizado","RM", Messagebox.OK, Messagebox.INFORMATION);
						LoadUsuarios();
				}
				else {
						Messagebox.show("Error! El usuario no ha podido ser actualizado","RM", Messagebox.OK, Messagebox.INFORMATION);
				}
		
		}
		else {;}
	}

}
