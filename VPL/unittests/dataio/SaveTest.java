package dataio;

import graph.Graph;
import junit.framework.TestCase;

public class SaveTest extends TestCase {

	private Save out;
	private String path;
	private String fileName;

	public SaveTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		path = System.getProperty("user.dir")+"/tests/Save/";
		fileName = "test.txt";
	}

	public void testSaveObj() {
		int numSaves = 100;
		boolean expected = true;

		for(int i = 0; i < numSaves; i++){
			out = new Save(new Graph(), path, fileName + i);
			assertEquals(expected, out.saveObj());	
		}
	}

}
