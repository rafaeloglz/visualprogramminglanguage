
package sprite;

import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteFunction extends Sprite {

	public SpriteFunction(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.FUNCTION_IMAGE);
	}
	
	public SpriteFunction() {
		this.loadImageFile(SpriteConfig.FUNCTION_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
		
		Sprite s = new Square(0, 0, 9, 14);
		attach(72, 7, s);
		
		s = new Square(0, 0, 9, 14);
		attach(2, 7, s);

		s = new Circle(0, 0, 9, 9);
		attach(2, 43, s);

		s = new Circle(0, 0, 9, 9);
		attach(72, 43, s);
	}
}
