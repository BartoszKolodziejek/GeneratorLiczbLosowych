package sample;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class Controller implements Initializable {
    static double a;
    static double b;
    static int n;
    XYChart.Series liczby = new XYChart.Series();
    int i;


    @FXML
    public ScatterChart<?, ?> chart;
    @FXML
    Label l1 = new Label();
    @FXML
    Label l2 = new Label();
    @FXML
    TextField parametr1 = new TextField();
    @FXML
    TextField parametr2 = new TextField();
    @FXML
    TextField ile = new TextField();
    @FXML
    Label label = new Label();
    List<String> stringList = new ArrayList<String>();
    public static List<Double> doubles = new ArrayList<>();
    @FXML
    public ListView<String> listView = new ListView<>();
    ObservableList<String> list = FXCollections.observableArrayList();


    Generator generator;


    public void Trojkatny() {

        generuj();
        doubles = generator.trojkatny(n);


        int i = 0;
        for (double c : doubles) {
            stringList.add(i + 1 + " : " + c);
            System.out.println(c);
            i++;
        }
        listView.getItems().addAll(stringList);
        chart();


    }

    public void Gamma() {
        generuj();
        doubles = generator.gamma(n);


        int i = 0;
        for (double c : doubles) {
            stringList.add(i + 1 + " : " + c);
            System.out.println(c);
            i++;
        }
        listView.getItems().addAll(stringList);
        chart();

    }

    public void Rou() {
        generuj();
        doubles = generator.Rou(n);


        int i = 0;
        for (double c : doubles) {
            stringList.add(i + 1 + " : " + c);
            System.out.println(c);
            i++;
        }
        listView.getItems().addAll(stringList);
        chart();
    }

    public void Couchy() {
        generuj();
        doubles = generator.couchy(n);


        int i = 0;
        for (double c : doubles) {
            stringList.add(i + 1 + " : " + c);
            System.out.println(c);
            i++;
        }
        listView.getItems().addAll(stringList);
        chart();

    }

    public void Exp() {
        generuj();
        doubles = generator.exp(n);


        int i = 0;
        for (double c : doubles) {
            stringList.add(i + 1 + " : " + c);
            System.out.println(c);
            i++;
        }
        listView.getItems().addAll(stringList);
        chart();
    }

    public void Lognorm() {
        generuj();
        doubles = generator.lognorm(n);


        int i = 0;
        for (double c : doubles) {
            stringList.add(i + 1 + " : " + c);
            System.out.println(c);
            i++;
        }
        listView.getItems().addAll(stringList);
        chart();

    }

    public void Generuj() {
        switch (i) {
            case 1:
                Trojkatny();
                label.setText("");
                break;
            case 2:
                Gamma();
                label.setText("");
                break;
            case 3:
                Rou();
                label.setText("");
                break;
            case 4:
                Couchy();
                label.setText("");
                break;
            case 5:
                Exp();
                label.setText("");
                break;
            case 6:
                Lognorm();
                label.setText("");
                break;
            default:
                label.setText("Prosze wybrac rozklad");
                break;

        }

    }
    public void t(){
        i=1;
        l1.setText("a");
        l2.setText("b");
        parametr2.setVisible(true);
    }
    public void g(){
        i=2;
        i=1;
        l1.setText("alfa");
        parametr2.setVisible(false);
        l2.setText("");
    }
    public void r(){
        i=3;
        i=1;
        l1.setText("wartosc oczekiwana");
        l2.setText("odchyl stand");
        parametr2.setVisible(true);
    }
    public void c(){
        i=4;
        i=1;
        l1.setText("theta");
        l2.setText("lambda");
        parametr2.setVisible(true);
    }
    public void e(){
        i=5;
        i=1;
        l1.setText("theta");
        l2.setText("lambda");
        parametr2.setVisible(true);
    }
    public void l(){
        i=6;
        l1.setText("wartosc oczekiwana");
        l2.setText("odchyl stand");
        parametr2.setVisible(true);
    }


    public void generuj() {
        boolean c = true;


        for (int i = 0; i <= parametr1.getLength() - 1; i++) {
            //checking data
            if (parametr1.getCharacters().charAt(i) < '0' || parametr2.getCharacters().charAt(i) >'9') {
                label.setText("Prosze wprowadzic wartosc numeryczna");
                c = false;

            }

        }


        for (int i = 0; i <= parametr2.getLength() - 1; i++) {
            //checking data
            if (parametr2.getCharacters().charAt(i) < '0'
                    || parametr2.getCharacters().charAt(i) >'9' || ile.getLength()==0) {
                label.setText("Prosze wprowadzic wartosc numeryczna");
                c = false;

            }
        }

        for (int i = 0; i <= ile.getLength() - 1; i++) {
            //checking data
            if (ile.getCharacters().charAt(i) < '0'
                    || ile.getCharacters().charAt(i) > '9' || ile.getLength()==0) {
                label.setText("Prosze wprowadzic wartosc numeryczna");
                c = false;

            }
        }

         if(c){
        a = Double.parseDouble(parametr1.getText());
        b = Double.parseDouble(parametr2.getText());
        generator = new Generator(a, b, doubles);
        n = Integer.parseInt(ile.getText()) - 1;}

        System.out.println(a);
        System.out.println(b);
        System.out.println(n);


    }

    public void save() {

        String fileName = JOptionPane.showInputDialog("Nazwa pliku");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setSelectedFile(new File(fileName));
        jFileChooser.showSaveDialog(jFileChooser);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(jFileChooser.getSelectedFile()));
            for (String str : stringList) {
                writer.write(str);
                writer.newLine();

            }
            writer.close();

        } catch (Exception e) {

        }


    }

    public void refresh() {
        for (int i = listView.getItems().size() - 1; i >= 0; i--) {
            listView.getItems().remove(i);
            liczby.getData().remove(i);
            chart.getData().removeAll();

        }
        parametr2.setText("");
        parametr1.setText("");
        ile.setText("");


    }

    public void chart() {
        int i = 0;
        for (double d : doubles) {
            liczby.getData().add(new XYChart.Data<>(Integer.toString(i + 1), (d)));
            i++;
        }
        chart.getData().add(liczby);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(list);


        chart.setTitle("Wygenerowano: ");

        liczby.setName("LICZBY");


    }
}

