## Ej. 1

1. La diferencia entre un grafo y un arbol es que a diferencia de un arbol, donde los diferentes nodos siguen una relacion jerarquica, en un grafo todos los nodos estan al "mismo" nivel.

2. 
   1. Grafo no dirigido, no importa en que direccion vamos cuando cruzamos la frontera.
   1. Grafo dirigido, hay un pais que exporta y uno que importa.
   1. Grafo no dirigido, las computadoras conectadas entre si pueden hablar entre ellas sin distincion de "direccion de la conexion".
   1. Grafo dirigido. *x* **usa** a *y* por ello tenemos una relacion *direccional*, por llamarla de alguna manera

## Ej. 2

1. 
   1. Si se requiere que el grafo sea conexo, debe tener al menos *n-1* aristas, para conectar a todos los nodos a una sola malla.
   1. Para que este sea aciclico, debe tener *k* aristas, donde *k<=n-1*
   1. Para que cumpla los dos requisitos al mismo tiempo, debe tener exactamente *n-1* aristas distribuidas siguiendo la logica necesaria para que se cumplan las condiciones.
   1. Para que el grafo sea completo, debe tener *Sumatoria(n=0 -> n-1)[n]* aristas.

2. En este caso, el mayor numero de aristas que podemos tener es de 2 veces el numero de aristas para un grafo completo no dirigido, ya que podemos tener aristas que vayan del nodo *A* al nodo *B* y viceversa, conectandolos entre si.

## Ej. 3
1. Una matriz de adyacencia 
