import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Optional;

public class MainWindow extends Application {
    ObservableList<String> haslaMozliwe;
    static ObservableList<String> haslaWybrane = FXCollections.observableArrayList();
    public double rotateImage;
    int sleepTime=100;
    int posiadanePieniadze=0;
    int posiadaneZycia=3;
    String wylosowaneHaslo;
    int wylosowanaWartosc;
    Label [] labele;
    Label [] zycia = new Label[3];
    int score=0;
    Label scoreLabel;
    int wartoscLosowania=0;
    Stage ps;

    @Override
    public void start(Stage primaryStage2) throws Exception {
        ps=primaryStage2;
        VBox total = new VBox();
        MenuBar mb = new MenuBar();


        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Scene scene2 = new Scene(root,600,500);
        Menu men1 = new Menu("Reset");
        MenuItem resetItem = new MenuItem("Reset gry");
        mb.getMenus().add(men1);
   /*     resetItem.setOnAction((ActionEvent e)->{
            Stage stage = new Stage();
            Setup setup = new Setup();
            try {
                primaryStage2.close();
                setup.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    */
        Menu men2 = new Menu("Hasla");
        mb.getMenus().add(men2);
        Menu men3 = new Menu("Wyjscie");
        mb.getMenus().add(men3);
        MenuItem mi = new MenuItem("Ustawienia haseł");
        MenuItem wyjscie = new MenuItem("Zakoncz");
        mi.setOnAction(
                (ActionEvent e)-> {
                    Stage stage = new Stage();
                    Projekt hasla = new Projekt();
                    try {
                        hasla.start(stage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );
        wyjscie.setOnAction((ActionEvent e)->{
            primaryStage2.close();
        });
        men2.getItems().add(mi);
        men3.getItems().add(wyjscie);
        men1.getItems().add(resetItem);
        root.getChildren().add(mb);
        GridPane haslo = new GridPane();
        //haslo.setAlignment(Pos.BOTTOM_CENTER);
        haslo.setVgap(10);
        wylosowaneHaslo=haslaWybrane.get((int)Math.random()*haslaWybrane.size());
        labele=new Label[wylosowaneHaslo.length()];
        System.out.println(wylosowaneHaslo);
        for(int i = 0 ; i < labele.length;i++) {
            if(wylosowaneHaslo.charAt(i)==' ') {
                Label temp = new Label(" ");
                labele[i] = temp;
                haslo.add(labele[i],i,0);
                System.out.println(labele[i]);
            }else{
                Label temp = new Label("?");
                labele[i]=temp;
                haslo.add(labele[i],i,0);
                score+=wylosowanaWartosc;
                System.out.println(labele[i]);
            }
        }



GridPane zyciaRoot = new GridPane();
        zyciaRoot.setHgap(5);
        for(int i = 0 ; i < zycia.length;i++){
            Label temp = new Label("+");
            zycia[i]=temp;
            zyciaRoot.add(temp,i,0);
        }


        final ImageView strzalka = new ImageView();
        Image strzalka2 = new Image(new FileInputStream("src\\Strzalka.png"));
        strzalka.setImage(strzalka2);
        root.getChildren().add(strzalka);



        Button b1 = new Button("Krec!");
        final ImageView obrazek = new ImageView();
        Image obrazek2 = new Image(new FileInputStream("src\\Kolo.png"));
        obrazek.setImage(obrazek2);
       root.getChildren().add(obrazek);
        root.getChildren().add(b1);
        b1.setOnAction(
                (ActionEvent e)->{
                    obracanie(obrazek);

                    TextInputDialog tid = new TextInputDialog("Podaj litere:");
                    tid.setHeaderText("Podaj Litere");
                    Optional<String> result = tid.showAndWait();

                    sprawdzLitere(result.get());

                }
        );



        root.getChildren().add(haslo);


        root.getChildren().add(zyciaRoot);
        scoreLabel = new Label();
        scoreLabel.setText("Twój wynik: " + score);
        root.getChildren().add(scoreLabel);


        primaryStage2.setScene(scene2);
        primaryStage2.showAndWait();

    }
    public void obracanie(ImageView img){

        new Thread("kolo"){
            @Override
            public void run() {
                super.run();

                int iloscKrokow = 30;

                 wylosowanaWartosc=(int)(Math.random()*10);
                int[] tab = {
                        10,50,200,20,10,100,0,100,9,77
                };
                wartoscLosowania=tab[wylosowanaWartosc];
                System.out.println(wylosowanaWartosc+"---"+tab[wylosowanaWartosc]);
                sleepTime=100;

                for(int i = 0; i < 30+wylosowanaWartosc ; i++){
                    img.setRotate(img.getRotate()+36);
                    try {
                        sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sleepTime+=20;
                }
            }
        }.start();
    }

    public void sprawdzLitere(String podanaLitera){
        if(podanaLitera.length()==1) {
            if (wylosowaneHaslo.contains(podanaLitera)) {
                for (int i = 0; i < labele.length; i++) {
                    if (podanaLitera.charAt(0) == wylosowaneHaslo.charAt(i)) {
                        labele[i].setText(podanaLitera);
                        score+=wartoscLosowania;
                    }
                }

                scoreLabel.setText("Twój wynik: "+score);

                if(czyWygrana()){
                    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                    dialog.setContentText("Twoja wygrana " + score);
                    dialog.setHeaderText(null);
                    dialog.showAndWait();
                }


            }else{

                for(int i = 0 ; i <zycia.length;i++){
                    if(zycia[i].getText().equals("+")) {
                        zycia[i].setText("-");
                        break;
                    }

                    if(i==zycia.length-2) {
                        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                        dialog.setContentText("Przegrales, Twoja wygrana = " + score);
                        dialog.setHeaderText(null);
                        dialog.showAndWait();
                     //   Setup setup = new Setup();
                      //  Stage stage = new Stage();
                       // try {
                         //   setup.start(stage);
                        //} catch (Exception e) {
                         //   e.printStackTrace();
                        //}
                        ps.close();
                    }
                }
            }
        }
    }

    public boolean czyWygrana(){

        for(int i = 0 ; i < labele.length;i++){
            if(labele[i].getText().equals("?")){
                return false;
            }
        }
        return true;
    }
}
