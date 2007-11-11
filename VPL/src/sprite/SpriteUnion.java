
package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteUnion extends Sprite {

	public SpriteUnion(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.UNION_IMAGE);
	}
	
	public SpriteUnion() {
		this.loadImageFile(SpriteConfig.UNION_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
				
		Sprite s = new Square(0, 0, 9, 14, Color.magenta);
		attach(68, 9, s);		
		
		s = new Circle(0, 0, 9, 9);
		attach(1, 46, s);

		s = new Circle(0, 0, 9, 9);
		attach(69, 45, s);
		
		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(1, 9, s);

		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(1, 29, s);

	}
}
