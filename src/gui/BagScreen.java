package gui;


import domein.DomeinController;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Bag screen.
 */
public class BagScreen extends GridPane {


    private List<String> bag;
    private int column = 0;
    private int row = 1;
    private Label error;
    private DomeinController dc;
    private List<Button> lblList = new LinkedList<>();
    private boolean potionUsed = false;

    /**
     * Instantiates a new Bag screen.
     */
    public BagScreen(DomeinController dc) {
        this.dc = dc;
        this.setId("bag");
//		this.setVgap(10);

        bag = dc.giveBag();

        String content = "";

        error = new Label("");
        GridPane.setColumnSpan(error, 2);
        GridPane.setHalignment(error, HPos.CENTER);
        this.add(error, 0, 0);

        for (int counter = 0; counter < 34; counter++) {
            Tooltip tooltip = null;
            try {
                content = bag.get(counter);
                tooltip = new Tooltip(giveItemInformation(bag.get(counter)));
                tooltip.setShowDelay(Duration.ZERO);
                tooltip.setHideDelay(Duration.ZERO);
                tooltip.setShowDuration(Duration.INDEFINITE);
                tooltip.setHideOnEscape(true);
            } catch (IndexOutOfBoundsException e) {
                content = "	";
            }

            Button bagLbl = new Button(content);
            if (tooltip != null) bagLbl.setTooltip(tooltip);

            lblList.add(bagLbl);

            bagLbl.setOnAction(evt -> {
                potionUsed = false;
                try {
                    String confirmation = dc.selectItem(bagLbl.getText(), GamePanel.inGame);
                    error.setText(confirmation);
                    potionUsed = true;
                    update();
                } catch (IllegalArgumentException e) {
                    error.setText(e.getLocalizedMessage());
                }

            });

            this.add(bagLbl, column, row);
            row++;
            if (counter == 16) {
                column++;
                row = 1;
            }
        }


    }

    public boolean potionUsed() {
        boolean bool = potionUsed;
        potionUsed = false;
        return bool;
    }

    public void update() {

        String content = "";
        bag = dc.giveBag();

        for (int counter = 0; counter < 34; counter++) {

            Tooltip tooltip = null;
            try {
                content = bag.get(counter);
                tooltip = new Tooltip(giveItemInformation(bag.get(counter)));
                tooltip.setShowDelay(Duration.ZERO);
                tooltip.setHideDelay(Duration.ZERO);
                tooltip.setShowDuration(Duration.INDEFINITE);
                tooltip.setHideOnEscape(true);
            } catch (IndexOutOfBoundsException e) {
                content = "	";
            }

            lblList.get(counter).setText(content);
            if (tooltip != null) lblList.get(counter).setTooltip(tooltip);


        }
    }

    private String giveItemInformation(String item) {
        String info = "";

        if (item.contains("Magic Stone")) {
            info = "This item can be sold to a merchant for a nice price!";
        }

        if (item.contains("Power Potion") || item.contains("Blood")) {
            info = "This item will boost your power";

            if (item.contains("Uncommon")) {

            }

            if (item.contains("Rare")) {

            }

            if (item.contains("Epic")) {

            }

            if (item.contains("Legendary")) {

            }

        }
        if (item.contains("Mana Potion") || item.contains("Essence")) {
            info = "This item will restore your mana by ";

            if (item.contains("Common")) {
                info += "100 mana points.";
            }
            if (item.contains("Uncommon")) {
                info += "200 mana points.";
            }

            if (item.contains("Rare")) {
                info += "25 percent.";
            }

            if (item.contains("Epic")) {
                info += "40 percent.";
            }

            if (item.contains("Legendary")) {
                info += "70 percent";
            }
            if (item.contains("Mythical")) {
                info = "This item will fully restore your mana";
            }

        }
        if (item.contains("Armor") || item.contains("Robe")) {
            info = "This armor will give you more protection";

            if (item.contains("Uncommon")) {

            }

            if (item.contains("Rare")) {

            }

            if (item.contains("Epic")) {

            }

            if (item.contains("Legendary")) {

            }

        }
        if (item.contains("Staff")) {
            info = "This staff will enhance your magic power";

            if (item.contains("Uncommon")) {

            }

            if (item.contains("Rare")) {

            }

            if (item.contains("Epic")) {

            }

            if (item.contains("Legendary")) {

            }

        }

        return info;
    }

}
