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
		
		//crée
		boolean home = true;
		
		app.GraphicalInterface(home);
	}

	public static InterfaceApp getIntApp() {
		return app;
	}

}
