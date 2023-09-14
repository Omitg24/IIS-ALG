package Tema_7.Session_10_11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Titulo: Clase PromediadorImagen
 * 
 * @author Omar Teixeira, UO281847
 * @version 1 abr 2022
 */
public class PromediadorImagenBnB extends BranchAndBound {
	
	/**
	 * Imagenes real y mala
	 */
	private Imagen real_img, bad_img; // para almacenar las imagenes con patron bueno y malo (negativo del malo)
	/**
	 * Average imagen, grupo 1 y grupo 2
	 */
	private Imagen avg_img, half1_img, half2_img; // para almacenar los promedios del subconjunto 1 y 2
	/**
	 * Conjunto de imagenes
	 */
	private Imagen[] dataset; // almacena el conjunto de de imagenes generadas (buenas y malas)
	/**
	 * Solución
	 */
	private int[] sol; // array que determina donde poner las imágenes 0->no asignada, 1->primer subconjunto, 2->segundo subconjunto
	/**
	 * Mejor solución
	 */
	private int[] bestSol; // mejor solución
	/**
	 * Ancho y alto
	 */
	private int width, height; // ancho y alto de las imágenes
	//backtracking variables
	/**
	 * Contador
	 */
	private int counter; // contador de nodos en el arbol implícito
	/**
	 * Contador 1
	 */
	private int counter1;
	/**
	 * Contador 2
	 */
	private int counter2;
	/**
	 * ZNCC maximo
	 */
	private double max_zncc; // donde almacenar el ZNCC final
	
	/** Constructor
	* @real_path  ruta del modelo de imagen "buena" (patrón a encontrar) en disco
	* @bad_path  ruta del modelo de imagen "mala"
	* @n_real  numero de imagenes buenas (>= 1)
	* @n_bad  numero de imagenes "malas" (tiene que ser menor que las buenas) 
	* @s_noise  standard deviation for noise 
	*/
	public PromediadorImagenBnB(String real_path, String bad_path, int n_real, int n_bad, double s_noise) {
		
		assert (n_real >= 1) && (n_bad < n_real); 
		
		// Cargando los patrones de referencia (buena y mala)
		this.real_img = new Imagen(real_path);
		this.bad_img = new Imagen(bad_path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();
		this.avg_img = new Imagen(width, height);
		this.half1_img = new Imagen(width, height);
		this.half2_img = new Imagen(width, height);
		
		// Se crean con conjunto de imagenes con un array ordenado aleatoriamente para posicionar 
		// las imagenes buenas y malas aleatoriamente
		int total_imgs = n_real + n_bad; // numero total de imágenes
		this.dataset = new Imagen[total_imgs]; 
		this.sol = new int[total_imgs]; // dónde se almacena la solución actual (combinación de asignaciones): 0->no asignada, 1->primer subconjunto, 2->segundo subconjunto 
		this.bestSol = new int[total_imgs]; // dónde se almacena la mejor solución
		int[] rand_index = this.randomIndexes(total_imgs); // array con las posiciones aleatorias
		Imagen hold_img; // imagen temporal
		int region = 0; // 0-arriba, 1-bajo, 2-izquierda, 3-derecha
		for (int i=0; i<n_real; i++) { // imágenes buenas
			hold_img = new Imagen(this.width, this.height); // creación de la imagen
			hold_img.addSignal(this.real_img); // añadir los valores de los píxeles
			hold_img.suppressRegion(region); // suprimir una region
			hold_img.addNoise(s_noise); // añadir ruido
			this.dataset[rand_index[i]] = hold_img; // incluir la imagne en una posción aleatorio de dataset
			if (region == 3) region = 0;
			else region++;
		}
		region = 0;
		for (int i=n_real; i<n_real+n_bad; i++) { // bucle para las imágenes malas
			hold_img = new Imagen(this.width, this.height); 
			hold_img.addSignal(this.bad_img); 
			hold_img.invertSignal(); 
			hold_img.suppressRegion(region); 
			hold_img.addNoise(s_noise);   
			this.dataset[rand_index[i]] = hold_img; 
			if (region == 3) region = 0;
			else region++;
		}
	}
	
	/**
	 * Gener una array con valores de posiciones aleatorios
	 * @param n longitud del array
	 * @return 
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}
	
	/**
	 * Almacena las imágenes generadas según la mejor solución encontrada
	 * @out_dir directorio donde se almacenan las imágenes 
	 */
	public void saveResults(String out_dir) {
		this.avg_img.save(out_dir + "/img_avg.png");
		this.half1_img.save(out_dir + "/img_half1_avg.png");
		this.half2_img.save(out_dir + "/img_half2_avg.png");
		for(int i=0; i<this.dataset.length; i++) {
			this.dataset[i].save(out_dir + "/img_" + i + "_klass_" + this.bestSol[i] + ".png");
		}
	}
	
	/**
	 * @return devuelve el número de pasos requeridos por el algoritmo 
	 */
	public int getCounter() {
		return counter;
	}
	
	/** Calcula el ZNCC entre los promedios de los dos subconjuntos de imágenes
	 * @return el valor de ZNCC
	 */
	public double zncc() {
		return this.half1_img.zncc(this.half2_img);
	}
	
	/**
	 * Greedy algorithm: random instances for each half, the best one is the final solution    
	 * @n_tries numero de intentos aleatorios     
	 */
	public void splitSubsetsGreedy(int n_tries) {
		this.counter = 0;
		this.max_zncc=0;
		this.bestSol=null;
        for (int i = 0; i < n_tries; i++) {
        	Imagen group1 = new Imagen(width, height);
	        Imagen group2 = new Imagen(width, height);
            sol = generateRandomVector();
            
            counter++;
            for (int j = 0; j < sol.length; j++) {
                if (sol[j]==1) {
                	group1.addSignal(dataset[j]);
                } else if (sol[j]==2) {
                	group2.addSignal(dataset[j]);
                }
            }
            if (group1.zncc(group2) > max_zncc) {
                this.max_zncc = group1.zncc(group2);
                this.bestSol = sol;
                this.half1_img = group1.copy();
                this.half2_img = group2.copy();                
            }
        }
        this.avg_img = new Imagen(width, height);
        this.avg_img.addSignal(half1_img);
        this.avg_img.addSignal(half2_img);        
    }
	
	/**
	 * Método que genera el vector aleatorio
	 * 
	 * @return result, vector aleatorio
	 */
	private int[] generateRandomVector() {
		Random r = new Random();
		int[] result = new int[sol.length];
		for (int i = 0; i < sol.length; i++) {
			result[i]=r.nextInt(3);
		}
		return result;
	}
	
	/**
	 * Algoritmo backtracking con condición balanceo 
	 * @max_unbalancing: (condición de poda) determina la diferencia máxima en el número de imágenes
	 *                   entre los dos subconjuntos
	 */
	public void splitSubsetsBacktracking(int max_unbalancing) {
		int level = 0;		
		this.counter = 0;
		this.counter1=0;
		this.counter2=0;
		this.max_zncc=0;
		this.bestSol=null;
		auxBacktracking(level, max_unbalancing);		
        this.avg_img.addSignal(this.half1_img);
        this.avg_img.addSignal(this.half2_img); 
	}
	
	/**
	 * Método auxiliar para el Backtracking con Poda
	 * @param level
	 * @param max_unbalancing
	 */
	private void auxBacktracking(int level, int max_unbalancing) {
		counter++;
		if (level == dataset.length) {			
			Imagen group1 = new Imagen(width, height);
	        Imagen group2 = new Imagen(width, height);
	        for (int i = 0; i < sol.length; i++) {
                if (sol[i]==1) {
                	group1.addSignal(dataset[i]);
                } else if (sol[i]==2) {
                	group2.addSignal(dataset[i]);
                }
            }
            if (group1.zncc(group2) > max_zncc) {
                max_zncc = group1.zncc(group2);
                bestSol = sol;
                this.half1_img = group1.copy();
                this.half2_img = group2.copy();                
            }            
		} else {
			sol[level]=0;
			if (Math.abs((counter2-counter1)) <= max_unbalancing) {				
				auxBacktracking(level+1, max_unbalancing);
			}
			
			sol[level]=1;
			counter1++;
			if (Math.abs((counter2-counter1)) <= max_unbalancing) {				
				auxBacktracking(level+1, max_unbalancing);				
			}
			counter1--;
			
			sol[level]=2;
			counter2++;
			if (Math.abs((counter2-counter1)) <= max_unbalancing) {				
				auxBacktracking(level+1, max_unbalancing);				
			}
			counter2--;
		}
	}
	
	/**
	 * Algoritmo backtracking sin condición de balanceo.           
	 */
	public void splitSubsetsBacktracking() {
		int level = 0;		
		this.counter = 0;
		this.max_zncc=0;
		this.bestSol=null;
		auxBacktracking(level);		
        this.avg_img.addSignal(this.half1_img);
        this.avg_img.addSignal(this.half2_img);      
	}

	/**
	 * Método auxiliar para el Backtracking
	 * @param level
	 */
	private void auxBacktracking(int level) {
		counter++;
		if (level == dataset.length) {			
			Imagen group1 = new Imagen(width, height);
	        Imagen group2 = new Imagen(width, height);
	        for (int i = 0; i < sol.length; i++) {
                if (sol[i]==1) {
                	group1.addSignal(dataset[i]);
                } else if (sol[i]==2) {
                	group2.addSignal(dataset[i]);
                }
            }
            if (group1.zncc(group2) > max_zncc) {
                max_zncc = group1.zncc(group2);
                bestSol = sol;
                this.half1_img = group1.copy();
                this.half2_img = group2.copy();                
            }            
		} else {
			sol[level]=0;
			auxBacktracking(level+1);
			sol[level]=1;
			auxBacktracking(level+1);
			sol[level]=2;
			auxBacktracking(level+1);			
		}
	}
	
	/**
	 * Algoritmo BranchAndBound
	 */
	public void branchAndBound() {
		super.branchAndBound(new AverageNode(dataset, 0, new ArrayList<Integer>()));
		this.half1_img = ((AverageNode) bestNode).getHalf1();
		this.half2_img = ((AverageNode) bestNode).getHalf2();
		this.avg_img.addSignal(this.half1_img);
		this.avg_img.addSignal(this.half2_img);
		this.counter=counterBnB;
	}
}
