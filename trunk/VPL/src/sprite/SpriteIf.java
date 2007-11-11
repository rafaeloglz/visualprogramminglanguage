
package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteIf extends Sprite {

	public SpriteIf(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.IF_IMAGE);
	}
	
	public SpriteIf() {
		this.loadImageFile(SpriteConfig.IF_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
		
		Sprite s = new Square(0, 0, 9, 14, Color.magenta);
		attach(72, 9, s);

		s = new Square(0, 0, 9, 14, Color.magenta);
		attach(72, 27, s);

		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(2, 9, s);

		s = new Circle(0, 0, 9, 9);
		attach(3, 47, s);

		s = new Circle(0, 0, 9, 9);
		attach(72, 47, s);
	}
}
