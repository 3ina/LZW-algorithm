package com.lzw.algorithm;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.lzw.algorithm.LZWCompression;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Button compress_btn,decompress_btn;
    @FXML
    private TabPane mainTab;
    @FXML
    private Tab textInputTab,fileTab,ImageTab;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private TextArea inputTextArea,outputTextArea;

    @FXML
    protected void compressButtonAction(ActionEvent event) {

        if (mainTab.getSelectionModel().getSelectedItem().getId() == textInputTab.getId()) {
            String textInput = inputTextArea.getText();
            List<Integer> encodedText = LZWCompression.encode(textInput);
            String resultText = "";
            for (Integer num: encodedText
                 ) {
                resultText += num.toString() + " ";

            }
            outputTextArea.setText(resultText);
        }

    }

    @FXML
    protected void decompressButtonAction(ActionEvent event) {

        if (mainTab.getSelectionModel().getSelectedItem().getId() == textInputTab.getId()) {
            String encodedListString[] = inputTextArea.getText().split(" ");
            List<Integer>  encodedList = new ArrayList<>();

            for (String num: encodedListString
                 ) {
                encodedList.add(Integer.parseInt(num));
            }

            String decodedString = LZWCompression.decode(encodedList);
            outputTextArea.setText(decodedString);
        }

    }


}