package dataio;

import junit.framework.TestCase;

public class CodeWriterTest extends TestCase {
	
	CodeWriter cw;
	String path;
	String fileName;
	String code;

	public CodeWriterTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		path = System.getProperty("user.dir")+"/tests/CodeWriter/";
		fileName = "test.txt";
		code = "code";
		
	}

	public void testWrite() {
		int numWrites = 10;
		boolean expected = true;
		
		for(int i = 0; i < numWrites; i++){
			cw = new CodeWriter(code, path, fileName + i);
			assertEquals(expected,cw.write());		
		}
			
	}

}
