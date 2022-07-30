package gui;

import domein.DomeinController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SageDialogue extends GridPane {

    private DomeinController dc;
    private GamePanel gp;
    private Button skills = new Button("LEARN SKILLS");
    private Label content = new Label();
    private final List<String> dialogueOptions = new ArrayList<>();

    SecureRandom sr = new SecureRandom();

    public SageDialogue(DomeinController dc, GamePanel gp) {

        this.dc = dc;
        this.gp = gp;

        Collections.addAll(dialogueOptions,"I was once a traveller like you.\nAlthough I can no longer adventure like you.\nMy knowledge could prove useful","A good day to you!",
                "Would you be interested in learning some skills?","You'd best learn some skills if you don't want to die!");

        update();

        if (dc.getPlayerLevel()>=50){
            content.setText("I have nothing left to teach you...");
        }

        skills.setOnAction(evt -> gp.changeDialogue(1));

        this.add(content,0,0);
        this.add(skills,1,1);

    }

    public void update(){
        content.setText(dialogueOptions.get(sr.nextInt(dialogueOptions.size())));
    }
}
