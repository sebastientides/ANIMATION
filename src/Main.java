import java.awt.*;
import javax.swing.*;


public class Main{

	public static void main(String[] args) {
		DisplayMode displayMode=new DisplayMode(800,600,16,DisplayMode.REFRESH_RATE_UNKNOWN);
		Main m = new Main();
		m.run(displayMode);
	}
	private Screen screen;
	private Image bg;
	private Animation a;
	//loads pictures from computer to java and adds scene
	public void loadPics(){
		bg = new ImageIcon("C:\\Users\\Sebastien\\Desktop\\cardgameart\\800600bgimage.jpg").getImage();
		Image gem1=new ImageIcon("C:\\Users\\Sebastien\\Desktop\\cardgameart\\ADEFGEM.jpg").getImage();
		Image gem2=new ImageIcon("C:\\Users\\Sebastien\\Desktop\\cardgameart\\EDEFGEM.jpg").getImage();
		Image gem3=new ImageIcon("C:\\Users\\Sebastien\\Desktop\\cardgameart\\FDEFGEM.jpg").getImage();
		Image gem4=new ImageIcon("C:\\Users\\Sebastien\\Desktop\\cardgameart\\SDEFGEM.jpg").getImage();
		Image gem5=new ImageIcon("C:\\Users\\Sebastien\\Desktop\\cardgameart\\WDEFGEN.jpg").getImage();
		a = new Animation();
		a.addScene(gem1, 250);
		a.addScene(gem2, 250);
		a.addScene(gem3, 250);
		a.addScene(gem4, 250);
		a.addScene(gem5, 250);
	}
	//main engine to run
	public void run (DisplayMode dm){
		screen = new Screen();
		try{
			screen.setFullScreen(dm,new JFrame());
			loadPics();
			movieLoop();
		}finally{
			screen.restoreScreen();
		}
	}
	//main movie loop
	public void movieLoop(){
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(cumTime - startingTime < 5000){
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			a.update(timePassed);
			
			Graphics g = screen.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();
			
			try{
				Thread.sleep(20);
				
			}catch(Exception ex){
				
			}
		}
	}
	//draw method
	public void draw(Graphics g){
		g.drawImage(bg, 0, 0, null);
		g.drawImage(a.getImage(), 100, 100, null);
	}
}
