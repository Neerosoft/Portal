package portal.org.driver;

import java.util.ArrayList;
import java.util.HashMap;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import portal.org.dao.CombosDAO;
import portal.org.dao.DataDAO;
import portal.org.pojo.PCrr;
import portal.org.pojo.PData;
import portal.org.pojo.PDistrito;
import portal.org.pojo.PProvincia;

public class Electores extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	private CombosDAO pdao;
	private DataDAO ddao;
	
	private ArrayList<PProvincia>prov;
	private ArrayList<PDistrito>dst;
	private ArrayList<PCrr>crr;
	public ArrayList<PData>data;
	
	public HashMap<Object, Object>param;
	
	@Wire
	private Window winelectores;
	@Wire
	private Combobox cbbprov,cbbdst,cbbcrr;
	@Wire
	private Button btnBuscar;
	@Wire
	private Grid griddata;
	private String aux;



	public Electores() {
		this.pdao=new CombosDAO();
		this.ddao=new DataDAO();
		
		this.prov=(ArrayList<PProvincia>) pdao.getProvincia();
		this.dst=(ArrayList<PDistrito>) pdao.getDistrito();
		this.crr=(ArrayList<PCrr>) pdao.getCrr();
		
	}
	
	@Listen("onClick=#btnBuscar")
	public void btnok_Click() {
		String dato;
		this.param=new HashMap<Object, Object>();
		this.param.put("campo","*");
		this.param.put("crr",cbbcrr.getValue());
		this.data=(ArrayList<PData>) ddao.getData("*",param);		
		this.ConfigGrid();
		//Messagebox.show(""+dato,"RM", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	private void ConfigGrid() {
	
		ListModelList<PData> registros=new ListModelList<PData>(data);
		griddata.setVisible(true);
		griddata.setRenderdefer(5);
		griddata.setModel(registros);
		griddata.renderAll();
		
		winelectores.setTitle("");
		winelectores.setTitle(data.size()+" Personas en "+this.cbbcrr.getValue());
	
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this); 	
		this.ConfigurarCombos();
			
	}
	private void ConfigurarCombos() {
		//config Provincias
		this.cbbprov.getItems().clear();
		this.cbbcrr.setAttribute("style", "font-size:8pt");
		for(PProvincia p:prov) {
			this.cbbprov.appendItem(p.getProvincia());
		}
		this.cbbprov.setValue(prov.get(0).getProvincia().toString());
		
		//config distritos
		this.cbbdst.getItems().clear();
		for(PDistrito p:dst) {
			this.cbbdst.appendItem(p.getDistrito());
		}
		this.cbbdst.setValue(dst.get(0).getDistrito().toString());
		
		//config corregimientos
		this.cbbcrr.getItems().clear();
		for(PCrr p:crr) {
			this.cbbcrr.appendItem(p.getCorregimiento());
		}
		this.cbbcrr.setValue(crr.get(0).getCorregimiento().toString());
		
		
		
	}

}
