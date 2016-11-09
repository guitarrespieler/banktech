package artificialintelligence.neuralnetwork;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Mesterséges neuront megvalósító osztály
 * @author zsigatibor
 *
 */
public class Neuron extends Node{
	
	/**
	 * Kulcs: kapcsolat
	 * érték: kapcsolat súlya szerinti derivált
	 */
	private Map<Connection,Double> deltaByWeight = new LinkedHashMap<Connection, Double>();
	
	/**
	 * bias szerinti derivált
	 */
	private double derivateByBias = 0.0;
	
	/**
	 * Neuron bias értéke
	 */
	private double bias = 0.0;
	
	private double delta = 0.0;

	public Neuron(NodeType typeOfNode) {
		super(typeOfNode);
	}
	
	/**
	 * Megvalósítja a neuron aktivációs függvényét (ReLU)
	 */
	public void activate(){
		double tempvalue = 0;
		for (Connection connection : inputs) {
			double previousVal = connection.getInput().getValue();//elkérjük az előző réteg beli elemben tárolt értéket
			tempvalue += previousVal*connection.getWeight();	  //szummázzuk a value változóban a súlyozott értékeket	
		}
		tempvalue += bias;
		switch (type) {
		case HiddenLayer://belső, rejtett réteg beli neuronok esetén
			value = Math.max(0, tempvalue); //ReLU-fv.
			break;
		default:
			value = tempvalue;				//lineáris fv.
			break;
		}
		
	}

	/**
	 * 
	 * @param conn ezen a kapcsolaton keresztül
	 * @param value ilyen értékű a súly szerinti derivált
	 */
	public void addDerivateByWeightValue(Connection conn, double value){
		deltaByWeight.put(conn, value);
	}
	
	/**
	 * Megadja, hogy a paraméterül kapott kapcsolat felől
	 * milyen értékű a súly szerinti derivált
	 */
	public double getDerivateByWeight(Connection conn){
		if(!deltaByWeight.containsKey(conn))
			throw new IllegalArgumentException("DerivateByWeight map does not contain this key!");
		return deltaByWeight.get(conn);
	}
	
	public double getBias() {
		return bias;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}
	
	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}
	
	public double getDeltaB() {
		return derivateByBias;
	}

	public void setDeltaB(double derivByB) {
		this.derivateByBias = derivByB;
	}
	
}
