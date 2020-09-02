import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;


public class Projekt extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        String sciezka;
        ObservableList<String> wszystkieHaslo = FXCollections.observableArrayList(pobierzHasla("src//hasla.txt.txt"));

        ObservableList<String> candidates = FXCollections.observableArrayList(MainWindow.haslaWybrane);

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,600,600);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPrefHeight(Double.MAX_VALUE);

        ColumnConstraints column1 = new ColumnConstraints(scene.getWidth()/4,150,Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(scene.getWidth()/4);
        ColumnConstraints column3 = new ColumnConstraints(scene.getWidth()/4);

        column1.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(column1,column2,column3);

        ListView<String> candidateListView = new ListView<>(candidates);
        gridPane.add(candidateListView,0,1);

        ListView<String> herosListView = new ListView<>(wszystkieHaslo);
        gridPane.add(herosListView,2,1);

        Button sendRight = new Button("->");
        Button sendLeft = new Button("<-");
        Button nowe_haslo = new Button("Nowe haslo");
        Button zatwierdz = new Button("Zatwierdz");


        nowe_haslo.setOnAction(
                (ActionEvent e)->{
                    TextInputDialog tip = new TextInputDialog();
                    tip.setContentText("Podaj haslo");
                    Optional<String> wynik = tip.showAndWait();
                    if(wynik.isPresent()){
                        candidates.add(wynik.get());
                    }
                }
        );



        sendRight.setOnAction(
                (ActionEvent e)->{
                        String potential = candidateListView.getSelectionModel().getSelectedItem();
                        if(potential != null){
                            candidateListView.getSelectionModel().clearSelection();
                            candidates.remove(potential);
                            wszystkieHaslo.add(potential);
                        }
                        });

        sendLeft.setOnAction(
                (ActionEvent e)->{
                    String potential = herosListView.getSelectionModel().getSelectedItem();
                    if(potential != null){
                        herosListView.getSelectionModel().clearSelection();
                        wszystkieHaslo.remove(potential);
                        candidates.add(potential);
                    }
                }
        );

        zatwierdz.setOnAction(
                (ActionEvent e)->{
                    MainWindow.haslaWybrane=candidates;
                    primaryStage.close();
        });

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(sendRight,sendLeft,nowe_haslo,zatwierdz);
        gridPane.add(vBox,1,1);

        root.setCenter(gridPane);

        primaryStage.setScene(scene);
        primaryStage.show();



    }


    public ArrayList pobierzHasla(String sciezka) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(sciezka));
        ArrayList<String> temp = new ArrayList<>();

        while(sc.hasNext()){

            temp.add(sc.nextLine());
        }
        return temp;
    }



}
