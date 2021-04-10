package portal.org.driver;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zhtml.Div;
import org.zkoss.zul.Label;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import portal.org.dao.FruitDAO;
import portal.org.pojo.Fruit;


public class Visor extends SelectorComposer<Component>{
	
	private static final long serialVersionUID = 1L;		
	@Wire
	private Grid gridfruits,gridtransac;
	@Wire
	private Window winvisor;
	@Wire
	private Button btnBuscar,btnProcesar,btnCancelar;
	@Wire
	private Combobox cbbCampo;
	@Wire
	private Textbox txtParam;
	@Wire
	private Hlayout hcontrol,hconf;
	
	public ArrayList<Fruit>f,f2;
	private FruitDAO dao;

	public Visor() {
		this.dao=new FruitDAO();
		this.f=new ArrayList<Fruit>();
		this.f2=new ArrayList<Fruit>();
	}
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this); 			
			
	}
	@AfterCompose 
  	public void init()throws UnsupportedEncodingException {
			
		
		
	}
	@Listen("onCreate=#gridfruits")
	public void CargarGrid() {
		this.LoadQuery("","");
	}
	@Listen("onClick=#btnCancelar")
	public void ReloadPage() {
		Execution exe=Executions.getCurrent();			
		HttpSession session = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
		exe.sendRedirect("/");
	}
	@Listen("onClick=#btnProcesar")
	public void btnProcesar_Click() {
		
		try {
			System.out.println("\nDATOS A ACTUALIZAR\n");
			List components = gridfruits.getRows().getChildren();			
			f2.clear();
			int indice;
			
			String category,name,nutrient;
			
		    for(Object obj:components){
		    	
		    	Row comp = (Row) obj;
		        Checkbox checkbox = (Checkbox)comp.getChildren().get(0);
		        
		        if(checkbox.isChecked()) {
		        	Fruit pojo=new Fruit();
		        	
		        	Label lblcategory=(Label)comp.getChildren().get(1);
		        	Label lblname=(Label)comp.getChildren().get(2);
		        	Label lblnutrient=(Label)comp.getChildren().get(3);
		        	
		        	indice=Integer.parseInt(""+checkbox.getValue());
		        	category=lblcategory.getValue();
		        	name=lblname.getValue();
		        	nutrient=lblnutrient.getValue();
		        	
		        	pojo.setIndex(indice);
		        	pojo.setCategory(category);
		        	pojo.setName(name);
		        	pojo.setNutrient(nutrient);
		        	
		        	f2.add(pojo);
		        	
		        	System.out.println(indice+"  "+category+"  "+name+" "+nutrient);
		        	}
		    	}		
			
		}
		catch (Exception e) {
			Messagebox.show("Error al efectuar la lectura","Portal", Messagebox.OK, Messagebox.ERROR);
			e.printStackTrace();
		}
		
		 if(f2.isEmpty()==true) {
			 Messagebox.show("Debe escoger al menos un registro","Portal", Messagebox.OK, Messagebox.ERROR);
		 }
		 else {
			 this.hcontrol.setVisible(false);
			 this.hconf.setVisible(true);
			 this.showNewGrid(f2);
		 }
  
	
		
	}
	
	@Listen("onClick=#btnBuscar")
	public void btnBuscar_Click() {
	
		if(this.txtParam.getValue().equals("")) {
			Messagebox.show("DISCULLPE! INGRESE EL DATO DE BUSQUEDA","Portal", Messagebox.OK, Messagebox.INFORMATION);
		}
		else {
		LoadQuery(this.cbbCampo.getValue(),this.txtParam.getValue());
			if(f.isEmpty()==false) {
				this.txtParam.setDisabled(true);					
				this.cbbCampo.setDisabled(true);
				this.btnBuscar.setDisabled(true);
				}
			}
		
		
	}
	
	private void showNewGrid(ArrayList<Fruit>data) {
		ListModelList<Fruit>modelo2=new ListModelList<Fruit>(data);
		this.gridtransac.setModel(modelo2);
		this.gridtransac.setVisible(true);
		this.gridfruits.setVisible(false);
	}
	
	private void LoadQuery(String campo,String param) {
		
		f.clear();
		this.f=dao.getFruit(campo,param);
		ListModelList<Fruit>modelo;
		if(f.isEmpty()) {
			Messagebox.show("NO SE ECONTRO REGISTROS","Portal", Messagebox.OK, Messagebox.INFORMATION);
			gridfruits.setVisible(false);
			gridfruits.setRenderdefer(5);			
			gridfruits.renderAll();
		}
		else {
			modelo=new ListModelList<Fruit>(f);
			this.gridfruits.setModel(modelo);
			this.gridfruits.setVisible(true);
		}
		
	}
	
	
	
	
	

}
