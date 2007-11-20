package dataio;

import graph.Graph;
import graph.Vertex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;

import sprite.Sprite;
import sprite.SpriteBegin;
import struct.StructV;

import junit.framework.TestCase;

public class LoadTest extends TestCase {

	private Load<Graph> l;
	private Object obj;
	private ObjectInputStream in;
	private String path;
	private String fileName;
	private Graph g;
	private Save<Graph> out;

	public LoadTest(String name) {
		super(name);		
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		path = System.getProperty("user.dir")+"/tests/Load/";
		fileName = "vpl.txt";
	}

	public void testLoadObj() {
		boolean expected = true;

		try {
			in = new ObjectInputStream(new FileInputStream(new File(path+ fileName)));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int numSavesLoads = 100;

		out = new Save(null,null,null);
			assertEquals(false, out.saveObj());
		
		for(int i = 0; i < numSavesLoads; i++){
			Graph g = new Graph();
			out = new Save(g, path, fileName + i);
			out.saveObj();
			l = new Load(path, fileName + i);
			assertNotNull(l.loadObj());				
		}	
	}
}

