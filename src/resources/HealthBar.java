package resources;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * The type Health bar.
 */
public class HealthBar extends StackPane {

	final private double maxvalue;

	final private ProgressBar bar = new ProgressBar();
	final private Text text = new Text();
	private String content;

	final private static int DEFAULT_LABEL_PADDING = 5;

	/**
	 * Instantiates a new Health bar.
	 *
	 * @param value the value
	 */
	public HealthBar(final double value) {
		this.maxvalue = value;

		updateProgress(maxvalue);
//		workDone.addListener(new ChangeListener<Number>() {
//			@Override 
//			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
//				syncProgress();
//			}
//			
//		});

//		bar.setMaxWidth(Double.MAX_VALUE); // allows the progress bar to expand to fill available horizontal space.
		bar.setPrefWidth(200);

		getChildren().setAll(bar, text);
	}

	/**
	 * Update progress.
	 *
	 * @param value the value
	 */
// synchronizes the progress.
	public void updateProgress(double value) {
		content = String.format("%.2f/%.2f", value,maxvalue);
		text.setText(content);
		bar.setProgress(value/maxvalue);

		bar.setMinHeight(text.getBoundsInLocal().getHeight() + DEFAULT_LABEL_PADDING * 2);
		bar.setMinWidth(text.getBoundsInLocal().getWidth() + DEFAULT_LABEL_PADDING * 2);
	}

}
