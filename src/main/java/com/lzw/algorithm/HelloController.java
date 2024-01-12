package com.lzw.algorithm;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.lzw.algorithm.LZWCompression;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Button compress_btn,decompress_btn,selectFileBtn,saveBtnFile;
    @FXML
    private SplitPane splitPane;
    @FXML
    private TabPane mainTab;
    @FXML
    private Label fileSelectedLabel;
    @FXML
    private Tab textInputTab,fileTab,ImageTab;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private TextArea inputTextArea,outputTextArea,textFileTaboutput;

    private String filePath = null;

    @FXML
    protected void compressButtonAction(ActionEvent event) {

        if (mainTab.getSelectionModel().getSelectedItem().getId() == textInputTab.getId()) {
            String textInput = inputTextArea.getText();
            List<Integer> encodedText = LZWCompression.encode(textInput);
            String resultText = "";
            for (Integer num: encodedText
                 ) {
                resultText += num.toString() + "\n";

            }
            outputTextArea.setText(resultText);
        }else if(mainTab.getSelectionModel().getSelectedItem().getId() == fileTab.getId()){
            if(filePath != null){
                String s = FileToStringConverter.convertFileToString(filePath);
                List<Integer> encodedText = LZWCompression.encode(s);
                String resultText = "";
                for (Integer num: encodedText
                ) {
                    if(num != null) {
                        resultText += num.toString() + "\n";
                    }

                }
                textFileTaboutput.setText(resultText);
            }else {
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("Dialog");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                //Setting the content of the dialog
                dialog.setContentText("Please first select your file");
                //Adding buttons to the dialog pane
                dialog.getDialogPane().getButtonTypes().add(type);

                dialog.showAndWait();

            }
        }

    }

    @FXML
    protected void decompressButtonAction(ActionEvent event) {

        if (mainTab.getSelectionModel().getSelectedItem().getId() == textInputTab.getId()) {
            String encodedListString[] = inputTextArea.getText().split("\n");
            List<Integer>  encodedList = new ArrayList<>();

            for (String num: encodedListString
                 ) {
                encodedList.add(Integer.parseInt(num));
            }

            String decodedString = LZWCompression.decode(encodedList);
            outputTextArea.setText(decodedString);
        }else if(mainTab.getSelectionModel().getSelectedItem().getId() == fileTab.getId()) {
            if (filePath != null) {

            } else {
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("Dialog");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                //Setting the content of the dialog
                dialog.setContentText("Please first select your file");
                //Adding buttons to the dialog pane
                dialog.getDialogPane().getButtonTypes().add(type);

                dialog.showAndWait();

            }
        }
    }

    @FXML
    protected void selectBtnFileAction(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(splitPane.getScene().getWindow());
        filePath = selectedFile.getPath();
        fileSelectedLabel.setText("File path : "+filePath);


    }

    @FXML
    protected void saveBtnFileAction(ActionEvent event){

    }


}