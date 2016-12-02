//http://www.programming-algorithms.net/article/45708/Floyd-Warshall-algorithm
//http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/

import java.io.File;
import java.util.Stack;

import util.Reader;

public class MainFloyd {

	public static void main(String[] args) {
		int [][] matriz = new int[5][5];
		int [][][] D = new int[6][5][5];
		int [][][] pi = new int[6][5][5];
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

		for (int k = 0; k < D.length; k++) {
			System.out.println("\nD"+k+ "");

			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					if(D[k][i][j] > 999)
						System.out.printf("   INF");
					else
						System.out.printf("%6d",  D[k][i][j]);
				}
				System.out.print("\n");
			}
		}

		//*"======================= PI");
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if(i==j || D[0][i][j] > 999)
					pi[0][i][j] = 9999;
				else if (i!=j && D[0][i][j] < 999)
					pi[0][i][j] = i;
			}
		}		

		System.out.println("\nPi[0]");
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if(pi[0][i][j] > 999)
					System.out.printf("   NIL");
				else
					System.out.printf("%6d",  pi[0][i][j]);
			}
			System.out.print("\n");
		}

		x=0;
		for (int k = 1; k < D.length; k++) {
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					if(D[k-1][i][j] <= D[k-1][i][x] + D[k-1][x][j])
						pi[k][i][j] = pi[k-1][i][j];
					else if (D[k-1][i][j] > D[k-1][i][x] + D[k-1][x][j])
						pi[k][i][j] = pi[k-1][x][j];
				}
			}
			x++;
		}

		for (int k = 0; k < D.length; k++) {
			System.out.println("\nPi["+k+ "]");
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					if(pi[k][i][j] > 999)
						System.out.printf("   NIL");
					else
						System.out.printf("%6d",  pi[k][i][j]);
				}
				System.out.print("\n");
			}
			x++;
		}

		//*"======================= Calculando o menor caminho");

		print_path(pi, 2, 3);


	}


	private static void print_path(int[][][] pi, int i, int j){
		/*if(i!=j){
			int pai = pi[5][i][j];
			System.out.println(pi[5][i][j]);
			print_path(pi, i, pai);
		} else{

		}*/
		Stack<Integer> st = new Stack<Integer>();

		int pai = 0;
		st.add(j);
		while(i!=j){
			pai = pi[5][i][j];
			st.add(pi[5][i][j]);
			j=pai;
		}
		
		System.out.println("\nMenor caminho de "+i + " para " + j);
		for (int k = 0; k <= st.size()+1; k++) {
			System.out.print(st.peek());
			st.pop();
		}
	}

}
