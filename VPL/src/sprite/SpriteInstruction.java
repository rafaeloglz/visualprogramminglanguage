
package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteInstruction extends Sprite {

	public SpriteInstruction(int x, int y, int height, int width){
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.INSTRUCTION_IMAGE);
	}
	
	public SpriteInstruction() {
		this.loadImageFile(SpriteConfig.INSTRUCTION_IMAGE);
		calcValues();
	}

	public void attachConnectors(){
		
		Sprite s = new Square(0, 0, 9, 14, Color.magenta);
		attach(73, 7, s);		
		
		s = new Circle(0, 0, 9, 9);
		attach(2, 43, s);

		s = new Circle(0, 0, 9, 9);
		attach(70, 43, s);
		
		s = new Square(0, 0, 9, 14, Color.cyan);
		attach(2, 7, s);
	}
}
