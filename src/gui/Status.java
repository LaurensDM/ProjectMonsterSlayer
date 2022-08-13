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

    private Label manaPool = new Label();
    private Label level = new Label();
    private Label exp = new Label();
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
        Label title = new Label("STATUS");
        GridPane.setColumnSpan(title, 2);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(title, VPos.TOP);
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
        exp.setText(dc.getPlayerExp() + " / " + dc.getPlayerMaxExp());
        this.add(exp, 1, 2);

        try {
            Label weaponLbl = new Label("Equipped Weapon: ");
            this.add(weaponLbl, 0, 4);
            weaponName.setText(dc.getWeaponDetails());
            this.add(weaponName, 1, 4);
            weaponDurability.setText(dc.getWeaponDurability() + "");
            this.add(weaponDurability, 2, 4);
        } catch (NullPointerException e) {
            weaponName.setText("No weapon equipped");
            this.add(weaponName, 1, 4);
            weaponDurability.setText("");
            this.add(weaponDurability, 2, 4);
        }

        Button weaponUnequip = new Button("Unequip");
        this.add(weaponUnequip, 3, 4);


        try {
            Label armorLbl = new Label("Equipped Armor: ");
            this.add(armorLbl, 0, 5);
            armorName.setText(dc.getArmorDetails());
            this.add(armorName, 1, 5);
            armorDurability.setText(dc.getArmorDurability() + "");
            this.add(armorDurability, 2, 5);
        } catch (NullPointerException e) {
            armorName.setText("No armor equipped");
            this.add(armorName, 1, 5);
            armorDurability.setText("");
            this.add(armorDurability, 2, 5);
        }

        Button armorUnequip = new Button("Unequip");
        this.add(armorUnequip, 3, 5);

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
        manaPool.setText(dc.getManapool() + " / " + dc.getMaxMana());
        level.setText(dc.getPlayerLevel() + "");
        exp.setText(dc.getPlayerExp() + "");
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
