package graph;

import java.util.Hashtable;

import sprite.Sprite;
import sprite.SpriteBegin;
import struct.StructV;
import junit.framework.TestCase;

public class EdgeTest extends TestCase {

	Sprite s;
	Hashtable<String, Object> h;
	Graph g = new Graph();
	StructV<Hashtable<String, Object>> st = null;
	StructV<Hashtable<String, Object>> tempSt = null;
	int vertexNum = 100;
	
	public EdgeTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);			
			g.addVertex(st);
			if (i >= 1)
				g.addEdge(tempSt, st, null, 1);

			tempSt = st;
		}
	}

	public void testEqualsEdgeOfVE() {
		for (int i = 0; i < vertexNum; i++){
			assertEquals(false, g.getVertexAt(i).equals(null));
			assertEquals(true,g.getVertexAt(i).equals(g.getVertexAt(i)));
		}
	}

}
