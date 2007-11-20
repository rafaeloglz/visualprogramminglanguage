package graph;

import junit.framework.TestCase;

public class VertexTest extends TestCase {

	Vertex<?> v = new Vertex(null);
	int numNeighbors = 100;
	
	public VertexTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testAddNeighbor() {
		assertEquals(false,v.addNeighbor(null));
		assertEquals(true,v.addNeighborAt(0, null));
		
		for (int i = 0; i < numNeighbors; i++){
			if (i == 0) assertEquals(true,v.addNeighbor(new Vertex(null)));
			else assertEquals(false,v.addNeighbor(new Vertex(null)));
			assertEquals(true,v.addNeighborAt(i,new Vertex(null)));
		}
	}

	public void testGetNeighbor() {
		testAddNeighbor();
		for (int i = 0; i < numNeighbors; i++){
			assertNotNull(v.getNeighbor(null));
			assertNotNull(v.getNeighborAt(i));
		}
	}

	public void testRemoveNeighbor() {
		testAddNeighbor();
		for (int i = 0; i < numNeighbors; i++){
			assertNotNull(v.removeNeighbor(null));
			if (i < numNeighbors/3)
				assertNotNull(v.removeNeighbor(i));
		}
	}

	public void testGetNumNeighbors() {
		
		for (int i = 0; i < numNeighbors; i++){
			if (i == 0) assertEquals(true,v.addNeighbor(new Vertex(null)));
			else assertEquals(false,v.addNeighbor(new Vertex(null)));
			assertEquals(true,v.addNeighborAt(i,new Vertex(null)));
			
			assertEquals(i+1,v.getNumNeighbors());
		}
	}

	public void testEqualsVertexOfV() {
//		fail("Not yet implemented");
	}

	public void testGetNeighbors() {
//		fail("Not yet implemented");
	}

}
