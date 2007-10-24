package graph;

import java.util.ArrayList;

import sprite.SpriteEnd;
import struct.StructV;

public class GraphAlgorithm {
	
	private Graph g;
	private Vertex pivot;
	
	public GraphAlgorithm(Graph g) {		
		this.g = g;		
		pivot = null;
	}

	public int solve() {

		if(g.getHead() != null)
			return solve2(g.getHead(), true);
		else return 0;
	}
	
	public int solve2(Vertex<StructV> v, boolean bool) {
		
		StructV<Integer> st = null;
		int count = v.getNumNeighbors ();
		
		if(isPivot (v) && bool) {
			
			pivot = v;
			return 0;
		}
		else {
			
			switch(count) {
				
				case 0:{
					
					st = v.getValue();
					
					return st.getValue();
				}
				
				case 1: {
					
					st = v.getValue();
					
					return st.getValue() + solve2 (v.getNeighborAt(0), true); 
				}
				case 2:	{
					
					st = v.getValue();
					
					return solve2 (v.getNeighborAt(0), true) * solve2 (v.getNeighborAt(1), true) +
					st.getValue() + solve2 (pivot, false); 
				}
				
				default: break;
			}
		}

		return 0;
	}
	
	/**
	 * M&eacutetodo para determinar si un vertice es pivote.
	 *
	 * @param v			<code>Vertex<V></code>
	 */
	public boolean isPivot (Vertex<StructV> v){
		
		int count = 0;
		ArrayList<Vertex<StructV>> pivots = new ArrayList();
		
		for(int i = 0; i < g.getNumVertices(); i++) {
			
			Vertex<StructV> t = g.getVertexAt(i);
			
			for(int j = 0; j < t.getNumNeighbors(); j++) {
				
				if(v.equals(t.getNeighborAt(j)));
					count++;
			}
				 
		}
		
		return false;
	}
}
