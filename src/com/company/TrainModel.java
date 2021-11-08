package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class TrainModel {
    Connection conn = null;
    String url = null;
    Statement stmt = null;
    PreparedStatement pstmt=null;
    ResultSet rs = null;

    TrainModel(String url){
        this.url=url;
    }

    public void connectToTrainData() throws SQLException{
        conn= DriverManager.getConnection(url);
    }

    public void closeTrainDataConnection() throws SQLException{
        if(conn!=null) {
            conn.close();
        }
    }

    public void createStatement() throws SQLException{
        this.stmt=conn.createStatement();
    }

    public ArrayList<String> SQLQueryStation() throws SQLException{
        ArrayList<String> stations=new ArrayList<>();
        String sql = "Select name from Station;";
        rs = stmt.executeQuery(sql);
        while(rs!=null && rs.next()){
            String name = rs.getString(1);
            System.out.println(name);
            stations.add(name);
        }
        return stations;
    }
    public void PstmtRetrieveDeparturesforStation() throws SQLException{
        System.out.println("which station do you wish to find depapartures for?");
        Scanner scanner = new Scanner(System.in);
        String departureStation = scanner.nextLine();
        System.out.println("What time do you wish to depart:");
        Float ft = scanner.nextFloat();
        String sql= "SELECT stationname, time FROM departure WHERE stationname = ? and time > ?;";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, departureStation);
        pstmt.setFloat(2, ft);
        rs=pstmt.executeQuery();
        while (rs!=null && rs.next()){
            System.out.println(rs.getString(1)+ " time:" + rs.getFloat(2));
        }
    }
    public ArrayList<TrainInfo> QueryForDepartures() throws SQLException{
        Scanner scanner=new Scanner(System.in);
        ArrayList<TrainInfo> trainInfos = new ArrayList<>();
        System.out.println("which Station do you want to depart form:");
        String stStation= scanner.nextLine();
        System.out.println("Which station do you want to go to?");
        String endStation = scanner.nextLine();
        System.out.println("At what time would you like to depart?");
        float departureTime= scanner.nextFloat();
        String sql="SELECT D1.trainid as train, D1.Stationname as start, D1.time as departure, "
                +"D2.stationname as destination, D2.time as arrival "
                +" FROM departure as D1 "
                +" JOIN departure as D2 ON D1.trainid=D2.trainid "
                +"WHERE D1.Stationname= ? AND D1.time > ? AND D2.Stationname = ? AND D1.time < D2.time ;";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,stStation);
        pstmt.setFloat(2,departureTime);
        pstmt.setString(3,endStation);
        rs=pstmt.executeQuery();
        while(rs!=null && rs.next()){
            String start=rs.getString("start");
            String end=rs.getString("destination");
            Integer train=rs.getInt("train");
            float depart=rs.getFloat("departure");
            float arr=rs.getFloat("arrival");
            System.out.println(train+ ": " + start + " at " + depart + " -> " + end + " at " + arr);
            TrainInfo t=new TrainInfo(train, start, end, depart,arr);
            trainInfos.add(t);
        }
        return trainInfos;
    }
}
class TrainInfo{
    Integer trainid=null;
    String startstation = null;
    String endstation=null;
    float departuretime;
    float arrivaltime;
        TrainInfo(Integer trainid, String start, String end, float depart, float arr){
            this.arrivaltime=arr;
        this.departuretime=depart;
        this.startstation = start;
        this.endstation = end;
        this.trainid = trainid;
    }
}