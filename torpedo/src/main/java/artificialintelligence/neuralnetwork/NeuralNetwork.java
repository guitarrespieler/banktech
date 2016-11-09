package artificialintelligence.neuralnetwork;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import artificialintelligence.NeuralNetworkInitializer.NNSolutionOne;

/**
 * Neurális hálózatot reprezentáló osztály.
 * @author zsigatibor
 *
 */
public class NeuralNetwork {
	
	/**
	 * Bátorsági faktor
	 */
	private double courageFactor = 0.0;
	
	/**
	 * Kulcs: kimeneti neuron
	 * Érték: hozzá tartozó hiba
	 */
	private Map<Neuron,Double> errorByNeuron = new LinkedHashMap<Neuron, Double>();
	
	/**
	 * NNSolutionThree feladatban ez kell
	 */
	private static final double outputsNeuronsDelta = 1.0;
	
	/**
	 * Az eredményeket tárolom ebben a listában
	 */
	private LinkedList<LinkedList<Double>> results = new LinkedList<LinkedList<Double>>(); 
		
	/**
	 * Ebben a listában tárolom a rejtett rétegben
	 * lévő neuronokat. Egy szint egy neuronokból álló lista
	 */
	private LinkedList<LinkedList<Neuron>> hiddenLayers = new LinkedList<LinkedList<Neuron>>();
	
	/**
	 * A bemeneti réteget fogom ebben a listában tárolni.
	 */
	private LinkedList<INode> inputLayer = new LinkedList<INode>();
	
	/**
	 * A kimeneti réteget fogom itt tárolni.
	 */
	private LinkedList<Neuron> outputLayer = new LinkedList<Neuron>();

	/**
	 * Kulcs: kimeneti neuron
	 * Érték: neuron elvárt kimeneti értéke
	 */
	private Map<Neuron,Double> expectedResultsByNeuron = new HashMap<Neuron, Double>();
	
	
	/**
	 * Új rejtett szint hozzáadása a hálózathoz
	 * 
	 */
	public void addNewLevel(LinkedList<Neuron> newLevel){
		hiddenLayers.add(newLevel);
	}
	
	/**
	 * Új bemenet hozzáadása a hálózat bemeneti rétegéhez
	 * 
	 */
	public void addNewInput(INode n){
		inputLayer.add(n);
	}
	
	/**
	 * Új kimenet hozzáadása a hálózat kimeneti rétegéhez
	 * 
	 */
	public void addNewOutput(Neuron n){
		outputLayer.add(n);
	}
	
	/**
	 * Ha létrejöttek a csomópontok, ezzel felépíthetjük
	 * a köztük levő kapcsolatokat.
	 */
	public void generateConnections(){
		if(!hiddenLayers.isEmpty()){		
			//bemenet és első szint közti kapcsolat létrehozása
			for (INode node : inputLayer) {
				for (Neuron neuron : hiddenLayers.get(0)) {
					newConnection(node, neuron);
				}
			}
			//neuronok közötti kapcsolatok létrehozása
			for(int i = 0; i < hiddenLayers.size() - 1; i++){
				LinkedList<Neuron> list_out = hiddenLayers.get(i);
				LinkedList<Neuron> list_in = hiddenLayers.get(i+1);
				
				for(int j = 0; j < list_out.size(); j++){
					for(int k = 0; k < list_in.size(); k++){
						newConnection(list_out.get(j), list_in.get(k));
					}
				}			
			}
			//kimenetek bekötése
			LinkedList<Neuron> list_lastLevel = hiddenLayers.get(hiddenLayers.size() - 1);
			for (Neuron neuron : list_lastLevel) {
				for(int i = 0; i < outputLayer.size(); i++){
					newConnection(neuron, outputLayer.get(i));
				}			
			}		
		}else{
			for (INode inp : inputLayer) {
				for (INode outp : outputLayer) {
					newConnection(inp, outp);
				}
			}			
		}	
	}

	/**
	 * Létrehozza a csomópontok között a kapcsolatokat.
	 * @param out - amelyik csomópont kimenetéről van szó
	 * @param in - amelyik csomópont bemenetéről van szó
	 */
	private void newConnection(INode out, INode in) {
		Connection c = new Connection(NNSolutionOne.generateRandomGaussian(NNSolutionOne.averageVal, NNSolutionOne.deviationVal));
		out.addOutput(c);
		c.setInput(out);
		c.setOutput(in);
		in.addInput(c);
	}
	
	public LinkedList<LinkedList<Neuron>> getHiddenLayers(){
		return hiddenLayers;
	}
	
	public LinkedList<Neuron> getOutputs(){
		return outputLayer;
	}

	public LinkedList<INode> getInputs() {
		return inputLayer;
	}
	
	public void evaulateReLUWithTheseParams(LinkedList<String[]> inputParams){
		for (String[] strings : inputParams) {
			for (int i = 0; i < inputLayer.size(); i++) {
				INode inputNode = inputLayer.get(i);
				inputNode.setValue(Double.valueOf(strings[i]));//beállítjuk a bemenet értékét
			}
			//Beállítottuk a bemenetek értékét, jöhet a számítás
			calculateReLU();
		}
	}

	/**
	 * számítások elvégzése
	 */
	private void calculateReLU() {
		for (LinkedList<Neuron> neuronList : hiddenLayers) {
			for (Neuron neuron : neuronList) {
				neuron.activate();
			}
		}
		for (Neuron outputNeuron : outputLayer) {
			outputNeuron.activate();
		}
		LinkedList<Double> valList = new LinkedList<Double>();
		for (Neuron outputNeuron : outputLayer) {
			valList.add(outputNeuron.getValue());
		}
		results.add(valList);
	}
	
	/**
	 * 
	 * @return kiszámított eredmények listája
	 */
	public LinkedList<LinkedList<Double>> getResults(){
		return results;
	}
	
	
	
	/**
	 * Kiszámítja az egész hálózatban a delta-értékeket
	 * @param numberOfHiddenLayers
	 */
	public void calculateDeltas(){		
		calculateOutputLayersDeltas();
		
		if(hiddenLayers.size() > 0){
			LinkedList<Neuron> lastLayer = hiddenLayers.get(hiddenLayers.size()-1);
			calculateThisLayersDeltas(lastLayer);
		
			if(hiddenLayers.size() > 1){//Kiszámítja a többi layer neuronjainak delta értékeit.
				for(int i = hiddenLayers.size() - 2; i <= 0; --i){
					if(i<0) //valamiért nem jó így a ciklus feje, ez az apró javítás megóvja az alulindexeléstől
						break;
					LinkedList<Neuron> actualLayer = hiddenLayers.get(i);
					calculateThisLayersDeltas(actualLayer);
				}
			}
		}
	}

	/**
	 * @param actualLayer ennek a rétegnek kiszámítja a delta értékeit
	 * a syllabusban 
	 */
	private void calculateThisLayersDeltas(LinkedList<Neuron> actualLayer) {
		for (Neuron neuron : actualLayer) {
			if(neuron.derivateOfValue() == 0){//ekkor nem kell tovább számolni ezt az ágat
				neuron.setDelta(0);
				continue;				
			}
			double tempDelta = 0.0;
			for (Connection conn : neuron.getAllOutputs()) {
				tempDelta += ((Neuron)conn.getOutput()).getDelta()*conn.getWeight();
			}
			neuron.setDelta(tempDelta);
		}
	}
	
	/**
	 * Kiszámítja a kimeneti neuronok delta-értékét.
	 */
	private void calculateOutputLayersDeltas() {
		for (Neuron outputNeuron : outputLayer) {
//			if(NNSolutionThree.isActive){
//				outputNeuron.setDelta(outputsNeuronsDelta);
//			}else{
//				calculateError();
				outputNeuron.setDelta(errorByNeuron.get(outputNeuron));
//			}
		}	
	}

	/**
	 * Kiszámítja a hibát
	 */
	public void calculateError() {
		for (Neuron outputNeuron : outputLayer) {
			double outputVal = outputNeuron.getValue();
			double error = expectedResultsByNeuron.get(outputNeuron) - outputVal;			
			errorByNeuron.put(outputNeuron, error) ;
		}		
	}

	/**
	 * Kiszámítja a súly szerinti deriváltakat
	 */
	public void calculateDerivatesByWeight() {
		for (LinkedList<Neuron> layers : hiddenLayers) {
			calculateThisLayersDerivatesByWeight(layers);
		}
		//ugyanezt a kimeneti rétegre is:
		calculateThisLayersDerivatesByWeight(outputLayer);		
	}

	/**
	 * @param layers erre a rétegre kiszámítja a súly szerinti deriváltakat
	 * és el is menti az értékeket.
	 */
	private void calculateThisLayersDerivatesByWeight(LinkedList<Neuron> layers) {
		for (Neuron neuron : layers) {
			double derivateByWeight = 0.0;
			for (Connection conn : neuron.inputs) {
				double value = conn.getInput().getValue();
				double delta = neuron.getDelta();
				
				derivateByWeight = -2*delta*value; //Syllabusz alapján
//				if(NNSolutionThree.isActive)
//					derivateByWeight = derivateByWeight/ -2; //valamiért ez a feladatrész így fogadja el...
				
				neuron.addDerivateByWeightValue(conn, derivateByWeight);//elmentjük a kiszámított értéket
			}						
		}
	}

	/**
	 * Kiszámítja a bias érték szerinti deriváltakat
	 */
	public void calculateDerivatesByBias() {
		for (LinkedList<Neuron> layers : hiddenLayers) {
			calculateThisLayersDerivatesByBias(layers);
		}
		//Ugyanezt a kimeneti rétegre:
		calculateThisLayersDerivatesByBias(outputLayer);		
	}

	/**
	 * @param layers erre a rétegre kiszámítja a bias szerinti
	 * deriváltakat és el is menti azt
	 */
	private void calculateThisLayersDerivatesByBias(LinkedList<Neuron> layers) {
		for (Neuron neuron : layers) {
			double delta = neuron.getDelta();
			double derivateByBias = delta;
//			double derivateByBias = -2*delta;
//			if(NNSolutionThree.isActive)
//				derivateByBias = derivateByBias / -2; //A harmadik feladat valamiért így jó
			neuron.setDeltaB(derivateByBias);
		}
	}
	
	/**
	 * Súlyok módosítása a delta-szabállyal
	 */
	public void modifyWeights(){
		//Rejtett rétegekre
		for (LinkedList<Neuron> hiddenLayers : hiddenLayers) {
			modifyThisLayersWeights(hiddenLayers);
		}
		//kimeneti rétegekre
			modifyThisLayersWeights(outputLayer);
	}
	
	

	/**
	 * @param layer ennek a rétegnek módosítja a súlyait
	 */
	private void modifyThisLayersWeights(LinkedList<Neuron> layer) {
		for (Neuron neuron : layer) {
			for (Connection conn : neuron.getAllInputs()) {
				double oldWeight = conn.getWeight();
				double delta = neuron.getDelta();
				double prevValue = conn.getInput().getValue();
				
				double newWeight = oldWeight + 2*courageFactor*delta*prevValue;
				conn.setWeight(newWeight);
			}
		}
	}
	
	/**
	 * Módosítjuk a bias értékeket
	 */
	public void modifyBiasVals(){
		for (LinkedList<Neuron> layer : hiddenLayers) {
			modifyThisLayersBiases(layer);
		}
		//ugyanezt a kimeneti rétegre is:
		modifyThisLayersBiases(outputLayer);
	}
	
	/**
	 * A paraméterül kapott réteg bias értékeit módosítja
	 */
	private void modifyThisLayersBiases(LinkedList<Neuron> layer) {
		for ( Neuron neuron : layer) {
			double oldBias = neuron.getBias();
			
			double newBias = oldBias + 2*courageFactor*neuron.getDelta();
			neuron.setBias(newBias);
		}
		
	}
	
	/**
	 * Elvárt kimeneti értékek megadása
	 */
	public void setExpectedResults(String[] values){
		for(int i = 0; i < outputLayer.size(); i++){
			Neuron neuron = outputLayer.get(i);
			Double expectedValue = Double.parseDouble(values[i]);
			expectedResultsByNeuron.put(neuron, expectedValue);
		}
	}
	
	public double getThisNeuronsExpectedResult(Neuron neuron){
		return expectedResultsByNeuron.get(neuron);
	}

	public double getCourageFactor() {
		return courageFactor;
	}

	public void setCourageFactor(double courageFactor) {
		this.courageFactor = courageFactor;
	}
	
	public double getErrorByThisNeuron(Neuron neuron){
		if(!outputLayer.contains(neuron))
			throw new IllegalArgumentException("OutputLayer does not contain this neuron!");
		if(!errorByNeuron.containsKey(neuron))
			throw new IllegalArgumentException("Errormap does not contain this neuron!");
		
		return errorByNeuron.get(neuron);
	}
}
