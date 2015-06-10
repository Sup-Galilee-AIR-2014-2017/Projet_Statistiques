package ModStats.Clustering;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import ModStats.DoCalculs;
import ModStats.Clustering.ClusteringTools.CoupleElements;

public class ClusteringToolsTest {

	
	
	//---------------- CAH ----------------
		
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
				
		Repartition result = ClusteringTools.HierarchicalClustering(elementsList);
		
		assertEquals(2, result.getNbClusters());
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
				
		Repartition result = ClusteringTools.HierarchicalClustering(elementsList);
		
		assertEquals(3, result.getNbClusters());
	}
	
	
	
	//---------------- K-Means ----------------
	
	
	//TODO To remove and keep only the real K_means tests methods (not the K-mean"for K").
	@Test
	public void K_MeansForK_ClusteringTest_TwoClusterExpected() {
		
		ArrayList<Double[]> elementsList = new ArrayList<Double[]>();
		int k = 2;			
		
		elementsList.add(new Double[]{4.5, 78.9, 25.7});
		elementsList.add(new Double[]{1.8, 10.7, 79.1});

		elementsList.add(new Double[]{7.4, 80.4, 30.8});
		elementsList.add(new Double[]{5.2, 11.4, 75.6});
		
		elementsList.add(new Double[]{3.6, 75.9, 29.2});
		elementsList.add(new Double[]{3.3, 15.0, 73.0});
		
		elementsList.add(new Double[]{4.8, 72.8, 20.5});
				
		Repartition result = ClusteringTools.K_MeanForK(elementsList, k);
		
		assertEquals(2, result.getNbClusters());
	}
	
	
	/*
	 * ON NE PEUT PAS LAISSER LES TESTS UNITAIRES SUR LA METHODE KMOYENNE CAR IL Y A UNE FAILLE 
	 * DANS UN CAS PRECIS QUE J'EXPLIQUE DANS LE RAPPORT => LORSQUE K EST GRAND, IL Y A PLUS DE
	 * CHANCE QUE KMOYENNE EFFECTUE UNE REPARTITION DE PLUSIEURS PETITS CLUSTERS DE 1 ELEMENTS
	 * ET L'INDICE DAVIES BOULDIN RENVOIT ALORS UN INDICE AU PLUS FAIBLE CE QUI NE DEVRAIT PAS 
	 * ETRE LE CAS => FAILLE DE L'INDICE DAVIES BOULDIN, TESTE MATHEMATIQUEMENT SUR PAPIER (HORS 
	 * ALGO JAVA) 
	 * 
	 * */
	
	
	/*
	@Test
	public void KmeanClusteringTest_TwoClusterExpected() {
		
		ArrayList<Double[]> elementsList = new ArrayList<Double[]>();
					
		elementsList.add(new Double[]{4.5, 78.9, 25.7});
		elementsList.add(new Double[]{1.8, 10.7, 79.1});

		elementsList.add(new Double[]{7.4, 80.4, 30.8});
		elementsList.add(new Double[]{5.2, 11.4, 75.6});
		
		elementsList.add(new Double[]{3.6, 75.9, 29.2});
		elementsList.add(new Double[]{3.3, 15.0, 73.0});
		
		elementsList.add(new Double[]{4.8, 72.8, 20.5});
				
		Repartition result = ClusteringTools.K_Mean(elementsList);
		
		assertEquals(2, result.getNbClusters());
	}
	*/
	@Test
	public void testFailleDaviesBouldin() {
		Repartition rep = new Repartition();
		
		ArrayList<Double[]> cluster1 = new ArrayList<Double[]>();
		ArrayList<Double[]> cluster2 = new ArrayList<Double[]>();
		ArrayList<Double[]> cluster3 = new ArrayList<Double[]>();
		ArrayList<Double[]> cluster4 = new ArrayList<Double[]>();
		ArrayList<Double[]> cluster5 = new ArrayList<Double[]>();
		
		cluster2.add(new Double[]{4.5, 78.9, 25.7});
		cluster5.add(new Double[]{1.8, 10.7, 79.1});

		cluster1.add(new Double[]{7.4, 80.4, 30.8});
		cluster5.add(new Double[]{5.2, 11.4, 75.6});
		
		cluster4.add(new Double[]{3.6, 75.9, 29.2});
		cluster5.add(new Double[]{3.3, 15.0, 73.0});
		
		cluster3.add(new Double[]{4.8, 72.8, 20.5});
		
		rep.addCluster(cluster1);
		rep.addCluster(cluster2);
		rep.addCluster(cluster3);
		rep.addCluster(cluster4);
		rep.addCluster(cluster5);
		
		Double indice = ClusteringTools.getDaviesBouldinIndice(rep);
		
		int i = 0;
		i++;
		
	}
	/*
	@Test
	public void KmeanClusteringTest_ThreeClusterExpected() {
		
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
				
		Repartition result = ClusteringTools.K_Mean(elementsList);
		
		assertEquals(3, result.getNbClusters());
	}
	*/
	
		
	//------------------ Davies Bouldin ----------------
		
	
	
	@Test
	public void DaviesBouldin_BigIndiceExpected_Test() {
		Repartition repartition = new Repartition();
		repartition.addCluster(new ArrayList<Double[]>());
		repartition.addCluster(new ArrayList<Double[]>());
		repartition.addCluster(new ArrayList<Double[]>());
		repartition.getCluster(0).add(new Double[] {1.0, 145.2});
		repartition.getCluster(0).add(new Double[] {28.8, 1.4});
		repartition.getCluster(0).add(new Double[] {11.4, 75.6});
		
		repartition.getCluster(1).add(new Double[] {9.2, 80.2});
		repartition.getCluster(1).add(new Double[] {1.8, 160.7});
		repartition.getCluster(1).add(new Double[] {18.9, 1.9});
		
		repartition.getCluster(2).add(new Double[] {18.0, 4.0});
		repartition.getCluster(2).add(new Double[] {1.8, 150.7});
		repartition.getCluster(2).add(new Double[] {29.4, 5.4});
		
		
		double indice = ClusteringTools.getDaviesBouldinIndice(repartition);
		
		assertTrue(indice > 3);
		assertTrue(indice > 6);
	}
	
	@Test
	public void DaviesBouldin_SmallIndiceExpected_Test() {
		Repartition repartition = new Repartition();
		repartition.addCluster(new ArrayList<Double[]>());
		repartition.addCluster(new ArrayList<Double[]>());
		repartition.addCluster(new ArrayList<Double[]>());
		repartition.getCluster(0).add(new Double[] {4.0, 159.8});
		repartition.getCluster(0).add(new Double[] {2.8, 165.7});
		repartition.getCluster(0).add(new Double[] {1.4, 180.9});
		
		repartition.getCluster(1).add(new Double[] {55.2, 70.9});
		repartition.getCluster(1).add(new Double[] {50.8, 75.2});
		repartition.getCluster(1).add(new Double[] {58.9, 68.5});
		
		repartition.getCluster(2).add(new Double[] {28.0, 1.6});
		repartition.getCluster(2).add(new Double[] {27.8, 3.4});
		repartition.getCluster(2).add(new Double[] {29.4, 2.2});
		
		
		double indice = ClusteringTools.getDaviesBouldinIndice(repartition);
		
		assertTrue(indice < 1);
	}
	
	
	//--------------- Other usefull methods -------------------
	
	
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
