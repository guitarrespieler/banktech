package artificialintelligence.NeuralNetworkInitializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import artificialintelligence.neuralnetwork.NeuralNetwork;


public class NNSolutionOne {
	
	private static Random randomGen = new Random();
	
	/**
	 * A feladat által kért várható érték.
	 */
	public static final double averageVal = 0.0;
	
	/**
	 * A feladat által kért szórás.
	 */
	public static final double deviationVal = 0.1;
	
	
	private static String input = "";
	
	public static void main(String[] args) {
		try {								
			//beolvasás		
						
			input = readIn();
			
			String[] inputArray = input.split(",");//vesszők mentén feldaraboljuk a stringet
						
			//értelmező létrehozása
			ArchitectureDecoder decoder = new ArchitectureDecoder(inputArray);
			//neurális hálózat létrehozása
			NeuralNetwork nn = decoder.buildNN();
			
			//Kimenetet előállító objektum létrehozása
			OutputBuilder output = new OutputBuilder(nn);			
			
			output.setFirstLine(input);
			
			//Kimenet előállítása a létrehozott neurális hálózatból
			System.out.println(output.generateOutputString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return a szabványos bemenetről olvasott szöveg
	 * @throws IOException ha valami hiba történik a beolvasás során
	 */
	public static String readIn() throws IOException {
		StringBuilder sb = new StringBuilder();		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str;
		
		while((str = br.readLine()) != null && str.length() != 0){
			sb.append(str);
			sb.append("\n");
		}
		br.close();		
		String inputStr = sb.toString();
		inputStr = inputStr.trim();
		return inputStr;
	}
	
	/**
	 * Normál eloszlású random számot generál 
	 * @param averageValue várható értékkel és
	 * @param deviation szórással.
	 */
	public static double generateRandomGaussian(double averageValue,double deviation){
		return randomGen.nextGaussian() * deviation + averageValue;
	}
	
	

}
