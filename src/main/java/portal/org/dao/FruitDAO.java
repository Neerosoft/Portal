package portal.org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import portal.org.connections.HSQLDBConnections;
import portal.org.pojo.Fruit;
import portal.org.vm.FruitFilter;

public class FruitDAO {
	private HSQLDBConnections hsqldb=null;

	public FruitDAO() {
		this.hsqldb=new HSQLDBConnections();
	}
	public ArrayList<Fruit>getFruit(String campo, String param){
		ArrayList<Fruit>frutas=new ArrayList<Fruit>();
		String sqlCommander1="select * from V_FRUIT";
		String sqlCommander2="select * from V_FRUIT where "+campo+" like ?";
		
		Connection con=null;
		Statement orden=null;
		PreparedStatement ps=null;
		ResultSet salida=null;		
		int index=1;
		
		if(campo.equals("")) {
		
				try {
						con=hsqldb.getConnection();			
						orden=con.createStatement();
						salida=orden.executeQuery(sqlCommander1);
						while(salida.next()) {
				
						Fruit f=new Fruit();
						f.setIndex(index);
						f.setCategory(salida.getString("CATEGORY"));
						f.setName(salida.getString("NAME"));
						f.setNutrient(salida.getString("NUTRIENT"));
				
						frutas.add(f);
						index++;
						}
					salida.close();		
					hsqldb.close(con);
			
		} 
				catch (Exception e) {
					System.out.println("Error al consultar la vista frutas");
					e.printStackTrace();
		}
		
		}
		else {

			try {
					con=hsqldb.getConnection();			
					ps=con.prepareStatement(sqlCommander2);
					ps.setString(1,param+"%");
					salida=ps.executeQuery();
					
					while(salida.next()) {
			
					Fruit f=new Fruit();
					f.setIndex(index);
					f.setCategory(salida.getString("CATEGORY"));
					f.setName(salida.getString("NAME"));
					f.setNutrient(salida.getString("NUTRIENT"));
			
					frutas.add(f);
					index++;
					}
				salida.close();		
				hsqldb.close(con);
		
	} 
			catch (Exception e) {
				System.out.println("Error al consultar la vista frutas");
				e.printStackTrace();
	}
			
		}
		return frutas;
		
	}
	
	
	
   /* public  ArrayList<Fruit> getFilterFruits(FruitFilter foodFilter) {
		ArrayList<Fruit>fruit=getFruit();
		ArrayList<Fruit> somefoods = new ArrayList<Fruit>();
        String cat = foodFilter.getCategory().toLowerCase();
        String nm = foodFilter.getName().toLowerCase();
        String nut = foodFilter.getNutrients().toLowerCase();
         
        for (Iterator<Fruit> i = fruit.iterator(); i.hasNext();) {
            Fruit tmp = i.next();
            if (tmp.getCategory().toLowerCase().contains(cat) &&
                tmp.getName().toLowerCase().contains(nm)  &&
                tmp.getNutrient().toLowerCase().contains(nut)) {
                somefoods.add(tmp);
            }
        }
        return somefoods;
    }
    
 // This Method only used in "Header and footer" Demo
    public ArrayList<Fruit> getFoodsByCategory(String category) {
    	ArrayList<Fruit>fruit=getFruit();
    	ArrayList<Fruit> somefoods = new ArrayList<Fruit>();
        for (Iterator<Fruit > i = fruit.iterator(); i.hasNext();) {
        	Fruit tmp = i.next();
            if (tmp.getCategory().equalsIgnoreCase(category)){
                somefoods.add(tmp);
            }
        }
        return somefoods;
    }*/

}
