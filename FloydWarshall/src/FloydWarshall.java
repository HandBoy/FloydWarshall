import java.util.Stack;

public class FloydWarshall {
	private int [][] matriz = new int[5][5];
	private int [][][] D = new int[6][5][5];
	private int [][][] pi = new int[6][5][5];
	
	
	
	public FloydWarshall(int[][] matriz) {
		super();
		this.matriz = matriz;
	}
	
	public void calcPesosCaminhosMinimos(){
		System.out.println("Calculando Pesos de Caminhos Minimos");
		int cont;
		int x = 0;
		
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				D[0][i][j] = this.matriz[i][j];
			}
		}				

		for (int k = 1; k < this.D.length; k++) {
			for (int i = 0; i < this.matriz.length; i++) {
				for (int j = 0; j < this.matriz.length; j++) {
					cont = Integer.min(this.D[k-1][i][j], this.D[k-1][i][x]+this.D[k-1][x][j]);
					this.D[k][i][j] = cont;
					if(i==j && cont < 0){
						System.out.println("Ciclo negativo detectado do j " +j );
						System.exit(0);
					}

				}				
			}
			x++;				
		}
	}
	
	public void calcMatrizPredecessores(){
		System.out.println("Calculando Matriz de Predecessores");
		int x = 0;
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				if(i==j || D[0][i][j] > 999)
					this.pi[0][i][j] = 9999;
				else if (i!=j && D[0][i][j] < 999)
					this.pi[0][i][j] = i;
			}
		}		

		x=0;
		for (int k = 1; k < this.D.length; k++) {
			for (int i = 0; i < this.matriz.length; i++) {
				for (int j = 0; j < this.matriz.length; j++) {
					if(this.D[k-1][i][j] <= this.D[k-1][i][x] + this.D[k-1][x][j])
						this.pi[k][i][j] = this.pi[k-1][i][j];
					else if (this.D[k-1][i][j] > this.D[k-1][i][x] + this.D[k-1][x][j])
						this.pi[k][i][j] = this.pi[k-1][x][j];
				}
			}
			x++;
		}
		System.out.println("Matriz de Predecessores calculada");
	}
	
	public void printCaminhoMinimo(int i, int j){
		Stack<Integer> st = new Stack<Integer>();
		System.out.println("\nMenor caminho de "+i + " para " + j);
		int pai = 0;
		st.add(j);
		while(i!=j){
			pai = this.pi[5][i][j];
			st.add(this.pi[5][i][j]);
			j=pai;
		}		
		for (int k = 0; k <= st.size()+1; k++) {
			System.out.print(st.peek());
			st.pop();
		}
	}

	public void print(int[][][]  matriz, String nome, String infinito){
		for (int k = 0; k < matriz.length; k++) {
			System.out.println("\n"+nome+"["+k+"]");
			for (int i = 0; i < matriz.length-1; i++) {
				for (int j = 0; j < matriz.length-1; j++) {
					if(matriz[k][i][j] > 999)
						System.out.printf(infinito);
					else
						System.out.printf("%6d",  matriz[k][i][j]);
				}
				System.out.print("\n");
			}
		}
	}

	public void print(int[][][]  matriz, String nome, String infinito, int index){
		System.out.println("\n"+nome+"["+index+"]");
		for (int i = 0; i < matriz.length-1; i++) {
			for (int j = 0; j < matriz.length-1; j++) {
				if(matriz[index][i][j] > 999)
					System.out.printf(infinito);
				else
					System.out.printf("%6d",  matriz[index][i][j]);
			}
			System.out.print("\n");
		}

	}
}
