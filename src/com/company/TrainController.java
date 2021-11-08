package com.company;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.SQLException;
import java.util.ArrayList;

public class TrainController {
    TrainView view;
    TrainModel model;

    public TrainController(TrainView v, TrainModel m) throws SQLException {
        this.view=v;
        this.model=m;
        this.view.exitBtn.setOnAction(e-> Platform.exit());
        this.model.connectToTrainData();
        this.model.createStatement();
        this.view.stations = getStation();
        this.view.hours = getHours();
        this.view.minutes = getMinutes();
        this.view.FindTrains.setOnAction(e->HandlerPrintTrainRoutes(view.StartStationComB.getValue(),view.EndStationComB.getValue(),view.HoursComB.getValue(),view.MinutesComB.getValue(), view.textField));
        this.view.configure();

    }
    public void HandlerPrintTrainRoutes(String From, String To, Integer Hour, Integer Minutes, TextArea txtArea){
        txtArea.clear();
        txtArea.appendText(" Train, From Station: Departure -> To station: arrival \n");
        double time=(double) Hour +((double) Minutes/100);

        try {
            ArrayList<TrainInfo> trips= model.QueryForDepartures(From,To,time);
            for (int i = 0; i < trips.size(); i++) {
                String deptime = String.format("%.2f", trips.get(i).departuretime);
                String arrtime = String.format("%.2f", trips.get(i).arrivaltime);
                txtArea.appendText(i + ";" + trips.get(i).startstation + ": " + deptime + " -> " + trips.get(i).endstation + ": " + arrtime + "\n");

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<String> getStation() throws SQLException{
        ArrayList<String> stations = model.SQLQueryStation();
        ObservableList<String> stationnames = FXCollections.observableArrayList(stations);
        return stationnames;
    }

    public ObservableList<Integer> getHours(){
        ArrayList<Integer> hours = new ArrayList<>();
        for(int i= 0;i<24;i++){
            hours.add(i);
        }
        return FXCollections.observableArrayList(hours);
    }

    public ObservableList<Integer> getMinutes(){
        ArrayList<Integer> minutes = new ArrayList<>();
        for(int i= 0;i<60;i++){
            minutes.add(i);
        }
        return FXCollections.observableArrayList(minutes);
    }
}
