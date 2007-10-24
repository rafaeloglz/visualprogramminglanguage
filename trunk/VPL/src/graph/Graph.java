package graph;
import struct.*;

/**
 * Clase que implementa al grafo.
 * 
 * @author Andr&eacute Freyr&iacutea Cedeño
 * @author Rafael Ochoa Gonzalez
 * @author Ulises Figuero Ram&iacuterez
 * @author Jos&eacute Roberto Ram&iacuterez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */


import java.io.Serializable;
import java.util.ArrayList;
import struct.*;

public class Graph<V, E> implements Serializable {
	
	private ArrayList<Vertex<V>> vertices;
	private ArrayList<Edge<V, E>> edges;
	private Vertex<V> head;
	private ArrayList<Vertex<V>> tail;
	
	/**
	 * Constructor por omisi&oacuten.
	 */
	public Graph() {
		
		vertices = new ArrayList<Vertex<V>>();
		edges = new ArrayList<Edge<V, E>>();
		head = null;
	}
	
	/**
	 * M&eacutetodo para obtener el inicio del grafo.
	 * 
	 * @return		<code>Vertex<V></code>
	 */
	public Vertex<V> getHead() {
		
		return head;
	}
	
	/**
	 * M&eacutetodo para obtener el n&uacutemero de aristas.
	 * 
	 * @return		<code>int</code>
	 */
	public int getNumEdges() {
		
		return edges.size();
	}
	
	/**
	 * M&eacutetodo para obtener el n&uacutemero de v&eacutertices.
	 * 
	 * @return		<code>int</code>
	 */
	public int getNumVertices() {
	
		return vertices.size();
	}
	
	/**
	 * M&eacutetodo para obtener la lista de v&eacutertices.
	 * 
	 * @return		<code>ArrayList<Node></code>
	 */
	public ArrayList<Vertex<V>> getVertices() {
		
		return vertices;
	}
	
	/**
	 * M&eacutetodo para obtener la lista de aristas.
	 * 
	 * @return		<code>ArrayList<Edge></code>
	 */
	public ArrayList<Edge<V, E>> getEdges() {
		
		return edges;
	}
	
	/**
	 * M&eacutetodo para especificar el inicio del grafo.
	 * 
	 * @param head		<code>Vertex<V></code>
	 */
	public void setHead(V value) {
				
		head = this.getVertex(value);		
	}
	
	/**
	 * M&eacutetodo para agregar un v&eacutertice.
	 * 
	 * @param value		<code>V</code>
	 */
	public boolean addVertex(V value) {
		
		Vertex<V> v = new Vertex(value);
		
		if(getVertex(value) == null)
			if(vertices.add(v)) 
				return true;
		
		return false;
	}
	
	/**
	 * M&eacutetodo para agregar una arista.
	 * 
	 * @param source			<code>V</code>
	 * @param dest				<code>V</code>
	 * @param weight			<code>E</code>
	 */
	public boolean addEdge(V source, V dest, E weight) {
		
		//System.out.println("addEdge");
		
		Vertex<V> s = getVertex(source);
		Vertex<V> d = getVertex(dest);
		
		if (s != null & d !=null){
			
			Edge<V, E> e = new Edge(s, d, weight);
			if(getEdge(source, dest, weight) == null)
				if(edges.add(e) && s.addNeighbor(d))
					return true;
		}
		
		return false;
	}
	
	/**
	 * M&eacutetodo para obtener un v&eacutertice por valor.
	 * 
	 * @param value		<code>V</code>
	 * @return			<code>Vertex<V></code>
	 */
	public Vertex<V> getVertex(V value) {
		
		Vertex<V> temp = new Vertex(value);	
		
		for(int i = 0; i < getNumVertices(); i++)
			if(vertices.get(i).equals(temp))				
				return vertices.get(i);		
		
		return null;
	}
	
	/**
	 * M&eacutetodo para obtener un v&eacutertice por &iacutendice.
	 * 
	 * @param index		<code>int</code>
	 * @return			<code>Vertex<V></code>
	 */
	public Vertex<V> getVertexAt(int index) {
		
		return vertices.get(index);
	}
	
	/**
	 * M&eacutetodo para obtener una arista por valor.
	 * 
	 * @param value		<code>V</code>
	 * @return			<code>Vertex<V></code>
	 */
	public Edge<V, E> getEdge(V source, V dest, E weight) {
		
		Vertex<V> s = new Vertex(source);
		Vertex<V> d = new Vertex(dest);
		Edge<V, E> temp = new Edge(s, d, weight);
		
		for(int i = 0; i < getNumEdges(); i++)
			if(edges.get(i).equals(temp))
				return edges.get(i);		
		
		return null;
	}
	
	/**
	 * M&eacutetodo para obtener una arista por &iacutendice.
	 * 
	 * @param value		<code>V</code>
	 * @return			<code>Vertex<V></code>
	 */
	public Edge<V, E> getEdgeAt(int index) {
		
		return edges.get(index);
	}
	
	/**
	 * M&eacutetodo para elimiar un vertice.
	 * 
	 * @param value		<code>V</code>
	 * @return			<code>boolean</code>
	 */
	public boolean removeVertex(V value) {
	
	
		return true;
	}
	
	/**
	 * M&eacutetodo para elimiar un arista.
	 * 
	 * @param source		<code>V</code>
	 * @param dest			<code>V</code>
	 * @param weight		<code>E</code>
	 * @return				<code>boolean</code>
	 */
	public boolean removeEdge(V source, V dest, E weight) {

		
		return true;	
	}
	
	/**
	 * M&eacutetodo para limpiar al grafo.
	 */
	public void clear() {
		
		vertices.clear();
		edges.clear();
	}
	
	/**
	 * M&eacutetodo que indica si el grafo est&aacute vac&iacuteo.
	 * 
	 * @return		<code>boolean</code>
	 */
	public boolean isEmpty() {
		
		return vertices.isEmpty();
	}
	
	/**
	 * M&eacutetodo para imprimir el grafo en consola.
	 */
	public void print() {
		
		for(int v = 0; v < getNumVertices(); v++) {
			
			Vertex<V> temp = vertices.get(v);
			
			System.out.print("nodo#" + v + ".valor(" + temp.getValue () + ")" + ": ");
			
			for (int e = 0; e < temp.getNumNeighbors(); e++){
				
				Vertex<V> temp2 = temp.getNeighborAt(e);
				
				System.out.print("nodo" + ".valor(" + temp2.getValue () + ")" + " ");
			}
			
			System.out.println ();
		}
	}
}
