package prog3.grafos.util;

import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class OrdenTopologico<T> {
	
	private int[] nodosFuente (Grafo<T> gr){
		int[] nodos = new int[gr.listaDeVertices().tamanio()];
		gr.listaDeVertices().comenzar();
		while (!gr.listaDeVertices().fin()){
			Vertice<T> v = gr.listaDeVertices().proximo();
			gr.listaDeAdyacentes(v).comenzar();
			while (!gr.listaDeAdyacentes(v).fin()){
				nodos[gr.listaDeAdyacentes(v).proximo().verticeDestino().posicion()]++;
			}
		}
		return nodos;
	}
	
	public ListaGenerica<Vertice<T>> ordenTopologico(Grafo<T> gr){
		int[] nodos = nodosFuente(gr);
		ListaGenerica<Vertice<T>> cola = new ListaGenericaEnlazada<>();
		ListaGenerica<Vertice<T>> lista = new ListaGenericaEnlazada<>();
		int i;
		for (i = 0; i<nodos.length; i++){
			if (nodos[i] == 0){
				cola.agregarFinal(gr.vertice(i));
				nodos[i]--;
			}
		}
		
		while (!cola.esVacia()){
			Vertice<T> v = cola.proximo();
			cola.eliminarEn(0);
			lista.agregarFinal(v);
			gr.listaDeAdyacentes(v).comenzar();
			while (!gr.listaDeAdyacentes(v).fin()){
				nodos[gr.listaDeAdyacentes(v).proximo().verticeDestino().posicion()]--;
			}
			for (int j = 0; j<nodos.length; j++) if (nodos[j] == 0) cola.agregarFinal(gr.vertice(j));
		}
		
		return lista;
	}
}
