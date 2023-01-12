package com.example.inzynieria_sprint_n;

/**
 * Klasa handler do komunikacji pomiędzy FXML a innymi klasami, pobiera także dane od fxml
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class BudgetScreenHandler {
    @FXML
    private TextField missionNameInput;
    @FXML
    private TextField budgetInput;
    @FXML
    private ListView missionListView;

    private MissionListHandler listHandler;

    private List<Mission> missionList;

    public BudgetScreenHandler() throws IOException {

        listHandler = new MissionListHandler(missionList);
    }

    @FXML
    public void addMissionClick(){
        /*
        String missionName = missionNameInput.getText();
        String budgetString = budgetInput.getText();
        long budgetLong = Long.parseLong(budgetString);
        Mission mission = new Mission(missionName, budgetString, "UNP", false);
        missionList.add(mission);
        listHandler.addRecord(mission);//UNP - bez nadanego priorytetu
    */
    }


}
