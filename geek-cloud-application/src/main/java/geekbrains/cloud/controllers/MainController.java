package geekbrains.cloud.controllers;

import geekbrains.cloud.model.AbstractMessage;
import geekbrains.cloud.model.FileMessage;
import geekbrains.cloud.model.ListMessage;
import geekbrains.cloud.network.Net;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Path clientDir;

    public ListView<String> clientView;
    public ListView<String> serverView;
    private Net net;

 



    private void read() {
        try {
            while (true) {
               AbstractMessage message = net.read();
               if (message instanceof ListMessage lm){
                   serverView.getItems().clear();
                  serverView.getItems().addAll(lm.getFiles());
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getClientFiles() throws IOException {
        return Files.list(clientDir)
                .map(Path::getFileName)
                .map(Path::toString)
                .toList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            clientDir = Path.of("files");
            clientView.getItems().clear();
            clientView.getItems().addAll(getClientFiles());
            net = new Net("localhost", 8189);
            Thread readThread = new Thread(this::read);
            readThread.setDaemon(true);
            readThread.start();

//            clientView.getItems().addAll(getClientFiles());
//
//            clientView.setOnMouseClicked(mouseEvent -> {
//                if (mouseEvent.getClickCount() == 2) {
//                    String fileName = clientView.getSelectionModel().getSelectedItem();
//                    try {
//                        sendFile(fileName);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private List<String> getClientFiles() {
//        String[] files = clientDir.list();
//        if (files == null) {
//            return List.of();
//        }
//        return Arrays.stream(files)
//                .toList();
//    }

//    private void sendFile(String fileName) throws IOException {
//        net.getOutputStream().writeUTF("#file#");
//        net.getOutputStream().writeUTF(fileName);
//
//        File file = clientDir.toPath().resolve(fileName).toFile();
//        net.getOutputStream().writeLong(file.length());
//        byte[] buffer = new byte[256];
//        try (InputStream fis = new FileInputStream(file)) {
//            while (fis.available() > 0){
//                int readCount = fis.read(buffer);
//                net.getOutputStream().write(buffer, 0, readCount);
//            }
//        }
//        net.getOutputStream().flush();
//    }

    public void upload(ActionEvent actionEvent) throws IOException {
        String fileName = clientView.getSelectionModel().getSelectedItem();
        net.write(new FileMessage(clientDir.resolve(fileName)));
    }

    public void download(ActionEvent actionEvent) {

    }
}
