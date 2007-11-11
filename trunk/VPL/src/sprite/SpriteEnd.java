
package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteEnd extends Sprite {

	public SpriteEnd(int x, int y, int height, int width){
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.END_IMAGE);
	}
	
	public SpriteEnd() {
		this.loadImageFile(SpriteConfig.END_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
		
		Sprite s = new Square(0, 0, 0, 0, Color.black);
		attach(0, 0, s);
		
		s = new Square(0, 0, 0, 0, Color.black);
		attach(0, 0, s);
		
		s = new Square(0, 0, 11, 11, Color.cyan);
		attach(1, 34, s);		
	}
}
