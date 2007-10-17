
package sprite;

import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteLock extends Sprite {
	
	public SpriteLock() {
		
		this.loadImageFile(SpriteConfig.LOCK_IMAGE);
		calcValues();
	}
	
	public SpriteLock(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.LOCK_IMAGE);
	}

	public void attachConnectors(){
		
		Sprite s = new Circle(0, 0, 9, 9);
		attach(1, 10, s);

		s = new Circle(0,0, 9, 9);
		attach(60, 10, s);
	}
}
