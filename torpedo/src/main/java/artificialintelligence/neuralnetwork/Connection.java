package artificialintelligence.neuralnetwork;

/**
 * Neuronok közötti kapcsolatokat reprezentáló osztály.
 * @author zsigatibor
 *
 */
public class Connection {
	private INode input;
	private INode output;
	
	private double weight = 0.0;

	public Connection(double weightVal) {
		weight = weightVal;
	}
	
	public INode getInput() {
		return input;
	}

	public void setInput(INode input) {
		this.input = input;
	}

	public INode getOutput() {
		return output;
	}

	public void setOutput(INode output) {
		this.output = output;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
