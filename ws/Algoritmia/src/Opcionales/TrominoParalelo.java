package Opcionales;

import java.util.concurrent.RecursiveAction;

/**
 * Titulo: Clase Tromino
 *
 * @author Omar Teixeira, UO281847
 * @version 11 mar 2022
 */
public class TrominoParalelo extends RecursiveAction{
	
	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Titulo: Clase HolePosition
	 *
	 * @author Omar Teixeira, UO281847
	 * @version 11 mar 2022
	 */
	private enum HolePosition {
		/**
		 * Arriba a la izquierda
		 */
		UP_LEFT,
		/**
		 * Arriba a la derecha
		 */
		UP_RIGHT,
		/*
		 * Abajo a la izquierda
		 */
		DOWN_LEFT,
		/**
		 * Abajo a la derecha
		 */
		DOWN_RIGHT;
	}
	
	/**
	 * Tablero
	 */
	private int[][] board;
	
	/**
	 * Contador
	 */
	private int counter = 1;
	
	/**
	 * Atributos
	 */
	private int n, x, y, xCoord, yCoord;
	
	/**
	 * Constructor del Tromino
	 * @param n
	 */
	public TrominoParalelo(int x, int y, int xCoord, int yCoord, int n, int[][] board) {
		if (n%2==0 && ((n/2)%2==0 || n==2)) {			
			this.x=x;
			this.y=y;
			this.xCoord=xCoord;
			this.yCoord=yCoord;
			this.n=n;
			this.board=board;	
			board[x][y] = -1;
		} else {
			System.out.println("El tamaño debe de ser potencia de 2.");
			System.exit(0);
		}
	}
	

	@Override
	protected void compute() {		
//		if (x < n && y < n) {
//			board[x][y] = -1;
//		} else {
//			System.out.println("La posición del hueco debe dentro del tablero.");
//		}
		if (n<=2) {
			setTromino(x, y, xCoord, yCoord, n);
		} else {	
			int xQuadrant1 = xCoord+n/2-1, yQuadrant1 = yCoord+n/2-1;
			int xQuadrant2 = xCoord+n/2-1, yQuadrant2 = yCoord+n/2;
			int xQuadrant3 = xCoord+n/2, yQuadrant3 = yCoord+n/2-1;
			int xQuadrant4 = xCoord+n/2, yQuadrant4 = yCoord+n/2;
			switch (checkHolePos(x, y, xCoord, yCoord, n)) {
				case UP_LEFT:
					xQuadrant1 = x;
					yQuadrant1= y;
					board[xCoord+n/2-1][yCoord+n/2]=counter;
					board[xCoord+n/2][yCoord+n/2-1]=counter;
					board[xCoord+n/2][yCoord+n/2]=counter;
					
					counter++;
					break;
				case UP_RIGHT:
					xQuadrant2 = x;
					yQuadrant2= y;
					board[xCoord+n/2-1][yCoord+n/2-1]=counter;
					board[xCoord+n/2][yCoord+n/2-1]=counter;
					board[xCoord+n/2][yCoord+n/2]=counter;
					
					counter++;
					break;
				case DOWN_LEFT:
					xQuadrant3 = x;
					yQuadrant3= y;
					board[xCoord+n/2-1][yCoord+n/2-1]=counter;
					board[xCoord+n/2-1][yCoord+n/2]=counter;
					board[xCoord+n/2][yCoord+n/2]=counter;
					
					counter++;
					break;
				case DOWN_RIGHT:
					xQuadrant4 = x;
					yQuadrant4= y;
					board[xCoord+n/2-1][yCoord+n/2-1]=counter;
					board[xCoord+n/2][yCoord+n/2-1]=counter;
					board[xCoord+n/2-1][yCoord+n/2]=counter;
					
					counter++;
					break;
			}
			TrominoParalelo quadrant1 = new TrominoParalelo(xQuadrant1, yQuadrant1, xCoord, yCoord, n/2, board);
			TrominoParalelo quadrant2 = new TrominoParalelo(xQuadrant2, yQuadrant2, xCoord, yCoord+n/2, n/2, board);
			TrominoParalelo quadrant3 = new TrominoParalelo(xQuadrant3, yQuadrant3, xCoord+n/2, yCoord, n/2, board);
			TrominoParalelo quadrant4 = new TrominoParalelo(xQuadrant4, yQuadrant4, xCoord+n/2, yCoord+n/2, n/2, board);
			
			invokeAll(quadrant1, quadrant2, quadrant3, quadrant4);
		}
	}	

	/**
	 * Método que posiciona el tromino en el caso de que el tamaño de la matriz sea 2
	 * @param x
	 * @param y
	 * @param xCoord
	 * @param yCoord
	 * @param n
	 */
	private void setTromino(int x, int y, int xCoord, int yCoord, int n) {
		int empty = board[x][y];
		board[xCoord+n/2-1][yCoord+n/2-1]=counter;
		board[xCoord+n/2-1][yCoord+n/2]=counter;
		board[xCoord+n/2][yCoord+n/2-1]=counter;
		board[xCoord+n/2][yCoord+n/2]=counter;
		board[x][y]=empty;
		counter++;
	}

	/**
	 * Método que comprueba que donde está el hueco
	 * @param x
	 * @param y
	 * @param xCoord
	 * @param yCoord
	 * @param n
	 * @return holePosition
	 */
	private HolePosition checkHolePos(int x, int y, int xCoord, int yCoord, int n) {
		if (x <= xCoord+n/2-1 && y <= yCoord+n/2-1) {
			return HolePosition.UP_LEFT;
		} else if (x <= xCoord+n/2-1 && y > yCoord+n/2-1) {
			return HolePosition.UP_RIGHT;
		} else if (x > xCoord+n/2-1 && y <= yCoord+n/2-1) {
			return HolePosition.DOWN_LEFT;
		} else {
			return HolePosition.DOWN_RIGHT;
		}
	}

	/**
	 * Método que imprime el tablero
	 */
	public void showBoard() {		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] >= 10000) {
					System.out.print(board[i][j] + " ");
				} else if (board[i][j] >= 1000) {
					System.out.print(board[i][j] + "  ");
				} else if (board[i][j] >= 100) {
					System.out.print(board[i][j] + "   ");
				} else if (board[i][j] >= 10) {
					System.out.print(board[i][j] + "    ");
				} else if (board[i][j] == -1) {
					System.out.print(board[i][j] + "    ");
				} else {
					System.out.print(board[i][j] + "     ");
				}				
			}
			System.out.println();
		}
	}

	
}
