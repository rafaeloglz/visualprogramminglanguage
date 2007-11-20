package graph;

import java.util.ArrayList;

import struct.StructV;
import junit.framework.TestCase;

public class CodeMakerTest extends TestCase {

	CodeMaker cm;
	
	public CodeMakerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ArrayList<Graph> graphs =  (ArrayList<Graph>) (new LoadAndSave()).load("", "vpl.txt");
		cm = new CodeMaker(graphs,new ArrayList<String>());
	}

	public void testMake() {
		for (int i = 0; i < 100; i++)
			assertEquals(true, cm.make());
	}

	public void testWriteToFile() {
		String path = System.getProperty("user.dir")+"/tests/CodeMaker/";
		String filename = "Project.java";
		
		cm.make();
		cm.writeToFile(path, filename);
	}

	public void testRecurse() {
		cm.recurse(null);
		cm.recurse(new Vertex<StructV>(null));
	}

}
