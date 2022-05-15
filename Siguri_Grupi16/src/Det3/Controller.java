package Det3;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

/**
* Controller class for user interactions with the GUI
*/
public class Controller implements Initializable {

@FXML
public TextField messageString;
public ChoiceBox choiceBox;
public ChoiceBox offsetChoice;
public TextField keyString;
public Label offsetLbl;
public Label keyPhraseLabel;
public ImageView imageDisplay;
public AnchorPane anchorPane;
public TextArea textLog;
@FXML
private TextField lblOutput;
ArrayList<String> logs = new ArrayList<>() ;


/* public void initialize() {
 choiceBoxSelect();
}*/

/**
* Populates Cipher Type choice box
*/
public void choiceBoxSelect() {

 for (CipherType type : CipherType.values()) {
   choiceBox.getItems().add(type);
   choiceBox.getSelectionModel().selectFirst();
 }
}

/**
* Populates Offset choice box
*/
public void offsetSelect() {
 for (int i = 1; i <= 25; i++) {
   offsetChoice.getItems().add(i);
 }
 offsetChoice.getSelectionModel().selectFirst();
}

/**
* Toggles off irrelevant UI elements based off of the selected CipherType
*/
public void offsetToggle() {
 CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

 if (cipherType == CipherType.Caesar) {

   offsetChoice.setVisible(true);
   offsetSelect();
   offsetLbl.setText("Offset: ");

   keyString.setVisible(false);
   keyPhraseLabel.setText("");


 } else if (cipherType == CipherType.Vigenere) {
   keyPhraseLabel.setText("Key Phrase: ");
   keyString.setVisible(true);

   offsetChoice.setVisible(false);
   offsetLbl.setText("");

 } else {
   offsetChoice.setVisible(false);
   offsetLbl.setText("");

   keyPhraseLabel.setText(" ");
   keyString.setVisible(false);
   keyPhraseLabel.setText("");

 }
}

/**
* Determines what type of encryption will be used based off of the CipherType selected by the
* Cipher Type ChoiceBox.
*
* @param mouseEvent - occurs when mouse clicks the encrypt button
*/
public void encrypt(MouseEvent mouseEvent) {
 String plainString = messageString.getText();
 CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

 textLog.setText("");
 CipherLog recordLog = new CipherLog(plainString, cipherType.toString());
 logs.add(recordLog.toString());
 for(String a : logs){
   textLog.appendText(a + "\n");
 }

 switch (cipherType) {
   
   case Caesar:
     int offset = (int) offsetChoice.getValue();
     Ciphers Caesar = new Caesar();
     lblOutput.setText(Caesar.encrypt(plainString, offset, null).toString());
     break;
   case Vigenere:
     String key = keyString.getText();
     Ciphers Vigenere = new Vigenere();
     lblOutput.setText(Vigenere.encrypt(plainString, 0, key).toString());
     
 }
}

/**
* Determines what type of decryption will be used based off of the CipherType selected by the
* Cipher Type ChoiceBox.
*
* @param mouseEvent - occurs when mouse clicks the decrypt button
*/
public void decrypt(MouseEvent mouseEvent) {
 String messString = messageString.getText();
 CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

 textLog.setText(" ");
 CipherLog recordLog = new CipherLog(messString, cipherType.toString());
 logs.add(recordLog.toString());
 for(String a : logs){
   textLog.appendText(a + "\n");
 }

 switch (cipherType) {
  
   case Caesar:
     int offset = (int) offsetChoice.getValue();
     Ciphers Caesar = new Caesar();
     lblOutput.setText(Caesar.decrypt(messString, offset, null).toString());
     break;
   case Vigenere:
     String key = keyString.getText();
     Ciphers Vigenere = new Vigenere();
     lblOutput.setText(Vigenere.decrypt(messString, 0, key).toString());
    
 }
}

/**
* Calls offsetToggle function when a cipher type is selected
*
* @param actionEvent - occurs when mouse clicks on a cipher type
*/
public void typeSelect(ActionEvent actionEvent) {
 offsetToggle();
}
FileChooser fileChooser = new FileChooser();
@FXML
void save(MouseEvent event) {
   File file = fileChooser.showSaveDialog(new Stage());
   if(file != null){
       saveSystem(file, lblOutput.getText());
   }
}

@FXML
void getText(MouseEvent event) {
   File file = fileChooser.showOpenDialog(new Stage());
   if(file != null){
       try {
           Scanner scanner = new Scanner(file);
           while(scanner.hasNextLine()){
         	  messageString.appendText(scanner.nextLine() + "\n");
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
   }

 
}
@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
   fileChooser.setInitialDirectory(new File("C:\\Users\\User\\eclipse-workspace\\Gr_16.zip_expanded\\CipherIt-master\\src\\main"));
   choiceBoxSelect();
}

public void saveSystem(File file, String content){
   try {
       PrintWriter printWriter = new PrintWriter(file);
       printWriter.write(content);
       printWriter.close();
   } catch (FileNotFoundException e) {
       e.printStackTrace();
   }



}}