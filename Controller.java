package sample;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.awt.event.ActionEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {
    String address = null;
    @FXML
    private Label lbl;
    @FXML
    private TextField label2;

    @FXML
    private WebView wv;


    InetAddress IP = InetAddress.getLocalHost();
    String currentIP = IP.getHostAddress();

    public Controller() throws UnknownHostException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buttonclick() throws IOException, InterruptedException {
        //String command = "cd /Users/pranav";
        ProcessBuilder pb = new ProcessBuilder("python", "-m", "SimpleHTTPServer");
        pb.directory(new File(address));
        Process p = pb.start();
        lbl.setText(currentIP);
    }

    public void buttonclick2() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Web.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        String visit = label2.getText().toString();
        WebEngine engine = wv.getEngine();
        engine.load("http://"+visit+":8000");
        engine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldLoc, String newLoc) {
                    // create a file save option for performing a download.
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("Save As " + newLoc);
                    System.out.println(chooser.getTitle());


                        chooser.setInitialFileName(chooser.getTitle());
                        File saveFile = chooser.showSaveDialog(wv.getScene().getWindow());

                        if (saveFile != null) {
                            BufferedInputStream is = null;
                            BufferedOutputStream os = null;
                            try {
                                is = new BufferedInputStream(new URL(newLoc).openStream());
                                os = new BufferedOutputStream(new FileOutputStream(saveFile));
                                int b = is.read();
                                while (b != -1) {
                                    os.write(b);
                                    b = is.read();
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("Unable to save file: " + e);
                            } catch (MalformedURLException e) {
                                System.out.println("Unable to save file: " + e);
                            } catch (IOException e) {
                                System.out.println("Unable to save file: " + e);
                            } finally {
                                try {
                                    if (is != null) {
                                        is.close();
                                    }
                                } catch (IOException e) {
                                    /*
                                     * no action required.
                                     */}
                            }
                                try {
                                    if (os != null) {
                                        os.close();
                                    }
                                } catch (IOException e) {
                                    /*
                                     * no action required.
                                       */
                                }
                            }
                        }
                        // todo provide feedback on the save function and provide a download list and download list lookup.
        });
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
