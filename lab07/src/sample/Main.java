package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {

    // Create 3 arrays to store warnings, warningCount and piecolors
    public static String[] warnings = {"FLASH FLOOD", "SEVERE THUNDERSTORM", "SPECIAL MARINE", "TORNADO"};
    public static int[] countWarnings = new int[4];
    public static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON};

    @Override
    public void start(Stage stage) {
        // Call readCSV to read CSV file.
        readCSVFile("src/sample/weatherwarnings-2015.csv");

        // Create our Canvas, GC and Group
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Group group =  new Group();

        // Create Pie Chart
        double incAngle = 0;
        double incrY = 100;
        double factorTotal = 80; // To get the angular extent
        for (int i = 0; i < countWarnings.length; i ++){
            gc.setFill(pieColours[i]);
            gc.fillArc(50, 100, 300, 300, incAngle, countWarnings[i]/factorTotal, ArcType.ROUND);
            gc.fillRect(400, incrY, 70, 20);
            incAngle += countWarnings[i]/factorTotal; // How each arc extent works.
            incrY += 40;
        }

        // Set each warning to a Text object (Legend)
        Text text = new Text("FLASH FLOOD");
        Text text1 = new Text("SEVERE THUNDERSTORM");
        Text text2 = new Text("SPECIAL MARINE");
        Text text3 = new Text("TORNADO");

        // Use .set which sets the warning to properties x and y.
        text.setX(500);
        text.setY(110);
        text1.setX(500);
        text1.setY(150);
        text2.setX(500);
        text2.setY(190);
        text3.setX(500);
        text3.setY(230);

        // Get the list of children from group (Warnings text)
            // Add all inserts all elems in group
        group.getChildren().addAll(text,text1,text2,text3);
        group.getChildren().addAll(canvas);

        // Create new scene and show
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
    }

    // Function for reading the CSV file.
    private static void readCSVFile(String fileName) {
        // readLine() in BufferedReader. Split tokens based on comma.

        // Create path var to get file name
        Path pathToFile = Paths.get(fileName);

        // Read from bufferReader, newBufferedReader takes in a Charset.
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            String line = br.readLine();

            while (line != null) {

                String[] data = line.split(",");
                if (data[5].equals(warnings[0])){
                    countWarnings[0]++;
                } else if (data[5].equals(warnings[1])){
                    countWarnings[1]++;
                } else if (data[5].equals(warnings[2])){
                    countWarnings[2]++;
                } else if (data[5].equals(warnings[3])){
                    countWarnings[3]++;
                } else {
                    System.out.println("Error");
                }

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

