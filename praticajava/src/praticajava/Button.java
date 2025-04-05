package praticajava;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Button {
	private BufferedImage[] backgroundbutton;
	private BufferedImage newskillbutton;
	private BufferedImage deleteskillbutton;
	
	public int x,y,width,height;
	public String function, skill;
	
	public boolean clicked = false, deleted = false;

	public Button(int x, int y, int width, int height, String function, String skill) {
	
	backgroundbutton = new BufferedImage[2];
	backgroundbutton[0] = Main.spritesheet.getSprite(208, 0, 16, 16);
	backgroundbutton[1] = Main.spritesheet.getSprite(224, 0, 16, 16);
	
	newskillbutton = Main.spritesheet.getSprite(240, 0, 108, 20);
	
	deleteskillbutton = Main.spritesheet.getSprite(208, 16, 16, 16);
	
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.function = function;
	this.skill = skill;
	}
	
	public void tick() {
		if(deleted)
			Main.buttons.remove(this);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
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
	
	public void execute() {
		switch(function) {
		case "ChangeBackgroundPlus":
			for(int i = 0; i < Main.skills.size(); i++) {
				if( Main.skills.get(i).getSkill() == skill )
					{
					Main.skills.get(i).changeBackground(-1);
					}
					
			}
		break;
		case "ChangeBackgroundMinus":
			for(int i = 0; i < Main.skills.size(); i++) {
				if( Main.skills.get(i).getSkill() == skill )
					{
					Main.skills.get(i).changeBackground(1);
					}
					
			}
		break;
		case "CreateSkill":
			int[] none = {0};
			Skill nskill = new Skill( JOptionPane.showInputDialog(Main.frame, "Nome da habilidade:"),50,150+Main.skills.size()*70,0,0,none);
			Main.skills.add(nskill);
		break;
		case "DeleteSkill":
			if(JOptionPane.showConfirmDialog(Main.frame, "Tem certeza que deseja deletar o progresso?") == 0) {
				for(int i = 0; i < Main.skills.size(); i++) {
					if( Main.skills.get(i).getSkill() == skill )
						{
						Main.skills.get(i).deleteSkill();
						}
						
				}
			}
				
		break;
		case "ChangePfpPlus":
			if(Main.bg.pfpindex < 19)
			Main.bg.pfpindex++;
			else
			Main.bg.pfpindex = 0;
		break;
		case "ChangePfpMinus":
			if(Main.bg.pfpindex > 0)
			Main.bg.pfpindex--;
			else
			Main.bg.pfpindex = 19;
		break;
		}
	}
	
	public void render(Graphics g) {
		switch(function) {
		case "ChangeBackgroundPlus":
			g.drawImage(backgroundbutton[0],x,y-(int)Main.cameray,null);
		break;
		case "ChangeBackgroundMinus":
			g.drawImage(backgroundbutton[1],x,y-(int)Main.cameray,null);
		break;
		case "CreateSkill":
			g.drawImage(newskillbutton,x,y-(int)Main.cameray,null);
		break;
		case "DeleteSkill":
			g.drawImage(deleteskillbutton,x,y-(int)Main.cameray,null);
		break;
		case "ChangePfpPlus":
			g.drawImage(backgroundbutton[0],x,y-(int)Main.cameray,null);
		break;
		case "ChangePfpMinus":
			g.drawImage(backgroundbutton[1],x,y-(int)Main.cameray,null);
		break;
		}
	}
}
