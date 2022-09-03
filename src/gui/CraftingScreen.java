package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import resources.ResourceController;


public class CraftingScreen extends BorderPane {
    private Label craftingLbl1 = new Label("Component");
    private Label craftingLbl2 = new Label("Magic Stone");
    private DomeinController dc;
    private ResourceController rs;
    private BagScreen inventory;
    private Button craftBtn = new Button("Craft");
    private VBox vbox = new VBox();

    public CraftingScreen(DomeinController dc, ResourceController rs) {
        this.dc = dc;
        this.rs = rs;

        buildGui();
    }

    private void buildGui() {
        this.setId("crafting");
        craftingLbl1.setPadding(new Insets(40));
        craftingLbl2.setPadding(new Insets(40));
        craftingLbl1.setMaxWidth(Double.MAX_VALUE);
        craftingLbl2.setMaxWidth(Double.MAX_VALUE);
        craftingLbl1.setAlignment(Pos.CENTER);
        craftingLbl2.setAlignment(Pos.CENTER);
        craftBtn.setPadding(new Insets(2));
        craftBtn.setAlignment(Pos.CENTER);
        inventory = new BagScreen(dc, 1);

        vbox.getChildren().addAll(craftingLbl1, craftingLbl2);
        vbox.setSpacing(50);
        vbox.setAlignment(Pos.CENTER);


        this.setOnMouseMoved(evt -> {
            craftingLbl1.setText(dc.getCraftingComponent1());
            craftingLbl2.setText(dc.getCraftingComponent2());
        });

        craftBtn.setOnAction(evt -> {
            dc.craftItem();
        });

        HBox hbox2 = new HBox();
        hbox2.getChildren().add(craftBtn);
        hbox2.setAlignment(Pos.CENTER);


        this.setCenter(vbox);
        this.setRight(inventory);
        this.setBottom(hbox2);
    }

    public void reset() {
        dc.deselectComponent(craftingLbl1.getText());
        dc.deselectComponent(craftingLbl2.getText());
        craftingLbl1.setText("");
        craftingLbl2.setText("");
        inventory.update();
    }
}
