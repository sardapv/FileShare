package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;

import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {
    String address = null;
    @FXML
    private Label lbl;
    private TextField label2;

    @FXML
    private WebView wv;
    private WebEngine engine;

    InetAddress IP = InetAddress.getLocalHost();
    String currentIP = IP.getHostAddress();

    public Controller() throws UnknownHostException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //engine=wv.getEngine();
    }

    public void buttonclick(){
        String command = "cd ";
        command.concat(address);
        String command2 = "python -m SimpleHTTPServer";
        try {
            Process proc = Runtime.getRuntime().exec(command);
            Process proc2 = Runtime.getRuntime().exec(command2);
            lbl.setText(currentIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buttonclick2() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Web.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @FXML
    public void choose(ActionEvent event) throws IOException {
        choose();
    }

    public void choose() throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("This is my file ch");
        //Show open file dialog
        File file = directoryChooser.showDialog(null);
        if (file != null) {
            address = file.getPath().toString();
            lbl.setText(address);
        }
    }
}
