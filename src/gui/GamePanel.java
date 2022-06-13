package gui;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import resources.CollisionChecker;
import resources.Player;
import resources.ResourceController;
import resources.TileManager;

public class GamePanel extends BorderPane {

	Canvas canvas;
	
	private GraphicsContext gc;
	//booleans
	private boolean moveUp;
	private boolean moveDown;
	private boolean moveLeft;
	private boolean moveRight;
	
	public final int screenWidth;
	public final int screenHeight;
	final int FPS = 60;
	ResourceController rs;
//	boolean nextScreen = false;
	AnimationTimer gameloop;
	
	public static final int maxScreenCol = 16;
	public static final int maxScreenRow = (int) (maxScreenCol/Main.rowMultiplier);
	public final static int TILESIZE = ScreenController.screenWidth/maxScreenCol;
	//WORLD parameters
	public final int maxWorldCol = 50;
	public final int MaxWorldRow = 50;
	public final int worldWidth = TILESIZE * maxWorldCol;
	public final int worldHeight = TILESIZE * MaxWorldRow;
	//OTHER
	public TileManager tileM;
	public CollisionChecker collision;
	final int initialX;
	final int initialY;
	public Player player;

	public GamePanel(double width, double height, int x, int y, ResourceController rs) {
		
		this.rs = rs;
		initialX = x;
		initialY = y;
		screenWidth = maxScreenCol*TILESIZE;
		screenHeight =maxScreenRow*TILESIZE;
		buildGui();
	}

	private void buildGui() {

		canvas = new Canvas(maxScreenCol*TILESIZE,maxScreenRow*TILESIZE);
		gc = canvas.getGraphicsContext2D();
		player = new Player(this, initialX, initialY);
		tileM = new TileManager(this);
		tileM.createMap("world01");
		collision = new CollisionChecker(this);
		canvas.setFocusTraversable(true);
		this.setCenter(canvas);
		
		paintMap();
		drawPlayer();
		
		this.setOnKeyPressed(evt -> {
			KeyCode code = evt.getCode();
			if (code.equals(KeyCode.Z) || code.equals(KeyCode.UP))
				moveUp = true;
			if (code.equals(KeyCode.S) || code.equals(KeyCode.DOWN))
				moveDown = true;
			if (code.equals(KeyCode.Q) || code.equals(KeyCode.LEFT))
				moveLeft = true;
			if (code.equals(KeyCode.D) || code.equals(KeyCode.RIGHT))
				moveRight = true;
			if (code.equals(KeyCode.SHIFT)) 
				player.sprint();
		});

		this.setOnKeyReleased(evt -> {
			KeyCode code = evt.getCode();
			if (code.equals(KeyCode.Z) || code.equals(KeyCode.UP)) {
				moveUp = false;
				player.stopMoving();
			}
			if (code.equals(KeyCode.S) || code.equals(KeyCode.DOWN)) {
				moveDown = false;
				player.stopMoving();
			}
			if (code.equals(KeyCode.Q) || code.equals(KeyCode.LEFT)) {
				moveLeft = false;
				player.stopMoving();
			}
			if (code.equals(KeyCode.D) || code.equals(KeyCode.RIGHT)) {
				moveRight = false;
				player.stopMoving();
			}
			if (code.equals(KeyCode.SHIFT)) {
				player.walk();
			}
		});

		gameloop = new AnimationTimer() {

//			double drawInterval = 1000000000/FPS;
//			double delta = 0;
//			long lastTime = System.nanoTime();
//			
			@Override
			public void handle(long currentTime) {
//				currentTime = System.nanoTime();
//				
//				delta += (currentTime - lastTime) / drawInterval;
//				
//				lastTime = currentTime;
//				
//				
//				if (delta >= 2) {
						update();
						paintMap();
						drawPlayer();
					
//					delta--;
//				}
			}
		};

		gameloop.start();

	}

	private void paintMap() {
		tileM.drawMap(gc);
	}

	private void drawPlayer() {
		player.drawPlayer(gc);
//		encounter();
	}

//	private void encounter() {
//		if (playerX > 16 * 64 || playerX < 15 * 64) {
//			int getal = new SecureRandom().nextInt(10);
//			if (getal == 0 && nextScreen == false) {
//				nextScreen = true;
//				gameloop.stop();
//				ScreenController.changeToSpelScherm(this, rs, new DomeinController(), worldX, worldY);
//			}
//		}
//	}

	public boolean update() {
		player.update(moveUp, moveDown, moveLeft, moveRight);
		if (moveUp || moveDown || moveLeft || moveRight) {
			return true;
		}

		return false;
	}

}
