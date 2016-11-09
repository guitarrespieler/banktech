/**
 * 
 */
package artificialintelligence.NeuralNetworkInitializer;

import java.util.LinkedList;

import artificialintelligence.neuralnetwork.NeuralNetwork;
import artificialintelligence.neuralnetwork.Neuron;
import artificialintelligence.neuralnetwork.Node;
import artificialintelligence.neuralnetwork.NodeType;


/**
 * Ez az osztály felelős a szabványos bemeneten érkező
 * szöveg feldolgozásáért és futtatásáért.
 * @author zsigatibor
 *
 */
public class ArchitectureDecoder {
	private String[] input;
	
	/**
	 * a bemeneti dimenziók száma
	 */
	private int firstParam;
	
	/**
	 * a rejtett rétegek neuronszámai
	 */
	private LinkedList<Integer>params = new LinkedList<Integer>();
	
	/**
	 * a kimeneti dimenziók száma
	 */
	private int lastParam;	

	
	public ArchitectureDecoder(String[] inputArray) {
		input = inputArray;
		
		interpretString();
	}
	
	/**
	 * értelmezi a konstruktorban kapott stringet
	 * @throws Exception 
	 */
	private void interpretString(){		
		firstParam = Integer.parseInt(input[0]);
		lastParam = Integer.parseInt(input[input.length-1]);
					
		for (int i = 1; i < input.length-1; i++) {
			params.add(Integer.parseInt(input[i]));			
		}
	}

	/**
	 * Felépíti a neurális hálózatot
	 * @return referencia az elkészített neurális hálózat objektumra
	 */
	public NeuralNetwork buildNN(){
		NeuralNetwork NN = new NeuralNetwork();
		
		//bemenetek hozzáadása
		for(int i = 0; i < firstParam; i++){
			NN.addNewInput(new Node(NodeType.InputLayer));
		}
		
		//mesterséges neuronok hozzáadása
		for(int i = 0; i < params.size(); i++){
			int limit = params.get(i);
			LinkedList<Neuron> neuronList = new LinkedList<Neuron>();
			for(int j = 0; j < limit; j++){
				neuronList.add(new Neuron(NodeType.HiddenLayer));
			}
			NN.addNewLevel(neuronList);
		}
		
		//kimenetek hozzáadása
		for(int i = 0; i < lastParam; i++){
			NN.addNewOutput(new Neuron(NodeType.OutputLayer));
		}
		
		//kapcsolatok építése
		NN.generateConnections();
		
		return NN;		
	}
	
}
