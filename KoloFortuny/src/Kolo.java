/*import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class Kolo extends HBox {
    int sleepTime = 100;



    public Kolo() throws Exception {


        HBox root = new HBox();
        Scene scene = new Scene(root,600,600);

        Button b1 = new Button("Zakrec!");
        final ImageView obrazek = new ImageView();
        Image obrazek2 = new Image(new FileInputStream("src\\Kolo.png"));
        obrazek.setImage(obrazek2);
        root.getChildren().add(obrazek);
        obrazek.setRotate(22);
        root.getChildren().add(b1);
        b1.setOnAction(
                (ActionEvent e)->{
                    obracanie(obrazek);
                }
        );





    }

    public void obracanie(ImageView img){

         new Thread("kolo"){
             @Override
             public void run() {
                 super.run();

                 int iloscKrokow = 24;

                 int wylosowanaWartosc=(int)(Math.random()*8);
                 int[] tab = {
                         10,50,200,20,10,100,0,-100
                 };
                 System.out.println(wylosowanaWartosc+"---"+tab[wylosowanaWartosc]);
                sleepTime=100;


                 for(int i = 0; i < 24+wylosowanaWartosc ; i++){
                     img.setRotate(img.getRotate()+45);
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
}
*/