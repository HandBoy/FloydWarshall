//http://www.programming-algorithms.net/article/45708/Floyd-Warshall-algorithm
//http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/

import java.io.File;

import com.sun.javafx.binding.StringFormatter;

import util.Reader;

public class MainFloyd {

	public static void main(String[] args) {
		int [][] matriz = new int[5][5];
		int [][][] D = new int[6][5][5];
		File arquivo = new File(args[0]);
		matriz = Reader.lerArquivo(arquivo);
		int cont = 0;
		int x = 0;
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				D[0][i][j] = matriz[i][j];
			}
		}				
		
		for (int k = 1; k < D.length; k++) {
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					cont = Integer.min(D[k-1][i][j], D[k-1][i][x]+D[k-1][x][j]);
					D[k][i][j] = cont;
				}				
			}
			x++;				
		}
		System.out.println("=======================");
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if(D[0][i][j] > 999)
					System.out.printf("   INF");
				else
					System.out.printf("%6d",  D[3][i][j]);
			}
			System.out.print("\n");
		}

	}

}
