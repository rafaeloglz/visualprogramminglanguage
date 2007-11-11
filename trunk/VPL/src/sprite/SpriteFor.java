
package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteFor extends Sprite {

	public SpriteFor(int x, int y, int height, int width){
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.FOR_IMAGE);
	}
	
	public SpriteFor() {
		this.loadImageFile(SpriteConfig.FOR_IMAGE);
		calcValues();
	}

	public void attachConnectors(){

		Sprite s = new Square(0, 0, 9, 14, Color.magenta);
		attach(25, 0, s);
		
		s = new Square(0, 0, 9, 14, Color.magenta);
		attach(67, 2, s);
		
		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(1, 2, s);
		
		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(37, 0, s);
		
		s = new Circle(0, 0, 10, 10);
		attach(1, 44, s);

		s = new Circle(0, 0, 10, 10);
		attach(67, 44, s);
	}
}
