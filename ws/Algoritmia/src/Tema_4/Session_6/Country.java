package Tema_4.Session_6;

/**
 * Titulo: Clase Country
 *
 * @author Omar Teixeira, UO281847
 * @version 18 mar 2022
 */
public class Country {

	/**
	 * Nombre
	 */
	private String name;
	/**
	 * Color
	 */
	private String colour;
	
	/**
	 * Constructor del pa�s
	 * @param name
	 */
	public Country(String name) {
		this.name=name;
	}
	
	/**
	 * M�todo que devuelve el nombre
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * M�todo que setea el color del pais
	 * @param colour
	 */
	public void setColour(String colour) {
		this.colour=colour;
	}
	
	/**
	 * M�todo que devuelve el color
	 * @return colour
	 */
	public String getColour() {
		return colour;
	}
	
	/**
	 * M�todo que devuelve el toString del pa�s principal
	 * @return toString
	 */
	public String toStringForMainCountry() {
		return name + ": ("+ colour + ")";
	}
	
	/**
	 * M�todo que devuelve el toString del pa�s frontera
	 * @return toString
	 */
	public String toStringForFrontier() {
		return name + " ("+ colour + ")";
	}
}
