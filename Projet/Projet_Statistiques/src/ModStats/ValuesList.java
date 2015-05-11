/**
 * 
 */
package ModStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author admaay
 *
 */
public class ValuesList {
	
	private List<Double> listOfValues;
	
	/**
	 * 
	 */
	public ValuesList(){
		listOfValues = new ArrayList<Double>();
	}
	
	
	/**
	 * add an element on the list
	 * @param x
	 * @return
	 */
	public boolean add(Double x){
		return listOfValues.add(x);
	}
	
	/**
	 * clear the list
	 */
	public void clear(){
		listOfValues.clear();
	}
	
	/**
	 * get the element i on the list
	 * @param i
	 * @return
	 */
	public Double get(int i) {
		return listOfValues.get(i);
	}
	
	/**
	 * 
	 * @return the size of the list
	 */
	public int size() {
		return listOfValues.size();
	} 
	
	/**
	 * set the element i 
	 * @param i
	 * @param element
	 */
	public void set(int i, Double element) {
		listOfValues.set(i, element);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Double> sortValues() {
		Collections.sort(listOfValues);
		return listOfValues;
	}
	
	public int frequency(double arg1){
		return Collections.frequency(listOfValues, arg1);
		
	}
	
	public boolean contains(double arg){
		return listOfValues.contains(arg);
	}
	
	/**
	 * 
	 * @return
	 */
	public double minValue() {
		return Collections.min(listOfValues);	
	}
	
	/**
	 * 
	 * @return
	 */
	public double maxValue() {
		return Collections.max(listOfValues);	
	}
}
