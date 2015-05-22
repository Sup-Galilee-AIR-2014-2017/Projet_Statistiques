/**
 * 
 */
package AppInterface;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * @author admaay
 *
 */



public class AppProgressBar extends Dialog {

	public Object result;
	public Shell shlPleaseWait;
	public boolean end = false; 

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AppProgressBar(Shell parent, int style) {
		super(parent, style);
		//shlPleaseWait = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		setText("Please wait...\r\n");
		//this.createContents();
	}

	/**
	 * Open the dialog.
	 * @return the result
	 * @throws InterruptedException 
	 */
	public void open() throws InterruptedException {
		createContents();		
		shlPleaseWait.open();
		shlPleaseWait.layout();
		Display display = getParent().getDisplay();
		Date d = new Date();
		int now = d.getSeconds();
		while (!shlPleaseWait.isDisposed() && !end || d.getSeconds() - now < 1) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
			d = new Date();
		}
		shlPleaseWait.dispose();
	}
	
	public void setEnd() {
		end = true;
	}
	
	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlPleaseWait = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlPleaseWait.setImage(SWTResourceManager.getImage(About.class, "/resources/imgs/logo_app_projet_grande.png"));
		shlPleaseWait.setMinimumSize(new Point(95, 25));
		shlPleaseWait.setSize(350, 222);
		Rectangle rect = getParent().getBounds();
		Rectangle rect2 = shlPleaseWait.getBounds();
		Point pt = new Point((rect.x + rect.width/2)-rect2.width/2, (rect.y + rect.height/2)-rect2.height/2);
		shlPleaseWait.setLocation(pt.x, pt.y);
		shlPleaseWait.setText("Please wait...");
		shlPleaseWait.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(shlPleaseWait, SWT.NONE);
		composite.setLayout(null);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setImage(SWTResourceManager.getImage(About.class, "/resources/imgs/logo_app_projet_grande.png"));
		lblNewLabel.setBounds(26, 10, 276, 50);
		
		Label lblStatisticalApplication = new Label(composite, SWT.NONE);
		lblStatisticalApplication.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
		lblStatisticalApplication.setAlignment(SWT.CENTER);
		lblStatisticalApplication.setBounds(10, 66, 324, 32);
		lblStatisticalApplication.setText("STATISTICAL APPLICATION\r\nVersion 0.5");
		
		Label lblLoadingData = new Label(composite, SWT.NONE);
		lblLoadingData.setText("Loading Data");
		lblLoadingData.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
		lblLoadingData.setAlignment(SWT.CENTER);
		lblLoadingData.setBounds(10, 155, 324, 32);
		
		ProgressBar pb3 = new ProgressBar(composite, SWT.SMOOTH | SWT.INDETERMINATE);
		pb3.setLocation(26, 111);
		pb3.setSize(276, 32);
		
		
	}
	
	public void disposePb() {
		shlPleaseWait.dispose();
	}
}
