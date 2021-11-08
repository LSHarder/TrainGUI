package com.company;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        this.view.configure();
    }

    public ObservableList<String> getStation() throws SQLException{
        ArrayList<String> stations = model.SQLQueryStation();
        ObservableList<String> stationnames = FXCollections.observableArrayList(stations);
        return stationnames;
    }
}
