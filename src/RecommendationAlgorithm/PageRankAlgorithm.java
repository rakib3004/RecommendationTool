package RecommendationAlgorithm;

import AHPalgorithm.AHPcalculation;
import AHPalgorithm.AHPprocessImplementation;
import FilePackage.DateTimeWriter;
import JavFX.Main;
import MainPackage.BookNumber;
import MainPackage.Processing;
import ObjectOriented.AHPcriteriaWeight;
import ObjectOriented.PriorityData;
import PageRankAlgorithm.PageRankCalculation;
import PageRankAlgorithm.PageRankProcessData;
import TableViewPackage.PRA_Chart_View;
import TableViewPackage.PRA_TableView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PageRankAlgorithm extends Application {

    PriorityData[] priorityData;
    AHPcriteriaWeight ahPcriteriaWeight;

    int numberOfBooks;
    Processing processing = new Processing();
    BookNumber bookNumber = new BookNumber();
    AHPcalculation ahPcalculation = new AHPcalculation();
    AHPprocessImplementation ahPprocessImplementation = new AHPprocessImplementation();
    @Override
    public void start(Stage primaryStage) throws IOException {
        String  className = this.getClass().getSimpleName();
        DateTimeWriter dateTimeWriter =  new DateTimeWriter();
        dateTimeWriter.dateTimeWriterMethods(className);

        Font font = new Font(19);
        Font font1 = new Font(47);
        Font font2 = new Font( 26);
        Button tableView = new Button("Table View");
        Button graphView = new Button("Graph View");
        Button consoleView = new Button("Console View");

        tableView.setPrefSize(410,230);
        graphView.setPrefSize(410,230);
        consoleView.setPrefSize(410,230);

        tableView.setFont(font2);
        graphView.setFont(font2);
        consoleView.setFont(font2);
        tableView.setTranslateX(30);
        tableView.setTranslateY(35);
        graphView.setTranslateX(30);
        graphView.setTranslateY(270);
        consoleView.setTranslateX(30);
        consoleView.setTranslateY(505);

        tableView.setContentDisplay(ContentDisplay.TOP);
        graphView.setContentDisplay(ContentDisplay.TOP);
        consoleView.setContentDisplay(ContentDisplay.TOP);
        FileInputStream fileInputStream1 = new FileInputStream(
                "src"+ File.separator +"Images"+ File.separator +"table.png");
        Image image1 = new Image(fileInputStream1);
        tableView.setGraphic(new ImageView(image1));

        FileInputStream fileInputStream2 = new FileInputStream(
                "src"+ File.separator +"Images"+ File.separator +"graph.png");
        Image image2 = new Image(fileInputStream2);
        graphView.setGraphic(new ImageView(image2));

        FileInputStream fileInputStream3 = new FileInputStream(
                "src"+ File.separator +"Images"+ File.separator +"console.png");
        Image image3 = new Image(fileInputStream3);
        consoleView.setGraphic(new ImageView(image3));
        tableView.setOnAction(actionEvent -> {
            PRA_TableView pra_tableView = new PRA_TableView();
            try {
                pra_tableView.start(primaryStage);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        consoleView.setOnAction(actionEvent -> {
            PageRankCalculation pageRankCalculation = new PageRankCalculation();
            try {
                priorityData = processing.fileReaderMethods();
                numberOfBooks = bookNumber.bookNumberFindingMethods();
                //   priorityData = pageRankCalculation.pageRankCalculationMethods(priorityData,numberOfBooks);
                PageRankProcessData pageRankProcessData = new PageRankProcessData();
                priorityData = pageRankProcessData.PageRankProcessDataMethods(priorityData,numberOfBooks);                System.exit(0);
                priorityData = pageRankProcessData.PageRankProcessDataMethods(priorityData,numberOfBooks);                System.exit(0);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        graphView.setOnAction(actionEvent -> {
            PRA_Chart_View pra_chart_view = new PRA_Chart_View();
            try {

                pra_chart_view.start(primaryStage);
            }

            catch (Exception exception) {
                exception.printStackTrace();
            }

        });

        Button exit = new Button("Exit");
        exit.setTranslateX(1200);
        exit.setTranslateY(700);
        exit.setOnAction(actionEvent -> {
            System.exit(0);
        });
        FileInputStream fileInputStream4 = new FileInputStream(
                "src"+ File.separator +"Images"+ File.separator +"exit.png");
        Image image4 = new Image(fileInputStream4);
        exit.setGraphic(new ImageView(image4));
        exit.setPrefSize(120, 20);
        exit.setContentDisplay(ContentDisplay.LEFT);
        exit.setFont(font);
        Text text = new Text("Page Rank Algorithm");
        text.setTranslateX(600);
        text.setTranslateY(500);
        text.setFont(font1);
        text.setFill(Color.BLACK);
        text.setTextAlignment(TextAlignment.LEFT);
        text.setStyle("-fx-font-weight: bold;");

        Button home = new Button("Home");
        home.setTranslateX(470);
        home.setTranslateY(520);
        home.setPrefSize(375,30);
        home.setFont(font2);
        home.setContentDisplay(ContentDisplay.LEFT);
        FileInputStream fileInputStream5 = new FileInputStream(
                "src"+ File.separator +"Images"+ File.separator +"home.png");
        Image image5 = new Image(fileInputStream5);
        home.setGraphic(new ImageView(image5));
        home.setOnAction(actionEvent -> {
            Main main = new Main();
            try {
                main.start(primaryStage);
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        });
        Button back = new Button("Back");
        back.setTranslateX(950);
        back.setTranslateY(520);
        back.setPrefSize(375,30);
        back.setFont(font2);
        back.setContentDisplay(ContentDisplay.LEFT);
        FileInputStream fileInputStream6 = new FileInputStream(
                "src"+ File.separator +"Images"+ File.separator +"back.png");
        Image image6 = new Image(fileInputStream6);
        back.setGraphic(new ImageView(image6));
        back.setOnAction(actionEvent -> {
            try {
                ProcessImplementation processImplementation1 = new ProcessImplementation();
                processImplementation1.start(primaryStage);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        Image background = new Image("Images"+ File.separator +"framework.jpg");
        Canvas canvas = new Canvas(850, 425);
        canvas.setTranslateX(470);
        canvas.setTranslateY(35);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.drawImage(background,0,0);
        Group group = new Group();
        group.getChildren().addAll(canvas,tableView,
                graphView,exit,back,home,text,consoleView);
        Scene scene1 = new Scene(group,1500,950);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Recommendation Tool");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public Button setStyle( Button button)
    {
        button.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;");
        return  button;
    }
}
