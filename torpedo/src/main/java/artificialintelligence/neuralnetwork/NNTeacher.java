package artificialintelligence.neuralnetwork;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A neurális hálózat tanításáért felelős
 * @author zsigatibor
 *
 */
public class NNTeacher {
	
	NeuralNetwork nn;
	TeachingHelper helper;
	
	/**
	 * A felhasználandó tanítási paramétereket tárolom itt.
	 */
	private List<String[]> usedTeachingParams = new LinkedList<String[]>();
	/**
	 * A validációs paraméterek lesznek ebben a listában.
	 */
	private List<String[]> validationParams = new LinkedList<String[]>();
	
	/**
	 * Ebben a listában tárolom az epochok végén
	 * kiírandó négyzetes hibaátlagok értékét
	 */
	private LinkedList<Double> avgErr2List = new LinkedList<Double>();

	public NNTeacher(NeuralNetwork network, TeachingHelper th) {
		nn = network;
		helper = th;
	}
	
	/**
	 * Tanítja és validálja a neurális hálózatot
	 * @param inputParams beolvasott bemeneti paraméterek
	 */
	public void teach(LinkedList<String[]>inputParams){
		
		usedTeachingParams = helper.getUsedTeachingParams(inputParams);
		validationParams = helper.getValidationParams(inputParams);
		
		int index = 0;
		
		while(index < helper.getNumberOfEpochs()){			
			
			teachWithTheseInputParams(usedTeachingParams);
			
			validateWithTheseInputParams(validationParams);
			
			index++;
		}
	}

	/**
	 * A validációs mintákkal validálja a betanított neurális hálózatot.
	 * Az avgErr2List listát feltölti az aktuális epoch-ban képződött
	 * átlagos hibanégyzetekkel 
	 */
	private void validateWithTheseInputParams(List<String[]> validationParams) {
		
		LinkedList<Double> tempAvgList = new LinkedList<Double>();
		
		LinkedList<String[]>actualInputParams;
		String[] expectedOutput;
		
		Iterator<String[]> iterator = validationParams.iterator();
		
		while(iterator.hasNext()){
			//Kettészedjük a bemenetet inputok halmazára és outputra
			String[] params = iterator.next();
			actualInputParams = getActualInputs(params);
			expectedOutput = getExpectedOutput(params);
			
			//A neurális hálózat kimenetének kiszámítása az aktuális súlyaival
			nn.evaulateReLUWithTheseParams(actualInputParams);
			
			nn.setExpectedResults(expectedOutput);
			
			nn.calculateError();
			tempAvgList.add(calculateAvgErr2ByNumberOfOutputs());
		}
		double avgerr2 = calculateAvgErr2ByNumberOfValidationParams(tempAvgList);
		avgErr2List.add(avgerr2);
//		System.out.println(avgerr2);
	}

	/**
	 * Kiszámítja a négyzetes hibák átlagát a validációs paraméterek száma alapján
	 */
	private Double calculateAvgErr2ByNumberOfValidationParams(LinkedList<Double> tempAvgList) {
		double avgsum = 0.0;
		for (Double double1 : tempAvgList) {
			avgsum += double1;
		}
//		return avgsum / helper.getNumberOfValidationSamples();
		return avgsum / tempAvgList.size();
	}

	/**
	 * Kiszámítja a kimenetek száma szerinti négyzetes hiba átlagát
	 */
	private double calculateAvgErr2ByNumberOfOutputs() {
		List<Neuron> outputLayer = nn.getOutputs();
		
		double err2Sum = 0;
		
		for (int i = 0; i < outputLayer.size(); i++) {
			Neuron neuron = outputLayer.get(i);
			
			double tempErr = nn.getErrorByThisNeuron(neuron);
			
			err2Sum += tempErr * tempErr;
		}
		double avgErr2 = err2Sum / outputLayer.size();
		
		return avgErr2;
	}

	/**
	 * A kapott listában lévő paraméterekre lefuttatja a tanítást
	 */
	private void teachWithTheseInputParams(List<String[]> usedTeachingParams2) {
		LinkedList<String[]>actualInputParams;
		String[] expectedOutput;
		
		Iterator<String[]> iterator = usedTeachingParams2.iterator();
		
		while(iterator.hasNext()){
			String[] params = iterator.next();
			
			//Kettészedjük a bemenetet inputok halmazára és outputra
			actualInputParams = getActualInputs(params);
			expectedOutput = getExpectedOutput(params);
			
			//A neurális hálózat kimenetének kiszámítása az aktuális súlyaival
			nn.evaulateReLUWithTheseParams(actualInputParams);
			
			
			//hiba kiszámítása
			nn.setExpectedResults(expectedOutput);
			nn.calculateError();
			
			
			
			//új delta értékek
			nn.calculateDeltas();
			
			//deriváltak kiszámítása
			nn.calculateDerivatesByWeight();
			nn.calculateDerivatesByBias();
			
			//súlyok módosítása
			nn.modifyWeights();			
			nn.modifyBiasVals();
		}
	}
	
	

	/**
	 * @return elvárt kimenet adott bemenetre
	 */
	private String[] getExpectedOutput(String[] input) {
		int numberOfInputs = nn.getInputs().size();
		int numberofOutputs = input.length - numberOfInputs;
		
		String[] strArray = new String[numberofOutputs];
		int j = 0;
		for(int i = numberOfInputs; i < input.length; i++){
			strArray[j++] = (input[i]);
		}
		return strArray;
	}

	/**
	 * @return bemeneti paraméterek
	 */
	private LinkedList<String[]> getActualInputs(String[] str) {
		LinkedList<String[]> list = new LinkedList<String[]>();
		
		String[] inputs = new String[nn.getInputs().size()];
		for(int i = 0; i < nn.getInputs().size(); i++){
			inputs[i] =str[i];
		}
		
		list.add(inputs);
		return list;
	}
	
	public LinkedList<Double> getAvgErr2List() {
		return avgErr2List;
	}
}