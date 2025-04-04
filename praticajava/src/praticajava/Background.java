package praticajava;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background {
	private BufferedImage[] background;
	private BufferedImage portrait;
			
	private int index = 0;
	private int frames = 0;
	private int maxframes = 20;
	
	
	
	public Background(int x, int y, int width, int height, BufferedImage sprite) {
		background = new BufferedImage[4];
		background[0] = Main.backgroundspritesheet.getSprite(0, 0, 600, 400);
		background[1] = Main.backgroundspritesheet.getSprite(600*1, 0, 600, 400);
		background[2] = Main.backgroundspritesheet.getSprite(600*2, 0, 600, 400);
		background[3] = Main.backgroundspritesheet.getSprite(600*3, 0, 600, 400);
		
		portrait = Main.spritesheet.getSprite(72, 0, 100, 100);
	}
	
	public void tick() {
		frames++;
		if(frames >= maxframes) {
			if(index < 3)
			index++;
			else
			index = 0;
			
			frames = 0;
		}
		
	}

	public void render(Graphics g) {
		g.drawImage(background[index],0,0,Main.WIDTH,Main.HEIGHT,null);
		g.drawImage(portrait,10,10-(int)Main.cameray,null);
		g.setFont(new Font("Pixeloid Mono", Font.BOLD,25));
		g.setColor(Color.WHITE);
		g.drawString("Progressão de Personagem: João Pedro Nossol", 120,(int)(52-Main.cameray));
		g.setColor(Color.BLACK);
		g.drawString("Progressão de Personagem: João Pedro Nossol", 122,(int)(50-Main.cameray));
	}
}
