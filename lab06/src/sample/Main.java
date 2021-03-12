package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.Group;

public class Main extends Application {

    // Bar Chart Data
    private static double[] avgHousePricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };

    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };


    // Pie Chart Data
    public static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    public static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    public static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    private Canvas canvas;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 800, 425, Color.WHITE);

        // Assign Canvas properties
        this.canvas = new Canvas();
        this.canvas.widthProperty().bind(primaryStage.widthProperty());
        this.canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);
        primaryStage.setTitle("Lab6 Graphs");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Call functions to draw graphics
        drawBarChart(new barIncrement(avgHousePricesByYear, Color.RED), new barIncrement(avgCommercialPricesByYear, Color.BLUE));
        drawPieChart(pieColours, purchasesByAgeGroup);
    }

    // Series function for init values and properties of charts.
    public static class barIncrement {
        public double[] values;
        public Color colour;
        public String label;

        public barIncrement() {
            this.values = new double[0];
            this.colour = Color.WHITE;
        }

        public barIncrement(double[] values, Color colour) {
            this.values = values;
            this.colour = colour;
        }

        public barIncrement(double[] values, Color color, String label) {
            this.values = values;
            this.colour = colour;
            this.label = label;
        }

        public double[] getValues() {
            return values;
        }
        public void setValues(double[] values) {
            this.values = values;
        }

        public Color getColour() {
            return colour;
        }
        public void setColour(Color colour) {
            this.colour = colour;
        }

        public String getLabel() {
            return label;
        }
        public void setLabel(String label) {
            this.label = label;
        }
    }


    // Function for Pie Chart
    private void drawPieChart(Color[] color, int[] purchases) {
        GraphicsContext graphic = canvas.getGraphicsContext2D();

        // Vars for pie chart
        int total = 0;
        double sAngle = 0;
        double eAngle;
        // Increase total from purchase
        for (int purchase : purchases) {
            total += purchase;
        }

        double arcs = Math.pow(total, -1) * 360;
        System.out.println(purchases.length);
        for (int i = 0; i < purchases.length; i++) {
            graphic.setFill(color[i]);
            eAngle = (purchases[i] * arcs);
            //System.out.println("Start Angle: " + sAngle + "End Angle: ", eAngle + "Color: ", graphic.getFill());
            graphic.fillArc(420,  50, 325, 325, sAngle, eAngle, ArcType.ROUND);
            sAngle += eAngle;
        }
        graphic.strokeRect(420, 50, 325, 325);
    }

    // Function for bar Chart
    private void drawBarChart(barIncrement... bar) {
        GraphicsContext graphic = canvas.getGraphicsContext2D();

        // Get the max and min values of all the charts
        double max = Double.NEGATIVE_INFINITY, min = 0;
        int maxLength = 0;

        for (barIncrement b : bar) {
            if (b.values.length > maxLength) {
                maxLength = b.values.length;
            }
            for (double x : b.values) {
                if (x > max) {
                    max = x;
                }
                if (x < min) {
                    min = x;
                }
            }
        }

        // Start drawing each bar

        double spacing = 10;
        double jump =(325 - 2 * spacing)/(bar.length * (maxLength +2));

        for (int i = 0; i < bar.length; i++) {
            graphic.setFill(bar[i].colour);
            double[] arr = bar[i].values;
            double x = 50 + jump*i + spacing/2;

            // Draw the elements in the series
            for (double v : arr) {
                double height = ((v - min) / (max - min)) * 325;
                graphic.strokeRect(x, 50 + 325 - height, jump, height);
                graphic.fillRect(x, 50 + 325 - height, jump, height);
                x += jump * bar.length + spacing;
            }
        }
        graphic.strokeRect(50, 50, 325, 325);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
