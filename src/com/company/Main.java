package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Main extends Application {
    public void start(Stage primaryStage){
        String url = "jdbc:sqlite:C:/Users/mille/IdeaProjects/sqlframads/TrainDb.sqlite";
        TrainView view=new TrainView();
        TrainModel model= new TrainModel(url);
        TrainController control = null;
        try {
            control = new TrainController(view, model);
            primaryStage.setTitle("Train Trip Finder");
            primaryStage.setScene(new Scene(view.asParent(), 600, 475));
            primaryStage.show();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args){
        launch(args);
    }
}
/*
public class Main {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:C:/Users/mille/IdeaProjects/sqlframads/TrainDb.sqlite";
        TrainModel TDB = new TrainModel(url);
        try {
            //conn=DriverManager.getConnection(url);
            TDB.connectToTrainData();
            //stmt=conn.createStatement();
            TDB.createStatement();
            //rs = stmt.executeQuery(sql);
            TDB.SQLQueryStation();
            //while(rs!=null && rs.next()){
            //    String name = rs.getString(1);
            //    System.out.println(name);
            //}
            //TDB.PstmtRetrieveDeparturesforStation();
            TDB.QueryForDepartures();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            //if(conn!=null){
            try {
                TDB.closeTrainDataConnection();
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }

        }
    }
}
*/