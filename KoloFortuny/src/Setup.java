import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.*;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Setup extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        BorderPane root = new BorderPane();
        Label napisWybierzHasla = new Label("Zaznacz hasła: ");
        root.getChildren().add(napisWybierzHasla);
        ObservableList<String> hasla = FXCollections.observableArrayList();
        ArrayList<String> haslaWybrane = new ArrayList<>();
        Scanner sc = new Scanner(new File("src/hasla.txt.txt"));
        while(sc.hasNext()){
            hasla.add(sc.nextLine());
        }
        GridPane gridPane = new GridPane();
        ArrayList<CheckBox>  listaCheckboxow = new ArrayList<>();

      for(int i = 0 ; i < hasla.size();i++){
          CheckBox temp = new CheckBox();
          listaCheckboxow.add(temp);
          javafx.scene.control.Label temp2 = new javafx.scene.control.Label(hasla.get(i));
          gridPane.add(temp2,0,i,1,1);
          gridPane.add(temp,1,i,1,1);
      }
        javafx.scene.control.Button przejdzDalej = new javafx.scene.control.Button("Przejdz dalej");
      gridPane.add(przejdzDalej,0,hasla.size(),2,1);
      przejdzDalej.setOnAction(
              (ActionEvent e)->{
                  Stage stage = new Stage();
                  MainWindow mw = new MainWindow();

                  mw.haslaMozliwe= (ObservableList<String>) hasla;
                          for(int i = 0 ; i < hasla.size();i++){
                              if(listaCheckboxow.get(i).isSelected()){
                                  mw.haslaWybrane.add(hasla.get(i));
                              }
                          }
                  try {
                      mw.start(stage);
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
                          primaryStage.close();
              }
      );

      Scene scene = new Scene(gridPane,300,300);
      primaryStage.setScene(scene);
      primaryStage.show();

    }


    public void getHasla(ObservableList<String> hasla){
        for(int i = 0 ; i < hasla.size();i++){

        }
    }
}
