import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
    Font menuFont;
    Timer frameDraw;
    Rocketship rocket;
    GamePanel(){
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	menuFont = new Font("Arial", Font.PLAIN, 24);
    	frameDraw = new Timer(1000/60,this);
    	rocket = new Rocketship(250,700,50,50);
        frameDraw.start();
    }
    void updateMenuState() {  
    	
    }
    void updateGameState() {  
    	
    }
    void updateEndState()  {  
    	
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
    void drawGameState(Graphics g) {  
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	rocket.draw(g);
    }
    void drawEndState(Graphics g)  {  
    	g.setColor(Color.RED);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    }
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
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
		}else if(currentState == END){
		    updateEndState();
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
		    } else {
		        currentState++;
		    }
		}   
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
