package praticajava;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import praticajava.Spritesheet;
import praticajava.Sound;

@SuppressWarnings("unused")
public class Main extends Canvas implements Runnable, KeyListener, MouseListener {
	
private static final long serialVersionUID = 1L;
public static JFrame frame;
private Thread thread;
private boolean isRunning = true;
public static final int WIDTH = 1200;
public static int HEIGHT = 800;
public final static int SCALE = 1;

private BufferedImage image;

public static int fh = (int)(Toolkit.getDefaultToolkit().getScreenSize().height * 0.8), fw = (int)( Toolkit.getDefaultToolkit().getScreenSize().height * 1);

public static Spritesheet backgroundspritesheet;
private BufferedImage background = null;

public Main(){
	addKeyListener(this);
	addMouseListener(this);
	
	this.setPreferredSize(new Dimension(fw,fh));
	initFrame();
	image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

	backgroundspritesheet = new Spritesheet("/background.png");
	background = backgroundspritesheet.getSprite(0, 0, 1200, 800);
			//(getClass().getResource("/background.png"));
	
	
}

public void initFrame() {
	requestFocus();
	frame = new JFrame("Programa Teste");
	frame.add(this);
	frame.setResizable(true);
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
	
	Sound.startup.play();
	
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
	Sound.startup.play();
	
	
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
	
	g.drawImage(background,0,0, frame);
	/***/
	g.dispose();
	
	g = bs.getDrawGraphics();

	
	bs.show();
	g.drawImage(image, 0, 0,fw,fh,null);
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
	
	//if(delta <= 0.8 ) {
		//delta = 1;
	//}
	if(delta >= 1) {
		//System.out.println("delta: "+ delta);
		
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

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
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
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

}
