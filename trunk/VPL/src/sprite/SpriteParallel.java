
package sprite;

import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteParallel extends Sprite {

	public SpriteParallel(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.PARALLEL_IMAGE);
	}
	
	public SpriteParallel() {
		this.loadImageFile(SpriteConfig.PARALLEL_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
		
		Sprite s = new Square(0, 0, 9, 14);
		attach(1, 2, s);

		s = new Square(0, 0, 6, 42);
		attach(66, 2, s);

		s = new Circle(0, 0, 9, 9);
		attach(65, 46, s);
	}
}
