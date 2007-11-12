/**
 * Clase que implementa a los vertices del grafo.
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
import struct.*;

public class Vertex<V> implements Serializable {

	private V value;
	private ArrayList<Vertex<V>> neighbors;

	/**
	 * Constructor por omisi&oacuten.
	 * 
	 * @param value
	 *            <code>V</code>
	 */
	public Vertex(V value) {
		this.value = value;
		neighbors = new ArrayList();
	}

	/**
	 * M&eacutetodo para agregar un vecino.
	 * 
	 * @param neighbor
	 *            <code>Vertex<V></code>
	 */
	public boolean addNeighbor(Vertex<V> neighbor) {

		if (getNeighbor(neighbor.getValue()) == null)
			if (neighbors.add(neighbor))
				return true;

		return false;
	}

	/**
	 * M&eacutetodo para agregar un vecino en el indice indicado
	 * 
	 * @param neighbor
	 *            <code>Vertex<V></code>
	 * @param index
	 *            <code>Vertex<V></code>
	 */

	public boolean addNeighborAt(int index, Vertex<V> neighbor) {

		for (int i = 0; i < index; i++)
			neighbors.add(null);

		neighbors.add(index, neighbor);
		return true;
	}

	/**
	 * M&eacutetodo para especificar el valor del vertice.
	 * 
	 * @param value
	 *            <code>V</code>
	 */
	public void setValue(V value) {

		this.value = value;
	}

	/**
	 * M&eacutetodo para obtener el valor del vertice.
	 * 
	 * @return <code>V</code>
	 */
	public V getValue() {

		return value;
	}

	/**
	 * M&eacutetodo para obtener la lista de vecinos.
	 * 
	 * @param value
	 *            <code>V</code>
	 * @return <code>ArrayList<Vertex<V>></code>
	 */
	public Vertex<V> getNeighbor(V value) {

		Vertex<V> temp = new Vertex(value);

		for (int i = 0; i < getNumNeighbors(); i++)
			if (neighbors.get(i) != null)
				if (neighbors.get(i).equals(temp))
					return neighbors.get(i);

		return null;
	}

	/**
	 * M&eacutetodo para obtener la lista de vecinos.
	 * 
	 * @param index
	 *            <code>int</code>
	 * @return <code>ArrayList<Vertex<V>></code>
	 */
	public Vertex<V> getNeighborAt(int index) {

		return neighbors.get(index);
	}

	public boolean removeNeighbor(V value) {

		for (int i = 0; i < getNumNeighbors(); i++)
			if (neighbors.get(i).getValue().equals(value)) {

				neighbors.remove(i);
				return true;
			}

		return false;
	}

	/**
	 * M&eacutetodo para obtener el n&uacutemero de vecinos.
	 * 
	 * @return <code>int</code>
	 */
	public int getNumNeighbors() {

		return neighbors.size();
	}

	/**
	 * M&eacutetodo para realizar comparaciones entre vertices.
	 * 
	 * @param v
	 *            <code>Vertex<V></code>
	 * @return <code>boolean</code>
	 */
	public boolean equals(Vertex<V> v) {

		StructV st = (StructV) value;

		return st.equalsTo((StructV) v.getValue());
	}

	/**
	 * M&eacutetodo para obtener a los vecinos del vertice.
	 * 
	 */

	public ArrayList<Vertex<V>> getNeighbors() {
		return neighbors;
	}

	/**
	 * M&eacutetodo para asignar a los vecinos del vertice.
	 * 
	 * @param neighbors
	 *            Vecinos <code>ArrayLis<Vertex<V>></code>
	 */

	public void setNeighbors(ArrayList<Vertex<V>> neighbors) {
		this.neighbors = neighbors;
	}
}
