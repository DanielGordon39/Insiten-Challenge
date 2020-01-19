import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;

public class GUI extends Application {

    String filename = ".\\\\resrc\\\\Companies .json";
    LinkedList<Company> list;
    Runner run = new Runner();

    public void start(Stage stage) {

        Button start = new Button("Start!");

        start.setOnAction(e -> {
            list = run.run(filename);
            reset(new Stage());
            stage.close();
        });

        HBox hb1 = new HBox();
        hb1.getChildren().add(start);
        Scene scene = new Scene(hb1);
        stage.setScene(scene);
        stage.setTitle("Insiten Acquisition App");
        stage.show();

    }

    public void reset(Stage stage) {

        Label label = new Label();
        label.setText("\n\n\n\n");
        Button csButton = new Button("Check Status'");
        csButton.setOnAction(e -> {
            label.setText(run.printMap(run.getCompanyStatusCounts(list)));
        });

        Button crButton = new Button("Get Revenue Nearest To: ");
        TextField input = new TextField();

        crButton.setOnAction(e -> {
            label.setText(run.getCompanyClosestToTargetRevenue(list, Long.parseLong(input.getText())).toString());
        });

        Button options = new Button("More Options...");
        Label label1 = new Label("JSON File Path: ");
        TextField newFile = new TextField();
        Button confirm = new Button("x");
        VBox vb2 = new VBox();
        HBox hb6 = new HBox();
        hb6.getChildren().addAll(label1, newFile, confirm);
        vb2.getChildren().addAll(hb6);
        Scene scene2 = new Scene(vb2);
        options.setOnAction(e -> {
            stage.close();
            stage.setScene(scene2);
            stage.setTitle("More Options");
            stage.show();

        });
        confirm.setOnAction(e -> {
            filename = newFile.getText();
            stage.close();
            reset(new Stage());
        });

        HBox hb2 = new HBox();
        hb2.getChildren().add(csButton);

        HBox hb3 = new HBox();
        hb3.getChildren().addAll(crButton, input);

        HBox hb4 = new HBox();
        hb4.getChildren().add(label);

        HBox hb5 = new HBox();
        hb5.getChildren().add(options);

        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb2, hb3, hb4, hb5);
        Scene scene = new Scene(vb1);
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();



    }
}
