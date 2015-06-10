package ModStats.Clustering;

import java.util.ArrayList;

import ModStats.DoCalculs;

//Before using the cluster methods, we have to check that there is no missing data before calling these methods.
public class ClusteringTools {
	
	public static class CoupleElements { public ArrayList<Double[]> Element1, Element2;} // Couple of two groups of elements
		
	
	// A Double[] type variable is a point (an element or a line in the input text file)
	public static Repartition HierarchicalClustering(ArrayList<Double[]> elementsList) {
		
		//TODO G�rer les effets de bord
		
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
		
		return bestRepartition;
	}
	
	public static double getDaviesBouldinIndice(Repartition repartition) {
		double DB = 0;
		
		int k = repartition.getNbClusters(); //ici, k vaut le nombre de groupes (pas sur, k doit peut �tre �tre le nombre de centres d�finis al�atoirement tout au d�but dde l'algo).

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
	
	
	public static Repartition K_Mean(ArrayList<Double[]> allElements) {
		ArrayList<Repartition> repartitionsList = new ArrayList<Repartition>();
		ArrayList<Double> daviesBouldinIndicesList = new ArrayList<Double>();
		Repartition bestRepartition = null;
		
		
		for(int k = 2; k <= 50; k++) {
			Repartition repartition = K_MeanForK(allElements, k);
			Double daviesIndice = getDaviesBouldinIndice(repartition);
			
			repartitionsList.add(repartition);
			daviesBouldinIndicesList.add(daviesIndice);
		}
		
		//Retrieve the best repartition
		Double bestIndice = null;
		for (int i = 0; i < repartitionsList.size(); i++) {
			Double currentIndiceDavies = daviesBouldinIndicesList.get(i);
			if(bestIndice == null || currentIndiceDavies < bestIndice){
				bestIndice = currentIndiceDavies;
				bestRepartition = repartitionsList.get(i);
			}				
		}
		
		if(bestRepartition.getNbClusters() != 2)
			System.out.println();
		
		return bestRepartition != null ? bestRepartition : new Repartition(); //It could be null if the input list of the function was empty, but it shouldn't happen
	}
	
	public static Repartition K_MeanForK(ArrayList<Double[]> allElements, int k) {
				
		
		
		Repartition clusters = new Repartition();
		Repartition oldClusters = new Repartition();
		
		ArrayList<Double []> centers = new ArrayList<Double []>();
		
		Double [] minValue = getMinValueFromList(allElements);
		Double [] maxValue = getMaxValueFromList(allElements); 
		
		//Add k random elements (centers) to the center list.
		for(int i = 0; i< k ; i++) {
			Double [] randomElement = new Double[minValue.length];
			
			for (int j = 0; j < randomElement.length; j++) {
				Double randomValue = minValue[j] + Math.random()*maxValue[j];
				randomElement[j] = randomValue;
			}
			
			centers.add(randomElement);
			clusters.addCluster(new ArrayList<Double[]>());
		}
		
		//First affectation
		for (Double [] element : allElements) {
			
			
			Double minDistance = null;
			int indiceCenterWithSmallestDistance = -1;
			
			for (int c = centers.size()-1; c >= 0 ; c--) {
				
				Double [] center = centers.get(c);
				

				double distanceFromCenter = distanceBetweenTwoElements(center, element);
				
				if(minDistance == null || distanceFromCenter < minDistance){
					minDistance = distanceFromCenter;
					indiceCenterWithSmallestDistance = c;
				}
				
			}
			
			clusters.getCluster(indiceCenterWithSmallestDistance).add(element);
			
		}
		
		
		
		//Test if a cluster is empty and remove it
		Repartition clustersToRemove = new Repartition();
		for (ArrayList<Double []> cluster : clusters.getAllClusters()) {
			if (cluster.isEmpty())
				clustersToRemove.addCluster(cluster);
				//clusters.removeCluster(cluster);
		}
		clusters.removeAllClusters(clustersToRemove);
		
				
		
		
		while(comparePartitions(clusters, oldClusters) == false){
			
			
			/*for (ArrayList<Double> cluster : oldClusters) {
				DoCalculs.removed(cluster);
			}*/
			
			oldClusters = new Repartition();
			
			for (int i=0 ; i< clusters.getNbClusters(); i++) {
				oldClusters.addCluster(clusters.getCluster(i));
			}
			
			for (int i = 0 ; i < clusters.getNbClusters(); i++) {
				centers.set(i, getBarycentre(clusters.getCluster(i)));
			}
			
			
			for(int i = clusters.getNbClusters()-1; i >= 0; i--) {
				
				ArrayList<Double []> cluster = clusters.getCluster(i);
				
				for (int j= cluster.size() -1; j >=0; j--) {
				
					Double [] elementCluster = cluster.get(j);
					
					Double minDistance = null;
					int indiceCenterWithSmallestDistance = -1;
					
					for (int c = clusters.getNbClusters()-1; c >= 0 ; c--) {
												
						Double [] center = centers.get(c);
						

						double distanceFromCenter = distanceBetweenTwoElements(center, elementCluster);
						
						if(minDistance == null || distanceFromCenter < minDistance){
							minDistance = distanceFromCenter;
							indiceCenterWithSmallestDistance = c;
						}
						
					}
					
					if(indiceCenterWithSmallestDistance != i) {
						clusters.getCluster(indiceCenterWithSmallestDistance).add(cluster.get(j));
						cluster.remove(j);
												
					}
				}
				
			}
			
			
				
			
		}
				
		
		return clusters;
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
	
	// Return a double [] with the min value by array indice (comparing each attribute) of the list. 
	public static Double [] getMinValueFromList(ArrayList<Double[]> liste) {
		
		if(liste.size() > 0) {
			int sizeOfValueArray = liste.get(0).length;
			Double [] min = new Double[sizeOfValueArray];
			
			// initialize all values to null;
			for (int i = 0; i <sizeOfValueArray; i++) {
				min[i] = null;
			}
			
			for (int i = 0; i < sizeOfValueArray; i++) {
				
				Double minValForAttribute = null;
				
				for(int j = 0; j < liste.size(); j++){
					double attributeValue = liste.get(j)[i];
					if(minValForAttribute == null || attributeValue < minValForAttribute)
						minValForAttribute = attributeValue;
				}
				
				min[i] = minValForAttribute;
			}
			return min;			
		}else 
			return new Double [0];

	}
	
	public static Double [] getMaxValueFromList(ArrayList<Double[]> liste) {
		
		if(liste.size() > 0) {
			int sizeOfValueArray = liste.get(0).length;
			Double [] max = new Double[sizeOfValueArray];
			
			// initialize all values to null;
			for (int i = 0; i <sizeOfValueArray; i++) {
				max[i] = null;
			}
			
			for (int i = 0; i < sizeOfValueArray; i++) {
				
				Double maxValForAttribute = null;
				
				for(int j = 0; j < liste.size(); j++){
					double attributeValue = liste.get(j)[i];
					if(maxValForAttribute == null || attributeValue > maxValForAttribute)
						maxValForAttribute = attributeValue;
				}
				
				max[i] = maxValForAttribute;
			}
			return max;			
		}else 
			return new Double [0];
		
	}
	

	private static boolean comparePartitions(Repartition cmp1, Repartition cmp2) {
		boolean cmp=true;	
		
		if(cmp1.getNbClusters() == cmp2.getNbClusters()) {
			for (int i = 0; i< cmp1.getNbClusters(); i++) {
				if(!compareClusters(cmp1.getCluster(i), cmp2.getCluster(i))) {
					cmp = false;
					break;
				}
			}
		}else {
			cmp = false;
		}
		
		return cmp;
	}
	

	public static boolean compareClusters(ArrayList<Double []> cmp1, ArrayList<Double []> cmp2) {
		ArrayList<Double []> cmp11= new ArrayList<Double []>();
		boolean cmp=true;
	
		
		if (cmp1.size() == cmp2.size()){
			cmp11.addAll(cmp1);
		
			for (int i=0 ; i < cmp2.size(); i++) {
				
						if (rchr(cmp2.get(i), cmp11)==true){
						
							cmp11.remove(cmp2.get(i));
						}
						
						else {
							cmp=false;
							break;
						}
			}	
						
		}	
		
		else {
			cmp=false;
		}
		
		return cmp;
	}
	
	//Research a 'var' value from 'list' and return true if it is found.
	public static boolean rchr(Double [] var, ArrayList<Double []> list) {
		
		boolean trouve=false;
		
			for (int j=0 ; j < list.size(); j++) {
				
						if (var == list.get(j)){
						
							trouve = true;	
						}			
			}						
		
		return trouve;
	}
	
	
}
