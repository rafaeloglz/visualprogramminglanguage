
package sprite;

import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteSemaphore extends Sprite {

	public SpriteSemaphore(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.SEMAPHORE_IMAGE);
	}
	
	public SpriteSemaphore() {
		this.loadImageFile(SpriteConfig.SEMAPHORE_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
		
		Sprite s = new Circle(0, 0, 8, 44);
		attach(2, 6, s);

		//s = new Circle(0, 0, 8, 44);
		//attach(60, 6, s);
	}
}
