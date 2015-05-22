package ModVisual;
import java.awt.Color;
import java.awt.Shape;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;	

import ModStats.InputObject;

public class ScatterPlot_1 {
	
	public XYSeries series[];
    public XYSeriesCollection seriesCollection;
	boolean show = true;
	boolean toolTips = true;
	boolean urls = true;
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);



	public ScatterPlot_1(){
		 seriesCollection = new XYSeriesCollection();		 
	}
	
	public void setInputObject(InputObject[] inob) {
		series = new XYSeries[inob.length];
		 
		 //fixe les noms
		 for(int i = 0; i < series.length; i++)
			 series[i] = new XYSeries(inob[i].getName());
	}
	
	
    public void addValues(XYSeries serie, double[] value1,double[] value2){
    	for(int i=0; i<value1.length; i++){
            serie.add(value1[i],value2[i]);
        }
        seriesCollection.addSeries(serie);
    }
    
    public void clearSeries() {
    	seriesCollection.removeAllSeries();
    }
    
    public JFreeChart createChart(int nb,int xaxis,int yaxis) {
    	
		Color[] tabColor = { Color.blue, Color.green,  Color.red, Color.yellow, Color.orange,  Color.pink, Color.cyan, Color.darkGray, Color.gray,  Color.lightGray, Color.magenta,
				Color.black
		};
		
    	JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot", "X : "+"Column "+xaxis, "Y : "+"Column "+yaxis, this.seriesCollection, PlotOrientation.VERTICAL, true, true, false);        
        
        XYPlot plot = (XYPlot) chart.getPlot();
        
        Shape[] cross = DefaultDrawingSupplier.createStandardSeriesShapes();
        
        for(int i = 0; i < nb; i++) {
        	renderer.setSeriesPaint(i, tabColor[i%tabColor.length]);
        	renderer.setSeriesShape(i, cross[1]);
        }

        
        plot.setRenderer(renderer);
        return chart;
    }
    
    public void changeColor(int index, Color color) {
    	renderer.setSeriesPaint(index, color);
    }
    
}
