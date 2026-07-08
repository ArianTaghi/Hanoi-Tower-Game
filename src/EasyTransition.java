import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class EasyTransition {
    public static TranslateTransition t1(Node node) {
        TranslateTransition rsl = new TranslateTransition(Duration.seconds(0.3), node);
        rsl.setCycleCount(1);
        rsl.setAutoReverse(false);
        rsl.setByY(-350);
        return rsl;
    }
    public static ParallelTransition t3(Node node) {
        TranslateTransition rsl = new TranslateTransition(Duration.seconds(0.3), node);
        rsl.setCycleCount(1);
        rsl.setAutoReverse(false);
        rsl.setByY(-350);
        FadeTransition fade = new FadeTransition(Duration.seconds(0.3), node);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        return  new ParallelTransition(fade, rsl);
    }
    public static TranslateTransition t2(Node node) {
        TranslateTransition rsl = new TranslateTransition(Duration.seconds(0.3), node);
        rsl.setCycleCount(1);
        rsl.setAutoReverse(false);
        rsl.setByY(350);
        return rsl;
    }
        public static ParallelTransition t4(Node node) {
                TranslateTransition rsl = new TranslateTransition(
                        Duration.seconds(0.3),
                        node
                );
                rsl.setCycleCount(1);
                rsl.setAutoReverse(false);
                rsl.setByY(350);
                FadeTransition fade = new FadeTransition(Duration.seconds(0.3), node);
                fade.setFromValue(0.0);
                fade.setToValue(1);
                return  new ParallelTransition(fade, rsl);
            }
}
