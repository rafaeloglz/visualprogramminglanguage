package sprite;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Sprite{
	
	public Circle(int x, int y, int height, int width){
		super(x, y, width, height);
	}
	
	public void paintSprite(Graphics g){
		//g.fillOval(x, y, width, height);
	}
	
	protected void paintSprite(Graphics g, int x, int y) {
		setX(x);
		setY(y);
		
		/*
		g.setColor(Color.white); 
		g.drawOval(this.x, this.y, width, height);
		g.setColor(Color.black);
		*/
	}
	
	/**
  	 * M&eacutetodo para crear un clon del objeto y sus atributos.
  	 * 
  	 * @return		<code>Circle</code>
	 * @throws CloneNotSupportedException 
  	 */

	@Override
	public Sprite clone() {
		return new Circle(this.x, this.y, this.height, this.width);
	}	
}
