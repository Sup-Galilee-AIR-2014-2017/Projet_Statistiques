package ModStats.Clustering;

import java.util.ArrayList;


public class ClusteringTools {
	
	public static class CoupleElements { public ArrayList<Double[]> Element1, Element2;} // Couple of two groups of elements
		
	
	// A Double[] type variable is a point (an element or a line in the input text file)
	public static ArrayList<ArrayList<Double[]>> HierarchicalClustering(ArrayList<Double[]> elementsList) {
		
		//TODO Gérer les effets de bord
		
		ArrayList<Repartition> repartitionsList = new ArrayList<Repartition>();
		CoupleElements coupleNearSets = new CoupleElements();
		
		Repartition lastRepartition = new Repartition();
		
		//Initialisation of the repartition list (each element is alone in its group).
		for (Double[] element : elementsList) {
			ArrayList<Double[]> group = new ArrayList<Double[]>();
			group.add(element);
			lastRepartition.addCluster(group);
		}		

		//Create all repartitions while there is not only one cluster remaining in the last repartition.
		while(lastRepartition.getNbClusters() > 1) {
			coupleNearSets = nearestPoints(lastRepartition);//FR = Les points les plus proches
			
			ArrayList<Double[]> cluster1 = new ArrayList<Double[]>();

			cluster1.addAll(coupleNearSets.Element1);
			cluster1.addAll(coupleNearSets.Element2);
						
			ArrayList<ArrayList<Double[]>> otherClusters = new ArrayList<ArrayList<Double[]>>();//Other elements (alone element or group of elements)
			
			for (ArrayList<Double[]> oldCluster : lastRepartition.getAllClusters()) {
				if(oldCluster != coupleNearSets.Element1 && oldCluster != coupleNearSets.Element2) {
					otherClusters.add(oldCluster);
				}
			}
			
			lastRepartition = new Repartition();
			lastRepartition.addCluster(cluster1);
			lastRepartition.addAllClusters(otherClusters);
			
			if(!otherClusters.isEmpty())
				repartitionsList.add(lastRepartition);
		}

	
		
		//Check each repartition and retrieve the best one (uses indice of Davies-Bouldin).
		double minIndiceDaviesBouldin = -1;
		Repartition bestRepartition = null;
		for (Repartition repartition : repartitionsList) {
			double indiceDavies = getDaviesBouldinIndice(repartition);
			if(indiceDavies < minIndiceDaviesBouldin || bestRepartition == null) {
				minIndiceDaviesBouldin = indiceDavies;
				bestRepartition = repartition; 
			}
		}		
		
		return bestRepartition.getAllClusters();
	}
	
	public static double getDaviesBouldinIndice(Repartition repartition) {
		double DB = 0;
		
		//TODO écrire le commentaire du dessous en anglais
		int k = repartition.getNbClusters(); //ici, k vaut le nombre de groupes (pas sur, k doit peut être être le nombre de centres définis aléatoirement tout au début dde l'algo).

		for(int i=0; i< k; i++) {
			ArrayList<Double[]> group = repartition.getCluster(i);
			
			
			
			double meanDistanceElementToBarycentreOfGroup = getMeanDistanceElementToBarycentreOfGroup(group);
			
			ArrayList<Double> calculMax = new ArrayList<Double>();
			for (int j=0; j<k; j++) {				
				if(i != j){
					ArrayList<Double[]> groupJ = repartition.getCluster(j);
					double meanDistanceInGroupJ = getMeanDistanceElementToBarycentreOfGroup(groupJ);
					double distanceBetweenBarycentres = distanceBetweenTwoElements(getBarycentre(group), getBarycentre(groupJ));
					calculMax.add((meanDistanceElementToBarycentreOfGroup + meanDistanceInGroupJ) / distanceBetweenBarycentres);
				}					
			}
			
			double max = getMaxValue(calculMax);
			DB += max;
		}
		
		DB = DB / k;

		return DB;
	}
		
		
		
	
	//------------------ Private tools -----------------
	
	
	
	
	//TODO changer le nom en nearestElements
	public static CoupleElements nearestPoints(Repartition repartition) {
		CoupleElements coupleElements = new CoupleElements();
		int matriceDimension = repartition.getNbClusters();
		double [][] matriceOfDistances = new double[matriceDimension][matriceDimension];
		
		//Fill the matrice under middle of the dimension.
		for(int i=0;i < matriceDimension; i++) {
			for(int j=0; j<i; j++) {
				Double [] point1 = getBarycentre(repartition.getCluster(i));
				Double [] point2 = getBarycentre(repartition.getCluster(j));
				double distance = distanceBetweenTwoElements(point1, point2);
				matriceOfDistances[i][j] = distance;
			}
		}
		
		//Retrieve the two indices of the smallest distance value of the matrice.
		double smallestDistance = -1;
		int indiceI= -1, indiceJ = -1;
		for(int i=0;i < matriceDimension; i++) {
			for(int j=0; j<i; j++) {
				if(smallestDistance == -1 || matriceOfDistances[i][j] < smallestDistance) {
					smallestDistance = matriceOfDistances[i][j];
					indiceI = i;
					indiceJ = j;
				}
			}
		}
		
		//Retrieve 
		coupleElements.Element1 = repartition.getCluster(indiceI);//TODO renommer les elements en cluster
		coupleElements.Element2 = repartition.getCluster(indiceJ);				
		
		return coupleElements;
	}

	private static Double[] getBarycentre(ArrayList<Double[]> pointsList) {
		if(pointsList.size() > 0) {
			int nbDimensions = pointsList.get(0).length;
			int nbPoints = pointsList.size();
			
			Double [] barycentre = new Double [nbDimensions];
			
			for(int j = 0; j<nbDimensions; j++) {
				double meanForDimension = 0;
				for(int i = 0; i<nbPoints; i++) {
					double valuePoint = pointsList.get(i)[j];
					meanForDimension += valuePoint;
				}
				barycentre[j] = meanForDimension / nbPoints;
			}
			return barycentre;
		}else {
			return new Double[0];
		}
		
		
	}

	public static double distanceBetweenTwoElements(Double [] point1, Double [] point2) {
		double distance = 0;
		//TODO s'assurer que les deux elements sont de même dimensions
		int tabDimension = point1.length;
		for (int i = 0; i< tabDimension; i++) {
			distance += Math.pow(point2[i] - point1[i], 2);
		}
		distance = Math.sqrt(distance);
		return distance;		
	}
	
	private static double getMeanDistanceElementToBarycentreOfGroup(ArrayList<Double[]> group) {
		
		Double[] barycentreGroup = getBarycentre(group);
		
		double mean = 0;
		for (Double[] element : group) {
			double distanceElementToBarycentre = distanceBetweenTwoElements(barycentreGroup, element);
			mean += distanceElementToBarycentre;
		}
		
		mean /= group.size();
		
		return mean;
	}
	
	private static double getMaxValue(ArrayList<Double> calculMax) {
		double max = 0;
		for (Double value : calculMax) {
			if(value> max)
				max = value;
		}
		return max;
	}
}
