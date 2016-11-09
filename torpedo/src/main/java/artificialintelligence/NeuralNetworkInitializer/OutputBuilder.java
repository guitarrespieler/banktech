package artificialintelligence.NeuralNetworkInitializer;

import java.util.LinkedList;

import artificialintelligence.neuralnetwork.Connection;
import artificialintelligence.neuralnetwork.NeuralNetwork;
import artificialintelligence.neuralnetwork.Neuron;

/**
 * A megfelelő kimenet előállításáért felelős osztály.
 * @author zsigatibor
 *
 */
public class OutputBuilder {

	private NeuralNetwork nn;
	
	private String firstLine = "";
	
	public OutputBuilder(NeuralNetwork nnetwork){
		nn = nnetwork;
	}
	
	/**
	 * Bejárja a neurális hálót és az első feladatnak
	 * megfelelő kimenetet hozza létre.
	 * 
	 */
	public String generateOutputString(){
		if(nn == null)
			throw new NullPointerException("NeuralNetwork object is null!");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(firstLine);
		sb.append("\n");
		
		LinkedList<LinkedList<Neuron>> networklist = nn.getHiddenLayers();
		
		for (LinkedList<Neuron> LinkedList : networklist) {
			for (Neuron neuron : LinkedList) {
				for (Connection c : neuron.getAllInputs()) {
					sb.append(c.getWeight());
					sb.append(",");					
				}
				sb.append(neuron.getBias());
				sb.append("\n");
			}
		}
		for (Neuron node : nn.getOutputs()) {
			for (Connection c : node.getAllInputs()) {
				sb.append(c.getWeight());
				sb.append(",");					
			}
			sb.append(node.getBias());
			sb.append("\n");
		}
		
		return sb.toString();
	}

	/**
	 * Beállítható vele az első sor értéke,
	 * így nem kell újra kiszámolni a már
	 * meglévő információt
	 */
	public void setFirstLine(String s){
		firstLine = s;
	}
}
