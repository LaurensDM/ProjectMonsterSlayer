package gui;

import entity.Entity;
import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import resources.AssetSetter;
import resources.CollisionChecker;
import resources.ResourceController;
import resources.SuperObject;
import resources.TileManager;

public class GamePanel extends StackPane {

	Canvas canvas;
	private GraphicsContext gc;
	ResourceController rs;
	// booleans
	private boolean moveUp;
	private boolean moveDown;
	private boolean moveLeft;
	private boolean moveRight;

	// SCREEN
	public final int screenWidth;
	public final int screenHeight;
	public static final int maxScreenCol = 16;
	public static final int maxScreenRow = (int) Math.ceil(maxScreenCol / WelcomeScreen.rowMultiplier);
	final int FPS = 60;
	public final static int TILESIZE = ScreenController.screenWidth / maxScreenCol;

//	boolean nextScreen = false;
	AnimationTimer gameloop;

	// WORLD parameters
	public final int maxWorldCol = 50;
	public final int MaxWorldRow = 50;
	public final int worldWidth = TILESIZE * maxWorldCol;
	public final int worldHeight = TILESIZE * MaxWorldRow;

	// OTHER
	public TileManager tileM;
	public CollisionChecker collision;
	public AssetSetter setter;
//	public UI ui = new UI(this);
	final int initialX;
	final int initialY;

	// ENTITY AND OBJECT
	public Player player;
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];

	// UI
	Label label;
	private VBox bottomUI = new VBox();
	private VBox topUI = new VBox();
	private VBox notification = new VBox();
	private int messageCounter;
	private VBox dialogue = new VBox();
	private GridPane bag = new GridPane();

	public GamePanel(double width, double height, int x, int y, ResourceController rs) {

		this.rs = rs;
		initialX = x;
		initialY = y;
		screenWidth = maxScreenCol * TILESIZE;
		screenHeight = maxScreenRow * TILESIZE;
		buildGui();
	}

	private void buildGui() {

		canvas = new Canvas(screenWidth, screenHeight);
		gc = canvas.getGraphicsContext2D();
		player = new Player(this, initialX, initialY);
		tileM = new TileManager(this);
		tileM.createMap("world01");
		collision = new CollisionChecker(this);
		setter = new AssetSetter(this);
		canvas.setFocusTraversable(true);

		setUpGame();

		drawGameScreen();

		configureTopUI();
		showNotification();
		configureBottomUI();
		configureDialogue();

		this.getChildren().addAll(canvas, bottomUI, topUI, notification, dialogue);
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
			if (code.equals(KeyCode.R)) {
				notification.setVisible(true);
			}
			if (code.equals(KeyCode.H)) {
				if (dialogue.isVisible()) {
					dialogue.setVisible(false);
				} else
					dialogue.setVisible(true);
			}
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
				drawGameScreen();
				hideNotification();
//					delta--;
//				}
			}
		};

		gameloop.start();

	}

	private void drawGameScreen() {
		// MAP
		tileM.drawMap(gc);
		// OBJECT
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(gc, this);
			}
		}
		// NPC

		for (int i = 0; i < npc.length; i++) {
			if (npc[i] != null) {
				npc[i].draw(gc);
			}
		}

		// PLAYER
		player.drawPlayer(gc);

		// DRAW UI
//		ui.draw(gc);
	}

	private void setUpGame() {
		setter.setObject();
		setter.setNPC();
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
		for (int i = 0; i < npc.length; i++) {
			if (npc[i] != null) {
				npc[i].update();
			}
		}
		if (moveUp || moveDown || moveLeft || moveRight) {
			return true;
		}

		return false;
	}

	private void configureTopUI() {
		Label label = new Label("Hello There");

		HBox hbox = new HBox(label);
		hbox.setAlignment(Pos.CENTER_LEFT);

		topUI.getChildren().add(hbox);
		topUI.setAlignment(Pos.TOP_LEFT);
	}

	private void showNotification() {
		Label noti = new Label("I will disappear, press R to reappear");
		HBox hbox = new HBox();

		hbox.getChildren().add(noti);
		hbox.setAlignment(Pos.CENTER_LEFT);
		HBox.setMargin(noti, new Insets(0, 0, 0, TILESIZE));
		notification.getChildren().add(hbox);
		notification.setAlignment(Pos.CENTER_LEFT);

	}

	private void hideNotification() {
		if (notification.isVisible()) {

			messageCounter++;

			if (messageCounter == 250) {
				notification.setVisible(false);
				messageCounter = 0;
			}
		}
	}

	public void showDialogue(String content) {

	}

	public void configureDialogue() {
		Label label = new Label("This is a dialogue window!\n Press H to hide or make visible again!");
		dialogue.getChildren().add(label);
		dialogue.setId("dialogue");
		dialogue.setMaxWidth(screenWidth * 0.6);
		dialogue.setMaxHeight(screenHeight * 0.3);
		dialogue.setAlignment(Pos.CENTER);
		StackPane.setMargin(dialogue, new Insets(0, 0, screenHeight / 2 + 10, 0));
		dialogue.setLayoutX(FPS);
	}

	private void fillBag() {

	}

	public void showBag() {

	}

	private void configureBottomUI() {
		label = new Label("Exploring the world");

		bottomUI.getChildren().add(label);
		bottomUI.setAlignment(Pos.BOTTOM_CENTER);

		Node label = bottomUI.getChildren().get(0);

		((Label) label).setText("Changed message!");

	}

}
