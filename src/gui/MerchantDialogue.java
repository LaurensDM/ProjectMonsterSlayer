package gui;

import domein.DomeinController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.List;

public class MerchantDialogue extends GridPane {

   private DomeinController dc;
   private GamePanel gp;
    private Label content = new Label();
    private Button sellStones = new Button("Sell Magic Stones");
    private GridPane shop = new GridPane();
    private List<String> stock;
    public MerchantDialogue(DomeinController dc, GamePanel gamePanel) {

        this.dc = dc;
        this.gp = gamePanel;
        this.setAlignment(Pos.CENTER);
        this.setId("Dialogue");
        content.setText("Welcome to my humble shop!");
        configureShop();

        this.add(content, 0, 0);
        GridPane.setHalignment(content, HPos.CENTER);
        this.add(shop, 0, 1);
        GridPane.setHalignment(shop, HPos.CENTER);
        this.add(sellStones, 0, 2);
        GridPane.setHalignment(sellStones, HPos.RIGHT);

        sellStones.setOnAction(evt -> {
            dc.convertStonesToMoney();
        });

    }

    private void configureShop() {
        int counter = 0;
        stock = dc.getMerchantStock();
        for (String item : stock) {
            Label itemLbl = new Label(item);
            Tooltip tooltip = new Tooltip("This item costs " + dc.getPriceItem(item) + " gold");
            tooltip.setShowDelay(Duration.ZERO);
            tooltip.setHideDelay(Duration.ZERO);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setHideOnEscape(true);
            itemLbl.setTooltip(tooltip);
            shop.add(itemLbl, 0, counter);
            counter++;
        }

    }

    public void updateShop() {


    }
}
