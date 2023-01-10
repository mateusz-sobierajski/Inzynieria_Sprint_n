package com.example.inzynieria_sprint_n;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class NewMissionHandler {
    @FXML
    private TextField missionNameInput;
    @FXML
    private TextField budgetInput;
    @FXML
    private Button missionAcceptBtn;
    @FXML
    private ListView missionListView;

    //TODO sprawdzić czy te tagi @FXML są potrzebne
    @FXML
    private String missionName;
    @FXML
    private long budget;
    @FXML
    private MissionListHandler listHandler;
    @FXML
    private String priority;

    private List<Mission> missionList = new ArrayList<Mission>();

    public NewMissionHandler() {
        listHandler = new MissionListHandler(missionList);
    }

    //TODO kontroler dla fxmla nadzorujący resztę klas
    @FXML
    public void addMissionClick() {

        String missionName = missionNameInput.getText();
        String budgetString = budgetInput.getText();
        long budgetLong = Long.parseLong(budgetString);
        Mission mission = new Mission(missionName, budgetString, "UNP", false);
        System.out.println(mission);
        missionList.add(mission);
        listHandler.addRecord(mission);//UNP - bez nadanego priorytetu


    }
    /*
    @FXML
    public void printOutput(){
    }
    */
}
