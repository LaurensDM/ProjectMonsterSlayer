package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import resources.HashClass;
import resources.ResourceController;

import java.util.Arrays;

public class RegisterScreen extends GridPane {

    private DomeinController dc;
    private ResourceController rs;

    public RegisterScreen(DomeinController dc, ResourceController rs) {
        this.dc = dc;
        this.rs = rs;

        buildGui();
    }

    private void buildGui() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(5);
        this.setVgap(25);
        Label title = new Label("Create new player");
        GridPane.setRowSpan(title, 2);
        GridPane.setColumnSpan(title, 4);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(title, VPos.TOP);
        GridPane.setMargin(title, new Insets(0, 0, 100, 0));
        title.setId("title");
        this.add(title, 0, 0);

        Label name = new Label("Name: ");
        TextField nameTxt = new TextField();
        nameTxt.setPromptText("Type a username here");
        this.add(name, 1, 2);
        this.add(nameTxt, 2, 2);

        Label password = new Label("Password: ");
        TextField passwordTxt = new TextField();
        passwordTxt.setPromptText("Type a password here");
        Tooltip passwordTip = new Tooltip("The password must contain at least 3 numbers");
        passwordTxt.setTooltip(passwordTip);
        this.add(password, 1, 3);
        this.add(passwordTxt, 2, 3);

        Label affinity = new Label("Choose an affinity: ");
        ComboBox affinityBox = new ComboBox<>(FXCollections.observableArrayList(Arrays.asList("Fire", "Water", "Lightning", "Earth", "Wind")));
        affinityBox.setPromptText("Choose an element");
        this.add(affinity, 1, 4);
        this.add(affinityBox, 2, 4);

        Button register = new Button("Register Player");
        Button back = new Button("Return");
        this.add(register, 1, 5);
        this.add(back, 2, 5);

        register.setOnAction(evt -> {
            String nameString = nameTxt.getText();
            String salt = HashClass.getSalt();
            String passwordHash = HashClass.securePassword(passwordTxt.getText(), salt);
            String affinityString = (String) affinityBox.getSelectionModel().getSelectedItem();
            try {
                verifyPassword(passwordTxt.getText());
                dc.registerPlayer(nameString, passwordHash, salt, affinityString);
                nameTxt.clear();
                passwordTxt.clear();
            } catch (IllegalArgumentException ie) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText(ie.getLocalizedMessage());
                error.showAndWait();
            }

        });

        back.setOnAction(evt -> {
            ScreenController.changeToSelectScreen(this, rs, dc);
        });

    }

    private void verifyPassword(String password) {
        if (password == null || password.length() < 3 || !password.matches("(\\w*\\d{1}){3,}")) {
            throw new IllegalArgumentException(
                    "Uw wachtwoord moet uit minstens 3 characters bestaan en minstens 2 cijfers bevatten!");
        }
    }
}
