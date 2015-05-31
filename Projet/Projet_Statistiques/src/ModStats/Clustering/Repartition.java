package ModStats.Clustering;

import java.util.ArrayList;


public class Repartition {
	
	private ArrayList<ArrayList<Double[]>> repartition;
	
	public Repartition() {
		this.repartition = new ArrayList<ArrayList<Double[]>>();
	}
	
	public int getNbClusters() {
		return repartition.size();
	}
	
	public ArrayList<Double[]> getCluster(int i) {
		return repartition.get(i);
	}
	
	public void addCluster(ArrayList<Double[]> cluster) {
		this.repartition.add(cluster);
	}
	
	public void addAllClusters(ArrayList<ArrayList<Double[]>> clusterList) {
		this.repartition.addAll(clusterList);
	}

	public ArrayList<ArrayList<Double[]>> getAllClusters() {
		return this.repartition;
	}
	
}
