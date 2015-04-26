package ModStats;

import java.util.ArrayList;
import java.util.List;

public class InputObject {
	
	private String Name;	 
	private List<ValuesList> Data;

	
	/**
	 * 
	 */
	public InputObject(){
		Data = new ArrayList<ValuesList>();
	}
	
	/**
	 * 
	 * @param objectName
	 */
	public InputObject(String objectName){
		Name = objectName;
		Data = new ArrayList<ValuesList>();
	}
	
	
	/**
	 * Getters
	 * 
	 */		
	public ValuesList getValuesList(int index) {
		return Data.get(index);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return Name;
	}
	
	
	/**
	 * Setters
	 * 
	 */
	public void setListData(List<ValuesList> l){		
		
		for(ValuesList vl : l) {
			ValuesList vlTemp = new ValuesList();
			for(int j = 0; j < vl.size(); j++)
				vlTemp.add(vl.get(j));
			Data.add(vlTemp);
		}
		
	}
	

}
