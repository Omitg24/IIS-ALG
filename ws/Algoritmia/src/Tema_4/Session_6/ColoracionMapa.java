package Tema_4.Session_6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Titulo: Clase ColoracionMapa
 *
 * @author Omar Teixeira, UO281847
 * @version 18 mar 2022
 */
public class ColoracionMapa {
//Constantes:
	/**
	 * Constante coloursFile
	 */
	public static final String coloursFile = "files/colores.txt";
	/**
	 * Constante frontiersFile
	 */
	public static final String frontiersFile = "files/fronteras.txt";
	
//Atributos:
	/**
	 * Mapa de países
	 */
	private Map<Country, List<Country>> countriesMap = new HashMap<Country, List<Country>>();
	/**
	 * Lista de colores
	 */
	private List<String> coloursList = new ArrayList<String>();
	/**
	 * Lista de colores usados
	 */
	private List<String> usedColoursList = new ArrayList<String>();
	
//Implementación:
	/**
	 * Constructor de la coloracion del mapa
	 */
	public ColoracionMapa() {
		FileUtil.loadColours(coloursFile, coloursList);		//Se carga el fichero de colores
		FileUtil.loadCountriesAndFrontiers(frontiersFile, countriesMap);		//Se carga el fichero de paises
		updateReferences();		//Se actualizan las referencias
		colour();	//Se colorean los paises
		showCountriesAndFrontiers();		//Se muestran los paises y fronteras
		showColoursUsed();		//Se muestran los colore usados
	}
	
	/**
	 * Método que actualiza las referencias entre países
	 */
	private void updateReferences() {
		List<Country> countriesList = new ArrayList<Country>(countriesMap.keySet());
		int i = 0;
		for (Country mainCountry : countriesList) {	//Recorrer la lista de países
			List<Country> frontiersList =  countriesMap.get(mainCountry);	//Cargar la lista de fronteras
			for (Country frontier : frontiersList) {	//Recorrer fronteras de cada país
				for (Country country : countriesList) {	//Recorrer la lista de países 2
					if (frontier.getName().equals(country.getName())) {	//Si tienen el mismo nombre ->
						frontiersList.set(i, country);		//Se cambia la referencia
					}
				}
				i++;
			}
			i=0;
		}
	}

	/**
	 * Método que colorea los países
	 */
	private void colour() {
		List<Country> countriesList = new ArrayList<Country>(countriesMap.keySet());
		for (Country mainCountry : countriesList) {		//Recorrer la lista de paises
			colourCountries(mainCountry);	//Colorea cada pais y sus fronteras
		}
	}
	
	/**
	 * Método que colorea los paises y fronteras
	 * @param country
	 * @return true o false
	 */
	private void colourCountries(Country country) {
		boolean usedColour = false;		//Color usado
		for (String colour : coloursList) {		//Recorre la lista de colores
			for (Country frontier : countriesMap.get(country)) {	//Recorrer la lista de fonteras
				if (frontier.getColour() == colour) {		//Si la frontera tiene el mismo color que el color de de recorrer el bucle ->
					usedColour = true;		//Color usado pasa a true
					break;		//Se sale del bucle
				}
			}
			if (!usedColour) {		//Si no se ha usado el color ->
				country.setColour(colour);		//Se setea el color del país
				if (!usedColoursList.contains(colour)) {	//Si la lista de colores usados no contiene el color ->
					usedColoursList.add(colour);	//Se añade a la lista
				}
				break;	//Se usa el break para salir de esta iteraccion
			}
			usedColour = false;		//Se pasa el usedColor a false para el siguiente color
		}
	}

//Imprimir
	/**
	 * Método que imprime los colores
	 */
	@SuppressWarnings("unused")
	private void showColours() {
		System.out.println("-------- Lista de Colores --------");
		for (String c : coloursList) {
			System.out.println(c);
		}
	}
	
	/**
	 * Método que imprime los colores usados
	 */
	private void showColoursUsed() {
		System.out.println("\nEl número cromático es: " + usedColoursList.size());
		System.out.println("Se han usado los siguientes colores:");
		int i = 1;
		for (String c : usedColoursList) {
			System.out.println("\t" + i + ". " + c);
			i++;
		}
	}
	
	/**
	 * Método que imprime los países y las fronteras
	 */
	private void showCountriesAndFrontiers() {
		Set<Country> countriesList = countriesMap.keySet();
		System.out.println("-------- Lista de Países --------");
		for (Country c : countriesList) {
			System.out.println(c.toStringForMainCountry());
			for (Country frontier : countriesMap.get(c)) {
				System.out.println("\t" + frontier.toStringForFrontier());
			}
		}
	}	
}