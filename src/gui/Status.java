package gui;

import domein.DomeinController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Status extends GridPane {

    private DomeinController dc;
    private Label name = new Label();
    private Label manaPool = new Label();
    private Label level = new Label();
    private Label exp = new Label();
    private Label money = new Label();
    private Label rank = new Label();
    private Label weaponDurability = new Label();
    private Label weaponName = new Label();
    private Label armorDurability = new Label();
    private Label armorName = new Label();

    public Status(DomeinController dc) {
        this.dc = dc;

        buildGui();
    }

    private void buildGui() {
        this.setId("status");
        this.setAlignment(Pos.CENTER);
        this.setVgap(25);
        this.setHgap(25);
        Label title = new Label("STATUS");
        GridPane.setColumnSpan(title, 4);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(title, VPos.TOP);
        this.add(title, 0, 0);

        Label nameLbl = new Label("Player Name: ");
        this.add(nameLbl, 1, 1);
        name.setText(dc.getPlayerName());
        this.add(name, 2, 1);

        Label manaLbl = new Label("Mana: ");
        this.add(manaLbl, 1, 4);
        manaPool.setText(String.format("%.0f / %.0f", dc.getManapool(), dc.getMaxMana()));
        this.add(manaPool, 2, 4);

        Label levelLbl = new Label("Level: ");
        this.add(levelLbl, 1, 2);
        level.setText(dc.getPlayerLevel() + "");
        this.add(level, 2, 2);

        Label expLbl = new Label("EXP: ");
        this.add(expLbl, 1, 3);
        exp.setText(String.format("%d/%d", dc.getPlayerExp(), dc.getPlayerMaxExp()));
        this.add(exp, 2, 3);

        Label moneyLbl = new Label("Money: ");
        this.add(moneyLbl, 1, 5);
        money.setText(dc.getPlayerMoney() + "");
        this.add(money, 2, 5);

        Label rankLbl = new Label("Adventure Rank: ");
        this.add(rankLbl, 1, 6);
        rank.setText(dc.getPlayerRank());
        this.add(rank, 2, 6);

        try {
            Label weaponLbl = new Label("Equipped Weapon: ");
            this.add(weaponLbl, 0, 7);
            weaponName.setText(dc.getWeaponDetails());
            this.add(weaponName, 1, 7);
            weaponDurability.setText(dc.getWeaponDurability() + "");
            this.add(weaponDurability, 2, 7);
        } catch (NullPointerException e) {
            weaponName.setText("No weapon equipped");
            this.add(weaponName, 1, 7);
            weaponDurability.setText("");
            this.add(weaponDurability, 2, 7);
        }

        Button weaponUnequip = new Button("Unequip");
        this.add(weaponUnequip, 3, 7);


        try {
            Label armorLbl = new Label("Equipped Armor: ");
            this.add(armorLbl, 0, 8);
            armorName.setText(dc.getArmorDetails());
            this.add(armorName, 1, 8);
            armorDurability.setText(dc.getArmorDurability() + "");
            this.add(armorDurability, 2, 8);
        } catch (NullPointerException e) {
            armorName.setText("No armor equipped");
            this.add(armorName, 1, 8);
            armorDurability.setText("");
            this.add(armorDurability, 2, 8);
        }

        Button armorUnequip = new Button("Unequip");
        this.add(armorUnequip, 3, 8);

        try {
            dc.getWeaponDetails();
            weaponUnequip.setVisible(true);
        } catch (NullPointerException e) {
            weaponUnequip.setVisible(false);
        }

        try {
            dc.getArmorDetails();
            armorUnequip.setVisible(true);
        } catch (NullPointerException e) {
            armorUnequip.setVisible(false);
        }

        weaponUnequip.setOnAction(evt -> {
            dc.unequipWeapon();
            weaponUnequip.setVisible(false);
            update();
        });

        armorUnequip.setOnAction(evt -> {
            dc.unequipArmor();
            armorUnequip.setVisible(false);
            update();
        });
    }

    public void update() {
        manaPool.setText(String.format("%.0f / %.0f", dc.getManapool(), dc.getMaxMana()));
        level.setText(dc.getPlayerLevel() + "");
        exp.setText(String.format("%d / %d", dc.getPlayerExp(), dc.getPlayerMaxExp()));
        money.setText(dc.getPlayerMoney() + "");
        rank.setText(dc.getPlayerRank());
        try {
            weaponName.setText(dc.getWeaponDetails());
            weaponDurability.setText(dc.getWeaponDurability() + "");
        } catch (NullPointerException e) {
            weaponName.setText("No weapon equipped");
            weaponDurability.setText("");
        }

        try {
            armorName.setText(dc.getArmorDetails());
            armorDurability.setText(dc.getArmorDurability() + "");
        } catch (NullPointerException e) {
            armorName.setText("No armor equipped");
            armorDurability.setText("");
        }


    }
}
