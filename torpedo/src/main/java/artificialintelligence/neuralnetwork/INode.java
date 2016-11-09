package artificialintelligence.neuralnetwork;

import java.util.LinkedList;

/**
 * Gráfbeli csomópont interface.
 * @author zsigatibor
 *
 */
public interface INode {

	/**
	 * Bejövő él hozzáadása
	 * 
	 */
	public void addInput(Connection c);
	
	/**
	 * Kimenő él hozzáadása
	 * 
	 */
	public void addOutput(Connection c);
	
	/**
	 * Bejövő éleket adja meg.
	 * 
	 */
	public LinkedList<Connection> getAllInputs();
	
	/**
	 * Kimenő éleket adja meg.
	 * 
	 */
	public LinkedList<Connection> getAllOutputs();
	
	/**
	 * A csomópontban tárolt értéket adja meg.
	 * 
	 */
	Double getValue();
	
	/**
	 * A csomópontban tárolt érték adható meg vele.
	 */
	void setValue(double param);
	
	double derivateOfValue();
}
