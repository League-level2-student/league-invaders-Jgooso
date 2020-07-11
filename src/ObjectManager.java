import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	int score = 0;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),10,50,50));
	}
	void update() {
		rocket.update();
		for(int i = 0; i < aliens.size();i++) {
			aliens.get(i).update();
			if(aliens.get(i).y > LeagueInvaders.HEIGHT || aliens.get(i).y < 0) {
				aliens.get(i).isActive = false;
			}
		}
		for(int i = 0; i < projectiles.size();i++) {
			projectiles.get(i).update();
			if(projectiles.get(i).y > LeagueInvaders.HEIGHT || projectiles.get(i).y < 0) {
				projectiles.get(i).isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
		
	}
	void checkCollision() {
		for(int i = 0; i < aliens.size();i++) {
			aliens.get(i).update();
			if(rocket.collisionBox.intersects(aliens.get(i).collisionBox)) {
				aliens.get(i).isActive = false;
				rocket.isActive = false;
				new GamePanel().currentState = 2;
			}
		}
		for(int i = 0; i < projectiles.size();i++) {
			projectiles.get(i).update();
			for(int x = 0; x< aliens.size();x++) {
			if(aliens.get(x).collisionBox.intersects(projectiles.get(i).collisionBox)) {
				projectiles.get(i).isActive = false;
				aliens.get(x).isActive = false;
				score++;
			}
			}
		}
	 }
	void draw(Graphics g) {
		rocket.draw(g);
		for(int i = 0; i < aliens.size();i++) {
			aliens.get(i).draw(g);
			
		}
		for(int i = 0; i < projectiles.size();i++) {
			projectiles.get(i).draw(g);
			
		}
		
	}
	void purgeObjects() {
		
		for(int i = 0; i < aliens.size();i++) {
			if(aliens.get(i).isActive == false) {
				aliens.remove(i);
				i--;
			}
			
		}
		for(int i = 0; i < projectiles.size();i++) {
			if(projectiles.get(i).isActive == false) {
				projectiles.remove(i);
				i--;
			}
			
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(aliens.size() < 10) {
		addAlien();
		}
		
	}
}
