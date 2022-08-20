package gui;

import domein.DomeinController;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import resources.ResourceController;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Animation screen.
 */
public class AnimationScreen extends BorderPane {

    /**
     * The Number defeated.
     */
    Label damageLbl;
    /**
     * The Sr.
     */
    SecureRandom sr = new SecureRandom();
    /**
     * The Dc.
     */
    DomeinController dc;
    private Button attack;
    private Button allOut;
    private Button judgement;
    private Button fire;
    private Button ice;
    private Button lightning;
    private Button wind;
    private Button earth;
    private Button trueMagic;
    private Button run;
    private boolean fullpower = false;
    private boolean judgementAttack = false;
    private List<Button> buttons;
    private int teller = 0;
    private int bodyCount = 0;
    private Label critLbl;
    private AnimationTimer timer;
    private int timerCounter = 0;

    /**
     * Instantiates a new Animation screen.
     *
     * @param dc the DomeinController
     * @param rs the ResourceController
     * @param x  the worldX
     * @param y  the worldY
     */
    public AnimationScreen(DomeinController dc, ResourceController rs, int x, int y) {
        this.dc = dc;
        SpelScherm spel = new SpelScherm(dc, rs, x, y);
        BagScreen bag = new BagScreen(dc);
        this.setCenter(spel);
        this.setRight(bag);
        bag.setVisible(false);


        VBox vbox = new VBox();

        // creating attack buttons
        attack = new Button("Attack");
        allOut = new Button("Full Power");
        if (dc.showFullPowerStage() < 1) {
            allOut.setDisable(true);
        }
        fire = new Button("Fire");
        ice = new Button("Water");
        lightning = new Button("Lightning");
        wind = new Button("Wind");
        earth = new Button("Earth");
        trueMagic = new Button("True Magic");
        if (dc.trueMagicAqcuired() == false) {
            trueMagic.setDisable(true);
        }

        buttons = new LinkedList<>(Arrays.asList(fire, ice, lightning, wind, earth, trueMagic));
        if (dc.judgementAcquired()) {
            judgement = new Button("Judgement");
            buttons.add(judgement);
        }

        changeVisibiltyButtons(false);

        // action events for every attack button
        attack.setOnAction(event -> {

            attack.setVisible(false);
            changeVisibiltyButtons(true);

            allOut.setOnAction(evt0 -> {
                if (fire.getId() == null) {
                    fullpower = true;
                    changeIdButtons("power");
                    spel.changeWizard(new Image(getClass().getResourceAsStream("/images/powerWizard.gif")), fullpower);

                } else {
                    fullpower = false;
                    changeIdButtons(null);
                    spel.changeWizard(new Image(this.getClass().getResourceAsStream("/images/Wizard Stand.gif")),
                            fullpower);
                }

            });

            for (Button button : buttons) {

                button.setOnAction(evt -> {

                    if (button == judgement) {
                        judgementAttack = true;
                    }

                    teller += 2;
                    spel.attackEvent(button.getText(), fullpower, judgementAttack);
                    updateDamageLbl();
                    if (spel.enemyDefeated()) {
                        changeVisibiltyButtons(false);
                        attack.setVisible(true);
                        run.setVisible(true);
                        teller = 0;
                        timer = new AnimationTimer() {
                            @Override
                            public void handle(long l) {
                                timerCounter++;
                                if (timerCounter == 150) {
                                    critLbl.setText("");
                                    damageLbl.setText("You have successfully defeated the enemy!\n\t\tReturning ...");
                                }
                                if (timerCounter == 250) {
                                    if (dc.bagIsFull()) {
                                        ScreenController.changeToGamePanel(AnimationScreen.this, rs, x, y, dc, "Bag is full, some items were left behind");
                                    } else {
                                        ScreenController.changeToGamePanel(AnimationScreen.this, rs, x, y, dc, null);
                                    }
                                }
                            }
                        };

                        timer.start();

                    }
                    changeIdButtons(null);
                    judgementAttack = false;
                    fullpower = false;

                });
            }
        });

        // alignment for vbox, vbox is left side of the screen
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        VBox.setMargin(allOut, new Insets(20, 0, 0, 0));
        vbox.getChildren().addAll(fire, ice, lightning, wind, earth, trueMagic);
        if (dc.judgementAcquired()) {
            vbox.getChildren().add(judgement);
        }
        vbox.getChildren().add(allOut);

        this.setLeft(vbox);

        // main buttons, bottom of the screen
        HBox hbox = new HBox();
        run = new Button("Run");
        Button bagButton = new Button("Bag");

        run.setOnAction(evt -> {
            int getal = sr.nextInt(20 - teller);
            if (getal == 0) {
                run.setVisible(false);
                critLbl.setText("");
                damageLbl.setText("You have failed to run away!");
            } else {
                changeVisibiltyButtons(false);
                attack.setVisible(true);
                changeIdButtons(null);
                fullpower = false;
                teller = 0;
                run.setVisible(false);
                timer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        if (timerCounter == 0) {
                            critLbl.setText("");
                            damageLbl.setText("You have successfully run away!");
                        }

                        timerCounter++;
                        if (timerCounter == 100) {
                            ScreenController.changeToGamePanel(AnimationScreen.this, rs, x, y, dc, null);
                        }
                    }
                };

                timer.start();
            }

        });
        bagButton.setOnAction(evt -> {
            if (bag.isVisible()) {
                bag.setVisible(false);
            } else {
                bag.setVisible(true);
                if (bag.potionUsed()) {
                    spel.updateMana(dc.getManapool());
                }
            }

        });

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.getChildren().addAll(attack, run, bagButton);
        this.setBottom(hbox);

        // text, top of the screen
        HBox hbox2 = new HBox();
        critLbl = new Label();
        critLbl.setId("crit");
        critLbl.setTextFill(Color.RED);
        damageLbl = new Label();
        damageLbl.setId("damage");
        hbox2.getChildren().addAll(critLbl, damageLbl);
        hbox2.setAlignment(Pos.BOTTOM_CENTER);
        hbox2.setSpacing(20);
        BorderPane.setMargin(hbox2, new Insets(20, 0, 0, 0));
        this.setTop(hbox2);

    }

    private void updateDamageLbl() {
        if (dc.isCriticalHit()) {
            critLbl.setText("Critical hit!");
        } else {
            critLbl.setText("");
        }
        damageLbl.setText(dc.showDamage());
    }

    private void changeVisibiltyButtons(boolean visible) {
        allOut.setVisible(visible);
        for (Button button : buttons) {
            button.setVisible(visible);
        }
    }

    private void changeIdButtons(String id) {
        for (Button button : buttons) {
            button.setId(id);
        }
    }

}
