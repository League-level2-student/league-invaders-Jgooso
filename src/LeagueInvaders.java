import javax.swing.*;

public class LeagueInvaders {
	JFrame frame;
	GamePanel panel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	public static void main(String[] args) {
		LeagueInvaders league = new LeagueInvaders(new JFrame());
		
	}
	public LeagueInvaders(JFrame frame) {
		this.frame = frame;
		panel = new GamePanel();
		setup();
	}
	void setup() {
		frame.addKeyListener(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
