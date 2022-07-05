package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

public class BagScreen extends GridPane {

	private List<String> bag = new ArrayList<>(Arrays.asList("item1","item2","item3","item4","item5"));
	
	public BagScreen() {
		this.setBackground(Background.EMPTY);
		this.setAlignment(Pos.CENTER);
		this.add(new Label("OI"), 0, 0);
		
		
		
	}


}
