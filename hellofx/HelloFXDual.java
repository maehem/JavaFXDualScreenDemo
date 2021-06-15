package hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;

public class HelloFXDual extends Application {
    private List<Screen> screens;
    private List<Group> groups = new ArrayList<>();


    public void startSingle(Stage stage) { // Original code, not used here.
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        //Scene scene = new Scene(new StackPane(l), 640, 480);
        Scene scene = new Scene(new StackPane(l), 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        System.out.println("Screen bounds: " +  bounds);

        screens = Screen.getScreens();
        System.out.println("Number of screens found: "+ screens.size());

        var width = new double[screens.size()];
        var height = new double[screens.size()];
        var offsetX = new double[screens.size()];
        var offsetY = new double[screens.size()];

        for (var i = 0; i < screens.size(); i++) {
            System.out.println("Parsing screen: "+ screens.get(i));

            width[i] = screens.get(i).getBounds().getWidth();
            height[i] = screens.get(i).getBounds().getHeight();
            offsetX[i] = screens.get(i).getBounds().getMinX();
            offsetY[i] = screens.get(i).getBounds().getMinY();

            Group g = createChess(width[i], height[i], (i == 0) ? Color.RED : Color.GREEN );
            groups.add(g);

            var scene = new Scene(g);
            var myStage = new Stage();
            myStage.setTitle("Screen " + (i + 1));
            myStage.setX(offsetX[i]);
            myStage.setY(offsetY[i]);
            myStage.setWidth(width[i]);
            myStage.setScene(scene);
            myStage.show();
        }
    }

    public Group createChess (double w, double h, Color color) {
        var g = new Group();
        for (var i = 0; i < 10; i++)  {
            for (var j = 0; j < 10; j ++) {
                double w0 = w * j/10;
                double h0 = h * i/10;
                boolean filled = (i%2 == j%2);
                boolean dbg = (i%4 == j%4);
                if (filled) {
                    var r = new Rectangle(w0, h0, w/10, h/10);
                    r.setFill(color);
                    r.setStrokeWidth(4);
                    r.setStroke(Color.BLACK);
                    r.setOnMousePressed(e -> showClicked(w0, h0, w/10, h/10));
                    g.getChildren().addAll(r);
                    if (dbg) {
                        var l = new Label("(" + w0 + ", " + h0+")");
                        l.setTranslateX(w0+w/20);
                        l.setTranslateY(h0+h/20);
                        g.getChildren().add(l);
                    }
                }
            }
        }
        return g;
    }

    public void showClicked(double x, double y, double width, double height) {
        System.out.println("Clicked box on " + x + "/" + y);
        var r = new Rectangle(x, y, width, height);
        r.setFill(Color.ORANGE);
        r.setStrokeWidth(4);
        r.setStroke(Color.BLACK);

        for (var i = 0; i < groups.size(); i++) {
            groups.get(i).getChildren().add(r);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
