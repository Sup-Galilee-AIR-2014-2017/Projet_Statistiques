package APP_Main;

import java.util.ArrayList;

import AppInterface.InterfaceApp;
import ModStats.DoCalculs;
/**
 * 
 */

/**
 * @author admaay
 *
 */
public class STATISTICAL_APP {

	/**
	 * @param args
	 */
	static InterfaceApp app = new InterfaceApp();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DaviesBouldinTests();
		
		//crée
		boolean home = true;
		
		app.GraphicalInterface(home);
	}
	
	public static InterfaceApp getIntApp() {
		return app;
	}

	//TODO TEMPORAIRE, A SUPPRIMER ET EVENTUELLEMENT METTRE EN PLACE DES TESTS UNITAIRES
	private static void DaviesBouldinTests() {
		ArrayList<ArrayList<Double>> repartition = new ArrayList<ArrayList<Double>>();
		repartition.add(new ArrayList<Double>());
		repartition.add(new ArrayList<Double>());
		repartition.add(new ArrayList<Double>());
		repartition.get(0).add(1.0);
		repartition.get(0).add(28.8);
		repartition.get(0).add(11.4);
		
		repartition.get(1).add(9.2);
		repartition.get(1).add(1.8);
		repartition.get(1).add(18.9);
		
		repartition.get(2).add(18.0);
		repartition.get(2).add(1.8);
		repartition.get(2).add(29.4);
		
		
		DoCalculs.getDaviesBouldinIndice(repartition);
	}
}
