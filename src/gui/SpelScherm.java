package gui;

import domein.DomeinController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import resources.HealthBar;
import resources.ResourceController;


/**
 * The type Spel scherm.
 */
//Responsable for battle mechanics
public class SpelScherm extends GridPane {

    private DomeinController dc;
    private int worldX;
    private int worldY;
    private String enemy;
    private ResourceController resources;
    private boolean defeated = false;
    private HealthBar healthBarEnemy;
    private HealthBar manaBar;
    private double maxHealth;
    private double maxMana;
    private ImageView wizard;
    private ImageView enemyImg;
    private Label player = new Label();
    private Label lblEnemy = new Label();
    private boolean bagFull = false;

    /**
     * Instantiates a new Spel scherm.
     *
     * @param dc        the DomeinController
     * @param resources the ResourceController
     * @param x         the worldX
     * @param y         the worldY
     */
    public SpelScherm(DomeinController dc, ResourceController resources, int x, int y) {
        this.dc = dc;
        this.resources = resources;
        player.setText(dc.getPlayerName());
        worldX = x;
        worldY = y;
        buildGui();
    }


    private void buildGui() {
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setVgap(0);
        this.setHgap(300);
        this.setId("game");

        wizard = new ImageView(new Image(this.getClass().getResourceAsStream("/images/Wizard Stand.gif")));
        GridPane.setMargin(wizard, new Insets(50, 0, 0, 0));
        wizard.setFitWidth(125);
        wizard.setFitHeight(225);

        enemyImg = new ImageView();
        GridPane.setMargin(enemyImg, new Insets(50, 0, 0, 0));


        GridPane.setHalignment(player, HPos.CENTER);
        GridPane.setHalignment(lblEnemy, HPos.CENTER);

        this.add(wizard, 0, 2);
        this.add(enemyImg, 2, 2);

        this.add(player, 0, 0);
        this.add(lblEnemy, 2, 0);
        event();
    }

    /**
     * Event.
     */
    void event() {
        dc.registerEnemy();
        enemy = dc.getEnemy();
        lblEnemy.setText(dc.getEnemy());

        this.maxHealth = dc.getEnemyHealth();
        this.maxMana = dc.getMaxMana();
        healthBarEnemy = new HealthBar(dc.getEnemyHealth(), maxHealth);
        healthBarEnemy.setId("enemyHealth");
        GridPane.setHalignment(healthBarEnemy, HPos.RIGHT);
        manaBar = new HealthBar(dc.getManapool(), maxMana);
        manaBar.setId("mana");
        GridPane.setHalignment(manaBar, HPos.LEFT);
        this.add(healthBarEnemy, 2, 1);
        this.add(manaBar, 0, 1);

        healthBarEnemy.setVisible(true);
        manaBar.setVisible(true);

        enemy = dc.getEnemy();
        geefImageType(enemy);


    }

    /**
     * Enemy defeated boolean.
     *
     * @return the boolean
     */
    public boolean enemyDefeated() {

        return defeated;
    }

    /**
     * Attack event.
     *
     * @param type      the type
     * @param fullpower the full power
     * @param judgement the judgement
     */
    public void attackEvent(String type, boolean fullpower, boolean judgement) {
        try {
            defeated = false;
            if (fullpower) {
                dc.useAllOutAttack();
                dc.attack(type);
                wizard.setImage(new Image(this.getClass().getResourceAsStream("/images/Wizard Stand.gif")));
                wizard.setFitWidth(125);
                wizard.setFitHeight(225);
            } else {
                if (judgement) {
                    dc.useJudgement();
                }
                dc.attack(type);
            }


            if (dc.outOfMana() && dc.isDefeated() == false) {
                ScreenController.changeToGameOver(this, resources, dc, worldX, worldY);
            }


            if (dc.isDefeated()) {
                    defeated = true;
                } else {
                dc.attackBack();
            }

        } catch (IllegalArgumentException e) {
            System.err.println(e.getLocalizedMessage());
        }
        updateHealth(dc.getEnemyHealth());
        updateMana(dc.getManapool());

    }

    private void updateHealth(double health) {
        if (health <= 0) {
            healthBarEnemy.updateProgress(0);
            return;
        }
        healthBarEnemy.updateProgress(health);
    }

    public void updateMana(double mana) {
        manaBar.updateProgress(mana);
    }

    private void geefImageType(String enemy) {
        String string = enemy.toLowerCase();
        if (string.contains("dragon")) {
            if (string.contains("fire")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/redDragonFlying.gif")));
                adjustSize(300, 400);
                return;
            }
            if (string.contains("water")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/waterDragon.gif")));
                adjustSize(300, 300);
                return;
            }
            if (string.contains("lightning")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/lightningDragon.gif")));
            }
            if (string.contains("wind")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/windDragon.gif")));
                adjustSize(300, 400);
                return;
            }
            if (string.contains("earth")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/earthDragon.gif")));
                adjustSize(300, 400);
                return;
            }
            if (string.contains("gold")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/goldenDragon.gif")));
                adjustSize(300, 350);
            }
            adjustSize(200, 300);
        } else if (string.contains("troll")) {
            if (string.contains("rock")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/troll.gif")));
                adjustSize(300, 400);
                return;
            } else
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/normalTroll.gif")));
            adjustSize(200, 300);
        } else if (string.contains("goblin")) {
            if (string.contains("hob")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/hobgoblin.gif")));
            } else
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/Goblin_idle.gif")));
            adjustSize(100, 200);
        } else if (string.contains("slime")) {

            enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/slime.gif")));
            adjustSize(200, 300);
        } else if (string.contains("golem")) {

            if (string.contains("fire")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/Fire_Golem.gif")));
                adjustSize(300, 400);
                return;
            }
            if (string.contains("water")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/Ice_Golem.gif")));
                adjustSize(300, 300);
                return;
            }
            if (string.contains("lightning")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/Lightning_Golem.gif")));
            }
            if (string.contains("wind")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/Wind_Golem.gif")));
                adjustSize(300, 400);
                return;
            }
            if (string.contains("earth")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/Earth_Golem.gif")));
                adjustSize(300, 400);
                return;
            }
            if (string.contains("ancient")) {
                enemyImg.setImage(new Image(getClass().getResourceAsStream("/images/Ancient_Golem (2).gif")));
                adjustSize(300, 350);
            }
            adjustSize(200, 300);
        }
        enemyImg.setPreserveRatio(false);

    }

    private void adjustSize(double width, double height) {
        enemyImg.maxWidth(width);
        enemyImg.maxHeight(height);
        enemyImg.setFitHeight(height);
        enemyImg.setFitWidth(width);
    }

    /**
     * Change wizard.
     *
     * @param image     the image
     * @param fullpower the full power
     */
    public void changeWizard(Image image, boolean fullpower) {
        wizard.setImage(image);
        if (fullpower) {
            wizard.setFitWidth(225);
            wizard.setFitHeight(350);
        } else {
            wizard.setFitWidth(125);
            wizard.setFitHeight(225);
        }
    }

//	private Image adjustImage(Image image) {
//		PixelReader pixel = image.getPixelReader();
//		WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
//		PixelWriter pixelWriter = wImage.getPixelWriter();
//
//		for (int readY = 0; readY < image.getHeight(); readY++) {
//			for (int readX = 0; readX < image.getWidth(); readX++) {
//				Color color = pixel.getColor(readX, readY);
//
//				if (color.equals(Color.WHITE)) {
//					color = color.TRANSPARENT;
//				}
//				pixelWriter.setColor(readX, readY, color);
//			}
//
//		}
//		return wImage;
//	}

}
