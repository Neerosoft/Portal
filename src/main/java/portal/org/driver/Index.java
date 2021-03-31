package portal.org.driver;

import java.io.UnsupportedEncodingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;;

public class Index extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Button btnSaludar;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this); 	
			
	}
	
	@AfterCompose 
  	public void init()throws UnsupportedEncodingException {
	}
	
	@Listen("onClick=#btnSaludar")
	public void btnok_Click() {
	
	Messagebox.show("Copiado","Portal", Messagebox.OK, Messagebox.INFORMATION);	
		
	}

}
