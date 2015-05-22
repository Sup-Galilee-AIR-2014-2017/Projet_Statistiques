package AppInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class About extends Dialog {

	protected Object result;
	protected Shell shlAbout;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public About(Shell parent, int style) {
		super(parent, style);
		setText("About ...");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlAbout.open();
		shlAbout.layout();
		Display display = getParent().getDisplay();
		while (!shlAbout.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlAbout = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlAbout.setImage(SWTResourceManager.getImage(About.class, "/resources/imgs/logo_app_projet_grande.png"));
		shlAbout.setMinimumSize(new Point(95, 25));
		shlAbout.setSize(350, 250);
		Rectangle rect = getParent().getBounds();
		Rectangle rect2 = shlAbout.getBounds();
		Point pt = new Point((rect.x + rect.width/2)-rect2.width/2, (rect.y + rect.height/2)-rect2.height/2);
		shlAbout.setLocation(pt.x, pt.y);
		shlAbout.setText("About...");
		shlAbout.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlAbout, SWT.NONE);
		composite.setLayout(null);
		
		Button btnOk = new Button(composite, SWT.NONE);
		btnOk.setBounds(125, 192, 84, 23);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAbout.dispose();
			}
		});
		btnOk.setText("OK");
		
		StyledText styledText = new StyledText(composite, SWT.READ_ONLY);
		styledText.setBounds(44, 104, 258, 82);
		styledText.setText("DEVELOPEMENT TEAM\r\n\r\nMam Abdoulaye DIOUF,\tProject Manager\r\nMaeva CHERRY,\t\t\tDevelopper\r\nAlexandre HERVET,\t\tDevelopper\r\nAdam YIMMAD,\t\t\tDevelopper");
		styledText.setAlignment(SWT.CENTER);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setImage(SWTResourceManager.getImage(About.class, "/resources/imgs/logo_app_projet_grande.png"));
		lblNewLabel.setBounds(26, 10, 276, 50);
		
		Label lblStatisticalApplication = new Label(composite, SWT.NONE);
		lblStatisticalApplication.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
		lblStatisticalApplication.setAlignment(SWT.CENTER);
		lblStatisticalApplication.setBounds(10, 66, 324, 32);
		lblStatisticalApplication.setText("STATISTICAL APPLICATION\r\nVersion 0.5");

	}
}
