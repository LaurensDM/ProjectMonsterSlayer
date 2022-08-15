package gui;

import domein.DomeinController;
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
import resources.*;

/**
 * The type Game panel.
 */
public class GamePanel extends StackPane {

    /**
     * The constant maxScreenCol.
     */
    public static final int maxScreenCol = 16;
    /**
     * The constant maxScreenRow.
     */
    public static final int maxScreenRow = (int) Math.ceil(maxScreenCol / WelcomeScreen.rowMultiplier);
    /**
     * The constant TILESIZE.
     */
    public final static int TILESIZE = ScreenController.screenWidth / maxScreenCol;
    public static boolean inGame = true;
    /**
     * The Screen width.
     */
// SCREEN
    public final int screenWidth;
    /**
     * The Screen height.
     */
    public final int screenHeight;
    /**
     * The Max world col.
     */
// WORLD parameters
    public final int maxWorldCol = 50;
    /**
     * The Max world row.
     */
    public final int MaxWorldRow = 50;
    /**
     * The World width.
     */
    public final int worldWidth = TILESIZE * maxWorldCol;
    /**
     * The World height.
     */
    public final int worldHeight = TILESIZE * MaxWorldRow;
    /**
     * The Fps.
     */
    final int FPS = 60;
    /**
     * The Initial x.
     */
//	public UI ui = new UI(this);
    final int initialX;
    /**
     * The Initial y.
     */
    final int initialY;
    /**
     * The ResourceController.
     */
    public ResourceController rs;
    /**
     * The DomeinController.
     */
    public DomeinController dc;
    /**
     * Interact.
     */
    public boolean interact = false;
    /**
     * The Gameloop.
     */
//	boolean nextScreen = false;
    public AnimationTimer gameloop;
    /**
     * The Tile manager.
     */
// OTHER
    public TileManager tileM;
    /**
     * The Collision.
     */
    public CollisionChecker collision;
    /**
     * The Setter.
     */
    public AssetSetter setter;
    /**
     * The Player.
     */
// ENTITY AND OBJECT
    public Player player;
    /**
     * The Objects.
     */
    public SuperObject obj[] = new SuperObject[10];
    /**
     * The Npc's.
     */
    public Entity npc[] = new Entity[10];
    /**
     * The Canvas.
     */
    Canvas canvas;
    String initialNotification;
    /**
     * The Label.
     */
// UI
    Label label;
    private GraphicsContext gc;
    // booleans
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean pause = false;
    private VBox bottomUI = new VBox();
    private VBox topUI = new VBox();
    private VBox notification = new VBox();
    private int messageCounter;
    private GridPane dialogue;
    private BagScreen inventory;
    private SettingScreen settings;
    private Status status;
    private SageDialogue sageDialogue;
    private SkillPanel skills;
    private MerchantDialogue merchantDialogue;
    private GuildDialogue guildDialogue;
    private RandomNPCDialogue randomDialogue;

    private int manaCounter = 0;


    /**
     * Instantiates a new Game panel.
     *
     * @param x  the worldX
     * @param y  the worldY
     * @param rs the ResourceController
     * @param dc the DomeinController
     */
    public GamePanel(int x, int y, ResourceController rs, DomeinController dc, String notification) {

        this.rs = rs;
        this.dc = dc;
        initialX = x;
        initialY = y;
        initialNotification = notification;
        screenWidth = maxScreenCol * TILESIZE;
        screenHeight = maxScreenRow * TILESIZE + TILESIZE;
        buildGui();
    }

    private void buildGui() {

        //CREATE OBJECTS
        canvas = new Canvas(screenWidth, screenHeight);
        gc = canvas.getGraphicsContext2D();
        player = new Player(this, initialX, initialY);
        tileM = new TileManager(this);
        tileM.createMap("world01");
//		tileM.generateRandomMap();
//      tileM.generateMapFromImage(new Image(getClass().getResourceAsStream("/files/mapV1.png")));
        collision = new CollisionChecker(this);
        setter = new AssetSetter(this);
        canvas.setFocusTraversable(true);
        settings = new SettingScreen(dc, rs, true);
        status = new Status(dc);
        //GAME AND UI SETUP
        setUpGame();


        this.getChildren().addAll(canvas, bottomUI, topUI, notification, dialogue, inventory, status, settings);

        //KEYEVENTS
        this.setOnKeyPressed(evt -> {
            KeyCode code = evt.getCode();
            if (code.equals(SettingScreen.keyCodes.get(0)) || code.equals(KeyCode.UP))
                moveUp = true;
            if (code.equals(SettingScreen.keyCodes.get(1)) || code.equals(KeyCode.DOWN))
                moveDown = true;
            if (code.equals(SettingScreen.keyCodes.get(2)) || code.equals(KeyCode.LEFT))
                moveLeft = true;
            if (code.equals(SettingScreen.keyCodes.get(3)) || code.equals(KeyCode.RIGHT))
                moveRight = true;
            if (code.equals(KeyCode.SHIFT))
                player.sprint();
            if (code.equals(KeyCode.R)) {
                notification.setVisible(true);
            }
            if (code.equals(KeyCode.E)) {
                interact = true;

                if (this.getChildren().get(4).isVisible()) {
                    this.getChildren().get(4).setVisible(false);
                    pause = false;

                }
            }

            if (code.equals(KeyCode.ESCAPE)) {
                if (settings.isVisible()) {
                    settings.setVisible(false);
                    pause = false;
                } else {
                    settings.setVisible(true);
                    pause = true;
                }

            }

            if (code.equals(KeyCode.I)) {
                if (inventory.isVisible()) {
                    inGame = true;
                    inventory.setVisible(false);
                } else {
                    inGame = false;
                    inventory.update();
                    inventory.setVisible(true);
                }
            }

            if (code.equals(KeyCode.K)) {
                if (status.isVisible()) status.setVisible(false);
                else {
                    status.update();
                    status.setVisible(true);
                }
            }
        });


//KEY RELEASE EVENTS
        this.setOnKeyReleased(evt -> {
            KeyCode code = evt.getCode();
            if (code.equals(SettingScreen.keyCodes.get(0)) || code.equals(KeyCode.UP)) {
                moveUp = false;
                player.stopMoving();
            }
            if (code.equals(SettingScreen.keyCodes.get(1)) || code.equals(KeyCode.DOWN)) {
                moveDown = false;
                player.stopMoving();
            }
            if (code.equals(SettingScreen.keyCodes.get(2)) || code.equals(KeyCode.LEFT)) {
                moveLeft = false;
                player.stopMoving();
            }
            if (code.equals(SettingScreen.keyCodes.get(3)) || code.equals(KeyCode.RIGHT)) {
                moveRight = false;
                player.stopMoving();
            }
            if (code.equals(KeyCode.SHIFT)) {
                player.walk();
            }
        });


        //GAMELOOP
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
                if (pause == false) {
                    update();
                    drawGameScreen();
                    hideNotification();
                    manaTimer();
                    if (interact) {
                        interact = false;
                    }

                }

                if (settings.returnPressed() == true) {
                    if (pause == true) {
                        pause = false;
                    }
                }

//					delta--;
//				}
            }
        };

        gameloop.start();

    }

    private void manaTimer() {
        manaCounter++;
        if (manaCounter == 250) {
            dc.regenerateMana();
            manaCounter = 0;
        }

    }

    //DRAW GAME ON SCREEN
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

    //SETUP OBJECTS AND NPC
    private void setUpGame() {
        setter.setObject();
        setter.setNPC();

        drawGameScreen();
        configureTopUI();
        configureNotification();
        configureBottomUI();
        configureDialogue();
        configureStatus();
        configureSettings();
        configureInventory();
    }

    private void configureStatus() {
        status.setMaxWidth(0.3 * screenWidth);
        status.setMaxHeight(0.7 * screenHeight);
        status.setVisible(false);
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

    /**
     * Update.
     *
     * @return whether an update is needed
     */
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

    private void configureNotification() {
        String content = "Press ESC to show settings";

        if (initialNotification != null) {
            content = initialNotification;
        }

        Label noti = new Label(content);
        HBox hbox = new HBox();

        hbox.getChildren().add(noti);
        hbox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(noti, new Insets(0, 0, 0, TILESIZE));
        notification.getChildren().add(hbox);
        notification.setAlignment(Pos.CENTER_LEFT);
        notification.setVisible(true);

    }

    /**
     * Show notification.
     *
     * @param content the content
     */
    public void showNotification(String content) {
        HBox hbox = (HBox) notification.getChildren().get(0);
        Label label = (Label) hbox.getChildren().get(0);
        label.setText(content);

        notification.setVisible(true);
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

    /**
     * Show dialogue.
     */
    public void showDialogue() {
//		Node label = dialogue.getChildren().get(0);
//		((Label) label).setText(content);
        this.getChildren().get(4).setVisible(true);
        pause = true;
    }

    /**
     * Configure dialogue.
     */
    public void configureDialogue() {
        dialogue = new SkillPanel(dc, this);
        dialogue.setId("dialogue");
        dialogue.setMaxWidth(screenWidth * 0.6);
        dialogue.setMaxHeight(screenHeight * 0.3);
        dialogue.setAlignment(Pos.CENTER);
        StackPane.setMargin(dialogue, new Insets(10, 0, 10, 0));
        dialogue.setVisible(false);
        sageDialogue = new SageDialogue(dc, this);
        skills = new SkillPanel(dc, this);
        merchantDialogue = new MerchantDialogue(dc, this);
        guildDialogue = new GuildDialogue(dc, this);
        randomDialogue = new RandomNPCDialogue(dc, this);
    }

    public void changeDialogue(int option) {
        switch (option) {
            case 0 -> this.getChildren().set(4, sageDialogue);
            case 1 -> this.getChildren().set(4, skills);
            case 2 -> this.getChildren().set(4, merchantDialogue);
            case 3 -> this.getChildren().set(4, guildDialogue);
            case 4 -> this.getChildren().set(4, randomDialogue);
        }
        GridPane grid = (GridPane) this.getChildren().get(4);
        grid.setId("dialogue");
        grid.setMaxWidth(screenWidth * 0.6);
        grid.setMaxHeight(screenHeight * 0.3);
        grid.setAlignment(Pos.CENTER);
    }

    /**
     * Configure Inventory.
     */
    private void configureInventory() {
        inventory = new BagScreen(dc);
        inventory.setMaxWidth(screenWidth * 0.2);
        inventory.setMaxHeight(screenHeight * 0.8);
        StackPane.setAlignment(inventory, Pos.CENTER_RIGHT);
        inventory.setVisible(false);
    }

    /**
     * Show bag.
     */
    public void showBag() {
        inventory.setVisible(true);
    }

    private void configureBottomUI() {
        label = new Label("Exploring the world");

        bottomUI.getChildren().add(label);
        bottomUI.setAlignment(Pos.BOTTOM_CENTER);

        Node label = bottomUI.getChildren().get(0);

        ((Label) label).setText("Changed message!");

    }

    private void configureSettings() {
        // TODO Auto-generated method stub
        settings.setId("settings");
        settings.setMaxWidth(0.8 * screenWidth);
        settings.setMaxHeight(0.8 * screenHeight);
        settings.setVisible(false);
    }
}
