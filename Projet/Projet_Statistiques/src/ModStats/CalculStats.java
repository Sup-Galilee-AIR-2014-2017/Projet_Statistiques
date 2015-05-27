package ModStats;

import java.util.List;

public class CalculStats {
	
	/*Methode de calcul de la mediane
	 * si n est impaire median=f ((n+1)/2
	 * si n est paire median = 1/2 [ f(n/2)+ f((n+1)/2) ]
	 */
	public static double median(ValuesList listData){
		double result = 0;
		double half1, half2;
		List<Double> listDt;
		listDt=listData.sortValues();
		if(listDt.size()%2!=0){
			result=listDt.get(((listDt.size()+1)/2)-1);	
		}
		else{
			half1=listDt.get((listDt.size()/2)-1);
			half2=listDt.get(((listDt.size()/2)+1)-1);
			result=(half1+half2)/2;
		}
		return roundDouble(result);
	}
	
	/*Methode de calcul de la moyenne
	 *somme des valeurs / nombre de valeurs 
	 */
	public static double mean(ValuesList listData){
		double sum=0;
		double iValue;
		int i;
		for(i=0;i<listData.size();i++){
			iValue=listData.get(i);
			sum=sum+iValue;
		}
		return roundDouble(sum/listData.size());
	}
	
	/*Methode de calcul de l'ecart-type
	 */
	public static double standDeviation(ValuesList listData){
		double sum1;
		double sum2=0;
		double iValue;
		int i;
		for(i=0;i<listData.size();i++){
			iValue=listData.get(i);
			sum1=Math.pow(iValue-mean(listData),2);
			sum2=sum2+sum1;
		}
		return roundDouble(Math.sqrt(sum2/listData.size()));
	}
	
	/**
	 * 
	 */
	public static double variance(ValuesList listData){
		double sum1;
		double sum2=0;
		double iValue;
		int i;
		for(i=0;i<listData.size();i++){
			iValue=listData.get(i);
			sum1=Math.pow(iValue-mean(listData),2);
			sum2=sum2+sum1;
		}
		return roundDouble(sum2/listData.size());
	}
	
	
	public static double coefCorel (ValuesList listData1, ValuesList listData2){
		double coefCorel=0;
		int i;
		double moy1=0;
		double moy2=0;
		double num=0;
		double denum1=0;
		double denum2=0;
		double denum;
		moy1=mean(listData1);
		moy2=mean(listData2);
		for (i=0;i<listData1.size();i++){
			num = num + (listData1.get(i)-moy1)*(listData2.get(i)-moy2);
			denum1 = denum1 + (Math.pow(listData1.get(i)-moy1,2));
			denum2 = denum2 + (Math.pow(listData2.get(i)-moy2,2));
		}
		denum = Math.sqrt(denum1)*Math.sqrt(denum2);
		coefCorel=num/denum;
		return roundDouble(coefCorel);
	}
	
	public static double coefAlphaRegression (ValuesList listData1, ValuesList listData2){
		double alpha=0;
		double moy1;
		double moy2;
		double num=0; 
		double denum=0;
		moy1=mean(listData1);
		moy2=mean(listData2);
		for (int i=0;i<listData1.size();i++){
			num = num + (listData1.get(i)-moy1)*(listData2.get(i)-moy2);
			denum = denum + (Math.pow(listData1.get(i)-moy1,2));
		}
		
		alpha=num/denum;
		return alpha;
	}
	
	public static double coefBetaRegression (ValuesList listData1, ValuesList listData2){
		double beta=0;
		double moy1=0;
		double moy2=0;
		double alpha=0;
		moy1=mean(listData1);
		moy2=mean(listData2);
		
		alpha = coefAlphaRegression (listData1, listData2);
		beta = moy2 - alpha*moy1;
		
		return beta;
	}
	
	/*methode de calcul de la valeur minimum*/
	public static double min(ValuesList listData) {
		return listData.minValue();	
	}
	
	/*methode de calcul de la valeur maximale*/
	public static double max(ValuesList listData) {
		return listData.maxValue();	
	}
	
	
	
	
	
	public static double roundDouble(Double result){
		return (double) Math.round(result*100)/100;
	}
	
	
	 public static String mode(ValuesList listData){
		  ValuesList freq = new ValuesList();
		  ValuesList res = new ValuesList();
		  String result = "";
		  int size = listData.size();
		  int nbMax=0;
		  int tabMax[] = new int[size];

		  for(int i = 0;i<size;i++){
		   freq.add((double)listData.frequency(listData.get(i)));
		  }

		  for (int i = 0;i<size;i++){
		   if(freq.get(i)==freq.maxValue()){
			   tabMax[i] = i;
			   nbMax++;
		   }
		   else {
			   tabMax[i]=-1;
			   nbMax++;
		   }
		  }
		  
		  for (int i = 0; i<nbMax;i++){
		   if(tabMax[i]!=-1)
		    if(!res.contains(listData.get(tabMax[i]))){
		     res.add(listData.get(tabMax[i]));
		    }
		  }

		  for(int i = 0;i<res.size();i++){
		   result = result + res.get(i)+" ";
		  }
		  
		  return result;
		 }
	
	 	
	 public static String KMoy(ValuesList listData){
		  ValuesList freq = new ValuesList();
		  ValuesList res = new ValuesList();
		  String result = "";
		  int size = listData.size();
		  int nbMax=0;
		  int tabMax[] = new int[size];

		  for(int i = 0;i<size;i++){
		   freq.add((double)listData.frequency(listData.get(i)));
		  }

		  for (int i = 0;i<size;i++){
		   if(freq.get(i)==freq.maxValue()){
			   tabMax[i] = i;
			   nbMax++;
		   }
		   else {
			   tabMax[i]=-1;
			   nbMax++;
		   }
		  }
		  
		  for (int i = 0; i<nbMax;i++){
		   if(tabMax[i]!=-1)
		    if(!res.contains(listData.get(tabMax[i]))){
		     res.add(listData.get(tabMax[i]));
		    }
		  }

		  for(int i = 0;i<res.size();i++){
		   result = result + res.get(i)+" ";
		  }
		  
		  return result;
		 }
	
}

		
