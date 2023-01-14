package com.example.inzynieria_sprint_n;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URISyntaxException;

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


    public NewMissionHandler() throws IOException, URISyntaxException {
        listHandler = new MissionListHandler();
    }

    @FXML
    public void addMissionClick() {

        String missionName = missionNameInput.getText();
        String budgetString = budgetInput.getText();
        Mission mission = new Mission(missionName, budgetString, "UNP", false);
        listHandler.addRecord(mission);//UNP - bez nadanego priorytetu


    }
}
