package graph;

import junit.framework.TestCase;

public class LoadAndSaveTest extends TestCase {

	LoadAndSave<Object> s;
	
	public LoadAndSaveTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		s = new LoadAndSave<Object>();
	}

	public void testSave() {
		s.save(null, null, null);
		//Other cases tested in SaveTest
	}

	public void testLoad() {
		s.load(null, null);	
		//Other cases tested in LoadTest
	}

}
