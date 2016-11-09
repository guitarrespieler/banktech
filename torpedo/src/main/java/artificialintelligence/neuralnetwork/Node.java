package artificialintelligence.neuralnetwork;

import java.util.LinkedList;

/**
 * INode alap megvalósítása. Alapvetően ebből lesz
 * a bemenet és a kimenet.
 * @author zsigatibor
 *
 */
public class Node implements INode {
	
	/**
	 * Kimenő élek tárolására
	 * 
	 */
	protected LinkedList<Connection> outputs = new LinkedList<Connection>();
	
	/**
	 * Bejövő élek tárolására
	 * 
	 */
	protected LinkedList<Connection> inputs = new LinkedList<Connection>();
	
	/**
	 * Csomópontban lévő ReLU érték tárolására
	 */
	protected Double value = 0.0;
	
	protected NodeType type;
	
	/**
	 * 
	 * @param typeOfNode a csomópont típusa
	 */
	public Node(NodeType typeOfNode) {
		type = typeOfNode;
	}

	public void addInput(Connection c) {
		// TODO Auto-generated method stub
		
	}

	public void addOutput(Connection c) {
		// TODO Auto-generated method stub
		
	}

	public LinkedList<Connection> getAllInputs() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Connection> getAllOutputs() {
		// TODO Auto-generated method stub
		return null;
	}

	public Double getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setValue(double param) {
		// TODO Auto-generated method stub
		
	}

	public double derivateOfValue() {
		// TODO Auto-generated method stub
		return 0;
	}

//	/* (non-Javadoc)
//	 * @see solutionOne.INode#addInput(solutionOne.Connection)
//	 */
//	@Override
//	public void addInput(Connection c) {
//		inputs.add(c);
//	}
//
//	/* (non-Javadoc)
//	 * @see solutionOne.INode#addOutput(solutionOne.Connection)
//	 */
//	@Override
//	public void addOutput(Connection c) {
//		outputs.add(c);
//
//	}
//
//	/* (non-Javadoc)
//	 * @see solutionOne.INode#getAllInputs()
//	 */
//	@Override
//	public LinkedList<Connection> getAllInputs() {
//		return inputs;
//	}
//
//	/* (non-Javadoc)
//	 * @see solutionOne.INode#getAllOutputs()
//	 */
//	@Override
//	public LinkedList<Connection> getAllOutputs() {
//		return outputs;
//	}
//
//	/* (non-Javadoc)
//	 * @see solutionOne.INode#getValue()
//	 */
//	@Override
//	public Double getValue() {
//		return value;
//	}
//
//	/* (non-Javadoc)
//	 * @see solutionOne.INode#setValue()
//	 */
//	@Override
//	public void setValue(double param) {
//		value = param;		
//	}
//
//	@Override
//	public double derivateOfValue() {
//		return (value > 0)? 1.0 : 0.0;//0, ha a ReLU 0, 0-t ad vissza Egyébként 1-et
//	}

}
