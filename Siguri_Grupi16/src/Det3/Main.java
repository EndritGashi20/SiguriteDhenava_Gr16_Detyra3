package Det3;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  /**
   * Main method for program.
   *
   * @param args -- Command line arguments
   */
  public static void main(String[] args) {
	  File file = new File("src\\sample\\text.txt");
      try {
          file.createNewFile();
      } catch (IOException e) {
          e.printStackTrace();
      }
    launch(args);
  }

  /**
   * Initializes JavaFX GUI window.
   *
   * @param primaryStage - Primary stage for application
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setTitle("Grupi_16");

    primaryStage.setScene(scene);

    primaryStage.show();
  }
}
