package praticajava;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends Canvas implements Runnable, KeyListener, MouseListener {
	
private static final long serialVersionUID = 1L;
public static JFrame frame;
private Thread thread;
private boolean isRunning = true;
public static final int WIDTH = 1080;
public static int HEIGHT = 560;
public final static int SCALE = 1;

public static double cameray = 0;

public double fwToWidth;
public double fhToHeight;

private BufferedImage image;

public static int fh = (int)(Toolkit.getDefaultToolkit().getScreenSize().height * 0.56), fw = (int)( Toolkit.getDefaultToolkit().getScreenSize().height * 1.08);

public static Spritesheet backgroundspritesheet;
public static Spritesheet spritesheet;
public static Spritesheet spritesheetpfp;

static Background bg;
public static List<Cell> cells;
public static List<LevelCircle> levelscircles;
public static List<Skill> skills;
public static List<Button> buttons;

public Main(){
	addKeyListener(this);
	addMouseListener(this);
	
	this.setPreferredSize(new Dimension(fw,fh));
	initFrame();
	image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	fwToWidth = (double)WIDTH/(double)fw;
	fhToHeight = (double)HEIGHT/(double)fh;
	System.out.println("Proporção X: "+fwToWidth);
	System.out.println("Proporção Y: "+fhToHeight);

	backgroundspritesheet = new Spritesheet("/background.png");
	spritesheet = new Spritesheet("/cells.png");
	spritesheetpfp = new Spritesheet("/spritescaixos.png");
	
	bg = new Background(0,0,WIDTH,HEIGHT,null);
	
	cells = new ArrayList<Cell>();
	levelscircles = new ArrayList<LevelCircle>();
	skills = new ArrayList<Skill>();
	buttons = new ArrayList<Button>();
	
	//Skill skill = new Skill(50,150+skills.size()*50, JOptionPane.showInputDialog(frame, "Nome da habilidade"),0);
	//Main.skills.add(skill);
	
	Button createskill = new Button(6, 110, 108, 20, "CreateSkill", "");
	Main.buttons.add(createskill);
	
	Button changepfp = new Button(10, 0, 16, 16, "ChangePfpPlus", "");
	Main.buttons.add(changepfp);
	Button changepfp2 = new Button(26, 0, 16, 16, "ChangePfpMinus", "");
	Main.buttons.add(changepfp2);
	
	Save.applySave(Save.loadSave());
}

public void initFrame() {
	requestFocus();
	frame = new JFrame("Progress Manager");
	frame.add(this);
	frame.setResizable(false);
	frame.pack();
	frame.getWidth();
	frame.getHeight();
	
	Image imagem = null;
	try {
		imagem = ImageIO.read(getClass().getResource("/icon.png"));
	}catch (IOException e){
		e.printStackTrace();
	}
	Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image = toolkit.getImage(getClass().getResource("/mouse.png"));
    Cursor c = toolkit.createCustomCursor(image, new Point(0,0), "img");
    
    frame.setCursor(c);
    frame.setIconImage(imagem);
   
			
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	Sound.startup.loop(0);
	
	 usingCustomFonts();
	 
	

	
}

public synchronized void start() {
	thread = new Thread(this);
	isRunning = true;
	thread.start();
}
public synchronized void stop() {
	isRunning = false;
try {
	thread.join();
} catch (InterruptedException e) {
	
	e.printStackTrace();
}

}

public void tick() {
	bg.tick();
	for(int i = 0; i < cells.size(); i++) {
		cells.get(i).tick();
	}
	for(int i = 0; i < levelscircles.size(); i++) {
		levelscircles.get(i).tick();
	}
	for(int i = 0; i < buttons.size(); i++) {
		buttons.get(i).tick();
	}
	for(int i = 0; i < skills.size(); i++) {
		skills.get(i).tick();
	}
}

public void render() {
	BufferStrategy bs = this.getBufferStrategy();
	if(bs == null) {
		this.createBufferStrategy(3);
		return;
	}
	Graphics g = image.getGraphics();
	g.setColor(Color.BLACK);
	g.fillRect(0, 0,WIDTH,HEIGHT);
	
	//background
	bg.render(g);
	
	//cells
	for(int i = 0; i < skills.size(); i++) {
		skills.get(i).render(g);
	}
	
	for(int i = 0; i < cells.size(); i++) {
		cells.get(i).render(g);
	}
	for(int i = 0; i < levelscircles.size(); i++) {
		levelscircles.get(i).render(g);
	}
	for(int i = 0; i < buttons.size(); i++) {
		buttons.get(i).render(g);
	}
	
	/***/
	g.dispose();
	
	g = bs.getDrawGraphics();

	
	bs.show();
	g.drawImage(image, 0, 0,frame.getWidth(),frame.getHeight(),null);
	//aqui se digita o que tu não quer que seja pixelado

	
}

public static void main(String args[]) {
	Main exec = new Main();
	exec.start();
}

@Override
public void run() {
	
	// TODO Auto-generated method stub
	long lastTime = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    int frames = 0;
    double timer = System.currentTimeMillis();
   
while(isRunning) {
	long now = System.nanoTime();
	delta+= (now - lastTime) / ns;
	lastTime = now;
	
	if(delta >= 1) {
		
		tick();
		render();
		frames++;
		delta = 0;
		
			
	}
if(System.currentTimeMillis() - timer >= 1000) {
		System.out.println("FPS: "+ frames);
		
		frames=0;
		
		timer+=1000;
		
	}
}

stop();

}

// from baeldung.com
void usingCustomFonts() {
    GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
    List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
    try {
        List<File> LIST = Arrays.asList(
          new File("/font.ttf"), // Pixeloid Sans
          new File("/font2.ttf"), // Pixeloid Mono
          new File("/font3.ttf"),
          new File("/font4.otf") // FOT-NewRodin Pro
        );
        for (File LIST_ITEM : LIST) {
            if (LIST_ITEM.exists()) {
                Font FONT = Font.createFont(Font.TRUETYPE_FONT, LIST_ITEM);
                if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())) {
                    GE.registerFont(FONT);
                    System.out.println(FONT.getFontName());
                }
            }
        }
    } catch (FontFormatException | IOException exception) {
        JOptionPane.showMessageDialog(null, exception.getMessage());
    }
}

public boolean isColliding(double x1, double y1, Cell cl) {
	
	if(x1 >= cl.getX() && x1 <= cl.getX()+cl.getWidth() && y1 >= cl.getY() && y1 <= cl.getY()+cl.getHeight()+5) {
		return true;
	}
	
	return false;
}

public boolean isCollidingLevel(double x1, double y1, LevelCircle cl) {
	
	if(x1 >= cl.getX() && x1 <= cl.getX()+cl.getWidth() && y1 >= cl.getY() && y1 <= cl.getY()+cl.getHeight()+5) {
		return true;
	}
	
	return false;
}
public boolean isCollidingButton(double x1, double y1, Button cl) {
	
	if(x1 >= cl.getX() && x1 <= cl.getX()+cl.getWidth() && y1 >= cl.getY() && y1 <= cl.getY()+cl.getHeight()+5) {
		return true;
	}
	
	return false;
}

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	double mousex = (e.getX()*fwToWidth);
	double mousey = (e.getY()*fhToHeight)+Main.cameray;
	System.out.println(mousex);
	System.out.println(mousey);
	//Muda a célula
	for(int i = 0; i < cells.size(); i++) {
		if(isColliding(mousex,mousey, cells.get(i))){
			cells.get(i).changeIndex(e);
			System.out.println("clicou na célula");
		}
	}
	//altera o level
	for(int i = 0; i < levelscircles.size(); i++) {
		if(isCollidingLevel(mousex,mousey, levelscircles.get(i))){
			levelscircles.get(i).changeLevel(e);
			System.out.println("clicou no level");
		}
	}
	//clica em um botão
	for(int i = 0; i < buttons.size(); i++) {
		if(isCollidingButton(mousex,mousey, buttons.get(i))){
			buttons.get(i).execute();
			System.out.println("clicou no botão");
		}
	}
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode() == KeyEvent.VK_UP) {
		cameray-=15;
		
	}
	if(e.getKeyCode() == KeyEvent.VK_DOWN) {
		cameray+=15;
		
	}
	
	if(e.getKeyCode() == KeyEvent.VK_S) {
		Save.SaveNow();
	}
	if(e.getKeyCode() == KeyEvent.VK_L) {
		Save.applySave(Save.loadSave());
	}
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

}
