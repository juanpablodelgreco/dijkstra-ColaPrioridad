
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grafo {

	private int[][] matriz;
	private int cantNodos;
	private int cantAristas;
	private ArrayList<Nodo> nodos;
	private int[] gradosNodos;
	private int nodoSalida;
	private int nodoLlegada;
	private ArrayList<Arista> aristas = new ArrayList<Arista>();

	public Grafo(String path) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(path));
		this.cantNodos = scan.nextInt();
		this.nodoSalida = scan.nextInt() - 1;
		this.nodoLlegada = scan.nextInt() - 1;
		this.cantAristas = scan.nextInt();
		//Inicializo matriz
		this.inicializarMatriz();
		nodos = new ArrayList<Nodo>();
		gradosNodos = new int[this.cantNodos];
		//Agrego las aristas
		for (int i = 0; i < this.cantNodos; i++)
			gradosNodos[i] = 0;
		int n1;
		int n2;
		int peso;
		for (int i = 0; i < this.cantAristas; i++) {
			n1 = scan.nextInt() - 1;
			n2 = scan.nextInt() - 1;
			peso = scan.nextInt();
			this.gradosNodos[n1]++;
			this.gradosNodos[n2]++;
			//Si no es dirigido tengo que poner 2 veces esto
			this.ponerAristaPeso(n1, n2, peso); 
			this.aristas.add(new Arista(n1, n2));
		}

		// Agrego nodos
		for (int i = 0; i < this.cantNodos; i++) {
			Nodo nodo = new Nodo(i);
			nodo.setGrado(this.gradosNodos[i]);
			this.nodos.add(nodo);
		}
		scan.close();
	}

	private void inicializarMatriz() {
		int n = this.cantNodos;
		matriz = new int[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				matriz[i][j] = 0;
	}

	public void ponerArista(int fil, int col) {
		this.matriz[fil][col] = 1;
	}

	public void ponerAristaPeso(int fil, int col, int peso) {
		this.matriz[fil][col] = peso;
	}

	public boolean hayArista(int fil, int col) {
		return this.matriz[fil][col] != 0;
	}

	public int getPesoArista(int fil, int col) {
		return this.matriz[fil][col];
	}

	public int getCantNodos() {
		return cantNodos;
	}

	public int getNodoLlegada() {
		return nodoLlegada;
	}

	public int getNodoSalida() {
		return nodoSalida;
	}

}
