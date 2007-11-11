
package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteSync extends Sprite {

	public SpriteSync(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.SYNC_IMAGE);
	}
	
	public SpriteSync() {
		this.loadImageFile(SpriteConfig.SYNC_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
		
		Sprite s = new Square(0, 0, 7, 40, Color.cyan);
		attach(2, 2, s);

		s = new Square(0, 0, 9, 14, Color.magenta);
		attach(66, 2, s);

		s = new Circle(0, 0, 10, 10);
		attach(2, 42, s);		
	}
}
