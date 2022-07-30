package gui;

import domein.DomeinController;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MerchantDialogue extends GridPane {

   private DomeinController dc;
   private GamePanel gp;
   private Label content = new Label();

   private GridPane shop = new GridPane();
    public MerchantDialogue(DomeinController dc, GamePanel gamePanel) {

        this.dc = dc;
        this.gp = gamePanel;
        content.setText("Welcome to my humble shop!");
        updateShop();

        this.add(content,0,0);
        GridPane.setHalignment(content, HPos.CENTER);
        this.add(shop,0,1);
        GridPane.setHalignment(shop,HPos.CENTER);
    }

    private void updateShop(){



    }
}
