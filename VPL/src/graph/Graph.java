/**
 * Clase que implementa al grafo.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package graph;

import java.io.Serializable;
import java.util.ArrayList;

public class Graph<V, E> implements Serializable {

	private ArrayList<Vertex<V>> vertices;
	private ArrayList<Edge<V, E>> edges;
	private Vertex<V> head;
	private ArrayList<Vertex<V>> tail;

	/**
	 * Constructor por omisi&oacute;n.
	 */
	public Graph() {

		vertices = new ArrayList<Vertex<V>>();
		edges = new ArrayList<Edge<V, E>>();
		head = null;
	}

	/**
	 * M&eacute;todo para obtener el inicio del grafo.
	 * 
	 * @return <code>Vertex<V></code>
	 */
	public Vertex<V> getHead() {

		return head;
	}

	/**
	 * M&eacute;todo para obtener el n&uacute;mero de aristas.
	 * 
	 * @return <code>int</code>
	 */
	public int getNumEdges() {

		return edges.size();
	}

	/**
	 * M&eacute;todo para obtener el n&uacute;mero de v&eacute;rtices.
	 * 
	 * @return <code>int</code>
	 */
	public int getNumVertices() {

		return vertices.size();
	}

	/**
	 * M&eacute;todo para obtener la lista de v&eacute;rtices.
	 * 
	 * @return <code>ArrayList<Node></code>
	 */
	public ArrayList<Vertex<V>> getVertices() {

		return vertices;
	}

	/**
	 * M&eacute;todo para obtener la lista de aristas.
	 * 
	 * @return <code>ArrayList<Edge></code>
	 */
	public ArrayList<Edge<V, E>> getEdges() {

		return edges;
	}

	/**
	 * M&eacute;todo para especificar el inicio del grafo.
	 * 
	 * @param head
	 *            <code>Vertex<V></code>
	 */
	public void setHead(V value) {

		head = this.getVertex(value);
	}

	/**
	 * M&eacute;todo para agregar un v&eacute;rtice.
	 * 
	 * @param value
	 *            <code>V</code>
	 */
	public boolean addVertex(V value) {

		Vertex<V> v = new Vertex(value);

		if (getVertex(value) == null)
			if (vertices.add(v))
				return true;

		return false;
	}

	/**
	 * M&eacute;todo para agregar una arista.
	 * 
	 * @param source
	 *            <code>V</code>
	 * @param dest
	 *            <code>V</code>
	 * @param weight
	 *            <code>E</code>
	 */
	public boolean addEdge(V source, V dest, E weight, int index) {

		Vertex<V> s = getVertex(source);
		Vertex<V> d = getVertex(dest);

		if (s != null & d != null) {

			Edge<V, E> e = new Edge(s, d, weight);
			if (getEdge(source, dest, weight) == null)
				if (edges.add(e) && s.addNeighborAt(index, d))
					return true;
		}

		return false;
	}

	/**
	 * M&eacute;todo para obtener un v&eacute;rtice por valor.
	 * 
	 * @param value
	 *            <code>V</code>
	 * @return <code>Vertex<V></code>
	 */
	public Vertex<V> getVertex(V value) {

		Vertex<V> temp = new Vertex(value);

		for (int i = 0; i < getNumVertices(); i++)
			if (vertices.get(i).equals(temp))
				return vertices.get(i);

		return null;
	}

	/**
	 * M&eacute;todo para obtener un v&eacute;rtice por &iacute;ndice.
	 * 
	 * @param index
	 *            <code>int</code>
	 * @return <code>Vertex<V></code>
	 */
	public Vertex<V> getVertexAt(int index) {

		return vertices.get(index);
	}

	/**
	 * M&eacute;todo para obtener una arista por valor.
	 * 
	 * @param value
	 *            <code>V</code>
	 * @return <code>Vertex<V></code>
	 */
	public Edge<V, E> getEdge(V source, V dest, E weight) {

		Vertex<V> s = new Vertex(source);
		Vertex<V> d = new Vertex(dest);
		Edge<V, E> temp = new Edge(s, d, weight);

		for (int i = 0; i < getNumEdges(); i++)
			if (edges.get(i).equals(temp))
				return edges.get(i);

		return null;
	}

	/**
	 * M&eacute;todo para obtener una arista por &iacute;ndice.
	 * 
	 * @param value
	 *            <code>V</code>
	 * @return <code>Vertex<V></code>
	 */
	public Edge<V, E> getEdgeAt(int index) {

		return edges.get(index);
	}

	/**
	 * M&eacute;todo para elimiar un vertice.
	 * 
	 * @param value
	 *            <code>V</code>
	 * @return <code>boolean</code>
	 */
	public boolean removeVertex(V value) {

		return true;
	}

	/**
	 * M&eacute;todo para elimiar un arista.
	 * 
	 * @param source
	 *            <code>V</code>
	 * @param dest
	 *            <code>V</code>
	 * @param weight
	 *            <code>E</code>
	 * @return <code>boolean</code>
	 */
	public boolean removeEdge(V source, V dest, E weight) {

		return true;
	}

	/**
	 * M&eacute;todo para limpiar al grafo.
	 */
	public void clear() {

		vertices.clear();
		edges.clear();
	}

	/**
	 * M&eacute;todo que indica si el grafo est&aacute; vac&iacute;o.
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isEmpty() {

		return vertices.isEmpty();
	}

	/**
	 * M&eacute;todo para imprimir el grafo en consola.
	 */
	public void print() {

		for (int v = 0; v < getNumVertices(); v++) {

			Vertex<V> temp = vertices.get(v);

			System.out.print("nodo#" + v + ".valor(" + temp.getValue() + ")"
					+ ": ");

			for (int e = 0; e < temp.getNumNeighbors(); e++) {

				Vertex<V> temp2 = temp.getNeighborAt(e);

				System.out.print("nodo" + ".valor(" + temp2.getValue() + ")"
						+ " ");
			}

			System.out.println();
		}
	}
}
