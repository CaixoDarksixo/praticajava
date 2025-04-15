package praticajava;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Cell {
	private BufferedImage[] cellframe;
	
	private int index = 0;
	public int x;
	public int y;
	public int width;
	public int height;
	public String skill;
	public int order;
	
	public Skill parent;
	
	boolean deleted = false;
	
	public Cell(int x, int y, String skill, int order, int index, Skill parent) {
		cellframe = new BufferedImage[3];
		cellframe[0] = Main.spritesheet.getSprite(0, 0, 12*1, 30*1);
		cellframe[1] = Main.spritesheet.getSprite(12*1, 0, 12*1, 30*1);
		cellframe[2] = Main.spritesheet.getSprite(12*2, 0, 12*1, 30*1);
		
		this.x = x;
		this.y = y;
		this.width = 11;
		this.height = 30;
		this.skill = skill;
		this.order = order;
		this.index = index;
		
		this.parent = parent;
	}
	
	public void tick() {
		if(deleted){
		Main.cells.remove(this);
		System.gc();
		}
	}

	public void render(Graphics g) {
		g.drawImage(cellframe[index],x,y+parent.getArrayPosition()*70-(int)Main.cameray,null);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y + parent.getArrayPosition()*70;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public String getSkill() {
		return skill;
	}
	public int getOrder() {
		return order;
	}
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int i) {
		index = i;
		parent.setIndexes(this);
	}
	
	public void ResetBar() {
		for(int i = 0; i < Main.cells.size(); i++) {
			if( Main.cells.get(i).getSkill() == skill )
				{
				Main.cells.get(i).setIndex(0);
				}
				
		}
		for(int i = 0; i < Main.levelscircles.size(); i++) {
			if( Main.levelscircles.get(i).getSkill() == skill )
				{
				Main.levelscircles.get(i).updateLevel();
				}
				
		}
		Sound.startup.loop(0);
	}

	public void changeIndex(MouseEvent e) {
		if(index == 0) {
			if(e.getButton() == MouseEvent.BUTTON1)
				index = 1;
			if(e.getButton() == MouseEvent.BUTTON3)
				index = 2;
			
			parent.setIndexes(this);
			
			if(order == 40) {
				ResetBar();
			}
			
			
			for(int i = 0; i < Main.cells.size(); i++) {
				if( Main.cells.get(i).getSkill() == skill &&
					Main.cells.get(i).getOrder() < order &&
					Main.cells.get(i).getIndex() == 0)
					Main.cells.get(i).setIndex(index);
			}
		}else {
			index = 0;
			
			for(int i = 0; i < Main.cells.size(); i++) {
				if( Main.cells.get(i).getSkill() == skill &&
					Main.cells.get(i).getOrder() > order &&
					Main.cells.get(i).getIndex() != 0)
					Main.cells.get(i).setIndex(index);
			}
		}
		
		
	}
}
