package ModStats.Clustering;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import ModStats.DoCalculs;
import ModStats.Clustering.ClusteringTools.CoupleElements;

public class ClusteringToolsTest {

	@Test
	public void HierarchicalClusteringTest_TwoClusterExpected() {
		
		ArrayList<Double[]> elementsList = new ArrayList<Double[]>();
					
		elementsList.add(new Double[]{4.5, 78.9, 25.7});
		elementsList.add(new Double[]{1.8, 10.7, 79.1});

		elementsList.add(new Double[]{7.4, 80.4, 30.8});
		elementsList.add(new Double[]{5.2, 11.4, 75.6});
		
		elementsList.add(new Double[]{3.6, 75.9, 29.2});
		elementsList.add(new Double[]{3.3, 15.0, 73.0});
		
		elementsList.add(new Double[]{4.8, 72.8, 20.5});
				
		ArrayList<ArrayList<Double[]>> result = ClusteringTools.HierarchicalClustering(elementsList);
		
		assertEquals(2, result.size());
	}
	
	@Test
	public void HierarchicalClusteringTest_ThreeClusterExpected() {
		
		ArrayList<Double[]> elementsList = new ArrayList<Double[]>();
					
		elementsList.add(new Double[]{4.5, 78.9, 25.7});
		elementsList.add(new Double[]{1.8, 10.7, 79.1});
		elementsList.add(new Double[]{102.0, 120.7, 79.1});
		

		elementsList.add(new Double[]{7.4, 80.4, 30.8});
		elementsList.add(new Double[]{5.2, 11.4, 75.6});
		elementsList.add(new Double[]{100.0, 111.7, 79.1});
		
		elementsList.add(new Double[]{3.6, 75.9, 29.2});
		elementsList.add(new Double[]{3.3, 15.0, 73.0});
		elementsList.add(new Double[]{105.0, 115.7, 79.1});
		
		elementsList.add(new Double[]{4.8, 72.8, 20.5});
				
		ArrayList<ArrayList<Double[]>> result = ClusteringTools.HierarchicalClustering(elementsList);
		
		assertEquals(3, result.size());
	}
	
	@Test
	public void DaviesBouldin_BigIndiceExpected_Test() {
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
		
		
		double indice = DoCalculs.getDaviesBouldinIndice(repartition);
		
		assertTrue(indice > 3);;
	}
	
	@Test
	public void DaviesBouldin_SmallIndiceExpected_Test() {
		ArrayList<ArrayList<Double>> repartition = new ArrayList<ArrayList<Double>>();
		repartition.add(new ArrayList<Double>());
		repartition.add(new ArrayList<Double>());
		repartition.add(new ArrayList<Double>());
		repartition.get(0).add(4.0);
		repartition.get(0).add(2.8);
		repartition.get(0).add(1.4);
		
		repartition.get(1).add(55.2);
		repartition.get(1).add(50.8);
		repartition.get(1).add(58.9);
		
		repartition.get(2).add(28.0);
		repartition.get(2).add(27.8);
		repartition.get(2).add(29.4);
		
		
		double indice = DoCalculs.getDaviesBouldinIndice(repartition);
		
		assertTrue(indice < 1);
	}
	
	@Test
	public void nearestPointsTest() {
		
		Repartition repartition = new Repartition();
		ArrayList<Double[]> cluster1 = new ArrayList<Double[]>();
		ArrayList<Double[]> cluster2 = new ArrayList<Double[]>();
		ArrayList<Double[]> cluster3 = new ArrayList<Double[]>();
		ArrayList<Double[]> cluster4 = new ArrayList<Double[]>();
		
		cluster1.add(new Double[] {12.4,15.6,4.2});
		cluster2.add(new Double[] {77.4,88.6,44.2});
		cluster3.add(new Double[] {10.4,10.6,5.2});
		cluster3.add(new Double[] {15.4,22.8,3.1});
		cluster4.add(new Double[] {1.4,1.6,4.2});
		
		repartition.addCluster(cluster1);
		repartition.addCluster(cluster2);
		repartition.addCluster(cluster3);
		repartition.addCluster(cluster4);
			
		
		CoupleElements result = ClusteringTools.nearestPoints(repartition);
		
		assertTrue(result.Element1 == cluster3 && result.Element2 == cluster1 || 
				result.Element1 == cluster1 && result.Element2 == cluster3);
	}
		
	@Test
	public void distanceBetweenTwoElementsTest() {
		Double [] point1 = {2.0, 3.0};
		Double [] point2 = {7.0, 1.0};		
		
		double result = ModStats.Clustering.ClusteringTools.distanceBetweenTwoElements(point1, point2);
		
		assertEquals(Math.sqrt(29), result, 0.0);
	}
	
	
	
	
}
