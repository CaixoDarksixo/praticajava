package praticajava;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Skill {
	

	private BufferedImage background;
	
	public int x,y;
	public String Skill;

	public Skill(int x, int y, String skill, int level) {
		LevelCircle lv = new LevelCircle(x,y,skill,level);
		Main.levelscircles.add(lv);
		
		background = Main.spritesheet.getSprite(0, 100, 500, 35);
		
		for(int i = 1; i <= 40; i++) {
			Cell celltest = new Cell(x+39+11*(i-1),y+2,skill,i);
			Main.cells.add(celltest);
		}
		
		this.x = x;
		this.y = y;
		this.Skill = skill;
	}
	
	public void render(Graphics g) {
		g.drawImage(background,x,y,null);
	
		//g.setFont(new Font("arial", Font.BOLD,20));
		g.setFont(new Font("Arial", Font.BOLD,15));
		g.setColor(Color.WHITE);
		g.drawString(""+Skill, x+30,y);
		g.setColor(Color.BLACK);
		g.drawString(""+Skill, x+31,y);
	
	}
}
