import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject{
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 50;
		if (needImage) {
		    loadImage ("rocket.png");
		}
		// TODO Auto-generated constructor stub
	}
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
        
	}
	void update() {
		super.update();
	}
	public void right() {
        x+=speed;
    }
	public void left() {
        x-=speed;
    }
	public void up() {
        y-=speed;
    }
	public void down() {
        y+=speed;
    }
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y, 10, 10);
} 
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}

}
