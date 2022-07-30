package gui;


import java.util.List;
import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * The type Bag screen.
 */
public class BagScreen extends GridPane {



	private List<String> bag;
	private int column=0;
	private int row =0;

	/**
	 * Instantiates a new Bag screen.
	 */
	public BagScreen(DomeinController dc) {

		this.setId("bag");
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(50));
		this.setVgap(10);

		bag = dc.giveBag();

		for (int counter = 0; counter < bag.size(); counter++) {
			Label bagLbl = new Label(bag.get(counter));
			Tooltip tooltip = new Tooltip(giveItemInformation(bag.get(counter)));
			tooltip.setShowDelay(Duration.ZERO);
			tooltip.setHideDelay(Duration.ZERO);
			tooltip.setShowDuration(Duration.INDEFINITE);
			tooltip.setHideOnEscape(true);
			bagLbl.setTooltip(tooltip);

			this.add(bagLbl,column,row);
			row++;
			if (counter==10) {
				column++;
				row=0;
			}
		}



		
	}


	private String giveItemInformation(String item){
		String info="";


		if (item.contains("Power Potion") || item.contains("Blood")){
			info="This item will boost your power";

			if (item.contains("Uncommon")){

			}

			if (item.contains("Rare")){

			}

			if (item.contains("Epic")){

			}

			if (item.contains("Legendary")){

			}

		}
		if (item.contains("Mana Potion") || item.contains("Essence")){
			info = "This item will restore your mana by ";

			if (item.contains("Uncommon")){

			}

			if (item.contains("Rare")){

			}

			if (item.contains("Epic")){

			}

			if (item.contains("Legendary")){

			}

		}
		if (item.contains("Armor")){
			info = "This armor will give you more protection";

			if (item.contains("Uncommon")){

			}

			if (item.contains("Rare")){

			}

			if (item.contains("Epic")){

			}

			if (item.contains("Legendary")){

			}

		}
		if (item.contains("Staff")){
			info = "This staff will enhance your magic power";

			if (item.contains("Uncommon")){

			}

			if (item.contains("Rare")){

			}

			if (item.contains("Epic")){

			}

			if (item.contains("Legendary")){

			}

		}

		return info;
	}

}
