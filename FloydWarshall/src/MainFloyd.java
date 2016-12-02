//http://www.programming-algorithms.net/article/45708/Floyd-Warshall-algorithm
//http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/

import java.io.File;

import util.Reader;

public class MainFloyd {

	public static void main(String[] args) {
		int [][] matriz = new int[5][5];
		File arquivo = new File(args[0]);
		matriz = Reader.lerArquivo(arquivo);
		
		FloydWarshall floyd = new FloydWarshall(matriz);
		floyd.calcPesosCaminhosMinimos();
		floyd.calcMatrizPredecessores();

		floyd.printCaminhoMinimo(3-1, 4-1);
	}


	

}
