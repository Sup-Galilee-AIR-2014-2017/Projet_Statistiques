package ModVisual;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;



public class HistoG {
DefaultCategoryDataset dataset;
String plotTitle; 
String xaxis;
String yaxis;
PlotOrientation orientation;
boolean show = true; 
boolean toolTips = true;
boolean urls = true;

	public HistoG(DefaultCategoryDataset dataset, String plotTitle, String xaxis, String yaxis, PlotOrientation orientation){
		this.dataset=dataset;
		this.plotTitle=plotTitle;
		this.xaxis=xaxis;
		this.yaxis=yaxis;
		this.orientation=orientation;
		//dataset.setType(HistogramType.RELATIVE_FREQUENCY);
	}
	
	public void addValues(double[] value,int col){
		for(int i=0; i<value.length; i++){
			this.dataset.addValue(value[i], "Column "+col, ""+i);
		}
	}
	
	public JFreeChart createChart(){
		JFreeChart chart=ChartFactory.createBarChart(this.plotTitle, this.yaxis, this.xaxis, 
				this.dataset, this.orientation, this.show, this.toolTips, this.urls);
		return chart; 
	}
	
	public String getPlotTitle(){
		return this.plotTitle;
	}
}
