package AppInterface;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

import swing2swt.layout.BorderLayout;
import DATA_And_Files_Propreties.DataAndFilesProprieties;
import ExportPDF.CorpPdf;
import ModStats.DoCalculs;
import ModStats.InputObject;
import ModVisual.HistoG;
import ModVisual.ScatterPlot_1;

import com.ibm.icu.util.StringTokenizer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * <h1>Interface principale de l'application</h1>
 * 
 *  
 * @author Chery Maeva
 * @author Adam Y.
 * @version 2.1 / 18.05.2013
 * @since december 2012 
 *
 */

public class InterfaceApp {
	
	 /**
	  * @wbp.parser.entryPoint
	  */
	
	final Display display = new Display();//.getDefault();
    final Shell shell = new Shell(display);
	
	 /**
	  * @wbp.parser.entryPoint
	  */
	 public  void GraphicalInterface(boolean home) {
	    	
	        shell.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/logo_app_projet_grande.png"));
	        
	        shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    shell.setMinimumSize(new Point(130, 20));
		    shell.setModified(true);
		    shell.setSize(820, 600);
		    shell.setText("STATISTICAL APPLICATION");
		    shell.setLayout(new BorderLayout(3, 1));
		   
		    //
		    final DoCalculs calc = new DoCalculs();
		    final DataAndFilesProprieties dfp = new DataAndFilesProprieties();
		    final AppProgressBar  pb = new AppProgressBar(shell,SWT.NONE);
		    
		    //final boolean end = false;
		    final JFreeChart[] chart = new JFreeChart[100];
		    final JFreeChart[] sp_chart = new JFreeChart[100];
		    
		    final List<ScrolledComposite> scrolledComposite = new ArrayList<ScrolledComposite>();
		    final List<Table> tableRes = new ArrayList<Table>();
		    final List<TabItem> tbtmResults = new ArrayList<TabItem>();
		    final ViewForm viewForm = new ViewForm(shell, SWT.NONE);
		    final TabFolder tabFolder = new TabFolder(viewForm, SWT.NONE);
		    tabFolder.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    viewForm.setTopCenterSeparate(true);
		    
		    
		    final Group grpInfo = new Group(shell, SWT.SHADOW_ETCHED_OUT);
	        grpInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
	        grpInfo.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
	        grpInfo.setText("Informations");
	        grpInfo.setLayoutData(BorderLayout.SOUTH);
	        grpInfo.setLayout(new FillLayout(SWT.HORIZONTAL));
		    
		    final CLabel lblInfo = new CLabel(grpInfo, SWT.SHADOW_IN);
		    lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
		    lblInfo.setBottomMargin(5);
		    lblInfo.setTopMargin(7);
		    lblInfo.setText("About home");
		     
		     /******************************************************************************************************************/
            
		     
		     final Composite composite_barreChoixHG = new Composite(viewForm, SWT.NONE);
		     
//		     viewForm.setTopLeft(composite_barreChoixHG);
		     composite_barreChoixHG.setLayout(new FillLayout(SWT.HORIZONTAL));
		     
		     Group grpChoixDesColonnes = new Group(composite_barreChoixHG, SWT.SHADOW_OUT);
		     grpChoixDesColonnes.setText("Choix du colonne et du Nb de Lignes");
		     GridLayout gl_grpChoixDesColonnes = new GridLayout(1, false);
		     gl_grpChoixDesColonnes.marginWidth = 1;
		     grpChoixDesColonnes.setLayout(gl_grpChoixDesColonnes);
		     
		     
		     Composite composite = new Composite(grpChoixDesColonnes, SWT.NONE);
		     composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		     GridLayout gl_composite = new GridLayout(6, false);
		     gl_composite.horizontalSpacing = 15;
		     composite.setLayout(gl_composite);
		     
		     Label lblColToShow = new Label(composite, SWT.HORIZONTAL | SWT.CENTER);
		     lblColToShow.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		     lblColToShow.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		     lblColToShow.setText("Column to show :");
		     
		     final Combo combo = new Combo(composite, SWT.NONE | SWT.READ_ONLY);
		     combo.setText("Column");
		     
		     Label lblNbOfLines = new Label(composite, SWT.NONE);
		     lblNbOfLines.setAlignment(SWT.CENTER);
		     lblNbOfLines.setText("Interval of Lines to show :");
		     
		     final Spinner spinner = new Spinner(composite, SWT.BORDER | SWT.READ_ONLY);
		     final Spinner spinner_1 = new Spinner(composite, SWT.BORDER | SWT.READ_ONLY);
		     
		     GridData gd_spinner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		     gd_spinner.widthHint = 32;
		     spinner.setLayoutData(gd_spinner);
		     
		     
		     //pour faire à ce que le max d'ici soit le min de  l'autre
		     spinner.addSelectionListener(new SelectionAdapter() {
		     public void widgetSelected(SelectionEvent e) {
		           int selection = spinner.getSelection();
		           spinner_1.setMinimum(selection+1);
		         }
		     });
		     
		     spinner.setMaximum(1);
		     spinner.setMinimum(1);
		     
		     
		     GridData gd_spinner_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		     gd_spinner_1.widthHint = 32;
		     spinner_1.setLayoutData(gd_spinner_1);
		     
		     spinner_1.setMaximum(1);
		     spinner_1.setMinimum(1);
		     
		     Button btnValider = new Button(composite, SWT.CENTER);
		     GridData gd_btnValider = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		     gd_btnValider.heightHint = 23;
		     btnValider.setLayoutData(gd_btnValider);
		     btnValider.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/btnValider.png"));
		     btnValider.addSelectionListener(new SelectionAdapter() {
		     	@Override
		     	public void widgetSelected(SelectionEvent e) {
		     		int ActiveItem = getActiveTabItem(tabFolder);
		     		int ColNum = combo.getSelectionIndex();
		     		int FirstLine = spinner.getSelection() -1; //pour faire correspondre avec les index des tableaux
		     		int LastLine = spinner_1.getSelection() -1;
		     		if(calc.fileImported()) {
			     		// il doit choisir la colonne à afficher
			     		if(ColNum >= 0) {
			     			//get data
				     		InputObject lt = dfp.getData();
				     				     		
				     		//get values
							double[] value = new double[(LastLine-FirstLine)+1];
							
				     		try {
								for(int j = 0; j < (LastLine-FirstLine)+1; j++) {
									value[j] = lt.getValuesList(ColNum).get(j+FirstLine);
								}
								
							} catch (Exception e1) {
								e1.printStackTrace();
								lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
								lblInfo.setText("Error : Failed to get data !");
							}
				     		
				     		//content
				     		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				     		HistoG histogramme = new HistoG(dataset, "Histogram", "Values", "Number", PlotOrientation.VERTICAL);
				     		histogramme.addValues(value,ColNum+1);
				     		chart[ActiveItem] = histogramme.createChart();
				     		
				     		ChartComposite frame = new ChartComposite(scrolledComposite.get(ActiveItem), SWT.NONE, chart[ActiveItem], true);
				     		//frame.setSize(300, 300);
				     		
				     		scrolledComposite.get(ActiveItem).setContent(frame);
				            scrolledComposite.get(ActiveItem).setMinSize(frame.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				            
				            lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
				      		lblInfo.setText("Histogram for the column "+(ColNum+1)+", from the line "+(FirstLine+1)+" to the line "+(LastLine+1));
			     		}else {
			     			lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
							lblInfo.setText("Error : You must choose the column and the interval of lines to visualise. Verify your choose !");
			     		}
		     		}else {
            			lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
						lblInfo.setText("Error : No data found ! you must import a data file first !");
            		}
		     			
		     	}
		     });
		     btnValider.setText("Validate");
		     btnValider.setToolTipText("Validate your choice");
		     
		     /****************************END HG BEGIN SP*********************************************************************************/
		     
		     final Composite composite_barreChoixSP = new Composite(viewForm, SWT.NONE);
	            composite_barreChoixSP.setLayout(new FillLayout(SWT.HORIZONTAL));
	           
	            Group grpChoixDesColonnes_1 = new Group(composite_barreChoixSP, SWT.NONE);
	            grpChoixDesColonnes_1.setText("Choix Des Colonnes et du Couleur");
	            grpChoixDesColonnes_1.setLayout(new GridLayout(1, false));
	            
	            Composite composite_1 = new Composite(grpChoixDesColonnes_1, SWT.NONE);
	            GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
	            gd_composite_1.widthHint = 736;
	            composite_1.setLayoutData(gd_composite_1);
	            GridLayout gl_composite_1 = new GridLayout(13, false);
	            gl_composite_1.horizontalSpacing = 15;
	            composite_1.setLayout(gl_composite_1);
	            
	            Label lblCoordonnees = new Label(composite_1, SWT.NONE);
	            lblCoordonnees.setBounds(0, 0, 49, 13);
	            lblCoordonnees.setText("Coordonnees :");
	            
	            Label lblX = new Label(composite_1, SWT.CENTER);
	            lblX.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
	            lblX.setBounds(0, 0, 49, 13);
	            lblX.setText("X :");
	            
	            final Spinner spinner_2 = new Spinner(composite_1, SWT.BORDER | SWT.READ_ONLY);
	            
	            Label label_2 = new Label(composite_1, SWT.NONE);
	            label_2.setText("Y :");
	            label_2.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
	            final Spinner spinner_3 = new Spinner(composite_1, SWT.BORDER | SWT.READ_ONLY);
	            
	            spinner_2.setBounds(0, 0, 46, 21);
	            spinner_2.setMaximum(0);
	            spinner_2.setMinimum(0);
	            
	            
	            spinner_3.setBounds(0, 0, 46, 21);
	            spinner_3.setMaximum(0);
	            spinner_3.setMinimum(0);
	            new Label(composite_1, SWT.NONE);
	            
	            final ScatterPlot_1[] scatter = new ScatterPlot_1[100];
	            
	            Button btnValidateSP = new Button(composite_1, SWT.NONE);
	            GridData gd_btnValidateSP = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
	            gd_btnValidateSP.heightHint = 23;
	            btnValidateSP.setLayoutData(gd_btnValidateSP);
	            btnValidateSP.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/btnValider.png"));
	            btnValidateSP.addSelectionListener(new SelectionAdapter() {
			     	@Override
			     	public void widgetSelected(SelectionEvent e) {
			     		
			     		if(calc.fileImported()) {
				     		int ActiveItem = getActiveTabItem(tabFolder);
				     		scatter[ActiveItem] = new ScatterPlot_1();
	
				     		scatter[ActiveItem].setInputObject(dfp.getDataByObject());
			                int in = 0;
			                for(InputObject inob : dfp.getDataByObject()) {
			                	double[] val1 = new double[inob.getValuesList(spinner_2.getSelection()-1).size()];
			                	for(int i = 0; i < inob.getValuesList(spinner_2.getSelection()-1).size(); i++)
			                		val1[i] = inob.getValuesList(spinner_2.getSelection()-1).get(i);
			                	
			                	double[] val2 = new double[inob.getValuesList(spinner_3.getSelection()-1).size()];
			                	for(int i = 0; i < inob.getValuesList(spinner_3.getSelection()-1).size(); i++)
			                		val2[i] = inob.getValuesList(spinner_3.getSelection()-1).get(i);
			                	
			                	scatter[ActiveItem].addValues(scatter[ActiveItem].series[in], val1, val2);
			                	in++;
			                }
			                
			                
			                sp_chart[ActiveItem] = scatter[ActiveItem].createChart(dfp.getNbObjectFound(),spinner_2.getSelection(),spinner_3.getSelection());
			                
			                ChartComposite frame = new ChartComposite(scrolledComposite.get(ActiveItem),SWT.NONE,sp_chart[ActiveItem],true);
			                
			                scrolledComposite.get(ActiveItem).setContent(frame);
			                scrolledComposite.get(ActiveItem).setMinSize(frame.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			     		}else {
	            			lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
							lblInfo.setText("Error : No data found ! you must import a data file first !");
	            		}
			     	}
	            });
	            
	            btnValidateSP.setText("Validate ");
	            btnValidateSP.setToolTipText("Validate your choice");
	            new Label(composite_1, SWT.NONE);
	            new Label(composite_1, SWT.NONE);
	            new Label(composite_1, SWT.NONE);
	            
	            final Combo combo_1 = new Combo(composite_1, SWT.NONE);
	            GridData gd_combo_1 = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
	            gd_combo_1.widthHint = 113;
	            combo_1.setLayoutData(gd_combo_1);
	            combo_1.setText("text");
	            new Label(composite_1, SWT.NONE);
	            
	            Button btnChooseColor = new Button(composite_1, SWT.NONE);
	            btnChooseColor.addSelectionListener(new SelectionAdapter() {
			     	@Override
			     	public void widgetSelected(SelectionEvent e) {
			     		int ActiveItem = getActiveTabItem(tabFolder);
			     		ColorDialog dlg = new ColorDialog(shell);
			     	    RGB rgb = dlg.open();
			     	   if (rgb != null) {
			     	      Color color = new Color(rgb.red,rgb.green,rgb.blue);
			     	      int index = combo_1.getSelectionIndex();
			     	      if(index >=0)
			     	    	  scatter[ActiveItem].changeColor(index, color);
			     	   }
			     	
			     	}	
			     	    
	                });
	            btnChooseColor.setText("Choose Color");
		     
		     /***********************************************************END SP*******************************************************************/
		     
		     //ceci permet de detecter l'onglet courant et ...
		     tabFolder.addSelectionListener(new SelectionAdapter() {
	            	@Override
	            	public void widgetSelected(SelectionEvent e) {
	            		int i;
	            		for(i =0; i< tabFolder.getItemCount(); i++) {
	            			if(tabFolder.getItem(i).hashCode()==tabFolder.getSelection()[0].hashCode())
	            				break;
	            		}
	            		String ItTitle = tabFolder.getSelection()[0].getText();
	            		if(ItTitle.startsWith("Histogram")) {
	            			viewForm.setTopLeft(composite_barreChoixHG);
	            		}
	            		else if(ItTitle.startsWith("Scatter"))
	            			viewForm.setTopLeft(composite_barreChoixSP);
	            		else
	            			viewForm.setTopLeft(null);
	            		
	            	}
	            });
		     
		   
		    /***********************************BEGIN MENU *****************************************************************************/

	         
	        Menu menu = new Menu(shell, SWT.BAR);
	        shell.setMenuBar(menu);
	         
	        MenuItem mntmFile_1 = new MenuItem(menu, SWT.CASCADE);
	        mntmFile_1.setText("File");
	         
	        Menu menu_1 = new Menu(mntmFile_1);
	        mntmFile_1.setMenu(menu_1);
	         
	        MenuItem mntmImporterDonnee = new MenuItem(menu_1, SWT.NONE);
	        mntmImporterDonnee.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeImport.png"));
	          
	        mntmImporterDonnee.addSelectionListener(new SelectionAdapter() {
	      	@Override
	      	public void widgetSelected(SelectionEvent e) {
	      		String nomFichier;
	      		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
	      		dialog.setFilterExtensions(new String[] { "*.*" });
	      		nomFichier = dialog.open();
	      		
 	      		if(nomFichier != null) {
	      			Display.getDefault().asyncExec(new Runnable() {
	      				public void run() {
	      				
							try {
								pb.open();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}							
    	      			}
	      			});
	      			
    	      		//set file
	      			try {
						dfp.setFilePropreties(nomFichier);
					} catch (IOException e1) {
						lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
						lblInfo.setText("Error : Failed to set file propreties !");
					}
	      			
    	      		dfp.setDataFile();
    	      		calc.setInputData(dfp.getData());
    			     int Nblines = 1;
    			     Nblines = dfp.getNbLines();
    			     spinner.setMinimum(1);
    			     spinner.setMaximum(Nblines);
    			     
    			     spinner_1.setMinimum(1);
    			     spinner_1.setMaximum(Nblines);
    			     
    			     
    			     int NbCol = 0;
    			     NbCol = dfp.getNbDataColumns();
    			     
    			     if(NbCol > 0) {
				    	 String[] items = new String[NbCol];
				    	 for(int i=0; i < NbCol; i++) {
				    		 items[i] = ""+(i+1);
				    	 }
				    	 combo.setItems(items);
				    	 spinner_2.setMaximum(NbCol);
				         spinner_2.setMinimum(1);
				         spinner_3.setMaximum(NbCol);
				         spinner_3.setMinimum(1);
				     }
    			     String[] obj = dfp.getObjectNames();
    			     combo_1.setItems(obj);
    	      		//info
    			    if(NbCol > 0 && Nblines >= 1) {
    			    	lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
    	      			lblInfo.setText("The file was succefully imported ! *** File summary : "+ Nblines + " lines, "+NbCol+ " Columns, "+ dfp.getNbObjectFound() +" Objects, "+dfp.getNbMissingData()+" Missing data ! ***");
    	      			pb.setEnd();
    			    }
 	      		}
	      		
	      		
	      	}
	      });
	          mntmImporterDonnee.setText("Import data");
	          
	         
	          MenuItem mntmImporterModule = new MenuItem(menu_1, SWT.NONE);
	          mntmImporterModule.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeImport.png"));
	          mntmImporterModule.addSelectionListener(new SelectionAdapter() {
	  	      	@Override
	  	      	public void widgetSelected(SelectionEvent e) {
	  	      		ComeSoon cs = new ComeSoon(shell,SWT.ID_ABOUT);
	  	      		cs.open();
	  	      	}
	          });
	          mntmImporterModule.setText("Import Module");
	          
	          new MenuItem(menu_1, SWT.SEPARATOR);
	          
	          /*******************************************************************************************************************/
	          
	          MenuItem mntmExportToPdf = new MenuItem(menu_1, SWT.NONE);
	          mntmExportToPdf.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconePdf.png"));
	          mntmExportToPdf.addSelectionListener(new SelectionAdapter() {
	            	@Override
	            	public void widgetSelected(SelectionEvent e) {
	            		
	            		if(calc.fileImported()) {
		            		String nomFichier;
		    	      		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		    	      		
		    	      		//to get the tan name
		    	      		int ActiveItem = getActiveTabItem(tabFolder);
		    	      		StringTokenizer st = new StringTokenizer(tbtmResults.get(ActiveItem).getText(),"|");
		    	      		String title = st.nextToken();
		    	      		
	    	      			dialog.setFileName("Export_"+title);
		    	      		
	    	      			if(title.equals("Statistical Calculs") || title.startsWith("Histogram") || title.startsWith("Scatter Plot")) {	
			    	      		dialog.setFilterExtensions(new String[] { "*.*" });
			    	      		nomFichier = dialog.open();
			    	      		
			    	      		
			    	      		//exportation
			    	      		if(nomFichier != null) {
			    	      			File f = new File(nomFichier);
				    	      		if(f.exists()) {
				    	      			f.delete();
				    	      		}
			    	      			
			    	      			CorpPdf corp = new CorpPdf("STATICAL CALCULS SUMMARY", dfp.getNbLines(), dfp.getNbDataColumns(), dfp.getNbObjectFound(), dfp.getObjectNames(), dfp.getNbMissingData());
			    		    		Document document = new Document();
			    		    		
			    		    		//test si bon extension
			    		    		if(nomFichier.endsWith(".pdf")) {
			    		    			try {
			        		    			PdfWriter.getInstance(document, 
			        		    				new FileOutputStream(nomFichier));
			        		    		} catch (FileNotFoundException e1) {
			        		    			e1.printStackTrace();
			        		    		} catch (DocumentException e1) {
			        		    			e1.printStackTrace();
			        		    		}
			    		    		}else {
			    		    			nomFichier = nomFichier.concat(".pdf");
			    		    			try {
			        		    			PdfWriter.getInstance(document, 
			        		    				new FileOutputStream(nomFichier));
			        		    		} catch (FileNotFoundException e1) {
			        		    			e1.printStackTrace();
			        		    		} catch (DocumentException e1) {
			        		    			e1.printStackTrace();
			        		    		}
			    		    		}
			    		    		
			    		    		//get all result
			    		    		double[] tabMean = calc.getMeans(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
			    		    		double[] tabMediane = calc.getMedians(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
			    		    		double[] tabSD = calc.getSDs(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
			    		    		double[] tabMin = calc.getMins(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
			    		    		double[] tabMax = calc.getMaxs(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
			    		    		double[] tabVar = calc.getVariances(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
			    		    		String[] tabMode = calc.getModes(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
			    		    		
			    		    		if(title.equals("Statistical Calculs")) {
			    		                corp.createTab(document, dfp.getNbDataColumns(), tabMean, tabMediane, tabSD, tabMin, tabMax, tabVar, tabMode);
			    		                try {
			    		                 document.add(corp.paraHeader);
			    		                 document.add(corp.paraIntro);
			    		                 document.add(corp.paraResume);
			    		                 document.add(corp.paraResume1);
			    		                 document.add(corp.paraResume2);
			    		                 
			    		                 document.add(corp.paraResume4);
			    		                 //for(int i=0;i<dfp.getNbObjectFound();i++){
			    		                  document.add(corp.paraResume5);
			    		                 //}
			    		                 
			    		                 document.add(corp.paraResume7);
			    		                 document.add(corp.table);
			    		                } catch (DocumentException ex) {
			    		                 ex.printStackTrace();
			    		                }
			    		                document.close();
			    		               }else if(title.equals("Histogram")) {
			    		                try {
			    		                 document.open();
			    		                 document.add(corp.paraHeader);
			    		                 document.add(corp.paraIntro);
			    		                 document.add(corp.paraResume);
			    		                 document.add(corp.paraResume1);
			    		                 document.add(corp.paraResume2);
			    		                 
			    		                 document.add(corp.paraResume4);
			    		                 //for(int i=0;i<dfp.getNbObjectFound();i++){
			    		                  document.add(corp.paraResume5);
			    		                 //}
			    		                 
			    		                 document.add(corp.paraResume8);
			    		                 document.add(corp.table);
			    		                } catch (DocumentException ex) {
			    		                 ex.printStackTrace();
			    		                }
			    		                corp.createImage(chart[ActiveItem], document);
			    		                document.close();
			    		    		}else if(title.equals("Scatter Plot")) {
			    		                try {
			          		                 document.open();
			          		                 document.add(corp.paraHeader);
			          		                 document.add(corp.paraIntro);
			          		                 document.add(corp.paraResume);
			          		                 document.add(corp.paraResume1);
			          		                 document.add(corp.paraResume2);
			          		                 
			          		                 document.add(corp.paraResume4);
			          		                 //for(int i=0;i<dfp.getNbObjectFound();i++){
			          		                  document.add(corp.paraResume5);
			          		                 //}
			          		                 
			          		                 document.add(corp.paraResume9);
			          		                 document.add(corp.table);
			          		                } catch (DocumentException ex) {
			          		                 ex.printStackTrace();
			          		                }
			          		                corp.createImage(sp_chart[ActiveItem], document);
			          		                document.close();
			       		    		}
			    		    		lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
			    	      			lblInfo.setText("*** The results was succefully exported  ***");
			    	      		}
			    		    		
		    	      		}else {
		    	      			lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
								lblInfo.setText("Error : No data found ! you must import a data file first !");
		    	      		}
		            	}else {
	    	      			lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
							lblInfo.setText("To use export to PDF function you must be on either statistical calculations, Histogram, or Scatter Plot tab ! ");
	    	      		}
	            	}
	          });
	          mntmExportToPdf.setText("Export to PDF");
	          
	          new MenuItem(menu_1, SWT.SEPARATOR);
	         
	          
	          MenuItem mntmQuit = new MenuItem(menu_1, SWT.NONE);
	          
	          mntmQuit.addSelectionListener(new SelectionAdapter() {
	          	@Override
	          	public void widgetSelected(SelectionEvent e) {
	          		System.exit(0);
	          	}
	          });
	          mntmQuit.setText("Quit");
	         
	          final MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
	          mntmHelp.setText("Help");
	         
	          Menu menu_2 = new Menu(mntmHelp);
	          mntmHelp.setMenu(menu_2);
	         
	          MenuItem mntmHelp_1 = new MenuItem(menu_2, SWT.NONE);
	          mntmHelp_1.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/Iconhelp.png"));
	          mntmHelp_1.setText("Help");//TODO
	         
	          MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
	          mntmAbout.addSelectionListener(new SelectionAdapter() {
	            	@Override
	            	public void widgetSelected(SelectionEvent e) {
	            		About ab = new About(shell,SWT.ID_ABOUT);
	            		ab.open();
	            	}
	            });
	          mntmAbout.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
	          mntmAbout.setText("About");
	          
	          /*******************************************************END MENU******************************************/
	          
	          /****************************************************BAR MENU START **************************************/
	          
	          final Menu menu_n = new Menu(shell, SWT.POP_UP);
	          MenuItem menu1 = new MenuItem(menu_n, SWT.PUSH);
	          menu1.addSelectionListener(new SelectionAdapter() {
	          	@Override
	          	public void widgetSelected(SelectionEvent e) {
	          		lblInfo.setText("Histogram graphic");
	          		int ActiveItem = getActiveTabItem(tabFolder);
	          		
	          		String ItemTitle;
	          		
					if (ActiveItem > 0)
						ItemTitle = "Histogram"+" |"+ActiveItem;
					else
						ItemTitle = "Histogram";
					
	          		tbtmResults.get(ActiveItem).setText(ItemTitle);
            		tbtmResults.get(ActiveItem).setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeHisto.png"));
            		
            		scrolledComposite.get(ActiveItem).setExpandVertical(true);
    	            scrolledComposite.get(ActiveItem).setExpandHorizontal(true);
    	            
    	            viewForm.setTopLeft(composite_barreChoixHG);
	          	}
	          });
	          menu1.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeHisto.png"));
	          menu1.setText("Histogram");
	          
	          
	          MenuItem menu2 = new MenuItem(menu_n, SWT.PUSH);
	          menu2.addSelectionListener(new SelectionAdapter() {
	          	@Override
	          	public void widgetSelected(SelectionEvent e) {
	          		lblInfo.setText("Scatter Plot graphic");
	          		int ActiveItem = getActiveTabItem(tabFolder);
	          		
	          		String ItemTitle;
	          		
					if (ActiveItem > 0)
						ItemTitle = "Scatter Plot"+" |"+ActiveItem;
					else
						ItemTitle = "Scatter Plot";
	          		
	          		tbtmResults.get(ActiveItem).setText(ItemTitle);
            		tbtmResults.get(ActiveItem).setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeScatterPlot.png"));
            		
    	            scrolledComposite.get(ActiveItem).setExpandVertical(true);
    	            scrolledComposite.get(ActiveItem).setExpandHorizontal(true);
    	            
            		viewForm.setTopLeft(composite_barreChoixSP);
	          	}
	          });
	          menu2.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeScatterPlot.png"));
	          menu2.setText("Scatter Plot");        
        
	          
	          /**
	           * La barre contenant les boutons Mod stat visualisation etc....
	           * 
	           */	          
	          Composite composite_3 = new Composite(shell, SWT.BORDER);
	          composite_3.setLayoutData(BorderLayout.NORTH);
	          composite_3.setLayout(new GridLayout(17, false));
            
	          Button button = new Button(composite_3, SWT.NONE);
            button.addSelectionListener(new SelectionAdapter() {
            	@Override
            	public void widgetSelected(SelectionEvent e) {
            		
	          		int ActiveItem = getActiveTabItem(tabFolder); 
	          		
	          		StyledText  styledText = new StyledText(scrolledComposite.get(ActiveItem), SWT.READ_ONLY);
	    		    
	    		    styledText.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
	                styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//	                styledText.setSelectionForeground(SWTResourceManager.getColor(51, 153, 255));
	                styledText.setText("Welcome on Stat App !\r\n\r\nThis application allows you to achieve your statistical calculations, to have graphical display:\r\n" +
	                		"Histogram and Scatter Plot.\r\n\r\n" +
	                		"Running : \r\n- Start by importing a file using the import button in the menu \r\n\n" +
	                		"- then choose the  file you want \r\n\n" +
	                		"The data of the the imported file contains decimal numbers arranged in columns \r\n"+
	                		"and the columns separated by commas which are not reused. \r\n\n" +
	                		"Exemple:\r\n"+
	                		"\t - Ce qu’il faut faire	              5.1,3.5,1.4,0.2,Iris-setosa\r\n"+
	                		"\t - Ce qu’il ne faut pas faire	      5.1 :3.5 :1.4 :0.2:Iris-setosa\r\n"+
	                		"\t - Ou 				                  5.1;3.5;1.4;0.2;Iris-setosa\r\n\n"+
	                		"Once the user has selected the file, he can see in the dialog box at the bottom\r\n" +
	                		"of the page, that the file has been successfully imported and have information \r\n"  +
	                		"on the file.\r\n\n" +
	                		"When the user clicks on the 'statistics' button, he can view statistical results\r\n" +
	                		"in tabular form\r\n\n" +
	                		"By clicking on 'visual graphics' button, the user can view results as:\r\n" +
	                		"\t - Histogram\r\n"+
	                		"\t - Scatter Plot\r\n\n" +
	                		"The user can export the results in PDF format which contains the results \r\n" +
	                		" of the active tab");
	                styledText.setEnabled(false);
	                styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
	                styledText.setLeftMargin(5);
	                styledText.setDoubleClickEnabled(false);
	                styledText.setEditable(false);       		
            		
	                lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
	                lblInfo.setText("Home");
	          			          		
	          		String ItemTitle;
	          		
					if (ActiveItem > 0)
						ItemTitle = "Home"+" |"+ActiveItem;
					else
						ItemTitle = "Home"; 
            		tbtmResults.get(ActiveItem).setText(ItemTitle);
            		tbtmResults.get(ActiveItem).setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeHome.png"));
            		
            		scrolledComposite.get(ActiveItem).setExpandVertical(true);
    	            scrolledComposite.get(ActiveItem).setExpandHorizontal(true);
    	            //tbtmResults[ActiveItem].setControl(scrolledComposite[ActiveItem]);
    	            
    	            scrolledComposite.get(ActiveItem).setContent(styledText);
    	            scrolledComposite.get(ActiveItem).setMinSize(styledText.computeSize(SWT.DEFAULT, SWT.DEFAULT));  
    	            
    	            //
    	            viewForm.setTopLeft(null);
    	           
            	}
            });
            button.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeHome.png"));
            button.setToolTipText("Home");
            new Label(composite_3, SWT.NONE);
            Button btnModuleStatistique = new Button(composite_3, SWT.NONE);
            btnModuleStatistique.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeStat.png"));
            btnModuleStatistique.addSelectionListener(new SelectionAdapter() {
            	@Override
            	public void widgetSelected(SelectionEvent e) {          		
            		lblInfo.setText("Statistical Calculs Modul");
            		
            		//hide ... if active
            		viewForm.setTopLeft(null);
        				            
            		String ItemTitle;
            		int ActiveItem = getActiveTabItem(tabFolder); 
					if (ActiveItem > 0)
						ItemTitle = "Statistical Calculs"+" |"+ActiveItem;
					else
						ItemTitle = "Statistical Calculs";
					
            		tbtmResults.get(ActiveItem).setText(ItemTitle);
            		tbtmResults.get(ActiveItem).setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeStat.png"));
            		//affichage des resultats
            		scrolledComposite.get(ActiveItem).setExpandVertical(true);
            		scrolledComposite.get(ActiveItem).setExpandHorizontal(true);
            		
            		
            		tableRes.set(ActiveItem,  new Table(scrolledComposite.get(ActiveItem), SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI));
            		tableRes.get(ActiveItem).setLinesVisible(true);
            		tableRes.get(ActiveItem).setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
            		tableRes.get(ActiveItem).setHeaderVisible(true);
            		
            		TableColumn tblclmnColumns = new TableColumn(tableRes.get(ActiveItem), SWT.LEFT);
            		tblclmnColumns.setResizable(false);
            		tblclmnColumns.setWidth(40);
            		tblclmnColumns.setText("No.");
	                  
            		TableColumn tblclmnMoyenne = new TableColumn(tableRes.get(ActiveItem), SWT.CENTER);
            		tblclmnMoyenne.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeMean.png"));
            		tblclmnMoyenne.setWidth(100);
            		tblclmnMoyenne.setText("Mean");
	                  
            		TableColumn tblclmnMediane = new TableColumn(tableRes.get(ActiveItem), SWT.CENTER);
            		tblclmnMediane.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeMedian.png"));
            		tblclmnMediane.setWidth(110);
            		tblclmnMediane.setText("Mediane");
	                  
            		TableColumn tblclmnEcartType = new TableColumn(tableRes.get(ActiveItem), SWT.CENTER);
            		tblclmnEcartType.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeStandDeviation.png"));
            		tblclmnEcartType.setWidth(135);
            		tblclmnEcartType.setText("Stand Deviation");
	                  
            		TableColumn tblclmnMin = new TableColumn(tableRes.get(ActiveItem), SWT.CENTER);
            		tblclmnMin.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeMinmax.png"));
            		tblclmnMin.setWidth(98);
            		tblclmnMin.setText("Min");
            		
            		TableColumn tblclmnMax = new TableColumn(tableRes.get(ActiveItem), SWT.CENTER);
            		tblclmnMax.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeMinmax.png"));
            		tblclmnMax.setWidth(98);
            		tblclmnMax.setText("Max");
	                  
            		TableColumn tblclmnVariance = new TableColumn(tableRes.get(ActiveItem), SWT.CENTER);
            		tblclmnVariance.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeVariance.png"));
            		tblclmnVariance.setWidth(115);
            		tblclmnVariance.setText("Variance");
	                  
            		TableColumn tblclmnMode = new TableColumn(tableRes.get(ActiveItem), SWT.CENTER);
            		tblclmnMode.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeMode.png"));
            		tblclmnMode.setWidth(107);
            		tblclmnMode.setText("Mode");
	                
            		//si on a importé un fichier
            		if(calc.fileImported()) {
            			//we get col. number
            			int colNb = dfp.getNbDataColumns();
            			TableItem[] tableItem = new TableItem[colNb];
            			for(int i = 0; i < colNb; i++) {
            				tableItem[i] = new TableItem(tableRes.get(ActiveItem), SWT.NONE);
            				try {
        						tableItem[i].setText(new String[] {""+(i+1),calc.getMean(i), calc.getMedian(i),calc.getStandDeviation(i),calc.getMin(i),calc.getMax(i),calc.getVariance(i),calc.getMode(i)});
        					} catch (Exception e1) {
        						e1.printStackTrace();
        					}
            			}                		
                		
                		lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
						lblInfo.setText("Displaying calcul stat for actual data file");
            		}else {
            			lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
						lblInfo.setText("Error : No data found ! you must import a data file first !");
            		}
            		
            		
            		scrolledComposite.get(ActiveItem).setContent(tableRes.get(ActiveItem));
            		scrolledComposite.get(ActiveItem).setMinSize(tableRes.get(ActiveItem).computeSize(SWT.DEFAULT, SWT.DEFAULT));	
            	}
                              	
            });
            btnModuleStatistique.setSelection(true);
            btnModuleStatistique.setText("Statistics");
            btnModuleStatistique.setToolTipText("Show statistical calculations");
            new Label(composite_3, SWT.NONE);
            
            final ToolBar toolBar = new ToolBar(composite_3, SWT.RIGHT);
            
            final ToolItem tltmDropdownItem = new ToolItem(toolBar, SWT.DROP_DOWN);
            tltmDropdownItem.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeGraphics.png"));
            tltmDropdownItem.addSelectionListener(new SelectionAdapter() {
            	@Override
            	public void widgetSelected(SelectionEvent e) {
            		if (e.detail == SWT.ARROW) {
            			Rectangle rect = tltmDropdownItem.getBounds();
            			Point pt = new Point(rect.x, rect.y + rect.height);
            			pt = toolBar.toDisplay(pt);
            			menu_n.setLocation(pt.x, pt.y);
            			menu_n.setVisible(true);
            		}
            	}
            });
            tltmDropdownItem.setText("Visual Graphic");
            tltmDropdownItem.setToolTipText("VisualGraphic Modul");
            new Label(composite_3, SWT.NONE);
                  
            
            final Label label = new Label(composite_3, SWT.SEPARATOR | SWT.VERTICAL);
            GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
            gd_label.heightHint = 18;
            label.setLayoutData(gd_label);
            new Label(composite_3, SWT.NONE);
            
            Button btnNewButton_1 = new Button(composite_3, SWT.FLAT);
            btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            	@Override
            	public void widgetSelected(SelectionEvent e) {
            		
            		String nomFichier;
    	      		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
    	      		dialog.setFilterExtensions(new String[] {"*.*" });
    	      		nomFichier = dialog.open();
    	      			
    	      		if(nomFichier != null) {
    	      			    	      			
    	      			Display.getDefault().asyncExec(new Runnable() {
    	      				public void run() {
    	      				
    							try {
    								pb.open();
    							} catch (InterruptedException e) {
    								e.printStackTrace();
    							}
    							
        	      			}
    	      			});
    	      			
        	      		//set file
    	      			try {
							dfp.setFilePropreties(nomFichier);
						} catch (IOException e1) {
							lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
    						lblInfo.setText("Error : Failed to set file propreties !");
						}
    	      			
        	      		dfp.setDataFile();//setFileName(nomFichier);
        	      		calc.setInputData(dfp.getData());
        			     int Nblines = 1;
        			     Nblines = dfp.getNbLines();
        			     spinner.setMinimum(1);
        			     spinner.setMaximum(Nblines);
        			     
        			     spinner_1.setMinimum(1);
        			     spinner_1.setMaximum(Nblines);
        			     
        			     
        			     int NbCol = 0;
        			     NbCol = dfp.getNbDataColumns();
        			     
        			     if(NbCol > 0) {
    				    	 String[] items = new String[NbCol];
    				    	 for(int i=0; i < NbCol; i++) {
    				    		 items[i] = ""+(i+1);
    				    	 }
    				    	 combo.setItems(items);
    				    	 spinner_2.setMaximum(NbCol);
    				         spinner_2.setMinimum(1);
    				         spinner_3.setMaximum(NbCol);
    				         spinner_3.setMinimum(1);
    				     }
        			     String[] obj = dfp.getObjectNames();
        			     combo_1.setItems(obj);
        	      		//info
        			    if(NbCol > 0 && Nblines >= 1) {
        			    	lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
        	      			lblInfo.setText("The file was succefully imported ! *** File summary : "+ Nblines + " lines, "+NbCol+ " Columns, "+ dfp.getNbObjectFound() +" Objects, "+dfp.getNbMissingData()+" Missing data ! ***");
        	      			pb.setEnd();
        			    }else {
      						lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
    						lblInfo.setText("Error : Failed to read data file !");
    						pb.setEnd();
    						calc.clearImportedFile();
      					}      			    
    	      		}
    	      		
            	}
            });
            btnNewButton_1.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeImport.png"));
            btnNewButton_1.setToolTipText("Import data file");
            
            
            Button btnNewButton_3 = new Button(composite_3, SWT.FLAT);
            btnNewButton_3.addSelectionListener(new SelectionAdapter() {
            	@Override
            	public void widgetSelected(SelectionEvent e) {
            		String nomFichier;
    	      		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
    	      		
    	      		if(calc.fileImported()) {
	    	      		//to get the tan name
	    	      		int ActiveItem = getActiveTabItem(tabFolder);
	    	      		StringTokenizer st = new StringTokenizer(tbtmResults.get(ActiveItem).getText(),"|");
	    	      		String title = st.nextToken();
	    	      		
	    	      		dialog.setFileName("Export_"+title);
	    	      		if(title.equals("Statistical Calculs") || title.startsWith("Histogram") || title.startsWith("Scatter Plot")) {
	    	      		
		    	      		dialog.setFilterExtensions(new String[] { "*.*" });
		    	      		nomFichier = dialog.open();
		    	      		
		    	      		
		    	      		
		    	      		//exportation
		    	      		if(nomFichier != null) {
		    	      			File f = new File(nomFichier);
			    	      		if(f.exists()) {
			    	      			f.delete();
			    	      		}
		    	      			
		    	      			CorpPdf corp = new CorpPdf("STATICAL CALCULS SUMMARY", dfp.getNbLines(), dfp.getNbDataColumns(), dfp.getNbObjectFound(), dfp.getObjectNames(), dfp.getNbMissingData());
		    		    		Document document = new Document();
		    		    		
		    		    		//test si bon extension
		    		    		if(nomFichier.endsWith(".pdf")) {
		    		    			try {
		        		    			PdfWriter.getInstance(document, 
		        		    				new FileOutputStream(nomFichier));
		        		    		} catch (FileNotFoundException e1) {
		        		    			e1.printStackTrace();
		        		    		} catch (DocumentException e1) {
		        		    			e1.printStackTrace();
		        		    		}
		    		    		}else {
		    		    			nomFichier = nomFichier.concat(".pdf");
		    		    			try {
		        		    			PdfWriter.getInstance(document, 
		        		    				new FileOutputStream(nomFichier));
		        		    		} catch (FileNotFoundException e1) {
		        		    			e1.printStackTrace();
		        		    		} catch (DocumentException e1) {
		        		    			e1.printStackTrace();
		        		    		}
		    		    		}
		    		    		
		    		    		//get all result
		    		    		double[] tabMean = calc.getMeans(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
		    		    		double[] tabMediane = calc.getMedians(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
		    		    		double[] tabSD = calc.getSDs(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
		    		    		double[] tabMin = calc.getMins(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
		    		    		double[] tabMax = calc.getMaxs(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
		    		    		double[] tabVar = calc.getVariances(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
		    		    		String[] tabMode = calc.getModes(dfp.getNbDataColumns());//new double[fp.getNbOfColumns()-1];
		    		    		
		    		    		if(title.equals("Statistical Calculs")) {
		    		                corp.createTab(document, dfp.getNbDataColumns(), tabMean, tabMediane, tabSD, tabMin, tabMax, tabVar, tabMode);
		    		                try {
		    		                 document.add(corp.paraHeader);
		    		                 document.add(corp.paraIntro);
		    		                 document.add(corp.paraResume);
		    		                 document.add(corp.paraResume1);
		    		                 document.add(corp.paraResume2);
		    		                 
		    		                 document.add(corp.paraResume4);
		    		                 //for(int i=0;i<dfp.getNbObjectFound();i++){
		    		                  document.add(corp.paraResume5);
		    		                 //}
		    		                 
		    		                 document.add(corp.paraResume7);
		    		                 document.add(corp.table);
		    		                } catch (DocumentException ex) {
		    		                 ex.printStackTrace();
		    		                }
		    		                document.close();
		    		               }else if(title.equals("Histogram")) {
		    		                try {
		    		                 document.open();
		    		                 document.add(corp.paraHeader);
		    		                 document.add(corp.paraIntro);
		    		                 document.add(corp.paraResume);
		    		                 document.add(corp.paraResume1);
		    		                 document.add(corp.paraResume2);
		    		                 
		    		                 document.add(corp.paraResume4);
		    		                 //for(int i=0;i<dfp.getNbObjectFound();i++){
		    		                  document.add(corp.paraResume5);
		    		                 //}
		    		                 
		    		                 document.add(corp.paraResume8);
		    		                 document.add(corp.table);
		    		                } catch (DocumentException ex) {
		    		                 ex.printStackTrace();
		    		                }
		    		                corp.createImage(chart[ActiveItem], document);
		    		                document.close();
		    		    		}else if(title.equals("Scatter Plot")) {
		    		                try {
		       		                 document.open();
		       		                 document.add(corp.paraHeader);
		       		                 document.add(corp.paraIntro);
		       		                 document.add(corp.paraResume);
		       		                 document.add(corp.paraResume1);
		       		                 document.add(corp.paraResume2);
		       		                 
		       		                 document.add(corp.paraResume4);
		       		                 //for(int i=0;i<dfp.getNbObjectFound();i++){
		       		                  document.add(corp.paraResume5);
		       		                 //}
		       		                 
		       		                 document.add(corp.paraResume9);
		       		                 document.add(corp.table);
		       		                } catch (DocumentException ex) {
		       		                 ex.printStackTrace();
		       		                }
		       		                corp.createImage(sp_chart[ActiveItem], document);
		       		                document.close();
		    		    		}
		    		    		lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
		    	      			lblInfo.setText("*** The results was succefully exported  ***");
		    	      		}
	    	      		}else {
	    	      			lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
							lblInfo.setText("To use export to PDF function you must be on either statistical calculations, Histogram, or Scatter Plot tab ! ");
	    	      		}
    	      		}else {
    	      			lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeWarning.png"));
						lblInfo.setText("Error : No data found ! you must import a data file first !");
    	      		}
    	      		
            	}
            });
            btnNewButton_3.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconePdf.png"));
            btnNewButton_3.setToolTipText("Export to PDF");
            
            Button btnAbout = new Button(composite_3, SWT.FLAT);
            btnAbout.addSelectionListener(new SelectionAdapter() {
            	@Override
            	public void widgetSelected(SelectionEvent e) {
            		About ab = new About(shell,SWT.ID_ABOUT);
            		ab.open();
            	}
            });
            btnAbout.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
            btnAbout.setToolTipText("About");
            new Label(composite_3, SWT.NONE);
            
                                    
            Label label_1 = new Label(composite_3, SWT.SEPARATOR);
            GridData gd_label_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
            gd_label_1.heightHint = 18;
            label_1.setLayoutData(gd_label_1);
            label_1.setText("...");
            new Label(composite_3, SWT.NONE);
            viewForm.setLayoutData(BorderLayout.CENTER);
            
            viewForm.setContent(tabFolder);
            
            tbtmResults.add(new TabItem(tabFolder, SWT.NONE));
            tbtmResults.get(0).setText("Home");
            tbtmResults.get(0).setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeHome.png"));
            scrolledComposite.add(new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL));
            
            //
            tableRes.add(new Table(scrolledComposite.get(0), SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI));
           

                       
            
            Button btnNewView = new Button(composite_3, SWT.FLAT | SWT.CENTER);
            btnNewView.addSelectionListener(new SelectionAdapter() {
            	@Override
            	public void widgetSelected(SelectionEvent e) {
            		//ajout d'une vue
            		int j = tabFolder.getItemCount();
            		tbtmResults.add(new TabItem(tabFolder, SWT.NONE));
            		if(j > 0)
            			tbtmResults.get(j).setText("View |"+j);
            		else
            			tbtmResults.get(j).setText("View");
            		
            		scrolledComposite.add(new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL));
            		tableRes.add(new Table(scrolledComposite.get(j), SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI));
            		tbtmResults.get(j).setControl(scrolledComposite.get(j));
            		
            		
            	}
            });
            btnNewView.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/btn_plus.png"));
            btnNewView.setToolTipText("Add new tab");
            
            Button btnDelView = new Button(composite_3, SWT.FLAT);
            btnDelView.addSelectionListener(new SelectionAdapter() {
            	@Override
            	public void widgetSelected(SelectionEvent e) {
            		//suppression de la vue active
            		
            		int activeItem = getActiveTabItem(tabFolder);
            		if(tabFolder.getItemCount() > 1) {
            			scrolledComposite.get(activeItem).dispose();
                		tbtmResults.get(activeItem).dispose();
                		scrolledComposite.remove(activeItem);
                		tbtmResults.remove(activeItem);
                		tableRes.remove(activeItem);
                		
                		for(int i = activeItem; i < tabFolder.getItemCount(); i++) {
                			StringTokenizer st = new StringTokenizer(tbtmResults.get(i).getText(),"|");
                			if(i>0)
                				tbtmResults.get(i).setText(st.nextToken()+"|"+i);
                			else
                				tbtmResults.get(i).setText(st.nextToken());
                		}
            		}else {
            			lblInfo.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/IconeAbout.png"));
    	      			lblInfo.setText("Cannot delete principal tab, close window if you want to quit the application");
            		}
            			
            		
            	}
            });
            GridData gd_btnDelView = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
            gd_btnDelView.heightHint = 29;
            
            btnDelView.setLayoutData(gd_btnDelView);
            btnDelView.setImage(SWTResourceManager.getImage(InterfaceApp.class, "/resources/imgs/btn_delete.png"));
            btnDelView.setToolTipText("Delete current tab");
      
            if(home) {	            
	            
            	StyledText  styledText = new StyledText(scrolledComposite.get(0), SWT.READ_ONLY);
    		    
    		    styledText.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
                styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//                styledText.setSelectionForeground(SWTResourceManager.getColor(51, 153, 255));
                styledText.setText("Welcome on Stat App !\r\n\r\nThis application allows you to achieve your statistical calculations, to have graphical display:\r\n" +
                		"Histogram and Scatter Plot.\r\n\r\n" +
                		"Running : \r\n- Start by importing a file using the import button in the menu \r\n\n" +
                		"- then choose the  file you want \r\n\n" +
                		"The data of the the imported file contains decimal numbers arranged in columns \r\n"+
                		"and the columns separated by commas which are not reused. \r\n\n" +
                		"Exemple:\r\n"+
                		"\t - Ce qu’il faut faire	              5.1,3.5,1.4,0.2,Iris-setosa\r\n"+
                		"\t - Ce qu’il ne faut pas faire	      5.1 :3.5 :1.4 :0.2:Iris-setosa\r\n"+
                		"\t - Ou 				                  5.1;3.5;1.4;0.2;Iris-setosa\r\n\n"+
                		"Once the user has selected the file, he can see in the dialog box at the bottom\r\n" +
                		"of the page, that the file has been successfully imported and have information \r\n"  +
                		"on the file.\r\n\n" +
                		"When the user clicks on the 'statistics' button, he can view statistical results\r\n" +
                		"in tabular form\r\n\n" +
                		"By clicking on 'visual graphics' button, the user can view results as:\r\n" +
                		"\t - Histogram\r\n"+
                		"\t - Scatter Plot\r\n\n" +
                		"The user can export the results in PDF format which contains the results \r\n" +
                		" of the active tab");
                styledText.setEnabled(false);
                styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
                styledText.setLeftMargin(5);
                styledText.setDoubleClickEnabled(false);
                styledText.setEditable(false);	            
	            scrolledComposite.get(0).setExpandVertical(true);
	            scrolledComposite.get(0).setExpandHorizontal(true);
	            tbtmResults.get(0).setControl(scrolledComposite.get(0));
	            
	            scrolledComposite.get(0).setContent(styledText);
	            scrolledComposite.get(0).setMinSize(styledText.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	            
	            home = false;
            }	            
                    
	        /**
	         * 
	         */
	        shell.open();
	   	   	shell.layout();
	   	   	while (!shell.isDisposed()) {
	   	   		if (!display.readAndDispatch()) {
	                   display.sleep();
	   	   		}
	   	   	}
	 }
	 
	 /**
	  * 
	  * @param tabFolder
	  * @return l'onglet active
	  */
	   public static int getActiveTabItem(TabFolder tabFolder) {
	    	int i;
			for(i = 0 ; i< tabFolder.getItemCount(); i++) {
				if(tabFolder.getItem(i).hashCode() == tabFolder.getSelection()[0].hashCode())
					break;
			}
	    	return i;
	    }
}