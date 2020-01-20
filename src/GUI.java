import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class GUI extends Application {

    private String filename = ".\\\\Insiten-Challenge-master\\\\resrc\\\\Companies .json";
    private LinkedList<Company> list;
    private Runner run = new Runner();

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

    private void reset(Stage stage) {

        Label label = new Label();
        label.setText("\n\n\n\n");
        Button csButton = new Button("Check Statuses");
        csButton.setOnAction(e -> {
            label.setText(run.printMap(run.getCompanyStatusCounts(list)));
        });

        Button crButton = new Button("Get Revenue Nearest To: ");
        TextField input = new TextField();

        crButton.setOnAction(e -> {
            if (!isNumeric(input.getText()) || input.getText().isEmpty()) {
                label.setText("Please input a valid target revenue");
            } else {
                if (input.getText().length() > 18) {
                    label.setText("Please input a valid target revenue\nValid revenues cannot be more than 18 digits long");
                } else {
                    label.setText(run.getCompanyClosestToTargetRevenue(list, Long.parseLong(input.getText())).toString());
                }
            }
        });

        Button options = new Button("More Options...");
        Label label1 = new Label("JSON File Path: ");
        TextField newFile = new TextField();
        Button browse = new Button("...");
        Button confirm = new Button("Open");
        Button ret = new Button("Return to Main Menu");
        Hyperlink link = new Hyperlink();
        link.setText("Click Here to Make Suggestions");
        VBox vb2 = new VBox();
        HBox hb6 = new HBox();
        HBox hb8 = new HBox();
        hb8.getChildren().add(ret);
        hb6.getChildren().addAll(label1, newFile, browse, confirm);
        vb2.getChildren().addAll(hb6, link, hb8);
        Scene scene2 = new Scene(vb2);
        options.setOnAction(e -> {
            stage.close();
            stage.setScene(scene2);
            stage.setTitle("More Options");
            stage.show();

        });
        confirm.setOnAction(e -> {
            String oldFile = filename;

            filename = newFile.getText();
            stage.close();
            if (filename.length() > 4) {
                if (filename.isEmpty() || !filename.substring(filename.length() - 5, filename.length()).contains(".json")) {
                    popup("The filename:\n" + filename + "\n does not end in '.json'. Reverting back to the previous file:\n" + oldFile, new Stage());
                    filename = oldFile;
                } else {
                    LinkedList<Company> oldList = list;
                    list = run.run(filename);
                    if (list == null) {
                        popup("The file:\n" + filename + "\ncould not be found. Reverting back to the previous file:\n" + oldFile, new Stage());
                        list = oldList;
                        filename = oldFile;
                    } else {
                        reset(new Stage());
                    }
                }
            } else {
                popup("The filename:\n" + filename + "\nwas not long enough to contain '.json'. Reverting back to the previous file:\n" + oldFile, new Stage());
                filename = oldFile;
            }
        });
        browse.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File sf = fc.showOpenDialog(null);
            newFile.setText(sf.getAbsolutePath());
        });
        ret.setOnAction(e -> {
            stage.close();
            reset(new Stage());
        });
        link.setOnAction(e -> {
            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://forms.gle/q9cocSLG7wbAfCTp9"));
                } catch (IOException E) {
                    E.printStackTrace();
                } catch (URISyntaxException E) {
                    E.printStackTrace();
                }
            }
        });

        Button exit = new Button("Exit");
        exit.setOnAction(e -> stage.close());
        HBox hb2 = new HBox();
        hb2.getChildren().add(csButton);

        HBox hb3 = new HBox();
        hb3.getChildren().addAll(crButton, input);

        HBox hb4 = new HBox();
        hb4.getChildren().add(label);

        HBox hb5 = new HBox();
        hb5.getChildren().add(options);

        HBox hb7 = new HBox();
        hb7.getChildren().add(exit);

        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb2, hb3, hb4, hb5, hb7);
        Scene scene = new Scene(vb1);
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }

    private void popup(String string, Stage stage) {
        Button ok = new Button("Ok");
        Label message = new Label();
        message.setText(string);
        ok.setOnAction(e -> {
            reset(new Stage());
            stage.close();
        });
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(message, ok);
        Scene scene = new Scene(vb1);
        stage.setScene(scene);
        stage.setTitle("Popup");
        stage.show();
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
