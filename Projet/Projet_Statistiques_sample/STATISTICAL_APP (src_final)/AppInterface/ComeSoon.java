package AppInterface;

import org.eclipse.swt.SWT;
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

public class ComeSoon extends Dialog {

	protected Object result;
	protected Shell shlAbout;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ComeSoon(Shell parent, int style) {
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
		shlAbout.setSize(250, 150);
		Rectangle rect = getParent().getBounds();
		Rectangle rect2 = shlAbout.getBounds();
		Point pt = new Point((rect.x + rect.width/2)-rect2.width/2, (rect.y + rect.height/2)-rect2.height/2);
		shlAbout.setLocation(pt.x, pt.y);
		shlAbout.setText("Come back soon ...");
		shlAbout.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlAbout, SWT.NONE);
		composite.setLayout(null);
		
		Button btnOk = new Button(composite, SWT.NONE);
		btnOk.setBounds(77, 64, 84, 23);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAbout.dispose();
			}
		});
		btnOk.setText("OK");
		
		Label lblNewLabel = new Label(composite, SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD | SWT.ITALIC));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 22, 224, 32);
		lblNewLabel.setText("Please come back soon ...");
		
	}
}