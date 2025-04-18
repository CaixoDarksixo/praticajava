package praticajava;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

public class Skill {
	

	private BufferedImage[] background;
	
	
	
	public int x,y,level,bgindex = 0,maxbgindex = 8;
	public String Skill;
	
	int[] CellIndexes;

	public Skill(String skill, int x, int y, int level, int bgindex, int[] cellindexes) {
		LevelCircle lv = new LevelCircle(x,y,skill,level,this);
		Main.levelscircles.add(lv);
		
		background = new BufferedImage[9];
		for(int i = 0; i <= maxbgindex; i++) 
		{
			background[i] = Main.spritesheet.getSprite(0, 100+i*35, 500, 35);
		}
		
		
		for(int i = 1; i <= 40; i++) {
			if(Array.getLength(cellindexes) > 1) {
					Cell cell = new Cell(x+39+11*(i-1),y+2,skill,i,cellindexes[i-1],this);
					Main.cells.add(cell);
			}else {
					Cell cell = new Cell(x+39+11*(i-1),y+2,skill,i,0,this);
					Main.cells.add(cell);
			}
		}
		
		
		Button changebg = new Button(x+39,y+32,16,16,"ChangeBackgroundPlus",skill, this);
		Main.buttons.add(changebg);
		Button changebg2 = new Button(x+39+16,y+32,16,16,"ChangeBackgroundMinus",skill, this);
		Main.buttons.add(changebg2);
		Button delete = new Button(x-20,y+10,16,16,"DeleteSkill",skill, this);
		Main.buttons.add(delete);
		
		this.x = x;
		this.y = y;
		this.Skill = skill;
		this.level = level;
		this.bgindex = bgindex;
		
		if(cellindexes.length <= 1)
		CellIndexes = new int[40];
		else
		CellIndexes = cellindexes;
	}
	
	public void tick() {
	
	}
	
	public String getSkill() {
		return Skill;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y + getArrayPosition()*70;
	}
	public int getTrueY() {
		return y;
	}
	public int getLevel() {
		return level;
	}
	public int getBgIndex() {
		return bgindex;
	}
	public int[] getIndexes() {
		return CellIndexes;
	}
	
	public int getArrayPosition() {
		return Main.skills.indexOf(this);
	}
	
	public void setLevel(int nlevel) {
		level = nlevel;
	}
	public void setIndexes(Cell c) {
		CellIndexes[c.getOrder()-1] = c.getIndex();
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
			if(bgindex+change < 0)
		bgindex = maxbgindex;
		else if(bgindex+change > maxbgindex)
		bgindex = 0;
		else
		bgindex+=change;
	}
	
	public void render(Graphics g) {
		g.drawImage(background[bgindex],x,y+getArrayPosition()*70-(int)Main.cameray,null);
	
		//g.setFont(new Font("arial", Font.BOLD,20));
		g.setFont(new Font("Arial", Font.BOLD,18));
		g.setColor(Color.WHITE);
		g.drawString(""+Skill, x+40,y+getArrayPosition()*70-(int)Main.cameray);
		g.setColor(Color.BLACK);
		g.drawString(""+Skill, x+41,y+getArrayPosition()*70-1-(int)Main.cameray);
	
	}
}
