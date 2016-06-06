/**
  Utilizar en conjunto con los archivos proporcionados por la catedra.
**/

package prog3.grafos.utiles;

import prog3.grafos.Arista;
import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class Recorridos<T> {

	public ListaGenerica<Vertice<T>> dfs(Grafo<T> gr){
		ListaGenerica<Vertice<T>> ret = new ListaGenericaEnlazada<Vertice<T>>();
		ListaGenerica<ListaGenerica<Vertice<T>>> listasEnListasEnListas = new ListaGenericaEnlazada<ListaGenerica<Vertice<T>>>();
		boolean[] marcas = new boolean[gr.listaDeVertices().tamanio()];
		
		// elegir un nodo para empezar
		boolean termine = false;
		while (!termine){
			int k;
			for (k=0; k<marcas.length; k++)
				if (marcas[k]){ break; } else termine=true;
			listasEnListasEnListas.agregarFinal(_dfs(gr, gr.listaDeVertices().elemento(k), marcas));	
		}
		
		//colapsar las listas
		while (!listasEnListasEnListas.fin()){
			ListaGenerica<Vertice<T>> listaA = listasEnListasEnListas.proximo();
			while (!listaA.fin()){
				ret.agregarFinal(listaA.proximo());
			}
		}
		
		// return
		return ret;
	}
	
	private ListaGenerica<Vertice<T>> _dfs (Grafo<T> gr, Vertice<T> v, boolean[] marcas){
		ListaGenericaEnlazada<Vertice<T>> ls = new ListaGenericaEnlazada<Vertice<T>>();
		ListaGenerica<ListaGenerica<Vertice<T>>> listaDeListasDeVertices = new ListaGenericaEnlazada<ListaGenerica<Vertice<T>>>();
		
		ListaGenerica<Arista<T>> adyacentes = gr.listaDeAdyacentes(v);
		adyacentes.comenzar();
		
		while (!adyacentes.fin()){
			Arista<T> ar = adyacentes.proximo();
			if (!marcas[ar.verticeDestino().posicion()]){
				marcas[ar.verticeDestino().posicion()] = true;
				listaDeListasDeVertices.agregarFinal(_dfs(gr, ar.verticeDestino(), marcas));
			}				
		}
		
		//colapsar las listas
		while (!listaDeListasDeVertices.fin()){
			ListaGenerica<Vertice<T>> listaA = listaDeListasDeVertices.proximo();
			while (!listaA.fin()){
				ls.agregarFinal(listaA.proximo());
			}
		}
		
		// return
		return ls;
	}
	
	public ListaGenerica<Vertice<T>> bfs (Grafo<T> gr){
		ListaGenerica<Vertice<T>> ret = new ListaGenericaEnlazada<Vertice<T>>();
		ListaGenericaEnlazada<ListaGenerica<Vertice<T>>> listasEnListas = new ListaGenericaEnlazada<ListaGenerica<Vertice<T>>>();  
		boolean[] marcas = new boolean[gr.listaDeVertices().tamanio()];
		
		boolean termine = false;
		while (!termine){
			int k;
			for (k=0; k<marcas.length; k++)
				if (marcas[k]) break; else termine = true;
			listasEnListas.agregarFinal(_bfs(gr, marcas, k));
		}
		
		//colapsar las listas
		while (!listasEnListas.fin()){
			ListaGenerica<Vertice<T>> listaA = listasEnListas.proximo();
			while (!listaA.fin()){
				ret.agregarFinal(listaA.proximo());
			}
		}
		// return
		return ret;
	}
	
	private ListaGenerica<Vertice<T>> _bfs (Grafo<T> gr, boolean[] marcas, int partida){
		ListaGenericaEnlazada<Vertice<T>> ret = new ListaGenericaEnlazada<Vertice<T>>();
		ListaGenericaEnlazada<Vertice<T>> cola = new ListaGenericaEnlazada<Vertice<T>>();
		
		cola.agregarFinal(gr.listaDeVertices().elemento(partida));
		while (!cola.esVacia()){
			ListaGenerica<Arista<T>> aristas = gr.listaDeAdyacentes(cola.elemento(0));
			marcas[cola.elemento(0).posicion()] = true;
			ret.agregarFinal(cola.elemento(0));
			cola.eliminarEn(0);
			
			while (!aristas.fin()){
				Vertice<T> v = aristas.proximo().verticeDestino();
				if (!marcas[v.posicion()])
					cola.agregarFinal(v);
			}
		}
		return ret;
	}
}
