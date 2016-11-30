package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	public static int[][] lerArquivo(File arquivo){

		try {
			if (arquivo.exists()) {		
				FileReader fr = new FileReader(arquivo);		//faz a leitura do arquivo				
				BufferedReader br = new BufferedReader(fr);
				//equanto houver mais linhas
				String linha;
				int tamahoMatriz = Integer.parseInt(br.readLine());
				int[][] matriz = new int[tamahoMatriz][tamahoMatriz];
				int i = 0 ;
				while (br.ready()) {				
					linha = br.readLine();						//lê a proxima linha	
					String[] parts = linha.split(" ");
					//System.out.println(parts.length);
					if(!linha.isEmpty()){
						for (int j = 0; j < matriz.length; j++) {
							matriz[i][j] = Integer.parseInt(parts[j]);
						}
						i++;
					}
				}

				br.close();
				fr.close();
				return matriz;
			} else {
				return null;
			}


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
