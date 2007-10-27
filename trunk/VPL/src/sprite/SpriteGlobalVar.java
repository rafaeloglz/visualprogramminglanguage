package sprite;

public class SpriteGlobalVar extends Sprite {
	
	public SpriteGlobalVar(int x, int y, int height, int width){
		
		super(x, y, height, width);
		this.loadImageFile(SpriteConfig.GLOBALVAR_IMAGE);
	}

	public SpriteGlobalVar() {
		this.loadImageFile(SpriteConfig.GLOBALVAR_IMAGE);
		calcValues();
	}
}
