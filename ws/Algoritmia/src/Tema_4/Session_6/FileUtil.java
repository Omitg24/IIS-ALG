package Tema_4.Session_6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Titulo: Clase FileUtil
 *
 * @author Omar Teixeira, UO281847
 * @version 18 mar 2022
 */
public abstract class FileUtil {

	/**
	 * Método que carga los colores
	 * @param inputFileName
	 * @param coloursList
	 */
	public static void loadColours(String inputFileName, List<String> coloursList) {
		String line;
	    try {
	    	   BufferedReader fichero = new BufferedReader(new FileReader(inputFileName));
	    		while (fichero.ready()) {
	    			line = fichero.readLine();
	    			coloursList.add(line);
	    		}
	    		fichero.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("El archivo no se ha encontrado.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("Error de entrada/salida.");
	    } 
	  }
	
	/**
	 * Método que carga las fronteras de países
	 * @param inputFileName
	 * @param countriesMap
	 */
	public static void loadCountriesAndFrontiers(String inputFileName, Map<Country, List<Country>> countriesMap) {
		String line;
		String[] lineArray = null;
		String[] frontierArray = null;
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(inputFileName));
	    		while (file.ready()) {
	    			line = file.readLine();
	    			lineArray = line.split(": ");
	    			Country country = new Country(lineArray[0]);
	    			frontierArray = lineArray[1].split(", ");
	    			List<Country> frontiersList = new ArrayList<Country>();
	    			for (int i = 0; i < frontierArray.length; i++) {
	    				if (!frontierArray[i].equals("NO")) {
	    					frontiersList.add(new Country(frontierArray[i]));
	    				}	    				
	    			}	    			
	    			countriesMap.put(country, frontiersList);
	    		}
	    		file.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("El archivo no se ha encontrado.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("Error de entrada/salida.");
	    } 
	}
}
