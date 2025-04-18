package praticajava;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background {
	private BufferedImage[] background;
	private BufferedImage[] portrait;
			
	private int index = 0;
	private int frames = 0;
	private int maxframes = 20;
	
	public int pfpindex = 0;
	
	public Background(int x, int y, int width, int height, BufferedImage sprite) {
		background = new BufferedImage[4];
		background[0] = Main.backgroundspritesheet.getSprite(0, 0, 600, 400);
		background[1] = Main.backgroundspritesheet.getSprite(600*1, 0, 600, 400);
		background[2] = Main.backgroundspritesheet.getSprite(600*2, 0, 600, 400);
		background[3] = Main.backgroundspritesheet.getSprite(600*3, 0, 600, 400);
		
		portrait = new BufferedImage[20];
		for(int i = 0; i < 10; i++)
		portrait[i]	= Main.spritesheetpfp.getSprite(i*100, 0, 100, 100);
		for(int i = 0; i < 10; i++)
		portrait[10+i]	= Main.spritesheetpfp.getSprite(i*100, 100, 100, 100);
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
		g.drawImage(portrait[pfpindex],10,10-(int)Main.cameray,null);
		
		g.setColor(Color.WHITE);
		g.fillRoundRect(115,(int)(10-Main.cameray),900,100,20,10);
		
		g.setFont(new Font("FOT-NewRodin Pro", Font.BOLD,30));
		//g.setColor(Color.WHITE);
		//g.drawString("Progresso", 120,(int)(52-Main.cameray));
		g.setColor(Color.getHSBColor((float)(222)/360,(float)0.65,(float)0.8));
		g.drawString("Progressão", 142,(int)(70-Main.cameray));
	}
}
