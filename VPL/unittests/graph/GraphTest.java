package graph;

import java.util.Hashtable;
import sprite.Sprite;
import sprite.SpriteBegin;
import struct.StructE;
import struct.StructV;
import junit.framework.TestCase;

public class GraphTest extends TestCase {

	Graph g;

	public GraphTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		g = new Graph();
	}

	public void testGetHead() {
		Vertex<StructV<Hashtable<String, Object>>> expected = null;
		assertEquals(expected, g.getHead());				
	}

	public void testGetNumEdges() {

		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;
		StructV<Hashtable<String, Object>> tempSt = null;
		int vertexNum = 0;

		int expected = 0;
		assertEquals(expected, g.getNumEdges());

		vertexNum = 1;
		expected = 0;
		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);
			g.addVertex(st);
		}

		g.addEdge(null, null, null, 1);
		assertEquals(expected, g.getNumEdges());

		vertexNum = 100;
		expected = vertexNum -1;
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
		assertEquals(expected, g.getNumEdges());
	}

	public void testGetNumVertices() {
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;		
		int vertexNum = 0;

		vertexNum = 100;
		int expected = vertexNum;

		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);			
			g.addVertex(st);						
		}
		assertEquals(expected, g.getNumVertices());
	}

	public void testGetVertices() {
	}

	public void testGetEdges() {
	}

	public void testSetHead() {
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;
		int vertexNum = 0;

		vertexNum = 100;
		Vertex<StructV<Hashtable<String, Object>>> expected = null;

		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);			
			expected = g.getVertex(st);			
			g.setHead(st);
			assertEquals(expected, g.getHead());			
		}
	}

	public void testAddVertex() {
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;		
		int vertexNum = 0;

		vertexNum = 100;
		boolean expected = true;

		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);			
			assertEquals(expected, g.addVertex(st));			
		}		
	}

	public void testAddEdge() {
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;		
		int vertexNum = 0;
		StructV<Hashtable<String, Object>> tempSt = null;

		vertexNum = 100;
		boolean expected = true;

		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);			
			g.addVertex(st);
			if (i >= 1)			
				assertEquals(expected, g.addEdge(tempSt, st, null, 1));
			tempSt = st;
		}		
	}

	public void testGetVertex() {
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;		
		int vertexNum = 0;

		vertexNum = 100;

		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);
			g.addVertex(st);
			Vertex<StructV<Hashtable<String, Object>>> expected = new Vertex<StructV<Hashtable<String, Object>>>(st);
			assertEquals(true, g.getVertex(st).equals(expected));			
		}
	}

	public void testGetVertexAt() {
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;		
		int vertexNum = 0;

		vertexNum = 100;
		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);
			g.addVertex(st);
			Vertex<StructV<Hashtable<String, Object>>> expected = new Vertex<StructV<Hashtable<String, Object>>>(st);
			assertEquals(true, g.getVertexAt(i).equals(expected));			
		}
	}

	public void testGetEdge() {
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;		
		int vertexNum = 0;
		StructV<Hashtable<String, Object>> tempSt = null;
		Edge tempEdge;

		vertexNum = 100;
		boolean expected = true;

		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);			
			g.addVertex(st);
			if (i >= 1){
				Vertex<StructV<Hashtable<String, Object>>> source = new Vertex<StructV<Hashtable<String, Object>>>(tempSt);
				Vertex<StructV<Hashtable<String, Object>>> dest = new Vertex<StructV<Hashtable<String, Object>>>(st);				
				StructE stE = new StructE( null, null);
				tempEdge = new Edge(source, dest, stE);
				g.addEdge(tempSt, st, stE, 1);
				assertEquals(true, g.getEdge(tempSt, st, stE).equals(tempEdge));
			}			
			tempSt = st;
		}
	}

	public void testGetEdgeAt() {
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;		
		int vertexNum = 0;
		StructV<Hashtable<String, Object>> tempSt = null;
		Edge tempEdge;

		vertexNum = 100;
		boolean expected = true;

		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);			
			g.addVertex(st);
			if (i >= 1){
				Vertex<StructV<Hashtable<String, Object>>> source = new Vertex<StructV<Hashtable<String, Object>>>(tempSt);
				Vertex<StructV<Hashtable<String, Object>>> dest = new Vertex<StructV<Hashtable<String, Object>>>(st);				
				StructE stE = new StructE( null, null);
				tempEdge = new Edge(source, dest, stE);
				g.addEdge(tempSt, st, stE, 1);
				assertEquals(true, g.getEdgeAt(i-1).equals(tempEdge));
			}			
			tempSt = st;
		}
	}

	public void testRemoveVertex() {
	}

	public void testRemoveEdge() {		
	}

	public void testClear() {
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;		
		int vertexNum = 0;

		boolean expected = true;
		g.clear();
		assertEquals(expected, g.isEmpty());

		vertexNum = 100;
		expected = true;
		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);
			g.addVertex(st);
		}

		g.clear();
		assertEquals(expected, g.isEmpty());	
	}

	public void testIsEmpty() {
		int vertexNum = 0;
		Sprite s;
		Hashtable<String, Object> h;
		StructV<Hashtable<String, Object>> st = null;
		boolean expected = true;
		assertEquals(expected, g.isEmpty());

		vertexNum = 100;
		expected = false;		
		for(int i = 0; i < vertexNum; i++){
			s = new SpriteBegin();
			h = new Hashtable<String, Object>();
			h.put("test", "test");		
			st = new StructV<Hashtable<String, Object>>(s, h);
			g.addVertex(st);
		}
		assertEquals(expected, g.isEmpty());		
	}
}
