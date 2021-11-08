package com.company;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class TrainView {
    GridPane startview;
    Label StartStationLbl;
    Label EndStationLbl;
    Label TimeLbl;
    Button exitBtn;
    Button FindTrains;
    ComboBox<String> StartStationComB;
    ComboBox<String> EndStationComB;
    ComboBox<Integer> HoursComB;
    ComboBox<Integer> MinutesComB;
    TextArea textField;
    ObservableList<String> stations;
    ObservableList<Integer> hours;
    ObservableList<Integer> minutes;
    public TrainView(){
        startview=new GridPane();
        CreateView();
    }
    private void CreateView(){
        startview.setMinSize(300,200);
        startview.setPadding( new Insets(10,10,10,10));
        startview.setVgap(5);
        startview.setHgap(1);

        StartStationLbl = new Label("Select start station:");
        startview.add(StartStationLbl,1,1);
        EndStationLbl = new Label("Select destination");
        startview.add(EndStationLbl,1,2);
        TimeLbl = new Label("Select time");
        startview.add(TimeLbl,1,3);
//Button setup
        exitBtn=new Button("Exit");
        startview.add(exitBtn,20,20);
        FindTrains = new Button("Find departures");
        startview.add(FindTrains,1,7);
        //exitBtn.setOnAction(e-> Platform.exit()); //Når den bliver aktiveret så gør den dette
//Tegner ComboBox
        StartStationComB=new ComboBox<>();
        startview.add(StartStationComB, 2,1);

        EndStationComB = new ComboBox<>();
        startview.add(EndStationComB,2,2);

        HoursComB = new ComboBox<>();
        startview.add(HoursComB,2,3);

        MinutesComB = new ComboBox<Integer>();
        startview.add(MinutesComB,3,3);

        textField = new TextArea();
        startview.add(textField,1,9,15,10);

    }
    public void configure(){
        StartStationComB.setItems(stations);
        StartStationComB.getSelectionModel().selectFirst();

        EndStationComB.setItems(stations);
        EndStationComB.getSelectionModel().selectFirst();

        HoursComB.setItems(hours);
        HoursComB.getSelectionModel().selectFirst();

        MinutesComB.setItems(minutes);
        MinutesComB.getSelectionModel().selectFirst();

    }

    public Parent asParent(){
        return startview;
    }
}
