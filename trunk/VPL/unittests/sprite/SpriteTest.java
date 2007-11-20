package sprite;

import junit.framework.TestCase;

public class SpriteTest extends TestCase {

	Sprite s = new Sprite(){};
	
	public SpriteTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testIntersects() {
		assertEquals(false, s.intersects(null));
	}

	public void testClone() {
		try {
			s.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
