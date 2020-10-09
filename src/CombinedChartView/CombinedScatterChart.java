package CombinedChartView;

import AHPalgorithm.AHPcalculation;
import AHPalgorithm.AHPprocessImplementation;
import FilePackage.DateTimeWriter;
import JavFX.ProcessAnalysis;
import MainPackage.BookNumber;
import MainPackage.Processing;
import MultiVariableRegression.MultipleLinearRegression;
import ObjectOriented.AHPcriteriaWeight;
import ObjectOriented.PriorityData;
import PageRankAlgorithm.PageRankCalculation;
import PageRankAlgorithm.PageRankProcessData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class CombinedScatterChart extends Application {
    PriorityData[] priorityData;
    AHPcriteriaWeight ahPcriteriaWeight;
    int numberOfBooks;
    int iterator;
    Processing processing = new Processing();
    BookNumber bookNumber = new BookNumber();
    MultipleLinearRegression multipleLinearRegression = new MultipleLinearRegression();
    PageRankCalculation pageRankCalculation = new PageRankCalculation();
    AHPcalculation ahPcalculation = new AHPcalculation();
    AHPprocessImplementation ahPprocessImplementation = new AHPprocessImplementation();

    @Override
    public void start(Stage primaryStage) throws IOException {
        String className = this.getClass().getSimpleName();
        DateTimeWriter dateTimeWriter = new DateTimeWriter();
        dateTimeWriter.dateTimeWriterMethods(className);

        Button back = new Button("Back");
        Button exit = new Button("Exit");
        back.setTranslateX(0);
        back.setTranslateY(650);
        exit.setTranslateX(1100);
        exit.setTranslateY(650);
        back.setOnAction(actionEvent -> {
            ProcessAnalysis processAnalysis = new ProcessAnalysis();
            try {
                processAnalysis.start(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        exit.setOnAction(actionEvent -> {
            System.exit(0);
        });
        setStyle(exit);
        setStyle(back);

        back.setPrefSize(160, 50);
        exit.setPrefSize(160, 50);

       priorityData = processing.fileReaderMethods();
        numberOfBooks = bookNumber.bookNumberFindingMethods();
        priorityData = multipleLinearRegression.multipleLinearRegressionMethods(priorityData, numberOfBooks);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setLabel("Book Class Category");
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setLabel("Numbers of Book");

        ScatterChart scatterChart = new ScatterChart(categoryAxis, numberAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Multi-variable Linear Regression ");
        int positionIndicator = 0;
        for (iterator = 0; iterator < numberOfBooks; iterator++) {
            positionIndicator++;
            series1.getData().add(new XYChart.Data(String.valueOf(positionIndicator), priorityData[iterator].getMLRweight()));
        }

        scatterChart.getData().add(series1);
        scatterChart.setTranslateX(10);
        scatterChart.setTranslateY(25);
        scatterChart.setPrefSize(1250, 300);

        ahPcriteriaWeight = ahPcalculation.AHPcalculationMethods(priorityData, numberOfBooks);
        priorityData = ahPprocessImplementation.ahpProcessImplementationMethods(ahPcriteriaWeight, priorityData, numberOfBooks);
        CategoryAxis categoryAxis1 = new CategoryAxis();
        categoryAxis1.setLabel("Book Class Category");

        NumberAxis numberAxis1 = new NumberAxis();
        numberAxis1.setLabel("Numbers of Book");
        ScatterChart scatterChart1 = new ScatterChart(categoryAxis1, numberAxis1);
        XYChart.Series series2 = new XYChart.Series();

        series2.setName("Analytic Hierarchy Process");

        for (iterator = 0; iterator < numberOfBooks; iterator++) {
            positionIndicator++;
            series2.getData().add(new XYChart.Data(String.valueOf(positionIndicator), priorityData[iterator].getAHPweight()));
        }

        scatterChart1.getData().add(series2);

        scatterChart1.setTranslateX(10);
        scatterChart1.setTranslateY(350);
        scatterChart1.setPrefSize(650, 300);
        PageRankProcessData pageRankProcessData = new PageRankProcessData();
        priorityData = pageRankProcessData.PageRankProcessDataMethods(priorityData, numberOfBooks);
        CategoryAxis categoryAxis2 = new CategoryAxis();
        categoryAxis2.setLabel("Book Class Category");

        NumberAxis numberAxis2 = new NumberAxis();
        numberAxis2.setLabel("Numbers of Book");

        ScatterChart scatterChart2 = new ScatterChart(categoryAxis2, numberAxis2);
        XYChart.Series series3 = new XYChart.Series();

        series3.setName("Page Rank Algorithm");

        for (iterator = 0; iterator < numberOfBooks; iterator++) {
            positionIndicator++;
            series3.getData().add(new XYChart.Data(String.valueOf(positionIndicator), priorityData[iterator].getPRAweight()));
        }

        scatterChart2.getData().add(series3);
        scatterChart2.setTranslateX(700);
        scatterChart2.setTranslateY(350);
        scatterChart2.setPrefSize(650, 300);

        MenuItem scatterChart3 = new MenuItem("Scatter Chart");
        scatterChart3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CombinedScatterChart combinedScatterChart = new CombinedScatterChart();
                try {
                    combinedScatterChart.start(primaryStage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } });
        MenuItem lineChart3 = new MenuItem("Line Chart");

        lineChart3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                CombinedLineChart combinedLineChart = new CombinedLineChart();
                try {
                    combinedLineChart.start(primaryStage,0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        MenuItem stackedAreaChart3 = new MenuItem("StackedArea Chart");
        stackedAreaChart3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                CombinedStackedAreaChart combinedStackedAreaChart = new CombinedStackedAreaChart();
                try {
                    combinedStackedAreaChart.start(primaryStage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        MenuButton selectOtherChart = new MenuButton("Select OtherChart");
        selectOtherChart.getItems().addAll( scatterChart3, lineChart3, stackedAreaChart3);
        selectOtherChart.setTranslateX(650);
        selectOtherChart.setTranslateY(320);
        selectOtherChart.setPrefSize(150, 35);

        Canvas canvas = new Canvas(1500, 950);
        Group group = new Group();
        group.getChildren().addAll(canvas, scatterChart, scatterChart1, scatterChart2, exit, back,selectOtherChart);

        Scene scene1 = new Scene(group, 1500, 950);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Recommendation Tool");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public Button setStyle(Button button) {
        button.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "                linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "        -fx-background-radius: 5,4,3,5;\n" +
                "        -fx-background-insets: 0,1,2,0;\n" +
                "        -fx-text-fill: white;\n" +
                "        -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "        -fx-font-family: \"Arial\";\n" +
                "        -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "        -fx-font-size: 1.5em;\n" +
                "        -fx-padding: 10 20 10 20;");
        return button;
    }
}
