package ModStats;

import java.util.ArrayList;

import ModStats.Clustering.Repartition;


public class DoCalculs {
	
	private static InputObject io;

	
	public void setInputData(InputObject in) {
		io = in;
	}
	
	public static InputObject getIO() {
		if(fileImported())
			return io;
		else
			return new InputObject();
	}

	public static boolean fileImported() {
			return (io != null);
	}
	
	public void clearImportedFile() {
		io = null;
	}
	
	
	public String getMean(int col) {
		//InputObject lt = DoCalculs.getValues();
		String result = "";// = new String();
		
		//get values
		ValuesList values = new ValuesList();
		//BufferedReader ReadIn = getBuffer();
		
		//get all values		
		for(int j = 0; j < io.getValuesList(col).size(); j++) {
			values.add(io.getValuesList(col).get(j));
		}
		result = ""+CalculStats.mean(values);
		return result;
	}
	
	public String getCoefCorl(int col1, int col2){
		String result ="";
		
		ValuesList values1 = new ValuesList();
		ValuesList values2 = new ValuesList();
		
		for (int j=0; j < io.getValuesList(col1).size();j++){
			values1.add(io.getValuesList(col1).get(j));
		}
		
		for (int j=0; j < io.getValuesList(col2).size();j++){
			values2.add(io.getValuesList(col2).get(j));
		}
		
		result = ""+CalculStats.coefCorel(values1, values2);
		
		return result;
	}
	
	public String getCoefAlphaRegression(int col1, int col2){
		String result ="";
		
		ValuesList values1 = new ValuesList();
		ValuesList values2 = new ValuesList();
		
		for (int j=0; j < io.getValuesList(col1).size();j++){
			values1.add(io.getValuesList(col1).get(j));
		}
		
		for (int j=0; j < io.getValuesList(col2).size();j++){
			values2.add(io.getValuesList(col2).get(j));
		}
		
		result = ""+CalculStats.coefAlphaRegression(values1, values2);
		
		return result;
	}
	
	public String getCoefBetaRegression(int col1, int col2){
		String result ="";
		
		ValuesList values1 = new ValuesList();
		ValuesList values2 = new ValuesList();
		
		for (int j=0; j < io.getValuesList(col1).size();j++){
			values1.add(io.getValuesList(col1).get(j));
		}
		
		for (int j=0; j < io.getValuesList(col2).size();j++){
			values2.add(io.getValuesList(col2).get(j));
		}
		
		result = ""+CalculStats.coefBetaRegression(values1, values2);
		
		return result;
	}
	

	//public double[][] getCoefCorls(int col1){g}
	
	
//	public String getMean(ValuesList vl) {
//		String result = "";// = new String();
//		
//		result = ""+CalculStats.mean(vl);
//		return result;
//	}
	
	public double[] getMeans(int NbCol) {
		
		double[] result = new double[NbCol];
		
		//get values
		ValuesList values = new ValuesList();
		
		for(int i = 0; i < NbCol; i++) {
			values.clear();
			for(int j = 0; j < io.getValuesList(i).size(); j++) {
				values.add(io.getValuesList(i).get(j));
			
			}
			result[i] = CalculStats.mean(values);
		}
		
		return result;
	}
	
	
	public String getMin(int col)  {
		//InputObject lt = DoCalculs.getValues();
		String result = "";// = new String();
		
		//get values
		ValuesList values = new ValuesList();
		//BufferedReader ReadIn = getBuffer();
		
		//get all values
		for(int j = 0; j < io.getValuesList(col).size(); j++) {
			values.add(io.getValuesList(col).get(j));
		}
		result = ""+CalculStats.min(values);
		
		return result;
	}
	
	public double[] getMins(int NbCol) {
		
		double[] result = new double[NbCol];
		
		//get values
		ValuesList values = new ValuesList();
		
		for(int i = 0; i < NbCol; i++) {
			values.clear();
			for(int j = 0; j < io.getValuesList(i).size(); j++) {
				values.add(io.getValuesList(i).get(j));
			}
			result[i] = CalculStats.min(values);
		}
		
		return result;
	}
	
	public String getMax(int col)  {
		//InputObject lt = DoCalculs.getValues();
		String result = "";// = new String();
		
		//get values
		ValuesList values = new ValuesList();
		//BufferedReader ReadIn = getBuffer();
		
		//get all values
		for(int j = 0; j < io.getValuesList(col).size(); j++) {
			values.add(io.getValuesList(col).get(j));
		}
		result = ""+CalculStats.max(values);
		
		return result;
	}
	
	public double[] getMaxs(int NbCol) {
		
		double[] result = new double[NbCol];
		
		//get values
		ValuesList values = new ValuesList();
		
		for(int i = 0; i < NbCol; i++) {
			values.clear();
			for(int j = 0; j < io.getValuesList(i).size(); j++) {
				values.add(io.getValuesList(i).get(j));
			}
			result[i] = CalculStats.max(values);
		}
		
		return result;
	}
//	
//
	public String getMedian(int col) {
//		InputObject lt = DoCalculs.getValues();
		String result = "";// = new String();
		
		//get values
		ValuesList values = new ValuesList();
//		BufferedReader ReadIn = getBuffer();
		
		//get all values
		for(int j = 0; j < io.getValuesList(col).size(); j++) {
			values.add(io.getValuesList(col).get(j));
		}
		result = ""+CalculStats.median(values);
		
		return result;
	}
	
	public double[] getMedians(int NbCol) {
		
		double[] result = new double[NbCol];
		
		//get values
		ValuesList values = new ValuesList();
		
		for(int i = 0; i < NbCol; i++) {
			values.clear();
			for(int j = 0; j < io.getValuesList(i).size(); j++) {
				values.add(io.getValuesList(i).get(j));
			}
			result[i] = CalculStats.median(values);
		}
		
		return result;
	}
//
//	
	public String getVariance(int col) {
//		InputObject lt = DoCalculs.getValues();
		String result = "";// = new String();
		
		//get values
		ValuesList values = new ValuesList();
//		BufferedReader ReadIn = getBuffer();
		
		//get all values
		for(int j = 0; j < io.getValuesList(col).size(); j++) {
			values.add(io.getValuesList(col).get(j));
		}
		result =  ""+CalculStats.variance(values);

		return result;
	}
	
	public double[] getVariances(int NbCol) {
		
		double[] result = new double[NbCol];
		
		//get values
		ValuesList values = new ValuesList();
		
		for(int i = 0; i < NbCol; i++) {
			values.clear();
			for(int j = 0; j < io.getValuesList(i).size(); j++) {
				values.add(io.getValuesList(i).get(j));
			}
			result[i] = CalculStats.variance(values);
		}
		
		return result;
	}
	
//
//	
	public String getStandDeviation(int col) {
//		InputObject lt = DoCalculs.getValues();
		String result = "";// = new String();
		
		//get values
		ValuesList values = new ValuesList();
//		BufferedReader ReadIn = getBuffer();
		
		//get all values
		for(int j = 0; j < io.getValuesList(col).size(); j++) {
			values.add(io.getValuesList(col).get(j));
		}
		result = ""+CalculStats.standDeviation(values);

		return result;
	}
	
	public double[] getSDs(int NbCol) {
		
		double[] result = new double[NbCol];
		
		//get values
		ValuesList values = new ValuesList();
		
		for(int i = 0; i < NbCol; i++) {
			values.clear();
			for(int j = 0; j < io.getValuesList(i).size(); j++) {
				values.add(io.getValuesList(i).get(j));
			}
			result[i] = CalculStats.standDeviation(values);
		}
		
		return result;
	}
	
	public String getMode(int Col) {
		
		String result = "";
		//get values
		ValuesList values = new ValuesList();
		
		for(int j = 0; j < io.getValuesList(Col).size(); j++) {
			values.add(io.getValuesList(Col).get(j));
		}
		result = CalculStats.mode(values);
		
		return result; 
	}
	
	public static double getMinValueFromList(ArrayList<Double> liste) {
		Double min = null;
		for (Double val : liste) {
			if(min == null || val < min)
				min = val;
		}
		return min;
	}
	
	public static double getMaxValueFromList(ArrayList<Double> liste) {
		Double max = null;
		for (Double val : liste) {
			if(max == null || val > max)
				max = val;
		}
		return max;
	}
	
	public static ArrayList<ArrayList<Double>> K_Moyenne(ArrayList<Double> allElements, int k) {
		
		
		
		
		ArrayList<ArrayList<Double>> clusters = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> oldClusters = new ArrayList<ArrayList<Double>>();
		
		ArrayList<Double> centers = new ArrayList<Double>();
		
		double minValue = getMinValueFromList(allElements);
		double maxValue = getMaxValueFromList(allElements);
		
		
		for(int i = 0; i< k ; i++) {
			double randomValue = minValue + Math.random()*maxValue;
			centers.add(randomValue);
			clusters.add(new ArrayList<Double>());
		}
		
		
		
		//First affectation
		for (Double element : allElements) {
			
			
			Double minDistance = null;
			int indiceCenterWithSmallestDistance = -1;
			
			for (int c = centers.size()-1; c >= 0 ; c--) {
				
				double center = centers.get(c);
								
				double distanceFromCenter = Math.abs(center - element);
				
				if(minDistance == null || distanceFromCenter < minDistance){
					minDistance = distanceFromCenter;
					indiceCenterWithSmallestDistance = c;
				}
				
			}
			
			clusters.get(indiceCenterWithSmallestDistance).add(element);
			
		}
		
		//Test if a cluster is empty and remove it
		for (ArrayList<Double> cluster : clusters) {
			if (cluster.isEmpty())
				clusters.remove(cluster);
		}
		
				
		
		
		while(compareLists(clusters, oldClusters) == false){
			
			
			/*for (ArrayList<Double> cluster : oldClusters) {
				DoCalculs.removed(cluster);
			}*/
			
			oldClusters = new ArrayList<ArrayList<Double>>();
			
			for (int i=0 ; i< clusters.size(); i++) {
				oldClusters.add(clusters.get(i));
			}
			
			for (int i = 0 ; i < clusters.size(); i++) {
				centers.set(i, DoCalculs.getBarycentre(clusters.get(i)));
			}
			
			
			for(int i = clusters.size()-1; i >= 0; i--) {
				
				ArrayList<Double> cluster = clusters.get(i);
				
				for (int j= cluster.size() -1; j >=0; j--) {
				
					Double elementCluster = cluster.get(j);
					
					Double minDistance = null;
					int indiceCenterWithSmallestDistance = -1;
					
					for (int c = clusters.size()-1; c >= 0 ; c--) {
												
						double center = centers.get(c);
						
						
						double distanceFromCenter = Math.abs(center - elementCluster);
						
						if(minDistance == null || distanceFromCenter < minDistance){
							minDistance = distanceFromCenter;
							indiceCenterWithSmallestDistance = c;
						}
						
					}
					
					if(indiceCenterWithSmallestDistance != i) {
						clusters.get(indiceCenterWithSmallestDistance).add(cluster.get(j));
						cluster.remove(j);
												
					}
				}
				
			}
			
			
				
			
		}
		
		
		
		
		
		
		/*
		
		
		ArrayList<Double> C1 =new ArrayList<Double>();
		ArrayList<Double> Cl1 =new ArrayList<Double>();
		ArrayList<Double> C2 =new ArrayList<Double>();
		ArrayList<Double> Cl2 =new ArrayList<Double>();
		ArrayList<Double> C3 =new ArrayList<Double>();
		ArrayList<Double> Cl3 =new ArrayList<Double>();
		double M1=1,M2=2,M3=3;
		
		for (int i =0 ; i< valeurs.size(); i++){
			if(Math.abs(valeurs.get(i)-M1)<Math.abs(valeurs.get(i)-M2) && Math.abs(valeurs.get(i)-M1)<Math.abs(valeurs.get(i)-M3))
				C1.add(valeurs.get(i));
			
			else if(Math.abs(valeurs.get(i)-M2)<Math.abs(valeurs.get(i)-M1) && Math.abs(valeurs.get(i)-M2)<Math.abs(valeurs.get(i)-M3))
				C2.add(valeurs.get(i));
			
			else if(Math.abs(valeurs.get(i)-M3)<Math.abs(valeurs.get(i)-M1) && Math.abs(valeurs.get(i)-M3)<Math.abs(valeurs.get(i)-M2))
				
			C3.add(valeurs.get(i));
		}
		
		
		while(DoCalculs.compare(C1, Cl1) ==false || DoCalculs.compare(C2, Cl2) ==false || DoCalculs.compare(C3, Cl3)==false){
			DoCalculs.removed(Cl1);
			DoCalculs.removed(Cl2);
			DoCalculs.removed(Cl3);
			
			Cl1.addAll(C1);
			Cl2.addAll(C2);
			Cl3.addAll(C3);
			
			M1=DoCalculs.getBarycentre(C1);
			M2=DoCalculs.getBarycentre(C2);
			M3=DoCalculs.getBarycentre(C3);
			
			for (int i =0 ; i< C1.size(); i++){
				if(Math.abs(C1.get(i)-M1)<Math.abs(C1.get(i)-M2) && Math.abs(C1.get(i)-M1)<Math.abs(C1.get(i)-M3));
				
				else if(Math.abs(C1.get(i)-M2)<Math.abs(C1.get(i)-M1) && Math.abs(C1.get(i)-M2)<Math.abs(valeurs.get(i)-M3)){
					C2.add(C1.get(i));
					C1.remove(i);
				}
				
				else if(Math.abs(C1.get(i)-M3)<Math.abs(C1.get(i)-M1) && Math.abs(C1.get(i)-M3)<Math.abs(C1.get(i)-M2)){
					
				C3.add(C1.get(i));
				C1.remove(i);
				}
			}
			
			for (int i =0 ; i< C2.size(); i++){
				if(Math.abs(C2.get(i)-M1)<Math.abs(C2.get(i)-M2) && Math.abs(C2.get(i)-M1)<Math.abs(C2.get(i)-M3)){
				C1.add(C2.get(i));
				C2.remove(i);
				}
			
				else if(Math.abs(C2.get(i)-M2)<Math.abs(C2.get(i)-M1) && Math.abs(C2.get(i)-M2)<Math.abs(C2.get(i)-M3)){
					;
				}
				
				else if(Math.abs(C2.get(i)-M3)<Math.abs(C2.get(i)-M1) && Math.abs(C2.get(i)-M3)<Math.abs(C2.get(i)-M2)){
					
				C3.add(C2.get(i));
				C2.remove(i);
				}
			}
			
			for (int i =0 ; i< C3.size(); i++){
				if(Math.abs(C3.get(i)-M1)<Math.abs(C3.get(i)-M2) && Math.abs(C3.get(i)-M1)<Math.abs(C3.get(i)-M3)){
					C1.add(C3.get(i));
					C3.remove(i);
				}
				
				else if(Math.abs(C3.get(i)-M2)<Math.abs(C3.get(i)-M1) && Math.abs(C3.get(i)-M2)<Math.abs(C3.get(i)-M3)){
					C2.add(C3.get(i));
					C3.remove(i);
				}
				
				else if(Math.abs(C3.get(i)-M3)<Math.abs(C3.get(i)-M1) && Math.abs(C3.get(i)-M3)<Math.abs(C3.get(i)-M2))
					
				;
				
			}
			
			
			
		}*/
		
		
		return clusters;
	}
	
	private static ArrayList<ArrayList<Double>> Clone(ArrayList<ArrayList<Double>> clusters) {

		ArrayList<ArrayList<Double>> list= new ArrayList<ArrayList<Double>>();
		for (ArrayList<Double> cluster : clusters) {
			ArrayList<Double> dList = new ArrayList<Double>();
			for (Double val : cluster) {
				dList.add(val);
			}
			list.add(dList);
		}
		return list;
	}

	public String[] getModes(int NbCol) {
		String[] result = new String[NbCol];
		
		//get values
		ValuesList values = new ValuesList();
		
		for(int i = 0; i < NbCol; i++) {
			values.clear();
			for(int j = 0; j < io.getValuesList(i).size(); j++) {
				values.add(io.getValuesList(i).get(j));
			}
			result[i] = CalculStats.mode(values);
		}
		
		return result; 
		
	}
	
	public static double getDaviesBouldinIndice(ArrayList<ArrayList<Double>> repartition) {
		double DB = 0;
		
		int k = repartition.size(); //ici, k vaut le nombre de groupes (pas sur, k doit peut être être le nombre de centres définis aléatoirement tout au début dde l'algo).

		for(int i=0; i< k; i++) {
			ArrayList<Double> group = repartition.get(i);
			
			
			
			double meanDistanceElementToBarycentreOfGroup = getMeanDistanceElementToBarycentreOfGroup(group);
			
			ArrayList<Double> calculMax = new ArrayList<Double>();
			for (int j=0; j<k; j++) {				
				if(i != j){
					ArrayList<Double> groupJ = repartition.get(j);
					double meanDistanceInGroupJ = getMeanDistanceElementToBarycentreOfGroup(groupJ);
					double distanceBetweenBarycentres = Math.abs(getBarycentre(group) - getBarycentre(groupJ));
					calculMax.add((meanDistanceElementToBarycentreOfGroup + meanDistanceInGroupJ) / distanceBetweenBarycentres);
				}					
			}
			
			double max = getMaxValue(calculMax);
			DB += max;
		}
		
		DB = DB / k;
		//0.23
		return DB;
	}
	
		
	
	
	
	
	
	
	
	//------------------- Private Tools ----------------
	
	
	
	

	private static double getMaxValue(ArrayList<Double> calculMax) {
		double max = 0;
		for (Double value : calculMax) {
			if(value> max)
				max = value;
		}
		return max;
	}

	private static double getMeanDistanceElementToBarycentreOfGroup(ArrayList<Double> group) {
		
		double barycentreGroup = getBarycentre(group);
		
		double mean = 0;
		for (Double element : group) {
			double distanceElementToBarycentre = Math.abs(barycentreGroup - element);
			mean += distanceElementToBarycentre;
		}
		
		mean /= group.size();
		
		return mean;
	}
	

	public static double getBarycentre(ArrayList<Double> valuesList) {
		double sum = 0, barycentre;
		for (Double element : valuesList) {
			sum += element;
		}		
		
		barycentre = sum/valuesList.size();
		return barycentre;
	}


	public static boolean rchr(double var, ArrayList<Double> list) {
		
		boolean trouve=false;
		
			for (int j=0 ; j < list.size(); j++) {
				
						if (var == list.get(j)){
						
							trouve = true;	
						}			
			}						
		
		return trouve;
	}
	
	public static boolean compare(ArrayList<Double> cmp1, ArrayList<Double> cmp2) {
		ArrayList<Double> cmp11= new ArrayList<Double>();
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
	
	public static boolean compareLists(ArrayList<ArrayList<Double>> cmp1, ArrayList<ArrayList<Double>> cmp2) {
		boolean cmp=true;	
		
		if(cmp1.size() == cmp2.size()) {
			for (int i = 0; i< cmp1.size(); i++) {
				if(!compare(cmp1.get(i), cmp2.get(i))) {
					cmp = false;
					break;
				}
			}
		}else {
			cmp = false;
		}
		
		return cmp;
	}
	
	public static void removed(ArrayList<Double> liste) {
		
		if (liste.size() >0){	
			
			for (int i=liste.size()-1 ; i >=0 ; i--) {
				liste.remove(i);		
			}
			
		}
		else;
	}
	

}
