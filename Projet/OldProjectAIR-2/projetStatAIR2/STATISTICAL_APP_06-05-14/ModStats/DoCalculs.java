package ModStats;


public class DoCalculs {
	
	private InputObject io;

	
	public void setInputData(InputObject in) {
		io = in;
	}

	public boolean fileImported() {
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
	

}
