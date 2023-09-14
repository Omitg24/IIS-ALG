package Tema_6.Session_8_9;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Titulo: Clase Imagen
 * 
 * @author Omar Teixeira, UO281847
 * @version 1 abr 2022
 */
public class Imagen {
	/**
	 * Imagen
	 */
	private float[][] img;
	/**
	 * Ancho y alto
	 */
	private int width, height;
	
	/**
	 * constructor para leer una imagen de disco
	 * @param img_path ruta del archivo 
	 */
	public Imagen(String img_path) {
		try 
		{
		    BufferedImage buff_img = ImageIO.read(new File(img_path)); //lectura imagen 		    
		    //store data into a float array
		    this.width = buff_img.getWidth();
		    this.height = buff_img.getHeight();
		    Raster raster = buff_img.getData();
		    this.img = new float[width][height];
		    for (int i = 0; i < width; i++)
		        for (int j = 0; j < height; j++)
		        	this.img[i][j] = raster.getSample(i, j, 0);		    		    
		    this.zeroNormalization(); // Cero-nomalización (media de los valores de los píxeles es cero
		                              // y su desviación estándar es 1)
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	/**
	 *  Constructor para generar una imagen vacia (los niveles de gris de todos los píxeles a zero)
	 * @param width píxeles de ancho
	 * @param height píxeles de alto
	 */
	public Imagen(int width, int height) {
		this.width = width;
		this.height = height;
		this.img = new float[this.width][this.height];
	}
	
	/**
	 * Método que devuelve el ancho
	 * @return width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Método que devuelve el alto
	 * @return height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Método que devuelve la imagen
	 * @return img
	 */
	public float[][] getSignal() {
		return this.img;
	}
	
	/**
	 * Permite añadir la señal (niveles de gris para cada píxel) de otra imagen
	 * La suma de dos imágenes cero-normalizadas dan como resultado otra imagen cero-nomalizada
	 * La suma de dos imágenes cero-normalizadas es un promedio en lugar de una acumulación
	 * @param img imagen a añadir
	 */
	public void addSignal(Imagen img) {
		for (int i = 0; i < width; i++)
	        for (int j = 0; j < height; j++)
	            this.img[i][j] += img.img[i][j];
	}
	
	/**
	 * Añade ruido, additivo Gaussiano, a los píxles de la imagen
	 * @param noise_std desviación estándar para el modelo de ruido
	 */
	public void addNoise(double noise_std) {
		Random rand = new Random();
	    for (int i = 0; i < width; i++)
	        for (int j = 0; j < height; j++)
	            this.img[i][j] += rand.nextGaussian() * noise_std;
	}
	
	/**
	 * Obtiene el negativo de una imagen, suponemos la imagen está cero-normalizada
	 */
	public void invertSignal() {
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				this.img[i][j] *= -1;
	}
	
	/**
	 *  @region indica que región hay que suprimir (por los píxeles a 0): 0-arriba, 1-abajo, 
	 *          2-izquierda and 3-derecha
	 */
	public void suppressRegion(int region) {
		//suppression loop
		switch (region) {
			case 0: // remove up half
				for (int i=0; i<this.width; i++)
					for (int j=0; j<this.height/2; j++)
						this.img[i][j] = 0;
				break;
			case 1: // remove bottom half
				for (int i=0; i<this.width; i++)
					for (int j=this.height/2; j<this.height; j++)
						this.img[i][j] = 0;
				break;
			case 2: // remove left half
				for (int i=0; i<this.width/2; i++)
					for (int j=0; j<this.height; j++)
						this.img[i][j] = 0;
				break;
			default: // remove right half
				for (int i=this.width/2; i<this.width; i++)
					for (int j=0; j<this.height; j++)
						this.img[i][j] = 0;
		}
		
		// Cero-normalización
		this.zeroNormalization();
	}
	
	/** 
	 * Calcula la Correlación cruzada cero normalizada (Zero Mean Normalized Cross-Correlation, ZNCC)
	 * con respecto a otra image 
	 * @param img imagen de referencia para el cálculo de la ZNCC(img, this)
	 * @return
	 */
	public float zncc(Imagen img) {
		float cc = 0;
		float std_i = 0;
		float mean_1 = this.computeMean();
		float mean_2 = img.computeMean();
		float std_1 = this.computeStd();
		float std_2 = img.computeStd();
		if ((std_1 == 0) || (std_2 == 0))
			return 0;
		std_i = (float)(1.0 / (std_1 * std_2));
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				cc += (this.img[i][j]-mean_1) * (img.img[i][j]-mean_2);
		return (cc * std_i) / (this.width * this.height);
	}
	
	/**
	 * Almacena la imagen en formato PNG
	 * @param file_path ruta (terminada en .png) donde guardar la imagen
	 */
	public void save(String file_path) {
		int[] p = new int[1];
		int[][] img_ubyte = this.toUnsignedByte();
		BufferedImage buff_img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = buff_img.getRaster();
		for(int i = 0; i < this.width; i++) {
	        for(int j = 0; j < this.height; j++) {
	        	p[0] = img_ubyte[i][j];
	            raster.setPixel(i, j, p);
	        }
	    }
		try {
			ImageIO.write(buff_img, "png", new File(file_path));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @return una matriz de enteros para representar los píxeles en el rango [0, 255] (byte sin signo)
	 */
	public int[][] toUnsignedByte(){
		int[][] hold_img = new int[this.width][this.height];
		// Computes maximum and minimum values
		float min = Float.MAX_VALUE;
		float max = Float.MIN_VALUE;
		for(int i = 0; i < this.width; i++)
	        for(int j = 0; j < this.height; j++) {
	            if (this.img[i][j] > max)
	            	max = this.img[i][j];
	            if (this.img[i][j] < min)
	            	min = this.img[i][j];
	        }
		// Linear map to fit 0-255 byte range
		float a = (float) (255.0 / (max - min));
		float b = (float) (-1.0 * a * min); 
		for(int i = 0; i < this.width; i++)
	        for(int j = 0; j < this.height; j++) 
	        	hold_img[i][j] = (int)(a * this.img[i][j] + b);
	    return hold_img;
	}
	
	/**
	 * Fuerza a que los valores de los píxeles estén zero nomralizados (media 0 y desviación estándar 1)
	 */
	public void zeroNormalization() {
		float mean = this.computeMean();
		float std = this.computeStd();
		for (int i=0; i<this.img.length; i++) 
			for (int j=0; j<this.img[i].length; j++)
				this.img[i][j] = (this.img[i][j] - mean) / std;
	}
	
	/**
	 * @return devluelve el valor medio de los niveles de gris (píxeles)
	 */
	private float computeMean() {
		float total = 0;
		for (int i=0; i<this.width; i++) 
			for (int j=0; j<this.height; j++)
				total += this.img[i][j];
		return total / (this.width * this.height);
	}
	
	/**
	 * @return calcular la desviación estándar de los píxeles
	 */
	private float computeStd() {
		float hold;
		float total = 0;
		float mean = this.computeMean();
		for (int i=0; i<this.width; i++) 
			for (int j=0; j<this.height; j++) {
				hold = (this.img[i][j] - mean);
				total += hold * hold;
			}
		return (float) Math.sqrt(total / (this.width * this.height));
	}
	
	/**
	 * @return genera una copia de esta imagen
	 */
	public Imagen copy() {
		Imagen hold_img = new Imagen(this.width, this.height);
		hold_img.addSignal(this);
		return hold_img;
	}
}