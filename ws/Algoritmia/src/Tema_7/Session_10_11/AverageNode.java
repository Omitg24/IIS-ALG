package Tema_7.Session_10_11;

import java.util.ArrayList;

/**
 * Titulo: Clase AverageNode
 * 
 * @author Omar Teixeira, UO281847
 * @version 1 abr 2022
 */
public class AverageNode extends Node {

	/**
	 * Atributo dataset
	 */
	private Imagen[] dataset;
	/**
	 * Atributo sol
	 */
	private ArrayList<Integer> sol;	
	/**
	 * Atributos half1_avg, half2_avg
	 */
	private Imagen half1_avg, half2_avg;
	
	/**
	 * Constructor
	 * @param dataset
	 * @param depth
	 * @param sol
	 */
	public AverageNode(Imagen[] dataset, int depth, ArrayList<Integer> sol) {
		this.dataset=dataset;
		this.depth=depth;
		this.sol=new ArrayList<Integer>(sol);
		calculateHeuristicValue();
	}
	
	/**
	 * Método que calcula el valor heurístico
	 */
	@Override
	public void calculateHeuristicValue() {
		if (!isSolution()) {
			this.heuristicValue=-1;
		} else {
			Imagen group1 = new Imagen(dataset[0].getWidth(), dataset[0].getHeight());
			Imagen group2 = new Imagen(dataset[0].getWidth(), dataset[0].getHeight());
			for (int i=0; i<sol.size(); i++) {
				if (sol.get(i)==1) {
					group1.addSignal(dataset[i]);
				} else if (sol.get(i)==2) {
					group2.addSignal(dataset[i]);
				}
			}
			this.heuristicValue=-1*(group1.zncc(group2));
			this.half1_avg=group1.copy();
			this.half2_avg=group2.copy();			
		}		
	}

	/**
	 * Método que expande los nodos
	 * @return sol
	 */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> expanded = new ArrayList<Node>();
		sol.add(0);
		expanded.add(new AverageNode(dataset, depth+1, sol));
		sol.set(depth, 1);
		expanded.add(new AverageNode(dataset, depth+1, sol));
		sol.set(depth, 2);
		expanded.add(new AverageNode(dataset, depth+1, sol));
		return expanded;
	}

	/**
	 * Método isSolution
	 * @return true o false
	 */
	@Override
	public boolean isSolution() {
		return depth==dataset.length;
	}
	
	/**
	 * Método que devuelve la primera mitad
	 * @return half1_avg
	 */
	public Imagen getHalf1() {
		return half1_avg;
	}
	
	/**
	 * Método que devuelve la segunda mitad
	 * @return half2_avg
	 */
	public Imagen getHalf2() {
		return half2_avg;
	}
}