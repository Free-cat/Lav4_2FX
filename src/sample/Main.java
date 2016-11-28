package sample;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    public ListView prev;
    public ListView after;
    public Button schet;
    public Button zap;
    public TextField chisla;
    public Label notif;
    public ArrayList<Integer> nombers = new ArrayList<>();
    public ArrayList<Integer> nombers1 = new ArrayList<>();
    int a;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Лаба 4_2");
        primaryStage.setScene(new Scene(root, 520, 348));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }

    public void schitai(ActionEvent actionEvent) {
        if (nombers1.size() == 0){
            for (int i=0; i<a;){
                int temp = nombers.get(i) + nombers.get(i+1);
                nombers1.add(temp);
                i+=2;
            }
            addAfter();
            a=a/2;
        }
        else {
            if ((a%2) == 0) {
                nombers.clear();
                for (int i = 0; i < a; i++) {
                    nombers.add(nombers1.get(i));
                }
                nombers1.clear();
                for (int i = 0; i < a; ) {
                    int temp = nombers.get(i) + nombers.get(i + 1);
                    nombers1.add(temp);
                    i += 2;
                }
                addPrev();
                addAfter();
                a = a / 2;
            }
                else {
                nombers.clear();
                for (int i = 0; i < a; i++) {
                    nombers.add(nombers1.get(i));
                }
                addPrev();
                nombers1.clear();
                for (int i = 0; i < a-1; ) {
                    int temp = nombers.get(i) + nombers.get(i + 1);
                    nombers1.add(temp);
                    i += 2;
                }
                nombers1.add(nombers.get((a-1)));
                addAfter();
                a = (a+1) / 2;
                }
        }
    }

    public void zapolni(ActionEvent actionEvent) {
        nombers.clear();
        nombers1.clear();
        ObservableList data = FXCollections.observableArrayList();
        a = Integer.parseInt(chisla.getText());
        boolean check = check();
        if (check){
            for (int i=0; i<a; i++){
                nombers.add(1 + (int)(Math.random() * 50));
            }
            addPrev();
            schet.setDisable(false);
        }
    }

    public boolean check() {
        int a = Integer.parseInt(chisla.getText());
        if (a%2 != 0 || a<0){
            notif.setText("Вводите только четные положительные числа");
            notif.setVisible(true);
            return false;
        }
        else {
            notif.setVisible(false);
            return true;
        }
    }

    public void addPrev(){
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(nombers);
        prev.setItems(data);
        schet.setDisable(false);
    }

    public void addAfter(){
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(nombers1);
        after.setItems(data);
    }
}
