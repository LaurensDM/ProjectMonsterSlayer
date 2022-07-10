package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domein.DomeinController;
import javafx.animation.AnimationTimer;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * The type Sage dialogue.
 */
public class SageDialogue extends GridPane {

	private Button lvl1Skill = new Button("Learn");
	private Button lvl5Skill = new Button("Learn");
	private Button lvl10Skill = new Button("Learn");
	private Button lvl15Skill = new Button("Learn");
	private Button lvl20Skill = new Button("Learn");
	private Button lvl25Skill = new Button("Learn");
	private Button lvl30Skill = new Button("Learn");
	private Button lvl35Skill = new Button("Learn");
	private Button lvl40Skill = new Button("Learn");
	private Button lvl45Skill = new Button("Learn");
	private Button lvl50Skill = new Button("Learn");

	private List<Button> skillListBtn = new ArrayList<>();

	private Label lvl1SkillLbl = new Label("Improve mana efficiëncy");
	private Label lvl5SkillLbl = new Label("Improve mana density (power)");
	private Label lvl10SkillLbl = new Label("Full power attack");
	private Label lvl15SkillLbl = new Label("Improve mana efficiëncy II");
	private Label lvl20SkillLbl = new Label("Improve mana density (power) II");
	private Label lvl25SkillLbl = new Label("Full power upgrade");
	private Label lvl30SkillLbl = new Label("Reflection shield");
	private Label lvl35SkillLbl = new Label("Improve mana efficiëncy III");
	private Label lvl40SkillLbl = new Label("Improve mana density (power) III");
	private Label lvl45SkillLbl = new Label("Full power mastery");
	private Label lvl50SkillLbl = new Label("Fusion Magic");

	/**
	 * The list of all labels about skills.
	 */
	List<Label> skillListlbl = new ArrayList<>();

	/**
	 * Label with information.
	 */
	Label informationLbl = new Label();

	private int columnLabel = 0;
	private int columnButton = 1;
	private DomeinController dc;
	/**
	 * The Last row.
	 */
	int lastRow = 0;

	/**
	 * The Timer.
	 */
	AnimationTimer timer;
	private int teller = 0;

	/**
	 * Instantiates a new Sage dialogue.
	 *
	 * @param dc the DomeinController
	 */
	public SageDialogue(DomeinController dc) {
		this.dc = dc;
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(20));
		Collections.addAll(skillListBtn, lvl1Skill, lvl5Skill, lvl10Skill, lvl15Skill, lvl20Skill, lvl25Skill,
				lvl30Skill, lvl35Skill, lvl40Skill, lvl45Skill, lvl50Skill);

		Collections.addAll(skillListlbl, lvl1SkillLbl, lvl5SkillLbl, lvl10SkillLbl, lvl15SkillLbl, lvl20SkillLbl,
				lvl25SkillLbl, lvl30SkillLbl, lvl35SkillLbl, lvl40SkillLbl, lvl45SkillLbl, lvl50SkillLbl);

		for (int i = 0, row = 0; i < skillListBtn.size(); i++, row += 2) {
			if (i == 6) {
				columnButton = 3;
				columnLabel = 2;
				row = 0;
			}
			if (i > 5) {

				GridPane.setMargin(skillListlbl.get(i), new Insets(0, 0, 0, 20));
			}

			this.add(skillListBtn.get(i), columnButton, row);
			this.add(skillListlbl.get(i), columnLabel, row);

			Label label = new Label("");
			label.setVisible(false);
			GridPane.setColumnSpan(label, 2);
			this.add(label, columnLabel, row + 1);

			if (row > lastRow) {
				lastRow = row;
			}
		}

		GridPane.setColumnSpan(informationLbl, 4);
		GridPane.setHalignment(informationLbl, HPos.CENTER);
		this.add(informationLbl, 0, lastRow + 2);
		events();

		informationLbl.setVisible(false);

		for (int level = 5, i = 1; level <= 50; level += 5, i++) {
			if (dc.getPlayerLevel() < level) {
				skillListBtn.get(i).setDisable(true);
			}
		}
	}

	private void events() {

		// BUTTON EVENTS

		lvl1Skill.setOnAction(evt -> {
			resetTimer();
			informationLbl.setVisible(true);
			informationLbl.setText("You have succesfully improved your mana efficiëncy!");
			dc.addSkill(1);
			timer();
		});

		lvl5Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 5) {
				informationLbl.setVisible(true);
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 5!");

			} else {
				dc.addSkill(5);
				informationLbl.setText("You have succesfully improved your mana density!");
			}

			timer();

		});

		lvl10Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 10) {
				informationLbl.setVisible(true);
				informationLbl.setText("");
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 10!");

			} else {
				dc.addSkill(10);
				informationLbl.setText("You have succesfully learned how to unleash a full power attack!");
			}
			timer();
		});

		lvl15Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 15) {
				informationLbl.setVisible(true);
				informationLbl.setText("");
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 15!");

			} else {

				if (dc.showEfficiencyLevel() > 0.8) {
					informationLbl.setText("You need to learn the previous level of this skill first!");
				} else {
					dc.addSkill(15);
					informationLbl.setText("You have succesfully learned the intermediate level of mana efficiëncy!");
				}
			}
			timer();
		});

		lvl20Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 20) {
				informationLbl.setVisible(true);
				informationLbl.setText("");
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 20!");

			} else {
				if (dc.showPowerLevel() < 1.125) {
					informationLbl.setText("You need to learn the previous level of this skill first!");
				} else {
					dc.addSkill(20);
					informationLbl.setText("You have succesfully learned the intermediate level of mana density!");
				}
			}
			timer();
		});

		lvl25Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 25) {
				informationLbl.setVisible(true);
				informationLbl.setText("");
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 25!");

			} else {
				if (dc.showFullPowerStage() < 1) {
					informationLbl.setText("You need to learn the previous level of this skill first!");
				} else {
					dc.addSkill(25);
					informationLbl.setText("You have a deeper understanding of the full power attack!");
				}
			}
			timer();
		});

		lvl30Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 30) {
				informationLbl.setVisible(true);
				informationLbl.setText("");
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 30!");

			} else {
				dc.addSkill(30);
				informationLbl.setText("You have succesfully learned the reflection shield!");
			}
			timer();
		});

		lvl35Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 35) {
				informationLbl.setVisible(true);
				informationLbl.setText("");
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 35!");

			} else {
				if (dc.showEfficiencyLevel() > 0.4) {
					informationLbl.setText("You need to learn the previous level of this skill first!");
				} else {
					dc.addSkill(35);
					informationLbl.setText("You have succesfully learned the advanced level of mana efficiëncy!");
				}
			}
			timer();
		});

		lvl40Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 40) {
				informationLbl.setVisible(true);
				informationLbl.setText("");
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 40!");

			} else {
				if (dc.showPowerLevel() < 1.5) {
					informationLbl.setText("You need to learn the previous level of this skill first!");
				} else {
					dc.addSkill(40);
					informationLbl.setText("You have succesfully learned the advanced level of mana density!");
				}
			}
			timer();
		});

		lvl45Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 45) {
				informationLbl.setVisible(true);
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 45!");

			} else {
				if (dc.showFullPowerStage() < 2) {
					informationLbl.setText("You need to learn the previous level of this skill first!");
				} else {
					dc.addSkill(45);
					informationLbl.setText("You have succesfully mastered the use of the full power attack!");
				}
			}
			timer();
		});

		lvl50Skill.setOnAction(evt -> {
			resetTimer();
			if (dc.getPlayerLevel() < 50) {
				informationLbl.setVisible(true);
				informationLbl.setText(
						"It is too early for you to learn this technique, come back when you've reached level 50!");

			} else {
				if (dc.showEfficiencyLevel() > 0.2 || dc.showFullPowerStage() < 3 || dc.showPowerLevel() < 2
						|| dc.reflectionAcquired() == false) {
					informationLbl.setText("You need to learn all the previous skills first!");
				} else {
					dc.addSkill(50);
					informationLbl.setText("You have succesfully learned Fusion Magic");
				}
				
			}
			timer();
		});

		// lABEL EVENTS

		lvl1SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl1SkillLbl) + 2);
			label.setText("This skill will reduce the amount of mana spent per attack");
			label.setVisible(true);
		});

		lvl1SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl1SkillLbl) + 2);
			label.setVisible(false);
		});

		lvl5SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl5SkillLbl) + 3);
			label.setText("This skill will increase your attack power!");
			label.setVisible(true);
		});

		lvl5SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl5SkillLbl) + 3);
			label.setVisible(false);
		});
		lvl10SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl10SkillLbl) + 4);
			label.setText("This skill will allow you to use an attack with all of your mana!");
			label.setVisible(true);
		});

		lvl10SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl10SkillLbl) + 4);
			label.setVisible(false);
		});
		lvl15SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl15SkillLbl) + 5);
			label.setText("This skill will highly reduce the amount of mana spent per attack!");
			label.setVisible(true);
		});

		lvl15SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl15SkillLbl) + 5);
			label.setVisible(false);
		});
		lvl20SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl20SkillLbl) + 6);
			label.setText("This skill will highly increase your attack power!");
			label.setVisible(true);
		});

		lvl20SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl20SkillLbl) + 6);
			label.setVisible(false);
		});
		lvl25SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl25SkillLbl) + 7);
			label.setText("This skill will do something!");
			label.setVisible(true);
		});

		lvl25SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl25SkillLbl) + 7);
			label.setVisible(false);
		});
		lvl30SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl30SkillLbl) + 20);
			label.setText(
					"This skill has a chance to reflect the incoming damage back to the enemy, while taking no damage yourself!");
			label.setVisible(true);
		});

		lvl30SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl30SkillLbl) + 20);
			label.setVisible(false);
		});
		lvl35SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl35SkillLbl) + 21);
			label.setText("This skill will greatly reduce the amount of mana spent per attack!");
			label.setVisible(true);
		});

		lvl35SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl35SkillLbl) + 21);
			label.setVisible(false);
		});
		lvl40SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl40SkillLbl) + 22);
			label.setText("This skill will greatly increase your attack power!");
			label.setVisible(true);
		});

		lvl40SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl40SkillLbl) + 22);
			label.setVisible(false);
		});
		lvl45SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl45SkillLbl) + 23);
			label.setText("Mastering the use of the full power attack will greatly reduce its chance to fail!");
			label.setVisible(true);
		});

		lvl45SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl45SkillLbl) + 23);
			label.setVisible(false);
		});
		lvl50SkillLbl.setOnMouseEntered(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl50SkillLbl) + 24);
			label.setText("Fusion magic is the essence of magic, attack with a combination of every element!");
			label.setVisible(true);
		});

		lvl50SkillLbl.setOnMouseExited(evt -> {
			Label label = (Label) this.getChildren().get(GridPane.getRowIndex(lvl50SkillLbl) + 24);
			label.setVisible(false);
		});
	}

	private void timer() {

		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				teller++;
				if (teller == 300) {
					informationLbl.setVisible(false);
					teller = 0;
					timer.stop();
				}

			}
		};

		timer.start();
	}

	private void resetTimer() {
		if (timer != null) {
			timer.stop();
		}
		teller = 0;
	}

}
