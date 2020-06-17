

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
	
	private Grafo grafo;
	private int nodoInicial;
	private int cantNodos;
	boolean nodoTerminado[];
	private ArrayList<CostoAlNodo> costos;

	private static final int INFINITO = -1;

	public Dijkstra(String path) throws FileNotFoundException 
	{	
		grafo = new Grafo(path);
		this.nodoInicial = grafo.getNodoSalida();
		this.cantNodos = grafo.getCantNodos();
		this.nodoTerminado = new boolean[cantNodos];
		costos = new ArrayList<CostoAlNodo>();
	}

	@SuppressWarnings("unchecked")
	public void ejecutar() throws IOException 
	{
		Queue<CostoAlNodo> cola = new PriorityQueue<CostoAlNodo>();
		CostoAlNodo nodoActual = new CostoAlNodo(this.nodoInicial, 0);
		CostoAlNodo actualizado = null;
		cola.add(nodoActual);
		int nodo = nodoActual.getNodo();

		for (int i = 0; i < this.cantNodos; i++) 
		{
			if (i != nodo) 
				costos.add(new CostoAlNodo(i, INFINITO));
			else 
			{
				costos.add(new CostoAlNodo(i, 0));
				costos.get(i).agregarNodoAlCamino(nodo);
			}
		}

		while (!cola.isEmpty())
		{
			nodoActual = cola.poll();
			nodo = nodoActual.getNodo();
			
			for (int i = 0; i < this.cantNodos; i++) 
			{
				if (nodo != i && !this.nodoTerminado[i]) 
				{
					if (this.grafo.hayArista(nodo, i)) 
					{
						if (this.costos.get(i).getCostoMinimo() == INFINITO || this.costos.get(nodo).getCostoMinimo() + this.grafo.getPesoArista(nodo, i) < this.costos.get(i).getCostoMinimo()) {
							actualizado = this.costos.get(i);
							actualizado.setCostoMinimo(this.costos.get(nodo).getCostoMinimo() + this.grafo.getPesoArista(nodo, i));
							actualizado.setCaminoMasCorto((ArrayList<Integer>)this.costos.get(nodo).getCaminoMasCorto().clone());
							actualizado.agregarNodoAlCamino(i);
							
							if (!cola.contains(actualizado))
								cola.add(actualizado);
						}
					}
					}
				}
			this.nodoTerminado[nodo] = true;
		}
		this.escribirSolucionEnConsola();
	}

	private void escribirSolucionEnConsola() 
	{
		for(CostoAlNodo c:costos)
		System.out.print(c.getCostoMinimo()+" ");
	}
}
