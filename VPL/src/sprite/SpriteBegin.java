
package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteBegin extends Sprite {

	public SpriteBegin(int x, int y, int height, int width){
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.BEGIN_IMAGE);
	}
	
	public SpriteBegin() {
		this.loadImageFile(SpriteConfig.BEGIN_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
		Sprite s = new Square(0, 0, 11, 11, Color.magenta);
		attach(59, 9, s);		
	}
}
