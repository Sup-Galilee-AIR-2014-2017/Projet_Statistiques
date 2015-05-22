/**
 * 
 */
package DATA_And_Files_Propreties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ModStats.CalculStats;
import ModStats.InputObject;
import ModStats.ValuesList;

/**
 * @author admaay
 *
 */
public class DataAndFilesProprieties {
	
	private int nbCol;	
	private List<String> Lines; 
	private List<String> classesNames;
//	private List<Integer> classesNamesIndex;
	private List<ArrayList<Integer>> classesNamesIndexes;
	private int[] nbMissingDataPerCol;
	private BufferedReader ReadIn = null;
	private String delims=",";
	private List<ValuesList> tempDataList;
	
	
	public DataAndFilesProprieties() {
		classesNames = new ArrayList<String>();
//		classesNamesIndex = new ArrayList<Integer>();
		
		classesNamesIndexes = new ArrayList<ArrayList<Integer>>(); 
		
		Lines = new ArrayList<String>();
		tempDataList = new ArrayList<ValuesList>();
	}
	
	
	public void setFilePropreties(String file) throws IOException {
		String aLine,str = "";
		String[] tokens = null;
		
		//
		ReadIn = new BufferedReader(new FileReader(file));
		
		//nb of col
		ReadIn.mark(10000);
		if((aLine = ReadIn.readLine() )!= null) {			
			tokens = aLine.split(delims);
			nbCol = tokens.length;
			str = tokens[nbCol-1];
		}
		
		ReadIn.reset();
		
		//vide...
		if(!classesNames.isEmpty())
			classesNames.clear();
		
//		if(!classesNamesIndex.isEmpty())
//			classesNamesIndex.clear();
		if(!classesNamesIndexes.isEmpty())
			classesNamesIndexes.clear();
		
		if(!Lines.isEmpty())
			Lines.clear();
		
		// add
		int pos = 0;
		//nb of lines
		//ReadIn.mark(30000);
		boolean exist = false;
		while((aLine = ReadIn.readLine() )!= null) {
			//verifier si ce n'est pas une ligne vide
			if(aLine.length() > 0) {
				str = aLine.split(delims)[nbCol-1];
				for(int i = 0; i < classesNames.size(); i++) {
					if(str.equals(classesNames.get(i))) {
						classesNamesIndexes.get(i).add(pos);
						exist = true;
					}
				}
				if(!exist) {
					classesNames.add(str);
					ArrayList<Integer> l = new ArrayList<Integer>();
					l.add(pos);
					classesNamesIndexes.add(l);
				}exist = false;
				Lines.add(aLine);
				pos++;
			}
		}
	}
	
	public void setDataFile() {
		String[] tokens = null;
		String[][] temp = new String[Lines.size()][nbCol-1];
		nbMissingDataPerCol = new int[nbCol-1];
		
		int localMissingDataVar = 0;
		double[] ColMean = new double[nbCol-1];
		//FilesPropreties filesPropreties = new FilesPropreties();
		//templist pour un InputObject
		
		//avant toute chose verifie que temp list est bien vide
		if(!tempDataList.isEmpty())
			tempDataList.clear();
		 
		int nbOfDataCol = nbCol - 1;
		
		ValuesList[] q = new ValuesList[nbOfDataCol];
		
		for(int i = 0; i < nbOfDataCol; i++) {
			q[i] = new ValuesList();
		}
		
		//création 
		for(int i = 0 ; i < nbOfDataCol; i++) {
			tempDataList.add(q[i]);
		}
		
		//

		
		int line = 0;
		for(int y = 0; y < Lines.size(); y++) {
			tokens = Lines.get(y).split(delims);
			//

			//System.out.print(tokens[nbOfDataCol]);
			Double value;
			for(int i = 0 ; i < nbOfDataCol; i++) {
				try{
					value = Double.parseDouble(tokens[i]);
					temp[line][i] = "OK";
				}catch(NumberFormatException e) {
					value = 0.0;
					temp[line][i] = "NULL";
					nbMissingDataPerCol[i]++;
					localMissingDataVar++;
				}
				q[i].add(value);
				tempDataList.set(i, q[i]);
				
				//at the end we determine also the mean of the column
				if(line == Lines.size()-1) {
					ColMean[i] = CalculStats.mean(q[i]);
//				System.out.println("mean = "+ColMean[i]);
				}
			}
			
			line++;
		}
		
		
		//set missing data
		if(localMissingDataVar > 0) {
		int k,l = 0;
			for(k = 0; k < Lines.size(); k++) {
				for(l= 0; l< nbCol-1; l++) {
					if(temp[k][l].equals("NULL") && nbMissingDataPerCol[l] > 0.02*Lines.size()) {
						//TODO
						//on remplace le vide par la moyenne
						ValuesList vltemp = tempDataList.get(l);
						vltemp.set(k, ColMean[l]);
						tempDataList.set(l, vltemp);
						
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public InputObject getData() {
		InputObject io = new InputObject();
		io.setListData(tempDataList);
		return io;
	}
	
	//TODO
	public InputObject[] getDataByObject() {
		InputObject[] io = new InputObject[classesNames.size()];

		ValuesList[][] r = new ValuesList[classesNames.size()][nbCol];
		for(int i = 0; i < classesNames.size(); i++) {
			for(int j = 0; j < nbCol; j++) {
				r[i][j] = new ValuesList();
			}
		}
		
		for(int i = 0; i < classesNames.size(); i++) {
			for(int a : classesNamesIndexes.get(i) ) {//a < (Lines.size() - classesNamesIndex.get(classesNames.size()-(i+1))); a++) {
				for(int b = 0; b < nbCol-1; b++) {
					r[i][b].add(tempDataList.get(b).get(a));
				}
			}
		}
		
		for(int c = 0; c < classesNames.size(); c++) {
			List<ValuesList> tempList = new ArrayList<ValuesList>();
			for(int d = 0; d < nbCol; d++)
				tempList.add(r[c][d]);
			io[c] = new InputObject(classesNames.get(c));
			io[c].setListData(tempList);
		}
		
		return io;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNbLines() {
		return Lines.size();
	}
	
	
	public int getNbDataColumns() {
		return nbCol-1;
	}
	
	public int getNbMissingData() {
		int md = 0;
		for(int i = 0; i < nbCol-1; i++)
			md+=nbMissingDataPerCol[i];
		return md;
	}
	
	public int getNbObjectFound() {
		return classesNames.size();
	}
	
	public String[] getObjectNames() {
		String[] str = new String[classesNames.size()];
		int i = 0;
		for(String s : classesNames) {
			str[i] = s;
			i++;
		}
		return str;
	}
	
	
//	public static void main(String[] argc) throws IOException {
////		
//		DataAndFilesProprieties df = new DataAndFilesProprieties();
//		
//		df.setFilePropreties("resources/bezdekIris.data");
//		df.setDataFile();
//		int md = 0;
//		
//		for(int i = 0; i < df.nbCol-1; i++)
//			md+=df.nbMissingDataPerCol[i];
////		
//		System.out.println(df.nbCol+" "+df.Lines.size()+ " -miss "+md+" -nbClass "+df.classesNames.size()+" @"+df.getData().getValuesList(0).size());
//		
//		System.out.println("classes = " +df.classesNames.size()+" classes Index "+df.classesNamesIndexes.size());
//		for(int i = 0; i < df.classesNamesIndexes.size(); i++) {
//			System.out.println(" "+i+" -> "+df.classesNamesIndexes.get(i).size());
//			for(int j = 0; j < df.classesNamesIndexes.get(i).size(); j++)
//				System.out.print("-"+df.classesNamesIndexes.get(i).get(j));
//		}
//		
//		for(int i = 0; i < df.getDataByObject().length; i++) {
//			System.out.println(df.getDataByObject()[i].getName()+" "+df.getDataByObject()[i].getValuesList(3).get(0));
//			
//		}
		
//		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
//		//File file = new File("resources/bezdekIris.data");
//		//System.out.println(mimeTypesMap.getContentTypeFor(file));
//		FileNameMap fileNameMap = URLConnection.getFileNameMap();
//		
//		InputStream is = new BufferedInputStream(new FileInputStream("resources/bezdekIris.data"));
//		String mimeType = URLConnection.guessContentTypeFromStream(is);
//		is.close();
//		System.out.println(mimeType);
//	}
	

}
