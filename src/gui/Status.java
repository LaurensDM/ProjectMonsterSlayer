package gui;

import domein.DomeinController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Status extends GridPane {

    private DomeinController dc;

    private Label manaPool = new Label();
    private Label level = new Label();
    private Label exp = new Label();

    public Status(DomeinController dc) {
        this.dc = dc;

        buildGui();
    }

    private void buildGui() {
        this.setId("status");
        this.setAlignment(Pos.CENTER);
        this.setVgap(25);
        Label title = new Label("STATUS");
        this.add(title, 0, 0);

        Label manaLbl = new Label("Mana: ");
        this.add(manaLbl, 0, 3);
        manaPool.setText(dc.getManapool() + " / " + dc.getMaxMana());
        this.add(manaPool, 1, 3);

        Label levelLbl = new Label("Level: ");
        this.add(levelLbl, 0, 1);
        level.setText(dc.getPlayerLevel() + "");
        this.add(level, 1, 1);

        Label expLbl = new Label("EXP: ");
        this.add(expLbl, 0, 2);
        exp.setText(dc.getPlayerExp() + "");
        this.add(exp, 1, 2);
    }

    public void update() {
        manaPool.setText(dc.getManapool() + " / " + dc.getMaxMana());
        level.setText(dc.getPlayerLevel() + "");
        exp.setText(dc.getPlayerExp() + "");
    }
}
