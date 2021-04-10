/*package portal.org.vm;

import java.util.ArrayList;
import org.zkoss.zul.ListModel;
import portal.org.dao.FruitDAO;
import portal.org.pojo.Fruit;
import org.zkoss.zul.ListModelList;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class FruitFilterModel {
	public ArrayList<Fruit>f;
	private FruitDAO dao;
	private FruitFilter filter;
	private static final String footerMessage = "A Total of %d Food Items";
	
	public FruitFilterModel() {
		this.dao=new FruitDAO();
		
		this.f=dao.getFruit();	
		this.filter=new FruitFilter();
	}
	 public String getFooter() {
	        return String.format(footerMessage, f.size());
	    }
	
	public ListModel<Fruit>getFruitModel(){
		return new ListModelList<Fruit>(f);
	}

	public FruitFilter getFilter() {
		return filter;
	}

	public void setFilter(FruitFilter filter) {
		this.filter = filter;
	}
	 @Command
	    @NotifyChange({"fruitModel","footer"})
	    public void changeFilter() {
	        //currentFood = FoodData.getFilterFoods(foodFilter);
	        f=dao.getFilterFruits(filter);
	    }
	

}*/
