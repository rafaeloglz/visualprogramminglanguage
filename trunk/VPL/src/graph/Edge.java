/**
 * Clase que implementa a las aristas del grafo.
 * 
 * @author Andr&eacute;s Freyr&iacute;a Cedeno
 * @author Rafael Ochoa Gonz&aacute;lez
 * @author Ulises Figueroa Ram&iacute;rez
 * @author Jos&eacute; Roberto Ram&iacute;rez Aguilar
 * @author Juan Francisco Navarro Mariscal
 */

package graph;

import struct.*;
import java.io.Serializable;

public class Edge<V, E> implements Serializable {

	private Vertex<V> source;
	private Vertex<V> dest;
	private E weight;

	/**
	 * Constructor por omisi&oacute;n.
	 * 
	 * @param source
	 *            <code>Vertex<V></code>
	 * @param dest
	 *            <code>Vertex<V></code>
	 * @param weight
	 *            <code>E</code>
	 */
	public Edge(Vertex<V> source, Vertex<V> dest, E weight) {

		setSource(source);
		setDest(dest);
		setWeight(weight);
	}

	/**
	 * M&eacute;todo para especificar el vertice origen.
	 * 
	 * @param source
	 *            <code>Vertex<V></code>
	 */
	public void setSource(Vertex<V> source) {

		this.source = source;
	}

	/**
	 * M&eacute;todo para especificar el vertice final.
	 * 
	 * @param dest
	 *            <code>Vertex<V></code>
	 */
	public void setDest(Vertex<V> dest) {

		this.dest = dest;
	}

	/**
	 * M&eacute;todo para especificar el peso.
	 * 
	 * @param weight
	 *            <code>E</code>
	 */
	public void setWeight(E weight) {

		this.weight = weight;
	}

	/**
	 * M&eacute;todo para obtener el vertice origen.
	 * 
	 * @return <code>Vertex<V></code>
	 */
	public Vertex<V> getSource() {

		return source;
	}

	/**
	 * M&eacute;todo para obtener el vertice final.
	 * 
	 * @return <code>Vertex<V></code>
	 */
	public Vertex<V> getDest() {

		return dest;
	}

	/**
	 * M&eacute;todo para obtener el peso.
	 * 
	 * @return <code>E</code>
	 */
	public E getWeight() {

		return weight;
	}

	/**
	 * M&eacute;todo para realizar comparaciones entre aristas.
	 * 
	 * @param e
	 *            <code>Edge<V, E></code>
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean equals(Edge<V, E> e) {

		StructE st = (StructE) weight;

		return this.source.equals(e.getSource())
				&& this.dest.equals(e.getDest())
				&& this.weight.equals(e.getWeight());
	}
}
