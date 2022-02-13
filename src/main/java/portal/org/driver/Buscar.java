package portal.org.driver;

import java.util.ArrayList;
import java.util.HashMap;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import portal.org.dao.DataDAO;
import portal.org.pojo.PData;

public class Buscar extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	@Wire
	private Grid griddata;
	@Wire
	private Button btnBuscar;
	@Wire
	private Textbox txtParam;
	@Wire
	private Combobox cbbCampo;
	
	public ArrayList<PData>data;
	public HashMap<Object, Object>param;
	private DataDAO ddao;

	public Buscar() {
		this.ddao=new DataDAO();

	}
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this); 	
		
			
	}
	
	@Listen("onClick=#btnBuscar")
	public void btnok_Click() {
		if(txtParam.getValue()==""||txtParam.getValue()==null) {
			Messagebox.show("Faltan Datos","RM", Messagebox.OK, Messagebox.INFORMATION);
		}
		else {
			this.param=new HashMap<Object, Object>();	
			this.param.put("campo",cbbCampo.getValue());
			if(cbbCampo.getValue().equals("Nombre")) {
				this.param.put("param","%"+txtParam.getValue().toLowerCase()+"%");
			}
			else {
				this.param.put("param",txtParam.getValue());
			}
			
			System.out.println(cbbCampo.getValue());
			this.data=(ArrayList<PData>) ddao.getData(cbbCampo.getValue(),param);		
			this.ConfigGrid();
		}

		
	}
	private void ConfigGrid() {
		ListModelList<PData> registros=new ListModelList<PData>(data);
		griddata.setVisible(true);
		griddata.setRenderdefer(5);
		griddata.setModel(registros);
		griddata.renderAll();
		
	}

}
