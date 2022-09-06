package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.List;

public class MerchantDialogue extends GridPane {

    private DomeinController dc;
    private GamePanel gp;
    private Label content = new Label();
    private Button sellStones = new Button("Repair Weapon");
    private GridPane shop = new GridPane();
    private List<String> stock;
    private BagScreen inventory;
    private BorderPane pane = new BorderPane();

    public MerchantDialogue(DomeinController dc, GamePanel gamePanel) {

        this.dc = dc;
        this.gp = gamePanel;
        inventory = new BagScreen(dc, 2);
        this.setId("dialogue");
        content.setText("Welcome to my humble shop!");
        configureShop();


        pane.setTop(content);
        pane.setCenter(shop);
        pane.setBottom(sellStones);
        pane.setRight(inventory);

        this.add(pane, 0, 0);

        sellStones.setOnAction(evt -> {
            dc.convertStonesToMoney();
        });

    }

    private void configureShop() {
        shop.setAlignment(Pos.CENTER);
        shop.setVgap(25);

        int counter = 0;
        stock = dc.getMerchantStock();
        for (String item : stock) {
            Button itemLbl = new Button(item);
            Tooltip tooltip = new Tooltip("This item costs " + dc.getPriceItem(item) + " gold");
            tooltip.setShowDelay(Duration.ZERO);
            tooltip.setHideDelay(Duration.ZERO);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setHideOnEscape(true);
            itemLbl.setTooltip(tooltip);
            shop.add(itemLbl, 0, counter);
            counter++;
            itemLbl.setMinWidth(150);
            itemLbl.setPadding(new Insets(16));
            GridPane.setMargin(itemLbl, new Insets(0, 10, 0, 0));

            itemLbl.setOnAction(evt -> {
                try {
                    dc.buyItem(item);
                    content.setText("Thank you for purchasing the " + item + ".");
                } catch (IllegalArgumentException ie) {
                    content.setText("You do not have enough gold to buy this item!\nGet your poor hands off of it!");
                }

            });
        }

    }

    public void updateShop() {


    }
}
