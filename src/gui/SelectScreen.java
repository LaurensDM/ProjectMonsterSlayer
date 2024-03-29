package gui;

import domein.DomeinController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import resources.ResourceController;

/**
 * The type Select screen.
 */
public class SelectScreen extends GridPane{

	private Label lblMessage;
	private TextField txfUser;
	private PasswordField passwordLbl;
	private String naam;
	private String password;
	private DomeinController dc;
	private ResourceController rs;

	/**
	 * Instantiates a new Select screen.
	 *
	 * @param dc the DomeinController
	 * @param rs the ResourceController
	 */
	public SelectScreen(DomeinController dc, ResourceController rs) {
		// deze methode bouwt de gui op
		this.dc=dc;
		this.rs=rs;
		buildGui();
	}

	// gui opbouwen
	private void buildGui() {
		// telkens bij de start van het selecteerscherm moet de lijst met geselecteerde
		// spelers leeg zijn
		// alles links in het midden zetten
		this.setAlignment(Pos.CENTER); // original CENTER_LEFT
		this.setHgap(10);
		this.setVgap(10);

		this.setPadding(new Insets(25, 25, 25, 25));

		Label lblTitle = new Label("Log in");
		lblTitle.setId("title");

		// Titel in de eerste kolom van de eerste rij plaatsen
		this.add(lblTitle, 0, 0, 2, 1);

		// label naam in de eerste kolom van de 2de rij plaatsen
		Label lblUserName = new Label("Username");
		this.add(lblUserName, 0, 1);

		// invoer veld in de 2de kolom van de 2de rij plaatsen
		txfUser = new TextField();
		txfUser.setFocusTraversable(false);
		txfUser.setPromptText("Username");
		this.add(txfUser, 1, 1);

		// label geboorteJaar in de 1ste kolom van de 3de rij plaatsen
		Label lblGeboorteJaar = new Label("Password");
		this.add(lblGeboorteJaar, 0, 2);

		// invoerveld in de 2de kolom van de 3de rij plaatsen
		passwordLbl = new PasswordField();
		passwordLbl.setPromptText("Password");
		passwordLbl.setFocusTraversable(false);
		this.add(passwordLbl, 1, 2);


		Button btnSelecteer = new Button("Select");

		// button uiterst links in de 1ste kolom van de 4de rij zetten
		setHalignment(btnSelecteer, HPos.LEFT);
		this.add(btnSelecteer, 0, 3);

		Button btnAnnuleer = new Button("Cancel");
		// button uiterst rechts in de 2de kolom van de 4de rij zetten
		setHalignment(btnAnnuleer, HPos.RIGHT);
		this.add(btnAnnuleer, 1, 3);

		// Hyperlink inde 1ste kolom van de 6de rij zetten
		Hyperlink linkRegistreer = new Hyperlink("Register a player");
		this.add(linkRegistreer, 0, 5, 2, 1);

		

		// button in de 1ste kolom van de 5de rij zetten
		Button speel = new Button("Play");

		// de button neemt 2 kolommen in beslag
		setColumnSpan(speel, 2);
		speel.setMaxWidth(REMAINING);
		this.add(speel, 0, 6);

		// onderaan komt tekst tevoorschijn naar gelang op welke button gedrukt wordt,
		// kan gebruikt worden voor errors
		lblMessage = new Label();
		this.add(lblMessage, 1, 7);

		// op het opstarten van het scherm is btnSelecteer automatisch geselecteerd, als
		// men dan op spatie drukt wordt txfUser geselecteerd
		btnSelecteer.setOnKeyPressed(evt -> {
			if (evt.getCode() == KeyCode.UP) {
				txfUser.requestFocus();
			}
		});

//		 als men in txtUser zit te typen, bij het drukken van arrow down wordt txfGeboorte geselecteerd
		txfUser.setOnKeyPressed(evt -> {
			if (evt.getCode() == KeyCode.DOWN) {
				passwordLbl.requestFocus();
			}
		});

		// als men in txfGeboorte zit, bij drukken op arrow down wordt de speler
		// geselecteerd, bij arrow up wordt txfUser geselecteerd
		passwordLbl.setOnKeyPressed(arg0 -> {
			if (arg0.getCode() == KeyCode.DOWN) {
				selecteer();
			}

			if (arg0.getCode() == KeyCode.UP) {
				txfUser.requestFocus();
			}
		});

		// bij het drukken op enter wordt het spel gestart
		setOnKeyPressed(evt -> {
			if (evt.getCode() == KeyCode.ENTER) {
				speel();
			}
		});

		btnSelecteer.setOnAction(evt -> {
			selecteer();
		});

		// bij het drukken van de knop gaat men terug naar het WelkomScherm
		btnAnnuleer.setOnAction(evt -> {
			ScreenController.changeToWelcomeScreen(this, dc, rs);
		});

		linkRegistreer.setOnAction(evt -> {
            ScreenController.changeToRegisterScreen(this, dc, rs);
		});

		// Bij het drukken van de knop gaat men naar het ZatreScherm
		speel.setOnAction(evt -> {
			speel();
		});

	}

	private void selecteer() {
		try {
			naam = txfUser.getText();
			password = passwordLbl.getText();
			dc.selectPlayer(naam, password);
			lblMessage.setText(dc.giveDetailsPlayer());
			txfUser.clear();
			passwordLbl.clear();
			txfUser.requestFocus();
		} catch (NumberFormatException ne) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText(ne.getLocalizedMessage());
			error.show();
		} catch (IllegalArgumentException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText(e.getLocalizedMessage());
			DialogPane dialogPane = error.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			error.show();
		}
	}

	private void speel() {
		try {
			dc.startGame();
			ScreenController.changeToGamePanel(this, rs, GamePanel.TILESIZE * 23, GamePanel.TILESIZE * 21, dc, null);
		} catch (IllegalArgumentException ie) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText(ie.getLocalizedMessage());
			error.show();
		}
	}
}
