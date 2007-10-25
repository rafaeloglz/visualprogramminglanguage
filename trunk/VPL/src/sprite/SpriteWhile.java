
package sprite;

import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteWhile extends Sprite {

	public SpriteWhile(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.WHILE_IMAGE);
	}
	
	public SpriteWhile() {
		this.loadImageFile(SpriteConfig.WHILE_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
				
		Sprite s = new Square(0, 0, 9, 14);
		attach(29, 2, s);
		
		s = new Square(0, 0, 9, 14);
		attach(69, 2, s);

		s = new Square(0, 0, 9, 14);
		attach(1, 2, s);
		
		s = new Square(0, 0, 9, 14);
		attach(41, 2, s);
		
		s = new Circle(0, 0, 9, 9);
		attach(1, 44, s);

		s = new Circle(0, 0, 9, 9);
		attach(69, 44, s);
	}
}
