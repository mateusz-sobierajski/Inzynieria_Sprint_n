package com.example.inzynieria_sprint_n;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewMissionHandler implements Initializable {

    @FXML
    private TextField missionNameInput;
    @FXML
    private TextField budgetInput;
    @FXML
    private Button missionAcceptBtn;
    @FXML
    private ListView missionListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        missionListView.setCellFactory(listView -> new ListCell<Mission>() {
            @Override
            protected void updateItem(Mission item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    //TODO ZABAWA FORMATAMI ZEBY LADNIE WYGLADALO
                    setText(String.format( "%40s | ", item.getMissionName()) + item.getBudgetString() + "$ | " + item.getPriority() + " | " + item.isBlacklisted());
                }
            }
        });

        for (Mission mission : listHandler.missionArrayList){
            missionListView.getItems().add(mission);
        }
    }

    //TODO sprawdzić czy te tagi @FXML są potrzebne

    private String missionName;

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
