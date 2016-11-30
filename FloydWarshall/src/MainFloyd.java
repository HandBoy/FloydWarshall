//http://www.programming-algorithms.net/article/45708/Floyd-Warshall-algorithm

import java.io.File;

import com.sun.javafx.binding.StringFormatter;

import util.Reader;

public class MainFloyd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0){
			System.out.println("Passe algum argumento para o programa");
			System.exit(0);
		}
		File arquivo = new File(args[0]);
		
		if(arquivo.exists()){
			System.out.println("Arquivo Encontrado.");
			int [][] matriz = Reader.lerArquivo(arquivo);
			floydWarshall(matriz);
			/*for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					System.out.printf("%3d",  matriz[i][j]);
				}
				System.out.print("\n");
			}*/
		} else {
			System.out.println("Arquivo Não Encontrado: " + args[0]);
			System.exit(0);
		}

	}
	
	public static void floydWarshall(int[][] matriz){
		for (int k = 0; k < matriz.length; k++) {
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					matriz[i][j] = Integer.min(matriz[i][j],matriz[i][k]+matriz[k][j]);
				}
			}
		}
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				System.out.printf("%4d",  matriz[i][j]);
			}
			System.out.print("\n");
		}
		
	}
	
	

}
