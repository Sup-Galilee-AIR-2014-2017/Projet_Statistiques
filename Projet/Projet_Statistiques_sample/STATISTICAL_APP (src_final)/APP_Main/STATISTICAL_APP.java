package APP_Main;

import AppInterface.InterfaceApp;
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
		
		//crée
		boolean home = true;
		
		app.GraphicalInterface(home);
	}
	
	public static InterfaceApp getIntApp() {
		return app;
	}

}
