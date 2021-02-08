package a10;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

public class Example extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private ArrayList<Actor> actors;
	private ArrayList<Coins> coins;
	private ArrayList<Home> house;
	// Plants and zombies all go in here
	BufferedImage plantImage; // Maybe these images should be in those classes, but easy to change here.
	BufferedImage zombieImage;
	BufferedImage cowImage;
	BufferedImage chickenImage;
	BufferedImage crabImage;
	BufferedImage bCatImage;
	BufferedImage blank;
	BufferedImage coinImage;
	int numRows;
	int numCols;
	int cellSize;
	Random random;
	JLabel scoreLabel;
	int score;
	private JButton dog;
	private JButton chicken;
	private JButton cow;
	private boolean dogClicked;
	private boolean cowClicked;
	private boolean chickenClicked;
	private Random rand;
	private int zombieY;
	private int crabY;
	private int bCatY;
	Home home;
	Zombie zombie;
	SubZombie crab;
	JFrame gameover;

	/**
	 * Setup the basic info for the example
	 */
	public Example() {
		super();

		// Define some quantities of the scene

		rand = new Random();
		scoreLabel = new JLabel("Score: " + score);
		dog = new JButton("dog cost:1");
		cow = new JButton("cow cost:3");
		chicken = new JButton("chicken cost:2");
		dog.setBounds(25, 25, 60, 30);
		cow.setBounds(90, 25, 60, 30);
		chicken.setBounds(155, 25, 60, 30);

		// button
		this.add(dog);
		this.add(cow);
		this.add(chicken);
		this.add(scoreLabel);

		dog.addActionListener(this);
		cow.addActionListener(this);
		chicken.addActionListener(this);
		addMouseListener(this);

		// Define some quantities of the scene
		score = 0;
		numRows = 5;
		numCols = 7;
		cellSize = 75;
		setPreferredSize(new Dimension(50 + numCols * cellSize, 50 + numRows * cellSize));
		random = new Random();

		// Store all the plants and zombies in here.
		actors = new ArrayList<>();
		coins = new ArrayList<>();
		house = new ArrayList<>();

		// Load images
		try {
			plantImage = ImageIO.read(new File("src/a10/Animal-Icons/dog-icon.png"));
			cowImage = ImageIO.read(new File("src/a10/Animal-Icons/cow-icon.png"));
			chickenImage = ImageIO.read(new File("src/a10/Animal-Icons/chicken-icon.png"));
			zombieImage = ImageIO.read(new File("src/a10/Animal-Icons/cat-icon.png"));
			crabImage = ImageIO.read(new File("src/a10/Animal-Icons/crab-icon.png"));
			bCatImage = ImageIO.read(new File("src/a10/Animal-Icons/black-cat-icon.png"));
			blank = ImageIO.read(new File("src/a10/Animal-Icons/vector-house-outline-set.png"));
			coinImage = ImageIO.read(new File("src/a10/Animal-Icons/red-coin.png"));
		} catch (IOException e) {
			System.out.println("A file was not found");
			System.exit(0);
		}

		zombieY = 50;
		crabY = 125;
		bCatY = 200;

		// Make a zombie
		Home home = new Home(new Point2D.Double(0, 75), new Point2D.Double(50, 700), blank, 1, 0, 0, 0);

		zombie = new Zombie(new Point2D.Double(500, zombieY),
				new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), zombieImage, 100, 40, -.5, 10);

		crab = new SubZombie(new Point2D.Double(500, crabY),
				new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), crabImage, 150, 60, -.25, 20);

		BlackCat blackCat = new BlackCat(new Point2D.Double(500, bCatY),
				new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), bCatImage, 30, 60, -.5, 15);

		actors.add(zombie);
		actors.add(crab);

		house.add(home);

		// The timer updates the game each time it goes.
		// Get the javax.swing Timer, not from util.
		timer = new Timer(30, this);
		timer.start();

	}

	public int gridSpaceX(int x) {
		x = (x - 50) / 75;
		x = x * 75 + 50;

		return x;
	}

	public int gridSpaceY(int y) {
		y = (y - 50) / 75;
		y = y * 75 + 50;
		return y;
	}

	/***
	 * Implement the paint method to draw the plants
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Actor actor : actors) {
			actor.draw(g, 0);
			actor.drawHealthBar(g);
		}
		for (Coins c : coins) {
			c.draw(g, 0);
		}
		for (Home h : house) {
			h.draw(g, 0);

		}
	}

	/**
	 * 
	 * This is triggered by the timer. It is the game loop of this test.
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Increment their cooldowns and reset collision status
		for (Home h : house) {
			h.update();
		}
		for (Actor actor : actors) {

			actor.update();

		}

		// Try to attack
		for (Actor zombie : actors) {
			for (Home h : house) {
				zombie.attack(h);
			}
		}
		for (Actor zombie : actors) {
			for (Actor other : actors) {
				zombie.attack(other);

			}

		}

		// drop coins
		int row = rand.nextInt(5);
		int col = rand.nextInt(7);
		if (row == 0) {
			row += 2;
		}
		if (col == 0) {
			col += 2;
		}
		int x = row * (75);
		int y = col * (75);
		int coinspawn = rand.nextInt(100);
		if (coinspawn > 98) {
			Coins coin = new Coins(new Point2D.Double(x, y), new Point2D.Double(25, 25), coinImage, 50, 10, 0, 0);
			coins.add(coin);

		}
		repaint();
		ArrayList<Coins> newCoin = new ArrayList<>();
		;
		for (Coins c : coins) {
			if (c.isAlive()) {
				newCoin.add(c);
			} else
				score++;
		}
		for (Coins c : coins) {
			if (c.readyForAction()) {
				if (c.isAlive()) {
					coins.remove(c);
				} else
					score++;
			}
			coins = newCoin;
			scoreLabel.setText("Score: " + score);
		}
		for (Home h : house) {
			if (h.readyForAction()) {
				if (!h.isAlive()) {
					house.remove(h);
					JOptionPane.showMessageDialog(gameover, "GAME OVER");
					System.exit(0);
				}
			}
		}
		// Remove plants and zombies with low health
		ArrayList<Actor> nextTurnActors = new ArrayList<>();
		for (Actor actor : actors) {
			if (actor.isAlive()) {
				nextTurnActors.add(actor);
			}
			actor.removeAction(actors);
			// any special effect or whatever on removal
		}
		actors = nextTurnActors;
		int count = 0;
		int damage = 0;
		for (Actor actor : actors) {
			if (actor instanceof Zombie)
				count++;

		}
		if (count == 0) {
			damage += 10;
			zombie = new Zombie(new Point2D.Double(500, zombieY),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), zombieImage, 100, 40, -.5,
					damage);

			crab = new SubZombie(new Point2D.Double(500, crabY),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), crabImage, 150, 60, -.35,
					(damage * 2));

			BlackCat blackCat = new BlackCat(new Point2D.Double(500, bCatY),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), bCatImage, 30, 60, -.5,
					(damage + 5));
			actors.add(zombie);
			actors.add(crab);
			actors.add(blackCat);
		}

		// Check for collisions between zombies and plants and set collision status
		for (Actor zombie : actors) {
			for (Actor other : actors) {
				zombie.setCollisionStatus(other);
			}
		}
		for (Home h : house) {
			for (Actor other1 : actors) {
				h.setCollisionStatus3(other1);
			}
		}
		for (Actor other1 : actors) {
			for (Actor other : actors) {
				other1.setCollisionStatus2(other);
			}
		}
		// Move the actors.
		for (Actor actor : actors) {
			actor.move(); // for Zombie, only moves if not colliding.
		}

		// if buttons are clicked
		if (e.getSource() == chicken) {

			chickenClicked = true;

		}
		if (e.getSource() == dog) {
			dogClicked = true;
		}
		if (e.getSource() == cow) {
			cowClicked = true;
		}

		// Try to attack

		// Redraw the new scene
		repaint();
	}

	/**
	 * Make the game and run it
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame app = new JFrame("Dog and Cat Test");
				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Example panel = new Example();

				app.setContentPane(panel);
				app.pack();
				app.setVisible(true);
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Zombie zombie = new Zombie(new Point2D.Double(500, zombieY),
				new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), zombieImage, 100, 40, -1.75, 10);

		SubZombie crab = new SubZombie(new Point2D.Double(500, crabY),
				new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), crabImage, 150, 60, -1, 20);

		BlackCat blackCat = new BlackCat(new Point2D.Double(500, bCatY),
				new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), bCatImage, 150, 60, -1.5, 15);
		int cX = e.getX();
		int cY = e.getY();
		for (Coins c : coins) {
			if (c.isCollidingPoint(new Point2D.Double(cX, cY))) {
				c.changeHealth(-50);
			}
		}

		if (dogClicked && score >= 1) {
			score--;
			int x = gridSpaceX(e.getX());
			int y = gridSpaceY(e.getY());
			// shift x y
			Plant plant = new Plant(new Point2D.Double(x, y),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), plantImage, 100, 50, 10);
			Plant cow = new Plant(new Point2D.Double(x, y),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), cowImage, 300, 100, 20);
			Plant chicken = new Plant(new Point2D.Double(x, y),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), chickenImage, 50, 1, 1);
			if (chicken.isCollidingPoint(new Point2D.Double(x, y)) && plant.isCollidingPoint(new Point2D.Double(x, y))
					&& cow.isCollidingPoint(new Point2D.Double(x, y))) {
				actors.add(plant);
				dogClicked = false;
			}

		}
		if (cowClicked && score >= 3) {
			score = score - 3;
			int x = gridSpaceX(e.getX());
			int y = gridSpaceY(e.getY());
			Plant cow = new Plant(new Point2D.Double(x, y),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), cowImage, 300, 100, 20);
			Plant chicken = new Plant(new Point2D.Double(x, y),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), chickenImage, 50, 1, 1);
			Plant plant = new Plant(new Point2D.Double(x, y),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), plantImage, 100, 50, 10);
			if (chicken.isCollidingPoint(new Point2D.Double(x, y)) && plant.isCollidingPoint(new Point2D.Double(x, y))
					&& cow.isCollidingPoint(new Point2D.Double(x, y))) {
				actors.add(cow);
				cowClicked = false;
			}

		}
		if (chickenClicked && score >= 2) {
			score = score - 2;
			int x = gridSpaceX(e.getX());
			int y = gridSpaceY(e.getY());
			Plant chicken = new Plant(new Point2D.Double(x, y),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), chickenImage, 50, 1, 1);
			Plant plant = new Plant(new Point2D.Double(x, y),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), plantImage, 100, 50, 10);
			Plant cow = new Plant(new Point2D.Double(x, y),
					new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), cowImage, 300, 100, 20);
			if (chicken.isCollidingPoint(new Point2D.Double(x, y)) && plant.isCollidingPoint(new Point2D.Double(x, y))
					&& cow.isCollidingPoint(new Point2D.Double(x, y))) {
				actors.add(chicken);
				chickenClicked = false;
			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}