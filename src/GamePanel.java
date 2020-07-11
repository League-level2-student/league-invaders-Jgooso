import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
    Font menuFont;
    Timer frameDraw;
    Timer alienSpawn;
    Rocketship rocket;
    ObjectManager manager;
    
    
    GamePanel(){
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	menuFont = new Font("Arial", Font.PLAIN, 24);
    	frameDraw = new Timer(1000/60,this);
    	rocket = new Rocketship(250,700,50,50);
        frameDraw.start();
        manager = new ObjectManager(rocket);
        
        
        
        
    }
    void startGame() {
    	alienSpawn = new Timer(1000 , manager);
        alienSpawn.start();
    }
    void updateMenuState() {  
    	
    }
    void updateGameState() {  
    	manager.update();
    	if(rocket.isActive == false) {
    		currentState = END;
    	}
    }
    void updateEndState()  {  
    	manager.update();
    }
    void drawMenuState(Graphics g) {  
    	g.setColor(Color.BLUE);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(titleFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("League Invaders", 75, 100);
    	g.setFont(menuFont);
    	g.drawString("Press enter to begin", 150, 350);
    	g.drawString("Press space to see instructions", 100, 500);
    }
    void drawGameState(Graphics g) throws IOException {  
    	g.setColor(Color.BLACK);
    	g.drawImage(ImageIO.read(this.getClass().getResourceAsStream("space.png")),0,0,LeagueInvaders.WIDTH,LeagueInvaders.HEIGHT,null);
    	manager.draw(g);
    	g.setColor(Color.white);
    	g.setFont(menuFont);
    	g.drawString(String.valueOf(manager.score),10,650);
    	
    }
    void drawEndState(Graphics g)  {  
    	g.setColor(Color.RED);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setColor(Color.black);
    	g.setFont(titleFont);
    	g.drawString(String.valueOf(manager.score), 225, 100);
    	
    }
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    try {
				drawGameState(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.print("action");
		repaint();
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		    startGame();
		}else if(currentState == END){
		    updateEndState();
		    alienSpawn.stop();
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    rocket.up();
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    rocket.down();
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    rocket.left();
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    rocket.right();
		}
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        rocket = new Rocketship(250,700,50,50);
		        manager.score = 0;
		    } else {
		        currentState++;
		    }
		}  
		if(e.getKeyCode() == KeyEvent.VK_SPACE && currentState == GAME) {
			manager.addProjectile(rocket.getProjectile());
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
