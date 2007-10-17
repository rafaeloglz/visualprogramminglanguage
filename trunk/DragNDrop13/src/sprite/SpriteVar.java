
package sprite;

import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteVar extends Sprite {

	public SpriteVar(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.VAR_IMAGE);
	}

	public SpriteVar() {
		this.loadImageFile(SpriteConfig.VAR_IMAGE);
		calcValues();
	}
}
