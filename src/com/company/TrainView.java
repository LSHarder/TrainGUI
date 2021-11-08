package com.company;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TrainView {
    GridPane startview;
    Label StartStationLbl;
    Button exitBtn;
    ComboBox<String> StartStationComB;
    ObservableList<String> stations;
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
//Button setup
        exitBtn=new Button("Exit");
        startview.add(exitBtn,20,15);
        //exitBtn.setOnAction(e-> Platform.exit()); //Når den bliver aktiveret så gør den dette
//Tegner ComboBox
        StartStationComB=new ComboBox<>();
        startview.add(StartStationComB, 2,1);
    }
    public void configure(){
        StartStationComB.setItems(stations);
        StartStationComB.getSelectionModel().selectFirst();
    }

    public Parent asParent(){
        return startview;
    }
}
