package praticajava;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Skill {
	

	private BufferedImage[] background;
	
	
	
	public int x,y,bgindex = 0,maxbgindex = 8;
	public String Skill;

	public Skill(int x, int y, String skill, int level) {
		LevelCircle lv = new LevelCircle(x,y,skill,level);
		Main.levelscircles.add(lv);
		
		background = new BufferedImage[9];
		for(int i = 0; i <= maxbgindex; i++) 
		{
			background[i] = Main.spritesheet.getSprite(0, 100+i*35, 500, 35);
		}
		
		
		for(int i = 1; i <= 40; i++) {
			Cell celltest = new Cell(x+39+11*(i-1),y+2,skill,i);
			Main.cells.add(celltest);
		}
		
		Button changebg = new Button(x+39,y+32,16,16,"ChangeBackgroundPlus",skill);
		Main.buttons.add(changebg);
		Button changebg2 = new Button(x+39+16,y+32,16,16,"ChangeBackgroundMinus",skill);
		Main.buttons.add(changebg2);
		Button delete = new Button(x-20,y+10,16,16,"DeleteSkill",skill);
		Main.buttons.add(delete);
		
		this.x = x;
		this.y = y;
		this.Skill = skill;
	}
	
	public String getSkill() {
		return Skill;
	}
	
	public void deleteSkill() {
		for(int i = 0; i < Main.buttons.size(); i++) {
			if( Main.buttons.get(i).getSkill() == Skill )
				{
				Main.buttons.get(i).deleted = true;
				}
				
		}
		for(int i = 0; i < Main.cells.size(); i++) {
			if( Main.cells.get(i).getSkill() == Skill )
				{
				Main.cells.get(i).deleted = true;
				}
				
		}
		for(int i = 0; i < Main.levelscircles.size(); i++) {
			if( Main.levelscircles.get(i).getSkill() == Skill )
				{
				Main.levelscircles.remove(Main.levelscircles.get(i));
				}
				
		}
		Main.skills.remove(this);
	}
	
	public void changeBackground(int change) {
		bgindex+=change;
		if(bgindex < 0)
		bgindex = maxbgindex;
		if(bgindex > maxbgindex)
		bgindex = 0;
	}
	
	public void render(Graphics g) {
		g.drawImage(background[bgindex],x,y-(int)Main.cameray,null);
	
		//g.setFont(new Font("arial", Font.BOLD,20));
		g.setFont(new Font("Arial", Font.BOLD,15));
		g.setColor(Color.WHITE);
		g.drawString(""+Skill, x+30,y-(int)Main.cameray);
		g.setColor(Color.BLACK);
		g.drawString(""+Skill, x+31,y-(int)Main.cameray);
	
	}
}
