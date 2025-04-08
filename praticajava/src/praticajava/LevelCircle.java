package praticajava;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class LevelCircle {
	private BufferedImage Sprite;
	
	public String skill = "Default";
	public int level = 0;
	
	public int x,y,width = 36,height = 35;
	
	public Skill parent;
	
	public LevelCircle(int x, int y, String skill, int level, Skill parent) {
		
		Sprite = Main.spritesheet.getSprite(36*1, 0, 36*1, 35*1);
		
		this.skill = skill;
		this.level = level;
		this.x = x;
		this.y = y;
		
		this.parent = parent;
	}
	
	public void tick() {
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y+parent.getArrayPosition()*70;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getLevel() {
		return level;
	}
	
	public String getSkill() {
		return skill;
	}
	
	public void updateLevel() {
		level +=1;
		parent.setLevel(level);
	}
	
	public void changeLevel(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1)
				level += 1;
			if(e.getButton() == MouseEvent.BUTTON3)
				level -= 1;
			
			parent.setLevel(level);
	}

	public void render(Graphics g) {
		g.drawImage(Sprite,x,y+parent.getArrayPosition()*70-(int)Main.cameray,null);
		g.setFont(new Font("arial", Font.BOLD,25));
		g.setColor(Color.WHITE);
		if(level < 10) {
			g.drawString(""+level, x+10,y+parent.getArrayPosition()*70+27-(int)Main.cameray);
		}else {
			g.drawString(""+level, x+4,y+parent.getArrayPosition()*70+27-(int)Main.cameray);
		}
		g.setColor(Color.BLACK);
		if(level < 10) {
			g.drawString(""+level, x+12,y+parent.getArrayPosition()*70+26-(int)Main.cameray);
		}else {
			g.drawString(""+level, x+5,y+parent.getArrayPosition()*70+26-(int)Main.cameray);
		}
	}
}
